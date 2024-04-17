package mark;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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

    public double getDiameter() {
        return diameter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, 50, 50);
        g2d.fill(ellipse);
        g.setColor(color);
        ((Graphics2D) g).draw(ellipse);
        g.drawOval((int) x, (int) y, 20, 20);
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

class Main extends JFrame {
    Main() {
        super("NewFrame");
        setBounds(0, 0, 1000, 1000);
        setResizable(false);
        //setLayout(null);
        //setLayout(new GridLayout(3, 1, 10, 10));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CircleComponent planet1 = new CircleComponent(Color.black,
                10, 11.5, 55.3, 0.1, 100);
        CircleComponent moon1 = new CircleComponent(Color.RED,
                5, 2000, 2000, 0.1, 15);
        CircleComponent moon2 = new CircleComponent(Color.WHITE,
                50, 300, 300, 0.5, 50);

        add(planet1);//, BorderLayout.CENTER);
        planet1.setBounds(new Rectangle((int) planet1.returnX(), (int) planet1.returnY(),
                (int) planet1.getDiameter(), (int) planet1.getDiameter()));
        add(moon1);//, BorderLayout.NORTH);
        moon1.setBounds(new Rectangle((int) moon1.returnX(), (int) moon1.returnY(),
                (int) moon1.getDiameter(), (int) moon1.getDiameter()));
        add(moon2);//, BorderLayout.WEST);
        moon2.setBounds(new Rectangle((int) moon2.returnX(), (int) moon2.returnY(),
                (int) moon2.getDiameter(), (int) moon2.getDiameter()));

        setVisible(true);
        Map<CircleComponent, ArrayList<CircleComponent>> bodyMap = new TreeMap<>();
        ArrayList<CircleComponent> objects = new ArrayList<>();
        objects.add(moon1);
        objects.add(moon2);
        bodyMap.put(planet1, objects);
        double RadiusOfCircle = 100;
        double radialSpeed = 1;
        Graphics graphics = getGraphics();
        Thread thread = new Thread(() -> {
            double startX = planet1.getX() - 10;
            double startY = planet1.getY() - 10;
            double dtA = 0.01;
            while (true) {
                for (CircleComponent planet : bodyMap.keySet()) {
                    startX = 500 + planet.getRadiusOfCircle() * Math.cos(planet.getSpeed() * dtA);
                    startY = 500 + planet.getRadiusOfCircle() * Math.sin(planet.getSpeed() * dtA);
                    planet.setXY(startX, startY);
                    graphics.setColor(Color.BLACK);
                    graphics.fillOval((int) startX + 200, (int) startY + 200, 2, 2);
                    for (CircleComponent satellite : bodyMap.get(planet)) {
                        double X = startX + satellite.getRadiusOfCircle() * Math.cos(satellite.getSpeed() * dtA);
                        double Y = startY + satellite.getRadiusOfCircle() * Math.sin(satellite.getSpeed());
                        satellite.setXY(X, Y);
                    }
                }
                dtA += 0.1;
// planet1.setXY(startX,startY);
// startX = + RadiusOfCircle * Math.cos(dtA);
// startY = 500 + RadiusOfCircle * Math.sin(dtA);
                dtA = dtA + 0.01;
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread.start();
    }
}

public class Turn {
    public static void main(String[] args) {
        Main main = new Main();
    }
}