package com.example.task_2_11_kg.task3;

import com.example.task_2_11_kg.ObjReader.math.Vector3f;
import com.example.task_2_11_kg.ObjReader.model.Model;
import com.example.task_2_11_kg.ObjReader.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DeletePolygonTest {

    @Test
    void delete() throws Exception {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(0, 1, 1));
        for (int i = 0; i < 5; i++) {
            model.polygons.add(new Polygon());
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        model.polygons.get(0).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        model.polygons.get(1).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        model.polygons.get(2).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(1);
        model.polygons.get(3).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(3);
        model.polygons.get(4).setVertexIndices(list);
        DeletePolygon deletePolygon = new DeletePolygon(model);

        Assertions.assertTrue(deletePolygon.delete(3));
    }

    @Test
    void delete1() throws Exception {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(0, 1, 1));
        for (int i = 0; i < 5; i++) {
            model.polygons.add(new Polygon());
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        model.polygons.get(0).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        model.polygons.get(1).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        model.polygons.get(2).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(1);
        model.polygons.get(3).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(3);
        model.polygons.get(4).setVertexIndices(list);
        DeletePolygon deletePolygon = new DeletePolygon(model);

        Assertions.assertThrows(Exception.class, () -> deletePolygon.delete(-1));
    }

    @Test
    void delete2() throws Exception {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(0, 1, 1));
        for (int i = 0; i < 5; i++) {
            model.polygons.add(new Polygon());
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        model.polygons.get(0).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        model.polygons.get(1).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        model.polygons.get(2).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(1);
        model.polygons.get(3).setVertexIndices(list);
        list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(3);
        model.polygons.get(4).setVertexIndices(list);

        DeletePolygon deletePolygon = new DeletePolygon(model);
        for (int i = 4; i > 0 ; i--) {
            deletePolygon.delete(i, true);
        }

        Model testModel = new Model();
        testModel.vertices.add(new Vector3f(0, 0, 0));
        testModel.vertices.add(new Vector3f(1, 0, 0));
        testModel.vertices.add(new Vector3f(0, 1, 0));
        testModel.vertices.add(new Vector3f(0, 0, 1));
        testModel.vertices.add(new Vector3f(0, 1, 1));
        testModel.polygons.add(new Polygon());
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        testModel.polygons.get(0).setVertexIndices(list);

        Assertions.assertEquals(testModel.polygons.size(), deletePolygon.getModel().polygons.size());
    }

}
