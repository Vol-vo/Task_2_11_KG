package com.example.task_2_11_kg.task2;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BezierDraw {

    List<Point> points;

    public BezierDraw() {
        points = new ArrayList<>();
    }

    public void drawShapes(GraphicsContext gc, Group root, Canvas canvas) {
        root.setOnMouseClicked((MouseEvent event) -> {
            points.add(new Point((int) event.getX(), (int) event.getY()));
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawChart(gc);
            drawBezier(gc);
        });
    }

    private void drawChart(GraphicsContext gc) {    //метод для отрисовки точки
        System.out.println(" ");
        for (Point point : points) {
            System.out.println(point.getX() + " " + point.getY());
        }
        for (Point point : points) {
            gc.fillOval(point.getX() - 2.5, point.getY() - 2.5,
                    5, 5);
        }
    }

    private void drawBezier(GraphicsContext gc) {
        if (points.size() <= 1) {
            System.out.println("We have 1 point only.");
            return;
        }
        Point startPoint;
        for (double i = 0; i <= 1; i += 0.0001) {
            startPoint = getPoint(i);
            final PixelWriter pw = gc.getPixelWriter();
            pw.setColor(startPoint.getX(), startPoint.getY(), Color.BLACK);
        }
        System.out.println(" ");
    }

    private static int fact(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    private static double bernstein(double t, int n, int k) {
        int с = fact(n) / (fact(k) * fact(n - k));
        return с * Math.pow(1 - t, n - k) * Math.pow(t, k);
    }

    private Point getPoint(double t) {
        int n = points.size() - 1;
        Point point;
        double x = 0;
        double y = 0;
        for (int k = 0; k <= n; k++) {
            point = points.get(k);
            x += bernstein(t, n, k) * point.getX();
            y += bernstein(t, n, k) * point.getY();
        }
        return new Point((int) x, (int) y);
    }

}
