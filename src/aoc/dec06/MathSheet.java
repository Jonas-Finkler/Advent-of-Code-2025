package aoc.dec06;

import aoc.Puzzle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class MathSheet {


    Column[] columns;

    public MathSheet(String sheetText) {
        this(sheetText, false);
    }

    public MathSheet(String sheetText, boolean cephStyle) {
        String[] lines = sheetText.split("\n");
        if (cephStyle) {
            String operatorLine = lines[lines.length - 1];
            Operator[] operators = Arrays.stream(operatorLine.trim().split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(this::parseOperator)
                    .toArray(Operator[]::new);
            int nCols = operators.length;
            columns = new Column[nCols];

            // extract first n-1 lines
            String[] valueLines = Arrays.copyOfRange(lines, 0, lines.length - 1);
            ArrayList<BigInteger> valueBuffer = new ArrayList<>();

            int iCol = 0;
            for (int c = 0; c < valueLines[0].length(); c++) {
                final int colIndex = c;
                String col = Arrays.stream(valueLines).map(l -> String.valueOf(l.charAt(colIndex))).reduce("", String::concat).trim();
                if (!col.isEmpty()) {
                    valueBuffer.add(new BigInteger(col));
                } else {
                    // end of column
                    BigInteger[] values = valueBuffer.toArray(new BigInteger[0]);
                    columns[iCol] = new Column(values, operators[iCol]);
                    iCol++;
                    valueBuffer.clear();
                }
            }
            // last column
            BigInteger[] values = valueBuffer.toArray(new BigInteger[0]);
            columns[iCol] = new Column(values, operators[iCol]);

        } else {
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
