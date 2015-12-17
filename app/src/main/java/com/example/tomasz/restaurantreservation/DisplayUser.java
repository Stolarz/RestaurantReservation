package com.example.tomasz.restaurantreservation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DisplayUser extends AppCompatActivity {
private String username;
    private String email;
    private Button editbutton;
    private TextView edtPassword;
    private TextView edtUsername;
    private Button logoutButton;
    ParseUser currentUser = ParseUser.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivity();

    }
    View.OnClickListener editlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onCreateDialog().show();

        }
    };

    View.OnClickListener logout = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            ParseUser.logOut();
            Intent intent = new Intent(DisplayUser.this, LoginActivity.class);
            startActivity(intent);
        }
    };


    public Dialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.editlayout, null))
                // Add action buttons
                .setPositiveButton("Aktualizuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(R.layout.editlayout);

                    EditText pass = (EditText) findViewById(R.id.PASSWORD);
                        Log.e("ASDASD: ",""+pass.getText().toString());
                        currentUser.setPassword(pass.getText().toString());
                        currentUser.saveInBackground();
                        loadActivity();
                    }
                })
                .setNegativeButton("Anuluj", null);
        return builder.create();
    }

    private void loadActivity() {
        setContentView(R.layout.activity_display_user);
        editbutton = (Button) findViewById(R.id.EditButton);
        editbutton.setOnClickListener(editlistener);
        logoutButton = (Button) findViewById(R.id.LogoutButton);
        logoutButton.setOnClickListener(logout);


        username = currentUser.getUsername();
        email = currentUser.getEmail();

        edtPassword = (TextView) findViewById(R.id.EmailTextView);
        edtUsername = (TextView) findViewById(R.id.UsernameTextView);
        edtPassword.setText(email);
        edtUsername.setText(username);

        ArrayList<Reservation> arrayOfReservations = new ArrayList<Reservation>();

        final ReservationListViewAdapter adapter = new ReservationListViewAdapter(DisplayUser.this,arrayOfReservations);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("userReservations");
        query.whereEqualTo("userId",currentUser.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        Reservation newReservation = new Reservation(list.get(i).getString("RestaurantName"), list.get(i).getNumber("numberOfPeople").toString(), list.get(i).getString("Time"), list.get(i).getString("Date"));
                        adapter.add(newReservation);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("Parse query: ", e.getMessage());
                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.reservationListView);
        listView.setAdapter(adapter);

    }
}
