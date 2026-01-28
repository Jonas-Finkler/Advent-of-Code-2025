package aoc.dec08;

public class Connection {
    private JunctionBox A, B;
    private double distance;
    private boolean connected = false;

    public Connection(JunctionBox A, JunctionBox B) {
        this.A = A;
        this.B = B;
        this.distance = A.distanceTo(B);
    }

    public double getDistance() {
        return distance;
    }

    public boolean isConnectingDifferentCircuits() {
        return A.getCircuit() != B.getCircuit();
    }

    public boolean isConnected() {
        return connected;
    }

    public void connect() {
        connected = true;
        if (isConnectingDifferentCircuits()) {
            Circuit circuitA = A.getCircuit();
            Circuit circuitB = B.getCircuit();
            for (JunctionBox box : circuitB.getJunctionBoxes()) {
                circuitA.addJunctionBox(box);
            }
        }
    }

    public String toString() {
        return "Connection{" +
                "A=" + Integer.toString(A.getId()) +
                ", B=" + Integer.toString(B.getId()) +
                ", distance=" + distance +
                ", connected=" + connected +
                '}';
    }

    public double productOfXCoordinates() {
        return A.productOfXCoordinates(B);
    }
}
