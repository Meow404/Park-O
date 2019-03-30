package TrafficIncidents;

import TrafficIncidents.IncidentTypes.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static Extra.Extra.*;

public class TrafficIncidentManager {

    public static void main(String args[]) {
        ArrayList<IncidentTypes> trafficIncidents = new ArrayList<>();
        String trafficIncidentsApi = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";
        String dataMallAccountKey = "ALUuk8N1RNOOh8e8lGi3QQ==";

        JSONObject trafficIncidentJsonObj =new JSONObject(getHTTPSRequest(trafficIncidentsApi, dataMallAccountKey));
        JSONArray trafficIncidentJsonArray = trafficIncidentJsonObj.getJSONArray("value");


        for(int index=0; index<trafficIncidentJsonArray.length();index++){
            JSONObject trafficIncident = trafficIncidentJsonArray.getJSONObject(index);
            String incidentType = (String)trafficIncident.get("Type");
            String incidentMessage = (String)trafficIncident.get("Message");
            double xCor = (Double)trafficIncident.get("Latitude");
            double yCor = (Double)trafficIncident.get("Longitude");
            incidentType = incidentType.trim();
  
            if(incidentType.equals("Accident"))
                trafficIncidents.add(new Accident(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Roadwork"))
                trafficIncidents.add(new RoadWorks(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Vehicle breakdown"))
                trafficIncidents.add(new VehicleBreakdown(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Weather"))
                trafficIncidents.add(new Weather(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Obstacle"))
                trafficIncidents.add(new Obstacle(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Road Block"))
                trafficIncidents.add(new RoadBlock(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Heavy Traffic"))
                trafficIncidents.add(new HeavyTraffic(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Misc."))
                trafficIncidents.add(new Miscellaneous(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Diversion"))
                trafficIncidents.add(new Diversion(incidentMessage,xCor,yCor));
            else if(incidentType.equals("Unattended Vehicle"))
                trafficIncidents.add(new UnattendedVehicle(incidentMessage,xCor,yCor));
        }
     System.out.print(trafficIncidents.size());
    for(IncidentTypes Incident : trafficIncidents)
        Incident.print();
    }
}
