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
public class EmptyAndWrongFieldsScientificTest extends ApplicationTest {
    public double x, y;

    @Start
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(EmptyAndWrongFieldsScientificTest.class.getResource("fxml/Home.fxml")));

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
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void operationWithQuestionMarkTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#logID");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void operationWithXWithNoDerivativeSelectedTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation").write("x");
        robot.clickOn("#minusID");
        robot.clickOn("#fourOnAction");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

    @Test
    public void trigonometricOperationWithNoSelectedUnitTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation");
        robot.clickOn("#cosID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("30]");
        robot.clickOn("#resultID");
        robot.sleep(3000);
        robot.clickOn("#removeAllID");
    }

    @Test
    public void wrongOperationTest(FxRobot robot){
        robot.clickOn("#scientificCalculator");
        robot.sleep(2000);

        robot.clickOn("#enteredMathematicalOperation").write("2*.");
        robot.clickOn("#resultID");
        robot.sleep(3000);

        robot.clickOn("#deleteLastID");
        robot.clickOn("#deleteLastID");
        robot.clickOn("#enteredMathematicalOperation").write("2x.");
        robot.clickOn("#menu");
        robot.sleep(500);
        robot.clickOn("#derivativeCheckBox");
        robot.clickOn("#resultID");
        robot.sleep(3000);
    }

}
