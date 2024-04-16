import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Main extends JFrame {

    public Main(String title) {
        super(title);
    }

    public void createGui() {

        JButton button = new JButton("press me");
        button.setBounds(0, 0, 120, 25);
        add(button);

        //спутники?
        CircleComponent satellite1 = new CircleComponent(Color.BLACK,
                10, 490, 300, 0.1, 100);
        CircleComponent satellite2 = new CircleComponent(Color.RED,
                5, 300, 750, 0.1, 15);
        //звезда?
        CircleComponent star = new CircleComponent(Color.WHITE, 50,
                500, 500, 0.5, 50);

        // Мапа с обьектами и их спутниками.
        Map<CircleComponent, java.util.List<CircleComponent>> bodyMap = new TreeMap<>();
        //Это объекты-спутники
        java.util.List<CircleComponent> objects = new ArrayList<>();
        objects.add(satellite1);
        objects.add(satellite2);
        //К звезде подцепляем спутники
        bodyMap.put(star, objects);

        button.addActionListener(e -> {
            //Рисуем первоначальные позиции.
            Graphics graphics = getGraphics();

            //Запускаем поток для прорисовки движения.
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new MyTimerTask(graphics, bodyMap), 0);
        });

    }

    static class MyTimerTask extends TimerTask {

        private Graphics graphics;
        private Map<CircleComponent, List<CircleComponent>> bodyMap;
        private static double dtA = 0.01;

        public MyTimerTask(Graphics graphics, Map<CircleComponent, List<CircleComponent>> bodyMap) {
            this.graphics = graphics;
            this.bodyMap = bodyMap;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    return;
                }
                movePlanets();
            }
        }

        private void movePlanets() {
            for (CircleComponent a : bodyMap.keySet()) {
                double startX = 500 + a.getRadiusOfCircle() * Math.cos(a.getSpeed() * dtA);
                double startY = 500 + a.getRadiusOfCircle() * Math.sin(a.getSpeed() * dtA);
                a.setXY(startX, startY);
                for (CircleComponent satellite : bodyMap.get(a)) {
                    double X = startX + 100 * Math.cos(satellite.getSpeed() * dtA);
                    double Y = startY + 100 * Math.sin(satellite.getSpeed() * dtA);
                    satellite.setXY(X, Y);
                    satellite.paintComponent(graphics);
                }
            }
            dtA += 0.01;

        }
    }
}