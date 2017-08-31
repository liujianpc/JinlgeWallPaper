package com.jingle.wallpaperswitcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(arg0, MyIntentService.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startService(intent);
    }

}
