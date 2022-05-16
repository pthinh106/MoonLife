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
import com.example.moonlifemusic.Activity.PlayNhacProActivity;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BangXepHangAdapter extends  RecyclerView.Adapter<BangXepHangAdapter.ViewHolderr> {

    Context context;
    ArrayList<Baihat> mangBaiHat;
    View view;
    public BangXepHangAdapter(Context context, ArrayList<Baihat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_baihat_mau,parent,false);
        return new ViewHolderr(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, @SuppressLint("RecyclerView") int position) {
        Baihat baihat = mangBaiHat.get(position);
        holder.textViewtencasi.setText(baihat.getCaSi());
        holder.textViewtenBaiHat.setText(baihat.getTenBaiHat());
        holder.textViewindex.setText(position + 1 + "");
        Picasso.get().load(baihat.getHinhBaiHat()).into(holder.imageViewBaiHat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayNhacProActivity.class);
                intent.putExtra("baihat", baihat);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mangBaiHat != null ? mangBaiHat.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {
        ImageView imageViewBaiHat;
        TextView textViewtencasi, textViewtenBaiHat,textViewindex;

        public ViewHolderr(@NonNull View itemView) {

            super(itemView);
            imageViewBaiHat = itemView.findViewById(R.id.imgbaihatdanhsach);
            textViewtenBaiHat = itemView.findViewById(R.id.txtbaihatdanhsach);
            textViewtencasi = itemView.findViewById(R.id.txttencasibaihat);
            textViewindex = itemView.findViewById(R.id.txtdanhsachphatindex);
        }
    }
}
