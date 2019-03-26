package Facilities;

import Extra.Location.Location;

public class Facilities {
    private boolean hawkerCenter;
    private boolean shoppingMall;
    private boolean cinema;
    private boolean gym;
    private boolean bar;
    private boolean club;
    private boolean zoo;
    private String name;
    private Location location;

    public Facilities() {
        hawkerCenter = false;
        shoppingMall = false;
        cinema = false;
        gym = false;
        bar = false;
        club = false;
        zoo = false;
        name = "";
        location = new Location();
    }

    public void print(){
        System.out.println(String.format("|%15s : %-45s|","Hawker Center",hawkerCenter == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Shopping Mall",shoppingMall == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Cinema",cinema == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Gym",gym == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Bar",bar == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Club",club == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Zoo",zoo == true ? "True" : "False"));
        System.out.println(String.format("|%15s : %-45s|","Name", name));
        System.out.println(String.format("|%15s : %-45s|","X-Coordinate",location.getXCoordinate()));
        System.out.println(String.format("|%15s : %-45s|","Y-Coordinate",location.getYCoordinate()));
    }

    public boolean getHawkerCenter() {
        return hawkerCenter;
    }
    public void setHawkerCenter( boolean newHawkerCenter ) {
        hawkerCenter = newHawkerCenter;
    }
    public boolean getShoppingMall(){
        return shoppingMall;
    }
    public void setShoppingMall( boolean newShoppingMall ){
        shoppingMall = newShoppingMall;
    }
    public boolean getCinema(){
        return cinema;
    }
    public void setCinema( boolean newCinema ){
        cinema = newCinema;
    }
    public boolean getGym(){
        return gym;
    }
    public void setGym( boolean newGym ){
        gym = newGym;
    }
    public boolean getBar(){
        return bar;
    }
    public void setBar( boolean newBar ){
        bar = newBar;
    }
    public boolean getClub(){
        return club;
    }
    public void setClub( boolean newClub ){
        club = newClub;
    }
    public boolean getZoo(){
        return zoo;
    }
    public void setZoo( boolean newZoo ){
        zoo = newZoo;
    }
    public String getName(){
        return name;
    }
    public void setName( String newName ) {
        name = newName;
    }
    public Location getLocation(){
        return location;
    }
    public void setLocation( Location location ){
        this.location = location;
    }

}

