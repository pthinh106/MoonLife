package com.example.moonlifemusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moonlifemusic.R;
import com.google.android.material.textfield.TextInputLayout;

public class DangNhapActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout inputLayoutuser, inputLayoutpass;
    private Button buttondangnhap, buttondangki;
    private TextView textViewqmk;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhxa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        buttondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = inputLayoutuser.getEditText().getText().toString().trim();
                Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
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
    }
}