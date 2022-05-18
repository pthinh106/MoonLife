package com.example.moonlifemusic.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Adapter.TimKiemAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.example.moonlifemusic.Adapter.TimKiemAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Baihat> baihat;
    private TimKiemAdapter timKiemAdapter;
    private Dataservice dataservice;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        toolbar = findViewById(R.id.toolbarseachview);
        recyclerView = findViewById(R.id.rcv_search);
        recyclerView.setHasFixedSize(true);
    }
    public void getSearch(String key){
        dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetTimKiem(key);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihat = (ArrayList<Baihat>) response.body();
                if(baihat.size() > 0){
                    timKiemAdapter = new TimKiemAdapter(TimKiemActivity.this,baihat);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(TimKiemActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(timKiemAdapter);
                    timKiemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.seachview,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.seachviews).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }
}