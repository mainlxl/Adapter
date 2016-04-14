package com.mainli.adapterlib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 基类adapter
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter {

    protected List<T> mListData;

    public AbstractBaseAdapter(List<T> mListData) {
        this.mListData = mListData;
    }

    public void remove(int position) {
        mListData.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    public List<T> getList() {
        return mListData;
    }

    public void remove(T t) {
        if (mListData.contains(t)) {
            mListData.remove(t);
            notifyDataSetChanged();
        }
    }

    public void removeAll() {
        mListData.clear();
        notifyDataSetChanged();
    }

    public void resetData(List<T> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        mListData.addAll(data);
        notifyDataSetChanged();
    }

    public void add(T t) {
        mListData.add(t);
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        if (position > mListData.size()) {
            return null;
        }
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getItemResource(), null);
        }
        return getItemView(position, convertView,getItem(position));
    }

    public abstract int getItemResource();

    public abstract View getItemView(int position, View convertView,T t);
}
