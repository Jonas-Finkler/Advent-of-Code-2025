package aoc.dec02;

import aoc.Puzzle;
import java.util.Arrays;
import java.math.BigInteger;

public class Dec02 extends Puzzle {

    private IdRange[] ranges;

    public Dec02() {
        ranges = parseInput();
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String solve(Part part) {
        return solve(part == Part.B);
    }

    private String solve(boolean useExtendedDefinition) {
        BigInteger sum = BigInteger.ZERO;
        for (IdRange range : ranges) {
            sum = sum.add(range.sumOfInvalidIds(useExtendedDefinition));
        }
        return sum.toString();
    }

    private IdRange[] parseInput() {
        String input = getInput();
        String[] rangeStrs = input.split(",");
        return Arrays.stream(rangeStrs)
                .map((String s) -> new IdRange(s))
                .toArray(n -> new IdRange[n]);
    }
}
