package com.example.moonlifemusic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.DanhSachBaiHatActivity;
import com.example.moonlifemusic.Model.Listmucisc;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAllPlayListdapter extends  RecyclerView.Adapter<DanhSachAllPlayListdapter.ViewHolderr> {

    Context context;
    ArrayList<Listmucisc> mangAllPlayList;
    View view;
    public DanhSachAllPlayListdapter(Context context, ArrayList<Listmucisc> mangAllPlayList) {
        this.context = context;
        this.mangAllPlayList = mangAllPlayList;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_allplaylistt_mau,parent,false);
        return new ViewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, @SuppressLint("RecyclerView") int position) {
        Listmucisc listmucisc = mangAllPlayList.get(position);
        Picasso.get().load(listmucisc.getHinhNen()).into(holder.imageViewPlayList);
        holder.textViewPlaylist.setText(listmucisc.getTen());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("isplaylist", mangAllPlayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangAllPlayList != null ? mangAllPlayList.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {

        ImageView imageViewPlayList;
        TextView textViewPlaylist;
        public ViewHolderr(@NonNull View itemView) {
            super(itemView);
            imageViewPlayList = itemView.findViewById(R.id.imgviewplaylistall);
            textViewPlaylist = itemView.findViewById(R.id.txttenplaylistall);
        }
    }
}

