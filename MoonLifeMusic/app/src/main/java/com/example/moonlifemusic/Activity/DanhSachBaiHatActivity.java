package com.example.moonlifemusic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Adapter.DanhSachBaiHatAdapter;
import com.example.moonlifemusic.Model.Album;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    ExtendedFloatingActionButton floatingActionButton;
    Listmucisc listmucisc;
    ImageView imageView;
    ArrayList<Baihat> mangbaihat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    TextView textViewtitle, textViewtitletoorbar;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DateIntent();
        anhxa();
        initer();
        if( listmucisc != null && !listmucisc.getTen().equals("")){

            setValueInView(listmucisc.getTen(),listmucisc.getHinhNen());
            GetDataDanhSachBaiHatPlayList(listmucisc.getIdPlayList());

        }
        if( album != null && !album.getTenAlBum().equals("")){
            setValueInView(album.getTenAlBum(),album.getHinhAlbum());
            GetDataDanhSachBaiHatAlbum(album.getIdAlbum());
        }
    }

    private void setValueInView(String ten, String hinh) {
        textViewtitletoorbar.setText(ten);
        collapsingToolbarLayout.setTitleEnabled(false);
        textViewtitle.setText(ten);
        Picasso.get().load(hinh).into(imageView);
    }

    private void GetDataDanhSachBaiHatPlayList(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatPlayList(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                if(mangbaihat.size() > 0){
                    eventclickFloatButtion();
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    private void GetDataDanhSachBaiHatAlbum(String idpalbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatAlbum(idpalbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventclickFloatButtion();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void initer() {
        floatingActionButton.setEnabled(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) < 800){
                    textViewtitletoorbar.setVisibility(View.GONE);
                    textViewtitle.setVisibility(View.VISIBLE);
                }else{
                    textViewtitle.setVisibility(View.GONE);
                    textViewtitletoorbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void anhxa() {
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbardanhsachbaihat);
        coordinatorLayout = findViewById((R.id.coordinatorLayoutdanhsachphat));
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.rcv_danhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbtn);
        imageView = findViewById(R.id.imgdanhsachbaihat);
        textViewtitle = findViewById(R.id.txttitle);
        appBarLayout = findViewById(R.id.appbarlayout);
        textViewtitletoorbar = findViewById(R.id.txttitletoolbar);
    }

    private void DateIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("isplaylist")){
                listmucisc = (Listmucisc) intent.getSerializableExtra("isplaylist");
            }
        }
        if(intent != null){
            if(intent.hasExtra("idalbum")){
                album = (Album) intent.getSerializableExtra("idalbum");
            }
        }

    }
    private void eventclickFloatButtion(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacProActivity.class);
                intent.putExtra("mangbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }
}