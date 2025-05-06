import utils.UserUtils;

import java.util.*;

import static utils.UserUtils.*;

/*
q - queries
r - route
t - measurement
 */
public class Main {
    public static void main(String[] args) {
        long[] QR = stringToNumbers(getValueFromUser()); // eg. 5 7
        long[] maxPassengers = stringToNumbers(getValueFromUser()); // eg. 1 2 3 2 4
        /*
        Store Eg.
        Q 1 5 2
        Q 2 3 2
        C 2 3
        P 3 5 3
        Q 2 4 4
        A 2 5 6
        Q 1 5 8
         */
        List<String> queries = new ArrayList<>();
        for (int i = 0; i < QR[1]; i++) {
            queries.add(getValueFromUser());
        }
        System.out.println(S4A_Airlines(QR, maxPassengers, queries));
    }

    public static String S4A_Airlines(long[] QR, long[] maxPassangers, List<String> queries) {
        int maxRoute = (int) QR[0];
        StringBuilder builder = new StringBuilder();
        Map<Integer, Plane> planes = new LinkedHashMap();
        Map<Integer, Route> routes = new LinkedHashMap();
        Map<Integer,Integer> linked = new LinkedHashMap<>();
        for (int i = 1; i <= QR[0]; i++) {
            Route route = new Route(i);
            routes.put(i,route);
            planes.put(i, new Plane(i, Integer.parseInt(String.valueOf(maxPassangers[i-1])), 0,route));
            linked.put(i,i);
        }
        for (String n : queries) {
            int sum = 0;
            String[] query = UserUtils.queries(n);
            switch (query[0]) {
                /*
                eg.
                ---
                QUERY -   P 3 5 3
                INDEX -   0 1 2 3
                ---
                QUERY -   A 2 5 6
                INDEX -   0 1 2 3
                ---
                 */
                case "P":
                    planes.get(stringToInt(query[1])).map.put(query[3], query[2]);
                    break;
                case "A":
                    maxRoute++;
//                    routes.put(maxRoute, new Route(maxRoute));
                    planes.get(stringToInt(query[1])).map.put(query[3], query[2]);
                    linked.put(Integer.valueOf(query[1]),maxRoute);
                    break;
                /*
                eg. QUERY - C 2 3
                    INDEX - 0 1 2
                 */
                case "C":
                    planes.get(stringToInt(query[1])).map.remove(query[2]);
                    break;
                    /*
                      eg. QUERY - Q 1 5 8
                          INDEX - 0 1 2 3
                     */
                case "Q":
                    String t = query[3];
                    int indexFrom = stringToInt(query[1]);
                    int indexTo = stringToInt(query[2]);
                    Map<Integer,Integer> subMapLinked = createSubMap(linked,indexFrom,indexTo);

                    int actualT = Integer.parseInt(t);
                    for (int i = 0; i < actualT; i++) {
                        for (Map.Entry<Integer, Integer> entry : subMapLinked.entrySet()) {
                            int planeIndex = entry.getKey();
                            Plane plane = planes.get(planeIndex);
                            if (!plane.getMap().containsKey(String.valueOf(actualT-1))) {
                                continue;
                            }
                            String value = plane.getMap().get(String.valueOf(i));
                            if (value != null) {
                                sum += Integer.parseInt(value);
                            }
                        }
                    }
                    builder.append(sum).append("\n");
            }
        }
        return builder.toString();
    }
    public static Map<Integer, Integer> createSubMap(Map<Integer, Integer> originalMap, int startKey, int endKey) {
        Map<Integer, Integer> subMap = new LinkedHashMap<>();
        boolean inRange = false;

        for (Map.Entry<Integer, Integer> entry : originalMap.entrySet()) {
            //start check
            if (entry.getValue().equals(startKey)) {
                inRange = true;
            }
            if (inRange) {
                subMap.put(entry.getKey(), entry.getValue());
            }
            //end check
            if (entry.getValue().equals(endKey)) {
                break;
            }
        }
        return subMap;
    }
}
