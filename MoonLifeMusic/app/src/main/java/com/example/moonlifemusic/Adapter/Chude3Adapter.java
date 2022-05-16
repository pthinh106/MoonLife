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

public class Chude3Adapter extends  RecyclerView.Adapter<Chude3Adapter.ViewHolderr> {

    Context context;
    ArrayList<Listmucisc> mangChuDe3;
    View view;
    public Chude3Adapter(Context context, ArrayList<Listmucisc> mangChuDe3) {
        this.context = context;
        this.mangChuDe3 = mangChuDe3;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_chude3_mau,parent,false);
        return new ViewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, @SuppressLint("RecyclerView") int position) {
        Listmucisc listmucisc = mangChuDe3.get(position);
        holder.textViewtenplaylist.setText(listmucisc.getTen());
        Picasso.get().load(listmucisc.getHinhNen()).into(holder.imageViewPlayList);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("isplaylist", mangChuDe3.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangChuDe3 != null ? mangChuDe3.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {

        ImageView imageViewPlayList;
        TextView textViewtenplaylist;
        public ViewHolderr(@NonNull View itemView) {
            super(itemView);
            textViewtenplaylist = itemView.findViewById(R.id.txttenplaylist3);
            imageViewPlayList = itemView.findViewById(R.id.imgviewplaylist3);
        }
    }
}

