package com.project.lab6homework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import java.util.Objects;

//https://www.youtube.com/watch?v=HDVyFsFUuVg
public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Objects.equals(intent.getAction(), Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            try {
                boolean isOn = Settings.Global.getInt(
                        context.getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON
                ) == 1;

                Log.d("AIRPLANE MODE", isOn ? "ON" : "OFF");

            } catch (Settings.SettingNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
