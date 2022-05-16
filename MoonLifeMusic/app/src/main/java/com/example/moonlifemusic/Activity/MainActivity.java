package com.example.moonlifemusic.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moonlifemusic.Adapter.MyViewPagerAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Baihat> arrayList;
    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager2 = findViewById(R.id.view_pager2);
        mBottomNavigationView = findViewById(R.id.bottom_nav);
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
                }else if(id == R.id.bottom_mypgage){
                    mViewPager2.setCurrentItem(3);
                }
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
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_mypgage).setChecked(true);
                        break;
                }
            }
        });
        mViewPager2.setUserInputEnabled(false);
    }


}