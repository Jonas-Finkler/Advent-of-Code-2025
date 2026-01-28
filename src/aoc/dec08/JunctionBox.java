package aoc.dec08;

public class JunctionBox {
    private double x, y, z;
    private Circuit circuit;
    private static int idCounter = 0;
    private int id = 0;

    public JunctionBox(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.circuit = new Circuit(this);
        this.id = idCounter++;
    }

    public double distanceTo(JunctionBox other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public  void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public int getId() {
        return id;
    }

    public double productOfXCoordinates(JunctionBox other) {
        return x * other.x;
    }

}
