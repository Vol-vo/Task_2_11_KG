package com.example.task_2_11_kg.ObjReader.model;
import com.example.task_2_11_kg.ObjReader.math.*;


import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<>();    //тут хранятся индексы вершин полигона
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();
}
