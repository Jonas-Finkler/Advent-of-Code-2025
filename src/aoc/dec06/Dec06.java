package aoc.dec06;

import aoc.Puzzle;

public class Dec06 extends Puzzle {


    public Dec06() {
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            MathSheet sheet = new MathSheet(getInput());
            return sheet.calcFinalResult().toString();
        } else {
            MathSheet sheet = new MathSheet(getInput(), true);
            return sheet.calcFinalResult().toString();
        }
    }

    @Override
    public int getDay() {
        return 6;
    }
}
