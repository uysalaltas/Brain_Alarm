package com.example.uysal.brain_alarm;

import android.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SetAlarm extends AppCompatActivity {

    ListView wakeupList;
    private String clk = "";
    private int sleepHour;
    private int sleepMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        sleepHour = AlarmList.sharedPreferences.getInt("SleepTimeHour", 0);
        sleepMinute = AlarmList.sharedPreferences.getInt("SleepTimeMinute", 0);
        printTime();

        wakeupList = findViewById(R.id.wakeup_alarms);
        ArrayList<String> remTimes = new ArrayList<>();
        remTimes.add(clk);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, remTimes);

        wakeupList.setAdapter(arrayAdapter);

        wakeupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment newFragment = new Fragments();
                newFragment.show(getSupportFragmentManager(), "Fragment");
            }
        });

    }

    public String printTime() {
        if (sleepHour < 10) {
            clk = "0" + String.valueOf(sleepHour) + ":";
        } else {clk = String.valueOf(sleepHour)+":";}
        if (sleepMinute < 10) {
            clk = clk + "0" + String.valueOf(sleepMinute);
        } else {clk = clk + String.valueOf(sleepMinute);}
        return clk;
    }
}
