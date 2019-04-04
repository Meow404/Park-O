package CarPark;

import Extra.Location.Location;
import com.opencsv.CSVReader;

import static Extra.Extra.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class CarParkManager {

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

    private static ArrayList<CarPark> getCarParks() {
        ArrayList<CarPark> carParks = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader("Files\\carParkInformation.csv"));
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
        } catch (IOException EX) {
        }
        return carParks;
    }

    private static ArrayList<CarPark> getCarParksWithDistance(ArrayList<CarPark> carParks, Location currentLocation, double constraint) {
        ArrayList<CarPark> constrainedCarParks = new ArrayList<>();
        for (CarPark carPark : carParks) {
            if (distance(carPark.getLocation(), currentLocation) <= constraint) {
                constrainedCarParks.add(carPark);
            }
        }
        return constrainedCarParks;
    }

    private static ArrayList<CarPark> getCarParkList(ArrayList<CarPark> carParks, HashMap<String, CarParkUpdate> carParkUpdateHashMap) {
        for (int i = 0; i < carParks.size(); i++) {
            CarParkUpdate carParkUpdate = carParkUpdateHashMap.get(carParks.get(i).getCarParkNumber());
            if (carParkUpdate == null || carParkUpdate.getTotalLots() == 0) {
                carParks.remove(i);
                i--;
            } else
                carParks.get(i).update(carParkUpdate);
        }

        return carParks;
    }

    public static void main(String args[]) {
        final long startTime = System.currentTimeMillis();
        ArrayList<CarPark> carParks;
        carParks = getCarParks();
        carParks = getCarParksWithDistance(carParks, new Location(1.342678, 103.757214), 0.5);
        HashMap<String, CarParkUpdate> carParkUpdateHashMap = CarParkUpdate.getCarParkAvailablility();
        getCarParkList(carParks, carParkUpdateHashMap);
        order(carParks, new Location(1.342678, 103.757214));
        for (CarPark carPark : carParks)
            carPark.print();
        final long endTime = System.currentTimeMillis();
        System.out.print("Done " + (endTime - startTime));
    }
}
