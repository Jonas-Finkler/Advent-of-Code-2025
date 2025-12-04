package aoc.dec03;

import aoc.Puzzle;
import aoc.dec01.Dec01;

import java.math.BigInteger;
import java.util.Arrays;

public class Dec03 extends Puzzle {

    BatteryBank[] banks;

    @Override
    public int getDay() {
        return 3;
    }

    public Dec03() {
        banks = parseInput();
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            return sumBatteryJoltages(2).toString();
        } else {
            return sumBatteryJoltages(12).toString();
        }
    }

    private BigInteger sumBatteryJoltages(int nBatteries) {
        BigInteger sum = BigInteger.ZERO;
        for (BatteryBank bank : banks) {
            sum = sum.add(bank.maxJoltate(nBatteries));
        }
        return sum;
    }

    private BatteryBank[] parseInput() {
        String input = getInput().trim();
        String[] lines = input.split("\n");
        BatteryBank[] banks = new BatteryBank[lines.length];
        return Arrays.stream(lines).map(l ->
                new BatteryBank(l.chars().map(c -> c - '0').toArray())
        ).toArray(n -> new BatteryBank[n]);

    }
}
