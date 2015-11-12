package com.example.usern.mylife;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Natali on 8.11.2015 г..
 */
public class ImageActivity extends Activity {
    private ImageView imageview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        String uri = "@drawable/rusehack1";  // where myresource.png is the file
        // extension removed from the String
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        imageview = (ImageView) findViewById(R.id.imageView);
        Drawable res = getResources().getDrawable(imageResource);
        imageview.setImageDrawable(res);

        //btn2.setRotation(25);
    }
}
