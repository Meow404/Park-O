package TrafficIncidents.IncidentTypes;

import Extra.Location.Location;

public interface IncidentTypes {

    String retType();
    String retMessage();
    Location retLocation();
    void print();
}
