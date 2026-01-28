import aoc.Puzzle;
import aoc.dec01.Dec01;
import aoc.dec02.Dec02;
import aoc.dec03.Dec03;
import aoc.dec04.Dec04;
import aoc.dec05.Dec05;
import aoc.dec06.Dec06;
import aoc.dec07.Dec07;
import aoc.dec08.Dec08;

public class Main {
    public static void main(String[] args) {

        Puzzle[] puzzles = {
                new Dec01(),
                new Dec02(),
                new Dec03(),
                new Dec04(),
                new Dec05(),
                new Dec06(),
                new Dec07(),
                new Dec08()
        };

        for (Puzzle puzzle : puzzles) {
            puzzle.solveAndPrint();
        }
    }
}