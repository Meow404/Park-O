package com.example.parko;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parko.CarPark.CarPark;
import com.example.parko.CarPark.CarParkManager;
import com.example.parko.CyclePark.CyclePark;
import com.example.parko.CyclePark.CycleParkManager;
import com.example.parko.Extra.Location.Location;
import com.example.parko.Facilities.FacilitiesManager;
import com.example.parko.Facilities.FacilityTypes.FacilityTypes;
import com.example.parko.TrafficIncidents.IncidentTypes.IncidentTypes;
import com.example.parko.TrafficIncidents.TrafficIncidentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

//import circleimageview.CircleImageView;

import static java.lang.System.exit;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    CarParkManager cpm;
    private final static int MY_PERMISSION_FINE_LOCATION = 9001;
    //    private GeoApiContext mGeoApiContext = null;
    private static final String TAG = "MainActivity";
    boolean[] set = new boolean[4];
    ImageButton carParks, cycleParks, trafficIncident, facilities;
    LatLng myPosition;


//    public Bitmap createCustomMarker(@DrawableRes int resource, String _name) {
//
//        View marker = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
//
//        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
//        markerImage.setImageResource(resource);
//        TextView txt_name = (TextView)marker.findViewById(R.id.name);
//        txt_name.setText(_name);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
//        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
//        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
//        marker.buildDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        marker.draw(canvas);
//
//        return bitmap;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        carParks = (ImageButton) findViewById(R.id.carParks);
        cycleParks = (ImageButton) findViewById(R.id.cycleParks);
        trafficIncident = (ImageButton) findViewById(R.id.trafficIncident);
        facilities = (ImageButton) findViewById(R.id.facilities);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        if(mGeoApiContext == null) {
//            mGeoApiContext = new GeoApiContext.Builder()
//                                    .apiKey(getString(R.string.google_maps_api_key))
//                                    .build();
//        }

        carParks.setOnClickListener(this);
        cycleParks.setOnClickListener(this);
        trafficIncident.setOnClickListener(this);
        facilities.setOnClickListener(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        Location location = new Location(1.346272, 103.698873);
        myPosition = new LatLng(location.getXCoordinate(), location.getYCoordinate());
        Marker Test = mMap.addMarker(new MarkerOptions().position(myPosition).title("My Location").snippet("Hi!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
        Test.setVisible(true);

        googleMap.setTrafficEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.carParks:
                set[0] = set[0] ? false : true;
                break;
            case R.id.cycleParks:
                set[1] = set[1] ? false : true;
                break;
            case R.id.trafficIncident:
                set[2] = set[2] ? false : true;
                break;
            case R.id.facilities:
                set[3] = set[3] ? false : true;
                break;
        }

        setMapMarkers(myPosition);
    }

    private void setMapMarkers(LatLng currentLocation) {
        cpm = new CarParkManager(this);
        mMap.clear();
        if (set[0] == true) {
            CycleTaskRunner cycleTaskRunner = new CycleTaskRunner();
            cycleTaskRunner.execute(currentLocation);
        }
        if (set[1] == true) {
            CarTaskRunner carTaskRunner = new CarTaskRunner();
            carTaskRunner.execute(currentLocation);
        }
        if (set[2] == true) {
            IncidentTaskRunner incidentTaskRunner = new IncidentTaskRunner();
            incidentTaskRunner.execute(currentLocation);
        }
        if (set[3] == true) {
            FacilitiesTaskRunner facilitiesTaskRunner = new FacilitiesTaskRunner();
            facilitiesTaskRunner.execute(currentLocation);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }


    private class CycleTaskRunner extends AsyncTask<LatLng, Void, ArrayList<CyclePark>> {

        @Override
        protected ArrayList<CyclePark> doInBackground(LatLng... params) {
            ArrayList<CyclePark> cycleParks = new ArrayList<>();
            LatLng position = params[0];
            try {
                cycleParks = CycleParkManager.returnCycleParksInVicinity(new Location(position.latitude, position.longitude), 0.1);
            } catch (Exception e) {
                exit(0);
                e.printStackTrace();
                Log.d("101", e.getMessage());
            }
            return cycleParks;
        }


        @Override
        protected void onPostExecute(ArrayList<CyclePark> cycleParks) {
            for (CyclePark cyclePark : cycleParks) {

                LatLng bbm3 = new LatLng(cyclePark.getLocation().getXCoordinate(), cyclePark.getLocation().getYCoordinate());
                Marker BBM3 = mMap.addMarker(new MarkerOptions()
                        .position(bbm3)
                        .title(cyclePark.getDescription())
                        .icon((BitmapDescriptorFactory.defaultMarker())));
                BBM3.setTag("CyclePark");
            }
        }
    }

    private class CarTaskRunner extends AsyncTask<LatLng, Void, ArrayList<CarPark>> {

        @Override
        protected ArrayList<CarPark> doInBackground(LatLng... params) {
            ArrayList<CarPark> carParks = new ArrayList<>();
            LatLng position = params[0];
            try {
                carParks = cpm.returnUpdatedCarParkList(new Location(position.latitude, position.longitude), 1.5);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("101", e.getMessage());
                exit(0);
            }
            return carParks;
        }


        @Override
        protected void onPostExecute(ArrayList<CarPark> carParks) {
            for (CarPark carPark : carParks) {
                LatLng bbm3 = new LatLng(carPark.getLocation().getXCoordinate(), carPark.getLocation().getYCoordinate());
                Marker BBM3 = mMap.addMarker(new MarkerOptions()
                        .position(bbm3)
                        .title(carPark.getCarParkNumber())
                        .icon((BitmapDescriptorFactory.defaultMarker())));

                BBM3.setTag("CarPark");
            }
        }
    }

    private class IncidentTaskRunner extends AsyncTask<LatLng, Void, ArrayList<IncidentTypes>> {

        @Override
        protected ArrayList<IncidentTypes> doInBackground(LatLng... params) {
            ArrayList<IncidentTypes> incidents = new ArrayList<>();
            LatLng position = params[0];
            try {
                incidents = TrafficIncidentManager.returnTrafficIncidents(new Location(position.latitude, position.longitude));
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("101", e.getMessage());
                exit(0);
            }
            return incidents;
        }


        @Override
        protected void onPostExecute(ArrayList<IncidentTypes> incidents) {
            for (IncidentTypes incident : incidents) {

                LatLng bbm3 = new LatLng(incident.getLocation().getXCoordinate(), incident.getLocation().getYCoordinate());
                Marker BBM3 = mMap.addMarker(new MarkerOptions()
                        .position(bbm3)
                        .title(incident.retMessage())
                        .icon((BitmapDescriptorFactory.defaultMarker())));
                BBM3.setTag("Incident");
            }
        }
    }

    private class FacilitiesTaskRunner extends AsyncTask<LatLng, Void, ArrayList<FacilityTypes>> {

        @Override
        protected ArrayList<FacilityTypes> doInBackground(LatLng... params) {
            ArrayList<FacilityTypes> facilities = new ArrayList<>();
            LatLng position = params[0];
            try {
                facilities = FacilitiesManager.getAllFacilities(new Location(position.latitude, position.longitude), 0.5);
            } catch (Exception e) {
                exit(0);
                e.printStackTrace();
                Log.d("101", e.getMessage());
            }
            return facilities;
        }


        @Override
        protected void onPostExecute(ArrayList<FacilityTypes> facilities) {
            for (FacilityTypes facility : facilities) {

                LatLng bbm3 = new LatLng(facility.retLocation().getXCoordinate(), facility.retLocation().getYCoordinate());
                Marker BBM3 = mMap.addMarker(new MarkerOptions()
                        .position(bbm3)
                        .title(facility.retName())
                        .icon(BitmapDescriptorFactory.defaultMarker()));
                BBM3.setTag("Facility");
            }
        }
    }
}




