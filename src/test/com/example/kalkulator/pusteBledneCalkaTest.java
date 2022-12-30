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
public class pusteBledneCalkaTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(pusteBledneCalkaTest.class.getResource("fxml/Home.fxml")));

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
        robot.clickOn("#kCalek");
        robot.sleep(2000);

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#granicaGorna");
        robot.clickOn("#trzyOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaGorna").write("3]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#trzyOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaDolna").write("2]");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("3]");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#eID");
        robot.clickOn("#nieskonczonoscID");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#czteryOnAction");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("3]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#eID");
        robot.clickOn("#nieskonczonoscID");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#czteryOnAction");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#nieskonczonoscID");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("3]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#minusID");
        robot.clickOn("#nieskonczonoscID");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#czteryOnAction");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#cosID");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#cosID");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#cosID");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#menu");
        robot.sleep(100);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Całka nieoznaczona");
        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda prostokątów z nadmiarem");
        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#pierNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("3");
        robot.clickOn("#przecinekID");
        robot.clickOn("#wpisanaCalka").write("?]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#logID");
        robot.clickOn("#granicaGorna");
        robot.clickOn("#logID");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Całka nieoznaczona");
        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda prostokątów z nadmiarem");
        robot.clickOn("#usunID");
        robot.clickOn("#wpisanaCalka");
        robot.clickOn("#logID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisanaCalka").write("2][34]");
        robot.clickOn("#granicaDolna");
        robot.clickOn("#pierNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaDolna").write("3");
        robot.clickOn("#przecinekID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaDolna").write(",27]");;
        robot.clickOn("#granicaGorna");
        robot.clickOn("#pierNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaGorna").write("2");
        robot.clickOn("#przecinekID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#granicaGorna").write(",16]");;

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#liczbaPodprzedzialow").write("zez");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#liczbaPodprzedzialow");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#dwaOnAction");
        robot.clickOn("#trzyOnAction");
        robot.clickOn("#wpisanaCalka").write("zez");

        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda prostokątów z niedomiarem");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda trapezów");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda Simpsona");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Metoda analityczna");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
        robot.clickOn("#metodaChoiceBox");
        robot.clickOn("Całka nieoznaczona");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
    }
}
