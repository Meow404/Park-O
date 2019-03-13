package CarPark;

import jdk.jfr.Event;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class CarParkManager {

    private static CarPark retCarPark(String[] carParkInformation) {
        String carParkNumber = carParkInformation[0].substring(1);
        String address = carParkInformation[1];
        float xCor = Float.valueOf(carParkInformation[2]);
        float yCor = Float.valueOf(carParkInformation[3]);
        String carParkType = carParkInformation[4];
        String typeOfParkingSystem = carParkInformation[5];
        boolean freeParking = carParkInformation[7] == "NO" ? false : true;
        boolean nightParking = carParkInformation[8] == "NO" ? false : true;
        CarPark carPark = new CarPark(carParkNumber,address,xCor,yCor,carParkType,typeOfParkingSystem,freeParking,nightParking);
        return carPark;
    }

    private static ArrayList<CarPark> getCarParks() {
        ArrayList<CarPark> carParks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Files\\hdb-carpark-information\\hdb-carpark-information.csv"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    i = 1;
                    continue;
                }
                String[] values = line.split("\",\"");
                carParks.add(retCarPark(values));
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return carParks;
    }

    private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("CarPark.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateCarParkInformation() {
        String inputLine = "{}";
        try {
            URL oracle = new URL("https://api.data.gov.sg/v1/transport/carpark-availability");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            inputLine = in.readLine();
            in.close();
        } catch (MalformedURLException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        JSONObject obj = new JSONObject(inputLine);
        writeUsingOutputStream(obj.toString(4));

    }

    public static void main(String args[]) {
        ArrayList<CarPark> carParks = new ArrayList<>();
        carParks = getCarParks();
        updateCarParkInformation();
        System.out.print("Done");
    }
}
