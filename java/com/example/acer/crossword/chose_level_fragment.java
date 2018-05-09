package com.example.acer.crossword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.animatedrv.AnimatedRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class chose_level_fragment extends Fragment {
    Grid_adapter_levels grid_adapter_levels;
    RecyclerView recyclerView;
    View view;

    public chose_level_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chose_level_fragment, container, false);
        setadapter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setadapter();
    }

    public void setadapter() {
        recyclerView = view.findViewById(R.id.chose_level_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        grid_adapter_levels = new Grid_adapter_levels(MainActivity.arrayList, getActivity());
/////////////////////////////////////////////////////////////////////////////////////////////////////
        AnimatedRecyclerView recyclerView2 = new AnimatedRecyclerView.Builder(getActivity())
                .orientation(LinearLayoutManager.VERTICAL)
                .layoutManagerType(AnimatedRecyclerView.LayoutManagerType.LINEAR)
                .animation(R.anim.layout_animation_from_bottom)
                .animationDuration(600)
                .reverse(false)
                .build();
        grid_adapter_levels.notifyDataSetChanged();
        recyclerView2.scheduleLayoutAnimation();
        recyclerView.setAdapter(grid_adapter_levels);
    }
}
