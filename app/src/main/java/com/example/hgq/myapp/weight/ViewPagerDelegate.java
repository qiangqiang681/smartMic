package com.example.hgq.myapp.weight;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;


import com.example.hgq.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ViewPagerDelegate implements View.OnClickListener {
    private ViewGroup mContext;
    private View rootView;
    private ViewPager mViewPager;
    private List<MenuEntity> mMenuEntities;
    private ArrayList<MenuRVHandlers> menuRVHandlerses;
    private int mNumColumns = 3;
    private ImageView mButton;


    public void setMenuList(List<MenuEntity> menuEntities) {

        mMenuEntities = menuEntities;
        menuRVHandlerses = new ArrayList<>();
        int fragmentCount = menuEntities.size() / (mNumColumns * 2);
        if (menuEntities.size() % (mNumColumns * 2) != 0) {
            fragmentCount += 1;
        }
        for (int i = 0; i < fragmentCount; i++) {

            int lastIndex = Math.min((i + 1) * (mNumColumns * 2), menuEntities.size());
            MenuRVHandlers menuRVHandlers = new MenuRVHandlers(mNumColumns, menuEntities.subList(i * (mNumColumns * 2), lastIndex));

            menuRVHandlerses.add(menuRVHandlers);

        }
        mViewPager.setAdapter(new ViewpagerAdapter(menuRVHandlerses));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setup(ViewGroup mContext) {
        this.mContext = mContext;
        rootView = creatView();
    }

    private View creatView() {

        View rootView = LayoutInflater.from(mContext.getContext()).inflate(R.layout.layout_viewpager, null, false);
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp);
        mButton = (ImageView) rootView.findViewById(R.id.btn);
        mButton.setOnClickListener(this);

        return rootView;
    }

    public void show() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mButton, "rotation", new float[]{0, 180});
        rotation.setDuration(500);
        rotation.start();
        mContext.addView(rootView, lp);
    }


    @Override
    public void onClick(View v) {


        ViewCompat.animate(rootView)
                .alpha(0)
                .translationY(rootView.getHeight())
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        mContext.removeView(rootView);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                }).setDuration(300).start();

    }
}
