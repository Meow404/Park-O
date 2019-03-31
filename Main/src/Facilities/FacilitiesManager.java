package Facilities;

import Extra.Location.Location;
import org.json.JSONArray;
import org.json.JSONObject;

import static Extra.Extra.*;


public class FacilitiesManager {

    private static String[] themeArray = { "hawkercentre", "supermarkets", "axs_station", "exercisefacilities", "libraries" };
    private static String APIToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI1NDAsInVzZXJfaWQiOjI1NDAsImVtYWlsIjoiYWxmcmVkaGh3QGdtYWlsLmNvbSIsImZvcmV2ZXIiOmZhbHNlLCJpc3MiOiJodHRwOlwvXC9vbTIuZGZlLm9uZW1hcC5zZ1wvYXBpXC92MlwvdXNlclwvc2Vzc2lvbiIsImlhdCI6MTU1MzY3NjM3NSwiZXhwIjoxNTU0MTA4Mzc1LCJuYmYiOjE1NTM2NzYzNzUsImp0aSI6IjZlNGY4NjY2ZTU1ODQxNWU3YTZkNzE0N2FmMWJiYTc2In0.X7eHxXzNACWbyxZiyeJGxxLJeTt0kDA-iRriQmos7q0";
    private static JSONObject[] availableFacilities = new JSONObject[5];
    private static JSONArray facility = new JSONArray();

    /* API Token to be changed every 3 days */
    private void setAPIToken(String Token){
        this.APIToken = Token;
    }
    private static String getAPIToken(){
        return APIToken;
    }
    /* retrieve all theme data in JSON format into files */
    /* param 'extents' take in Lat, xCor, Long, yCor */
    private static void retrieveThemes(Location location){
        String themeRetrieved;
        for(int i = 0;i < 5; i++){
            themeRetrieved = readFromURL("https://developers.onemap.sg/privateapi/themesvc/retrieveTheme?queryName=" + themeArray[i] + "&token=" + getAPIToken() + "&extents=" + location.getXCoordinate() + ",%20103.7796402," + location.getYCoordinate()+ ",%20103.8726032");
            JSONObject jObj = new JSONObject(themeRetrieved);
            availableFacilities[i] =  jObj;
            writeUsingOutputStream(jObj.toString(4),themeArray[i] + ".txt");
        }
    }
    /* update hawkerCentre with top 5 search results */
    private static void updateHawkerCentre(JSONObject jobj){
        JSONArray hawkerCentreItems = jobj.getJSONArray("SrchResults");
        JSONArray hawkercentre = new JSONArray();
        for(int i = 1; i<6;i++){
            JSONObject object = hawkerCentreItems.getJSONObject(i);
            String hawkerCentreData1 = object.getString("NAME");
            String hawkerCentreData2 = object.getString("ADDRESS_MYENV");
            String hawkerCentreData3 = object.getString("ADDRESSPOSTALCODE");
            JSONObject update = new JSONObject();
            update.put("NAME", hawkerCentreData1);
            update.put("ADDRESS", hawkerCentreData2);
            update.put("POSTALCODE", hawkerCentreData3);
            hawkercentre.put(update);
        }
        facility.put(hawkercentre);
    }
    /* update supermarket with top 5 search results */
    private static void updateSupermarket(JSONObject jobj){
        writeUsingOutputStream(jobj.toString(4),"facilities.txt");
        JSONArray supermarketItems = jobj.getJSONArray("SrchResults");
       // writeUsingOutputStream(supermarketItems.toString(4),"facilities.txt");
        JSONArray supermarket = new JSONArray();
        for(int i = 1; i<6;i++){
            JSONObject object = supermarketItems.getJSONObject(i);
            String supermarketData1 = object.getString("NAME");
            String supermarketData2 = object.getString("STR_NAME");
            String supermarketData3 = object.getString("POSTCODE");
            JSONObject update = new JSONObject();
            update.put("NAME", supermarketData1);
            update.put("STR_NAME", supermarketData2);
            update.put("POSTCODE", supermarketData3);
            supermarket.put(update);
        }
        facility.put(supermarket);
    }
    /* update axsStation with top 5 search results */
    private static void updateAXSstation(JSONObject jobj){
        JSONArray axsStationItems = jobj.getJSONArray("SrchResults");
        JSONArray axsstation = new JSONArray();
        for(int i = 1; i<6;i++){
            JSONObject object = axsStationItems.getJSONObject(i);
            String axsStationData1 = object.getString("DESCRIPTION");
            String axsStationData2 = object.getString("AXS_ID");
            JSONObject update = new JSONObject();
            update.put("DESCRIPTION", axsStationData1);
            update.put("STR_NAME", axsStationData2);
            axsstation.put(update);
        }
        facility.put(axsstation);
    }
    /* update exerciseFacility with top 5 search results */
    private static void updateExerciseFacility(JSONObject jobj){
        JSONArray exerciseFacilityItems = jobj.getJSONArray("SrchResults");
        JSONArray exercisefacility = new JSONArray();
        for(int i = 1; i<6;i++){
            JSONObject object = exerciseFacilityItems.getJSONObject(i);
            String exerciseFacilityData1 = object.getString("NAME");
            //String exerciseFacilityData2 = object.getString("ADDRESSSTREETNAME");
            String exerciseFacilityData3 = object.getString("ADDRESSPOSTALCODE");
            JSONObject update = new JSONObject();
            update.put("NAME", exerciseFacilityData1);
            //update.put("ADDRESSSTREETNAME", exerciseFacilityData2);
            update.put("ADDRESSPOSTALCODE", exerciseFacilityData3);
            exercisefacility.put(update);
        }
        facility.put(exercisefacility);
    }

    /* update library with top 5 search results */
    private static void updateLibrary(JSONObject jobj){
        /* to be implemented as per above */
        JSONArray libraryItems = jobj.getJSONArray("SrchResults");
        JSONArray library = new JSONArray();
        for(int i = 1; i<4;i++){
            JSONObject object = libraryItems.getJSONObject(i);
            String libraryData1 = object.getString("NAME");
            String libraryData2 = object.getString("ADDRESSSTREETNAME");
            String libraryData3 = object.getString("ADDRESSPOSTALCODE");
            JSONObject update = new JSONObject();
            update.put("NAME", libraryData1);
            update.put("ADDRESSSTREETNAME", libraryData2);
            update.put("ADDRESSPOSTALCODE", libraryData3);
            library.put(update);
        }
        facility.put(library);
    }


    /* ###################################################################### */
    /* use this to get all available theme names */
    /* String allThemes = readFromURL("https://developers.onemap.sg/privateapi/themesvc/getAllThemesInfo?token="+APIToken); */
    /* ###################################################################### */


    public static void main(String args[]) {
        Location location = new Location(); /*for the purpose of completing the code */
        location.setXCoordinate(1.291789);
        location.setYCoordinate(1.3290461);
        retrieveThemes(location);
        updateHawkerCentre(availableFacilities[0]);
        updateSupermarket(availableFacilities[1]);
        updateAXSstation(availableFacilities[2]);
        updateExerciseFacility(availableFacilities[3]);
        updateLibrary(availableFacilities[4]);
        System.out.println(facility);
        System.out.print("Completed.");
    }

}
