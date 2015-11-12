package com.example.usern.mylife;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by Natali on 6.11.2015 г..
 */
public class LifeActivity extends Activity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_layout);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);


    }

    public void onClick(View v) {
        if (v.equals(btn1)) {
            Intent in = new Intent(LifeActivity.this, MapsActivity.class);
            startActivity(in);
        }
        if (v.equals(btn2)) {
            Intent i = new Intent(LifeActivity.this, DreamActivity.class);
            startActivity(i);
        }
    }
}
