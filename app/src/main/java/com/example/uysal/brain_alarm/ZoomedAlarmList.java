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

import java.sql.SQLSyntaxErrorException;
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
        ArrayList<String> remTimes4 = intent.getStringArrayListExtra("list4");
        ArrayList<String> remTimes5 = intent.getStringArrayListExtra("list5");
        ArrayList<String> remTimes6 = intent.getStringArrayListExtra("list6");
        ArrayList<String> remTimes7 = intent.getStringArrayListExtra("list7");
        ArrayList<String> remTimes8 = intent.getStringArrayListExtra("list8");
        ArrayList<String> remTimes9 = intent.getStringArrayListExtra("list9");

        final ShaderSeekArc seekArc = findViewById(R.id.seek_arc);
        textView = findViewById(R.id.textView);
        seekArc.setShowMark(false);
        seekArc.setShowProgress(false);
        seekArc.setStartColor(0xFF1a237e);
        seekArc.setEndColor(0xFF1a237e);

        final HashMap<Integer, String> hashMap1 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap2 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap3 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap4 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap5 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap6 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap7 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap8 = new HashMap<Integer, String>();
        final HashMap<Integer, String> hashMap9 = new HashMap<Integer, String>();

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

        if(remTimes4 != null){
            for (int i = 0; i < remTimes4.size(); i++){
                hashMap4.put(i,remTimes4.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes4.size());
            seekArc.setProgress(0);
            textView.setText(remTimes4.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap4.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes5 != null){
            for (int i = 0; i < remTimes5.size(); i++){
                hashMap5.put(i,remTimes5.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes5.size());
            seekArc.setProgress(0);
            textView.setText(remTimes5.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap5.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes6 != null){
            for (int i = 0; i < remTimes6.size(); i++){
                hashMap6.put(i,remTimes6.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes6.size());
            seekArc.setProgress(0);
            textView.setText(remTimes6.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap6.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes7 != null){
            for (int i = 0; i < remTimes7.size(); i++){
                hashMap7.put(i,remTimes7.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes7.size());
            seekArc.setProgress(0);
            textView.setText(remTimes7.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap7.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes8 != null){
            for (int i = 0; i < remTimes8.size(); i++){
                hashMap8.put(i,remTimes8.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes8.size());
            seekArc.setProgress(0);
            textView.setText(remTimes8.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap8.get((int) v));
                }

                @Override
                public void onStartTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }

                @Override
                public void onStopTrackingTouch(ShaderSeekArc shaderSeekArc) {

                }
            });
        }

        if(remTimes9 != null){
            for (int i = 0; i < remTimes9.size(); i++){
                hashMap9.put(i,remTimes9.get(i));
            }
            seekArc.setStartValue(0);
            seekArc.setEndValue(remTimes9.size());
            seekArc.setProgress(0);
            textView.setText(remTimes9.get(0));

            seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
                @Override
                public void onProgressChanged(ShaderSeekArc shaderSeekArc, float v) {
                    textView.setText(hashMap9.get((int) v));
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
