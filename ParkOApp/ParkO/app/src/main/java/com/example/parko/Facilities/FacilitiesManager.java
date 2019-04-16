package com.example.parko.Facilities;

import java.util.Scanner;

import com.example.parko.Extra.Location.Location;
import com.example.parko.Facilities.FacilityTypes.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.parko.Extra.Extra.*;
import static java.lang.System.exit;

public class FacilitiesManager {

    private static String APIToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI1NDAsInVzZXJfaWQiOjI1NDAsImVtYWlsIjoiYWxmcmVkaGh3QGdtYWlsLmNvbSIsImZvcmV2ZXIiOmZhbHNlLCJpc3MiOiJodHRwOlwvXC9vbTIuZGZlLm9uZW1hcC5zZ1wvYXBpXC92MlwvdXNlclwvc2Vzc2lvbiIsImlhdCI6MTU1NTIyMjgzMiwiZXhwIjoxNTU1NjU0ODMyLCJuYmYiOjE1NTUyMjI4MzIsImp0aSI6IjhmY2UzY2RlODgxMjM2N2UyOTA0MWEyNzIyOGMwMWU4In0.LTvMzlJ5N1J8b_tw21c9kw--oiwTzb8qyV4N-KuDBVo";

    /* API Token to be changed every 3 days */
    private void setAPIToken(String Token) {
        this.APIToken = Token;
    }

    private static String getAPIToken() {
        return APIToken;
    }

    private static FacilityTypes retFacilityTypes(String facilityType, JSONObject facility) {
        if (facilityType.equals("Hawker Centres"))
            return new HawkerCentre(facility);
        else if (facilityType.equals("Supermarkets"))
            return new Supermarket(facility);
        else if (facilityType.equals("AXS Station"))
            return new AXSstation(facility);
        else if (facilityType.equals("Gyms@SG"))
            return new ExerciseFacility(facility);
        else if (facilityType.equals("Libraries"))
            return new Library(facility);
        else
            return null;
    }

    public static ArrayList<FacilityTypes> returnFacilities(JSONObject facilitiesJSONObject) {
        ArrayList<FacilityTypes> facilities = new ArrayList<>();
        try {
            JSONArray facilitiesJSONArray = facilitiesJSONObject.getJSONArray("SrchResults");
            String type = facilitiesJSONArray.getJSONObject(0).getString("Theme_Name");

            for (int i = 1; i < facilitiesJSONArray.length(); i++) {
                JSONObject jObj = facilitiesJSONArray.getJSONObject(i);
                facilities.add(retFacilityTypes(type, jObj));
            }
        } catch (JSONException ex) {
        }
        return facilities;
    }

    /* ###################################################################### */
    /* use this to get ALL available theme names */
    /* String allThemes = readFromURL("https://developers.onemap.sg/privateapi/themesvc/getAllThemesInfo?token="+APIToken); */
    /* ###################################################################### */

    public static void getFacilities(Location location, Double constraint) {
//        location.setLatitude((retLongLang(location.getXCoordinate(), location.getYCoordinate())).getLat());
//        location.setLongtitude((retLongLang(location.getXCoordinate(), location.getYCoordinate())).getLong());
        int choice;
        do {

            System.out.println("\n\n    ~ Choose a facility ~");
            System.out.println("    (1) Hawker Centre");
            System.out.println("    (2) Supermarket");
            System.out.println("    (3) AXSstation");
            System.out.println("    (4) Library");
            System.out.println("    (5) ExerciseFacility");
            System.out.println("    (6) Exit");
            System.out.print("\n      Enter Your Choice : ");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            JSONObject results = null;

            switch (choice) {
                case 1:
                    results = HawkerCentre.retrieveTheme(location, APIToken, constraint);
                    break;
                case 2:
                    results = Supermarket.retrieveTheme(location, APIToken, constraint);
                    break;
                case 3:
                    results = AXSstation.retrieveTheme(location, APIToken, constraint);
                    break;
                case 4:
                    results = Library.retrieveTheme(location, APIToken, constraint);
                    break;
                case 5:
                    results = ExerciseFacility.retrieveTheme(location, APIToken, constraint);
                    break;
                case 6:
                    exit(0);
                default:
                    break;
            }
            ArrayList<FacilityTypes> facilities = returnFacilities(results);
            order(facilities, location);
            for (FacilityTypes facilty : facilities) {
                facilty.print();
            }
            //order(traffic_incidents,location);
        } while (choice != 6);
    }

    public static ArrayList<FacilityTypes> getAllFacilities(Location location, Double constraint) {
        JSONObject results;
        ArrayList<FacilityTypes> facilities = new ArrayList<>();
        results = HawkerCentre.retrieveTheme(location, APIToken, constraint);
        facilities.addAll(returnFacilities(results));
        results = Supermarket.retrieveTheme(location, APIToken, constraint);
        facilities.addAll(returnFacilities(results));
        results = AXSstation.retrieveTheme(location, APIToken, constraint);
        facilities.addAll(returnFacilities(results));
        results = Library.retrieveTheme(location, APIToken, constraint);
        facilities.addAll(returnFacilities(results));
        results = ExerciseFacility.retrieveTheme(location, APIToken, constraint);
        facilities.addAll(returnFacilities(results));
        order(facilities, location);
        return facilities;
    }
}
