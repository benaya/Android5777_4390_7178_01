package com.example.benaya.application2.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.benaya.application2.R;

import java.util.List;

/**
 * Created by יונתן on 26/02/2017.
 */

public class MyAdapterAtt <String> extends ArrayAdapter<String> {


    Context mCtx;
    List<String> list;
    // ListViewFilter ListFilter;

    ListViewFilterAtt ListFilterAtt;

    public void updateList(List<String> l) {
        list = l;
    }

    public MyAdapterAtt(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, (List<String>) objects);
        mCtx = context;
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = LayoutInflater.from(mCtx);  // <--- "The method getLayoutInflater() is undefined for the type myDynAdap"
            row = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView tv1 = (TextView) row.findViewById(R.id.itemName);
        tv1.setText(list.get(position).toString());
        // tv1.setBackgroundColor (Color.TRANSPARENT);

        // change background of 0th list element only
        if (position == 0)
            tv1.setBackgroundColor(Color.TRANSPARENT);

        return row;

    }

    @Override
    public Filter getFilter() {
        if (ListFilterAtt == null)
            ListFilterAtt = new ListViewFilterAtt((ArrayAdapter<java.lang.String>) this, (List<java.lang.String>) list);
        return ListFilterAtt;
        //return null;
    }
}




