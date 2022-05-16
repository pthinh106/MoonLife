package com.example.moonlifemusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Adapter.DanhSachAllPlayListdapter;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCacPlayListActivity extends AppCompatActivity {
    RecyclerView recyclerViewplaylistall;
    Toolbar toolbarplaylistall;
    String platlist;
    DanhSachAllPlayListdapter danhSachAllPlayListdapter;
    ArrayList<Listmucisc> mangPlayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cac_play_list);
        DateIntent();
        anhxa();
//
        if(!platlist.equals("")){
            initer();
            GetDataPlayList(platlist);
        }
    }

    private void GetDataPlayList(String idchude) {
        Dataservice dataservice = APIService.getService();
        Call<List<Listmucisc>> callback = dataservice.GetDanhSachPlayList(idchude);
        callback.enqueue(new Callback<List<Listmucisc>>() {
            @Override
            public void onResponse(Call<List<Listmucisc>> call, Response<List<Listmucisc>> response) {
                mangPlayList = (ArrayList<Listmucisc>) response.body();
                danhSachAllPlayListdapter = new DanhSachAllPlayListdapter(DanhSachCacPlayListActivity.this,mangPlayList);
                recyclerViewplaylistall.setLayoutManager(new GridLayoutManager(DanhSachCacPlayListActivity.this,3));
                recyclerViewplaylistall.setAdapter(danhSachAllPlayListdapter);
            }
            @Override
            public void onFailure(Call<List<Listmucisc>> call, Throwable t) {
            }
        });
    }

    private void DateIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("idChuDe")){
                platlist =  (String) intent.getSerializableExtra("idChuDe");
            }
        }
    }

    private void initer() {
        setSupportActionBar(toolbarplaylistall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(platlist.equals("1") ){
            getSupportActionBar().setTitle("Có Thể Bạn Muốn Nghe");
        }else if(platlist.equals("2") ){
            getSupportActionBar().setTitle("Lựa Chọn Hôm Nay");
        }else if(platlist.equals("3")  ){
            getSupportActionBar().setTitle("XONE's CORNER");
        }else if(platlist.equals("4") ){
            getSupportActionBar().setTitle("TOP 100");
        }
        toolbarplaylistall.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        recyclerViewplaylistall = findViewById(R.id.rcv_playlistall);
        toolbarplaylistall = findViewById(R.id.toolbarplaylistall);
    }
}