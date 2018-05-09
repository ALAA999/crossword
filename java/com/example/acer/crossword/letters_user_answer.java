package com.example.acer.crossword;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by acer on 2/19/2018.
 */

public class letters_user_answer extends RecyclerView.Adapter<letters_user_answer.RecyclerViewHolder> {
    List<String> list;
    Context context;

    public letters_user_answer(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public letters_user_answer.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.letters_user_answer, parent, false);
        letters_user_answer.RecyclerViewHolder listHolder = new letters_user_answer.RecyclerViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String item = list.get(position);
        holder.user_ans_textview.setText(item);
        if (item != " ") {
            holder.user_ans_textview.setBackgroundResource(R.drawable.win_letters_background);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView user_ans_textview;

        public RecyclerViewHolder(View view) {
            super(view);
            user_ans_textview = itemView.findViewById(R.id.letter);
        }
    }
}