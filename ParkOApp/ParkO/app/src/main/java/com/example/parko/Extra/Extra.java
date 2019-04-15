package com.example.parko.Extra;

import android.os.StrictMode;
import android.util.Log;

import com.example.parko.Extra.Location.Location;
import com.example.parko.Extra.Location.LocationHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.exit;

public class Extra {

    private static int donePercentage = 0;

    public static void writeUsingOutputStream(String data, String fileName) {
        OutputStream os = null;
        try {

            os = new FileOutputStream(new File("Files//" + fileName));
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

    public static Double[] splitLatLong(String LatLng) {
        String values[] = LatLng.split(",");
        Double xCor = Double.parseDouble(values[0]);
        Double yCor = Double.parseDouble(values[1]);
        Double[] location = {xCor, yCor};
        return location;
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
            Log.d("10001",ex.getMessage());
            exit(-1);
        } catch (ProtocolException ex) {
            Log.d("10002",ex.getMessage());
            exit(-1);
        } catch (IOException ex) {
            Log.d("10003",ex.getMessage());
            exit(-1);
        }
        return content.toString();
    }

    public static Location retLongLang(double x, double y) {
        String stringLocation = readFromURL("https://developers.onemap.sg/commonapi/convert/3414to4326?X=" + Double.valueOf(x) + "&Y=" + Double.valueOf(y));

        try {
            JSONObject locObject = new JSONObject(stringLocation);
            double lat = (Double) locObject.get("latitude");
            double lon = (Double) locObject.get("longitude");
            return new Location(x, y, lat, lon);
        }catch (JSONException ex){}
        return null;

    }

    public static void order(ArrayList objects, Location curLocation) {
        if (objects.size() > 1)
            for (int i = 0; i < objects.size(); i++)
                for (int j = 0; j < objects.size() - i - 1; j++) {
                    LocationHandler object1 = (LocationHandler) objects.get(j);
                    LocationHandler object2 = (LocationHandler) objects.get(j + 1);
                    if (distance(object1.getLocation(), curLocation) > distance(object2.getLocation(), curLocation))
                        Collections.swap(objects, j, j + 1);

                }
    }

    public static void printLoadingBar(int percentage) {
        System.out.print("\b\b");
        System.out.print(String.format("%3d ", percentage));
    }

    public static void systemSplitOutput(String Values, String message) {

        for (int i = 0; message.length() > 45 * i; i++) {
            String temp;
            if (message.length() < 45 * (i + 1))
                temp = message.substring(45 * i);
            else
                temp = message.substring(45 * i, 45 * (i + 1));

            System.out.println(String.format("|%15s : %-45s|", Values, temp.trim()));
        }

    }

    public static Double[] retAxisConstraints(Location curLocation, Double constraints) {
        Double[] axisConstraints = new Double[4];

        Double xConstant = 0.00899364881;
        Double yConstant = 0.0089961189;

        axisConstraints[0] = curLocation.getXCoordinate() - xConstant;
        axisConstraints[1] = curLocation.getYCoordinate() - yConstant;
        axisConstraints[2] = curLocation.getXCoordinate() + xConstant;
        axisConstraints[3] = curLocation.getYCoordinate() + yConstant;

        return axisConstraints;
    }
}
