package com.dam.zoomcars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.zoomcars.R;
import com.dam.zoomcars.model.Coche;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MisCochesAdapter extends RecyclerView.Adapter<MisCochesAdapter.CochesViewHolder> implements View.OnClickListener{

    Context mContext;
    List<Coche> mPosts;
    FirebaseUser firebaseUser;

    private View.OnClickListener listener;

    public MisCochesAdapter(Context mContext, List<Coche> mPosts){
        this.mContext = mContext;
        this.mPosts = mPosts;
    }

    public void setListener(View.OnClickListener listener){ this.listener = listener;}

    @NonNull
    @Override
    public CochesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.miscoches_layout, parent, false);
        v.setOnClickListener(this);
        MisCochesAdapter.CochesViewHolder cvh = new MisCochesAdapter.CochesViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CochesViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Coche coch = mPosts.get(position);

       /* Glide.with(mContext).load(event.getImageUrl())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(holder.ivImageEvento);*/

        if (coch.getDescripcion().equals("")){
            holder.tvMarcaModelo.setVisibility(View.GONE);
        }else{
            holder.tvMarcaModelo.setVisibility(View.VISIBLE);
            holder.tvMarcaModelo.setText(coch.getMarcaM());
            holder.tvAñofab.setText(coch.getAñofab());
            holder.tvTipoCombus.setText(coch.gettCombus());
            holder.tvKm.setText(coch.getKm());
            holder.tvDescrip.setText(coch.getDescripcion());
        }

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class CochesViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImageCoche;
        TextView tvMarcaModelo;
        TextView tvAñofab;
        TextView tvTipoCombus;
        TextView tvKm;
        TextView tvDescrip;


        public CochesViewHolder(@NonNull View itemView) {
            super(itemView);

        ivImageCoche = itemView.findViewById(R.id.ivImageEventoMC);
        tvMarcaModelo = itemView.findViewById(R.id.tvMarcaMC);
        tvAñofab = itemView.findViewById(R.id.tvTipoCombusMC);
        tvTipoCombus = itemView.findViewById(R.id.tvTipoCombusMC);
        tvKm = itemView.findViewById(R.id.tvKmMC);
        tvDescrip = itemView.findViewById(R.id.tvDescripcionMC);



        }
    }
}
