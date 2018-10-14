package com.example.uysal.brain_alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ZoomedAlarmList extends AppCompatActivity {
    ListView listView;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_alarm_list);

        listView = findViewById(R.id.zoomedAlarmList);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        ArrayList<String> remTimes = intent.getStringArrayListExtra("list");
        ArrayList<String> remTimes2 = intent.getStringArrayListExtra("list2");
        ArrayList<String> remTimes3 = intent.getStringArrayListExtra("list3");
        switch (position){
            case 0: ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,remTimes);
                listView.setAdapter(arrayAdapter);
                break;
            case 1: ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,remTimes2);
                listView.setAdapter(arrayAdapter2);
                break;
            case 2: ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,remTimes3);
                listView.setAdapter(arrayAdapter3);
                break;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int temp = listView.getPositionForView(view);
                String tempS = (String) listView.getItemAtPosition(temp);

                Intent intent = new Intent(getApplicationContext(), AlarmList.class);
//                intent.putExtra("Alarm", tempS);

                Alarms.getAlarms().add(tempS);

                startActivity(intent);
            }
        });

        //Tests
        System.out.println("remTimes " + remTimes);
        System.out.println("remTimes2 "+ remTimes2);
        System.out.println("remTimes3" + remTimes3);
    }

    public static int[] SplitToInt(String input){
        String[] time = input.split("\\:");
        int hour = Integer.valueOf(time[0]);
        int minute =  Integer.valueOf(time[1]);

        int [] merge = new int[2];
        merge[0] = hour;
        merge[1] = minute;

        return merge;
    }
}
