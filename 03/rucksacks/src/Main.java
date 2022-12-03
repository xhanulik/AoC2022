public class Main {
    public static void main(String[] args) {
        RucksackManager manager = new RucksackManager("inputs.txt");
        System.out.print(manager.evaluateRucksacks());
        System.out.print("\n");
        System.out.print(manager.evaluateGroups());
    }
}