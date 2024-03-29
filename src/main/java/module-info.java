module com.example.kalkulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires JMathPlot;
    requires jython.slim;
    requires jep;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}