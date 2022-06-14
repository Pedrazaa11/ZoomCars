package com.dam.zoomcars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dam.zoomcars.model.Usuario;

public class MiPerfilActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUsername;
    TextView tvFullname;
    Button btnEditperfil;
    Usuario user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        tvUsername = findViewById(R.id.tvUsuarioM);
        tvFullname = findViewById(R.id.tvNombreM);
        btnEditperfil = findViewById(R.id.btnEditperfilM);

        btnEditperfil.setOnClickListener(this);

        tvFullname.setText(user.getFullname());
        tvUsername.setText(user.getUsername());


    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnEditperfil)){
            Intent i = new Intent(MiPerfilActivity.this, EditarPerfilActivity.class);
            startActivity(i);
        }

    }
}