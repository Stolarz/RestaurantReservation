package com.example.tomasz.restaurantreservation;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Tomasz on 11.12.2015.
 */
public class Start extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(this, "QitqAuSJeUlKMOkNxnzmUplsDYJYlJm9KgLNFmeP", "fM9nw3tRoYMUiryrJIfimc7RtcJXQrgWgMN74Ca6");
    }
}
