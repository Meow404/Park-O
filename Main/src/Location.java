import java.util.*;
import java.math.*;
import java.lang.*;

public class Location {
    private float xCoordinate;
    private float yCoordinate;
    private int zipCode;

    public double getXCoordinate() {
        return xCoordinate;
    }
    public double getYCoordinate() {
        return yCoordinate;
    }
    public void setXCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public void setYCoordinate(float yCoordinate) {
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
