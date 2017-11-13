package com.test.recyclerview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    private Context context;
    private List<String> data;
    private LayoutInflater lif;

    public RecyclerViewAdapter(Context context, List<String> data)
    {
        this.context = context;
        this.data = data;
        lif = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
//        View view = lif.inflate(R.layout.item_recyclerview,null);
        View view = lif.inflate(R.layout.item_recyclerview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.tv_item.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    mOnItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    //add item
    public void add(int position)
    {
        data.add(position,"new item");
        notifyItemInserted(position);
    }

    //delete item
    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener l)
    {
        this.mOnItemClickListener = l;
    }

}
class MyViewHolder extends ViewHolder
{
    TextView tv_item;
    public MyViewHolder(View itemView)
    {
        super(itemView);
        tv_item = (TextView) itemView.findViewById(R.id.tv_item);
    }
}


