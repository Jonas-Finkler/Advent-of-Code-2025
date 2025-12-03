package aoc.dec01;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import aoc.Puzzle;

public class Dec01 extends Puzzle {

    @Override
    public int getDay() {
        return 1;
    }


    @Override
    public String solve(Part part) {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

            for (Integer steps: parseInput()) {
                if (part == Part.A) {
                    safe.turn(steps);
                    if (safe.getState() == 0) {
                        zeroCount++;
                    }
                } else {
                    zeroCount += safe.turnAndGetZeroCount(steps);
                }
            }
            return Integer.toString(zeroCount);
    }

    private int[] parseInput() {
        String input = getInput();
        String[] lines = input.split("\n");
        int[] steps = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int sign = line.charAt(0) == 'L' ? -1 : 1;
            steps[i] = sign * Integer.parseInt(line.substring(1).trim());
        }
        return steps;
    }

}
