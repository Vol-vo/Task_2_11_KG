module com.example.task_2_11_kg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.task_2_11_kg to javafx.fxml;
    exports com.example.task_2_11_kg.task2;
    opens com.example.task_2_11_kg.task2 to javafx.fxml;
}