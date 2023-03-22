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
public class IntegralCalculatorTrigonometricFunctionsTest extends ApplicationTest {
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
    public void integralCalculatorTrigonometricFunctionsTest1(FxRobot robot) {
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
        robot.clickOn("#sinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#cosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#sinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#upperLimitTextField").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#sinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#lowerLimitTextField").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
    @Test
    public void integralCalculatorTrigonometricFunctionsTest2(FxRobot robot){
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
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#cosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("2]");
        robot.clickOn("#minusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#cosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("0]");
        robot.clickOn("#minusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
    @Test
    public void integralCalculatorTrigonometricFunctionsTest3(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#unitChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Simpson's rule");

        robot.clickOn("#numberOfSubintervalsTextField");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("3]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("5]");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void integralCalculatorTrigonometricFunctionsTest4(FxRobot robot){
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
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#plusID");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("10]");
        robot.clickOn("#divisionID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("1]");
        robot.clickOn("#divisionID");
        robot.clickOn("#sevenOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
}
