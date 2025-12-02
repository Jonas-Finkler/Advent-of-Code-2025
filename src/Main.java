import aoc.dec01.Dec01;
import aoc.Puzzle;
import aoc.dec01.Safe;

public class Main {
    public static void main(String[] args) {

        Puzzle[] puzzles = {
            new Dec01(),
        };

        for (Puzzle puzzle : puzzles) {
            puzzle.solveAndPrint();
        }
    }
}