package aoc;

public abstract class Puzzle {
    public abstract String getName();
    public abstract String solveA() throws Exception;
    public abstract String solveB() throws Exception;

    private enum Part {
        A, B
    }

    public void solveAndPrint() {
        System.out.println("Solving " + getName() + ":");
        try {
            System.out.print("  A: ");
            System.out.println(solveAndTime(Part.A));
            System.out.print("  B: ");
            System.out.println(solveAndTime(Part.B));
        } catch (Exception e) {
            System.out.println("--> Error: " + e.getMessage());
        }
        System.out.println();
    }
    public String solveAndTime(Part part) throws Exception {
        long startTime = System.nanoTime();
        String result = (part == Part.A) ? solveA() : solveB();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        return result + " (" + duration / 1_000_000 + " ms)";
    }
}
