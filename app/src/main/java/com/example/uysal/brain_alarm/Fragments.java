package com.example.uysal.brain_alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.uysal.brain_alarm.AlarmList.sharedPreferences;

public class Fragments extends DialogFragment {

    Button sleepDelay;
    RadioGroup rG;
    RadioButton a10;
    RadioButton b20;
    RadioButton c30;
    RadioButton d60;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_fragments, container, false);
        sleepDelay = rootView.findViewById(R.id.sleepDelay_Btn);
        rG = rootView.findViewById(R.id.radioGroup);
        a10 = rootView.findViewById(R.id.a);
        b20 = rootView.findViewById(R.id.b);
        c30 = rootView.findViewById(R.id.c);
        d60 = rootView.findViewById(R.id.d);

        sleepDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rG.getCheckedRadioButtonId();

                switch (selectedId) {
                    case R.id.a:
                        System.out.println("10");
                        sharedPreferences.edit().putInt("SleepDelay", 10).apply();
                        break;
                    case R.id.b:
                        System.out.println("20");
                        sharedPreferences.edit().putInt("SleepDelay", 20).apply();
                        break;
                    case R.id.c:
                        System.out.println("30");
                        sharedPreferences.edit().putInt("SleepDelay", 30).apply();
                        break;
                    case R.id.d:
                        System.out.println("60");
                        sharedPreferences.edit().putInt("SleepDelay", 60).apply();
                        break;
                }
                Toast.makeText(getContext(), "Seçim Kaydedildi. Ayarlar menüsünden güncelleyebilirsiniz." , Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        return rootView;
    }

}
