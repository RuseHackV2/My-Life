package com.example.usern.mylife;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali on 7.11.2015 г..
 */
public class CountAdapter extends BaseAdapter {
    private Context mContext;
    private List<CountData> mItems;
    private static String TAG = "Adapter";
    public CountAdapter(Context context, ArrayList<CountData> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();

    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater
                    .inflate(R.layout.result_count, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.lcol = (TextView) convertView
                    .findViewById(R.id.textViewFragmentResult1);
            viewHolder.rcol = (TextView) convertView
                    .findViewById(R.id.textView2);
            convertView.setTag(viewHolder);
         //   Log.println(Log.DEBUG, TAG, "count={" + viewHolder.lcol + "}");
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();

        }

        // update the item view
        CountData item = mItems.get(position);
        if (position != 0) {
            Log.println(Log.DEBUG, TAG, "count={" + item.data1 + "}");
        }
        viewHolder.lcol.setText(item.data1);
        viewHolder.rcol.setText(item.data2);

        return convertView;
    }

    class ViewHolder {
        TextView lcol;
        TextView rcol;
    }

}

