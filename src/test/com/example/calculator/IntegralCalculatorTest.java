package com.example.calculator;/*
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
public class IntegralCalculatorTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(IntegralCalculatorTest.class.getResource("fxml/Home.fxml")));

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
    public void integralCalculatorTest1(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#leftBracketID");
        robot.clickOn("#eulerID");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#fiveOnAction");
        robot.clickOn("#factorialID");
        robot.clickOn("#rightBrecketID");
        robot.clickOn("#minusID");
        robot.clickOn("#sinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#piID");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#squareRootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write(",12]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#squareRootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write(",15]");
        robot.clickOn("#numberOfSubintervalsTextField");
        robot.clickOn("#fiveOnAction");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#sixOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Rectangles with excess rule");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Underflow rectangles rule");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Trapezoidal rule");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Analytical method");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Indefinite integral");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
}
