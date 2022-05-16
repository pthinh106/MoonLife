package com.example.moonlifemusic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;


public class Viewpagerplaynhac extends FragmentStateAdapter {

    public static ArrayList<Fragment> arrayListFragment = new ArrayList<>();

    public Viewpagerplaynhac(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayListFragment.size();
    }
    public void addFragment(Fragment fragment){
        arrayListFragment.add(fragment);
    }
    public void clear(){
        arrayListFragment.clear();;
    }
}
