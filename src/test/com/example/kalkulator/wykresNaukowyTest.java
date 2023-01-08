package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.util.Objects;

@ExtendWith(ApplicationExtension.class)
public class wykresNaukowyTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(wykresNaukowyTest.class.getResource("fxml/Home.fxml")));

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
    void wykresTest(FxRobot robot) {
        robot.clickOn("#kNaukowy");
        robot.moveTo(1050, 653);
        robot.press(MouseButton.PRIMARY).moveTo(650, 700).drop();

        //PUSTE DZIAŁANIE
        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#wykresOtworz");
        robot.sleep(2000);
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        //LN[2] I UJEMNA WARTOSC X NA WYKRESIE
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#lnID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("2]");
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        robot.clickOn("#usunID");

        //WYKRES FUNKCJI
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#dwaOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#pier2ID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#trzyOnAction");
        robot.sleep(5000);
        robot.clickOn("#wpisaneDzialanie").write("]");
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        //WYKRES FUNKCJI I POCHODNEJ
        robot.clickOn("#pochodnaCheckBox");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        robot.clickOn("#wykresZamknij");
        robot.sleep(3000);
    }

}
