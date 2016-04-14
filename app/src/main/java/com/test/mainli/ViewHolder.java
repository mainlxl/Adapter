package com.test.mainli;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mainli on 2016/4/13.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private int viewTypeflag;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
    }
    public ViewHolder(View itemView,int viewTypeflag) {
        super(itemView);
        mViews = new SparseArray<View>();
        this.viewTypeflag = viewTypeflag;
    }

    public <T extends View> T getView(@IdRes int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    public int getViewTypeflag() {
        return viewTypeflag;
    }

    public void setViewTypeflag(int viewTypeflag) {
        this.viewTypeflag = viewTypeflag;
    }

    //------------------------------辅助方法---------------------------------------------------------------------
    public void setText(@IdRes int id, CharSequence text) {
        TextView view = getView(id);
        view.setText(text);
    }

    public void setOnClickListenr(@IdRes int id, View.OnClickListener linstener) {
        getView(id).setOnClickListener(linstener);
    }

    public void setOnLongClickListener(@IdRes int id, View.OnLongClickListener linstener) {
        getView(id).setOnLongClickListener(linstener);
    }

}
