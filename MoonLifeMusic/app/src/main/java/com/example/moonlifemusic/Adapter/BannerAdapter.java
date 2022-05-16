package com.example.moonlifemusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.moonlifemusic.Model.QuangCao;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.fragment_banner_mau,null);
        ImageView imageViewBackGroundBanner = view.findViewById((R.id.imgviewbackgroundBanner));
        ImageView imageViewsongBanner = view.findViewById((R.id.imgviewbanner));
        TextView txttillesongbanner = view.findViewById((R.id.txtviewbannerbaihat));
        TextView txtnoidung = view.findViewById(R.id.txtviewnoidunbg);
        Picasso.get().load(arrayListBanner.get(position).getHinhAnh()).into(imageViewBackGroundBanner);
        Picasso.get().load(arrayListBanner.get(position).getHinhBaiHat()).into(imageViewsongBanner);
        txttillesongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListBanner.get(position).getNoiDung());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
