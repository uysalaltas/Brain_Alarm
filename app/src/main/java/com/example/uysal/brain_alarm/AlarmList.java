package com.example.uysal.brain_alarm;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.uysal.brain_alarm.data.AlarmContract;
import com.example.uysal.brain_alarm.data.AlarmsDbHelper;

import java.util.Date;

public class AlarmList extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        LoaderManager.LoaderCallbacks<Cursor>{

    // Definitions
    // ---------------------------------------------------------
    private static int ALARM_LOADER = 0;
    AlarmCursorAdapter alarmCursorAdapter;
    Clock clk;
    Fragments fragments;
    static SharedPreferences sharedPreferences;
    static Date d;
    static FloatingActionButton fab;
    AlarmsDbHelper mDbHelper;
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
        SwipeMenuListView swipeListView = findViewById(R.id.swipe);
        getSupportLoaderManager().initLoader(ALARM_LOADER, null, this);
        alarmCursorAdapter = new AlarmCursorAdapter(this, null);
        swipeListView.setAdapter(alarmCursorAdapter);

        // ---------------------------------------------------------

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


        swipeListView.setMenuCreator(creator);

        swipeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                return false;
            }
        });

        swipeListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Floating Button Operations
        // ---------------------------------------------------------
        fab = findViewById(R.id.fab);
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
        // ---------------------------------------------------------

        /* To access our database, we instantiate our subclass of SQLiteOpenHelper
         and pass the context, which is the current activity. */

        mDbHelper = new AlarmsDbHelper(this);

        // ---------------------------------------------------------
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
        } else if(id == R.id.action_settings2){
            deleteAllAlarms();
            Toast.makeText(getApplicationContext(), "Alarms Deleted", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        clk.setTime(hourOfDay, minute, 0);

        sharedPreferences.edit().putInt("SleepTimeHour", clk.getHours()).apply();
        sharedPreferences.edit().putInt("SleepTimeMinute", clk.getMinutes()).apply();

        Intent intent = new Intent(getApplicationContext(), SetAlarm.class);
        startActivity(intent);
    }

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
        int rowsDeleted = getContentResolver().delete(AlarmContract.AlarmEntry.CONTENT_URI,
                null,
                null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }
}
