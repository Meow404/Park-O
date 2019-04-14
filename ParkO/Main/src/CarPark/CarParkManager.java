package CarPark;

import Extra.Location.Location;
import com.opencsv.CSVReader;

import static Extra.Extra.*;

import java.io.*;
import java.util.ArrayList;


public class CarParkManager {

    private static ArrayList<CarPark> carParks;

    public CarParkManager() {
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

    private static ArrayList<CarPark> retCarParks() {
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

    public static ArrayList<CarPark> getCarParksWithDistance(Location currentLocation, double constraint) {
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
