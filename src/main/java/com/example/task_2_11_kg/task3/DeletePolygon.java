package com.example.task_2_11_kg.task3;

import com.example.task_2_11_kg.ObjReader.model.Model;
import com.example.task_2_11_kg.ObjReader.model.Polygon;
import com.example.task_2_11_kg.ObjReader.objreader.ObjReader;

import java.io.File;
import java.io.FileNotFoundException;


public class DeletePolygon {
    private final Model model;

    public DeletePolygon(File fileObject) throws FileNotFoundException {
        this.model = ObjReader.read(fileObject);
        counter();
    }

    public DeletePolygon(Model model) {
        this.model = model;
        counter();
    }

    private void counter(){
        for (Polygon p : model.polygons) {
            for (int j = 0; j < p.getVertexIndices().size(); j++) {
                model.vertices.get(p.getVertexIndices().get(j)).count += 1;
            }
        }
    }


    public boolean delete(int index, boolean remove) throws Exception {
        if (index > model.polygons.size() || index < 0) {
            throw new Exception("Полигона с таким индексом не существует. Ошибка возникла в методе delete."
                    + " Количество полигонов: " + model.polygons.size());
        }
        if (remove) {
            for (int i : model.polygons.get(index).getVertexIndices()) {
                model.vertices.get(i).count -= 1;
                if (model.vertices.get(i).count == 0) {
                    deleteBadVerticals(i);
                    model.vertices.remove(i);
                }
            }
        }
        model.polygons.remove(index);
        System.out.println("Полигон успешно удалён!");
        return true;
    }

    private void deleteBadVerticals(int index){
        for (Polygon p : model.polygons) {
            for (int j = 0; j < p.getVertexIndices().size(); j++) {
                if (p.getVertexIndices().get(j) > index) {
                    p.getVertexIndices().set(j, (p.getVertexIndices().get(j) - 1));
                }
            }
        }
    }

    public Model getModel() {
        return this.model;
    }

}
