package com.example.benaya.application2.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.entities.Activity_type;
import com.example.benaya.application2.model.entities.Attractions;
import com.example.benaya.application2.model.entities.Business;
import com.example.benaya.application2.model.entities.Child;

import java.util.ArrayList;

import static com.example.benaya.application2.model.entities.Activity_type.AIRLINES;
import static com.example.benaya.application2.model.entities.Activity_type.ENTERTAINMENT;
import static com.example.benaya.application2.model.entities.Activity_type.EXCURESIONS;
import static com.example.benaya.application2.model.entities.Activity_type.TRAVEL_AGENCY;
import static com.example.benaya.application2.model.entities.Activity_type.VACATION_PACKEGE;
import static java.security.AccessController.getContext;

/**
 * Created by User on 29/01/2017.
 */

public class listDsManager implements IDSManager {


    public static ArrayList<Business> businessList = new ArrayList<Business>();
    public static ArrayList<Attractions> attractionsesList = new ArrayList<Attractions>();

    @Override
    public void addManager(ContentValues contant_manager) {

    }

    @Override
    public void addBusiness() {
        Business b1 = new Business(1234,"david","a","israel","haifa",050343567,"yon@gmail","map.co.il");
        Business b2 = new Business(1678,"yosi","hanamal","israel","yafo",05011117,"yon@gmail","map.co.il");
        businessList.add(b1);
        businessList.add(b2);
    }

    @Override
    public void addAttraction() {}

    @Override
    public Cursor getManager() {
        return null;
    }

    @Override
    public Cursor getBusiness() {
        String[] columns = new String[]
                {
                        TravelContent.Business.business_id,
                        TravelContent.Business.business_name,
                        TravelContent.Business.business_street,
                        TravelContent.Business.business_country,
                        TravelContent.Business.business_city,
                        TravelContent.Business.business_phone,
                        TravelContent.Business.business_email,
                        TravelContent.Business.business_webSite
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Business b : businessList) {
            matrixCursor.addRow(new Object[]
                    {
                            b.getIDbusines(),
                            b.getNameBusines(),
                            b.getAd_street(),
                            b.getAd_country(),
                            b.getAd_city(),
                            b.getPhone(),
                            b.getEmail(),
                            b.getwebSite()
                    });
        }

        return matrixCursor;
    }

    @Override
    public Cursor getAttraction() {
        String[] columns = new String[]
                {
                        TravelContent.Attraction.ID_activity,
                        TravelContent.Attraction.activity_type,
                        TravelContent.Attraction.activity_country,
                        TravelContent.Attraction.activity_TStart,
                        TravelContent.Attraction.activity_TEnd,
                        TravelContent.Attraction.activity_price,
                        TravelContent.Attraction.activity_description
                        //   TravelContent.Attraction.activity_id
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Attractions a : attractionsesList) {
            matrixCursor.addRow(new Object[]
                    {
                            a.getIdActivity(),
                            a.getTypes(),
                            a.getCountry(),
                            a.getActivityEnd(),
                            a.getActivityEnd(),
                            a.getPrice(),
                            a.getDescription()
                            //    a.getIDbusines()
                    });
        }
        return matrixCursor;
    }

    public Cursor getBussinesByID(Long id) {
        MatrixCursor busCursor = new MatrixCursor(new String[]{TravelContent.Business.business_id,
                TravelContent.Business.business_name,
                TravelContent.Business.business_street,
                TravelContent.Business.business_country,
                TravelContent.Business.business_city,
                TravelContent.Business.business_phone,
                TravelContent.Business.business_email,
                TravelContent.Business.business_webSite});
        for(Business b : businessList)
            if(id.equals(b.getIDbusines())) {
                busCursor.addRow(new Object[]{b.getIDbusines(),
                        b.getNameBusines(),
                        b.getAd_street(),
                        b.getAd_country(),
                        b.getAd_city(),
                        b.getPhone(),
                        b.getEmail(),
                        b.getwebSite()});
            }
        return busCursor;
            }

    public ArrayList<String> getBussinesList() {
        ArrayList<String> tmp = new ArrayList<>();
        for(Business b: businessList)
            tmp.add(b.getNameBusines());
        return tmp ;   }

    public ArrayList<String> getAttrctionList() {
        ArrayList<String> tmp = new ArrayList<>();
        for(Attractions a: attractionsesList)
            tmp.add(a.getDescription());
        return tmp ;   }

    public void updateBusInListDB(Cursor b)
    {
        try {
            b.moveToFirst();
            while ((!b.isAfterLast()))//add all busines
            {
                businessList.add(new Business(b.getLong(0),b.getString(1),b.getString(2),b.getString(3),b.getString(4),b.getInt(5),
                        b.getString(6),b.getString(7)));
                b.moveToNext();
            }
            Log.d("DB", "bussines updated");
            b.close();
        }
        catch (Exception e) {
            Log.d("DB", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateActiveList(Cursor newActiveCurs) {
        attractionsesList.clear();
        createActivitiesList(newActiveCurs);
    }

    public void updateAttInListDB(Cursor b)
    {
        try {
            Activity_type type = Activity_type.TRAVEL_AGENCY;
            b.moveToFirst();
            while ((!b.isAfterLast()))//add all busines
            {
                //Toast.makeText(listDsManager.this,"ha:"+ b.getString(1).toString(),Toast.LENGTH_SHORT).show();

                if (b.getString(1).toString().equals("TRAVEL_AGENCY"))
                attractionsesList.add(new Attractions(b.getInt(0),type,b.getString(2),b.getString(3),b.getString(4)
                        ,b.getInt(5),b.getString(6)));
                b.moveToNext();
            }
            Log.d("DB", "Attraction updated");
            b.close();
        }
        catch (Exception e) {
            Log.d("DB", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public boolean checkChanges() {
        return false;
    }

    public void updateBusinessList(Cursor newBusinesscurs) {
        businessList.clear();
        createBusinessList(newBusinesscurs);
    }

    public void createBusinessList(Cursor BusinessMatrix) {
        for (BusinessMatrix.moveToFirst(); !BusinessMatrix.isAfterLast(); BusinessMatrix.moveToNext()) {
            businessList.add(new Business(
                    BusinessMatrix.getLong(BusinessMatrix.getColumnIndex(TravelContent.Business.business_id)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_name)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_street)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_country)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_city)),
                    BusinessMatrix.getInt(BusinessMatrix.getColumnIndex(TravelContent.Business.business_phone)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_email)),
                    BusinessMatrix.getString(BusinessMatrix.getColumnIndex(TravelContent.Business.business_webSite))));
        }
    }

    public void createActivitiesList(Cursor b) {
        for (b.moveToFirst(); !b.isAfterLast(); b.moveToNext()) {
            Activity_type kindOf = Activity_type.TRAVEL_AGENCY;

            if (Activity_type.VACATION_PACKEGE.toString().equals(b.getString(b.getColumnIndex("types")))) {
                kindOf = Activity_type.VACATION_PACKEGE;
            }
           else if (Activity_type.ENTERTAINMENT.toString().equals(b.getString(b.getColumnIndex("types")))) {
                kindOf = Activity_type.ENTERTAINMENT;
            }
            else if (Activity_type.AIRLINES.toString().equals(b.getString(b.getColumnIndex("types")))) {
                kindOf = Activity_type.AIRLINES;
            }
            else if (Activity_type.EXCURESIONS.toString().equals(b.getString(b.getColumnIndex("types")))) {
                kindOf = Activity_type.EXCURESIONS;
            }

            attractionsesList.add(new Attractions(b.getInt(1),kindOf,b.getString(1),b.getString(2),b.getString(3)
                        ,b.getInt(4),b.getString(5)));

        }
    }

    public Cursor getAllAttByD(String d) {
        MatrixCursor attCursor = new MatrixCursor(new String[]{
                TravelContent.Attraction.ID_activity,
                TravelContent.Attraction.activity_type,
                TravelContent.Attraction.activity_country,
                TravelContent.Attraction.activity_TStart,
                TravelContent.Attraction.activity_TEnd,
                TravelContent.Attraction.activity_price,
                TravelContent.Attraction.activity_description});
        for(Attractions a : attractionsesList)
            if(d.equals(a.getDescription())){attCursor.addRow(new Object[]{
                    a.getIdActivity(),
                    a.getTypes(),
                    a.getCountry(),
                    a.getActivityEnd(),
                    a.getActivityEnd(),
                    a.getPrice(),
                    a.getDescription()
            });}
        return attCursor;    }


    public Cursor getBussinesByName(String name) {
        MatrixCursor busCursor = new MatrixCursor(new String[]{ TravelContent.Business.business_id,
                TravelContent.Business.business_name,
                TravelContent.Business.business_street,
                TravelContent.Business.business_country,
                TravelContent.Business.business_city,
                TravelContent.Business.business_phone,
                TravelContent.Business.business_email,
                TravelContent.Business.business_webSite});
        for(Business b : businessList)
            if(name.equals(b.getNameBusines())){busCursor.addRow(new Object[]{
                    b.getIDbusines(),
                    b.getNameBusines(),
                    b.getAd_street(),
                    b.getAd_country(),
                    b.getAd_city(),
                    b.getPhone(),
                    b.getEmail(),
                    b.getwebSite()
            });
            }
        return busCursor;    }

    @Override
    public ArrayList<Business> getBusinessByArray() {
        return businessList;
    }

    @Override
    public ArrayList<Attractions> getAttractionByArray() {
        return attractionsesList;
    }

}
