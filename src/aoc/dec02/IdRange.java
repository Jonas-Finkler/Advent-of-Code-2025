package aoc.dec02;

import java.math.BigInteger;

public class IdRange {

    private final BigInteger beg, end;

    public IdRange(BigInteger beg, BigInteger end) {
        this.beg = beg;
        this.end = end;
    }

    public IdRange(String rangeStr) {
        String[] parts = rangeStr.trim().split("-");
        this.beg = new BigInteger(parts[0]);
        this.end = new BigInteger(parts[1]);
    }

    // Definition of first task, repeats twice
    private boolean idIsValid(BigInteger id) {
        String idStr = id.toString();
        if (idStr.length() % 2 == 1) {
            return true;
        }
        for (int i = 0; i < idStr.length() / 2; i++) {
            if (idStr.charAt(i) != idStr.charAt(idStr.length() / 2 + i)) {
                return true;
            }
        }
        return false;
    }

    // Using the definition of part B
    private boolean idIsValidExtended(BigInteger id) {
        String idStr = id.toString();

        for (int patternLen = 1; patternLen <= idStr.length() / 2; patternLen++) {
            if (idStr.length() % patternLen == 0) {
                boolean match = true;
                for (int i = patternLen; i < idStr.length(); i++) {
                    if (idStr.charAt(i) != idStr.charAt(i % patternLen)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return false;
                }
            }
        }
        return true;
    }

    public BigInteger sumOfInvalidIds(boolean useExtendedDefinition) {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger id = beg; id.compareTo(end) <= 0; id = id.add(BigInteger.ONE)) {
            boolean isValid = useExtendedDefinition ? idIsValidExtended(id) : idIsValid(id);
            if (!isValid) {
                sum = sum.add(id);
            }
        }
        return sum;
    }
}
