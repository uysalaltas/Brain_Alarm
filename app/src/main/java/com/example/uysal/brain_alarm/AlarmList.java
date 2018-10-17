package com.example.uysal.brain_alarm;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.uysal.brain_alarm.data.AlarmContract;
import com.example.uysal.brain_alarm.data.AlarmsDbHelper;

import java.util.ArrayList;

public class AlarmList extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, LoaderManager.LoaderCallbacks<Cursor>{

    private static int ALARM_LOADER = 0;

    AlarmCursorAdapter alarmCursorAdapter;
    //TODO shoudl talk which adapter will be set on listView and the object which not use anymore.
    // Definitions
    Clock clk;
    Fragments fragments;
    static SharedPreferences sharedPreferences;
    ListView alarmList;
    ArrayList<String> alarms;
    private AlarmsDbHelper mDbHelper;
    // ---------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

        // Check sleepDelay. If it is empty show on fragment.
        // ---------------------------------------------------------
        sharedPreferences = this.getSharedPreferences("com.example.uysal.brain_alarm", Context.MODE_PRIVATE);
        int sleepDelay = sharedPreferences.getInt("SleepDelay", 0);

        if(sleepDelay == 0) {
            fragments = new Fragments();
            fragments.show(getSupportFragmentManager(), "Uyuma Süresi");
        }
        // ---------------------------------------------------------

        // Toolbar Operations
        // ---------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ---------------------------------------------------------

        clk = new Clock();

        // List Operations
        // ---------------------------------------------------------
//        Intent intent = getIntent();
//        String alarm = intent.getStringExtra("Alarm");
//
        alarmList = findViewById(R.id.alarm_list);

        getSupportLoaderManager().initLoader(ALARM_LOADER, null, this);

        alarmCursorAdapter = new AlarmCursorAdapter(this, null);

        alarmList.setAdapter(alarmCursorAdapter);
//        alarms = new ArrayList<>();
//        alarms.add(alarm);

//        AlarmAdapter alarmAdapter=new AlarmAdapter(Alarms.getAlarms(), this);
//        alarmList.setAdapter(alarmAdapter);
        // ---------------------------------------------------------

        // Floating Button Operations
        // ---------------------------------------------------------
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Lütfen uyuma saatinizi giriniz.", Toast.LENGTH_LONG);
                toast.show();

            }
        });
        // ---------------------------------------------------------

        //DATABASE
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new AlarmsDbHelper(this);
        //----------------------------------------------------------

        alarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteAllAlarms();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fragments = new Fragments();
            fragments.show(getSupportFragmentManager(), "Uyuma Süresi");

            int sleepDelay = sharedPreferences.getInt("SleepDelay", 0);
            System.out.println(sleepDelay);

            return true;
        }
        else if(id == R.id.action_settings1){
            //TODO remove individual alarm

        }
        else if(id == R.id.action_settings2){
            deleteAllAlarms();
            System.out.println("asd");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        clk.setTime(hourOfDay, minute, 0);
        System.out.println(hourOfDay + ":" + minute);

        sharedPreferences.edit().putInt("SleepTimeHour", clk.getHours()).apply();
        sharedPreferences.edit().putInt("SleepTimeMinute", clk.getMinutes()).apply();

        Intent intent = new Intent(getApplicationContext(), SetAlarm.class);
        startActivity(intent);
    }

    //Database
    //TODO some debug line must remove
//    private void displayDataBaseInfo(){
//
//        String[] projection = {
//                AlarmContract.AlarmEntry.TABLE_NAME,
//                AlarmContract.AlarmEntry._ID,
//                AlarmContract.AlarmEntry.COLUMN_ALARM
//        };
//
//        Cursor cursor = getContentResolver().query(
//                AlarmContract.AlarmEntry.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null);
//        System.out.println("Alarms table contains " + cursor.getCount() + " alarms");
//
//        AlarmCursorAdapter adapter = new AlarmCursorAdapter(this, cursor);
//
//        alarmList.setAdapter(adapter);
//    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                AlarmContract.AlarmEntry.TABLE_NAME,
                AlarmContract.AlarmEntry._ID,
                AlarmContract.AlarmEntry.COLUMN_ALARM
        };

        return new CursorLoader(this,
                AlarmContract.AlarmEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        alarmCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        alarmCursorAdapter.swapCursor(null);
    }

    private void deleteAllAlarms(){
        int rowsDeleted = getContentResolver().delete(AlarmContract.AlarmEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }

}
