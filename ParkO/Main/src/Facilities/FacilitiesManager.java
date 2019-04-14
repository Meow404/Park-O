package Facilities;

import java.util.Scanner;

import Extra.Location.Location;
import Facilities.FacilityTypes.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static Extra.Extra.order;
import static java.lang.System.exit;

public class FacilitiesManager {

    private static String APIToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI1NDAsInVzZXJfaWQiOjI1NDAsImVtYWlsIjoiYWxmcmVkaGh3QGdtYWlsLmNvbSIsImZvcmV2ZXIiOmZhbHNlLCJpc3MiOiJodHRwOlwvXC9vbTIuZGZlLm9uZW1hcC5zZ1wvYXBpXC92MlwvdXNlclwvc2Vzc2lvbiIsImlhdCI6MTU1NDUzMzg1MSwiZXhwIjoxNTU0OTY1ODUxLCJuYmYiOjE1NTQ1MzM4NTEsImp0aSI6IjllYWVkM2IxNzczZmJkZmYxZjU4NmFjM2ZhODZiOTdiIn0.v8Eax8FIzaDK_HgxfhEJotuIIaa2fX_6lxdm1-4q_Xk";

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

        JSONArray facilitiesJSONArray = facilitiesJSONObject.getJSONArray("SrchResults");
        String type = facilitiesJSONArray.getJSONObject(0).getString("Theme_Name");

        for (int i = 1; i < facilitiesJSONArray.length(); i++) {
            JSONObject jObj = facilitiesJSONArray.getJSONObject(i);
            facilities.add(retFacilityTypes(type, jObj));
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
        } while (choice != 6);
    }
}
