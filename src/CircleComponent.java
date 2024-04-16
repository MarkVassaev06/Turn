import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class CircleComponent extends JComponent implements Comparable<CircleComponent> {
    private Color color;
    private double diameter;
    private double x;
    private double y;
    private double speed;
    private double radiusOfCircle;

    CircleComponent(Color color, double diameter, double x, double y, double speed, double radiusOfCircle) {
        this.color = color;
        this.diameter = diameter;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.radiusOfCircle = radiusOfCircle;
    }

    void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println(this.x + this.y);
    }

    public double returnX() {
        return x;
    }

    public double returnY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRadiusOfCircle() {
        return radiusOfCircle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, 50, 50);
        g2d.fill(ellipse);
        g.setColor(color);
        ((Graphics2D) g).draw(ellipse);
    }

    @Override
    public int compareTo(CircleComponent o) {
        if (this.x < o.x) {
            return -1;
        } else if (this.x > o.x) {
            return 1;
        } else {
            return 0;
        }
    }
}
