package com.example.benaya.application2.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.datasource.AsyncFileTask;
import com.example.benaya.application2.model.datasource.listDsManager;
import com.example.benaya.application2.model.entities.Child;
import com.example.benaya.application2.model.entities.ChildAtt;

import java.util.ArrayList;

/**
 * Created by User on 29/01/2017.
 */

public class AttractionFragment extends Fragment {

    final listDsManager l = new listDsManager();
    ArrayList<String> att;
    ArrayAdapter<String> adapter;
    final String URI_BUS = "content://com.example.android5777_4390_7178_01/business";
    final String URI_ATT = "content://com.example.android5777_4390_7178_01/attraction";
    AsyncFileTask dft = new AsyncFileTask();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final ListView lv = (ListView) rootView.findViewById(R.id.list_view1);
        final SearchView search_view = (SearchView) rootView.findViewById(R.id.search_view2);

        att = l.getAttrctionList();
        adapter = new MyAdapterAtt<String>(getActivity().getApplicationContext(), R.id.tvname, att);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Object o = lv.getItemAtPosition(i);
                Log.d("TAG", (String) o);
                Intent intent = new Intent(getActivity(), ChildAtt.class);
                //    Toast.makeText(getActivity(),"id"+l,Toast.LENGTH_SHORT).show();
                intent.putExtra("ATTNAME", (String) o);
                startActivity(intent);
            }
        });

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }
}


//  public AttractionFragment(){}
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_attraction, container, false);

        return rootView;
    }


}*/