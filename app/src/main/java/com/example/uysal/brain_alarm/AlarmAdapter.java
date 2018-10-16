package com.example.uysal.brain_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<String> list;
    private Context context;

    public AlarmAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        alarmSet(ZoomedAlarmList.SplitToInt(Alarms.getAlarms().get(position))[0],
                ZoomedAlarmList.SplitToInt(Alarms.getAlarms().get(position))[1], position);

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_alarm_list, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.alarm);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Switch switchOnOff = view.findViewById(R.id.switch1);

        switchOnOff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(context.getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();
                    alarmSet(ZoomedAlarmList.SplitToInt(Alarms.getAlarms().get(position))[0],
                            ZoomedAlarmList.SplitToInt(Alarms.getAlarms().get(position))[1], position);
                } else {
                    Toast.makeText(context.getApplicationContext(), "Alarm Off", Toast.LENGTH_SHORT).show();
                    alarmOff(position);
                }
            }
        });

        return view;
    }

    public void alarmSet(int hr, int mnt, int rqCode) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(this.context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, rqCode, intent,
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
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(this.context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, rqCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.cancel(alarmIntent);


    }

}
