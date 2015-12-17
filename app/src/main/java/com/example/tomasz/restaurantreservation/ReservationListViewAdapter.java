package com.example.tomasz.restaurantreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Tomasz on 17.12.2015.
 */
public class ReservationListViewAdapter extends ArrayAdapter<Reservation> {
    public ReservationListViewAdapter(Context context, ArrayList<Reservation> reservations){
        super(context,0,reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Reservation reservation = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_reservation,parent,false);
    }
        TextView resName = (TextView) convertView.findViewById(R.id.resName);
        TextView resDate = (TextView) convertView.findViewById(R.id.resDate);
        TextView resAmount = (TextView) convertView.findViewById(R.id.resAmount);
        TextView resTime = (TextView) convertView.findViewById(R.id.resTime);

        resAmount.setText(reservation.numberOfPeople);
        resDate.setText(reservation.Date);
        resName.setText(reservation.RestaurantName);
        resTime.setText(reservation.Time);

        return convertView;

    }
}
