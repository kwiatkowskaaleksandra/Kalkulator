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
public class dzialaniaNaukowyTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(dzialaniaNaukowyTest.class.getResource("fxml/Home.fxml")));

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
    public void dzialaniaTest(FxRobot robot){
        robot.clickOn("#kNaukowy");
        robot.sleep(2000);

        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#nawiasLID");
        robot.clickOn("#piecOnAction");
        robot.clickOn("#silniaID");
        robot.clickOn("#razyID");
        robot.clickOn("#pierNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#szescOnAction");
        robot.clickOn("#przecinekID");
        robot.clickOn("#siedemOnAction");
        robot.clickOn("#wpisaneDzialanie").write("]");
        robot.clickOn("#nawiasPID");
        robot.clickOn("#dzielID");
        robot.clickOn("#eID");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");

        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#potNID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("3]-");
        robot.clickOn("#osiemOnAction");
        robot.clickOn("#pot2ID");
        robot.clickOn("#wpisaneDzialanie").write("+");
        robot.clickOn("#dziewiecOnAction");
        robot.clickOn("#procentID");

        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#pochodnaCheckBox");
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#xID");
        robot.clickOn("#minusID");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#kropkaID");
        robot.clickOn("#jedenOnAction");
        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#jednostkaChoiceBox");
        robot.clickOn("Stopnie");
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#sinID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("30]+");
        robot.clickOn("#asinID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("0]-");
        robot.clickOn("#acosID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("0.1]+");
        robot.clickOn("#cotID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("x]");
        robot.clickOn("#wynikID");
        robot.sleep(3000);

        robot.clickOn("#usunID");
        robot.clickOn("#jednostkaChoiceBox");
        robot.clickOn("Radiany");
        robot.clickOn("#wpisaneDzialanie").write("1/2+");
        robot.clickOn("#tanID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie");
        robot.clickOn("#piID");
        robot.clickOn("#wpisaneDzialanie").write("]-");
        robot.clickOn("#atanID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("1]-");
        robot.clickOn("#acotID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#usunOstatnieID");
        robot.clickOn("#wpisaneDzialanie").write("0.3]");
        robot.clickOn("#wynikID");
        robot.sleep(3000);
    }
}
