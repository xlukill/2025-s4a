import utils.UserUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        StringBuilder builder = new StringBuilder();
        Map<Integer, Plane> planes = new LinkedHashMap();
        for (int i = 0; i < QR[0]; i++) {
            planes.put(i + 1, new Plane(i + 1, Integer.parseInt(String.valueOf(maxPassangers[i])), 0, i + 1));
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
                case "A":
                    planes.get(stringToInt(query[1])).map.put(query[3], query[2]);
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
                    Map<Integer, Plane> subMapPlane = createSubMap(planes, indexFrom, indexTo);

                    int actualT = Integer.parseInt(t);
                    for (int i = 0; i < actualT; i++) {
                        for (Map.Entry<Integer, Plane> entry : subMapPlane.entrySet()) {
//                          Integer k = entry.getKey();
                            Plane p = entry.getValue();
                            if (!p.getMap().containsKey(String.valueOf(actualT - 1))) {
                                continue;
                            }
                            String value = p.getMap().get(String.valueOf(i));
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

    public static Map<Integer, Plane> createSubMap(Map<Integer, Plane> originalMap, int startKey, int endKey) {
        Map<Integer, Plane> subMap = new LinkedHashMap<>();
        boolean inRange = false;

        for (Map.Entry<Integer, Plane> entry : originalMap.entrySet()) {
            //start check
            if (entry.getKey().equals(startKey)) {
                inRange = true;
            }
            if (inRange) {
                subMap.put(entry.getKey(), entry.getValue());
            }
            //end check
            if (entry.getKey().equals(endKey)) {
                break;
            }
        }
        return subMap;
    }
}
