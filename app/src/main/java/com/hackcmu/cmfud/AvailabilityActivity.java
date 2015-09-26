package com.hackcmu.cmfud;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class AvailabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        getSupportActionBar().hide();

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Rashid Auditorium", Color.rgb(78, 0, 0), 830, 2400));
        restaurants.add(new Restaurant("The Underground", Color.rgb(240, 106, 79), 830, 2400));
        restaurants.add(new Restaurant("Schatz Dining Room", Color.rgb(237, 28, 36), 1030, 1430));
        restaurants.add(new Restaurant("Entropy", Color.rgb(237, 20, 91), 1000, 2700));
        restaurants.add(new Restaurant("The Exchange", Color.rgb(242, 101, 34), 1000, 1430));

        ListView listView = (ListView) findViewById(R.id.availability_listview);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.restaurant_item, restaurants);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_availability, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
