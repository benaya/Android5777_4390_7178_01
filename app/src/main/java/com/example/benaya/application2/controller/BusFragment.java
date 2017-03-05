package com.example.benaya.application2.controller;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.support.v4.app.ListFragment;
import android.widget.Toast;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;
import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.datasource.listDsManager;
import com.example.benaya.application2.model.entities.Child;


public class BusFragment  extends
        ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final IDSManager db;
        db = ManagerFactory.getManager();

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        getContext(),
                        R.layout.list_item,
                        null,
                        new String[]{TravelContent.Business.business_name},
                        new int[]{R.id.itemName}

                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContext().getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
            //    db.updateBusInListDB(cursor);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

     /*   SearchView filterSearchView;
        filterSearchView = (SearchView) getActivity().findViewById(R.id.search_view);
        filterSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }
        });*/
        // EditText inputSearch = (EditText) getView().findViewById(R.id.inputSearch);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(),"id " + id,Toast.LENGTH_LONG).show();
       Intent intent = new Intent(getActivity(), Child.class);
        intent.putExtra("id",(long) id);
        startActivity(intent);
    }


}










/*  public BusFragment() {
    }

    final listDsManager l = new listDsManager();
    Cursor cursor;
    ArrayAdapter<Cursor> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bus2, container, false);


        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        getContext(),
                        R.layout.list_item,
                        null,
                        new String[]{TravelContent.Business.business_name, TravelContent.Business.business_id},
                        new int[]{R.id.itemName, R.id.itemId}

                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContext().getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();
        this.setListAdapter(adapter);

        return rootView;
    }*/
