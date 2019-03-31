package TrafficIncidents.IncidentTypes;

import Extra.Location.Location;

public interface FacilityType {

    String retType();
    String retMessage();
    Location retLocation();
    void print();
}
