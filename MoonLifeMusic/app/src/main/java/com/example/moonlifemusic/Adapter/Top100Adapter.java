package com.example.moonlifemusic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.DanhSachBaiHatActivity;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Top100Adapter extends  RecyclerView.Adapter<Top100Adapter.ViewHolderr> {

    Context context;
    ArrayList<Listmucisc> mangTop100;
    View view;
    public Top100Adapter(Context context, ArrayList<Listmucisc> mangTop100) {
        this.context = context;
        this.mangTop100 = mangTop100;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_top100_mau,parent,false);
        return new ViewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, @SuppressLint("RecyclerView") int position) {
        Listmucisc listmucisc = mangTop100.get(position);
        Picasso.get().load(listmucisc.getHinhNen()).into(holder.imageViewPlayList);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("isplaylist", mangTop100.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangTop100 != null ? mangTop100.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {

        ImageView imageViewPlayList;
        public ViewHolderr(@NonNull View itemView) {
            super(itemView);
            imageViewPlayList = itemView.findViewById(R.id.imgviewtop100);
        }
    }
}

