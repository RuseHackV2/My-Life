package com.example.usern.mylife;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Natali on 7.11.2015 г..
 */
public class Fragment1 extends Fragment {
    private ListView listView;
    private String mMessage1;
    private String mMessage2;
    private static String TAG = "MyDebug";
    private CountAdapter mAdapter;
    static public ArrayList<CountData> mItems = new ArrayList<CountData>();

 public static Fragment1 newInstance(String message1,String message2) {

     Fragment1 countd = new Fragment1();
     // create new Bundle
     Bundle bundle = new Bundle();
     // Save the given count in the Bundle
     bundle.putString("message1", message1);
     bundle.putString("message2", message2);

     // set the arguments
     countd.setArguments(bundle);

     return countd;

 }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    //    listView = (ListView) getActivity().findViewById(
    //            R.id.listView2);
        Bundle bundle = this.getArguments();
        Log.println(Log.DEBUG, TAG, "count={" + bundle + "}");
        if (bundle != null) {
            try {

                mMessage1 = bundle.getString("message1");
                mMessage2 = bundle.getString("message2");

                mItems.add(new CountData(mMessage1, mMessage2));

              //  Log.println(Log.DEBUG, TAG, "p={" + mMessage1 + "}");
                mAdapter = new CountAdapter(getActivity(), mItems);
                listView.setAdapter(mAdapter);

            } catch (Exception e) {
                Log.println(Log.DEBUG, TAG, "count={" + mItems + "}");

                e.printStackTrace();
            }
        }

        }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment1, container, false);
    }
}
