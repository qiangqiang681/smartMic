package com.example.hgq.myapp.weight;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.hgq.myapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class MenuRVHandlers {
    private View mView;
    private RecyclerView mRecyclerView;
    private int mNumColumns;
    private List<MenuEntity> mMenuEntities;
    private RvAdapter rvAdapter;

    public MenuRVHandlers(int mNumColumns, List<MenuEntity> menuEntities) {
        this.mNumColumns = mNumColumns;
        this.mMenuEntities = menuEntities;
    }

    public View onLoadView(ViewGroup viewGroup) {
        if (mView == null) {
            mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_rv_grid, viewGroup, false);
            createview(viewGroup);
        }
        return mView;
    }

    private void createview(ViewGroup viewGroup) {

        if (mMenuEntities == null || mMenuEntities.size() == 0) {
            return;
        }
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mView.getContext(), mNumColumns));
        mRecyclerView.setHasFixedSize(true);
        rvAdapter = new RvAdapter(mMenuEntities, viewGroup);
        mRecyclerView.setAdapter(rvAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private class RvAdapter extends RecyclerView.Adapter<RvAdapter.MenuVH> {

        List<MenuEntity> mDataList;
        private ViewGroup viewGroup;

        public RvAdapter(List<MenuEntity> mMenuEntities, ViewGroup viewGroup) {
            mDataList = mMenuEntities;
            this.viewGroup = viewGroup;
        }

        @Override
        public MenuVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_grid, null, false);
            return new MenuVH(view);
        }

        @Override
        public void onBindViewHolder(MenuVH holder, int position) {

            MenuEntity menuEntity = mDataList.get(position);
            if (menuEntity.iconId != 0) {

                holder.iv.setVisibility(View.VISIBLE);
                holder.iv.setImageResource(menuEntity.iconId);
            } else if (menuEntity.icon != null) {

                holder.iv.setVisibility(View.VISIBLE);
                holder.iv.setImageDrawable(menuEntity.icon);

            } else {
                holder.iv.setVisibility(View.GONE);
            }
            holder.name.setText(menuEntity.title);

            animation(holder);

        }

        private void animation(MenuVH menuVH) {
            ViewCompat.setAlpha(menuVH.itemView, 0);

            ObjectAnimator translationY = ObjectAnimator.ofFloat(menuVH.itemView, "translationY", 500, 0);
            translationY.setDuration(500);
            translationY.setInterpolator(new OvershootInterpolator(1.6f));
            ObjectAnimator alphaIn = ObjectAnimator.ofFloat(menuVH.itemView, "alpha", 0, 1);
            alphaIn.setDuration(100);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translationY, alphaIn);
            animatorSet.setStartDelay(30 * menuVH.getAdapterPosition());
            animatorSet.start();
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }


        class MenuVH extends RecyclerView.ViewHolder {

            public ImageView iv;
            public TextView name;
//            public LinearLayout itemRl;

            public MenuVH(View itemView) {
                super(itemView);

                iv = (ImageView) itemView.findViewById(R.id.iv);
                name = (TextView) itemView.findViewById(R.id.name);
//                itemRl = (LinearLayout) itemView.findViewById(R.id.itemRl);

            }
        }
    }
}
