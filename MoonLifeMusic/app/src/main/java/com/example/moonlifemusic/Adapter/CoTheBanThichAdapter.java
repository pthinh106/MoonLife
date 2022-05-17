package com.example.moonlifemusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.PlayNhacActivity;
import com.example.moonlifemusic.Activity.PlayNhacProActivity;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Model.Baihat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoTheBanThichAdapter extends  RecyclerView.Adapter<CoTheBanThichAdapter.ViewHolderr> {

    Context context;
    ArrayList<Baihat> mangBaiHat;
    View view;

    public CoTheBanThichAdapter(Context context, ArrayList<Baihat> mangChuDe3) {
        this.context = context;
        this.mangBaiHat = mangChuDe3;
    }

    @NonNull
    @Override
    public ViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_cothebanthich_mau,parent,false);
        return new ViewHolderr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderr holder, int position) {
        Baihat baihat = mangBaiHat.get(position);
        holder.textViewtenbaihat.setText(baihat.getTenBaiHat());
        holder.textViewtencasi.setText(baihat.getCaSi());
        Picasso.get().load(baihat.getHinhBaiHat()).into(holder.imageViewhinhbaihat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PlayNhacActivity.mediaPlayer != null){
                    PlayNhacActivity.mediaPlayer.stop();
                }
                Intent intent = new Intent(context, PlayNhacProActivity.class);
                intent.putExtra("baihat",baihat);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangBaiHat != null ? mangBaiHat.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {
        TextView textViewtencasi,textViewtenbaihat,textViewyeuthich;
        ImageView imageViewhinhbaihat;
        public ViewHolderr(@NonNull View itemView) {
            super(itemView);
//            textViewyeuthich= itemView.findViewById(R.id.yeuthich);
            textViewtenbaihat = itemView.findViewById(R.id.txttenbaohatcothethich);
            textViewtencasi = itemView.findViewById(R.id.txttencasicothethich);
            imageViewhinhbaihat = itemView.findViewById(R.id.imgbaihatcothethich);
        }
    }
}

