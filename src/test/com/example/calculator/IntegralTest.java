package com.example.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class IntegralTest extends IntegrationMethods {
    public String methods;
    public static String selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
    static String upperLimitTransformed = null;
    static String lowerLimitTransformed = null;

    IntegralCalculatorController integralCalculator = new IntegralCalculatorController();
    String hiddenIntegralFormula;

    @Test
    void corretIntegralTest() {
        String formula = "sin[x]+cos[π]";
        String lowerLimit = "1";
        String upperLimit = "12";
        String numberOfSubintervals = "4";
        String unitOfMeasure = "Degrees";

        String wynikMetodaProstokatowNiedomiar = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertEquals("-9.187972366282045", wynikMetodaProstokatowNiedomiar);

        String wynikMetodaProstokatowNadmiar = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertEquals("-12.977593099004954", wynikMetodaProstokatowNadmiar);

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertEquals("-11.0827827326435", wynikMetodaTrapezow);

        String wynikMetodaSimpsona = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertEquals("-11.78425351625917", wynikMetodaSimpsona);

        String wynikMetodaAnalityczna = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertEquals("-11.3035516528644", wynikMetodaAnalityczna);

    }

    @Test
    void wrongLimitsTest() {
        String formula = "2";
        String lowerLimit = "m5";
        String upperLimit = "w1";
        String numberOfSubintervals = "12.2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void emptyFieldsTest() {
        String formula = "";
        String lowerLimit = "";
        String upperLimit = "";
        String numberOfSubintervals = "";
        String unitOfMeasure = "";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void wrongNumberOfSubintervalsTest2() {
        String formula = "2";
        String lowerLimit = "-∞";
        String upperLimit = "+∞";
        String numberOfSubintervals = "-1";
        String unitOfMeasure = "";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));
    }

    @Test
    void operationWithQuestionMarkTest() {
        String formula = "2*cos[?]";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void trigonometricFunctionWithNoUnitTest() {
        String formula = "2*cos[10]";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Unit of measure";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void wrongFormulaTest() {
        String formula = "rownanie";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void wrongNumberOfSubintervalsTest() {
        String formula = "2*cos[x]";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "podprzedzialy";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));
    }

    @Test
    void emptyNumberOfSubintervalsTest() {
        String formula = "2*cos[x]";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));
    }

    @Test
    void emptyFormulaTest() {
        String formula = "";
        String lowerLimit = "-12";
        String upperLimit = "2";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void lowerLimitErrorTest() {
        String formula = "2*cos[x]";
        String lowerLimit = "∞+cos[x]";
        String upperLimit = "-4";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    @Test
    void upperLimitErrorTest() {
        String formula = "2*cos[x]";
        String lowerLimit = "3";
        String upperLimit = "-sin[3]*∞";
        String numberOfSubintervals = "2";
        String unitOfMeasure = "Radians";

        String underflowingRectanglesRule = underflowingRectanglesRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(underflowingRectanglesRule));

        String rectanglesWithExcessRule = rectanglesWithExcessRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(rectanglesWithExcessRule));

        String wynikCalkaMetodaTrapezow = wynikCalkaMetodaTrapezowTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(wynikCalkaMetodaTrapezow));

        String SimpsonRule = SimpsonRuleTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(SimpsonRule));

        String analyticalMethod = analyticalMethodTest(formula, lowerLimit, upperLimit, numberOfSubintervals, unitOfMeasure);
        Assertions.assertFalse(Boolean.parseBoolean(analyticalMethod));
    }

    String underflowingRectanglesRuleTest(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals, String unitOfMeasure) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        methods = "Underflowing rectangles rule";
        String result = "";
        if (integralCalculator.requiredFieldsCheck(lowerLimit, upperLimit, formula, methods, unitOfMeasure)) {
            if (lowerLimit.matches("[0-9]+")) {
                lowerLimit = (String.valueOf(Float.valueOf(lowerLimit)));
            }
            if (upperLimit.matches("[0-9]+")) {
                upperLimit = (String.valueOf(Float.valueOf(upperLimit)));
            }

            if (unitOfMeasure.equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitOfMeasure.equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            System.out.println("upper limit formula: " + transformation.changingTheEquation(upperLimit));
            System.out.println("lower limit formula: " + transformation.changingTheEquation(lowerLimit));

            if (methods.equals("Underflowing rectangles rule")) {
                if (integralCalculator.numberOfSubintervalsCheck(numberOfSubintervals)) {
                    resultOfTransformation(transformation, lowerLimit, upperLimit, formula);
                    if (!underflowingRectanglesRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals).equals("")) {
                        System.out.println("formula: " + transformation.transformationOfTheResult(integralFormula));
                        result = underflowingRectanglesRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals);
                    }
                }
            }
        }
        return result;
    }


    String rectanglesWithExcessRuleTest(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals, String unitOfMeasure) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        methods = "Rectangles with excess rule";
        String wynik = "";
        if (integralCalculator.requiredFieldsCheck(lowerLimit, upperLimit, formula, methods, unitOfMeasure)) {
            if (lowerLimit.matches("[0-9]+")) {
                lowerLimit = (String.valueOf(Float.valueOf(lowerLimit)));
            }
            if (upperLimit.matches("[0-9]+")) {
                upperLimit = (String.valueOf(Float.valueOf(upperLimit)));
            }

            if (unitOfMeasure.equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitOfMeasure.equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            System.out.println("upper limit formula: " + transformation.changingTheEquation(upperLimit));
            System.out.println("lower limit formula: " + transformation.changingTheEquation(lowerLimit));

            if (methods.equals("Rectangles with excess rule")) {
                if (integralCalculator.numberOfSubintervalsCheck(numberOfSubintervals)) {
                    resultOfTransformation(transformation, lowerLimit, upperLimit, formula);
                    if (!rectanglesWithExcessRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals).equals("")) {
                        System.out.println("formula: " + transformation.transformationOfTheResult(integralFormula));
                        wynik = rectanglesWithExcessRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals);
                    }
                }
            }
        }
        return wynik;
    }


    String wynikCalkaMetodaTrapezowTest(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals, String unitOfMeasure) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        methods = "Trapezoidal rule";
        String result = "";
        if (integralCalculator.requiredFieldsCheck(lowerLimit, upperLimit, formula, methods, unitOfMeasure)) {
            if (lowerLimit.matches("[0-9]+")) {
                lowerLimit = (String.valueOf(Float.valueOf(lowerLimit)));
            }
            if (upperLimit.matches("[0-9]+")) {
                upperLimit = (String.valueOf(Float.valueOf(upperLimit)));
            }

            if (unitOfMeasure.equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitOfMeasure.equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            System.out.println("upper limit formula: " + transformation.changingTheEquation(upperLimit));
            System.out.println("lower limit formula: " + transformation.changingTheEquation(lowerLimit));

            if (methods.equals("Trapezoidal rule")) {
                if (integralCalculator.numberOfSubintervalsCheck(numberOfSubintervals)) {
                    resultOfTransformation(transformation, lowerLimit, upperLimit, formula);
                    if (!trapezoidalRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals).equals("")) {
                        System.out.println("formula: " + transformation.transformationOfTheResult(integralFormula));
                        result = trapezoidalRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals);
                    }
                }
            }
        }
        return result;
    }


    String SimpsonRuleTest(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals, String unitOfMeasure) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        methods = "Simpson's rule";
        String result = "";
        if (integralCalculator.requiredFieldsCheck(lowerLimit, upperLimit, formula, methods, unitOfMeasure)) {
            if (lowerLimit.matches("[0-9]+")) {
                lowerLimit = (String.valueOf(Float.valueOf(lowerLimit)));
            }
            if (upperLimit.matches("[0-9]+")) {
                upperLimit = (String.valueOf(Float.valueOf(upperLimit)));
            }

            if (unitOfMeasure.equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitOfMeasure.equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            System.out.println("upper limit formula: " + transformation.changingTheEquation(upperLimit));
            System.out.println("lower limit formula: " + transformation.changingTheEquation(lowerLimit));

            if (methods.equals("Simpson's rule")) {
                if (integralCalculator.numberOfSubintervalsCheck(numberOfSubintervals)) {
                    resultOfTransformation(transformation, lowerLimit, upperLimit, formula);
                    if (!SimpsonRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals).equals("")) {
                        System.out.println("formula: " + transformation.transformationOfTheResult(integralFormula));
                        result = SimpsonRule(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed, numberOfSubintervals);
                    }
                }
            }
        }
        return result;
    }


    String analyticalMethodTest(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals, String unitOfMeasure) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        methods = "Analytical method";
        String result = "";
        if (integralCalculator.requiredFieldsCheck(lowerLimit, upperLimit, formula, methods, unitOfMeasure)) {
            if (lowerLimit.matches("[0-9]+")) {
                lowerLimit = (String.valueOf(Float.valueOf(lowerLimit)));
            }
            if (upperLimit.matches("[0-9]+")) {
                upperLimit = (String.valueOf(Float.valueOf(upperLimit)));
            }

            if (unitOfMeasure.equals("Degrees")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
            } else if (unitOfMeasure.equals("Radians")) {
                selectedUnityOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
            }

            System.out.println("upper limit formula: " + transformation.changingTheEquation(upperLimit));
            System.out.println("lower limit formula: " + transformation.changingTheEquation(lowerLimit));

            if (methods.equals("Analytical method")) {
                resultOfTransformation(transformation, lowerLimit, upperLimit, formula);
                if (!analyticalMethod(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed).equals("")) {
                    System.out.println("formula: " + transformation.transformationOfTheResult(integralFormula));
                    result = analyticalMethod(hiddenIntegralFormula, lowerLimitTransformed, upperLimitTransformed);
                }
            }
        }
        return result;
    }

    public String resultOfTransformation(TransformationOfTheEquation transformation, String lowerLimit, String upperLimit, String enteredIntegralFormula) {
        String unitOfMeasure = "";
        lowerLimitTransformed = transformation.transformationOfTheEquation(lowerLimit, "sympy.", selectedUnityOfMeasure);
        upperLimitTransformed = transformation.transformationOfTheEquation(upperLimit, "sympy.", selectedUnityOfMeasure);
        hiddenIntegralFormula = transformation.transformationOfTheEquation(enteredIntegralFormula, "sympy.", selectedUnityOfMeasure);
        if (Objects.equals(transformation.unitOfMeasure, "RADIANS")) {
            unitOfMeasure = "Radians";
        } else if (Objects.equals(transformation.unitOfMeasure, "DEGREES")) {
            unitOfMeasure = "Degrees";
        }
        return unitOfMeasure;
    }

    @Test
    void graphIntegralTest1() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String enteredIntegralFormula = "2*x";
        int xMinSpinner = 1;
        int xMaxSpinner = 5;

        if (integralCalculator.graphCheck(enteredIntegralFormula, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(enteredIntegralFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(enteredIntegralFormula, xMaxSpinner, xMinSpinner, x);

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
    void graphInegralTest2() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String enteredIntegralFormula = "x*ln[1]";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;

        if (integralCalculator.graphCheck(enteredIntegralFormula, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(enteredIntegralFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(enteredIntegralFormula, xMaxSpinner, xMinSpinner, x);

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
    void graphIntegralTest3() {
        GraphOfTheFunction graph = new GraphOfTheFunction();
        String enteredIntegralFormula = "";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;

        if (integralCalculator.graphCheck(enteredIntegralFormula, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = graph.listX(enteredIntegralFormula, xMaxSpinner, xMinSpinner);
                double[] y = graph.listY(enteredIntegralFormula, xMaxSpinner, xMinSpinner, x);

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