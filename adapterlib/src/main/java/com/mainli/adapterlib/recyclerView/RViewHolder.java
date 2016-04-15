package com.mainli.adapterlib.recyclerView;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mainli on 2016/4/13.
 * RecyclerView - ViewHolder
 */
public class RViewHolder extends RecyclerView.ViewHolder {
    public static final int viewSizeUndefined = -1;
    private SparseArray<View> mViews;

    /* package */ RViewHolder(View itemView, int viewSize) {
        super(itemView);
        mViews = new SparseArray<>(viewSize);
    }

    public <T extends View> T getView(@IdRes int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        if (view == null) {
            return null;
        }
        try {
            //noinspection unchecked
            return (T) view;
        } catch (ClassCastException e) {
            return null;
        }
    }

    /* package */ int countView() {
        return mViews.size();
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
