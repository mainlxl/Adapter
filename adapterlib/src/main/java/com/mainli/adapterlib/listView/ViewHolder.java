package com.mainli.adapterlib.listView;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

import com.mainli.adapterlib.ViewHolderHelper;

/**
 * ListView GridView 通用ViewHolder
 */
public class ViewHolder {
    public static final int viewSizeUndefined = -1;
    private View itemView;
    private SparseArray<View> viewList;

    /* package */ ViewHolder(View itemView, int viewSize) {
        this.itemView = itemView;
        this.viewList = new SparseArray<>(viewSize == viewSizeUndefined ? 10 : viewSize);
    }

    /* package */ int countView() {
        return viewList.size();
    }

    public <T extends View> T get(@IdRes int id) {
        return get(id, null);
    }

    public <T extends View> T get(@IdRes int id, Class<T> viewType) {
        return ViewHolderHelper.get(itemView, viewList, id, viewType);
    }
}