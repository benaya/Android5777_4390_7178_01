package com.example.benaya.application2.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.benaya.application2.R;
import com.example.benaya.application2.controller.dummy.DummyContent;
import com.example.benaya.application2.controller.dummy.DummyContent.DummyItem;
import com.example.benaya.application2.model.datasource.listDsManager;
import com.example.benaya.application2.model.entities.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BusinesFragment extends Fragment {

    public BusinesFragment(){}

    final listDsManager l = new listDsManager();
    //Cursor busines = l.getBusiness();
    String name;
    ArrayList<String> bus;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final ListView lv = (ListView) rootView.findViewById(R.id.list_view1);
        final SearchView search_view = (SearchView) rootView.findViewById(R.id.search_view2);
       // lv = (ListView) this.getView().findViewById(R.id.list_view1);
      //  search_view = (SearchView) this.getView().findViewById(R.id.search_view2);
      //  l.addBusiness();
        bus = l.getBussinesList();


        adapter = new MyAdapter<String>(getActivity().getApplicationContext(), R.id.tvname, bus);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = lv.getItemAtPosition(i);
                Log.d("TAG", (String) o);
                Intent intent = new Intent(getActivity(), Child.class);
            //    Toast.makeText(getActivity(),"id"+l,Toast.LENGTH_SHORT).show();
                intent.putExtra("BUSNAME", (String) o);
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