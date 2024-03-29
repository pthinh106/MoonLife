package com.example.moonlifemusic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service_Local.ForegroundServiceControl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayDanhSachBaiHatAdapter extends  RecyclerView.Adapter<PlayDanhSachBaiHatAdapter.Viewholder> {

    Context context;
    ArrayList<Baihat> arrayListbaihat;
    View view;

    public PlayDanhSachBaiHatAdapter(Context context, ArrayList<Baihat> arrayListbaihat) {
        this.context = context;
        this.arrayListbaihat = arrayListbaihat;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.fragment_playdanhsachbaihat_mau,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Baihat baihat = arrayListbaihat.get(position);
        holder.textViewindex.setText(position +1+ " ");
        holder.textViewtenbaihat.setText(baihat.getTenBaiHat());
        holder.textViewcasi.setText(baihat.getCaSi());
        Picasso.get().load(baihat.getHinhBaiHat()).into(holder.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ForegroundServiceControl.class);
                intent.putExtra("action_music_service", ForegroundServiceControl.ACTION_NEW);
                intent.putExtra("baihat", baihat);
                context.startService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListbaihat.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView textViewindex,textViewtenbaihat,textViewcasi;
        CircleImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textViewindex = itemView.findViewById(R.id.txtplaydanhsachphatindex);
            textViewtenbaihat = itemView.findViewById(R.id.txtplaybaihatdanhsach);
            textViewcasi = itemView.findViewById(R.id.txtplaytencasibaihat);
            imageView = itemView.findViewById(R.id.imgplaybaihatdanhsach);

        }
    }
}
