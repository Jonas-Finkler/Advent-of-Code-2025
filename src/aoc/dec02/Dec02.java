package aoc.dec02;

import aoc.Puzzle;
import java.util.Arrays;
import java.math.BigInteger;

public class Dec02 extends Puzzle {

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String solve(Part part) {
        return solve(part == Part.B);
    }

    private String solve(boolean useExtendedDefinition) {
        IdRange[] ranges = parseInput();
        BigInteger sum = BigInteger.ZERO;
        for (IdRange range : ranges) {
            sum = sum.add(range.sumOfInvalidIds(useExtendedDefinition));
        }
        return sum.toString();
    }

    private IdRange[] parseInput() {
        String input = getInput();
        String[] rangeStrs = input.split(",");
        IdRange[] ranges = Arrays.stream(rangeStrs)
                .map((String s) -> new IdRange(s))
                .toArray(n -> new IdRange[n]);
        return ranges;
    }
}
