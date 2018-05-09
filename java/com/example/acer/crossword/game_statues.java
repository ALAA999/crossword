package com.example.acer.crossword;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class game_statues extends Fragment {


    Button next;

    public game_statues() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_statues, container, false);
        next = view.findViewById(R.id.next_btn);
        Bundle bundle = this.getArguments();
        boolean win = bundle.getBoolean("win", false);
        if (win) {
            view.findViewById(R.id.win_img).setBackgroundResource(R.drawable.win);
            final int id = (question_fragment.id) + 1;
            if (id - 1 == 60) {
                next.setVisibility(View.INVISIBLE);
                next.setEnabled(false);
            } else {
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), question_activity.class);
                        intent.putExtra("question_id", id);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        } else {
            next.setBackgroundResource(R.drawable.again);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), question_activity.class);
                    intent.putExtra("question_id", question_fragment.id);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                }
            });
            view.findViewById(R.id.win_img).setBackgroundResource(R.drawable.lose);
        }
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
//                    /*chose_level_fragment fragment1 = new chose_level_fragment();
//                    FragmentUtil.replaceFragment(getActivity(), fragment1, R.id.question_act);*/
//                    return true;
//                }
//                return false;
//            }
//        });
        return view;
    }

}
