package com.example.benaya.application2.model.datasource;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

/**
 * Created by User on 29/01/2017.
 */

public class AsyncFileTask extends AsyncTask<String, Void, Boolean> {
    private  listDsManager db = new listDsManager();
    private String resultData = null;
    private Context c;

    final String uribus = "content://com.example.android5777_4390_7178_01/business";
    final String uriact = "content://com.example.android5777_4390_7178_01/attraction";

    public void startDownloadFile(Context context,String urlSpec) {
        this.c = context;
        resultData = null;
   //     final Uri uri = TravelContent.Business.BUSINESS_URI;

        doInBackground(urlSpec);
        execute(urlSpec);
    }

    public void startDownload2Files(Context context,String urlSpec,String urlSpec2) {
        this.c = context;
        resultData = null;
        execute(urlSpec, urlSpec2);
    }


    @Override
    protected Boolean doInBackground(String... params) {
        Boolean result = false;

        try {
     //           if ((params[0]==uriact)||(params[1]==uriact))

                    db.updateBusInListDB(c.getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null));
       //         if ((params[0]==uribus)||(params[1]==uribus))
           //        db.updateBusInListDB(c.getContentResolver().query(Uri.parse(uribus), null, null, null, null));

            if(isCancelled()) {
                return false;
            }

            result = true;
        } catch (Exception e) {
            resultData = e.getMessage();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }

}
