package com.example.uysal.brain_alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.util.Calendar;

public class WakeUpFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up_frame);

        TextView tx = findViewById(R.id.timeText);

        int h = Calendar.HOUR_OF_DAY;
        int m = Calendar.MINUTE;

        String time = String.valueOf(h) + ":" + String.valueOf(m);
        tx.setText(time);

        SwipeButton enableButton = findViewById(R.id.swipe_btn);
        enableButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                AlarmReceiver.ringtone.stop();
                finish();
                moveTaskToBack(true);
            }
        });
    }

}
