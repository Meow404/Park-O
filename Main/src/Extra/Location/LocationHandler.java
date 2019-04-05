package Extra.Location;

public abstract class LocationHandler {
    protected Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationHandler(Location location) {
        this.location = location;
    }
}
