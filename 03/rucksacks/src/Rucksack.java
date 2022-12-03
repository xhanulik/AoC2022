import java.util.HashSet;
import java.util.Set;

public class Rucksack {

    String allItems;
    String items1;
    String items2;

    public Rucksack(String items) {
        if (items.isEmpty() || items.length() % 2 != 0)
            throw new RuntimeException("Invalid arguments");
        this.allItems = items;
        this.items1 = items.substring(0, items.length() / 2);
        this.items2 = items.substring(items.length() / 2, items.length());
        assert(items1.length() == items2.length());
    }

    public Character findItemInBoth() {
        if (items1.isEmpty() || items2.isEmpty())
            return null;

        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();

        for(char c : items1.toCharArray()) {
            set1.add(c);
        }
        for(char c : items2.toCharArray()) {
            set2.add(c);
        }
        set1.retainAll(set2);

        if (set1.size() != 1)
            return null;

        return set1.stream().findFirst().get();
    }

    public String getIntersection(Rucksack second) {
        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();

        for(char c : allItems.toCharArray()) {
            set1.add(c);
        }
        for(char c : second.allItems.toCharArray()) {
            set2.add(c);
        }
        set1.retainAll(set2);

        StringBuilder result = new StringBuilder();
        for (var s : set1)
            result.append(s);
        return result.toString();
    }
}
