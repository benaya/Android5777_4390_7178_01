package com.example.benaya.application2.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benaya.application2.R;
import com.example.benaya.application2.model.backend.IDSManager;
import com.example.benaya.application2.model.backend.ManagerFactory;
import com.example.benaya.application2.model.datasource.TravelContent;
import com.example.benaya.application2.model.datasource.listDsManager;

import static android.Manifest.permission.READ_CONTACTS;

import java.util.ArrayList;
import java.util.List;

public class Screen_open extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private updateActivitiesTask aUpdateBusiness = null;
   // updateActivitiesTask aUpdateAttraction = null;
    listDsManager db;
    private View LoadingView;
    private static final int REQUEST_READ_CONTACTS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_open);
        db = new listDsManager();
        LoadingView = findViewById(R.id.progress);
        updateLoaging();
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);



            LoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
            LoadingView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    LoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            LoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void updateLoaging(){
        if (aUpdateBusiness != null) {return;}

        aUpdateBusiness = new updateActivitiesTask();
        aUpdateBusiness.execute((Void) null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(Screen_open.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public class updateActivitiesTask extends AsyncTask<Void, Void, Boolean> {
       updateActivitiesTask(){}
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean flag = false;

            try{
                Log.d("TAG", "getting Active from content provider");
                Cursor cBus = getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
                Log.d("TAG", "update Active cursors to list");
              db.updateBusInListDB(cBus);

                Log.d("TAG", "getting Active from content provider");
               Cursor cActive = getContentResolver().query(TravelContent.Attraction.ATTRACTION_URI, null, null, null, null);
                Log.d("TAG", "update Active cursors to list");
              db.updateAttInListDB(cActive);

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
            if (success)
            {
                Log.d("TAG", "onPostExecute - success, open main activity");

                Intent myIntent = new Intent(Screen_open.this,MainActivity.class);

                startActivity(myIntent);
            }
            else {
                Toast.makeText(Screen_open.this, "connection to server failed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        @Override
        protected void onCancelled() {
            aUpdateBusiness = null;
            showProgress(false);
        }
    }
}

