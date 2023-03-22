package com.example.calculator;

import com.example.calculator.OpenNewWindow;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
    public Button scientificCalculator;
    public Button integralCalculator;

    OpenNewWindow window = new OpenNewWindow();

    public void ScientificCalculatorOnAction() {
        Stage stage = (Stage) scientificCalculator.getScene().getWindow();
        stage.close();

        window.openWindow("ScientificCalculator",1035,653,443,129);
    }

    public void IntegralCalculatorOnAction() {
        Stage stage = (Stage) integralCalculator.getScene().getWindow();
        stage.close();

        window.openWindow("IntegralCalculator",1035,653,443,129);
    }

}
