package father;

import java.awt.*;
import java.util.Objects;

class CircleComponent implements Comparable<CircleComponent> {
    private Color color;
    private double diameter;
    private double x;
    private double y;
    private double speed;
    private double radiusOfOrbit;

    private String name;

    private final static Color ERASE_COLOR = Color.WHITE;

    CircleComponent(String name, Color color, double diameter, double x, double y, double speed, double radiusOfOrbit) {
        this.name = name;
        this.color = color;
        this.diameter = diameter;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.radiusOfOrbit = radiusOfOrbit;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println(this.x + this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getRadiusOfOrbit() {
        return radiusOfOrbit;
    }

    protected void eraseComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(ERASE_COLOR);
        g2d.fillOval((int) x, (int) y, (int) (diameter / 2), (int) (diameter / 2));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g2d.fillOval((int) x, (int) y, (int) (diameter / 2), (int) (diameter / 2));
    }


    @Override
    public int compareTo(CircleComponent o) {
        return o.name.compareTo(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircleComponent that = (CircleComponent) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
