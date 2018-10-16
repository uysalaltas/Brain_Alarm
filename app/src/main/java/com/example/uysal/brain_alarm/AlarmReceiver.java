package com.example.uysal.brain_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    public static Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Wake Up Device
        // ---------------------------------------------------------
        PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
        // ---------------------------------------------------------

        // Set Alarm And Ring The Tone
        // ---------------------------------------------------------
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);

        Toast.makeText(context, "Alarm Çalıyor!", Toast.LENGTH_LONG).show();
        ringtone.play();
        // ---------------------------------------------------------

        Intent i = new Intent(context.getApplicationContext(), WakeUpFrame.class);
        context.startActivity(i);
    }

}
