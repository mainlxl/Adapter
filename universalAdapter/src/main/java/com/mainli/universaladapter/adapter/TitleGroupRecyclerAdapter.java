package com.mainli.universaladapter.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mainli.universaladapter.viewholder.RViewHolder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Mainli on 2016/4/13.
 */
public abstract class TitleGroupRecyclerAdapter<G, T> extends RecyclerView.Adapter<RViewHolder> {
    protected Map<G, List<T>> mData;
    private SparseArray<G> mGroupIndexs;
    private SparseArray<T> mChildIndexs;
    protected final int TITLE_LAYOUT_TYOE = 0;
    protected final int ITEM_LAYOUT_TYOE = 1;

    public TitleGroupRecyclerAdapter(Map<G, List<T>> mData) {
        this.mData = mData;
        mGroupIndexs = new SparseArray<G>(mData.size());
        mChildIndexs = new SparseArray<T>();
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRessources(viewType), parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        onBindObject2View(holder, getGroupItem(position), getChildItem(position), position);
    }

    @Override
    public int getItemCount() {
        Collection<List<T>> values = mData.values();
        int childCount = 0;
        for (List<T> list : values) {
            if (list != null)
                childCount += list.size();
        }
        return childCount + mData.size();
    }

    public G getGroupItem(int position) {
        int position1 = position;
        G g = mGroupIndexs.get(position);
        if (g == null) {
            for (G index : mData.keySet()) {
                int size = mData.get(index).size();
                position1--;
                if (position1 < size) {
                    mGroupIndexs.put(position, index);
                    return index;
                }
                position1 -= size;
            }
        }
        return g;
    }

    public T getChildItem(int position) {
        T t = mChildIndexs.get(position);
        if (t != null)
            return t;
        int position1 = position;
        for (List<T> ts : mData.values()) {
            position1--;
            if (position1 < 0) return null;
            if (ts != null) {
                int size = ts.size();
                if (position1 < size) {
                    t = ts.get(position1);
                    mChildIndexs.put(position, t);
                    return t;
                }
                position1 -= (size);
            }
        }
        return t;
    }


    @Override
    public int getItemViewType(int position) {
        return getChildItem(position) == null ? TITLE_LAYOUT_TYOE : ITEM_LAYOUT_TYOE;
    }

    public abstract void onBindObject2View(RViewHolder vh, G g, T t, int position);

    public abstract int getLayoutRessources(int viewType);
}
