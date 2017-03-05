package com.example.benaya.application2.controller;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.backend.GlobalObject;
import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;
import com.example.benaya.application2.model.backend.MyBroadcastReceiver;
import com.example.benaya.application2.model.datasource.AsyncFileTask;
import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.datasource.listDsManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    updateBusiness aUpdateBusiness;
    updateAttraction aUpdateAttraction;
    IDSManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalObject.main = this;
        registerReceiver(new MyBroadcastReceiver(), new IntentFilter("HAGER.VAKNIN.ACTION_SERVICE.BUS"));
        registerReceiver(new MyBroadcastReceiver(), new IntentFilter("HAGER.VAKNIN.ACTION_SERVICE.ACT"));
        db = ManagerFactory.getManager();
        final String uribus = "content://com.example.android5777_4390_7178_01/business";
        final String uriact = "content://com.example.android5777_4390_7178_01/attraction";

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.d("TAG", "main activity1");
 //    setSupportActionBar(toolbar);
        Log.d("TAG", "main activity2");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * calling AsyncTask to get refreshed data
     */
    public void updateDatabase() {
        updateBusinessList();
       updateActivitiesList();
    }

    /**
     * execute update Business Task
     */
    public void updateBusinessList() {
        try {
            aUpdateBusiness = new updateBusiness();
            aUpdateBusiness.execute((Void) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * execute update Activities Task
     */
    public void updateActivitiesList() {
        try {
            aUpdateAttraction = new updateAttraction();
            aUpdateAttraction.execute((Void) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_business) {
            fragmentManager.beginTransaction().
                    replace(R.id.content_main, new BusinesFragment()).commit();
        } else if (id == R.id.nav_Attraction) {
            fragmentManager.beginTransaction().
                    replace(R.id.content_main, new AttractionFragment()).commit();
        } else if (id == R.id.nav_exit) {
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

 //  listDsManager db = new listDsManager();
    public class updateAttraction extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean flag = false;
            try{
                Log.d("TAG", "getting Active from content provider");
                Cursor cAtt = getContentResolver().query(TravelContent.Attraction.ATTRACTION_URI, null, null, null, null);
                Log.d("TAG", "update Active cursors to list");
                db.updateAttInListDB(cAtt);

                Log.d("TAG", "lists created successfully!");
                flag = true;
            }
            catch (Exception e)
            {
                Log.d("TAG", "failed due to:  " + e);
            }
           return flag;
        }
        @Override
        protected void onPostExecute( Boolean success) {
            aUpdateAttraction = null;
        }
        @Override
        protected void onCancelled() {
            aUpdateAttraction = null;
        }
    }

    public class updateBusiness extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean flag = false;

            try{
                Log.d("TAG", "getting Active from content provider");
                Cursor cBus = getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
                Log.d("TAG", "update Active cursors to list");
                db.updateBusInListDB(cBus);

                Log.d("TAG", "lists created successfully!");
                flag = true;
            }
            catch (Exception e)
            {
                Log.d("TAG", "failed due to:  " + e);
            }
            return flag;
        }

        @Override
        protected void onPostExecute( Boolean success) {
            aUpdateBusiness = null;
        }

        @Override
        protected void onCancelled() {
            aUpdateBusiness = null;
        }
    }
}


/*

    final String uribus = "HAGER.VAKNIN.ACTION_SERVICE.BUS";
    final String uriact = "HAGER.VAKNIN.ACTION_SERVICE.ACT";
    final AsyncFileTask dft = new AsyncFileTask();


    */
