package com.example.acer.crossword;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by acer on 2/19/2018.
 */

public class Grid_adapter_levels extends RecyclerView.Adapter<Grid_adapter_levels.RecyclerViewHolder> {
    List<questions> list;
    Context context;

    public Grid_adapter_levels(List<questions> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Grid_adapter_levels.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.grid_levels_item, parent, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final Grid_adapter_levels.RecyclerViewHolder holder, final int position) {
        final questions item = list.get(position);
//        Log.e("position" , position + "");
//        Log.e("ID" , item.getId() + "");
        holder.setIsRecyclable(false);// To avoid the mistakes that recyclerview makes
        holder.level_time.setText(item.is_ans + "s");
        try {
            int prevous_num = list.get(position - 1).getIs_ans();
            //if (prevous_num > 0) {
                holder.level.setText(item.getId() + "");
                holder.level_time.setTextColor(Color.RED);
                holder.level_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, question_activity.class);
                        int id = item.getId();
                        intent.putExtra("question_id", id);
                        context.startActivity(intent);
                    }
                });
           // } else {// when player hasn't unlocked this level
            //    holder.level.setBackgroundResource(R.drawable.ic_lock_outline_black_24dp);
           // }
        } catch (Exception e) {//it will work only for the first level first time
            holder.level.setText(item.getId() + "");
            holder.level_time.setTextColor(Color.RED);
//            Log.e("error", item.getId() + "");
            holder.level_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, question_activity.class);
                    int id = item.getId();
                    intent.putExtra("question_id", id);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView level, level_time;
        CardView level_card;

        public RecyclerViewHolder(View view) {
            super(view);
            level = itemView.findViewById(R.id.level);
            level_card = itemView.findViewById(R.id.level_card);
            level_time = itemView.findViewById(R.id.level_time);
        }
    }
}
