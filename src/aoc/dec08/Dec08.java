package aoc.dec08;

import aoc.Puzzle;
import aoc.dec07.TachyonManifold;

public class Dec08 extends Puzzle {

    private Playground playground;

    public Dec08() {
        playground = new Playground(getInput());
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            playground.connectNShortestConnections(1000);
            return Integer.toString(playground.productOfNLargestCircuitSizes(3));
        } else {
            playground.connectUntilSingleCircuit();
            return String.format("%.0f", playground.getLastConnectionMade().productOfXCoordinates());
        }
    }

    @Override
    public int getDay() {
        return 8;
    }
}
