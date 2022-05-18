package com.example.moonlifemusic.Activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moonlifemusic.Adapter.Viewpagerplaynhac;
import com.example.moonlifemusic.Fragment.DiaNhacFragment;
import com.example.moonlifemusic.Fragment.PlaydanhsachbaihatFragment;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service_Local.ForegroundServiceControl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class PlayNhacProActivity extends AppCompatActivity {
    public static ArrayList<Baihat> arrayListbaihat = new ArrayList<>();
    private Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    private boolean repeat = false, checkrandom = false, isplaying;
    private static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    private DiaNhacFragment fragment_dia_nhac;
    private PlaydanhsachbaihatFragment playdanhsachbaihatFragment;
    public static Viewpagerplaynhac adapternhac;
    private static ViewPager2 viewPager;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                isplaying = intent.getBooleanExtra("status_player", false);
                int action = intent.getIntExtra("action_music", 0);
                duration = intent.getIntExtra("duration_music", 0);
                timeValue = intent.getIntExtra("seektomusic", 0);
                position = intent.getIntExtra("position_music", 0);
                seekBarnhac.setProgress(timeValue);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                textViewrunrime.setText(simpleDateFormat.format(timeValue));
                handleMusic(action);
                TimeSong();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac_pro);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("send_data_to_activity"));
        GetDataFromIntent();
        AnhXa();
        enventClick();
        setViewStart();
        StartService();
        MainActivity.isplay(mangbaihat.get(position));
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
    }
    private void StartService() {
        Intent intent =  new Intent(this, ForegroundServiceControl.class);
        if (mangbaihat.size() > 0){
            intent.putExtra("obj_song_baihat", mangbaihat);
        }
        startService(intent);
    }
    private void enventClick() {
        imageButtonplaypausenhac.setOnClickListener(view -> {
            if (isplaying){
                sendActionToService(ForegroundServiceControl.ACTION_PAUSE);
                imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
            }else {
                sendActionToService(ForegroundServiceControl.ACTION_RESUME);
                imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
            }
        });
        imageButtonlapnhac.setOnClickListener(view -> {
            if (!repeat) {
                if (checkrandom) {
                    checkrandom = false;
                    imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                    imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                } else {
                    imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                }
                repeat = true;
            } else {
                imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                repeat = false;
            }
            sendActionToService(ForegroundServiceControl.ACTION_REPEAT);
        });
        imageButtontronnhac.setOnClickListener(view -> {
            if (!checkrandom){
                if (repeat){
                    repeat = false;
                    imageButtontronnhac.setImageResource(R.drawable.img);
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                }else {
                    imageButtontronnhac.setImageResource(R.drawable.img);
                }
                checkrandom = true;
            }else {
                imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                checkrandom = false;
            }
            sendActionToService(ForegroundServiceControl.ACTION_RANDOM);
        });
        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                durationToService = seekBar.getProgress();
                sendActionToService(ForegroundServiceControl.ACTION_DURATION);
            }
        });
        imageButtonnexnhac.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_NEXT));
        imageButtonpreviewnhac.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_PREVIOUS));
        toolbarplaynhac.setNavigationOnClickListener(view -> {
            finish();
        });
    }
    @SuppressWarnings("deprecation")
    private void setViewStart(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mangbaihat.size() > 0){
                    setView( mangbaihat.get(position).getHinhBaiHat(), mangbaihat.get(position).getTenBaiHat(), mangbaihat.get(position).getCaSi());
                }else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
    }
    private void NextMusic(){
        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        timeValue = 0;
    }
    private void completeNextMusic() {
        if (mangbaihat.size() > 0){
            NextMusic();
            setView(mangbaihat.get(position).getHinhBaiHat(), mangbaihat.get(position).getTenBaiHat(), mangbaihat.get(position).getCaSi());
        }
    }
    private void PreviousMusic(){
        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        timeValue = 0;
    }
    private void completePreviousMusic() {
        if (mangbaihat.size() > 0){
            PreviousMusic();
            setView( mangbaihat.get(position).getHinhBaiHat(), mangbaihat.get(position).getTenBaiHat(), mangbaihat.get(position).getCaSi());
        }
    }
    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if (intent.hasExtra("baihat")){
                Baihat baiHat = (Baihat) intent.getSerializableExtra("baihat");
                mangbaihat.add(baiHat);
                arrayListbaihat = mangbaihat;
            }else if (intent.hasExtra("mangbaihat")){
                mangbaihat = (ArrayList<Baihat>) intent.getSerializableExtra("mangbaihat");
                arrayListbaihat = mangbaihat;
            }
        }
    }

    private void AnhXa() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekbarsong);
        imageButtontronnhac = findViewById(R.id.btnramdom);
        imageButtonpreviewnhac = findViewById(R.id.btnprevious);
        imageButtonplaypausenhac = findViewById(R.id.btnplaypause);
        imageButtonnexnhac = findViewById(R.id.btnnext);
        imageButtonlapnhac = findViewById(R.id.btnrepeat);
        textViewtatoltime = findViewById(R.id.txtviewttotaltimesong);
        textViewcasi = findViewById(R.id.txttencasiplay);
        viewPager = findViewById(R.id.viewpagerplaynhac);
        textViewtennhac = findViewById(R.id.txttenbaihatplay);
        textViewrunrime = findViewById(R.id.txtviewtimesong);
        fragment_dia_nhac = new DiaNhacFragment();
        playdanhsachbaihatFragment = new PlaydanhsachbaihatFragment();
        adapternhac = new Viewpagerplaynhac(this);
        adapternhac.clear();
        adapternhac.addFragment(playdanhsachbaihatFragment);
        adapternhac.addFragment(fragment_dia_nhac);
        viewPager.setAdapter(adapternhac);
        viewPager.setCurrentItem(1);
        fragment_dia_nhac = (DiaNhacFragment) adapternhac.createFragment(1);
        setSupportActionBar(toolbarplaynhac);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);
    }
    private void TimeSong(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(duration));
        seekBarnhac.setMax(duration);
    }
    private void handleMusic(int action){
        switch (action){
            case ForegroundServiceControl.ACTION_PAUSE:
                imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                break;
            case ForegroundServiceControl.ACTION_RESUME:
                imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                break;
            case ForegroundServiceControl.ACTION_NEXT:
                completeNextMusic();
                break;
            case ForegroundServiceControl.ACTION_PREVIOUS:
                completePreviousMusic();
                break;
            case ForegroundServiceControl.ACTION_NEW:
                completePreviousMusic();
                break;
        }
    }
    private void sendActionToService(int action){
        Intent intent = new Intent(this, ForegroundServiceControl.class);
        intent.putExtra("action_music_service", action);
        intent.putExtra("duration", durationToService);
        intent.putExtra("repeat_music", repeat);
        intent.putExtra("random_music", checkrandom);
        startService(intent);
    }
    private void setView(String hinhBaiHat, String tenBaiHat, String tenCaSi){
        fragment_dia_nhac.playnhac(hinhBaiHat);
        Objects.requireNonNull(getSupportActionBar()).setTitle(tenBaiHat);
        textViewcasi.setText(tenCaSi);
        textViewtennhac.setText(tenBaiHat);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}