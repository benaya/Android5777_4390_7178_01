package com.example.benaya.application2.model.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.benaya.application2.model.datasource.AsyncFileTask;

/**
 * Created by User on 29/01/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    final String URI_BUS = "content://com.example.android5777_4390_7178_01/business";
    final String URI_ATT =  "content://com.example.android5777_4390_7178_01/attraction";
 //   AsyncFileTask dft = new AsyncFileTask();
    @Override
    public void onReceive(final Context context, Intent intent) {
        CharSequence intentData = intent.getCharSequenceExtra("message");
        if (intent.getAction().matches("HAGER.VAKNIN.ACTION_SERVICE.ACT"))
        {
            GlobalObject.getmain().updateActivitiesList();
            Log.d("TAG", "NEW ACTIVITY ADDED! " + intentData.toString());
        }
        else if (intent.getAction().matches("HAGER.VAKNIN.ACTION_SERVICE.BUS"))
        {
            GlobalObject.getmain().updateBusinessList();
            Log.d("TAG", "NEW BUSINESS ADDED! " + intentData.toString());}
    }

}

