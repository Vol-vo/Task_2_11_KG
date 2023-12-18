package com.example.task_2_11_kg.task3;

import com.example.task_2_11_kg.ObjReader.model.Model;
import com.example.task_2_11_kg.ObjReader.model.Polygon;
import com.example.task_2_11_kg.ObjReader.objreader.ObjReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DeletePolygon {
    private final Model model;
    private boolean remove;
    private boolean question;

    public DeletePolygon(File fileObject) throws FileNotFoundException {
        this.model = ObjReader.read(fileObject);
        remove = false;
        question = true;
    }

    public DeletePolygon(Model model) throws FileNotFoundException {
        this.model = model;
        remove = false;
        question = true;
    }

    public boolean delete(int index) throws Exception {

        if (index > model.polygons.size() || index < 0) {
            throw new Exception("Полигона с таким индексом не существует. Ошибка возникла в методе delete."
                    + " Количество полигонов: " + model.polygons.size());
        }
        model.polygons.remove(index);
        isDeleteVerticals();
        System.out.println("Полигон успешно удалён!");
        return true;
    }

    public boolean delete(int index, boolean remove) throws Exception {
        question = false;
        this.remove = remove;
        if (index > model.polygons.size() || index < 0) {
            throw new Exception("Полигона с таким индексом не существует. Ошибка возникла в методе delete."
                    + " Количество полигонов: " + model.polygons.size());
        }
        model.polygons.remove(index);
        isDeleteVerticals();
        System.out.println("Полигон успешно удалён!");
        return true;
    }

    private void isDeleteVerticals(){
        for (int i = 0; i < model.vertices.size(); i++) {
            boolean isReal = false;
            for (Polygon p : model.polygons){
                for (Integer pv : p.getVertexIndices()){
                    if(i == pv){
                        isReal = true;
                        break;
                    }
                }
                if (isReal){
                    break;
                }
            }
            if (!isReal){
                if (question) {
                    question = false;
                    System.out.print("Удаляем \"висячие\" вершины? (y or n) ");
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.next();
                    if (str.equals("y")){
                        remove = true;
                    }
                }
                if (remove) {
                    for (Polygon p : model.polygons) {
                        for (int j = 0; j < p.getVertexIndices().size(); j++) {
                            if (p.getVertexIndices().get(j) > i) {
                                p.getVertexIndices().set(j, (p.getVertexIndices().get(j) - 1));
                            }
                        }
                    }
                    model.vertices.remove(i);
                    break;
                }
            }
        }

    }

    public Model getModel() {
        return this.model;
    }

}
