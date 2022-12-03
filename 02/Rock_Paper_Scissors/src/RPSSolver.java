import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RPSSolver {

    public List<Character> enemyList = new ArrayList<Character>();
    public List<Character> myList = new ArrayList<Character>();

    private void parseLine(String line) {
        if (line.length() != 3 || line.charAt(1) != ' ')
            return;
        if (!"ABC".contains(String.valueOf(line.charAt(0)))
                || !"XYZ".contains(String.valueOf(line.charAt(2))))
            return;

        enemyList.add(line.charAt(0));
        myList.add(line.charAt(2));
    }

    public void readFile(String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                parseLine(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RPSSolver(String path) {
        readFile(path);
    }

    private int evaluatePair(Character enemy, Character my) {
        int result = 0;

        switch (my) {
            case 'X' -> result += 1;
            case 'Y' -> result += 2;
            case 'Z' -> result += 3;
        }

        switch (enemy) {
            case 'A':
                switch (my) {
                    case 'X' -> result += 3;
                    case 'Y' -> result += 6;
                }
                break;
            case 'B':
                switch (my) {
                    case 'Y' -> result += 3;
                    case 'Z' -> result += 6;
                }
                break;
            case 'C':
                switch (my) {
                    case 'Z' -> result += 3;
                    case 'X' -> result += 6;
                }
        }

        return result;
    }

    public int evaluate1Game() {
        int result = 0;
        for (int i = 0; i < enemyList.size(); i++) {
            Character enemy = enemyList.get(i);
            Character my = myList.get(i);

            result += evaluatePair(enemy, my);
        }
        return result;
    }

    private int findAndEvaluatePair(Character enemy, Character my) {
        int result = 0;

        switch (my) {
            case 'X':
                switch (enemy) {
                    case 'A' -> result += 3;
                    case 'B' -> result += 1;
                    case 'C' -> result += 2;
                }
                break;
            case 'Y':
                result += 3;
                switch (enemy) {
                    case 'A' -> result += 1;
                    case 'B' -> result += 2;
                    case 'C' -> result += 3;
                }
                break;
            case 'Z':
                result += 6;
                switch (enemy) {
                    case 'A' -> result += 2;
                    case 'B' -> result += 3;
                    case 'C' -> result += 1;
                }
                break;
        }

        return result;
    }

    public int evaluate2Game() {
        int result = 0;
        for (int i = 0; i < enemyList.size(); i++) {
            Character enemy = enemyList.get(i);
            Character my = myList.get(i);

            result += findAndEvaluatePair(enemy, my);
        }
        return result;
    }
}
