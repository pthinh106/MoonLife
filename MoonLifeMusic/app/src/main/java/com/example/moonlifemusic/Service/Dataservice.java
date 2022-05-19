package com.example.moonlifemusic.Service;
import com.example.moonlifemusic.Model.Album;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<QuangCao>>  GetDataBanner();
    @GET("chude1.php")
    Call<List<Listmucisc>> GetDataChuDe1();
    @GET("chude2.php")
    Call<List<Listmucisc>> GetDataChuDe2();
    @GET("chude3.php")
    Call<List<Listmucisc>> GetDataChuDe3();
    @GET("Album.php")
    Call<List<Album>> GetDataAlbum();
    @GET("cothebanthich.php")
    Call<List<Baihat>> GetDataBaiHat();
    @GET("top100.php")
    Call<List<Listmucisc>> GetDataTop100();
    @GET("allAlbum.php")
    Call<List<Album>> GetDataallAlbum();
    @GET("bangxephang.php")
    Call<List<Baihat>> GetDataBangXepHang();
    @GET("bangxephangtop5.php")
    Call<List<Baihat>> GetDataBangXepHangtop5();



    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhSachBaiHatPlayList(@Field("idplaylist") String idplaylist);
    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<Baihat>> GetTimKiem(@Field("key") String keywork);
    @FormUrlEncoded
    @POST("danhsachbaihatalbum.php")
    Call<List<Baihat>> GetDanhSachBaiHatAlbum(@Field("idalbum") String idalbum);
    @FormUrlEncoded
    @POST("danhsachcacplaylist.php")
    Call<List<Listmucisc>> GetDanhSachPlayList(@Field("idchude") String idchude);
    @FormUrlEncoded
    @POST("account.php")
    Call< String > getLogin(@Field("user") String user , @Field("pass") String pass );
    @FormUrlEncoded
    @POST("danhsachbaihatcanhan.php")
    Call<List<Baihat>> GetDanhSachBaiHatCanhan(@Field("user") String user);
    @FormUrlEncoded
    @POST("adddanhsachbaihatcanhan.php")
    Call<String> AddDanhSachBaiHatCanhan(@Field("user") String user,@Field("idbaihat") String idbaihat);
    @FormUrlEncoded
    @POST("addaccount.php")
    Call<String> AddAcount(@Field("user") String user,@Field("pass") String pass,@Field("name") String name,@Field("phone") String phone,@Field("email") String email);



}
