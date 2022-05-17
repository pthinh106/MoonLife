package com.example.moonlifemusic.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moonlifemusic.Adapter.MyViewPagerAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private CircleImageView circleImageView;
    private TextView textViewtenbaihat, textViewtencasi;
    private ImageButton btnpre, btnplay, btnnext;
    private static ArrayList<Baihat> arrayList;
    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;
    private  static String user;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    public static int position2 = 0,action;
    private boolean repeat = false, checkrandom = false, isplaying;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                isplaying = intent.getBooleanExtra("status_player", false);
                action = intent.getIntExtra("action_music", 0);
                duration = intent.getIntExtra("duration_music", 0);
                timeValue = intent.getIntExtra("seektomusic", 0);
                position = intent.getIntExtra("position_music", 0);
                position2 = position;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("send_data_to_activity"));
        anhxa();
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        mViewPager2.setAdapter(myViewPagerAdapter);
        mBottomNavigationView.setSelectedItemId(R.id.bottom_home);

        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.bottom_home){
                    mViewPager2.setCurrentItem(0);
                }else if(id == R.id.bottom_rank){
                    mViewPager2.setCurrentItem(1);
                }else if(id == R.id.bottom_sreach){
                    mViewPager2.setCurrentItem(2);
//                    Intent intent = new Intent(MainActivity.this, TimKiemActivity.class);
//                    startActivity(intent);
                }
//                else if(id == R.id.bottom_mypgage){
//                    mViewPager2.setCurrentItem(3);
//                }
                return true;
            }
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_rank).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_sreach).setChecked(true);
                        break;
//                    case 3:
//                        mBottomNavigationView.getMenu().findItem(R.id.bottom_mypgage).setChecked(true);
//                        break;
                }
            }
        });
        mViewPager2.setUserInputEnabled(false);
//        DateIntent();
    }

    private void anhxa() {
        mViewPager2 = findViewById(R.id.view_pager2);
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        linearLayout = findViewById(R.id.linearmusicactivity);
        circleImageView = findViewById(R.id.imgplaybaihatdanhsach1);
        textViewtenbaihat = findViewById(R.id.txtplaybaihatdanhsach1);
        textViewtencasi = findViewById(R.id.txtplaytencasibaihat1);
        btnpre = findViewById(R.id.btnprevious1);
        btnplay = findViewById(R.id.btnplaypause1);
        btnnext = findViewById(R.id.btnnext1);
        arrayList = PlayNhacProActivity.arrayListbaihat;

    }

    private void DateIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("user")){
                user = intent.getStringExtra("user");
            }
        }

    }
    private void isplay(){
        if(isplaying){
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
    public static String getuser(){
        if(user != null){
            return  user;
        }
        return null;
    }
    public static void setuser(){
        user = null;
    }


}