package aoc.dec08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Playground {

    private JunctionBox[] junctionBoxes;
    private Connection[] possibleConnections;
    private Connection lastConnectionMade;

    public Playground(String junctionBoxPositions) {
        // loop lines and parse x, y and z positions
        String[] lines = junctionBoxPositions.split("\n");
        ArrayList<JunctionBox> boxes = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);
            boxes.add(new JunctionBox(x, y, z));
        }
        junctionBoxes = boxes.toArray(new JunctionBox[0]);

        setupPossibleConnections();
    }

    private void setupPossibleConnections() {
        possibleConnections = new Connection[(junctionBoxes.length * (junctionBoxes.length - 1)) / 2];
        int index = 0;
        for (int i = 0; i < junctionBoxes.length; i++) {
            for (int j = i + 1; j < junctionBoxes.length; j++) {
                possibleConnections[index++] = new Connection(junctionBoxes[i], junctionBoxes[j]);
            }
        }
        // sort possibleConnections by distance
        java.util.Arrays.sort(possibleConnections, (a, b) -> Double.compare(a.getDistance(), b.getDistance()));

    }

    public void connectNShortestConnections(int n) {
        int connectionsMade = 0;
        for (Connection connection : possibleConnections) {
            if (!connection.isConnected()) {
                connection.connect();
                lastConnectionMade = connection;
                connectionsMade++;
                if (connectionsMade >= n) {
                    break;
                }
            }
        }
    }

    public void connectUntilSingleCircuit() {
        for (Connection connection : possibleConnections) {
            if (!connection.isConnected()) {
                connection.connect();
                lastConnectionMade = connection;
                if (getAllCircuits().size() == 1) {
                    break;
                }
            }
        }
    }

    public Connection getLastConnectionMade() {
        return lastConnectionMade;
    }

    // Get all unique circuits
    public Set<Circuit> getAllCircuits() {
        Set<Circuit> circuits = new HashSet<>();
        if (junctionBoxes == null) return circuits;
        for (JunctionBox box : junctionBoxes) {
            if (box == null) continue;
            Circuit circuit = box.getCircuit();
            if (circuit != null) circuits.add(circuit);
        }
        return circuits;
    }

    public int productOfNLargestCircuitSizes(int n) {
        int[] sizes = getAllCircuits().stream().mapToInt(Circuit::size).toArray();
        java.util.Arrays.sort(sizes);
        int product = 1;
        for (int i = sizes.length-1; i >= sizes.length - n && i >= 0; i--) {
            product *= sizes[i];
        }
        return product;
    }

}
