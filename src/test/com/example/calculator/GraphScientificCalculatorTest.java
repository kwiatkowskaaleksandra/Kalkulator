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
public class GraphScientificCalculatorTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(GraphScientificCalculatorTest.class.getResource("fxml/Home.fxml")));

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
    void emptyOperationGraphTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.moveTo(1050, 653);
        robot.press(MouseButton.PRIMARY).moveTo(650, 700).drop();
        robot.sleep(2000);

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#openGraph");
        robot.sleep(2000);
        robot.clickOn("#drawID");
        robot.sleep(3000);
    }

    @Test
    void naturalLogWithNegativeXTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#naturalLogID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("2]");

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#openGraph");
        robot.sleep(2000);
        robot.clickOn("#drawID");
        robot.sleep(3000);
    }

    @Test
    void graphOfTheFunctionTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#squareRootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredMathematicalOperation").write("]");

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#openGraph");
        robot.sleep(5000);
        robot.clickOn("#drawID");
        robot.sleep(3000);
    }

    @Test
    void functionAndDerivativeGraphTest(FxRobot robot) {
        robot.clickOn("#scientificCalculator");

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#twoOnAction");
        robot.clickOn("#plusID");
        robot.clickOn("#squareRootID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#threeOnAction");
        robot.clickOn("#enteredMathematicalOperation").write("]");

        robot.clickOn("#menu");
        robot.sleep(2000);
        robot.clickOn("#derivativeCheckBox");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#openGraph");
        robot.sleep(5000);
        robot.clickOn("#drawID");
        robot.sleep(3000);

        robot.clickOn("#closeGraph");
        robot.sleep(3000);
    }
}
