package com.dam.zoomcars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dam.zoomcars.model.Coche;

public class CocheDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMarcaCD;
    TextView tvAñofabCD;
    TextView tvCVCD;
    TextView tvKMCD;
    TextView tvPuertasCD;
    TextView tvMarchasCD;
    TextView tvCombusCD;
    TextView tvDescripCD;

    ImageView ivCocheFoto;

    Button btnMsjCD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coche_detail);

        tvMarcaCD = findViewById(R.id.tvMarcamCD);
        tvAñofabCD = findViewById(R.id.tvAñofabCD);
        tvCVCD = findViewById(R.id.tvCVCD);
        tvKMCD = findViewById(R.id.tvKmMC);
        tvPuertasCD = findViewById(R.id.tvPuertasCD);
        tvMarchasCD = findViewById(R.id.tvMarchasCD);
        tvCombusCD = findViewById(R.id.tvCombusCD);
        tvDescripCD = findViewById(R.id.tvDescripcionCD);

        ivCocheFoto = findViewById(R.id.ivCocheFotoCD);

        btnMsjCD = findViewById(R.id.btnEnviarMensajeCD);

        btnMsjCD.setOnClickListener(this);

        Coche c =getIntent().getParcelableExtra("COCHES");

        Glide.with(this)
                .load(c.getImageURL())
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.white))).into(ivCocheFoto);

        tvMarcaCD.setText(c.getMarcaM());
        tvAñofabCD.setText(c.getAñofab());
        tvCVCD.setText(c.getCV());
        tvKMCD.setText(c.getKm());
        tvPuertasCD.setText(c.getPuertas());
        tvMarchasCD.setText(c.gettMarchas());
        tvCombusCD.setText(c.gettCombus());
        tvDescripCD.setText(c.getDescripcion());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(CocheDetailActivity.this, MisChatsActivity.class);
        startActivity(i);
    }
}