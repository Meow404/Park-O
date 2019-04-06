package TrafficIncidents.IncidentTypes;

import Extra.Location.Location;
import Extra.Location.LocationHandler;
import static Extra.Extra.systemSplitOutput;

public abstract class IncidentTypes extends LocationHandler {
    private String message;
    private String incidentType;


    public IncidentTypes(String Message, double xCor, double yCor, String incidentType) {
        super(new Location(xCor,yCor));
        this.message = Message;
        this.incidentType = incidentType;
    }

    public String retType() {
        return incidentType;
    }

    public String retMessage() {
        return message;
    }

    public void print() {
        System.out.println(String.format("\n\n|%15s : %-45s|", "Incident Type", incidentType));
        systemSplitOutput("Details",message);
//        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
//        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }

}
