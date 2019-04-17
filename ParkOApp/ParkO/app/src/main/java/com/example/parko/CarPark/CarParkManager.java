package com.example.parko.CarPark;

import android.content.Context;
import android.util.Log;

import com.example.parko.Extra.Location.Location;
import com.example.parko.R;
import com.opencsv.CSVReader;

import static com.example.parko.Extra.Extra.*;

import java.io.*;
import java.util.ArrayList;


public class CarParkManager {

    private static ArrayList<CarPark> carParks;
    private Context context;

    public CarParkManager(Context context) {
        this.context = context;
        carParks = retCarParks();

    }

    private static CarPark retCarPark(String[] carParkInformation) {

        String carParkNumber = carParkInformation[0].substring(1);
        String address = carParkInformation[1];

        float xCor = Float.valueOf(carParkInformation[2]);
        float yCor = Float.valueOf(carParkInformation[3]);
        Location location = new Location(xCor, yCor);

        String carParkType = carParkInformation[4];
        String typeOfParkingSystem = carParkInformation[5];

        boolean freeParking = carParkInformation[7] == "NO" ? false : true;
        boolean nightParking = carParkInformation[8] == "NO" ? false : true;

        CarPark carPark = new CarPark(carParkNumber, address, location, carParkType, typeOfParkingSystem, freeParking, nightParking);

        return carPark;
    }

    private ArrayList<CarPark> retCarParks() {
        ArrayList<CarPark> carParks = new ArrayList<>();
        try {
            InputStream cpi = context.getResources().openRawResource(R.raw.car_park_information);
            CSVReader csvReader = new CSVReader(new InputStreamReader(cpi));
            String[] line;
            int i = 0;
            while ((line = csvReader.readNext()) != null) {
                if (i == 0) {
                    i = 1;
                    continue;
                }
                carParks.add(retCarPark(line));
            }
            csvReader.close();
        } catch (Exception EX) {
            Log.d("1001",EX.getMessage());
        }
        return carParks;
    }

    private static ArrayList<CarPark> getCarParksWithDistance(Location currentLocation, double constraint) {
        ArrayList<CarPark> constrainedCarParks = new ArrayList<>();
        for (CarPark carPark : carParks) {
            if (distance(carPark.getLocation(), currentLocation) <= constraint) {
                constrainedCarParks.add(carPark);
            }
        }
        return constrainedCarParks;
    }

    public static ArrayList<CarPark> returnUpdatedCarParkList(Location currentLocation, double constraint) {
        ArrayList<CarPark> constrainedCarParks = getCarParksWithDistance(currentLocation, constraint);
        constrainedCarParks = CarParkUpdate.getCarParkAvailability(constrainedCarParks);
        if (constrainedCarParks.size() != 0)
            order(constrainedCarParks, currentLocation);
        return constrainedCarParks;
    }


    public static void setCarParks(ArrayList<CarPark> carParks) {
        carParks = carParks;
    }

    public ArrayList<CarPark> getCarParks() {
        return carParks;
    }

}
