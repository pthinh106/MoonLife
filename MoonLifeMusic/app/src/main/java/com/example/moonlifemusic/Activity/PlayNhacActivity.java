package com.example.moonlifemusic.Activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moonlifemusic.Adapter.Viewpagerplaynhac;
import com.example.moonlifemusic.Fragment.DiaNhacFragment;
import com.example.moonlifemusic.Fragment.PlaydanhsachbaihatFragment;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    public static ArrayList<Baihat> arrayListbaihat = new ArrayList<>();
    private static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    private static Viewpagerplaynhac viewPagerplay;
    private PlaydanhsachbaihatFragment playdanhsachbaihatFragment;
    private static DiaNhacFragment diaNhacFragment;
    public static Toolbar toolbarplay;
    private static TextView textViewtimesong, textViewtotaltimesong, textViewtenbaihat, textViewtencasi;
    private static SeekBar seekBar;
    public static ImageButton imageViewrepeat, imageViewramdom, imageViewplay, imageViewnext, imageViewpre;
    private static ViewPager2 viewPager;
    public static MediaPlayer mediaPlayer;
    private static int position = 0;
    private static boolean repeat = false;
    private static boolean ramdom = false;
    private static boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        initer();
        if (mangbaihat.size() > 0) {
        }
        eventClick();
    }

    public static void mainplayclick() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            imageViewplay.setImageResource(R.drawable.nutpause);
        } else {
            mediaPlayer.start();
            imageViewplay.setImageResource(R.drawable.nutplay);
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerplay.createFragment(1) != null) {
                    if (mangbaihat.size() > 0) {
                        diaNhacFragment.playnhac(mangbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this::run);
                    } else {
                        handler.postDelayed(this::run, 300);
                    }
                }
            }
        }, 500);

        imageViewplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageViewplay.setImageResource(R.drawable.nutpause);
                } else {
                    mediaPlayer.start();
                    imageViewplay.setImageResource(R.drawable.nutplay);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageViewrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (ramdom == true) {
                        imageViewramdom.setImageResource(R.drawable.iconsuffle);
                        ramdom = false;
                    }
                    imageViewrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imageViewrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageViewramdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ramdom == false) {
                    if (repeat == true) {
                        imageViewrepeat.setImageResource(R.drawable.iconrepeat);
                        repeat = false;
                    }
                    imageViewramdom.setImageResource(R.drawable.img);
                    ramdom = true;
                } else {
                    imageViewramdom.setImageResource(R.drawable.iconsuffle);
                    ramdom = false;
                }
            }
        });
        imageViewnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnnextclick();
//                senNotificationMedia(position);
                getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
            }
        });
        imageViewpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnpreclick();
//                senNotificationMedia(position);
                getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
            }
        });

    }

    public static void btnnextclick() {
        if (mangbaihat.size() > 0) {
            if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if (position < mangbaihat.size()) {
                imageViewplay.setImageResource(R.drawable.nutpause);
                position++;
                if (repeat == true) {
                    if (position == (mangbaihat.size() - 1)) {
                        position = 0;
                    }
                    position--;
                }
                if (ramdom == true) {
                    Random random = new Random();
                    int index = random.nextInt(mangbaihat.size());
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index - 1;
                }
                if (position > (mangbaihat.size() - 1)) {
                    position = 0;
                }
                setup(position);
                updatetime();
            }
        }
        imageViewpre.setClickable(false);
        imageViewnext.setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewpre.setClickable(true);
                imageViewnext.setClickable(true);
            }
        }, 4000);
    }

    public static void btnpreclick() {
        if (mangbaihat.size() > 0) {
            if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if (position < mangbaihat.size()) {
                imageViewplay.setImageResource(R.drawable.nutpause);
                position--;
                if (repeat == true) {
                    if (position == 0) {
                        position = mangbaihat.size() - 1;
                    }
                    position++;
                }
                if (ramdom == true) {
                    Random random = new Random();
                    int index = random.nextInt(mangbaihat.size());
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index - 1;
                }
                if (position < 0) {
                    position = mangbaihat.size() - 1;
                }
                setup(position);
                updatetime();
            }
        }
        imageViewpre.setClickable(false);
        imageViewnext.setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewpre.setClickable(true);
                imageViewnext.setClickable(true);
            }
        }, 4000);
    }

    private void initer() {
        toolbarplay = findViewById(R.id.toolbarplaynhac);
        seekBar = findViewById(R.id.seekbarsong);
        textViewtimesong = findViewById(R.id.txtviewtimesong);
        textViewtotaltimesong = findViewById(R.id.txtviewttotaltimesong);
        imageViewnext = findViewById(R.id.btnnext);
        imageViewplay = findViewById(R.id.btnplaypause);
        imageViewpre = findViewById(R.id.btnprevious);
        imageViewramdom = findViewById(R.id.btnramdom);
        imageViewrepeat = findViewById(R.id.btnrepeat);
        viewPager = findViewById(R.id.viewpagerplaynhac);
        textViewtenbaihat = findViewById(R.id.txttenbaihatplay);
        textViewtencasi = findViewById(R.id.txttencasiplay);
        setSupportActionBar(toolbarplay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
        viewPagerplay = new Viewpagerplaynhac(this);
        playdanhsachbaihatFragment = new PlaydanhsachbaihatFragment();
        diaNhacFragment = new DiaNhacFragment();
        viewPagerplay.clear();
        viewPagerplay.addFragment(playdanhsachbaihatFragment);
        viewPagerplay.addFragment(diaNhacFragment);
        viewPager.setAdapter(viewPagerplay);
        viewPager.setCurrentItem(1);
        diaNhacFragment = (DiaNhacFragment) viewPagerplay.createFragment(1);
        if (mangbaihat.size() > 0) {
            setup(position);
//            senNotificationMedia(position);
        }
    }

    public static void setup(int position) {
        textViewtenbaihat.setText(mangbaihat.get(position).getTenBaiHat());
        textViewtencasi.setText(mangbaihat.get(position).getCaSi());
        new Playmusic().execute(mangbaihat.get(position).getLinkBaiHat());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerplay.createFragment(1) != null) {
                    if (mangbaihat.size() > 0) {
                        diaNhacFragment.playnhac(mangbaihat.get(position).getHinhBaiHat());
                        handler.removeCallbacks(this::run);
                    } else {
                        handler.postDelayed(this::run, 300);
                    }
                }
            }
        }, 500);
        imageViewplay.setImageResource(R.drawable.nutplay);
        toolbarplay.setTitle(mangbaihat.get(position).getTenBaiHat());
    }

    private void DataIntent() {
        Intent intent = getIntent();
        arrayListbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("baihat")) {
                Baihat baihat = (Baihat) intent.getSerializableExtra("baihat");
                arrayListbaihat.add(baihat);
                mangbaihat = arrayListbaihat;
            }
        }
        if (intent != null) {
            if (intent.hasExtra("mangbaihat")) {
                arrayListbaihat = (ArrayList<Baihat>) intent.getSerializableExtra("mangbaihat");
                mangbaihat = arrayListbaihat;

            }
        }
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    public static class Playmusic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timesong();
            updatetime();
        }
    }

    public static void timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    public static void updatetime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < mangbaihat.size()) {
                        imageViewplay.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            if (position == (mangbaihat.size() - 1)) {
                                position = 0;
                            }
                            position--;
                        }
                        if (ramdom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index - 1;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        setup(position);

                    }
                    imageViewpre.setClickable(false);
                    imageViewnext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageViewpre.setClickable(true);
                            imageViewnext.setClickable(true);
                        }
                    }, 4000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }


}