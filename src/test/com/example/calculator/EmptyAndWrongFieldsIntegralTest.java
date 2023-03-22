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
public class EmptyAndWrongFieldsIntegralTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(EmptyAndWrongFieldsIntegralTest.class.getResource("fxml/Home.fxml")));

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
    public void emptyOperationTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("3]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("2]");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void upperLimitErrorOperationTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula").write("3]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#eulerID");
        robot.clickOn("#infinityID");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#fourOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#removeAllID");
    }


    @Test
    void lowerLimitErrorOperationTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula").write("3]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#eulerID");
        robot.clickOn("#infinityID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#fourOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void integralFormulaErrorOperationTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#infinityID");
    }

    @Test
    public void infinityWithNoChosenMethodTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#oneOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula").write("3]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#minusID");
        robot.clickOn("#infinityID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#fourOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void operationWithQuestionMarkAndNoSelectedMethodTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#cosID");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#cosID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#cosID");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void emptyOperationWithSelectedMethodTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(100);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Indefinite integral");
        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Underflow rectangles rule");
        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Rectangles with excess rule");
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
    }

    @Test
    public void operationWithQuestionMarkAndSelectedMethodTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(100);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Rectangles with excess rule");
        robot.clickOn("#removeAllID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#NrootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula").write("3");
        robot.clickOn("#commaID");
        robot.clickOn("#enteredIntegralFormula").write("?]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#logID");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#logID");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void emptyNumberOfSubintervalsTextFieldTest(FxRobot robot){
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(100);

        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Underflow rectangles rule");
        robot.clickOn("#removeAllID");
        robot.clickOn("#enteredIntegralFormula");
        robot.clickOn("#logID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredIntegralFormula").write("2][34]");
        robot.clickOn("#lowerLimitTextField");
        robot.clickOn("#NrootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write("3");
        robot.clickOn("#commaID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#lowerLimitTextField").write(",27]");
        robot.clickOn("#upperLimitTextField");
        robot.clickOn("#NrootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write("2");
        robot.clickOn("#commaID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#upperLimitTextField").write(",16]");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void wrongNumberOfSubintervalsTextFieldTest(FxRobot robot) {
        robot.clickOn("#integralCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(100);
        robot.clickOn("#integrationMethodsChoiceBox");
        robot.clickOn("Underflow rectangles rule");

        robot.clickOn("#lowerLimitTextField").write("27");
        robot.clickOn("#upperLimitTextField").write("60");
        robot.clickOn("#numberOfSubintervalsTextField").write("zez");
        robot.clickOn("#enteredIntegralFormula").write("x*2");

        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#numberOfSubintervalsTextField");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredIntegralFormula").write("zez");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
}
