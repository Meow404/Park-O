package Extra.Location;

public class Location {
    private double xCoordinate;
    private double yCoordinate;
    private double latitude;
    private double longtitude;
    private int zipCode;

    public Location() {
        xCoordinate = 0;
        yCoordinate = 0;
        latitude = 0;
        longtitude = 0;
        zipCode = 0 ;
    }

    public Location(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        zipCode = 0 ;
    }

    public Location(double xCoordinate, double yCoordinate, double Lat, double Long) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.latitude = Lat;
        this.longtitude = Long;
        zipCode = 0 ;
    }

    public double getXCoordinate() { return xCoordinate; }

    public double getYCoordinate() { return yCoordinate; }

    public void setXCoordinate(double xCoordinate) { this.xCoordinate = xCoordinate; }

    public void setYCoordinate(double yCoordinate) { this.yCoordinate = yCoordinate; }

    public double getLat() {
        return latitude;
    }

    public double getLong() { return longtitude; }

    public void setLatitude(double Lat) {
        this.latitude = Lat;
    }

    public void setLongtitude(double Long) {
        this.longtitude = Long;
    }

    public int getLocation() {
        return 0;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public double retDistance(float xCoordinate, float yCoordinate) {
        return 1.0;
    }

    public int retDistanceZIP() {
        return 1;
    }


}
