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
public class ScientificCalculatorTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ScientificCalculatorTest.class.getResource("fxml/Home.fxml")));

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
    public void scientificCalculatorCorrectOperationTest1(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#leftBracketID");
        robot.clickOn("#fiveOnAction");
        robot.clickOn("#factorialID");
        robot.clickOn("#multiplicationID");
        robot.clickOn("#NrootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#sixOnAction");
        robot.clickOn("#commaID");
        robot.clickOn("#sevenOnAction");
        robot.clickOn("#enteredMathematicalOperation").write("]");
        robot.clickOn("#rightBracketID");
        robot.clickOn("#divisionID");
        robot.clickOn("#eulerID");

        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#removeAllID");
    }

    @Test
    public void scientificCalculatorCorrectOperationTest2(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#zeroOnAction");
        robot.clickOn("#NpowerID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("3]-");
        robot.clickOn("#eightOnAction");
        robot.clickOn("#squarePowerID");
        robot.clickOn("#enteredMathematicalOperation").write("+");
        robot.clickOn("#nineOnAction");
        robot.clickOn("#percentID");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void scientificCalculatorCorrectOperationTest3(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#derivativeCheckBox");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#xID");
        robot.clickOn("#minusID");
        robot.clickOn("#oneOnAction");
        robot.clickOn("#dotID");
        robot.clickOn("#oneOnAction");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void scientificCalculatorCorrectOperationTest4(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);
        robot.clickOn("#menu");
        robot.sleep(500);

        robot.clickOn("#unityOfMeasureChoiceBox");
        robot.clickOn("Degrees");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#sinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("30]+");
        robot.clickOn("#asinID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("0]-");
        robot.clickOn("#acosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("0.1]+");
        robot.clickOn("#cotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("x]");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }


    @Test
    public void scientificCalculatorCorrectOperationTest5(FxRobot robot) {
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);
        robot.clickOn("#menu");
        robot.sleep(500);

        robot.clickOn("#unityOfMeasureChoiceBox");
        robot.clickOn("Radians");
        robot.clickOn("#enteredMathematicalOperation").write("1/2+");
        robot.clickOn("#tanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#piID");
        robot.clickOn("#enteredMathematicalOperation").write("]-");
        robot.clickOn("#atanID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("1]-");
        robot.clickOn("#acotID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("0.3]");

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }
}
