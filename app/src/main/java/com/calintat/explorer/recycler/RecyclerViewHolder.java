package com.calintat.explorer.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder
{
    final Context context;

    ImageView image;

    View.OnClickListener onActionClickListener;

    private View.OnClickListener onClickListener;

    View.OnLongClickListener onActionLongClickListener;

    private View.OnLongClickListener onLongClickListener;

    //----------------------------------------------------------------------------------------------

    RecyclerViewHolder(Context context, RecyclerOnItemClickListener listener, View view)
    {
        super(view);

        this.context = context;

        setClickListener(listener);

        loadIcon();

        loadName();

        loadInfo();
    }

    //----------------------------------------------------------------------------------------------

    protected abstract void loadIcon();

    protected abstract void loadName();

    protected abstract void loadInfo();

    protected abstract void bindIcon(File file, Boolean selected);

    protected abstract void bindName(File file);

    protected abstract void bindInfo(File file);

    //----------------------------------------------------------------------------------------------

    private void setClickListener(final RecyclerOnItemClickListener listener)
    {
        this.onActionClickListener = v -> listener.onItemLongClick(getAdapterPosition());

        this.onActionLongClickListener = v -> listener.onItemLongClick(getAdapterPosition());

        this.onClickListener = v -> listener.onItemClick(getAdapterPosition());

        this.onLongClickListener = v -> listener.onItemLongClick(getAdapterPosition());
    }

    void setData(final File file, Boolean selected)
    {
        itemView.setOnClickListener(onClickListener);

        itemView.setOnLongClickListener(onLongClickListener);

        itemView.setSelected(selected);

        bindIcon(file, selected);

        bindName(file);

        bindInfo(file);
    }

    void setVisibility(View view, Boolean visibility)
    {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }
}