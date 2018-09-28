package com.example.uysal.brain_alarm;

import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;

import static com.example.uysal.brain_alarm.AlarmList.sharedPreferences;

public class SetAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Clock clock;
    ListView wakeupList;
    private String clk = "";
    private int sleepHour;
    private int sleepMinute;
    EditText sleepTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        clock = new Clock();

        // Getting sleep time from shared preferences
        sleepHour = sharedPreferences.getInt("SleepTimeHour", 0);
        sleepMinute = sharedPreferences.getInt("SleepTimeMinute", 0);
        printTime();
        // ---------------------------------------------------------

        // Setting EditText with sleep time
        sleepTime = findViewById(R.id.sleepTime2_txt);
        sleepTime.setText(clk);
        sleepTime.setKeyListener(null);
        // ---------------------------------------------------------

        // Setting sleep time to ListView
        wakeupList = findViewById(R.id.wakeup_alarms);
        ArrayList<String> remTimes = new ArrayList<>();
        remTimes.add(clk);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, remTimes);

        wakeupList.setAdapter(arrayAdapter);
        // ---------------------------------------------------------

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

    // Update sleep time if necessary
    public void editSleepTime(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        clock.setTime(hourOfDay, minute, 0);
        System.out.println(hourOfDay + ":" + minute);

        sharedPreferences.edit().putInt("SleepTimeHour", clock.getHours()).apply();
        sharedPreferences.edit().putInt("SleepTimeMinute", clock.getMinutes()).apply();

        // Restart activity
        finish();
        startActivity(getIntent());
    }
    // ---------------------------------------------------------
}
