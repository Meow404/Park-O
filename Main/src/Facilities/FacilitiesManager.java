package Facilities;
import java.util.Scanner;
import Extra.Location.Location;
import Facilities.FacilityTypes.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static Extra.Extra.order;
import static Extra.Extra.retLongLang;

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

    public static void main(String args[]) {
        Location location = new Location(); /*for the purpose of completing the code - using mock coordinate*/
        location.setXCoordinate(1.291789);
        location.setYCoordinate(1.3290461); /* we still need to do coordinate conversion for xCor and yCor */
        location.setLatitude((retLongLang(location.getXCoordinate(), location.getYCoordinate())).getLat());
        location.setLongtitude((retLongLang(location.getXCoordinate(), location.getYCoordinate())).getLong());


        System.out.println("Choose a facilities");
        System.out.println("(1) Hawker Centre");
        System.out.println("(2) Supermarket");
        System.out.println("(3) AXSstation");
        System.out.println("(4) Library");
        System.out.println("(5) ExerciseFacility");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {

            case 1:
                JSONObject results1 = HawkerCentre.retrieveTheme(location, APIToken);
                ArrayList<FacilityTypes> hawkerCentres = returnFacilities(results1);
                for (FacilityTypes facilty : hawkerCentres) {
                    facilty.print();
                }
                break;

            case 2:
                JSONObject results2 = Supermarket.retrieveTheme(location, APIToken);
                ArrayList<FacilityTypes> supermarkets = returnFacilities(results2);
                for (FacilityTypes facilty : supermarkets) {
                    facilty.print();
                }
                break;
            case 3:
                JSONObject results3 = AXSstation.retrieveTheme(location, APIToken);
                ArrayList<FacilityTypes> axsStations = returnFacilities(results3);
                for (FacilityTypes facilty : axsStations) {
                    facilty.print();
                }
                break;
            case 4:
                JSONObject results4 = Library.retrieveTheme(location, APIToken);
                ArrayList<FacilityTypes> libraries = returnFacilities(results4);
                for (FacilityTypes facilty : libraries) {
                    facilty.print();
                }
                break;
            case 5:
                JSONObject results5 = ExerciseFacility.retrieveTheme(location, APIToken);
                ArrayList<FacilityTypes> exerciseFacilities = returnFacilities(results5);
                for (FacilityTypes facilty : exerciseFacilities) {
                    facilty.print();
                }
                break;

            default: break;
        }
        //order(facilities,location);
    }
}
