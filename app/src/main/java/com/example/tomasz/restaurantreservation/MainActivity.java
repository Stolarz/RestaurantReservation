package com.example.tomasz.restaurantreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Restaurant> arrayOfRestaurants = new ArrayList<Restaurant>();
        final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, arrayOfRestaurants);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("RestaurantInfo");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    Log.e("s: ",""+list.size());
                    for (int index = 0; index < list.size(); index++) {
                        Restaurant newRestaurant = new Restaurant(list.get(index).getString("Name"),list.get(index).getNumber("seatsAmount"),list.get(index).getNumber("telephoneNumber"),list.get(index).getObjectId());

                        adapter.add(newRestaurant);
                        //Log.e(": ", parseResult[index].toString());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("Parse query: ", e.getMessage());
                }
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
       listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           Restaurant res = (Restaurant) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), Restaurant_Display.class);
                intent.putExtra("RESTAURANT_ID", res.id);
                startActivity(intent);
            }
        });
    }


}
