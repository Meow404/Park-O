package CyclePark;

import Extra.Location.Location;

public class CyclePark {
    private String description;
    private Location location;
    private String rackType;
    private long rackCount;
    private boolean shelterIndicator;

    public CyclePark(String description, double xCor, double yCor, String rackType, long rackCount, boolean shelterIndicator) {
        this.description = description;
        this.location = new Location(xCor, yCor);
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
        String locationString = Double.toString(location.getXCoordinate()) + ", " + Double.toString(location.getYCoordinate());
        System.out.println(String.format("|%15s : %-45s|", "Location", locationString));
    }
}
