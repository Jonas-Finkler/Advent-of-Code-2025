package aoc.dec03;

import java.math.BigInteger;
import java.util.Arrays;

public class BatteryBank {

    int[] joltages;

    public BatteryBank(int[] joltages) {
        this.joltages = joltages;
    }

    public BigInteger maxJoltate(int nBatteries) {
        int[] iSelected = new int[nBatteries];

        for (int i=0; i < nBatteries; i++) {
            int jInit = (i == 0) ? 0 : iSelected[i - 1] + 1;
            iSelected[i] = jInit;
            for (int j = jInit + 1; j < joltages.length - nBatteries + i + 1; j++) {
                if (joltages[j] > joltages[iSelected[i]]) {
                    iSelected[i] = j;
                }
            }
        }
        return new BigInteger(Arrays.stream(iSelected)
                .mapToObj(i-> Integer.valueOf(joltages[i]).toString())
                .reduce("", (a,b) -> a + b));
    }
}
