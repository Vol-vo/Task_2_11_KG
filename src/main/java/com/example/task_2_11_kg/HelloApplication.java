package com.example.task_2_11_kg;

import com.almasb.fxgl.core.math.Vec2;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;

public class HelloApplication extends Application {

    List<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas((double) 1000, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc, root);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc, Group root) {
        root.setOnMouseClicked((MouseEvent event) -> {
            points.add(new Point((int) event.getX(), (int) event.getY()));
            drawChart(gc);
            drawBezier(gc);
        });
    }

    private void drawChart(GraphicsContext gc) {    //метод для отрисовки графика
        System.out.println(" ");
        for (Point point : points) {
            System.out.println(point.getX() + " " + point.getY());
        }
        gc.fillOval(points.get(points.size() - 1).getX() - 2.5, points.get(points.size() - 1).getY() - 2.5,
                5, 5);
    }

    private void drawBezier(GraphicsContext gc) {
        if (points.size() == 1) {
            System.out.println("We have 1 point only.");
            return;
        }
        Point startPoint;
        for (float i = 0; i < 1; i += 0.0001) {
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
        return (с * Math.pow(1 - t, n - k) * Math.pow(t, k));
    }

    private Point getPoint(double t) {
        int n = points.size();
        Vec2 a = new Vec2(points.get(0).getX(), points.get(0).getY());
        for (int k = 0; k < n; k++) {
            Vec2 p = new Vec2(points.get(k).getX(), points.get(k).getY());
            a = a.add(p.mul(bernstein(t, n, k)));
        }
        return new Point((int) a.x, (int) a.y);
    }

}