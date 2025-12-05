package aoc.dec01;
import aoc.Puzzle;

public class Dec01 extends Puzzle {

    private int[] steps;

    public Dec01() {
        steps = parseInput();
    }

    @Override
    public int getDay() {
        return 1;
    }


    @Override
    public String solve(Part part) {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

            for (Integer step: steps) {
                if (part == Part.A) {
                    safe.turn(step);
                    if (safe.getState() == 0) {
                        zeroCount++;
                    }
                } else { // Part B
                    zeroCount += safe.turnAndGetZeroCount(step);
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
