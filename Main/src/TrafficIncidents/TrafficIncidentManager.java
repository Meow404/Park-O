package TrafficIncidents;

import Extra.Location.Location;
import TrafficIncidents.IncidentTypes.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static Extra.Extra.*;

public class TrafficIncidentManager {

    private static IncidentTypes retIncidentTypes(String incidentType, String incidentMessage, Double xCor, Double yCor) {
        if (incidentType.equals("Accident"))
            return new Accident(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Roadwork"))
            return new RoadWorks(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Vehicle breakdown"))
            return new VehicleBreakdown(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Weather"))
            return new Weather(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Obstacle"))
            return new Obstacle(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Road block"))
            return new RoadBlock(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Heavy Traffic"))
            return new HeavyTraffic(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Misc."))
            return new Miscellaneous(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Diversion"))
            return new Diversion(incidentMessage, xCor, yCor);
        else if (incidentType.equals("Unattended vehicle"))
            return new UnattendedVehicle(incidentMessage, xCor, yCor);
        else
            return null;
    }

    private static ArrayList<IncidentTypes> manageJsonData(JSONObject trafficIncidentJsonObj) {
        ArrayList<IncidentTypes> trafficIncidents = new ArrayList<>();
        JSONArray trafficIncidentJsonArray = trafficIncidentJsonObj.getJSONArray("value");
        writeUsingOutputStream(trafficIncidentJsonArray.toString(4),"trafficIncidents.txt");

        for (int index = 0; index < trafficIncidentJsonArray.length(); index++) {

            JSONObject trafficIncident = trafficIncidentJsonArray.getJSONObject(index);

            String incidentType = (String) trafficIncident.get("Type");
            String incidentMessage = (String) trafficIncident.get("Message");
            double xCor = (Double) trafficIncident.get("Latitude");
            double yCor = (Double) trafficIncident.get("Longitude");
            incidentType = incidentType.trim();

            trafficIncidents.add(retIncidentTypes(incidentType, incidentMessage, xCor, yCor));

        }
        return trafficIncidents;
    }


    public static ArrayList<IncidentTypes> returnTrafficIncidents(Location myLocation) {
        ArrayList<IncidentTypes> trafficIncidents ;
        String trafficIncidentsApi = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";
        String dataMallAccountKey = "ALUuk8N1RNOOh8e8lGi3QQ==";

        JSONObject trafficIncidentJsonObj = new JSONObject(getHTTPSRequest(trafficIncidentsApi, dataMallAccountKey));
        trafficIncidents = manageJsonData(trafficIncidentJsonObj);

        if(trafficIncidents.size()!=0)
            order(trafficIncidents,myLocation);

        return trafficIncidents;
    }
}
