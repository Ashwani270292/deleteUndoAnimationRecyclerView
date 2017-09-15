package com.ashwani.recyclerviewdeleteundoanimated.adapter;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashwani.recyclerviewdeleteundoanimated.R;
import com.ashwani.recyclerviewdeleteundoanimated.bean.CustomBean;
import com.ashwani.recyclerviewdeleteundoanimated.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by hp on 9/13/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<CustomBean> customBeen;
    List<Integer> deletedPositions;
    Context context;
    ItemClickListener itemClickListener;

    private final int VIEW_NORMAL = 1;
    private final int VIEW_DELETED = 2;


    public MyAdapter(Context context, List<CustomBean> customBeen, List<Integer> deletedPositions, ItemClickListener itemClickListener){
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.customBeen = customBeen;
        this.deletedPositions = deletedPositions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_NORMAL){
            View view = LayoutInflater.from(context).inflate(R.layout.card_normal,parent, false);
            return new NormalView(view);
        }else if(viewType == VIEW_DELETED){
            View view = LayoutInflater.from(context).inflate(R.layout.card_delete,parent, false);
            return new DeletedView(view);
        }else
            throw new IllegalStateException();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof DeletedView){
            ((DeletedView)holder).undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position,true);
                }
            });
        }else{
            ((NormalView)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position,false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return customBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(deletedPositions.contains(position))
           return VIEW_DELETED;
        else
            return VIEW_NORMAL;
    }

    public class DeletedView extends RecyclerView.ViewHolder{

        public TextView undo;

        public DeletedView(View item){
            super(item);
            undo = (TextView)item.findViewById(R.id.undo);
        }
    }

    public class NormalView extends RecyclerView.ViewHolder{

        public NormalView(View item){
            super(item);
        }

    }

}
