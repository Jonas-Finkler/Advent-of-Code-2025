package aoc.dec06;

import aoc.Puzzle;

public class Dec06 extends Puzzle {

    final MathSheet sheet;

    public Dec06() {
        sheet = new MathSheet(getInput());
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            return sheet.calcFinalResult().toString();
        } else {
            return "";
        }
    }

    @Override
    public int getDay() {
        return 6;
    }
}
