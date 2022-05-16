package com.example.moonlifemusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.moonlifemusic.Model.Baihat;
import com.example.moonlifemusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImgViewTopXepHangAdaper extends PagerAdapter {
    Context context;
    ArrayList<Baihat> baihat;
    public ImgViewTopXepHangAdaper(Context context, ArrayList<Baihat> baihat) {
        this.context = context;
        this.baihat = baihat;
    }


    @Override
    public int getCount() {
        return baihat.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view =inflater.inflate(R.layout.fragment_imgtopxephang,null);
            TextView textViewtenbaihat = view.findViewById(R.id.txttenbaihattopxephang);
            TextView textViewtencasi = view.findViewById(R.id.txttencasitopxephang);
            TextView textViewindex = view.findViewById(R.id.txtindex);
            ImageView imageViewBaihat = view.findViewById(R.id.imgviewtopxephang);
            Picasso.get().load(baihat.get(position).getHinhBaiHat()).into(imageViewBaihat);
            textViewtenbaihat.setText(baihat.get(position).getTenBaiHat());
            textViewtencasi.setText(baihat.get(position).getCaSi());
            textViewindex.setText("Top " + (position + 1));
            container.addView(view);
            return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
