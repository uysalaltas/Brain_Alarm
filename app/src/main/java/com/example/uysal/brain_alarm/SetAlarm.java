package com.example.uysal.brain_alarm;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.uysal.brain_alarm.AlarmList.sharedPreferences;

public class SetAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Clock clock;
    ListView wakeupList;
    int SLEEP_DELAY;
    private String clk1 = "";
    private int sleepHour;
    private int sleepMinute;
    Button sleepTime;
    public static ArrayList<String> remTimes;
    public static ArrayList<String> remTimes2;
    public static ArrayList<String> remTimes3;
    public static ArrayList<String> remTimes4;
    public static ArrayList<String> remTimes5;
    public static ArrayList<String> remTimes6;
    public static ArrayList<String> remTimes7;
    public static ArrayList<String> remTimes8;
    public static ArrayList<String> remTimes9;
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
        clk1 = generateTimeFormat(sleepHour, sleepMinute);

        SLEEP_DELAY = sharedPreferences.getInt("SleepDelay", 0);
        // ---------------------------------------------------------

        // Rem activities added
        remTimes = new ArrayList<String>();
        remTimes2 = new ArrayList<String>();
        remTimes3 = new ArrayList<String>();
        remTimes4 = new ArrayList<String>();
        remTimes5 = new ArrayList<String>();
        remTimes6 = new ArrayList<String>();
        remTimes7 = new ArrayList<String>();
        remTimes8 = new ArrayList<String>();
        remTimes9 = new ArrayList<String>();
        // ---------------------------------------------------------

        // Setting EditText with sleep time
        sleepTime = findViewById(R.id.editSleepTime_Btn);
        sleepTime.setText(clk1);
        // ---------------------------------------------------------

        // Setting sleep time to ListView
        wakeupList = findViewById(R.id.wakeup_alarms);

        // ---------------------------------------------------------
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
                    case 3: intent.putExtra("list4",remTimes4); break;
                    case 4: intent.putExtra("list5",remTimes5); break;
                    case 5: intent.putExtra("list6",remTimes6); break;
                    case 6: intent.putExtra("list7",remTimes7); break;
                    case 7: intent.putExtra("list8",remTimes8); break;
                    case 8: intent.putExtra("list9",remTimes9); break;
                }
                startActivity(intent);
            }
        });

    }

    // Update sleep time if necessary
    public void editSleepTime(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        clock.setTime(hourOfDay, minute, 0);

        sharedPreferences.edit().putInt("SleepTimeHour", clock.getHours()).apply();
        sharedPreferences.edit().putInt("SleepTimeMinute", clock.getMinutes()).apply();

        // Restart activity
        finish();
        startActivity(getIntent());
    }
    // ---------------------------------------------------------

    public void calculateAlarms(){
        ArrayList<String> remTimesGapMin = new ArrayList<String>();
        ArrayList<String> remTimesGapMax = new ArrayList<String>();
        remTimesGap = new ArrayList<String>();
        Clock clock1 = new Clock();
        clock1.setTime(sleepHour,sleepMinute,0);

        for(int i = 0; i<(1440); i = i+1){
            if(i >= 85 + SLEEP_DELAY && i <= 90 + SLEEP_DELAY){

                remTimes.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 85 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 90 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 190 + SLEEP_DELAY && i <= 200 + SLEEP_DELAY){

                remTimes2.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 190 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 200 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 290 + SLEEP_DELAY && i <=305 + SLEEP_DELAY){

                remTimes3.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 290 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 305 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 395 + SLEEP_DELAY && i <=415 + SLEEP_DELAY){

                remTimes4.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 395 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 415 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 505 + SLEEP_DELAY && i <=515 + SLEEP_DELAY){

                remTimes5.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 505 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 515 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 605 + SLEEP_DELAY && i <=615 + SLEEP_DELAY){

                remTimes6.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 605 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 615 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 705 + SLEEP_DELAY && i <=725 + SLEEP_DELAY){

                remTimes6.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 705 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 725 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 815 + SLEEP_DELAY && i <=835 + SLEEP_DELAY){

                remTimes6.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 815 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 835 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else if(i >= 925 + SLEEP_DELAY && i <=950 + SLEEP_DELAY){

                remTimes6.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 925 + SLEEP_DELAY)remTimesGapMin.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                if (i == 950 + SLEEP_DELAY)remTimesGapMax.add(generateTimeFormat(clock1.getHours(), clock1.getMinutes()));
                clock1.incrementMinutes(1);

            }else{
                clock1.incrementMinutes(1);
            }
        }

        for(int i=0; i < remTimesGapMax.size(); i++){

            remTimesGap.add(remTimesGapMin.get(i) + " - " + remTimesGapMax.get(i));
        }

        //arrayAdapterGap = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, remTimesGap);

        CustomLayout customLayout = new CustomLayout(remTimesGap, this);
        wakeupList.setAdapter(customLayout);

    }

    //Generation of time format
    public String generateTimeFormat (int hr, int min ) {
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
}
