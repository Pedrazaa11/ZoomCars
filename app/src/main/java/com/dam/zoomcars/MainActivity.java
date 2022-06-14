package com.dam.zoomcars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    public void IrTutorial(View v) {
        Intent i = new Intent(this, TutorialActivity.class);
        startActivity(i);
    }

    public void IrInfo(View v) {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }

    public void cerrarSesion(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override public boolean onCreateOptionsMenu(Menu mimenu) {

        getMenuInflater().inflate(R.menu.menumain, mimenu);

        return true;

    }

    public boolean oOptionItemSelected(MenuItem opcion_menu) {

        int id= opcion_menu.getItemId();

        if (id==R.id.nav_misvh) {
            return true;
        }

        if (id==R.id.nav_mischats) {
            return true;
        }

        if (id==R.id.nav_miperfil) {
            IrMiPerfil(null);
            return true;
        }

        if (id==R.id.nav_tutorial) {
            IrTutorial(null);
            return true;
        }

        if (id==R.id.nav_sobrenosotros) {
            IrInfo(null);
            return true;
        }

        if (id==R.id.nav_cerrarsesion) {
            cerrarSesion(null);
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}