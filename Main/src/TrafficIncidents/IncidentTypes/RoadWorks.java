package TrafficIncidents.IncidentTypes;

import Extra.Location.Location;

public class RoadWorks implements IncidentTypes {
    private String message;
    private String incidentType;
    private Location location;

    public RoadWorks(String Message, double xCor, double yCor) {
        this.message = Message;
        incidentType = "Road Works";
        this.location = new Location(xCor, yCor);
    }

    public String retType() {
        return incidentType;
    }

    public String retMessage() {
        return message;
    }

    public Location retLocation() {
        return location;
    }

    public void print() {
        System.out.println(String.format("\n|%15s : %-45s|", "Incident Type", incidentType));
        for (int i = 0; message.length() > 45 * i; i++) {
            String temp;
            if (message.length() < 45 * (i+1))
                temp = message.substring(45 * i);
            else
                temp = message.substring(45 * i, 45 * (i + 1));

            System.out.println(String.format("|%15s : %-45s|", "Details", temp.trim()));
        }
        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
