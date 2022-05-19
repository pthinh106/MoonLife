package com.example.moonlifemusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkyActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputLayout textInputLayoutname, textInputLayoutsdt, textInputLayoutemail,
                    textInputLayoutuser, textInputLayoutpass;
    Button buttondk;
    String user,pass,name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        anhxa();
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardangky);
        textInputLayoutname = findViewById(R.id.hovaten);
        textInputLayoutsdt = findViewById(R.id.sdt);
        textInputLayoutemail = findViewById(R.id.email);
        textInputLayoutuser = findViewById(R.id.user);
        textInputLayoutpass = findViewById(R.id.pass);
        buttondk = findViewById(R.id.btndangky);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng Ký");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttondk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = textInputLayoutuser.getEditText().getText().toString().trim();
                pass = textInputLayoutpass.getEditText().getText().toString().trim();
                name = textInputLayoutname.getEditText().getText().toString().trim();
                phone = textInputLayoutsdt.getEditText().getText().toString().trim();
                email = textInputLayoutemail.getEditText().getText().toString().trim();
                dangky(user,pass,name,phone,email);

            }
        });
    }

    private void dangky(String user, String pass, String name , String phone ,String email) {
        Dataservice dataservice = APIService.getService();
        Call<String> callback = dataservice.AddAcount(user,pass,name,phone,email);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String dangky = response.body();
                if(dangky.equals("0")){
                    Toast.makeText(DangkyActivity.this,"Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangkyActivity.this,DangNhapActivity.class);
                    intent.putExtra("username",user);
                    intent.putExtra("password",pass);
                    startActivity(intent);
                }else{
                    Toast.makeText(DangkyActivity.this,"Tồn Tại Tài Khoản",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}