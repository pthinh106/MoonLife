package com.example.moonlifemusic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.moonlifemusic.Fragment.BangXepHangFragment;
import com.example.moonlifemusic.Fragment.CaNhanFragment;
import com.example.moonlifemusic.Fragment.TimKiemFragment;
import com.example.moonlifemusic.Fragment.TrangChuFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TrangChuFragment();
            case 1:
                return new BangXepHangFragment();
            case 2:
                return new TimKiemFragment();
//            case 3:
//                return new CaNhanFragment();
            default:
                return new TrangChuFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
