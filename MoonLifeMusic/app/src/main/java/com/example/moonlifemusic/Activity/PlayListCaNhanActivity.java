package com.example.moonlifemusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moonlifemusic.R;

public class PlayListCaNhanActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list_ca_nhan);
        toolbar = findViewById(R.id.toolbarresetpass);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        if(intent.hasExtra("user")){
            id = intent.getStringExtra("user");
        }
    }
}