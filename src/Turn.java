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

class Main extends JFrame {
    Main() {
        super("NewFrame");
        setBounds(0, 0, 1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CircleComponent circleComponent = new CircleComponent(Color.black, 10, 11.5, 55.3, 0.1, 100);
        CircleComponent circleComponent1 = new CircleComponent(Color.RED, 5, 0, 0, 0.1, 15);
        CircleComponent circleComponent2 = new CircleComponent(Color.WHITE, 50, 0, 0, 0.5, 50);
        add(circleComponent2);
        add(circleComponent, BorderLayout.CENTER);
        add(circleComponent1, BorderLayout.CENTER);
        setVisible(true);
        // Массив с обьектами и их спутниками.
        Map<CircleComponent, ArrayList<CircleComponent>> BodyMap = new TreeMap<>();
        ArrayList<CircleComponent> objects = new ArrayList<>();
        objects.add(circleComponent1);
        BodyMap.put(circleComponent, objects);
        double RadiusOfCircle = 100;
        double radialSpeed = 1;
        Thread thread = new Thread(() -> {
//                double startX = circleComponent.getX() - 10;
//                double startY = circleComponent.getY() - 10;
            double dtA = 0.01;
            while (true) {
                for (CircleComponent a : BodyMap.keySet()) {
                    double startX = 500 + a.getRadiusOfCircle() * Math.cos(a.getSpeed() * dtA);
                    double startY = 500 + a.getRadiusOfCircle() * Math.sin(a.getSpeed() * dtA);
                    a.setXY(startX, startY);
                    for (CircleComponent Satellite : BodyMap.get(a)) {
                        double X = startX + 100 * Math.cos(Satellite.getSpeed() * dtA);
                        double Y = startY + 100 * Math.sin(Satellite.getSpeed() * dtA);
                        Satellite.setXY(X, Y);
                    }
                }
                dtA += 0.01;
//                    circleComponent.setXY(startX,startY);
//                    startX =  + RadiusOfCircle * Math.cos(dtA);
//                    startY = 500 + RadiusOfCircle * Math.sin(dtA);
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