package com.example.usern.mylife;

import android.util.Log;

/**
 * Created by Natali on 7.11.2015 г..
 */
public class CountData {
    public String data1;
    public String data2;
    private static String TAG = "Data";

    public CountData(String data1,String data2) {
        super();
        this.data1 = data1;
        this.data2 = data2;
     //   Log.println(Log.DEBUG, TAG, "data={" + data1 + "}");
    }

}
