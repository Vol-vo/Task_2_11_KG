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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseDragEvent;
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
        root.setOnMouseClicked((MouseEvent event) ->{
            if (!points.isEmpty()){
                if ((int) event.getX() > points.get(points.size() - 1).getX()) {
                    points.add(new Point((int) event.getX(), (int) event.getY()));
                    drawChart(gc);
                }
            }else{
                points.add(new Point((int)event.getX(), (int)event.getY()));
                drawChart(gc);
            }
        });
    }

    private void drawChart(GraphicsContext gc) {    //метод для отрисовки графика
        System.out.println("");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i).getX() + " " + points.get(i).getY());
        }
        gc.fillOval(points.get(points.size() - 1).getX() -2.5, points.get(points.size() - 1).getY() - 2.5,
                5, 5);
    }
}