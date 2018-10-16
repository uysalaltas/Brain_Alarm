package com.example.uysal.brain_alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WakeUpFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up_frame);
    }

    public void closeAlarm(View view) {
        AlarmReceiver.ringtone.stop();
        finish();
        moveTaskToBack(true);
    }
}
