package com.example.task_2_11_kg.task3;

import com.example.task_2_11_kg.ObjReader.objreader.ObjReader;
import com.example.task_2_11_kg.ObjReader.model.*;


public class DeletePolygon {
    private final Model model;
    public DeletePolygon(String fileObject) {
        this.model = ObjReader.read(fileObject);
    }

}
