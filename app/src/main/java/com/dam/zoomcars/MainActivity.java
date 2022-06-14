package com.dam.zoomcars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override public boolean onCreateOptionsMenu(Menu mimenu) {

        getMenuInflater().inflate(R.menu.menumain, mimenu);

        return true;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_misvh:
                Intent i = new Intent(MainActivity.this, MisVhActivity.class);
                startActivity(i);
                break;
            case R.id.nav_mischats:
                Intent i2 = new Intent(MainActivity.this, MisChatsActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_miperfil:
                Intent i3 = new Intent(this, MiPerfilActivity.class);
                startActivity(i3);
                break;
            case R.id.nav_tutorial:
                Intent i4 = new Intent(this, TutorialActivity.class);
                startActivity(i4);
                break;
            case R.id.nav_sobrenosotros:
                Intent i5 = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(i5);
                break;
            case R.id.nav_cerrarsesion:
                MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(MainActivity.this);
                alertDialog.setMessage("¿Estás seguro que deseas salir?");
                alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        finish();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.create().show();
                break;
        }

        return true;
    }
}