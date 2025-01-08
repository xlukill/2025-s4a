package utils;

import java.util.Scanner;

public class UserUtils {
    public static String getValueFromUser() {
        Scanner reader = new Scanner(System.in);
        String n = reader.nextLine();
        return n;
    }

    public static long[] stringToNumbers(String input) {
        String[] numbers = input.split(" ");

        long[] tabNumbers = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            tabNumbers[i] = Integer.parseInt(numbers[i]);
        }
        return tabNumbers;
    }

    public static String[] queries(String input) {
        return input.split(" ");
    }

    public static int stringToInt(String n) {
        return Integer.parseInt(n);
    }

}
