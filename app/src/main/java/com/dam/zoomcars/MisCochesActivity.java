package com.dam.zoomcars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dam.zoomcars.adapter.MisCochesAdapter;
import com.dam.zoomcars.model.Coche;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MisCochesActivity extends AppCompatActivity {

    RecyclerView rvMisCoches;
    private FirebaseUser firebaseUser;
    MisCochesAdapter misCochesAdapter;
    List<Coche> cocheList;
    private List<String> myCoches;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_coches);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        id = firebaseUser.getUid();

        rvMisCoches = findViewById(R.id.rvMiscoches);
        rvMisCoches.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvMisCoches.setLayoutManager(linearLayoutManager);
        cocheList = new ArrayList<>();
        misCochesAdapter = new MisCochesAdapter(this, cocheList);
        rvMisCoches.setAdapter(misCochesAdapter);

    }

    private void checkFollowing() {
        myCoches = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Coches");
//        reference.addListenerForSingleValueEvent(valueEventListener);
        Query query = FirebaseDatabase.getInstance().getReference("Coches")
                .orderByChild("publisher")
                .equalTo(id);
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    final ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            myCoches.clear();
            if (snapshot.exists()) {
                for (DataSnapshot sna : snapshot.getChildren()) {
                    Coche coche = sna.getValue(Coche.class);
                    cocheList.add(coche);
                }

                misCochesAdapter.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = rvMisCoches.getChildAdapterPosition(v);
                        Coche coche = cocheList.get(i);

                        Intent intentDatos = new Intent(MisCochesActivity.this, CocheDetailActivity.class);
                        intentDatos.putExtra("COCHE", coche);
                        intentDatos.putExtra("PUBLISHER_ID", coche.getId());
                        startActivity(intentDatos);

                    }
                });

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}