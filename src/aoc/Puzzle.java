package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Puzzle {

    public abstract String solve(Part part);
    public abstract int getDay();

    private String input;

    public enum Part {
        A, B
    }

    public Puzzle() {
        try {
            input = Files.readString(Paths.get("inputs/" + String.format("%02d", getDay()) + ".txt"));
        } catch (IOException e) {
            System.out.println("Missing input file for " + getDay());
            System.exit(1);
        }
    }

    public String getName() {
        return "Day " + getDay();
    }

    protected String getInput() {
        return input;
    }

    public void solveAndPrint() {
        System.out.println("Solving " + getName() + ":");
        System.out.println("  A: " + solveAndTime(Part.A));
        System.out.println("  B: " + solveAndTime(Part.B));
        System.out.println();
    }
    public String solveAndTime(Part part) {
        long startTime = System.nanoTime();
        String result = solve(part);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        return result + " (" + duration / 1_000_000 + " ms)";
    }
}
