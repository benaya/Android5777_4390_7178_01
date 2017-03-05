package com.example.benaya.application2.model.entities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.datasource.listDsManager;

public class ChildAtt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_att);

        listDsManager db = new listDsManager();
        String att_name = getIntent().getStringExtra("ATTNAME");
        Cursor att = db.getAllAttByD(att_name);

        att.moveToFirst();

        final TextView idact = (TextView) findViewById(R.id.tvidact);
        final TextView typ = (TextView) findViewById(R.id.tvtype);
        final TextView country = (TextView) findViewById(R.id.tvcountry);
        final TextView actstart = (TextView) findViewById(R.id.tvstartact);
        final TextView actend = (TextView) findViewById(R.id.tvendact);
        final TextView price = (TextView) findViewById(R.id.tvcost);
        final TextView description = (TextView) findViewById(R.id.tvdescript);

        idact.setText(att.getString(0));
        typ.setText(att.getString(1));
        country.setText(att.getString(2));
        actstart.setText(att.getString(3));
        actend.setText(att.getString(4));
        price.setText(att.getString(5));
        description.setText(att.getString(6));
    }
}
/*
int idActivity;
Activity_type types;
String country;
String activityStart;
String activityEnd;
int price;
String description;
 */