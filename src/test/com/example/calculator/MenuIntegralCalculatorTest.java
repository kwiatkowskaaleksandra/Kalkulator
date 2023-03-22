package com.example.calculator;/*
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
public class MenuIntegralCalculatorTest extends ApplicationTest {

    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MenuIntegralCalculatorTest.class.getResource("fxml/Home.fxml")));

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
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#helpButton");
        robot.moveTo(1250, 350);
        robot.press(MouseButton.PRIMARY).moveTo(1300, 400).drop();
        robot.clickOn("#closeHelp");

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Underflow rectangles rule");
        robot.clickOn("#enteredIntegralFormula").write("2*3+1");
        robot.clickOn("#upperLimitTextField").write("6");
        robot.clickOn("#lowerLimitTextField").write("2");
        robot.clickOn("#numberOfSubintervalsTextField").write("2");
        robot.clickOn("#resultID");
        robot.clickOn("#removeAllID");
        robot.clickOn("#operationHistoryButtonButton");
        robot.sleep(2000);
        robot.clickOn("2*3+1");
        robot.sleep(500);
        robot.clickOn("#back");
        robot.sleep(500);

        robot.clickOn("#enteredIntegralFormula").write("2*3+1");
        robot.clickOn("#upperLimitTextField").write("6");
        robot.clickOn("#lowerLimitTextField").write("2");
        robot.clickOn("#numberOfSubintervalsTextField").write("2");
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Degrees");
        robot.clickOn("#resultID");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#resultID");
        robot.sleep(500);

        robot.clickOn("#menuClose");
        robot.sleep(500);
        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#scientificCalculatorButton");
        robot.sleep(2000);
        robot.moveTo(1250, 350);
        robot.press(MouseButton.PRIMARY).moveTo(1300, 400).drop();
    }
}
