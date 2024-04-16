import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main extends JFrame {

    public Main(String title) {
        super(title);
    }

    public void createGui() {
        CircleComponent circleComponent = new CircleComponent(Color.black, 10, 11.5, 55.3, 0.1, 100);
        CircleComponent circleComponent1 = new CircleComponent(Color.RED, 5, 0, 0, 0.1, 15);
        CircleComponent circleComponent2 = new CircleComponent(Color.WHITE, 50, 0, 0, 0.5, 50);
        add(circleComponent, BorderLayout.CENTER);
        add(circleComponent1, BorderLayout.CENTER);
        add(circleComponent2);

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