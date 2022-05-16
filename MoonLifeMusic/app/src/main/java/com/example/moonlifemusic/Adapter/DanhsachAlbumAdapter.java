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
import com.example.moonlifemusic.Model.Album;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachAlbumAdapter extends  RecyclerView.Adapter<DanhsachAlbumAdapter.ViewHolderr> {

    Context context;
    ArrayList<Album> mangAlbum;
    View view;
    public DanhsachAlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_album_mau,parent,false);
        return new ViewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, @SuppressLint("RecyclerView") int position) {
        Album album = mangAlbum.get(position);
        holder.textViewtencasi.setText(album.getTenCaSi());
        holder.textViewtenalbum.setText(album.getTenAlBum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imageViewalbum);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("idalbum", mangAlbum.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangAlbum != null ? mangAlbum.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {
        ImageView imageViewalbum;
        TextView textViewtencasi, textViewtenalbum;

        public ViewHolderr(@NonNull View itemView) {

            super(itemView);
            imageViewalbum = itemView.findViewById(R.id.imgviewabum);
            textViewtenalbum = itemView.findViewById(R.id.txtviewtenalbum);
            textViewtencasi = itemView.findViewById(R.id.txtviewtencasialbum);
        }
    }
}

