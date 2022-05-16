package com.example.moonlifemusic.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moonlifemusic.Adapter.DanhsachAlbumAdapter;
import com.example.moonlifemusic.Model.Album;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAllAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewpallAlbum;
    Toolbar toolbarallAlum;
    DanhsachAlbumAdapter danhsachAlbumAdapter;
    ArrayList<Album> arrayAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_all_album);
        anhxa();
        setSupportActionBar(toolbarallAlum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ALBUM");
        toolbarallAlum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        GetDataAlbum();
    }
    private void GetDataAlbum() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetDataallAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                arrayAlbum = (ArrayList<Album>) response.body();
                danhsachAlbumAdapter = new DanhsachAlbumAdapter(DanhSachAllAlbumActivity.this,arrayAlbum);
                recyclerViewpallAlbum.setLayoutManager(new GridLayoutManager(DanhSachAllAlbumActivity.this,3));
                recyclerViewpallAlbum.setAdapter(danhsachAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
    private void anhxa() {
        recyclerViewpallAlbum = findViewById(R.id.rcv_allalbum);
        toolbarallAlum = findViewById(R.id.toolbarallalbum);
    }
}