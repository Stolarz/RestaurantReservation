package com.example.tomasz.restaurantreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView _lv;
    ArrayAdapter<String> adapter;
    String[] parseResult = {""};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("RestaurantInfo");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    parseResult = new String[list.size()];
                    for (int index = 0; index < list.size(); index++) {
                        parseResult[index] = list.get(index).getString("Name");
                        adapter.add(parseResult[index]);
                        Log.e(": ", parseResult[index].toString());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("Parse query: ", e.getMessage());
                }
            }
        });


        _lv = (ListView)findViewById(R.id.list);
        _lv.setAdapter(adapter);
    }
}
