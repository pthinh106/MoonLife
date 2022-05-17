package com.example.moonlifemusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moonlifemusic.Activity.DangNhapActivity;
import com.example.moonlifemusic.Activity.MainActivity;
import com.example.moonlifemusic.R;
import com.google.android.material.tabs.TabLayout;

public class CaNhanFragment extends Fragment {

    private TabLayout tabLayout;
    private View mView;
    private TextView textViewuser, textViewlogin;
    private String user;
    boolean check = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ca_nhan,container,false);
        textViewuser = mView.findViewById(R.id.usernamelg);
        textViewlogin = mView.findViewById(R.id.txtlogin);
        user = MainActivity.getuser();
        if(user == null){
            textViewlogin.setText("Login");
        }else{
            textViewlogin.setText("Logout");
            textViewuser.setText(MainActivity.getuser());
        }
        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = MainActivity.getuser();
                Toast.makeText(getActivity(),MainActivity.getuser(),Toast.LENGTH_SHORT).show();
                if(user == null){
                    Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                    startActivity(intent);
                }else{
                    textViewuser.setText("Khach");
                    textViewlogin.setText("Login");
                    MainActivity.setuser();
                }
            }
        });
        return mView;
    }
}
