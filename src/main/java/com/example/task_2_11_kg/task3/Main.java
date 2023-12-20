package com.example.task_2_11_kg.task3;

import com.example.task_2_11_kg.objwriter.ObjWriter;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        File fileOpen = new File("/Users/volvo/Documents/Учёба/2 курс/КГ/Task_2_11_KG/src/main/java/com/" +
                "example/task_2_11_kg/task3/3DModels/Faceform/WrapHand.obj");
        DeletePolygon deletePolygon = new DeletePolygon(fileOpen);
        int size1 = deletePolygon.getModel().vertices.size();
        try {
            for (int i = 0; i < 300; i++) {
                deletePolygon.delete(i, true);
            }
            System.out.println(size1 - deletePolygon.getModel().vertices.size());

            ObjWriter.write("TestPolygon.obj", deletePolygon.getModel());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
