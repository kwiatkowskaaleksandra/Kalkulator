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
public class menuNaukowyTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(menuNaukowyTest.class.getResource("fxml/Home.fxml")));

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
    public void menuTest(FxRobot robot) {
        robot.clickOn("#kNaukowy");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#pomocButton");
        robot.moveTo(1250, 350);
        robot.press(MouseButton.PRIMARY).moveTo(1300, 400).drop();
        robot.clickOn("#zamknijPomoc");

        robot.clickOn("#wpisaneDzialanie").write("2*3+1");
        robot.clickOn("#wynikID");
        robot.clickOn("#usunID");
        robot.clickOn("#historiaButton");
        robot.sleep(2000);
        robot.clickOn("2*3+1");
        robot.sleep(500);
        robot.clickOn("#wroc");
        robot.sleep(500);

        robot.clickOn("#wpisaneDzialanie").write("2*3+1");
        robot.clickOn("#jednostkaChoiceBox");
        robot.clickOn("Stopnie");
        robot.clickOn("#wynikID");
        robot.sleep(500);
        robot.clickOn("#jednostkaChoiceBox");
        robot.clickOn("Radiany");
        robot.clickOn("#wynikID");
        robot.sleep(500);

        robot.clickOn("#menuZamknij");
        robot.sleep(500);
        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#kalkulatorCalkaButton");
        robot.sleep(2000);
        robot.moveTo(1250, 350);
        robot.press(MouseButton.PRIMARY).moveTo(1300, 400).drop();
    }
}
