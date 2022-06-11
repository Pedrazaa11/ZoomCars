package com.dam.zoomcars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrMiPerfil(View v) {
        Intent i = new Intent(this, MiPerfilActivity.class);
        startActivity(i);
    }

    public void salirApp(View v) {
        finish();
    }

    @Override public boolean onCreateOptionsMenu(Menu mimenu) {

        getMenuInflater().inflate(R.menu.menumain, mimenu);

        return true;

    }

    public boolean oOptionItemSelected(MenuItem opcion_menu) {

        int id= opcion_menu.getItemId();

        if (id==R.id.MisVH) {
            return true;
        }

        if (id==R.id.MisChats) {
            return true;
        }

        if (id==R.id.MiPerfil) {
            IrMiPerfil(null);
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}