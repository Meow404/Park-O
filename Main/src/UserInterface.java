import CarPark.*;
import CyclePark.*;
import Extra.Location.Location;
import TrafficIncidents.*;
import TrafficIncidents.IncidentTypes.IncidentTypes;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;


public class UserInterface {


    static CarParkManager carParkManager = new CarParkManager();

    public static void main(String args[]) {
        Location myLocation = new Location(1.342678, 103.757214);
        Double constraint = 0.5;
        Scanner scanner = new Scanner(System.in);
        int opt ;
        do {
            System.out.print("\n1. Find Neighbouring Car Parks\n2. Find Neighbouring Cycle Parks\n3. Find Traffic Incidents\n4. Exit\n\nYour Choice ~ ");
            opt = scanner.nextInt();
            long startTime = System.currentTimeMillis();
            switch (opt) {
                case 1:
                    runCarParkManager(myLocation, constraint);
                    break;
                case 2:
                    runCycleParkManager(myLocation, constraint);
                    break;
                case 3:
                    runTrafficIncidentManager(myLocation);
                    break;
                case 4:
                    exit(0);
                default:
                    System.out.print("\nInvalid Input");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("\n\nExecution Time : " + (endTime - startTime));
        } while (opt <= 3);
    }

    private static void runCarParkManager(Location curLocation, double constraint) {
        ArrayList<CarPark> carParks = carParkManager.returnUpdatedCarParkList(curLocation, constraint);
        if (carParks.size() != 0) {
            for (CarPark carPark : carParks)
                carPark.print();
        } else {
            System.out.println("\n\nInsufficient Constraints");
        }

    }

    private static void runCycleParkManager(Location curLocation, double constraint) {
        ArrayList<CyclePark> cycleParks = CycleParkManager.returnCycleParksInVicinity(curLocation, constraint);
        if (cycleParks.size() != 0) {
            for (CyclePark cyclePark : cycleParks)
                cyclePark.print();
        } else {
            System.out.println("\n\nInsufficient Constraints");
        }

    }

    private static void runTrafficIncidentManager(Location curLocation) {
        ArrayList<IncidentTypes> trafficIncidents = TrafficIncidentManager.returnTrafficIncidents(curLocation);
        if (trafficIncidents.size() != 0) {
            for (IncidentTypes Incident : trafficIncidents)
                Incident.print();
        } else {
            System.out.println("\n\nInsufficient Constraints");
        }


    }
}


