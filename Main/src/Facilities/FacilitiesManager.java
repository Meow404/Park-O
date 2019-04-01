package Facilities;

import Extra.Location.Location;
import Facilities.FacilityTypes.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import static Extra.Extra.*;


public class FacilitiesManager {
    /* these are the various themes we will be querying for */
    private static String[] themeArray = { "hawkercentre", "supermarkets", "axs_station", "exercisefacilities", "libraries" };
    private static String APIToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI1NDAsInVzZXJfaWQiOjI1NDAsImVtYWlsIjoiYWxmcmVkaGh3QGdtYWlsLmNvbSIsImZvcmV2ZXIiOmZhbHNlLCJpc3MiOiJodHRwOlwvXC9vbTIuZGZlLm9uZW1hcC5zZ1wvYXBpXC92MlwvdXNlclwvc2Vzc2lvbiIsImlhdCI6MTU1MzY3NjM3NSwiZXhwIjoxNTU0MTA4Mzc1LCJuYmYiOjE1NTM2NzYzNzUsImp0aSI6IjZlNGY4NjY2ZTU1ODQxNWU3YTZkNzE0N2FmMWJiYTc2In0.X7eHxXzNACWbyxZiyeJGxxLJeTt0kDA-iRriQmos7q0";
    private static ArrayList<JSONObject> availableFacilities = new ArrayList<>();
    private static JSONArray facility = new JSONArray();

    /* API Token to be changed every 3 days */
    private void setAPIToken(String Token){
        this.APIToken = Token;
    }
    private static String getAPIToken(){
        return APIToken;
    }

    /* retrieve all theme data in JSON format into files */

    /* param 'extents' take in Lat, xCor, Long, yCor  We will need to use the coordinates converter here */
    private static void retrieveThemes(Location location){
        String themeRetrieved;
        for(int index  = 0;index < themeArray.length ; index++){
            themeRetrieved = readFromURL("https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + themeArray[index] + "&token=" + getAPIToken() + "&extents=" + location.getXCoordinate() + ",%20103.7796402," + location.getYCoordinate()+ ",%20103.8726032");
            JSONObject jObj = new JSONObject(themeRetrieved);
            /* we append the entire JSON data of EACH theme into each index of the ArrayList */
            availableFacilities.add(jObj);
            writeUsingOutputStream(jObj.toString(4),themeArray[index] + ".txt");
        }
    }

    private static FacilityTypes retFacilityTypes (String facilityType, String name, String address, Double xCor, Double yCor) {
        if (facilityType.equals("HawkerCentre"))
            return new HawkerCentre(name, address, xCor, yCor);
        else if (facilityType.equals("Supermarket"))
            return new Supermarket(name, address, xCor, yCor);
        else if (facilityType.equals("AXSstation"))
            return new AXSstation(name, address, xCor, yCor);
        else if (facilityType.equals("ExerciseFacility"))
            return new ExerciseFacility(name, address, xCor, yCor);
        else if (facilityType.equals("Library"))
            return new Library(name, address, xCor, yCor);
        else
            return null;
    }


    private static void updateFacility(){
        for(int index = 0; index < availableFacilities.size();index++){
            JSONArray facilityItems = availableFacilities.get(index).getJSONArray("SrchResults");
            JSONArray items = new JSONArray();
            switch(index){
                case 0: ArrayList<FacilityTypes> hawkerCentres = new ArrayList<>();
                        for(int i=1; i< 6;i++){
                            JSONObject jobj = facilityItems.getJSONObject(i);
                            String name = jobj.getString("NAME");
                            String address = jobj.getString("ADDRESS_MYENV");
                            String postal = jobj.getString("ADDRESSPOSTALCODE");
                            double xCor = Double.parseDouble(jobj.getString("LATITUDE"));
                            double yCor = Double.parseDouble(jobj.getString("LONGITUDE"));
                            hawkerCentres.add(retFacilityTypes("HawkerCentre", name, (address + postal) , xCor, yCor));
                        }
                        for (FacilityTypes Facility : hawkerCentres) {
                            Facility.print();
                        }
                        break;
                case 1: ArrayList<FacilityTypes> supermarkets = new ArrayList<>();
                        for(int i=1; i< 6;i++){
                            JSONObject jobj = facilityItems.getJSONObject(i);
                            String name = jobj.getString("NAME");
                            String address = jobj.getString("STR_NAME");
                            String postal = jobj.getString("POSTCODE");
                            String geoLoc = jobj.getString("LatLng");
                            Double[] xyCor = splitLatLong(geoLoc);
                            supermarkets.add(retFacilityTypes("Supermarket", name, (address + postal) , xyCor[0], xyCor[1]));
                        }
                        for (FacilityTypes Facility : supermarkets) {
                            Facility.print();
                        }
                        break;

                case 2: ArrayList<FacilityTypes> axsStations = new ArrayList<>();
                        for(int i=1; i< 6;i++){
                            JSONObject jobj = facilityItems.getJSONObject(i);
                            String axsID = jobj.getString("AXS_ID");
                            String address = jobj.getString("DESCRIPTION");
                            String geoLoc = jobj.getString("LatLng");
                            Double[] xyCor = splitLatLong(geoLoc);
                            axsStations.add(retFacilityTypes("AXSstation", axsID, address , xyCor[0], xyCor[1]));
                        }
                        for (FacilityTypes Facility : axsStations) {
                            Facility.print();
                        }
                        break;

                case 3: ArrayList<FacilityTypes> exerciseFacilities = new ArrayList<>();
                        for(int i=1; i< 6;i++){
                            JSONObject jobj = facilityItems.getJSONObject(i);
                            String name = jobj.getString("NAME");
                            String address = jobj.getString("ADDRESSSTREETNAME");
                            String postal = jobj.getString("ADDRESSPOSTALCODE");
                            String geoLoc = jobj.getString("LatLng");
                            Double[] xyCor = splitLatLong(geoLoc);
                            exerciseFacilities.add(retFacilityTypes("ExerciseFacility", name, (address + postal), xyCor[0], xyCor[1]));
                        }
                        for (FacilityTypes Facility : exerciseFacilities) {
                            Facility.print();
                        }
                        break;

                case 4: ArrayList<FacilityTypes> libraries = new ArrayList<>();
                        for(int i=1; i< 4;i++){
                            JSONObject jobj = facilityItems.getJSONObject(i);
                            String name = jobj.getString("NAME");
                            String address = jobj.getString("ADDRESSSTREETNAME");
                            String postal = jobj.getString("ADDRESSPOSTALCODE");
                            String geoLoc = jobj.getString("LatLng");
                            Double[] xyCor = splitLatLong(geoLoc);
                            libraries.add(retFacilityTypes("Library", name, (address + postal), xyCor[0], xyCor[1]));
                        }
                        for (FacilityTypes Facility : libraries) {
                            Facility.print();
                        }
                        break;
            }
        }
    }
    /* ###################################################################### */
    /* use this to get all available theme names */
    /* String allThemes = readFromURL("https://developers.onemap.sg/privateapi/themesvc/getAllThemesInfo?token="+APIToken); */
    /* ###################################################################### */

    public static void main(String args[]) {
        Location location = new Location(); /*for the purpose of completing the code - using mock coordinate*/
        location.setXCoordinate(1.291789);
        location.setYCoordinate(1.3290461); /* we still need to do coordinate conversion for xCor and yCor */
        retrieveThemes(location);
        updateFacility();
    }

}
