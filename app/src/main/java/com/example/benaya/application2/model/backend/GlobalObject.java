package com.example.benaya.application2.model.backend;

import android.support.v4.app.Fragment;

import com.example.benaya.application2.controller.MainActivity;

/**
 * Created by יונתן on 10/02/2017.
 */

public class GlobalObject {

    public static MainActivity main = null;
 //   public static activity_layout ActiveFrag = null;
 //   public static activity_cur curActiveFrag = null;
  //  public static business_layout BussFrag = null;
  ///  public static business_information infoFrag = null;
    public static Fragment currentFrag = null;
    public static int currentBusinessID;
    public static int savedBusinessID;

    public static MainActivity getmain(){
        if(main == null)
            main = new MainActivity();
        return main;
    }
  /*  public static activity_layout getActiveFragment(){
        if(ActiveFrag == null)
            ActiveFrag = new activity_layout();
        return ActiveFrag;
    }
    public static activity_cur getCurActivity(){
        if(curActiveFrag == null)
            curActiveFrag = new activity_cur();
        return curActiveFrag;
    }

    public static business_layout getBusinessFragment(){
        if(BussFrag == null)
            BussFrag = new business_layout();
        return BussFrag;
    }

    public static business_information getInfoFragment(){
        if(infoFrag == null)
            infoFrag = new business_information();
        return infoFrag;
    }*/

    public static Fragment getCurrentFrag(){
        return currentFrag;
    }
}
