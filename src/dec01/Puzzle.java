package dec01;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
public class Puzzle {

    public static void solveA() {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

        try {
            for (Integer steps: parseInput(new File("inputs/01.txt"))) {
                safe.turn(steps);
                if (safe.getState() == 0) {
                    zeroCount++;
                }
            }

            System.out.println("Solution for Dec 01 A: " + zeroCount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void solveB() {
        Safe safe = new Safe(50, 100);

        int zeroCount = 0;

        try {
            for (Integer steps: parseInput(new File("inputs/01.txt"))) {
                zeroCount += safe.turnAndGetZeroCount(steps);
            }

            System.out.println("Solution for Dec 01 B: " + zeroCount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Iterable<Integer> parseInput(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {

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
