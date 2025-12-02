package dec01;

public class Safe {

    private int state;
    private int numStates;

    public Safe(int initialState) {
        this(initialState, 100);
    }

    public Safe(int initialState, int numStates) {
        this.state = initialState;
        this.numStates = numStates;
    }

    public void turn(int step) {
        state += step;
    }

    public int getState() {
        return Math.floorMod(state, this.numStates);
    }

    // Turn the dial and count how often it lands on zero
    public int turnAndGetZeroCount(int step) {
        int zeroCount = 0;

        // If we go down and start at zero, we have to ignore the zero crossing corresponding to the initial zero which was already counted
        if (step < 0 && getState() == 0) {
            zeroCount--;
        }

        int initState = state;
        turn(step);
        int finalState = state;

        // Count how many times we crossed zero
        zeroCount += Math.abs(Math.floorDiv(finalState, this.numStates) - Math.floorDiv(initState, this.numStates));

        // If we go down and end at zero, we have to count the final zero crossing
        if (step < 0 && getState() == 0) {
            zeroCount++;
        }

        return zeroCount;

    }

}
