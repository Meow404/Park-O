package Extra.Location;

public class Location {
    private double xCoordinate;
    private double yCoordinate;
    private int zipCode;

    public Location() {
        xCoordinate = 0;
        yCoordinate = 0;
        zipCode = 0 ;
    }

    public Location(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        zipCode = 0 ;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
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
