package com.example.uysal.brain_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uysal.brain_alarm.data.AlarmContract;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.uysal.brain_alarm.AlarmList.swipeListView;

public class AlarmCursorAdapter extends CursorAdapter{

    private ArrayList<String> list;
    Context ctx;
    int a = 1;

    public AlarmCursorAdapter(Context context, Cursor c){
        super(context, c, 0);
        this.ctx = context;
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *1
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_alarm_list, parent, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.listview_alarm_list, null);
        }
        convertView.setTag(position);
        return super.getView(position, convertView, parent);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        final int pos = (Integer) view.getTag();

        this.ctx = context;
        // Find individual views that we want to modify in the list item layout
        TextView alarmTextView = (TextView) view.findViewById(R.id.alarm);
        // Find the columns of alarm attributes that we're interested in
        final int nameColumnIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_ALARM);
        final int statusColumnIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.COLUMN_STATUS);

        // Read the alarm attributes from the Cursor for the current alarm
        final String alarmString = cursor.getString(nameColumnIndex);
        final int status = cursor.getInt(statusColumnIndex);

        alarmTextView.setText(alarmString);
        alarmTextView.setFocusable(false);
        alarmSet(SplitToInt(alarmString)[0], SplitToInt(alarmString)[1], cursor.getPosition());

        Switch switchOnOff = view.findViewById(R.id.switch1);
        switchOnOff.setFocusable(false);
        final AlarmList al = new AlarmList();
        if(status == 1){
            switchOnOff.setChecked(true);
        }else {
            switchOnOff.setChecked(false);
        }
        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(context.getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();
                    alarmSet(SplitToInt(alarmString)[0], SplitToInt(alarmString)[1], pos);
                    AlarmList.statusHandle(ctx, 1, swipeListView.getItemIdAtPosition(pos));
                } else {
                    Toast.makeText(context.getApplicationContext(), "Alarm Off", Toast.LENGTH_SHORT).show();
                    alarmOff(pos);
                    AlarmList.statusHandle(ctx, 0, swipeListView.getItemIdAtPosition(pos));
                }
            }
        });

    }


    public void alarmSet(int hr, int mnt, int rqCode) {
        AlarmManager alarmMgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(this.ctx, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx, rqCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hr);
        calendar.set(Calendar.MINUTE, mnt);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
    }

    public void alarmOff(int rqCode) {
        AlarmManager alarmMgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(this.ctx, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ctx, rqCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.cancel(alarmIntent);
        //alarmIntent.cancel();
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
