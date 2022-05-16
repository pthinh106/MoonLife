package com.example.moonlifemusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlBum")
    @Expose
    private String tenAlBum;
    @SerializedName("TenCaSi")
    @Expose
    private String tenCaSi;
    @SerializedName("HinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlBum() {
        return tenAlBum;
    }

    public void setTenAlBum(String tenAlBum) {
        this.tenAlBum = tenAlBum;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }
}