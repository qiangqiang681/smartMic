package com.example.hgq.myapp.weight;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ViewpagerAdapter extends PagerAdapter {

    private ArrayList<MenuRVHandlers> menuRVHandlerses;

    public ViewpagerAdapter(ArrayList<MenuRVHandlers> menuRVHandlerses) {

        this.menuRVHandlerses = menuRVHandlerses;
    }

    @Override
    public int getCount() {
        return menuRVHandlerses.size();
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(menuRVHandlerses.get(position).onLoadView(container));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = menuRVHandlerses.get(position).onLoadView(container);
        container.addView(view);
        return view;
    }
}
