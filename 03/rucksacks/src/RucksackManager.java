import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RucksackManager {
    List<Rucksack> rucksacks = new ArrayList<>();

    public RucksackManager(String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                rucksacks.add(new Rucksack(line));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getPriority(Character c) {
        if (c == null)
            return 0;
        if (Character.isLowerCase(c))
            return c - 96;
        return c - 38;
    }

    public int evaluateRucksacks() {
        int result = 0;

        for (var r : rucksacks) {
            result += getPriority(r.findItemInBoth());
        }

        return result;
    }

    public int evaluateGroups() {
        int result = 0;
        int count = 0;
        Rucksack r1 = null;
        Rucksack r2 = null;
        Rucksack r3 = null;

        for (var r : rucksacks) {
            if (count % 3 == 0)
                r1 = r;
            if (count % 3 == 1)
                r2 = r;
            if (count % 3 == 2) {
                r3 = r;
                String r1r2 = r1.getIntersection(r2);
                String r2r3 = r2.getIntersection(r3);
                Set<Character> set1 = new HashSet<Character>();
                Set<Character> set2 = new HashSet<Character>();

                for(char c : r1r2.toCharArray()) {
                    set1.add(c);
                }
                for(char c : r2r3.toCharArray()) {
                    set2.add(c);
                }
                set1.retainAll(set2);

                result += getPriority(set1.stream().findFirst().get());
            }

            count++;
        }

        return result;
    }
}
