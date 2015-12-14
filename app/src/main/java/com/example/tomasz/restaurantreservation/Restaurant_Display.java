package com.example.tomasz.restaurantreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        _name =(TextView) findViewById(R.id.Restaurant_Name);
        _seats = (TextView) findViewById(R.id.Price);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String restaurant_id = extras.getString("RESTAURANT_ID");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("RestaurantInfo");
            query.whereEqualTo("objectId",restaurant_id);
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> list, ParseException e) {
                 resName = list.get(0).getString("Name");
                 resSeats = list.get(0).getNumber("seatsAmount");
                 _name.setText(resName);
                    _seats.setText(resSeats.toString());
                }

            });
        }


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


            }

            );

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("YES",new DialogInterface.OnClickListener()

            {

                public void onClick (DialogInterface dialog,int which){
                // Do nothing but close the dialog

                dialog.dismiss();
            }

            }

            );

            builder.setNegativeButton("NO",new DialogInterface.OnClickListener()

            {

                @Override
                public void onClick (DialogInterface dialog,int which){
                // Do nothing
                dialog.dismiss();
            }
            }

            );

            final AlertDialog alert = builder.create();


            Button button = (Button) findViewById(R.id.ReserveBtn);
            button.setOnClickListener(new

            OnClickListener() {
                public void onClick (View v)
                {
                    alert.show();
                }
            }

            );

        }
    }
