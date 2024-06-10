module com.lestarieragemilang.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires java.sql;
    requires com.jfoenix;

    opens com.lestarieragemilang.app to javafx.fxml;
    opens com.lestarieragemilang.app.Entities to javafx.base;

    exports com.lestarieragemilang.app;
}