package aoc.dec02;
import aoc.Puzzle;
import java.io.IOException;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;

public class Dec02 extends Puzzle {

    @Override
    public String getName(){
        return "December 02";
    }

    @Override
    public String solveA() throws Exception {
        return solve(false);
    }

    @Override
    public String solveB() throws Exception {
        return solve(true);
    }

    private String solve(boolean useExtendedDefinition) throws Exception {
        IdRange[] ranges = parseInput();
        BigInteger sum = BigInteger.ZERO;
        for (IdRange range : ranges) {
            sum = sum.add(range.sumOfInvalidIds(useExtendedDefinition));
        }
        return sum.toString();
    }

    private IdRange[] parseInput() throws IOException {
        String input = Files.readString(Paths.get("inputs/02.txt"));
        String[] rangeStrs = input.split(",");
        IdRange[] ranges = Arrays.stream(rangeStrs)
                .map((String s) -> new IdRange(s))
                .toArray(n -> new IdRange[n]);
        return ranges;
    }
}
