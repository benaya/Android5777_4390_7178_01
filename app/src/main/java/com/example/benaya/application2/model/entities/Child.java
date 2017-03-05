package com.example.benaya.application2.model.entities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.backend.GlobalObject;
import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;
import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.datasource.listDsManager;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Child extends AppCompatActivity {
    Business current;
    ArrayList<Business> array;
    IDSManager db;
    String p;
    String e;
    String map;
    String w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        listDsManager db = new listDsManager();
        String bus_name = getIntent().getStringExtra("BUSNAME");
        Cursor bus = db.getBussinesByName(bus_name);

        bus.moveToFirst();

        final TextView name = (TextView) findViewById(R.id.tvname);
        final TextView des = (TextView) findViewById(R.id.tvdes);
        final Button phone = (Button) findViewById(R.id.btphone);
        final Button email = (Button) findViewById(R.id.btmail);
        final Button web = (Button) findViewById(R.id.btwebsite);
        final TextView adsress = (TextView) findViewById(R.id.btaddress);

        name.setText(bus.getString(1));
        des.setText(bus.getString(0));
        phone.setText(bus.getString(5));
        email.setText(bus.getString(6));
        web.setText(bus.getString(7));
        adsress.setText(bus.getString(2) + " " + bus.getString(4) + " " + bus.getString(3));

        p = phone.getText().toString();
        findViewById(R.id.btphone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ p));
                startActivity(Intent.createChooser(intent, "Select an app"));
            }
        });

        e =email.getText().toString();
        findViewById(R.id.btmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmail =new Intent(Intent.ACTION_SENDTO);
                sendEmail.setData(Uri.parse("mailto:"+ e));
                startActivity(Intent.createChooser(sendEmail, "Select an app"));
            }
        });

        map =adsress.getText().toString();
        findViewById(R.id.btaddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomap =new Intent(Intent.ACTION_VIEW);
                gotomap.setData(Uri.parse("geo:0,0?q"+ e));
                startActivity(Intent.createChooser(gotomap, "Select an app"));
            }
        });
        w= web.getText().toString();
        map =adsress.getText().toString();
        findViewById(R.id.btwebsite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoweb =new Intent(Intent.ACTION_VIEW);
                gotoweb.setData(Uri.parse("http://"+ w));
                startActivity(Intent.createChooser(gotoweb, "Select an app"));
            }
        });
    }






}
    /*
    public void onButton2Pressed(Uri uri) //mail button
    {

            Intent sendEmail =new Intent(Intent.ACTION_SENDTO);
            sendEmail.setData(Uri.parse("mailto:"+current.getEmail()));
            startActivity(sendEmail);
        }
    }


 bphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tocall = "tel:" + current.getPhone();
                Intent sendToCaller = new Intent(Intent.ACTION_DIAL);
                sendToCaller.setData((Uri.parse(tocall)));
                startActivity(sendToCaller);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent sendToCaller = new Intent(Intent.ACTION_DIAL,
                                                 Uri.fromParts("tel", String.valueOf(current.getPhone()), null));
                                         startActivity(sendToCaller);
                                     }
                                 }
        );
*/




