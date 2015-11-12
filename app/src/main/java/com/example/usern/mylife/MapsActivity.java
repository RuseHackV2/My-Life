package com.example.usern.mylife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    boolean markerClicked;
    private static String TAG = "MyDebug";
    ImageView image;
    Marker marker, m, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        m = mMap.addMarker(new MarkerOptions().position(new LatLng(43.8460, 25.9602)).title("Ruse").snippet("RuseHack"));
        n = mMap.addMarker(new MarkerOptions().position(new LatLng(44.0380, 26.6182)).title("Tutrakan").snippet("My Home"));
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point).draggable(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(point));
        marker = mMap.addMarker(markerOptions);
        markerClicked = false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showMenuDialog();
        return true;
    }

    private void showMenuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] choiceList = {"View", "Add", "Cansel"};

        int selected = -1;

        builder.setTitle("Select Option");
        builder.setSingleChoiceItems(choiceList, selected,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (choiceList[which] == choiceList[0]) {
                            showViewDialog();
                        }
                        if (choiceList[which] == choiceList[1]) {
                            showAddDialog();
                        }
                        if (choiceList[which] == choiceList[2]) {
                            dialog.cancel();
                        }

                        dialog.dismiss();// Dismiss this dialog, removing it
                        // from the screen.
                    }

                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, LifeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] choiceList = {"Add Image", "Add Post", "Delete Marker"};

        int selected = -1;

        builder.setTitle("Select Option");
        builder.setSingleChoiceItems(choiceList, selected,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (choiceList[which] == choiceList[0]) {
                            SelectImageDialog();
                        }
                        if (choiceList[which] == choiceList[1]) {
                            showMyDialogPost();
                        }
                        if (choiceList[which] == choiceList[2]) {
                            marker.remove();
                        }

                        dialog.dismiss();// Dismiss this dialog, removing it
                        // from the screen.
                    }

                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.image, container, false);
    }


    private void showViewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] choiceList = {"View Image", "View Post", "Delete Marker"};

        int selected = -1;

        builder.setTitle("Select Option");
        builder.setSingleChoiceItems(choiceList, selected,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (choiceList[which] == choiceList[0]) {
                            // if (m.equals(new MarkerOptions().position(new LatLng(43.8460, 25.9602)).title("Ruse").snippet("RuseHack")))
                            {
                                Intent in = new Intent(MapsActivity.this, ImageActivity.class);
                                startActivity(in);
                            }

                        }
                        //SelectImageDialog();
                        //  Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //   startActivityForResult(intent, 2);


                        if (choiceList[which] == choiceList[1])

                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this)
                                    .create();
                            alertDialog.setTitle("RuseHack");
                            alertDialog
                                    .setMessage(" Дойдох, видях, победих! ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        if (choiceList[which] == choiceList[2]) {
                            marker.remove();
                        }

                        dialog.dismiss();// Dismiss this dialog, removing it
                        // from the screen.
                    }

                });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private void showMyDialogPost() {

        View messageView = LayoutInflater.from(MapsActivity.this).
                inflate(R.layout.info_window_layout, null);
        // Create alert dialog builder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set message_item.xml to AlertDialog builder
        alertDialogBuilder.setView(messageView);

        // Create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // Configure dialog button (OK)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private void SelectImageDialog() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    image.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                // Log.w("path of image from gallery......******************.........", picturePath + "");
                image.setImageBitmap(thumbnail);
            }
        }
    }
}