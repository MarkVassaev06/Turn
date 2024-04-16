import javax.swing.*;
import java.awt.*;

class CircleComponent extends JComponent implements Comparable<CircleComponent> {
    private Color color;
    private double diameter;
    private double x;
    private double y;
    private double speed;

    private String name;

    private final static Color ERASE_COLOR = Color.WHITE;

    CircleComponent(String name, Color color, double diameter, double x, double y, double speed) {
        this.name = name;
        this.color = color;
        this.diameter = diameter;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void setXY(double x, double y) {
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

    public double getDiametr() {
        return diameter;
    }

    protected void eraseComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(ERASE_COLOR);
        g2d.fillOval((int) x, (int) y, (int) (diameter / 2), (int) (diameter / 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g2d.fillOval((int) x, (int) y, (int) (diameter / 2), (int) (diameter / 2));
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
