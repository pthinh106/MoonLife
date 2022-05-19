package com.example.moonlifemusic.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
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
import com.example.moonlifemusic.Fragment.mybottomsheet;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service_Local.ForegroundServiceControl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Baihat> arrayList;
    private static LinearLayout linearLayout;
    private static CircleImageView circleImageView;
    private static TextView textViewtenbaihat, textViewtencasi;
    private ImageButton btnpre, btnplay, btnnext;
    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;
    private  static String user ,id;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    public static int action;
    private boolean isplaying = true;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                isplaying = intent.getBooleanExtra("status_player", false);
                action = intent.getIntExtra("action_music", 0);
                position = intent.getIntExtra("position_music", 0);
                arrayList= (ArrayList<Baihat>) intent.getSerializableExtra("mangbaihat");
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openbottomsheet(arrayList);
                    }
                });
                handleMusic(action);
            }
        }
    };

    private void handleMusic(int action){
        switch (action){
            case ForegroundServiceControl.ACTION_PAUSE:
                btnplay.setImageResource(R.drawable.nutpause);
                break;
            case ForegroundServiceControl.ACTION_RESUME:
                btnplay.setImageResource(R.drawable.nutplay);
                break;
            case ForegroundServiceControl.ACTION_NEXT:
                completeNextMusic();
                break;
            case ForegroundServiceControl.ACTION_PREVIOUS:
                completePreviousMusic();
                break;
            case ForegroundServiceControl.ACTION_NEW:
                newmusic();
                break;
            case ForegroundServiceControl.ACTION_ADD:
                break;
            case ForegroundServiceControl.ACTION_STOP:
                arrayList = new ArrayList<>();
                linearLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void newmusic() {
        Picasso.get().load(arrayList.get(position).getHinhBaiHat()).into(circleImageView);
        textViewtencasi.setText(arrayList.get(position).getCaSi());
        textViewtenbaihat.setText(arrayList.get(position).getTenBaiHat());
    }

    private void completePreviousMusic() {
        Picasso.get().load(arrayList.get(position).getHinhBaiHat()).into(circleImageView);
        textViewtencasi.setText(arrayList.get(position).getCaSi());
        textViewtenbaihat.setText(arrayList.get(position).getTenBaiHat());
    }

    private void completeNextMusic() {
        Picasso.get().load(arrayList.get(position).getHinhBaiHat()).into(circleImageView);
        textViewtencasi.setText(arrayList.get(position).getCaSi());
        textViewtenbaihat.setText(arrayList.get(position).getTenBaiHat());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("send_data_to_activity"));
        anhxa();
        eventclick();
        Intent intent = getIntent();
        if(intent.hasExtra("user")){
            user = intent.getStringExtra("user");

        }
        if(intent.hasExtra("id")){
            id = intent.getStringExtra("id");
        }
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
                else if(id == R.id.bottom_mypgage){
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

    private void eventclick() {
        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendActionToService(ForegroundServiceControl.ACTION_PREVIOUS);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendActionToService(ForegroundServiceControl.ACTION_NEXT);
            }
        });
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isplaying){
                    sendActionToService(ForegroundServiceControl.ACTION_PAUSE);
                    btnplay.setImageResource(R.drawable.nutpause);
                }else {
                    sendActionToService(ForegroundServiceControl.ACTION_RESUME);
                    btnplay.setImageResource(R.drawable.nutplay);
                }
            }
        });

    }

    private void sendActionToService(int action){
        Intent intent = new Intent(this, ForegroundServiceControl.class);
        intent.putExtra("action_music_service", action);
        startService(intent);
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
    }

    private void openbottomsheet(ArrayList<Baihat> arrayList) {
        mybottomsheet mybottomsheet = new mybottomsheet(arrayList);
        mybottomsheet.show(getSupportFragmentManager(),mybottomsheet.getTag());
    }

    public static void isplay(Baihat baihat){
        linearLayout.setVisibility(View.VISIBLE);
        Picasso.get().load(baihat.getHinhBaiHat()).into(circleImageView);
        textViewtencasi.setText(baihat.getCaSi());
        textViewtenbaihat.setText(baihat.getTenBaiHat());
    }
    public static void setoff(){
        linearLayout.setVisibility(View.GONE);
    }
    public static String getuser(){
        if(user != null){
            return  user;
        }
        return null;
    }
    public static String getId(){
        if(id != null){
            return  id;
        }
        return null;
    }
    public static void setuser(){
        user = null;
    }
    public static void setid(){
        id = null;
    }



}