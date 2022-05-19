package com.example.moonlifemusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moonlifemusic.Adapter.DanhSachBaiHatAdapter;
import com.example.moonlifemusic.Adapter.DanhSachBaiHatCaNhanAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatCaNhanActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CircleImageView circleImageView;
    ArrayList<Baihat> mangbaihat;
    DanhSachBaiHatCaNhanAdapter danhSachBaiHatCaNhanAdapter;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat_ca_nhan);
        toolbar = findViewById(R.id.toolbarbaihatcanhan);
        recyclerView = findViewById(R.id.rcv_baihatcanhan);
        circleImageView = findViewById(R.id.imgplaycanhang);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        if(intent.hasExtra("user")){
            user = intent.getStringExtra("user");
            getData(user);
        }
    }

    private void getData(String user) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhSachBaiHatCanhan(user);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatCaNhanAdapter = new DanhSachBaiHatCaNhanAdapter(DanhSachBaiHatCaNhanActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatCaNhanActivity.this));
                recyclerView.setAdapter(danhSachBaiHatCaNhanAdapter);
                if(mangbaihat.size() > 0){
                    clickeventbtn();
                }
            }
            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    private  void clickeventbtn(){
        circleImageView.setEnabled(true);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PlayNhacActivity.mediaPlayer != null){
                    PlayNhacActivity.mediaPlayer.stop();
                }
                Intent intent = new Intent(DanhSachBaiHatCaNhanActivity.this, PlayNhacProActivity.class);
                intent.putExtra("mangbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}