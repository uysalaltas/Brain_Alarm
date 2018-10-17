package com.example.uysal.brain_alarm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.uysal.brain_alarm.data.AlarmContract;
import com.example.uysal.brain_alarm.data.AlarmsDbHelper;
import java.util.ArrayList;

public class ZoomedAlarmList extends AppCompatActivity implements BaseColumns{

    ListView listView;
    int position;
    String temps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_alarm_list);

        listView = findViewById(R.id.zoomedAlarmList);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        final ArrayList<String> remTimes = intent.getStringArrayListExtra("list");
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
                temps = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), AlarmList.class);
//                intent.putExtra("Alarm", tempS);

                startActivity(intent);
                instertAlarm();
            }
        });

        //Tests
        System.out.println("remTimes " + remTimes);
        System.out.println("remTimes2 "+ remTimes2);
        System.out.println("remTimes3" + remTimes3);
    }

    private void instertAlarm(){

        String alarmString = temps;

        ContentValues values = new ContentValues();
        values.put(AlarmContract.AlarmEntry.COLUMN_ALARM, alarmString);

        Uri newUri = getContentResolver().insert(AlarmContract.AlarmEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(getApplicationContext(), "Bad", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_SHORT).show();
        }
    }
}
