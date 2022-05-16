package com.example.moonlifemusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.moonlifemusic.Activity.PlayNhacActivity;
import com.example.moonlifemusic.Activity.PlayNhacProActivity;
import com.example.moonlifemusic.Adapter.BangXepHangAdapter;
import com.example.moonlifemusic.Adapter.ImgViewTopXepHangAdaper;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangXepHangFragment extends Fragment {

    private View mView;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    CircleImageView circleImageView;
    BangXepHangAdapter bangXepHangAdapter;
    ViewPager viewPager;
    ImgViewTopXepHangAdaper imgViewTopXepHangAdaper;
    private Handler handler = new Handler();
    private Runnable runnable =new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem() == imgViewTopXepHangAdaper.getCount()-1){
                viewPager.setCurrentItem(0);
            }else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    } ;

    ArrayList<Baihat> arrayBaiHat,arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bang_xep_hang,container,false);
        anhxa();
        getData();
        getDataimg();
        return mView;
    }

    private void getDataimg() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDataBangXepHangtop5();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                arrayList = (ArrayList<Baihat>) response.body();
                imgViewTopXepHangAdaper = new ImgViewTopXepHangAdaper(getActivity(),arrayList);
                viewPager.setAdapter(imgViewTopXepHangAdaper);
                handler.postDelayed(runnable,2000);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable,2000);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });

    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDataBangXepHang();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                arrayBaiHat = (ArrayList<Baihat>) response.body();
                bangXepHangAdapter = new BangXepHangAdapter(getActivity(),arrayBaiHat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(bangXepHangAdapter);
                clickeventbtn();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        coordinatorLayout = mView.findViewById(R.id.coordinatorLayoutbangxephang);
        collapsingToolbarLayout = mView.findViewById(R.id.collapsingtoolbarbangxephang);
        toolbar = mView.findViewById(R.id.toolbarbangxephang);
        recyclerView = mView.findViewById(R.id.rcv_bangxephang);
        circleImageView = mView.findViewById(R.id.imgplaybangxephang);
        viewPager = mView.findViewById(R.id.viewpagerimg);
        circleImageView.setEnabled(false);

    }
    private  void clickeventbtn(){
        circleImageView.setEnabled(true);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PlayNhacActivity.mediaPlayer != null){
                    PlayNhacActivity.mediaPlayer.stop();
                }
                Intent intent = new Intent(getActivity(), PlayNhacProActivity.class);
                intent.putExtra("mangbaihat", arrayBaiHat);
                startActivity(intent);
            }
        });
    }

}
