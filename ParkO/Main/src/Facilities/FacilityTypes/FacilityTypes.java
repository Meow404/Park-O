package Facilities.FacilityTypes;

import Extra.Location.Location;
import Extra.Location.LocationHandler;

public interface FacilityTypes {
    String retType();
    String retName();
    String retAddress();
    Location retLocation();
    void print();
}
