package com.example.task_2_11_kg;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.math.*;

import javafx.scene.image.PixelWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;

public class HelloApplication extends Application {

    int width;
    int height;

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

    private void drawShapes(GraphicsContext gc, Group root) throws IOException {
        root.setOnMouseClicked((MouseEvent event) -> {
            points.add(new Point((int) event.getX(), (int) event.getY()));
            drawChart(gc);
            drawBezier(gc);
        });
    }

    private void drawChart(GraphicsContext gc) {    //метод для отрисовки графика
        System.out.println("");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i).getX() + " " + points.get(i).getY());
        }
        gc.fillOval(points.get(points.size() - 1).getX() - 2.5, points.get(points.size() - 1).getY() - 2.5,
                5, 5);
    }

    private void drawBezier(GraphicsContext gc) {
        if (points.size() == 1) {
            System.out.println("We have 1 point only.");
            return;
        }
        Point startPoint = new Point(points.get(0).getX(), points.get(0).getX());
        for (double i = 0; i < 1; i += 0.01) {
            final PixelWriter pw = gc.getPixelWriter();
            pw.setColor(startPoint.getX() + 4, startPoint.getY() - 1, Color.BLACK);
            startPoint = getPoint(i, 0);
        }
        //Википедия смотреть кривая Безье
    }
    private static int fact(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    //Bernstein polynomial
    private static int bernstein(double t, int n, int i){
        return (int) ((fact(n) / (fact(i) * fact(n-i))) * Math.pow(1-t, n-i) * Math.pow(t, i));
    }
    private Point getPoint(double t, int index) {
        /*
        int localIndex = index + 1;
        Point localPoint = points.get(index);
        if (index < points.size() - 1) {
            localPoint.setX(bernstein(t, points.size(), index) + getPoint(t, localIndex).getX());
            localPoint.setY(bernstein(t, points.size(), index) + getPoint(t, localIndex).getY());
        }
        return localPoint;

         */


        int newIndex = index + 1;
        Point localPoint = new Point(points.get(index).getX(), points.get(index).getY());
        if (index == points.size() - 1) {
            localPoint.setX((int) Math.pow(t,points.size() - 1) * points.get(points.size() - 1).getX());
            localPoint.setY((int) Math.pow(t,points.size() - 1) * points.get(points.size() - 1).getY());
        }else {
            localPoint.setX((int) (3 * Math.pow(t, index) * Math.pow((1 - t), points.size() - index)
                    * points.get(index).getX() + getPoint(t, newIndex).getX()));
            localPoint.setY((int) (3 * Math.pow(t, index) * Math.pow((1 - t), points.size() - index)
                    * points.get(index).getY() + getPoint(t, newIndex).getY()));
        }
        return localPoint;


    }

    public double binomialCalc(int n, int i) {
        if (n == 0 || i == 0)
            return 1;
        else
            return (double) (n - i + 1) / i;
    }
}