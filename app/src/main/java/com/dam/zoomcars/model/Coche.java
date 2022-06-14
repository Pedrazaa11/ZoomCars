package com.dam.zoomcars.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Coche implements Parcelable {

    private String id;
    private String marcaM;
    private String añofab;



    private String CV;
    private String puertas;
    private String tCombus;
    private String tMarchas;
    private String km;
    private String descripcion;
    private String imageURL;

    public Coche(String id,String marcaM, String añofab, String CV, String puertas, String tCombus, String tMarchas, String km, String descripcion, String imageURL) {
        this.id = id;
        this.marcaM = marcaM;
        this.añofab = añofab;
        this.CV = CV;
        this.puertas = puertas;
        this.tCombus = tCombus;
        this.tMarchas = tMarchas;
        this.km = km;
        this.descripcion = descripcion;
        this.imageURL = imageURL;
    }



    public Coche(){}





    protected Coche(Parcel in) {
        id = in.readString();
        marcaM = in.readString();
        añofab = in.readString();
        CV = in.readString();
        puertas = in.readString();
        tCombus = in.readString();
        tMarchas = in.readString();
        km = in.readString();
        descripcion = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Coche> CREATOR = new Creator<Coche>() {
        @Override
        public Coche createFromParcel(Parcel in) {
            return new Coche(in);
        }

        @Override
        public Coche[] newArray(int size) {
            return new Coche[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarcaM() {
        return marcaM;
    }

    public void setMarcaM(String marcaM) {
        this.marcaM = marcaM;
    }

    public String getAñofab() {
        return añofab;
    }

    public void setAñofab(String añofab) {
        this.añofab = añofab;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public String gettCombus() {
        return tCombus;
    }

    public void settCombus(String tCombus) {
        this.tCombus = tCombus;
    }

    public String gettMarchas() {
        return tMarchas;
    }

    public void settMarchas(String tMarchas) {
        this.tMarchas = tMarchas;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }



    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(marcaM);
        dest.writeString(añofab);
        dest.writeString(CV);
        dest.writeString(puertas);
        dest.writeString(tCombus);
        dest.writeString(tMarchas);
        dest.writeString(km);
        dest.writeString(descripcion);
        dest.writeString(imageURL);

    }
}
