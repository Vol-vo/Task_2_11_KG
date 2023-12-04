package com.example.task_2_11_kg.objwriter;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage) {
        super("Error writing OBJ file:" + errorMessage);
    }


}