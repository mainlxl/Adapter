package com.test.mainli;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mainli on 2016/4/13.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected List<T> mData;

    public RecyclerAdapter(List<T> mList) {
        this.mData = mList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRessources(viewType), parent, false));
        viewHolder.setViewTypeflag(viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.onBindObject2View(holder, getItem(position), position);
    }

    public abstract int getLayoutRessources(int viewType);

    public T getItem(int position) {
        return mData.get(position);
    }

    public abstract void onBindObject2View(ViewHolder vh, T t, int position);

}
