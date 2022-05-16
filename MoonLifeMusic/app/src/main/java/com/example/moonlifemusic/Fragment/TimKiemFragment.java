package com.example.moonlifemusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class TimKiemFragment extends Fragment {

    private View mView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Baihat> baihat;
    private TimKiemAdapter timKiemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = mView.findViewById(R.id.toolbarseachviews);
        recyclerView = mView.findViewById(R.id.rcv_searchs);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        getSearch("");
        return mView;
    }
    public void getSearch(String key){
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetTimKiem(key);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihat = (ArrayList<Baihat>) response.body();
                if(baihat.size()>0){
                    timKiemAdapter = new TimKiemAdapter(getActivity(),baihat);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(timKiemAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.seachview,menu);
        MenuItem menuItem = menu.findItem(R.id.seachviews);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSearch(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
