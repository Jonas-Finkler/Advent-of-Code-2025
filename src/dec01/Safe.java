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


}
