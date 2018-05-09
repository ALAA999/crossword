package com.example.acer.crossword;

import android.content.Context;
import android.os.Bundle;
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

public class Grid_adapter_question extends RecyclerView.Adapter<Grid_adapter_question.RecyclerViewHolder> {
    List<String> list;
    Context context;

    public Grid_adapter_question(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Grid_adapter_question.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.question_item, parent, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final String item = list.get(position);
        holder.letter.setText(item);
        holder.letter_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.equals(question_fragment.array_letter_ans[question_fragment.reached])) {
                    holder.letter.setBackgroundResource(R.drawable.win_letters_background);
                    //we put the letters in a String array then add the letter to Aanswer then set the adapter to new one
                    question_fragment.user_answer[question_fragment.reached] = item;
                    question_fragment.Aanswers.set(question_fragment.reached, item);// we will set the spaces we added before into new letters
                    question_fragment.recyclerView2.setAdapter(new letters_user_answer(question_fragment.Aanswers, context));
                    ++question_fragment.reached;
                    if (question_fragment.reached == question_fragment.array_letter_ans.length) {
                        question_fragment.ended = true;
                        open_game_statues(true , question_fragment.timer);
                    }
                } else {
                    holder.letter.setBackgroundResource(R.drawable.lose_letter_background);
                    --question_fragment.chances;
                    question_fragment.time.setText(question_fragment.chances + "");
                    if (question_fragment.chances == 0) {
                        question_fragment.ended = true;
                        open_game_statues(false , 0);
                    }
                }
            }
        });
    }

    public void open_game_statues(boolean win , int time_spent) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("win", win);
        game_statues fragment1 = new game_statues();
        fragment1.setArguments(bundle);
        FragmentUtil.replaceFragment(context, fragment1, R.id.question_act);
        int scoore = MainActivity.arrayList.get(question_fragment.id - 1).getIs_ans();
        if (scoore == 0 && win && time_spent < 9999) {//don't save new scoore in case that the new scoore is lower than the old one
            DatabaseHelper helper = new DatabaseHelper(context);
            MainActivity.arrayList.get(question_fragment.id - 1).setIs_ans(time_spent);
            helper.change_level_scoore(time_spent, question_fragment.id);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView letter;
        LinearLayout letter_click;

        public RecyclerViewHolder(View view) {
            super(view);
            letter = itemView.findViewById(R.id.letter);
            letter_click = itemView.findViewById(R.id.letter_click);
        }
    }
}
