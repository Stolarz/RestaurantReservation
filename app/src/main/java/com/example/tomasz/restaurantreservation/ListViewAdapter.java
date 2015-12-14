package com.example.tomasz.restaurantreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tsolik on 2015-12-14.
 */
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Restaurant> restaurants;

    public ListViewAdapter(Context context, ArrayList<Restaurant> restaurants) {
//        super(context,0, restaurants);
        this.restaurants = restaurants;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    Restaurant restaurant = getItem(position);
    if(convertView ==null){
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_restaurant, parent, false);

    }
        TextView resName = (TextView) convertView.findViewById(R.id.restaurantName);
        TextView resNumber = (TextView) convertView.findViewById(R.id.restaurantTelephone);
        TextView resSeats = (TextView) convertView.findViewById(R.id.restaurantSeatsNumber);

        resName.setText("Nazwa: "+restaurant.Name);
        resNumber.setText("Telefon: "+restaurant.telephoneNumber.toString());
        resSeats.setText("Ilość miejsc: "+restaurant.SeatsNumber.toString());

//s
    return convertView;
    }

    public void add(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
    }
}
