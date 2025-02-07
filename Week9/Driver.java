package Week9;

public class Driver {

    private int tag;
    private double x;
    private double y;

    public Driver(int tag) {
        this.tag = tag;
        this.x = 0.0;
        this.y = 0.0;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public String toString() {
        return "Particle " + tag + " is at (" + x + "," + y + ")";
    }

}
