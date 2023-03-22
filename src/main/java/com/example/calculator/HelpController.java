package com.example.calculator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    @FXML
    public ImageView closeHelp;
    @FXML
    public WebView webView;
    @FXML
    public Label labelKindOfCalculator;
    @FXML
    public Label labelDescription;

    @FXML
    public void closeOnAction() {
        Stage stage = (Stage) closeHelp.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (IntegralCalculatorController.kindOfCalculator.equals("Scientific calculator")) {
            WebEngine webEngine = webView.getEngine();

            URL url2 = this.getClass().getResource("css/ScientificCalculatorHelp.html");
            webEngine.load(Objects.requireNonNull(url2).toString());

            labelKindOfCalculator.setText(IntegralCalculatorController.kindOfCalculator);
            labelDescription.setText("The scientific calculator is designed for simple operations as well as more complex ones, such as: addition, subtraction, multiplication, " +
                    "division, square root, exponentiation, logarithm, factorial calculations, trigonometry operations and derivative calculations.");

        } else if (IntegralCalculatorController.kindOfCalculator.equals("Integral calculator")) {
            WebEngine webEngine = webView.getEngine();

            URL url2 = this.getClass().getResource("css/IntegralCalculatorHelp.html");
            webEngine.load(Objects.requireNonNull(url2).toString());

            labelKindOfCalculator.setText(IntegralCalculatorController.kindOfCalculator);
            labelDescription.setText("Integral Calculator is designed to calculate indefinite and definite integrals. The definite integral can be determined by numerical methods: " +
                    "rectangles with no excess, rectangles with excess, trapezoids, Simpson and the analytical method. In order to correctly calculate the definite integral, " +
                    "it is necessary to complete the fields concerning the lower and upper limits, the integral formula and, in the case of using numerical methods, the number of subintervals. " +
                    "In the case of an indefinite integral, only the field with the integral formula needs to be filled in.");
        }
    }
}
