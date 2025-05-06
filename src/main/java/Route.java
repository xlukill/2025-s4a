import java.util.TreeSet;

public class Route {
    private final long id;
    private final TreeSet<Long> activeDays = new TreeSet<>();
    private Plane assignedPlane;

    public Route(long id) {
        this.id = id;
    }

    public void addActiveDay(long day) {
        activeDays.add(day);
    }

    public void removeActiveDay(long day) {
        activeDays.remove(day);
    }

    public boolean isScheduledOn(long day) {
        return activeDays.contains(day);
    }

    public void assignPlane(Plane plane) {
        this.assignedPlane = plane;
    }

    public Plane getAssignedPlane() {
        return assignedPlane;
    }
}
