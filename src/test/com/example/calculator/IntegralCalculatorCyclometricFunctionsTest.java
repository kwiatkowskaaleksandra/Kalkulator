package com.example.calculator;/*
 * @project Kalkulator.iml
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
public class IntegralCalculatorCyclometricFunctionsTest extends ApplicationTest {
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
    public void integralCalculatorTrigonometricCyclometricTest1(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#asinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#acosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#asinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#upperLimitTextField").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#asinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#lowerLimitTextField").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#oneOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void integralCalculatorTrigonometricCyclometricTest2(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#acosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("1]");
        robot.clickOn("#minusID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#acosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("0]");
        robot.clickOn("#minusID");
        robot.clickOn("#sevenOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void integralCalculatorTrigonometricCyclometricTest3(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]+xk");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("1]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("0]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#sevenOnAction");

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

    @Test
    public void integralCalculatorTrigonometricCyclometricTest4(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]+x");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("1]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("0]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#sevenOnAction");

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

    @Test
    public void integralCalculatorTrigonometricCyclometricTest5(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#xID");
        robot.clickOn("#divisionID");
        robot.clickOn("#eightOnAction");
        robot.clickOn("#nineOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#sixOnAction");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#fiveOnAction");
        robot.clickOn("#sixOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#leftBracketID");
        robot.clickOn("#piID");
        robot.clickOn("#plusID");
        robot.clickOn("#eightOnAction");
        robot.clickOn("#factorialID");
        robot.clickOn("#rightBrecketID");
        robot.clickOn("#plusID");
        robot.clickOn("#nineOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#xID");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#sixOnAction");
        robot.clickOn("#fiveOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#leftBracketID");
        robot.clickOn("#piID");
        robot.clickOn("#plusID");
        robot.clickOn("#eightOnAction");
        robot.clickOn("#factorialID");
        robot.clickOn("#rightBrecketID");
        robot.clickOn("#plusID");
        robot.clickOn("#nineOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#xID");
        robot.clickOn("#numberOfSubintervalsTextField");
        robot.clickOn("#nineOnAction");
        robot.clickOn("#eightOnAction");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void integralCalculatorTrigonometricCyclometricTest6(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Degrees");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("0]");
        robot.clickOn("#divisionID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("1]");
        robot.clickOn("#divisionID");
        robot.clickOn("#sevenOnAction");

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
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Rectangles with excess rule");
        robot.clickOn("#removeAllID");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula").write("tan[π]");
        robot.clickOn("#upperLimitTextField").write("10");
        robot.clickOn("#lowerLimitTextField").write("2");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#removeAllID");

        robot.clickOn("#numberOfSubintervalsTextField").write("4");
        robot.clickOn("#enteredIntegralFormula").write("tan[22]");
        robot.clickOn("#upperLimitTextField").write("10");
        robot.clickOn("#lowerLimitTextField").write("2");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

}
