package com.example.moonlifemusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.PlayNhacProActivity;
import com.example.moonlifemusic.Adapter.PlayDanhSachBaiHatAdapter;
import com.example.moonlifemusic.R;

public class PlaydanhsachbaihatFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayDanhSachBaiHatAdapter playDanhSachBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playdanhsachbaihat,container,false);
        recyclerView = view.findViewById(R.id.rcv_playbaihat);
        if(PlayNhacProActivity.arrayListbaihat.size() > 0){
            playDanhSachBaiHatAdapter = new PlayDanhSachBaiHatAdapter(getActivity(), PlayNhacProActivity.arrayListbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playDanhSachBaiHatAdapter);
        }
        return view;
    }
}
