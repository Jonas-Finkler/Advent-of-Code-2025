package aoc.dec07;

import com.jogamp.common.util.IntObjectHashMap;

import java.util.LinkedList;

public class TachyonManifold {

    final TachyonManifoldElement[][] manifold;
    private int width;
    private int height;

    private int currentStep = 0;

    public TachyonManifold(String layout) {
        String[] lines = layout.split("\n");
        width = lines[0].length();
        height = lines.length;
        manifold = new TachyonManifoldElement[height][width];
        LinkedList<Position> beamPosList = new LinkedList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                manifold[y][x] = TachyonManifoldElement.fromChar(lines[y].charAt(x));
            }
        }
    }

    private static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public TachyonManifoldElement get(TachyonManifoldElement[][] manifold) {
            return manifold[y][x];
        }
        public void set(TachyonManifoldElement[][] manifold, TachyonManifoldElement el) {
            manifold[y][x] = el;
        }

        Position down() {
            return new Position(x, y + 1);
        }
        Position left() {
            return new Position(x - 1, y);
        }
        Position right() {
            return new Position(x + 1, y);
        }

    }

    public int propagateFullAndCountSplits() {
        int totalSplits = 0;
        while (currentStep < height - 2) {
            totalSplits += propagateStepAndCountSplits();
        }
        return totalSplits;
    }

    public long countQuantumPaths() {
        long totalPaths = 0;
        for (int x = 0; x < width; x++) {
            Position pos = new Position(x, height - 2);
            TachyonManifoldElement el = pos.get(manifold);
            if (el instanceof TachyonManifoldElement.BeamLike beam) {
                totalPaths += beam.getNTimelines();
            }
        }
        return totalPaths;
    }

    // returns how often the beam is split
    public int propagateStepAndCountSplits() {
        if (currentStep >= height - 1) {
            return 0;
        }

        int nSplits = 0;
        for (int x = 0; x < width; x++) {
            Position pos = new Position(x, currentStep);
            TachyonManifoldElement el = pos.get(manifold);
            if (el instanceof TachyonManifoldElement.BeamLike beam) {

                Position down = pos.down();
                switch (down.get(manifold)) {
                    case TachyonManifoldElement.Splitter splitter -> {
                        nSplits++;
                        Position left = down.left();
                        Position right = down.right();
                        if (left.x >= 0) {
                            left.set(manifold, beam.propagate(left.get(manifold)));
                        }
                        if (right.x < width) {
                            right.set(manifold, beam.propagate(right.get(manifold)));
                        }
                    }
                    default -> {
                        down.set(manifold, beam.propagate(down.get(manifold)));
                    }
                }
            }
        }
        currentStep++;
        return nSplits;
    }
}
