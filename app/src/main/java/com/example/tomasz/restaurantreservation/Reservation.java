package com.example.tomasz.restaurantreservation;

/**
 * Created by Tomasz on 17.12.2015.
 */
public class Reservation {
    String RestaurantName;
    String numberOfPeople;
    String Time;
    String Date;


    public Reservation(String RestaurantName, String numberOfPeople, String Time, String Date){
        this.RestaurantName = RestaurantName;
        this.numberOfPeople = numberOfPeople;
        this.Time = Time;
        this.Date = Date;

    }

}
