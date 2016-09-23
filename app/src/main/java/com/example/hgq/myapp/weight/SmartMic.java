package com.example.hgq.myapp.weight;

import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class SmartMic {


    private final ViewGroup mGroup;
    private ViewPagerDelegate mDelegate;
    private List<MenuEntity> mMenuEntities;

    public SmartMic(ViewGroup mGroup) {
        this.mGroup =mGroup;
    }

    public SmartMic setMenuList(@MenuRes int menuRes) {
        Menu menu = new PopupMenu(mGroup.getContext(), null).getMenu();
        new MenuInflater(mGroup.getContext()).inflate(menuRes, menu);
        List<MenuEntity> menuEntities = getMenuEntityFormMenuRes(menu);

        if(mDelegate != null) {
            mDelegate.setMenuList(menuEntities);
        }else
            this.mMenuEntities=menuEntities;

        return this;
    }

    private List<MenuEntity> getMenuEntityFormMenuRes(Menu menu) {
        List<MenuEntity> list = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            if (item.isVisible()) {
                MenuEntity itemEntity = new MenuEntity();
                itemEntity.title = item.getTitle().toString();
                itemEntity.icon = item.getIcon();
                list.add(itemEntity);
            }
        }
        return list;
    }

    public SmartMic setDelegate(ViewPagerDelegate viewPagerDelegate) {
        this.mDelegate=viewPagerDelegate;

        mDelegate.setup(mGroup);

        if(mMenuEntities != null){
            mDelegate.setMenuList(mMenuEntities);
        }
        return this;
    }

    public void show() {
        mDelegate.show();
    }
}
