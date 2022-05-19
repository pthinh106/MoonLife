package com.example.moonlifemusic.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.DangNhapActivity;
import com.example.moonlifemusic.Activity.DanhSachBaiHatCaNhanActivity;
import com.example.moonlifemusic.Activity.MainActivity;
import com.example.moonlifemusic.Activity.PlayListCaNhanActivity;
import com.example.moonlifemusic.Adapter.CoTheBanThichAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.example.moonlifemusic.Service_Local.ForegroundServiceControl;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaNhanFragment extends Fragment {

    private CardView cardViewcbaihat , cardViewplaylist;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private View mView;
    private TextView textViewuser, textViewlogin;
    private String user,id;
    boolean check = true;
    private CoTheBanThichAdapter coTheBanThichAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ca_nhan,container,false);
        anhxa();
        user = MainActivity.getuser();
        id = MainActivity.getId();
        if(user == null){
            textViewlogin.setText("Login");
        }else{
            textViewlogin.setText("Logout");
            textViewuser.setText(MainActivity.getuser());
        }

        evenclick();
        getData();
        return mView;
    }

    private void evenclick() {
        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = MainActivity.getuser();
                Toast.makeText(getActivity(),MainActivity.getuser(),Toast.LENGTH_SHORT).show();
                if(user == null){
                    Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                    startActivity(intent);
                }else{
                    MainActivity.setuser();
                    MainActivity.setid();
                    user = MainActivity.getuser();
                    textViewlogin.setText("LogIn");
                    textViewuser.setText("Khách");
//                    getActivity().finish();
//                    getActivity().startActivity(new Intent());
                }
            }
        });
        cardViewcbaihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getId() == null){
                    Toast.makeText(getActivity(),"Bạn Chưa Đăng Nhập",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), DanhSachBaiHatCaNhanActivity.class);
                    intent.putExtra("user",id);
                    startActivity(intent);
                }
            }
        });
        cardViewplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getId() == null){
                    Toast.makeText(getActivity(),"Bạn Chưa Đăng Nhập",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), PlayListCaNhanActivity.class);
                    intent.putExtra("user",id);
                    startActivity(intent);
                }
            }
        });
    }

    private void anhxa() {
        textViewuser = mView.findViewById(R.id.usernamelg);
        textViewlogin = mView.findViewById(R.id.txtlogin);
        recyclerView = mView.findViewById(R.id.gioithieuchoban);
        cardViewcbaihat = mView.findViewById(R.id.canhanbaihat);
        cardViewplaylist = mView.findViewById(R.id.canhanplaylist);
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDataBaiHat();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> ArrayBaiHat = (ArrayList<Baihat>) response.body();
                coTheBanThichAdapter = new CoTheBanThichAdapter(getActivity(),ArrayBaiHat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(coTheBanThichAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
