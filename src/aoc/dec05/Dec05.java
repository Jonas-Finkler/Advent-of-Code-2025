package aoc.dec05;

import aoc.Puzzle;

import java.math.BigInteger;

public class Dec05 extends Puzzle {

    private FoodInventory inventory;
    private BigInteger[] ingredientIds;

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public String solve(Part part) {
        if (part == Part.A) {
            int nFreshIngredients = 0;
            for (BigInteger id : ingredientIds) {
                if (inventory.isFresh(id)) {
                    nFreshIngredients++;
                }
            }
            return Integer.toString(nFreshIngredients);
        } else {
            return inventory.nFreshIds().toString();
        }
    }

    public Dec05() {
        parseInput();
    }

    private void parseInput() {
        String[] lines = getInput().trim().split("\n");

        // find the empty line separating ranges and ingredient IDs
        int emptyLineIndex = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().isEmpty()) {
                emptyLineIndex = i;
                break;
            }
        }

        // parse ranges
        FoodInventory.IdRange[] ranges = new FoodInventory.IdRange[emptyLineIndex];
        for (int i = 0; i < emptyLineIndex; i++) {
            ranges[i] = new FoodInventory.IdRange(lines[i]);
        }
        inventory = new FoodInventory(ranges);

        // parse ingredient IDs
        ingredientIds = new BigInteger[lines.length - emptyLineIndex - 1];
        for (int i = emptyLineIndex + 1; i < lines.length; i++) {
            ingredientIds[i - emptyLineIndex - 1] = new BigInteger(lines[i].trim());
        }
    }


}
