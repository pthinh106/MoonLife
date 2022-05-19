package com.example.moonlifemusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moonlifemusic.Adapter.DanhSachBaiHatAdapter;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout inputLayoutuser, inputLayoutpass;
    private Button buttondangnhap, buttondangki;
    private TextView textViewqmk;
    private String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Intent intent = getIntent();
        if(intent.hasExtra("username") && intent.hasExtra("password")){
            user = intent.getStringExtra("username");
            pass = intent.getStringExtra("password");
            inputLayoutuser.getEditText().setText(user);
            inputLayoutpass.getEditText().setText(pass);
        }
        anhxa();
        eventclick();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);

    }

    private void eventclick() {
        buttondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = inputLayoutuser.getEditText().getText().toString().trim();
                pass = inputLayoutpass.getEditText().getText().toString().trim();
                login(user,pass);

            }
        });
        buttondangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangkyActivity.class);
                startActivity(intent);
            }
        });
        textViewqmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,ResetpasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(String user ,String pass) {
        Dataservice dataservice = APIService.getService();
        Call<String> callback = dataservice.getLogin(user,pass);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String login = response.body();
                if(!login.equals("0")){
                    Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("id",login);
                    startActivity(intent);
                }else{
                    Toast.makeText(DangNhapActivity.this,"Sai Tài Khoản Hoặc PassWord",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardangnhap);
        inputLayoutuser = findViewById(R.id.edttkdn);
        inputLayoutpass = findViewById(R.id.edtmkdn);
        buttondangnhap = findViewById(R.id.btndn);
        buttondangki = findViewById(R.id.btndk);
        textViewqmk = findViewById(R.id.txtqmk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Đăng Nhập");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}