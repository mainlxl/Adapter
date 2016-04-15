package com.mainli.adapterlib.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mainli.adapterlib.viewholder.RViewHolder;

import java.util.List;

/**
 * 用于多种布局的RecyclerView 适配器
 * Created by Mainli on 2016/4/13.
 */
public abstract class MultiRecyclerAdapter<T> extends RecyclerView.Adapter<RViewHolder> {
    @LayoutRes
    private final int[] mLayoutIds;
    private final SparseIntArray mViewSizes;
    protected List<T> mData;

    public MultiRecyclerAdapter(List<T> mList, @LayoutRes int[] layoutIds) {
        this.mData = mList;
        this.mLayoutIds = layoutIds;
        this.mViewSizes = new SparseIntArray(mLayoutIds.length);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * viewType 在父类中会自动处理
         * {@link android.support.v7.widget.RecyclerView.Adapter#createViewHolder(ViewGroup, int)}
         * {@code
         *      final VH holder = onCreateViewHolder(parent, viewType);
         *      holder.mItemViewType = viewType;
         * }
         */
        return new RViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(mLayoutIds[viewType], parent, false),
                mViewSizes.get(viewType, RViewHolder.viewSizeUndefined));
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        this.onBindObject2View(holder, getItem(position), position);
        if (mViewSizes.get(holder.getItemViewType(), RViewHolder.viewSizeUndefined)
                == RViewHolder.viewSizeUndefined) {
            mViewSizes.put(holder.getItemViewType(), holder.countView());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mLayoutIds.length == 1)
            return 0;
        return getItemViewType(getItem(position), position);
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 如果{@link #mLayoutIds}长度只有1，此方法不会被调用
     * 否则必须重写，判断并返回LayoutId在数组中的下标
     */
    public int getItemViewType(T t, int position) {
        throw new UnsupportedOperationException("If {mLayoutIds.length > 1}" +
                " you must override getItemViewType(t, position) method" +
                " to return view type, in the layout ids array position.");
    }

    public abstract void onBindObject2View(RViewHolder vh, T t, int position);
}
