package com.example.benaya.application2.controller;

import com.example.benaya.application2.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29/01/2017.
 */


public class MyAdapter<String> extends ArrayAdapter<String> {


    Context mCtx;
    List<String> list;
   // ListViewFilter ListFilter;

    ListViewFilter ListFilter;

    public void updateList(List<String> l) {
        list = l;
    }

    public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
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
        if (ListFilter == null)
            ListFilter = new ListViewFilter((ArrayAdapter<java.lang.String>) this, (List<java.lang.String>) list);
        return ListFilter;
        //return null;
    }
}



