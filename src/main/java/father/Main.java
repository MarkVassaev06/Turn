package father;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Main extends JFrame {

    public Main(String title) {
        super(title);
        initGui();
    }

    private void initGui() {
        JButton button = new JButton("press me");
        button.setBounds(0, 0, 120, 25);
        add(button);
        //спутники?
        CircleComponent planet1 = new CircleComponent("planet1", Color.YELLOW,
                30, 490, 300, 0.1, 200);
        CircleComponent planet2 = new CircleComponent("planet2", Color.BLUE,
                25, 300, 750, 0.1, 300);
        CircleComponent satellite1 = new CircleComponent("satellite1", Color.BLUE,
                25, 300, 250, 0.1, 400);
        //звезда?
        CircleComponent star = new CircleComponent("star", Color.RED,
                50, 500, 500, 0.5, 50);

        // Мапа с обьектами и их спутниками.
        Map<CircleComponent, List<CircleComponent>> bodyMap = new TreeMap<>();
        //Это объекты-спутники
        List<CircleComponent> objects = new ArrayList<>();
        objects.add(planet1);
        objects.add(planet2);
        //К звезде подцепляем спутники
        bodyMap.put(star, objects);

        List<CircleComponent> objects1 = new ArrayList<>();
        objects1.add(satellite1);

        bodyMap.put(planet2, objects1);

        button.addActionListener(e -> {
            getRootPane().setBackground(Color.WHITE);
            setBackground(Color.WHITE);

            //Запускаем поток для прорисовки движения.
            java.util.Timer timer = new java.util.Timer();
            timer.schedule(new MyTimerTask(getGraphics(), bodyMap), 0);
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
            for (CircleComponent star : bodyMap.keySet()) {
                //star.eraseComponent(graphics);
                //Вычисляем новые координаты
                double startX = 500 + star.getRadiusOfOrbit() * Math.cos(star.getSpeed() * dtA);
                double startY = 500 + star.getRadiusOfOrbit() * Math.sin(star.getSpeed() * dtA);
                star.setXY(startX, startY);
                //Рисуем новое положение
                star.paintComponent(graphics);
                //Пробегаемся по спутникам звезды
                for (CircleComponent satellite : bodyMap.get(star)) {
                    //Затираем предыдущее положение
                    //satellite.eraseComponent(graphics);
                    //Вычисляем новые координаты
                    double X = startX + satellite.getRadiusOfOrbit() * Math.cos(satellite.getSpeed() * dtA);
                    double Y = startY + satellite.getRadiusOfOrbit() * Math.sin(satellite.getSpeed() * dtA);
                    satellite.setXY(X, Y);
                    //Рисуем новое положение
                    satellite.paintComponent(graphics);
                }
            }
            dtA += 0.01;
        }
    }
}