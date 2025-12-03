package aoc.dec01;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import aoc.Puzzle;

public class Dec01 extends Puzzle {

    @Override
    public String getName(){
        return "December 01";
    }

    @Override
    public String solveA() throws Exception {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

            for (Integer steps: parseInput(new File("inputs/01.txt"))) {
                safe.turn(steps);
                if (safe.getState() == 0) {
                    zeroCount++;
                }
            }
            return Integer.toString(zeroCount);
    }

    @Override
    public String solveB() throws Exception {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

            for (Integer steps: parseInput(new File("inputs/01.txt"))) {
                zeroCount += safe.turnAndGetZeroCount(steps);
            }

            return Integer.toString(zeroCount);
    }

    private Iterable<Integer> parseInput(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        return new Iterable<>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<>() {

                    @Override
                    public boolean hasNext() {
                        return scanner.hasNextLine();
                    }

                    @Override
                    public Integer next() {
                        String line = scanner.nextLine();
                        int sign = line.charAt(0) == 'L' ? -1 : 1;
                        int steps = Integer.parseInt(line.substring(1).trim());
                        return sign * steps;
                    }
                };
            }

        };
    }
}
