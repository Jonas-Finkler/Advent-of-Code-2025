package aoc;

public abstract class Puzzle {
    public abstract String getName();
    public abstract String solveA() throws Exception;
    public abstract String solveB() throws Exception;

    public void solveAndPrint() {

        System.out.println("Solving " + getName() + ":");
        try {
            System.out.print("  A: ");
            System.out.println(solveA());
            System.out.print("  B: ");
            System.out.println(solveB());
        } catch (Exception e) {
            System.out.println("--> Error: " + e.getMessage());
        }
        System.out.println();
    }
}
