package CyclePark;

import Extra.Location.Location;
import Extra.Location.LocationHandler;

public class CyclePark extends LocationHandler {
    private String description;
    private String rackType;
    private long rackCount;
    private boolean shelterIndicator;


    public CyclePark(String description, double xCor, double yCor, String rackType, long rackCount, boolean shelterIndicator) {
        super(new Location(xCor, yCor));
        this.description = description;
        this.rackType = rackType;
        this.rackCount = rackCount;
        this.shelterIndicator = shelterIndicator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRackType() {
        return rackType;
    }

    public void setRackType(String rackType) {
        this.rackType = rackType;
    }

    public long getRackCount() {
        return rackCount;
    }

    public void setRackCount(long rackCount) {
        this.rackCount = rackCount;
    }

    public boolean isShelterIndicator() {
        return shelterIndicator;
    }

    public void setShelterIndicator(boolean shelterIndicator) {
        this.shelterIndicator = shelterIndicator;
    }

    public void print() {
        System.out.println(String.format("\n|%15s : %-45s|", "Description", description));
        System.out.println(String.format("|%15s : %-45s|", "Rack Type", rackType));
        System.out.println(String.format("|%15s : %-45d|", "Rack Count", rackCount));
        System.out.println(String.format("|%15s : %-45s|", "Shelter", shelterIndicator));
    }
}
