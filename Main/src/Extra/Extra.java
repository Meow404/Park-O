package Extra;

import CarPark.CarPark;
import Extra.Location.Location;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import static java.lang.System.exit;

public class Extra {

    private static int donePercentage = 0;

    public static void writeUsingOutputStream(String data, String fileName) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName));
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

    public static double distance(Location startLocation, Location stopLocation) {
        if ((startLocation.getXCoordinate() == stopLocation.getXCoordinate()) && (startLocation.getYCoordinate() == stopLocation.getYCoordinate())) {
            return 0;
        } else {
            double theta = startLocation.getYCoordinate() - stopLocation.getYCoordinate();
            double dist = Math.sin(Math.toRadians(startLocation.getXCoordinate())) * Math.sin(Math.toRadians(stopLocation.getXCoordinate())) + Math.cos(Math.toRadians(startLocation.getXCoordinate())) * Math.cos(Math.toRadians(stopLocation.getXCoordinate())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.853159616;
            return dist;
        }

    }

    public static String readFromURL(String URL) {
        String content = "{}";
        try {
            java.net.URL oracle = new URL(URL);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            content = in.readLine();
            in.close();
        } catch (MalformedURLException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return content;
    }

    public static String getHTTPSRequest(String ApiURL, String accountKey) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(ApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("AccountKey", accountKey);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException ex) {
            ex.getMessage();
            exit(-1);
        } catch (ProtocolException ex) {
            ex.getMessage();
            exit(-1);
        } catch (IOException ex) {
            ex.getMessage();
            exit(-1);
        }
        return content.toString();
    }

    public static Location retLongLang(double x, double y) {
        String stringLocation = readFromURL("https://developers.onemap.sg/commonapi/convert/3414to4326?X=" + Double.valueOf(x) + "&Y=" + Double.valueOf(y));

        JSONObject locObject = new JSONObject(stringLocation);
        double lat = (Double) locObject.get("latitude");
        double lon = (Double) locObject.get("longitude");

        return new Location(lat, lon);
    }

    public static void order(ArrayList<CarPark> carParks, Location curLocation) {
        for (int i = 0; i < carParks.size(); i++)
            for (int j = 0; j < carParks.size() - i - 1; j++) {
                if (distance(carParks.get(j).getLocation(), curLocation) > distance(carParks.get(j + 1).getLocation(), curLocation))
                    Collections.swap(carParks, j, j + 1);
            }

    }

    public static void printLoadingBar(int percentage) {
        System.out.print("\b\b");
        System.out.print(String.format("%3d ", percentage));
    }
}
