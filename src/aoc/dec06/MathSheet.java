package aoc.dec06;

import aoc.Puzzle;

import java.math.BigInteger;
import java.util.Arrays;

public class MathSheet {


    Column[] columns;

    public MathSheet(String sheetText) {
        String[] lines = sheetText.split("\n");
        String[][] rows = Arrays.stream(lines).map(
                l -> Arrays.stream(l.trim().split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new)
        ).toArray(String[][]::new);
        int nCols = rows[0].length;

        columns = new Column[nCols];
        for (int c = 0; c < nCols; c++) {
            BigInteger[] values = new BigInteger[lines.length - 1];
            for (int r = 0; r < lines.length - 1; r++) {
                values[r] = new BigInteger(rows[r][c]);
            }

            columns[c] = new Column(values, parseOperator(rows[lines.length - 1][c]));
        }

    }

    public BigInteger calcFinalResult() {
        return Arrays.stream(columns)
                .map(Column::solve)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private Operator parseOperator(String opStr) {
        return switch (opStr) {
            case "+" -> Operator.ADD;
            case "*" -> Operator.MULTIPLY;
            default -> throw new IllegalArgumentException("Unknown operator: " + opStr);
        };
    }

    public enum Operator {
        ADD, MULTIPLY
    }

    public static class Column {
        BigInteger[] values;
        Operator operator;

        public Column(BigInteger[] values, Operator operator) {
            this.values = values;
            this.operator = operator;
        }

        public BigInteger solve() {
            return Arrays.stream(values).reduce(getNeutralElement(this.operator), this::applyOperator);
        }

        private BigInteger applyOperator(BigInteger a, BigInteger b) {
            return switch (operator) {
                case ADD -> a.add(b);
                case MULTIPLY -> a.multiply(b);
            };
        }

        private BigInteger getNeutralElement(Operator operator) {
            return switch (operator) {
                case ADD -> BigInteger.ZERO;
                case MULTIPLY -> BigInteger.ONE;
            };
        }

        @Override
        public String toString() {
            return "Column{" +
                    "values=" + Arrays.toString(values) +
                    ", operator=" + operator +
                    '}';
        }
    }

}
