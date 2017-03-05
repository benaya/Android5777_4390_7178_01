package com.example.benaya.application2.model.datasource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;

/**
 * Created by User on 29/01/2017.
 */

public class CustomContentProvider extends ContentProvider {
    IDSManager manager = ManagerFactory.getManager();
    final String TAG = "Travels";

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete " + uri.toString());

        return 0;
    }

    @Override
    public String getType(Uri uri) {

        Log.d(TAG, "getType " + uri.toString());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Log.d("TAG", "insert " + uri.toString());

        String listName = uri.getLastPathSegment();
        //long id = -1;
        switch (listName) {
            //  case "manager":
            //  manager.addManager(values);
            //  return ContentUris.withAppendedId(uri, id);

            case "business":
                manager.addBusiness();
                Log.d("TAG", "content bus good");
                //return ContentUris.withAppendedId(uri, id);

            case "attraction":
                manager.addAttraction();
                Log.d("TAG", "content att good");
                //    return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.d(TAG, "query " + uri.toString());

        String listName = uri.getLastPathSegment();

        switch (listName) {
            //  case "manager":
            //      return manager.getManager();//

            case "business":
                return manager.getBusiness();//

            case "attraction":
                return manager.getAttraction();//

        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update " + uri.toString());

        return 0;
    }
}

