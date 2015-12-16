package com.example.tomasz.restaurantreservation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Restaurant_Display extends AppCompatActivity {
    String resName;
    Number resSeats;
    TextView _name;
    TextView _seats;
    String tableType;
    String lat;
    String lon;
    String[] _avaibleSeatsType = new String[6];
    private Button btnShowMap;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__display);
        btnShowMap = (Button) findViewById(R.id.ShowMap);
        btnShowMap.setOnClickListener(showmaplsnr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        _name =(TextView) findViewById(R.id.Restaurant_Name);
        _seats = (TextView) findViewById(R.id.Price);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String restaurant_id = extras.getString("RESTAURANT_ID");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("RestaurantInfo");
            query.whereEqualTo("objectId", restaurant_id);
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    resName = list.get(0).getString("Name");
                    resSeats = list.get(0).getNumber("seatsAmount");
                    tableType = list.get(0).getString("tableType");
                    lat = list.get(0).getString("Lat");
                    lon = list.get(0).getString("Lon");
                    Log.e("TableType: ", "" + list.get(0).getRelation("tableType"));
                    _name.setText(resName);
                    _seats.setText(resSeats.toString());

                }

            });

/*
            ParseQuery<ParseObject> tabletype = ParseQuery.getQuery("RestaurantInfo");
            tabletype.whereEqualTo("tableType",tableType);

            tabletype.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    Log.e("AAAA: ",list.get(0).getNumber("for1").toString());
                    _avaibleSeatsType[0]= list.get(0).getNumber("for1").toString();
                    _avaibleSeatsType[1]= list.get(0).getNumber("for2").toString();
                    _avaibleSeatsType[2]= list.get(0).getNumber("for3").toString();
                    _avaibleSeatsType[3]= list.get(0).getNumber("for4").toString();
                    _avaibleSeatsType[4]= list.get(0).getNumber("for5").toString();
                    _avaibleSeatsType[5]= list.get(0).getNumber("for6").toString();



                }
            });
*/



        }


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){


                        AlertDialog.Builder builder = new AlertDialog.Builder(Restaurant_Display.this);
                        builder.setTitle("Wybierz typ stoliku")
                                .setItems(_avaibleSeatsType, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                    }
                                });
                       builder.create();

            }


            }

            );


        }

    View.OnClickListener showmaplsnr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Restaurant_Display.this, MapsActivity.class);
            intent.putExtra("LAT",lat);
            intent.putExtra("LON",lon);
            startActivity(intent);
        }
    };

}
