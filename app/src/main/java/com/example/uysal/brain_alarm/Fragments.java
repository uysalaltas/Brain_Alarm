package com.example.uysal.brain_alarm;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragments extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_fragments, null);

        //SET TITLE DIALOG TITLE
        getDialog().setTitle("Alarm Suggestions");

        return rootView;
    }

}
