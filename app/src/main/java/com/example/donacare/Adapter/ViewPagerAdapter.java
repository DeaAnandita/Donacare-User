package com.example.donacare.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.donacare.Fragment.HomeFragment;

public class ViewPagerAdapter extends PagerAdapter {
    int[] img;
    LayoutInflater inflater;
    HomeFragment context;
    int position = 3;

    public ViewPagerAdapter(HomeFragment homeFragment, int[] img) {
        this.context = homeFragment;
        this.img = img;
    }


    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        ImageView img;
//
////        inflater = (LayoutInflater)context.getSystem
////        View itemview = inflater.inflate(R.layout.item, container, false);
////        img = (ImageView) itemview.findViewById(R.id.ima1);
////        img.setImageResource(flag[position]);
//
////        //add item.xml to viewpager
////        ((ViewPager) container).addView(itemview);
////        return itemview;
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
