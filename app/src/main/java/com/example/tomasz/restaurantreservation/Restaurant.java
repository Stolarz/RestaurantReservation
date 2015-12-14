package com.example.tomasz.restaurantreservation;

/**
 * Created by tsolik on 2015-12-14.
 */
public class Restaurant {
    String Name;
    Number SeatsNumber;
    Number telephoneNumber;
    String id;

public Restaurant(String Name, Number SeatsNumber, Number telephoneNumber,String id){
    this.Name = Name;
    this.SeatsNumber = SeatsNumber;
    this.telephoneNumber = telephoneNumber;
    this.id = id;
}

}
