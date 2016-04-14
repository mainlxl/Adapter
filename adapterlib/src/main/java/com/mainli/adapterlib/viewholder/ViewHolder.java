package com.mainli.adapterlib.viewholder;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * ListView GridView 通用ViewHolder
 */
public class ViewHolder {
    public static final int viewSizeUndefined = -1;
    private View itemView;
    private SparseArray<View> viewList;

    public ViewHolder(View itemView, int viewSize) {
        this.itemView = itemView;
        this.viewList = new SparseArray<View>(viewSize == viewSizeUndefined ? 10 : viewSize);
    }

    public int countView() {
        return viewList.size();
    }

    public <T extends View> T get(@IdRes int id) {
        View childView = viewList.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            viewList.put(id, childView);
        }
        return (T) childView;

    }

    public <T extends View> T get(@IdRes int id, Class<T> viewType) {
        View childView = viewList.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            viewList.put(id, childView);
        }
        if (childView == null) {
            return null;
        }
        try {
            //noinspection unchecked
            return (T) childView;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
