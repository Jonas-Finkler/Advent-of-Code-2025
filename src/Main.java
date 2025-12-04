import aoc.Puzzle;
import aoc.dec01.Dec01;
import aoc.dec02.Dec02;
import aoc.dec03.Dec03;

public class Main {
    public static void main(String[] args) {

        Puzzle[] puzzles = {
                new Dec01(),
                new Dec02(),
                new Dec03()
        };

        for (Puzzle puzzle : puzzles) {
            puzzle.solveAndPrint();
        }
    }
}