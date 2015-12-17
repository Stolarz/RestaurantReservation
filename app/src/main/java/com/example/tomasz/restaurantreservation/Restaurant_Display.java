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
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.List;

public class Restaurant_Display extends AppCompatActivity {
    private String resName;
    private String address;
    private Number resSeats;
    private TextView _name;
    private TextView _seats;
    private String lat;
    private String lon;
    private Number maxReservation;
    private ParseRelation tableSize;
    private String restaurant_id;
    private Button btnShowMap;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant__display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnShowMap = (Button) findViewById(R.id.ShowMap);
        btnShowMap.setOnClickListener(showmaplsnr);
        _name =(TextView) findViewById(R.id.Restaurant_Name);
        _seats = (TextView) findViewById(R.id.Price);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            restaurant_id  = extras.getString("RESTAURANT_ID");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("RestaurantInfo");
            query.whereEqualTo("objectId", restaurant_id);query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    resName = list.get(0).getString("Name");
                    resSeats = list.get(0).getNumber("seatsAmount").intValue() - list.get(0).getNumber("seatsTaken").intValue();
                    address = list.get(0).getString("adress");
                    maxReservation = list.get(0).getNumber("maxPersonPerReservation");
                    Log.e("XXXX ",""+maxReservation);
                    lat = list.get(0).getString("Lat");
                    lon = list.get(0).getString("Lon");

                    _name.setText(resName);
                    _seats.setText(address);

                }
            });
        }


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View view){


                    Intent intent = new Intent(Restaurant_Display.this, MapsActivity.class);
                    intent.putExtra("LAT",lat);
                    intent.putExtra("LON",lon);
                    startActivity(intent);
            }


            }

            );


        }

    View.OnClickListener showmaplsnr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Restaurant_Display.this, MakeReservationActivity.class);
            intent.putExtra("RESTAURANT_NAME", resName);
            intent.putExtra("MAX", maxReservation.toString());
            startActivity(intent);
        }
    };


}
