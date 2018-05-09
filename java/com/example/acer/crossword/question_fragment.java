package com.example.acer.crossword;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class question_fragment extends Fragment {
    public static TextView chance, time;
    public static String[] user_answer;
    public static String[] array_letter_ans;
    public static boolean ended = false;
    public static ArrayList<String> Aanswers;
    public static int reached = 0, chances, id, timer = 0;
    public static RecyclerView recyclerView2;

    public question_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question_fragment, container, false);
        cler_parameters();
        time = view.findViewById(R.id.timer);
        time.setVisibility(View.VISIBLE);
        Button back = view.findViewById(R.id.back_button);
        back.setVisibility(View.VISIBLE);
        back.setEnabled(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        Bundle bundle = this.getArguments();
        id = bundle.getInt("id");
        ((ImageView) view.findViewById(R.id.question_image)).setImageResource(MainActivity.arrayList.get(id - 1).getImg());
        ((TextView) view.findViewById(R.id.game_title)).setText("لغز رقم " + id);
        String[] array_letters = MainActivity.arrayList.get(id - 1).getQuestion().split(" ");
        array_letter_ans = MainActivity.arrayList.get(id - 1).getRight_ans().split(" ");
        user_answer = new String[array_letter_ans.length];
        ArrayList<String> Aletters = new ArrayList<String>();
        Aanswers = new ArrayList<String>();
        for (int j = 0; j < array_letters.length; j++) {
            Aletters.add(array_letters[j]);
        }
        for (int j = 0; j < array_letter_ans.length; j++) {
            Aanswers.add(" ");//To make the recyclerview of the user letters answers having the same size as the answer
        }//>>>>>>>>>>>>>>>>>>>>>>>defining recyclerviews
        RecyclerView recyclerView = view.findViewById(R.id.queston_rv);
        recyclerView.setHasFixedSize(true);//The id is --1 because id save in sql starts from 1
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        Grid_adapter_question grid_adapter_question = new Grid_adapter_question(Aletters,
                getActivity());
        recyclerView.setAdapter(grid_adapter_question);

        recyclerView2 = view.findViewById(R.id.rv2);
        letters_user_answer lettersUserAnswer = new letters_user_answer(Aanswers, getActivity());
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManagaer);
        recyclerView2.setAdapter(lettersUserAnswer);
        timer();
        return view;
    }

    public void cler_parameters() {
        chance = null;
        user_answer = null;
        array_letter_ans = null;
        Aanswers = null;
        reached = 0;
        chances = 3;
        timer = 0;
        ended = false;
        id = 0;
    }

    public void timer() {
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (!ended) {
                    ++timer;
                    timer();
                }
            }
        }.start();
    }
}
