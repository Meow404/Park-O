package com.example.parko.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.parko.CarPark.CarPark;
import com.example.parko.CarPark.CarParkManager;
import com.example.parko.Extra.Location.Location;
import com.example.parko.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import static java.lang.System.exit;

public class ListActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    CarParkManager cpm ;
    LatLng myPosition;
    ProgressBar progressBar1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_list);

        cpm = new CarParkManager(this);

        progressBar1 = (ProgressBar)findViewById(R.id.progressBar2) ;
        progressBar1.setVisibility(View.VISIBLE);


        Location location = new Location(1.346272, 103.698873);
        myPosition = new LatLng(location.getXCoordinate(), location.getYCoordinate());

        mRecyclerView = (RecyclerView) findViewById(R.id.RView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.account:
                        startActivity(new Intent(ListActivity.this, UserActivity.class));
                        return true;
                    case R.id.carParkList:
                        startActivity(new Intent(ListActivity.this, ListActivity.class));
                        return true;
                    case R.id.Map:
                        startActivity(new Intent(ListActivity.this, MapsActivity.class));
                        return true;
                    default: return true;
                }
            }
        });

        CarTaskRunner carTaskRunner = new CarTaskRunner();
        carTaskRunner.execute(myPosition);

    }


    private class CarTaskRunner extends AsyncTask<LatLng, Void, ArrayList<CarPark>> {

        @Override
        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<CarPark> doInBackground(LatLng... params) {
            ArrayList<CarPark> carParks = new ArrayList<>();
            LatLng position = params[0];
            try {
                carParks = cpm.returnUpdatedCarParkList(new Location(position.latitude, position.longitude), 1.5);
                Log.d("1002",String.valueOf(carParks.size()));
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("101", e.getMessage());
                exit(0);
            }
            return carParks;
        }


        @Override
        protected void onPostExecute(ArrayList<CarPark> carParks) {
            progressBar1.setVisibility(View.INVISIBLE);
            Log.d("1001",String.valueOf(carParks.size()));
            mAdapter=new ListAdapter(carParks);
            mRecyclerView.setAdapter(mAdapter);

        }

    }
}
