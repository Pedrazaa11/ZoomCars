package com.dam.zoomcars.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dam.zoomcars.MainActivity;
import com.dam.zoomcars.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.HashMap;


public class AñadirVhFragment extends Fragment {

    Uri imageUri;
    String myUrl = "";
    StorageTask uploadTask;
    StorageReference storageReference;

    EditText etMarcaModelo;
    EditText etAñoFab;
    EditText etCV;
    EditText etPuertas;
    EditText etCombustible;
    EditText etCambio;
    EditText etKm;
    EditText etDescripcion;
    ImageView ivFotoCoche;
    ImageView ibAdjuntarFoto;
    Button btnPostCoche;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aniadir_vh, container, false);

        etMarcaModelo = view.findViewById(R.id.etMarcaMVH);
        etAñoFab = view.findViewById(R.id.etAñoVH);
        etCV = view.findViewById(R.id.etCvVH);
        etPuertas = view.findViewById(R.id.etPuertasVH);
        etCombustible = view.findViewById(R.id.etCombustibleVH);
        etCambio = view.findViewById(R.id.etCambioVH);
        etKm = view.findViewById(R.id.etKmVH);
        etDescripcion = view.findViewById(R.id.etDescripcionVH);

        ivFotoCoche = view.findViewById(R.id.ivFotoCoche);
        ibAdjuntarFoto = view.findViewById(R.id.ibAdjuntarFoto);

        btnPostCoche = view.findViewById(R.id.btnPostCoche);

        storageReference = FirebaseStorage.getInstance().getReference("Coches");

        btnPostCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMarcaModelo.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir una marca y modelo de coche", Toast.LENGTH_SHORT).show();
                }else if (etAñoFab.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir un año de fabricacion del coche", Toast.LENGTH_SHORT).show();
                }else if (etCV.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir los caballos del coche", Toast.LENGTH_SHORT).show();
                }else if (etPuertas.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir el Nº de puertas del coche", Toast.LENGTH_SHORT).show();
                }else if (etCombustible.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir un tipo de combustible", Toast.LENGTH_SHORT).show();
                }else if (etCambio.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir un tipo de cambio de marchas", Toast.LENGTH_SHORT).show();
                }else if (etKm.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir el kilometraje del coche", Toast.LENGTH_SHORT).show();
                }else if (etDescripcion.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debes escribir una descripcion del vehiculo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibAdjuntarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete la acción usando"), 1);
            }
        });


        return view;
    }


    //TODO: metodo para pasar la imagen a Ploopfragment
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Cargamos la imagen seleccionada en el ImageView
            imageUri = data.getData();
            Glide.with(ivFotoEvento.getContext()).load(imageUri)
                    .into(ivFotoEvento);
        }

    } */

    private void uploadImage() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Publicando Coche");
        progressDialog.show();

        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "");
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        myUrl = downloadUri.toString();

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coches");

                        String postId = reference.push().getKey();

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("id", postId);
                        hashMap.put("marcamodelo", etMarcaModelo.getText().toString());
                        hashMap.put("añodefabricacion", etAñoFab.getText().toString());
                        hashMap.put("cv", etCV.getText().toString());
                        hashMap.put("puertas", etPuertas.getText().toString());
                        hashMap.put("combustible", etCombustible.getText().toString());
                        hashMap.put("cambio", etCambio.getText().toString());
                        hashMap.put("kilometraje", etKm.getText().toString());
                        hashMap.put("descripcion", etDescripcion.getText().toString());
                        hashMap.put("imageUrl", myUrl);
                        hashMap.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());

                        reference.child(postId).setValue(hashMap);
                        progressDialog.dismiss();
                        startActivity(new Intent(getActivity(), MainActivity.class));

                    } else {
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(getContext(), "No has seleccionado ninguna imagen", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }



}