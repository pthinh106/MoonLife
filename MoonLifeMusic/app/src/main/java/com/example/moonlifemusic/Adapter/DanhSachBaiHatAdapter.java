package com.example.moonlifemusic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonlifemusic.Activity.MainActivity;
import com.example.moonlifemusic.Activity.PlayNhacProActivity;
import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.example.moonlifemusic.Service.APIService;
import com.example.moonlifemusic.Service.Dataservice;
import com.example.moonlifemusic.Service_Local.ForegroundServiceControl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends  RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolderr> {

    Context context;
    ArrayList<Baihat> mangBaiHat;
    View view;
    public DanhSachBaiHatAdapter(Context context, ArrayList<Baihat> mangBaiHat) {
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
                intent.putExtra("baihat",baihat);
                context.startActivity(intent);
            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(),v);
                popupMenu.inflate(R.menu.menu_addmusic);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.iteamadd:
                                Intent intent = new Intent(context, ForegroundServiceControl.class);
                                intent.putExtra("action_music_service", ForegroundServiceControl.ACTION_ADD);
                                intent.putExtra("addbaihat", baihat);
                                intent.putExtra("tontai", false);
                                context.startService(intent);
                                break;
                            case R.id.iteamaddcanhan:
                                if(MainActivity.getId() == null){
                                    Toast.makeText(v.getContext(), "Bạn Chưa Đăng Nhập",Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                Dataservice dataservice = APIService.getService();
                                Call<String> callback = dataservice.AddDanhSachBaiHatCanhan(MainActivity.getId(),baihat.getIdBaiHat());
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        String add = response.body();
                                        if(add.equals("1")){
                                            Toast.makeText(v.getContext(), "Thành Công",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(v.getContext(), "Thất Bại",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangBaiHat != null ? mangBaiHat.size(): 0;
    }

    public class ViewHolderr extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        ImageView imageViewBaiHat;
        TextView textViewtencasi, textViewtenBaiHat,textViewindex,textViewyeuthich;

        public ViewHolderr(@NonNull View itemView) {

            super(itemView);
//            textViewyeuthich= itemView.findViewById(R.id.yeuthich1);
            imageButton = view.findViewById(R.id.btnmoreplaylist);
            imageViewBaiHat = itemView.findViewById(R.id.imgbaihatdanhsach);
            textViewtenBaiHat = itemView.findViewById(R.id.txtbaihatdanhsach);
            textViewtencasi = itemView.findViewById(R.id.txttencasibaihat);
            textViewindex = itemView.findViewById(R.id.txtdanhsachphatindex);
        }
    }
}

