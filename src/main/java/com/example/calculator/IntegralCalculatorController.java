package com.example.calculator;/*
 * @project Kalkulator
 * @author kola
 */


import com.example.calculator.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class IntegralCalculatorController extends IntegrationMethods implements Initializable {

    @FXML
    public Label lowerLimitFormula;
    @FXML
    public Label upperLimitFormula;
    @FXML
    public Label lowerLimitLabel;
    @FXML
    public Label upperLimitLabel;
    @FXML
    public Label resultSymbol;
    @FXML
    public Label numberOfSubintervalsLabel;
    @FXML
    public Label menu;
    @FXML
    public Label menuClose;
    @FXML
    public Label openGraph;
    @FXML
    public Label closeGraph;
    @FXML
    public TextField numberOfSubintervalsTextField;
    @FXML
    public TextField lowerLimitTextField;
    @FXML
    public TextField upperLimitTextField;
    @FXML
    public TextField integralResultTextField;
    @FXML
    public TextField enteredIntegralFormula;
    @FXML
    public TextField hiddenIntegralFormula;
    @FXML
    public Button scientificCalculatorButton;
    @FXML
    public Button helpButton;
    @FXML
    public Button operationHistoryButtonButton;
    @FXML
    public Button zeroOnAction;
    @FXML
    public Button oneOnAction;
    @FXML
    public Button twoOnAction;
    @FXML
    public Button threeOnAction;
    @FXML
    public Button fourOnAction;
    @FXML
    public Button fiveOnAction;
    @FXML
    public Button sixOnAction;
    @FXML
    public Button sevenOnAction;
    @FXML
    public Button eightOnAction;
    @FXML
    public Button nineOnAction;
    @FXML
    public ChoiceBox<String> unitChoiceBox;
    @FXML
    public ChoiceBox<String> integrationMethodsChoiceBox;
    @FXML
    public VBox vBoxOperationHistory;
    @FXML
    public VBox vBoxMenu;
    @FXML
    public ImageView back;
    @FXML
    public ImageView close;
    @FXML
    public ListView<String> listViewOperationHistory;
    @FXML
    public AnchorPane slider;
    @FXML
    public Pane graphPane;
    @FXML
    public Spinner<Integer> xMinSpinner;
    @FXML
    public Spinner<Integer> xMaxSpinner;
    @FXML
    public WebView webView;
    @FXML
    public WebView webView1;
    @FXML
    public WebView upperLimitWebView;
    @FXML
    public WebView lowerLimitWebView;

    static String upperLimitTransformed = null;
    static String lowerLimitTransformed = null;
    private static String pressedField = String.valueOf(PressedField.INTEGRAL);
    public static String selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
    public static int clickedEnteredIntegralFormula = 0;
    public static int clickedLowerLimitFormula = 0;
    public static int clickedUpperLimitFormula = 0;
    public static int clickedNumberOfSubintervals = 0;
    public static String kindOfCalculator;

    OpenNewWindow window = new OpenNewWindow();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        webView1.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        upperLimitWebView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        lowerLimitWebView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        sideMenu();
        unitOfMeasureChoiceBox();
        integrationMethodsChoiceBox();
        digitsOnAction();
        EventHandler<KeyEvent> handler = new EventHandler<>() {
            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {
                if (willConsume) {
                    event.consume();
                }

                if (event.getCode() == KeyCode.B || event.getCode() == KeyCode.D || event.getCode() == KeyCode.F || event.getCode() == KeyCode.H || event.getCode() == KeyCode.J || event.getCode() == KeyCode.K || event.getCode() == KeyCode.M ||
                        event.getCode() == KeyCode.P || event.getCode() == KeyCode.U || event.getCode() == KeyCode.V || event.getCode() == KeyCode.W || event.getCode() == KeyCode.Y || event.getCode() == KeyCode.Z) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                        willConsume = true;
                    } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                        willConsume = false;
                    }
                }
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
            }
        };
        enteredIntegralFormula.addEventFilter(KeyEvent.ANY, handler);
        upperLimitTextField.addEventFilter(KeyEvent.ANY, handler);
        lowerLimitTextField.addEventFilter(KeyEvent.ANY, handler);
        numberOfSubintervalsTextField.addEventFilter(KeyEvent.ANY, handler);

        EventHandler<MouseEvent> mouseEvent = event -> {
            clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
        };
        enteredIntegralFormula.addEventFilter(MouseEvent.ANY, mouseEvent);
        upperLimitTextField.addEventFilter(MouseEvent.ANY, mouseEvent);
        lowerLimitTextField.addEventFilter(MouseEvent.ANY, mouseEvent);
        numberOfSubintervalsTextField.addEventFilter(MouseEvent.ANY, mouseEvent);

        integrationMethodsChoiceBox.setOnAction(actionEvent -> {
            if (integrationMethodsChoiceBox.getValue().equals("Indefinite integral")) {
                upperLimitTextField.setVisible(false);
                lowerLimitTextField.setVisible(false);
                numberOfSubintervalsTextField.setVisible(false);
                lowerLimitLabel.setVisible(false);
                upperLimitLabel.setVisible(false);
                numberOfSubintervalsLabel.setVisible(false);
                lowerLimitFormula.setVisible(false);
                upperLimitFormula.setVisible(false);
                integralResultTextField.setVisible(false);
                resultSymbol.setVisible(false);
            } else {
                upperLimitTextField.setVisible(true);
                lowerLimitTextField.setVisible(true);
                numberOfSubintervalsTextField.setVisible(true);
                lowerLimitLabel.setVisible(true);
                upperLimitLabel.setVisible(true);
                numberOfSubintervalsLabel.setVisible(true);
                lowerLimitFormula.setVisible(true);
                upperLimitFormula.setVisible(true);
                integralResultTextField.setVisible(true);
                resultSymbol.setVisible(true);
            }
        });
    }

    public boolean numberOfSubintervalsCheck(String numberOfSubintervals) {
        String communique = "";
        boolean error = false;
        try {
            if (numberOfSubintervals.isEmpty()) {
                communique = "The number of subintervals cannot be left blank.";
                error = true;
                throw new Exception(communique);
            } else if (!numberOfSubintervals.matches("\\d")) {
                communique = "The number of subintervals cannot contain letters and special characters. You must enter a natural number.";
                error = true;
                throw new Exception(communique);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, communique, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !error;
    }

    public void menuGraphPane() {
        graphPane.setTranslateX(222);
        sideMenuProperies(closeGraph, graphPane, openGraph);

        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMin.setValue(-4);
        xMinSpinner.setValueFactory(valueFactoryMin);
        SpinnerValueFactory<Integer> valueFactoryMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMax.setValue(4);
        xMaxSpinner.setValueFactory(valueFactoryMax);
    }

    public boolean graphCheck(String formula, int max, int min) {
        boolean error = false;
        String communique = "";
        try {
            if (formula.isEmpty()) {
                communique = "To display a graph, you must first enter a function.";
                error = true;
                throw new Exception(communique);
            }

            if (formula.matches("(.*)ln(.*)") || formula.matches("(.*)log(.*)") || formula.matches("(.*)sqrt(.*)")) {
                if (max < 0 || min < 0) {
                    communique = "The function cannot take negative arguments.";
                    error = true;
                    throw new Exception(communique);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, communique, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !error;
    }

    @FXML
    public void graph() {
        GraphOfTheFunction graphOfTheFunction = new GraphOfTheFunction();
        if (graphCheck(enteredIntegralFormula.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue())) {
            Plot2DPanel plotPanel = new Plot2DPanel();
            double[] x = graphOfTheFunction.listX(enteredIntegralFormula.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue());
            double[] y = graphOfTheFunction.listY(enteredIntegralFormula.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue(), x);

            plotPanel.addLegend("SOUTH");
            plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

            if (!integralResultTextField.getText().isEmpty()) {
                String transformationOfFormula = integralFormula;
                if (transformationOfFormula.matches("(.*)log(.*)")) {
                    transformationOfFormula = transformationOfFormula.replace("log", "sympy.log");
                }
                double[] xP = graphOfTheFunction.listX(transformationOfFormula, xMaxSpinner.getValue(), xMinSpinner.getValue());
                double[] yP = graphOfTheFunction.listY(transformationOfFormula, xMaxSpinner.getValue(), xMinSpinner.getValue(), xP);
                plotPanel.addLinePlot("F(x)", Color.GREEN, xP, yP);
            }

            JFrame frame = new JFrame("Graph");
            frame.setContentPane(plotPanel);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
    }

    public boolean requiredFieldsCheck(String lowerLimit, String upperLimit, String formula, String method, String unitOfMeasure) {
        String communique = "";
        boolean error = false;
        try {
            if (!integrationMethodsChoiceBox.getValue().equals("Indefinite integral")) {
                if (lowerLimit.isEmpty() || upperLimit.isEmpty()) {
                    communique = "The limit of the integral cannot be empty.";
                    error = true;
                    throw new Exception(communique);
                }
                if (formula.isEmpty()) {
                    communique = "The integral formula cannot be empty.";
                    error = true;
                    throw new Exception(communique);
                }
                if (lowerLimit.matches("(.*)∞(.*)")) {
                    if (!lowerLimit.equals("-∞") && !lowerLimit.equals("+∞")) {
                        communique = "Incorrect lower limit value.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
                if (upperLimit.matches("(.*)∞(.*)")) {
                    if (!upperLimit.equals("-∞") && !upperLimit.equals("+∞")) {
                        communique = "Incorrect upper limit value.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
                if (upperLimit.matches("(.*)∞") || lowerLimit.matches("(.*)∞")) {
                    if (!method.equals("Analytical method")) {
                        communique = "Infinity limits can only be used for analytical integration.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
                if (formula.matches("(.*)cos(.*)") || formula.matches("(.*)sin(.*)") || formula.matches("(.*)tan(.*)") || formula.matches("(.*)cot(.*)") ||
                        upperLimit.matches("(.*)cos(.*)") || upperLimit.matches("(.*)sin(.*)") || upperLimit.matches("(.*)tan(.*)") || upperLimit.matches("(.*)cot(.*)") ||
                        lowerLimit.matches("(.*)cos(.*)") || lowerLimit.matches("(.*)sin(.*)") || lowerLimit.matches("(.*)tan(.*)") || lowerLimit.matches("(.*)cot(.*)")) {
                    if (unitOfMeasure.equals("Unity of measure")) {
                        communique = "Please select one unit: degrees or radians.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
                if (formula.matches("(.*)\\?(.*)") || lowerLimit.matches("(.*)\\?(.*)") || upperLimit.matches("(.*)\\?(.*)")) {
                    communique = "Incorrectly typed mathematical operation. In place of '?' enter a numeric value.";
                    error = true;
                    throw new Exception(communique);
                }
            } else {
                if (formula.matches("(.*)cos(.*)") || formula.matches("(.*)sin(.*)") || formula.matches("(.*)tan(.*)") || formula.matches("(.*)cot(.*)")) {
                    if (unitOfMeasure.equals("Unity of measure")) {
                        communique = "Please select one unit: degrees or radians.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
                if (formula.matches("(.*)\\?(.*)")) {
                    communique = "Incorrectly typed mathematical operation. In place of '?' enter a numeric value.";
                    error = true;
                    throw new Exception(communique);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, communique, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return !error;
    }

    public void resultOnAction() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        WebEngine webEngine = webView.getEngine();
        WebEngine webEngine1 = webView1.getEngine();
        WebEngine upperLimit = lowerLimitWebView.getEngine();
        WebEngine lowerLimit = upperLimitWebView.getEngine();

        if (requiredFieldsCheck(lowerLimitTextField.getText(), upperLimitTextField.getText(), enteredIntegralFormula.getText(), integrationMethodsChoiceBox.getValue(), unitChoiceBox.getValue())) {
            if (lowerLimitTextField.getText().matches("\\d")) {
                if (!lowerLimitTextField.getText().matches("(.*)x(.*)")) {
                    lowerLimitTextField.setText(String.valueOf(Float.valueOf(lowerLimitTextField.getText())));
                }
            }
            if (upperLimitTextField.getText().matches("\\d")) {
                if (!upperLimitTextField.getText().matches("(.*)x(.*)")) {
                    upperLimitTextField.setText(String.valueOf(Float.valueOf(upperLimitTextField.getText())));
                }
            }

            if (unitChoiceBox.getValue().equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitChoiceBox.getValue().equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            String upper = (transformation.changingTheEquation(upperLimitTextField.getText()));
            String lower = (transformation.changingTheEquation(lowerLimitTextField.getText()));
            upperLimit.loadContent("<p scroll=\"no\" style=\"font-size: 12px;\">" + transformation.transformationOfTheResult(lower) + "</p>", "text/html");
            lowerLimit.loadContent("<p scroll=\"no\" style=\"font-size: 12px;\">" + transformation.transformationOfTheResult(upper) + "</p>", "text/html");

            webEngine.loadContent("<p scroll=\"no\">(" + transformation.changingTheEquation(enteredIntegralFormula.getText()) + ")dx</p>", "text/html");
            if (integrationMethodsChoiceBox.getValue().equals("Underflow rectangles rule")) {
                if (numberOfSubintervalsCheck(numberOfSubintervalsTextField.getText())) {
                    transformationResult(transformation);
                    if (!underflowingRectanglesRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()).equals("")) {
                        integralResultTextField.setText(underflowingRectanglesRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()));
                        webEngine1.loadContent("<p> <p2 style=\"font-size:23;\">[</p2>" + transformation.transformationOfTheResult(integralFormula) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + transformation.transformationOfTheResult(upperLimitTransformed) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + transformation.transformationOfTheResult(lowerLimitTransformed) + "</sub></p>", "text/html");
                    }
                }
            } else if (integrationMethodsChoiceBox.getValue().equals("Rectangles with excess rule")) {
                if (numberOfSubintervalsCheck(numberOfSubintervalsTextField.getText())) {
                    transformationResult(transformation);
                    if (!rectanglesWithExcessRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()).equals("")) {
                        integralResultTextField.setText(rectanglesWithExcessRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + transformation.transformationOfTheResult(integralFormula) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + transformation.transformationOfTheResult(upperLimitTransformed) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + transformation.transformationOfTheResult(lowerLimitTransformed) + "</sub></p>", "text/html");
                    }
                }
            } else if (integrationMethodsChoiceBox.getValue().equals("Trapezoidal rule")) {
                if (numberOfSubintervalsCheck(numberOfSubintervalsTextField.getText())) {
                    transformationResult(transformation);
                    if (!trapezoidalRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()).equals("")) {
                        integralResultTextField.setText(trapezoidalRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + transformation.transformationOfTheResult(integralFormula) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + transformation.transformationOfTheResult(upperLimitTransformed) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + transformation.transformationOfTheResult(lowerLimitTransformed) + "</sub></p>", "text/html");
                    }
                }
            } else if (integrationMethodsChoiceBox.getValue().equals("Simpson's rule")) {
                if (numberOfSubintervalsCheck(numberOfSubintervalsTextField.getText())) {
                    transformationResult(transformation);
                    if (!SimpsonRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()).equals("")) {
                        integralResultTextField.setText(SimpsonRule(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed, numberOfSubintervalsTextField.getText()));
                        webEngine1.loadContent("<p><p2 style=\"font-size:23;\">[</p2>" + transformation.transformationOfTheResult(integralFormula) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + transformation.transformationOfTheResult(upperLimitTransformed) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + transformation.transformationOfTheResult(lowerLimitTransformed) + "</sub></p>", "text/html");
                    }
                }
            } else if (integrationMethodsChoiceBox.getValue().equals("Analytical method")) {
                numberOfSubintervalsTextField.clear();
                transformationResult(transformation);
                if (!analyticalMethod(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed).equals("")) {
                    integralResultTextField.setText(analyticalMethod(hiddenIntegralFormula.getText(), lowerLimitTransformed, upperLimitTransformed));
                    webEngine1.loadContent("<p scroll=\"no\">[" + transformation.transformationOfTheResult(integralFormula) + "<p2 style=\"font-size:23;\">]</p2><sup style=\"position: relative; left: 3px; top:-4px;\">" + transformation.transformationOfTheResult(upperLimitTransformed) + "</sup><sub style=\"position: relative; left: -15px; top: 3px;\">" + transformation.transformationOfTheResult(lowerLimitTransformed) + "</sub></p>", "text/html");
                }
            } else if (integrationMethodsChoiceBox.getValue().equals("Indefinite integral")) {
                transformationResult(transformation);
                if (!indefiniteIntegral(hiddenIntegralFormula.getText()).equals("")) {
                    integralResultTextField.setText(indefiniteIntegral(hiddenIntegralFormula.getText()));
                    webEngine1.loadContent("<p scroll=\"no\">" + transformation.transformationOfTheResult(integralFormula) + "</p>", "text/html");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select an integration method.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            listViewOperationHistory.getItems().addAll(enteredIntegralFormula.getText());
        }
    }

    public void transformationResult(TransformationOfTheEquation transformation) {
        lowerLimitTransformed = transformation.transformationOfTheEquation(lowerLimitTextField.getText(), "sympy.", selectedUnityOfMeasure);
        upperLimitTransformed = transformation.transformationOfTheEquation(upperLimitTextField.getText(), "sympy.", selectedUnityOfMeasure);
        hiddenIntegralFormula.setText(transformation.transformationOfTheEquation(enteredIntegralFormula.getText(), "sympy.", selectedUnityOfMeasure));
        if (Objects.equals(transformation.unitOfMeasure, "RADIANS")) {
            unitChoiceBox.setValue("Radians");
        } else if (Objects.equals(transformation.unitOfMeasure, "DEGREES")) {
            unitChoiceBox.setValue("Degrees");
        }
    }


    public void piOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "π");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "π");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "π");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void plusOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "+");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "+");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "+");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void minusOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "-");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "-");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "-");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void multiplicationOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "*");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "*");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "*");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void divisionOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "/");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "/");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "/");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void leftBracketOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "(");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "(");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "(");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void rightBracketOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, ")");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, ")");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, ")");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void factorialOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "!");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "!");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "!");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void eulerOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "e");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "e");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "e");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void logarithmOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "log[?][?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "log[?][?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "log[?][?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void naturalLogarithmOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "ln[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "ln[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "ln[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void squareRootOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "sqrt[2,?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "sqrt[2,?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "sqrt[2,?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void NrootOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "sqrt[?,?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "sqrt[?,?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "sqrt[?,?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void NpowerOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "^[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "^[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "^[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void squarePowerOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "^[2]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "^[2]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "^[2]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void sinOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "sin[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "sin[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "sin[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void cosOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "cos[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "cos[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "cos[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void cotOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "cot[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "cot[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "cot[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void atanOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "atan[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "atan[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "atan[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void acosOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "acos[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "acos[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "acos[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void acotOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "acot[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "acot[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "acot[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void asinOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "asin[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "asin[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "asin[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void tanOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "tan[?]");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "tan[?]");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "tan[?]");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void infinityOnAction() {
        String communique = "";
        try {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "∞");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "∞");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    communique = "The infinity sign cannot be written into the integral formula.";
                    throw new Exception(communique);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, communique, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void operationHistoryOnMouseClicked() {
        listViewOperationHistory.setOnMouseClicked(event2 -> enteredIntegralFormula.setText(listViewOperationHistory.getSelectionModel().getSelectedItem()));
    }

    public void digitsOnAction() {
        zeroOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "0");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "0");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "0");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "0");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        oneOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "1");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "1");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "1");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "1");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        twoOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "2");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "2");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "2");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "2");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        threeOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "3");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "3");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "3");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "3");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        fourOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "4");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "4");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "4");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "4");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        fiveOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "5");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "5");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "5");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "5");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        sixOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "6");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "6");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "6");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "6");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        sevenOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "7");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "7");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "7");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "7");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        eightOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "8");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "8");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "8");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "8");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });

        nineOnAction.setOnAction(actionEvent -> {
            switch (pressedField) {
                case "UPPER_LIMIT" -> {
                    upperLimitTextField.insertText(clickedUpperLimitFormula, "9");
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
                case "LOWER_LIMIT" -> {
                    lowerLimitTextField.insertText(clickedLowerLimitFormula, "9");
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
                case "INTEGRAL" -> {
                    enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "9");
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
                case "NUMBER_OF_SUBINTERVALS" -> {
                    numberOfSubintervalsTextField.insertText(clickedNumberOfSubintervals, "9");
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        });
    }

    public void dotOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, ".");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, ".");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, ".");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void commaOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, ",");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, ",");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, ",");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void xOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                upperLimitTextField.insertText(clickedUpperLimitFormula, "x");
                clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
            }
            case "LOWER_LIMIT" -> {
                lowerLimitTextField.insertText(clickedLowerLimitFormula, "x");
                clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
            }
            case "INTEGRAL" -> {
                enteredIntegralFormula.insertText(clickedEnteredIntegralFormula, "x");
                clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
            }
        }
    }

    public void removeAllOnAction() {
        enteredIntegralFormula.clear();
        integralResultTextField.clear();
        lowerLimitTextField.clear();
        upperLimitTextField.clear();
        numberOfSubintervalsTextField.clear();
        lowerLimitFormula.setText("");
        upperLimitFormula.setText("");
        webView.getEngine().load("");
        webView1.getEngine().load("");
    }

    public void deleteLastOnAction() {
        switch (pressedField) {
            case "UPPER_LIMIT" -> {
                if (upperLimitTextField.getLength() > 0) {
                    upperLimitTextField.setText(upperLimitTextField.getText(0, upperLimitTextField.getLength() - 1));
                    clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
                }
            }
            case "LOWER_LIMIT" -> {
                if (lowerLimitTextField.getLength() > 0) {
                    lowerLimitTextField.setText(lowerLimitTextField.getText(0, lowerLimitTextField.getLength() - 1));
                    clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
                }
            }
            case "INTEGRAL" -> {
                if (enteredIntegralFormula.getLength() > 0) {
                    enteredIntegralFormula.setText(enteredIntegralFormula.getText(0, enteredIntegralFormula.getLength() - 1));
                    clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
                }
            }
            case "NUMBER_OF_SUBINTERVALS" -> {
                if (numberOfSubintervalsTextField.getLength() > 0) {
                    numberOfSubintervalsTextField.setText(numberOfSubintervalsTextField.getText(0, numberOfSubintervalsTextField.getLength() - 1));
                    clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
                }
            }
        }
    }

    public void clickedIntegralField() {
        pressedField = String.valueOf(PressedField.INTEGRAL);
        clickedEnteredIntegralFormula = enteredIntegralFormula.getCaretPosition();
    }

    public void clickedLowerLimit() {
        pressedField = String.valueOf(PressedField.LOWER_LIMIT);
        clickedLowerLimitFormula = lowerLimitTextField.getCaretPosition();
    }

    public void clickedUpperLimit() {
        pressedField = String.valueOf(PressedField.UPPER_LIMIT);
        clickedUpperLimitFormula = upperLimitTextField.getCaretPosition();
    }

    public void clickedNumberOfSubintervals() {
        pressedField = String.valueOf(PressedField.NUMBER_OF_SUBINTERVALS);
        clickedNumberOfSubintervals = numberOfSubintervalsTextField.getCaretPosition();
    }

    public void unitOfMeasureChoiceBox() {
        String a = "Unit of measure";
        String b = "Radians";
        String c = "Degrees";
        unitChoiceBox.getItems().addAll(b, c);
        unitChoiceBox.setValue(a);
    }

    public void integrationMethodsChoiceBox() {
        String a = "Integration methods";
        String b = "Analytical method";
        String c = "Rectangles with excess rule";
        String d = "Underflow rectangles rule";
        String e = "Trapezoidal rule";
        String f = "Simpson's rule";
        String g = "Indefinite integral";
        integrationMethodsChoiceBox.getItems().addAll(b, c, d, e, f, g);
        integrationMethodsChoiceBox.setValue(a);
    }

    public void sideMenu() {
        menuGraphPane();
        close.setOnMouseClicked(event -> System.exit(0));

        slider.setTranslateX(222);
        sideMenuProperies(menuClose, slider, menu);

        vBoxOperationHistory.setTranslateX(222);
        operationHistoryButtonButton.setOnMouseClicked(event -> {
            vBoxOperationHistory.setVisible(true);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxOperationHistory);
            transition.setToX(0);
            transition.play();
        });

        back.setOnMouseClicked(event -> {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxOperationHistory);
            transition.setToX(222);
            transition.play();
            transition.setOnFinished((ActionEvent e) -> vBoxOperationHistory.setVisible(false));
        });
    }

    static void sideMenuProperies(Label menuClose, Pane slider, Label menu) {
        menuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(222);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuClose.setVisible(false);
            });
        });

        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(222);
            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuClose.setVisible(true);
            });
        });
    }

    public void scientificCalculatorOnAction() {
        Stage stage = (Stage) scientificCalculatorButton.getScene().getWindow();
        stage.close();

        window.openWindow("ScientificCalculator",1035,653,443,129);
    }

    public void helpOnAction() {
        kindOfCalculator = "Integral calculator";

        window.openWindow("Help",600,600,1200,300);
    }


}