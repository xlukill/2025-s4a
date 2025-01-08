import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static utils.UserUtils.stringToNumbers;

public class S4A_Airlines {

    @Test
    public void S4A() {
        //given
        String input = readFile("src/test/resources/s4a_test_1.txt");
        String output = readFile("src/test/resources/s4a_test_output_1.txt");
        String[] inputTab = input.split("\n");
        long[] QR = stringToNumbers(inputTab[0]);
        long[] amountOfSeats = stringToNumbers(inputTab[1]);
        List<String> queries = new ArrayList<>();
        //when
        for (int i = 2; i < inputTab.length; i++) {
            queries.add(inputTab[i]);
        }
        //then
        String result = Main.S4A_Airlines(QR, amountOfSeats, queries);
        assertEquals(output, result);
    }

    private String readFile(String n) {
        StringBuilder stringBuilder = new StringBuilder();
        try (
                FileInputStream fileInputStream = new FileInputStream(n);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

