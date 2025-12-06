package aoc.dec04;

public class PaperRollPlan {

    // where the paper is
    private boolean[][] map;
    private int width, height;

    public PaperRollPlan(String map) {
        String[] lines = map.trim().split("\n");
        height = lines.length;
        width = lines[0].length();
        this.map = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.map[y][x] = (lines[y].charAt(x) == '@');
            }
        }
    }

    public boolean get(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return map[y][x];
    }

    public int nNeighbors(int x, int y) {
        int count = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                if (get(x + dx, y + dy)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isAccessible(int x, int y) {
        if (!get(x, y)) {
            return false;
        }
        return nNeighbors(x, y) < 4;
    }

    public int countAccessibleRolls() {
        int count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isAccessible(x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int removeAccessibleRolls() {
        boolean[][] removable = new boolean[height][width];
        int removedCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isAccessible(x, y)) {
                    removable[y][x] = true;
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (removable[y][x]) {
                    map[y][x] = false;
                    removedCount++;
                }
            }
        }
        return removedCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(get(x, y) ? '@' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
