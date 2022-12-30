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
public class wykresCalkaTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(wykresCalkaTest.class.getResource("fxml/Home.fxml")));

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
        robot.clickOn("#kCalek");
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
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#lnID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("2]");
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        robot.clickOn("#usunID");

        //WYKRES FUNKCJI
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#dwaOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#pier2ID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#trzyOnAction");
        robot.sleep(5000);
        robot.clickOn("#wpisanaCalka").write("]");
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        //WYKRES FUNKCJI I CALKI
        robot.clickOn("#granicaGorna");
        robot.clickOn("#lnID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaGorna").write("2]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#lnID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaDolna").write("6]");
        robot.clickOn("#liczbaPodprzedzialow");
        robot.clickOn("#czteryOnAction");
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda prostokątów z nadmiarem");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        //WYKRES
        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#czteryOnAction");
        robot.clickOn("#pot2ID");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#pot2ID");
        robot.clickOn("#plusID");
        robot.clickOn("#dwaOnAction");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#dwaOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#pot2ID");
        robot.clickOn("#plusID");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#liczbaPodprzedzialow");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#wykresID");
        robot.sleep(3000);

        robot.clickOn("#wykresZamknij");
        robot.sleep(3000);
    }
}
