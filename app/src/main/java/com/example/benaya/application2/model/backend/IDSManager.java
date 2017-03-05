package com.example.benaya.application2.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.entities.Attractions;
import com.example.benaya.application2.model.entities.Business;

import java.util.ArrayList;

/**
 * Created by User on 29/01/2017.
 */

public interface IDSManager {


      void addManager(ContentValues contant_manager);
    void addBusiness();
    void addAttraction();

      Cursor getManager();
    Cursor getBusiness();
    Cursor getAttraction();
    void updateAttInListDB(Cursor b);
    void updateBusInListDB(Cursor b);

    void updateBusinessList(Cursor newBusinesscurs);

    public ArrayList<Business> getBusinessByArray();
    public ArrayList<Attractions> getAttractionByArray();

    boolean checkChanges();

}
