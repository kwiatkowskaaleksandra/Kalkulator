package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;

class ScientificTest {
    ScientificCalculatorController scientificCalculator = new ScientificCalculatorController();

    public static String selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
    static String transformationFunctionFormula;
    boolean derivative;

    @Test
    void scientificCalcTest1() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        derivative = false;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        String functionFormula = "2%-1.3+sqrt[2,6/2]*3^[1.2]-log[3][9]+ln[2]*e+sin[80]+tan[π]+4!+sqrt[3,log[2][3]]+π*2/3+asin[22]";
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificCalcTest2() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String functionFormula = "2-1.3+sqrt[2,x]-2^[?]";
        derivative = false;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.NONE);
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificCalcTest3() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        derivative = false;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
        String functionFormula = "2%-1.3+sqrt[2,6/2]*3^[1.2]/2-log[3][9]+ln[2]*e+sin[80]+tan[π]+4!+sqrt[3,log[2][3]]+π*2/3+asin[22]+(2*π/3)";
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(transformationFunctionFormula));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificCalcTest4() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        derivative = false;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
        String functionFormula = "2^[x]";
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(transformationFunctionFormula));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificCalcTest5() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        derivative = false;
        selectedUnityOfMeasure = "Unit of measure";
        String functionFormula = "cos[40]";
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(transformationFunctionFormula));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificDerivativeCalcTest5() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String functionFormula = "";
        derivative = true;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificDerivativeCalcTest6() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String functionFormula = "2";
        derivative = true;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificDerivativeCalcTest7() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String functionFormula = "2*x";
        derivative = true;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void scientificDerivativeCalcTest8() {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String functionFormula = "xcc";
        derivative = true;
        selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        transformationFunctionFormula = transformation.transformationOfTheEquation(functionFormula, "sympy.", selectedUnityOfMeasure);

        if (scientificCalculator.enteredMathematicalOperationCheck(functionFormula, derivative, selectedUnityOfMeasure)) {
            if (!derivative && !scientificCalculator.calculatorResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.calculatorResult(transformationFunctionFormula)));
            }
            if (derivative && !scientificCalculator.derivativeResult(transformationFunctionFormula).equals("")) {
                System.out.println("formula: " + transformation.changingTheEquation(functionFormula));
                System.out.println("result: " + transformation.transformationOfTheResult(scientificCalculator.derivativeResult(transformationFunctionFormula)));
            }
        }
    }

    @Test
    void graphTest1() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String functionFormula = "2*3", wynik = "";
        int xMinSpinner = 0;
        int xMaxSpinner = 5;

        if (!scientificCalculator.graphCheck(functionFormula, xMaxSpinner, xMinSpinner, wynik)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(functionFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(functionFormula, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Graph");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("Error. " + w);
            }
        }
    }

    @Test
    void graphTest2() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String functionFormula = "5^[x]";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;
        String wynik = "ln[1/x]";

        if (!scientificCalculator.graphCheck(functionFormula, xMaxSpinner, xMinSpinner, wynik)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(functionFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(functionFormula, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Graph");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("Error. " + w);
            }
        }
    }

    @Test
    void graphTest3() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String functionFormula = "log[2][3]";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;
        String wynik = "ln[1/x]";

        if (!scientificCalculator.graphCheck(functionFormula, xMaxSpinner, xMinSpinner, wynik)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(functionFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(functionFormula, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Graph");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);

            } catch (Exception w) {
                System.out.println("Error. " + w);
            }
        }
    }

    @Test
    void graphTest4() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String functionFormula = "", wynik = "";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;

        if (!scientificCalculator.graphCheck(functionFormula, xMaxSpinner, xMinSpinner, wynik)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(functionFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(functionFormula, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Graph");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("Error. " + w);
            }
        }
    }
}