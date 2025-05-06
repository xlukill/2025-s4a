import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class Plane {
    int id;
    int maxCapacity;
    int occupiedSeats;
    Route assignedRoute;
    long placeAvailability;

    public LinkedHashMap<String, String> getMap() {
        return map;
    }

    LinkedHashMap<String, String> map = new LinkedHashMap<>();

    public Plane(int id, int maxCapacity, int occupiedSeats, Route assignedRoute) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.occupiedSeats = occupiedSeats;
        this.assignedRoute = assignedRoute;
        for (long i = 0; i < 1000; i++) {
            map.put(String.valueOf(i), String.valueOf(maxCapacity));
        }
        placeAvailability = -1;
    }
}



