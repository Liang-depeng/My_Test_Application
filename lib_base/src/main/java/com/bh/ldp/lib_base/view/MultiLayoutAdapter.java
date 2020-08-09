package com.bh.ldp.lib_base.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import androidx.annotation.NonNull;

public abstract class MultiLayoutAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<T> datas;
    private Context mContext;
    private int layoutResId;
    private MultiLayoutType<T> mMultiLayoutType;

    public MultiLayoutAdapter(Context context, List<T> datas, int layoutResId, MultiLayoutType<T> multiLayoutType) {
        this.datas = datas;
        this.mContext = context;
        this.layoutResId = layoutResId;
        this.mMultiLayoutType = multiLayoutType;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mMultiLayoutType != null) {
            layoutResId = viewType;
        }

        if (layoutResId == 0) {
            View view = new View(mContext);
            view.setVisibility(View.GONE);
            view.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
            return new RecyclerViewHolder(view);
        }

        View itemView = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        onBindMyViewHolder(holder, datas.get(position), position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongItemClick(v, position);
                return true;
            }
        });
    }

    protected abstract void onLongItemClick(View view, int position);

    protected abstract void onItemClick(View view, int position);

    protected abstract void onBindMyViewHolder(RecyclerViewHolder holder, T item, int position);

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiLayoutType != null) {
            return mMultiLayoutType.getLayoutResType(datas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

}
