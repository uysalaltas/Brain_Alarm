package com.example.uysal.brain_alarm;

import android.content.Context;
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

        //switchOnOff.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v) {
        //        //do something
        //        list.remove(position); //or some other task
        //        notifyDataSetChanged();
        //    }
        //});

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(context.getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context.getApplicationContext(), "Alarm Off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
