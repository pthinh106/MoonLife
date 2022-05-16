package com.example.moonlifemusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Listmucisc implements Serializable {

@SerializedName("IdPlayList")
@Expose
private String idPlayList;
@SerializedName("Ten")
@Expose
private String ten;
@SerializedName("HinhNen")
@Expose
private String hinhNen;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getHinhNen() {
return hinhNen;
}

public void setHinhNen(String hinhNen) {
this.hinhNen = hinhNen;
}

}