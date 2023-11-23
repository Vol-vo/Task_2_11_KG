package com.example.task_2_11_kg.ObjReader.model;

import com.example.task_2_11_kg.ObjReader.math.Vector3f;
import com.example.task_2_11_kg.ObjReader.math.Vector2f;
import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();
}
