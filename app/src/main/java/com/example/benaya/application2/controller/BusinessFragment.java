package com.example.benaya.application2.controller;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.backend.GlobalObject;
import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;
import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.datasource.listDsManager;

/**
 * Created by User on 29/01/2017.
 */

public class BusinessFragment extends
    Fragment {
    public  Cursor c;
    IDSManager db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_business, container, false);
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner2);
        spinner.setEnabled(false);
        db = ManagerFactory.getManager();
        final listDsManager l = new listDsManager();
        GlobalObject.getmain().updateBusinessList();
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Log.d("TAG", "main check2");
                return (getActivity().getContentResolver().query(TravelContent.Business.BUSINESS_URI, new String[]{}, "", new String[]{}, ""));
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);

  //             l.updateBusInListDB(c);
                CursorAdapter adapter = new CursorAdapter(getActivity(), cursor) {
                    @Override
                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
                        TextView tv = new TextView(context);
                        tv.setTextSize(20);
                        tv.setTextColor(Color.GREEN);
                        return tv;
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor cursor) {
                        TextView tv = (TextView) view;
                        tv.setText("[" + cursor.getString(0) + "]  " + cursor.getString(1));
                    }
                };
                Log.d("TAG", "main check3");

                spinner.setAdapter(adapter);
                spinner.setEnabled(true);
            }
        }.execute();
        return rootView;
    }

}


