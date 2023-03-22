package com.example.calculator;

import com.example.calculator.IntegralCalculatorController;
import com.example.calculator.GraphOfTheFunction;
import com.example.calculator.OpenNewWindow;
import com.example.calculator.TransformationOfTheEquation;
import com.example.calculator.UnitOfMeasure;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import jep.Jep;
import org.math.plot.Plot2DPanel;
import org.python.core.PyException;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ScientificCalculatorController implements Initializable {

    @FXML
    public TextField enteredMathematicalOperation;
    @FXML
    public WebView webView;
    @FXML
    public WebView webView2;
    @FXML
    public Label menu;
    @FXML
    public Label menuClose;
    @FXML
    public Label openGraph;
    @FXML
    public Label closeGraph;
    @FXML
    public ImageView closeWindow;
    @FXML
    public ImageView back;
    @FXML
    public AnchorPane slider;
    @FXML
    public Button integralCalculatorButton;
    @FXML
    public Button helpButton;
    @FXML
    public Button operationHistoryButton;
    @FXML
    public Button xID;
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
    public CheckBox derivativeCheckBox;
    @FXML
    public ChoiceBox<String> unityOfMeasureChoiceBox;
    @FXML
    public VBox vBoxOperationHistory;
    @FXML
    public VBox vBoxMenu;
    @FXML
    public ListView<String> listViewOperationHistory;
    @FXML
    public Pane graphPane;
    @FXML
    public Spinner<Integer> xMinSpinner;
    @FXML
    public Spinner<Integer> xMaxSpinner;

    static String transformationOfEnteredMathematicalOperation = null;
    static String functionFormula = null;
    public static String selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
    public static int clickedEnteredMathematicalOperation = 0;

    OpenNewWindow window = new OpenNewWindow();

    public void resultOnAction() throws PyException {
        WebEngine webEngine = webView.getEngine();
        WebEngine webEngine2 = webView2.getEngine();

        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        if (unityOfMeasureChoiceBox.getValue().equals("Degrees")) {
            selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
        } else if (unityOfMeasureChoiceBox.getValue().equals("Radians")) {
            selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        }
        transformationOfEnteredMathematicalOperation = transformation.transformationOfTheEquation(enteredMathematicalOperation.getText(), "sympy.", selectedUnityOfMeasure);

        if (enteredMathematicalOperationCheck(enteredMathematicalOperation.getText(), derivativeCheckBox.isSelected(), unityOfMeasureChoiceBox.getValue())) {
            if (!derivativeCheckBox.isSelected() && !calculatorResult(transformationOfEnteredMathematicalOperation).equals("")) {
                webEngine.loadContent("<p scroll=\"no\">" + transformation.changingTheEquation(enteredMathematicalOperation.getText()) + "</p>", "text/html");
                webEngine2.loadContent("<p scroll=\"no\">" + transformation.transformationOfTheResult(calculatorResult(transformationOfEnteredMathematicalOperation)) + "</p>", "text/html");
            }
            if (derivativeCheckBox.isSelected() && !derivativeResult(transformationOfEnteredMathematicalOperation).equals("")) {
                webEngine.loadContent("<p scroll=\"no\">(" + transformation.changingTheEquation(enteredMathematicalOperation.getText()) + ")'</p>", "text/html");
                webEngine2.loadContent("<p scroll=\"no\">" + transformation.transformationOfTheResult(derivativeResult(transformationOfEnteredMathematicalOperation)) + "</p>", "text/html");
            }
            listViewOperationHistory.getItems().addAll(enteredMathematicalOperation.getText());
        }

    }

    public boolean enteredMathematicalOperationCheck(String formula, boolean derivative, String unityOfMeasure) {
        boolean error = false;
        String communique = "";
        try {

            if (formula.isEmpty()) {
                communique = "Please enter a mathematical operation.";
                error = true;
                throw new Exception(communique);
            }

            if (formula.matches("(.*)\\?(.*)")) {
                communique = "Incorrectly typed mathematical operation. In place of '?' enter a numeric value.";
                error = true;
                throw new Exception(communique);
            }

            if (!derivative && formula.matches("(.*)x(.*)")) {
                communique = "Incorrectly typed mathematical operation. The 'x' variable can only be used when calculating the derivative.\n" +
                        "Go to the menu and select the appropriate function or correct the entered action.";
                error = true;
                throw new Exception(communique);
            }

            if (unityOfMeasure.equals("Unity of measure") && (formula.matches("(.*)cos(.*)") || formula.matches("(.*)sin(.*)") || formula.matches("(.*)tan(.*)") || formula.matches("(.*)cot(.*)")) && !derivative) {
                communique = "When using trigonometric functions, select the unit in which the angle is given: degrees or radians.\n" +
                        "Go to the menu and select the appropriate option in the 'Unit' tab.";
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

        IntegralCalculatorController.sideMenuProperies(closeGraph, graphPane, openGraph);

        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMin.setValue(-4);
        xMinSpinner.setValueFactory(valueFactoryMin);
        SpinnerValueFactory<Integer> valueFactoryMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(-9999, 9999);
        valueFactoryMax.setValue(4);
        xMaxSpinner.setValueFactory(valueFactoryMax);
    }

    public boolean graphCheck(String formula, int max, int min, String formulaOfResult) {
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
            if (formulaOfResult != null) {
                if (formulaOfResult.matches("(.*)ln(.*)") || formulaOfResult.matches("(.*)sqrt(.*)")) {
                    if (max < 0 || min < 0) {
                        communique = "The function cannot take negative arguments.";
                        error = true;
                        throw new Exception(communique);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, communique, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }

    public void graph() {
        GraphOfTheFunction graphOfTheFunction = new GraphOfTheFunction();

        if (!graphCheck(enteredMathematicalOperation.getText(), xMaxSpinner.getValue(), xMinSpinner.getValue(), functionFormula)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                String transformationOfFormula = enteredMathematicalOperation.getText();

                double[] x = graphOfTheFunction.listX(transformationOfFormula, xMaxSpinner.getValue(), xMinSpinner.getValue());
                double[] y = graphOfTheFunction.listY(transformationOfFormula, xMaxSpinner.getValue(), xMinSpinner.getValue(), x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                if (derivativeCheckBox.isSelected() && !transformationOfEnteredMathematicalOperation.isEmpty()) {
                    String transformationOfFunction = functionFormula;
                    if (transformationOfFunction.matches("(.*)log(.*)")) {
                        transformationOfFunction = transformationOfFunction.replace("log", "sympy.log");
                    }
                    double[] xP = graphOfTheFunction.listX(transformationOfFunction, xMaxSpinner.getValue(), xMinSpinner.getValue());
                    double[] yP = graphOfTheFunction.listY(transformationOfFunction, xMaxSpinner.getValue(), xMinSpinner.getValue(), xP);
                    plotPanel.addLinePlot("f'(x))", Color.GREEN, xP, yP);
                }

                JFrame frame = new JFrame("Graph");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("Exception: " + w);
            }
        }
    }

    public String derivativeResult(String formula) {
        if (!formula.matches("(.*)x(.*)")) {
            functionFormula = "0";
        } else {
            try (Jep jep = new Jep() {
            }) {
                jep.exec("""
                        import math
                        import sympy
                        x=sympy.Symbol('x')
                        c=(""" + formula + """
                        ).diff(x)
                        """);
                functionFormula = String.valueOf(jep.getValue("c"));
            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e);
                functionFormula = "";
                JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        return functionFormula;
    }

    public String calculatorResult(String formula) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                    c=round(float(""" + formula + """
                    ),15)
                    """);
            result = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
       webView2.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());

        sideMenu();
        choiceBoxUnity();
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
                clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
            }
        };
        enteredMathematicalOperation.addEventFilter(KeyEvent.ANY, handler);
        EventHandler<MouseEvent> mouseEvent = event -> clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        enteredMathematicalOperation.addEventFilter(MouseEvent.ANY, mouseEvent);

    }

    public void sideMenu() {
        menuGraphPane();
        closeWindow.setOnMouseClicked(event -> System.exit(0));

        slider.setTranslateX(222);

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

        vBoxOperationHistory.setTranslateX(222);
        operationHistoryButton.setOnMouseClicked(event -> {
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

    public void choiceBoxUnity() {
        String a = "Unity of measure";
        String b = "Radians";
        String c = "Degrees";
        unityOfMeasureChoiceBox.getItems().addAll(b, c);
        unityOfMeasureChoiceBox.setValue(a);
    }


    public void digitsOnAction() {
        zeroOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "0");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        oneOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "1");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        twoOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "2");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        threeOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "3");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        fourOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "4");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        fiveOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "5");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        sixOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "6");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        sevenOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "7");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        eightOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "8");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });

        nineOnAction.setOnAction(actionEvent -> {
            enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "9");
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        });
    }

    public void helpOnAction() {
        IntegralCalculatorController.kindOfCalculator = "Scientific calculator";

        window.openWindow("Help",600,600,1200,300);
    }

    public void integrationCalculatorOnAction() {
        Stage stage = (Stage) integralCalculatorButton.getScene().getWindow();
        stage.close();

        window.openWindow("IntegralCalculator",1035,653,443,129);
    }

    public void operationHistoryOnMouseClicked() {
        listViewOperationHistory.setOnMouseClicked(event2 -> enteredMathematicalOperation.setText(listViewOperationHistory.getSelectionModel().getSelectedItem()));
    }

    public void piOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "π");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void plusOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "+");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void minusOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "-");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void multiplicationOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "*");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void divisionOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "/");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void leftBracketOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "(");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void rightBracketOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, ")");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void factorialOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "!");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void eulerOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "e");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void logarithmOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "log[?][?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void naturalLogarithmOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "ln[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void squareRootOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "sqrt[2,?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void NrootOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "sqrt[?,?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void NpowerOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "^[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void squarePowerOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "^[2]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void sinOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "sin[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void cosOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "cos[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void tanOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "tan[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void cotOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "cot[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void asinOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "asin[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void atanOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "atan[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void acosOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "acos[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void acotOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "acot[?]");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void percentOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "%");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void dotOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, ".");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void commaOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, ",");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }

    public void xOnAction() {
        enteredMathematicalOperation.insertText(clickedEnteredMathematicalOperation, "x");
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }


    public void removeAllOnAction() {
        enteredMathematicalOperation.clear();
        webView.getEngine().load("");
        webView2.getEngine().load("");
        clickedEnteredMathematicalOperation = 0;
    }

    public void deleteLastOnAction() {
        if (enteredMathematicalOperation.getLength() > 0) {
            enteredMathematicalOperation.setText(enteredMathematicalOperation.getText(0, enteredMathematicalOperation.getLength() - 1));
            clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
        }
    }

    public void xDisable() {
        xID.setDisable(!derivativeCheckBox.isSelected());
    }

    public void clickedOnAction() {
        clickedEnteredMathematicalOperation = enteredMathematicalOperation.getCaretPosition();
    }
}