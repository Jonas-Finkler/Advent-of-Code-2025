package aoc.dec07;

import aoc.Puzzle;

public class Dec07 extends Puzzle {

    final TachyonManifold manifold;

    public Dec07() {
        manifold = new TachyonManifold(getInput());
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            return Integer.toString(manifold.propagateFullAndCountSplits());
        } else {
            return Long.toString(manifold.countQuantumPaths());
        }
    }

    @Override
    public int getDay() {
        return 7;
    }
}
