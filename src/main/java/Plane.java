import java.util.HashMap;

public class Plane {
    int id;
    int maxCapacity;
    int occupiedSeats;
    int assignedRoute;

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    HashMap<String, String> map = new HashMap<>();

    public Plane(int id, int maxCapacity, int occupiedSeats, int assignedRoute) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.occupiedSeats = occupiedSeats;
        this.assignedRoute = assignedRoute;
        for (long i = 0; i < 1000; i++) {
            map.put(String.valueOf(i), String.valueOf(maxCapacity));
        }
    }

    public Plane() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public int getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(int assignedRoute) {
        this.assignedRoute = assignedRoute;
    }
}
