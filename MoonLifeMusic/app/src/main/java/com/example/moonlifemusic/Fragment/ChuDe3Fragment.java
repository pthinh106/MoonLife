package com.example.moonlifemusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.DanhSachCacPlayListActivity;
import com.example.moonlifemusic.Adapter.Chude1Adapter;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuDe3Fragment extends Fragment {
     View view;
     Chude1Adapter chude1Adapter;
     RecyclerView recyclerView;
     TextView textView,textViewxemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude3,container,false);
        recyclerView = view.findViewById(R.id.rcv_chude3);
        textView = view.findViewById(R.id.txtchude3);
        textViewxemthem = view.findViewById(R.id.xemhthemchude3);
        textViewxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachCacPlayListActivity.class);
                intent.putExtra("idChuDe","3");
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Listmucisc>> callback = dataservice.GetDataChuDe3();
        callback.enqueue(new Callback<List<Listmucisc>>() {
            @Override
            public void onResponse(Call<List<Listmucisc>> call, Response<List<Listmucisc>> response) {
                ArrayList<Listmucisc> chude2Playlist = (ArrayList<Listmucisc>) response.body();
                chude1Adapter = new Chude1Adapter(getActivity(),chude2Playlist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(chude1Adapter);
            }

            @Override
            public void onFailure(Call<List<Listmucisc>> call, Throwable t) {

            }
        });
    }
}
