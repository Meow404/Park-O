package TrafficIncidents.IncidentTypes;

import Extra.Location.Location;
import Extra.Location.LocationHandler;

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
        for (int i = 0; message.length() > 45 * i; i++) {
            String temp;
            if (message.length() < 45 * (i + 1))
                temp = message.substring(45 * i);
            else
                temp = message.substring(45 * i, 45 * (i + 1));

            System.out.println(String.format("|%15s : %-45s|", "Details", temp.trim()));
        }
//        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
//        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }

}
