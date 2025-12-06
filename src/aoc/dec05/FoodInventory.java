package aoc.dec05;

import java.math.BigInteger;
import java.util.ArrayList;

public class FoodInventory {

    public static class IdRange {
        final BigInteger start;
        final BigInteger end;

        public IdRange(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

        public IdRange(String rangeStr) {
            String[] parts = rangeStr.trim().split("-");
            this.start = new BigInteger(parts[0]);
            this.end = new BigInteger(parts[1]);
        }

        public BigInteger getStart() {
            return start;
        }

        public BigInteger getEnd() {
            return end;
        }

        // check if two ranges can be merged
        public boolean canBeMergedWith(IdRange other) {
            return (this.end.compareTo(other.start) >= 0 && this.start.compareTo(other.end) <= 0)
                    || (other.end.compareTo(this.start) >= 0 && other.start.compareTo(this.end) <= 0);
        }

        public IdRange mergeWith(IdRange other) {
            BigInteger newStart = this.start.min(other.start);
            BigInteger newEnd = this.end.max(other.end);
            return new IdRange(newStart, newEnd);
        }

        public BigInteger nElements() {
            return end.subtract(start).add(BigInteger.ONE);
        }

        public boolean contains(BigInteger id) {
            return id.compareTo(start) >= 0 && id.compareTo(end) <= 0;
        }
    }

    // Always merged, i.e., non-overlapping ranges
    private IdRange[] freshIngredientRanges;

    public FoodInventory(IdRange[] ranges) {
        this.freshIngredientRanges = ranges;
        mergeRanges();
    }

    public boolean isFresh(BigInteger id) {
        for (IdRange range : freshIngredientRanges) {
            if (range.contains(id)) {
                return true;
            }
        }
        return false;
    }

    public BigInteger nFreshIds() {
        BigInteger sum = BigInteger.ZERO;
        for (IdRange range : freshIngredientRanges) {
            sum = sum.add(range.nElements());
        }
        return sum;
    }

    private void mergeRanges() {
        // sort by start
        java.util.Arrays.sort(freshIngredientRanges, (a, b) -> a.getStart().compareTo(b.getStart()));

        ArrayList<IdRange> mergedRanges = new ArrayList<>();

        IdRange currentRange = freshIngredientRanges[0];
        for (int i = 1; i < freshIngredientRanges.length; i++) {
            IdRange nextRange = freshIngredientRanges[i];
            if (currentRange.canBeMergedWith(nextRange)) {
                currentRange = currentRange.mergeWith(nextRange);
            } else {
                mergedRanges.add(currentRange);
                currentRange = nextRange;
            }
        }
        mergedRanges.add(currentRange);
        freshIngredientRanges = mergedRanges.toArray(new IdRange[0]);
    }

}
