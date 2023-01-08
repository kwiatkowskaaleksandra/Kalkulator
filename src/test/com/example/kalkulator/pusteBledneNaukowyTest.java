package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.util.Objects;

@ExtendWith(ApplicationExtension.class)
public class pusteBledneNaukowyTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(pusteBledneNaukowyTest.class.getResource("fxml/Home.fxml")));

        root.setOnMousePressed(event -> {
            x = stage.getX() - event.getSceneX();
            y = stage.getY() - event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getSceneX() + x);
            stage.setY(event.getSceneY() + y);
        });

        stage.setScene(new Scene(root, 606, 614));
        stage.toFront();
        stage.show();
    }

    @Test
    public void pusteBledneTest(FxRobot robot) {
        robot.clickOn("#kNaukowy");
        robot.sleep(2000);

        //PUSTE DZIALANIE
        robot.clickOn("#wynikID");
        robot.sleep(3000);

        //DZIALANIE Z '?'
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#logID");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#usunID");

        //DZIALANIE Z X BEZ ZAZNACZONEJ POCHODNEJ
        robot.clickOn("#wpisaneDzialanie").write("x");
        robot.clickOn("#minusID");
        robot.clickOn("#czteryOnAction");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#usunID");

        //DZIALANIE TRYGONOMETRYCZNE COS[30] BEZ WYBRANEJ JEDNOSTKI
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#cosID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("30]");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#usunID");

        //BLEDNE WPISANE DZIALANIE
        robot.clickOn("#wpisaneDzialanie").write("2*.");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("2x.");
        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#pochodnaCheckBox");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
    }
}
