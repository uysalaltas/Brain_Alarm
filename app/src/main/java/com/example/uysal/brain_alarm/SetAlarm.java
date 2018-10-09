package com.example.uysal.brain_alarm;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.uysal.brain_alarm.AlarmList.sharedPreferences;

public class SetAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Clock clock;
    ListView wakeupList;
    private int sleepHour;
    private int sleepMinute;
    EditText sleepTime;
    public static ArrayList<String> remTimes;
    public static ArrayList<String> remTimes2;
    public static ArrayList<String> remTimes3;
    ArrayList<String> remTimesGap;
    ArrayAdapter arrayAdapterGap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        clock = new Clock();

        // Getting sleep time from shared preferences
        sleepHour = sharedPreferences.getInt("SleepTimeHour", 0);
        sleepMinute = sharedPreferences.getInt("SleepTimeMinute", 0);
        // ---------------------------------------------------------

        // Setting EditText with sleep time
        sleepTime = findViewById(R.id.sleepTime2_txt);
        sleepTime.setKeyListener(null);
        // ---------------------------------------------------------

        remTimes = new ArrayList<String>();
        remTimes2 = new ArrayList<String>();
        remTimes3 = new ArrayList<String>();

        // Setting sleep time to ListView
        wakeupList = findViewById(R.id.wakeup_alarms);

        calculateAlarms();

        wakeupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ZoomedAlarmList.class);
                intent.putExtra("position", position);
                switch (position){
                    case 0: intent.putExtra("list",remTimes); break;
                    case 1: intent.putExtra("list2",remTimes2); break;
                    case 2: intent.putExtra("list3",remTimes3); break;
                }
                startActivity(intent);
            }
        });
    }

    //Calculate possible alarm suggestion
    public void calculateAlarms(){
        ArrayList<String> remTimesGapMin = new ArrayList<String>();
        ArrayList<String> remTimesGapMax = new ArrayList<String>();
        remTimesGap = new ArrayList<String>();
        Clock clock1 = new Clock();
        clock1.setTime(sleepHour,sleepMinute,0);

        for(int i = 0; i<(1440); i = i+1){
            if(i >= 100 && i <= 110){

                remTimes.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 100)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 110)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 240 && i <= 260){

                remTimes2.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 240)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 260)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 330 && i <=370){

                remTimes3.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 330)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 370)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else{
                clock1.incrementMinutes(1);
            }
        }

        for(int i=0; i < remTimesGapMax.size(); i++){

            remTimesGap.add(remTimesGapMin.get(i) + " - " + remTimesGapMax.get(i));
        }

        arrayAdapterGap = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, remTimesGap);
        wakeupList.setAdapter(arrayAdapterGap);

        //Test
        System.out.println("Class:SetAlarm ArrayList:remTimes: " + remTimes);
        System.out.println("Class:SetAlarm ArrayList:remTimes2 "+ remTimes2);
        System.out.println("Class:SetAlarm ArrayList:remTimes3" + remTimes3);
    }

    //Generation of time format
    public String generateTimeFormat (int hr, int min )
    {
        String clk="00:00";
        if (hr < 10){
            clk = "0" + String.valueOf(hr) + ":";
        }else{
            clk = String.valueOf(hr)+":";}
        if (min < 10){
            clk = clk + "0"+ String.valueOf(min);
        }else {
            clk = clk + String.valueOf(min);
        }
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
