package com.example.benaya.application2.controller;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.example.benaya.application2.model.datasource.listDsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 02/02/2017.
 */

public class ListViewFilter  extends Filter {
    private List<String> list;
    public ArrayAdapter<String>  myAdapter;
    final listDsManager l = new listDsManager();


    public ListViewFilter(ArrayAdapter<String>  myAdapter,List<String> objects) {
        list = objects;
        this.myAdapter=myAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        list.clear();
        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = l.getBussinesList();
            results.count = l.getBussinesList().size();
        }
        else {
            // We perform filtering operation
            List<String> tempList = new ArrayList<String>();

            for (String p : l.getBussinesList()) {
                if (p.toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    tempList.add(p);
            }
            results.values = tempList;
            results.count = tempList.size();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.count == 0)
            myAdapter.notifyDataSetInvalidated();
        else {

            //   list = (List<String>) results.values;
            list.removeAll(list);
            for(String item : (List<String>) results.values)
                list.add(item);
            myAdapter.notifyDataSetChanged();
        }
    }
}
