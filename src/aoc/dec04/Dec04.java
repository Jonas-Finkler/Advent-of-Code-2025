package aoc.dec04;

import aoc.Puzzle;

public class Dec04 extends Puzzle {

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public String solve(Part part) {
        PaperRollPlan plan = new PaperRollPlan(getInput());
        if (part == Part.A) {
            return Integer.toString(plan.countAccessibleRolls());
        } else {
            int totalRemoved = 0;
            int removedThisRound = -1;
            while (removedThisRound != 0) {
                removedThisRound = plan.removeAccessibleRolls();
                totalRemoved += removedThisRound;
            }

            return Integer.toString(totalRemoved);

        }
    }

}
