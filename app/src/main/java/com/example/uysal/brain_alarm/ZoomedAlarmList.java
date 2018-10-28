package com.example.uysal.brain_alarm;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uysal.brain_alarm.data.AlarmContract;

import java.util.ArrayList;
import java.util.HashMap;

import me.rorschach.library.ShaderSeekArc;

public class ZoomedAlarmList extends AppCompatActivity {
    int position;
    String tempS;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed_alarm_list);
        setTitle("Create Alarm");
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        ArrayList<String> remTimes = intent.getStringArrayListExtra("list");
        ArrayList<String> remTimes2 = intent.getStringArrayListExtra("list2");
        ArrayList<String> remTimes3 = intent.getStringArrayListExtra("list3");


        final ShaderSeekArc seekArc = (ShaderSeekArc) findViewById(R.id.seek_arc);
        textView = findViewById(R.id.textView);
        seekArc.setShowMark(false);
        seekArc.setShowProgress(false);

        final HashMap<Integer, String> hashMap1 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap2 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap3 = new HashMap<Integer, String>();

        if(remTimes != null){
            for (int i = 0; i < remTimes.size(); i++){
                hashMap1.put(i,remTimes.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes.size());
            seekArc.setProgress(0);
            textView.setText(remTimes.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap1.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes2 != null){
            for (int i = 0; i < remTimes2.size(); i++){
                hashMap2.put(i,remTimes2.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes2.size());
            seekArc.setProgress(0);
            textView.setText(remTimes2.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap2.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes3 != null){
            for (int i = 0; i < remTimes3.size(); i++){
                hashMap3.put(i,remTimes3.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes3.size());
            seekArc.setProgress(0);
            textView.setText(remTimes3.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap3.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_ok) {
            tempS = String.valueOf(textView.getText());
            instertAlarm();
            Intent intent = new Intent(getApplicationContext(), AlarmList.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_settings_clear){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void instertAlarm(){
        String alarmString = tempS;
        ContentValues values = new ContentValues();
        values.put(AlarmContract.AlarmEntry.COLUMN_ALARM, alarmString);
        values.put(AlarmContract.AlarmEntry.COLUMN_STATUS, 1);
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
