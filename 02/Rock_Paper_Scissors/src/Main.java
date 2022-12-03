public class Main {
    public static void main(String[] args) {
        String inputPath = "inputs.txt";

        RPSSolver solver = new RPSSolver(inputPath);
        int result1 = solver.evaluate1Game();
        System.out.print(result1);
        System.out.print("\n");
        int result2 = solver.evaluate2Game();
        System.out.print(result2);
    }
}