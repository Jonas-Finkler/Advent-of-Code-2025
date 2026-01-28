package aoc.dec08;

import java.util.ArrayList;

public class Circuit {
    private ArrayList<JunctionBox> junctionBoxes = new ArrayList<>();

    public Circuit(JunctionBox box) {
        addJunctionBox(box);
    }

    public JunctionBox[] getJunctionBoxes() {
        return junctionBoxes.toArray(new JunctionBox[0]);
    }

    public void addJunctionBox(JunctionBox box) {
        junctionBoxes.add(box);
        box.setCircuit(this);
    }

    public int size() {
        return junctionBoxes.size();
    }

}
