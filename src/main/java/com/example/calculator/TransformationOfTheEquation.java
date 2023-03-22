package com.example.calculator;/*
 * @project Kalkulator
 * @author kola
 */

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformationOfTheEquation {
    public String unitOfMeasure = String.valueOf(UnitOfMeasure.NONE);

    public String changingTheEquation(String formula) {

        for (int i = 0; i < formula.length(); i++) {

            if (formula.charAt(i) == 'l' && formula.charAt(i + 1) == 'n' && formula.charAt(i + 2) == '[') {

                int howManyOpeners = 0, howManyClosers = 0;
                String number = "";

                for (int j = i + 2; j < formula.length(); j++) {
                    if (formula.charAt(j) == '[') {
                        howManyOpeners++;
                    }
                    if (formula.charAt(j) == ']') {
                        howManyClosers++;
                    }
                    if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                        number += formula.charAt(j);
                    } else {
                        number += "]";
                        break;
                    }
                }

                String newNumber = number.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(newNumber);
                str.setCharAt(str.lastIndexOf("]"), ')');
                newNumber = String.valueOf(str);

                formula = formula.replace("ln" + number, "ln" + newNumber);

            } else if (formula.charAt(i) == 's' && formula.charAt(i + 1) == 'q' && formula.charAt(i + 2) == 'r' && formula.charAt(i + 3) == 't') {
                String degreeOfTheRoot = "", root = "";
                int k = 0;
                int howManyOpeners = 1, howManyClosers = 0;

                for (int j = i + 5; j < formula.length(); j++) {
                    if (formula.charAt(j) == ',') {
                        k = j;
                        break;
                    } else {
                        degreeOfTheRoot += formula.charAt(j);
                    }
                }

                for (int j = k + 1; j < formula.length(); j++) {
                    if (formula.charAt(j) == '[') {
                        howManyOpeners++;
                    } else if (formula.charAt(j) == ']') {
                        howManyClosers++;
                    }
                    if (howManyOpeners >= 1 && howManyOpeners != howManyClosers) {
                        root += formula.charAt(j);
                    } else break;
                }

                formula = formula.replace("sqrt[" + degreeOfTheRoot + "," + root + "]", "<sup>" + degreeOfTheRoot + "</sup>&#8730;(" + root + ")");

            } else if (formula.charAt(i) == 'l' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 'g' && formula.charAt(i + 3) == '[') {
                String baseOfTheLogarithm = "", logarithmNumber = "";
                int k = 0;
                int howManyOpeners = 0, howManyClosers = 0;

                for (int j = i + 3; j < formula.length(); j++) {
                    if (formula.charAt(j) == '[') {
                        howManyOpeners++;
                    }
                    if (formula.charAt(j) == ']') {
                        howManyClosers++;
                    }
                    if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                        baseOfTheLogarithm += formula.charAt(j);
                    } else {
                        baseOfTheLogarithm += "]";
                        k = j;
                        break;
                    }
                }
                String newBaseOfTheLogarithm = baseOfTheLogarithm.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(newBaseOfTheLogarithm);
                str.setCharAt(str.lastIndexOf("]"), ')');
                newBaseOfTheLogarithm = String.valueOf(str);

                howManyOpeners = 0;
                howManyClosers = 0;
                for (int j = k + 1; j < formula.length(); j++) {
                    if (formula.charAt(j) == '[') {
                        howManyOpeners++;
                    }
                    if (formula.charAt(j) == ']') {
                        howManyClosers++;
                    }
                    if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                        logarithmNumber += formula.charAt(j);
                    } else {
                        break;
                    }
                }
                String newLogarithmNumber = logarithmNumber.replaceFirst("\\[", "(");

                formula = formula.replace("log" + baseOfTheLogarithm + logarithmNumber + "]", "log<sub>" + newBaseOfTheLogarithm + "</sub>" + newLogarithmNumber + ")");

            } else if (formula.charAt(i) == '^') {
                String exponent = "";

                for (int j = i + 2; j < formula.length(); j++) {
                    if (formula.charAt(j) == ']') {
                        break;
                    } else {
                        exponent += formula.charAt(j);
                    }
                }
                formula = formula.replace("^[" + exponent + "]", "<sup>" + exponent + "</sup>");

            } else if (((formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 's' && formula.charAt(i + 3) == '[') ||
                    (formula.charAt(i) == 's' && formula.charAt(i + 1) == 'i' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '[') ||
                    (formula.charAt(i) == 't' && formula.charAt(i + 1) == 'a' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '[') ||
                    (formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 't' && formula.charAt(i + 3) == '[')) ||
                    (formula.charAt(i) == 'a' && (formula.charAt(i + 1) == 's' || formula.charAt(i + 1) == 'c' || formula.charAt(i + 1) == 't') && (formula.charAt(i + 2) == 'i' || formula.charAt(i + 2) == 'o' || formula.charAt(i + 2) == 'a') && (formula.charAt(i + 3) == 'n' || formula.charAt(i + 3) == 's' || formula.charAt(i + 3) == 't') && formula.charAt(i + 4) == '[')) {
                String angle = "";

                int howManyOpeners = 0, howManyClosers = 0;

                for (int j = i; j < formula.length(); j++) {
                    if (formula.charAt(j) == '[') {
                        howManyOpeners++;
                    }
                    if (formula.charAt(j) == ']') {
                        howManyClosers++;
                    }
                    if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                        angle += formula.charAt(j);
                    } else {
                        angle += "]";
                        break;
                    }

                }
                String newAngle = angle.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(newAngle);
                int lastIndex = newAngle.lastIndexOf("]");
                str.setCharAt(lastIndex, ')');
                newAngle = String.valueOf(str);
                formula = formula.replace(angle, newAngle);
            }
        }
        return formula;
    }


    public String transformationOfTheEquation(String formula, String lib, String unitOfMeasure) {
        try {
            Pattern dot = Pattern.compile(".");
            String counter = "";
            String angle;

            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == '/') {
                    for (int j = i - 1; j >= 0; j--) {
                        if (formula.charAt(j) == '+' || formula.charAt(j) == 'π' || formula.charAt(j) == '-' || formula.charAt(j) == '*' || formula.charAt(j) == '/' || formula.charAt(j) == 'x' || formula.charAt(j) == '(' || formula.charAt(j) == ')' || formula.charAt(j) == '[' || formula.charAt(j) == ']')
                            break;
                        else counter += formula.charAt(j);
                    }

                    Matcher dotFraction = dot.matcher(counter);

                    if (dotFraction.find()) {
                        if (!dotFraction.find()) {
                            StringBuilder str = new StringBuilder(formula);
                            str.insert(i, ".");
                            formula = String.valueOf(str);
                        }
                    }
                    counter = "";
                }

                if (formula.charAt(i) == '^') {
                    String exponent = "";
                    for (int j = i + 2; j < formula.length(); j++) {
                        if (formula.charAt(j) == ']') {
                            break;
                        } else {
                            exponent += formula.charAt(j);
                        }
                    }
                    formula = formula.replace("^[" + exponent + "]", "**(" + exponent + ")");
                }

                if (formula.charAt(i) == '∞') {
                    formula = formula.replace("∞", lib + "oo");
                }

                if (formula.charAt(i) == '%') {
                    formula = formula.replace("%", "/100");
                }

            }

            for (int i = 0; i < formula.length(); i++) {

                if (formula.charAt(i) == 's' && formula.charAt(i + 1) == 'q' && formula.charAt(i + 2) == 'r' && formula.charAt(i + 3) == 't' && formula.charAt(i + 4) == '[') {
                    String degreeOfTheRoot = "", root = "";
                    int k = 0;
                    int howManyOpeners = 1, howManyClosers = 0;

                    for (int j = i + 5; j < formula.length(); j++) {
                        if (formula.charAt(j) == ',') {
                            k = j;
                            break;
                        } else degreeOfTheRoot += formula.charAt(j);
                    }

                    for (int j = k + 1; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {
                            howManyOpeners++;
                        }
                        if (formula.charAt(j) == ']') {
                            howManyClosers++;
                        }
                        if (howManyOpeners >= 1 && howManyOpeners != howManyClosers) {
                            root += formula.charAt(j);
                        } else {
                            break;
                        }
                    }

                    formula = formula.replace("sqrt[" + degreeOfTheRoot + "," + root + "]", "(" + root + ")**(1./(" + degreeOfTheRoot + "))");
                
                } else if (formula.charAt(i) == 'π') {
                    formula = formula.replace("π", lib + "pi");

                } else if (formula.charAt(i) == '!') {
                    String factorial = "";

                    for (int j = i - 1; j >= 0; j--) {
                        if (!String.valueOf(formula.charAt(j)).equals("+") && !String.valueOf(formula.charAt(j)).equals("-") && !String.valueOf(formula.charAt(j)).equals("/") && !String.valueOf(formula.charAt(j)).equals("*") && !String.valueOf(formula.charAt(j)).equals("(") && !String.valueOf(formula.charAt(j)).equals(")")) {
                            factorial += formula.charAt(j);
                        } else break;
                    }

                    StringBuilder invertedFactorial = new StringBuilder();
                    invertedFactorial.append(factorial);
                    invertedFactorial.reverse();

                    Long factorialResult = factorial(Integer.parseInt(String.valueOf(invertedFactorial)));
                    formula = formula.replace(invertedFactorial + "!", String.valueOf(factorialResult));
                    
                } else if (formula.charAt(i) == 'e') {
                    formula = formula.replace("e", String.valueOf(Math.E));
                    
                } else if (formula.charAt(i) == 'l' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 'g' && formula.charAt(i + 3) == '[') {

                    String baseOfTheLogarithm = "", logarithmNumber = "";
                    int k = 0;
                    int howManyOpeners = 0, howManyClosers = 0;

                    for (int j = i + 3; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {
                            howManyOpeners++;
                        }
                        if (formula.charAt(j) == ']') {
                            howManyClosers++;
                        }
                        if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                            baseOfTheLogarithm += formula.charAt(j);
                        } else {
                            baseOfTheLogarithm += "]";
                            k = j;
                            break;
                        }
                    }
                    String newBaseOfTheLogarithm = baseOfTheLogarithm.replaceFirst("\\[", "");
                    StringBuilder str = new StringBuilder(newBaseOfTheLogarithm);
                    str.setCharAt(str.lastIndexOf("]"), ')');
                    newBaseOfTheLogarithm = String.valueOf(str);

                    howManyOpeners = 0;
                    howManyClosers = 0;
                    for (int j = k + 1; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {
                            howManyOpeners++;
                        }
                        if (formula.charAt(j) == ']') {
                            howManyClosers++;
                        }
                        if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                            logarithmNumber += formula.charAt(j);
                        } else {
                            break;
                        }
                    }
                    String newLogarithmNumber = logarithmNumber.replaceFirst("\\[", "(");

                    formula = formula.replace("log" + baseOfTheLogarithm + logarithmNumber + "]", lib + "log" + newLogarithmNumber + "," + newBaseOfTheLogarithm + "");

                } else if (formula.charAt(i) == 'l' && formula.charAt(i + 1) == 'n' && formula.charAt(i + 2) == '[') {
                    int howManyOpeners = 0, howManyClosers = 0;
                    String number = "";

                    for (int j = i + 2; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {
                            howManyOpeners++;
                        }
                        if (formula.charAt(j) == ']') {
                            howManyClosers++;
                        }
                        if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                            number += formula.charAt(j);
                        } else {
                            number += "]";
                            break;
                        }
                    }

                    String newNumber = number.replaceFirst("\\[", "(");
                    StringBuilder str = new StringBuilder(newNumber);
                    str.setCharAt(str.lastIndexOf("]"), ')');
                    newNumber = String.valueOf(str);

                    formula = formula.replace("ln" + number, lib + "log" + newNumber);

                } else if ((formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 's' && formula.charAt(i + 3) == '[') ||
                        (formula.charAt(i) == 's' && formula.charAt(i + 1) == 'i' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '[') ||
                        (formula.charAt(i) == 't' && formula.charAt(i + 1) == 'a' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '[') ||
                        (formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 't' && formula.charAt(i + 3) == '[')) {
                    angle = "";
                    int howManyOpeners = 0, howManyClosers = 0;

                    for (int j = i; j < formula.length(); j++) {
                        if (formula.charAt(j) == '[') {
                            howManyOpeners++;
                        }
                        if (formula.charAt(j) == ']') {
                            howManyClosers++;
                        }
                        if (howManyOpeners == 0 || howManyOpeners != howManyClosers) {
                            angle += formula.charAt(j);
                        } else {
                            angle += "]";
                            break;
                        }
                    }

                    StringBuilder str;
                    StringBuilder strLib;
                    String newAngle;
                    if (i - 1 >= 0 && formula.charAt(i - 1) == 'a') {
                        str = new StringBuilder(angle);
                        strLib = new StringBuilder("a");
                        strLib.append(str);
                        angle = String.valueOf(strLib);

                        str = new StringBuilder(angle);
                        strLib = new StringBuilder("sympy.");
                        strLib.append(str);
                        newAngle = String.valueOf(strLib);
                    } else {
                        str = new StringBuilder(angle);
                        strLib = new StringBuilder("sympy.");
                        strLib.append(str);
                        newAngle = String.valueOf(strLib);
                    }
                    if (!unitOfMeasure.equals("Unit of measure")) {
                        formula = formula.replace(angle, newAngle);
                        formula = formula.replace(newAngle, "(" + trigonometricFunctions(newAngle, unitOfMeasure) + ")");
                    } else throw new Exception();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return formula;
    }


    public String trigonometricFunctions(String angle, String unitOfMeasure) {
        String newAngle;
        String str2;
        StringBuilder str;

        if (angle.contains("π")) {
            newAngle = angle.replace("π", "sympy.pi");
            str = new StringBuilder(newAngle);
            str2 = String.valueOf(str);
            StringBuilder stringBuilder = new StringBuilder(str2);
            stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
            str2 = String.valueOf(stringBuilder);
            str2 = str2.replaceFirst("\\[", "(");
        } else {
            str = new StringBuilder(angle);
            str2 = String.valueOf(str);
            if (unitOfMeasure.equals(String.valueOf(UnitOfMeasure.RADIANS))) {
                StringBuilder stringBuilder = new StringBuilder(str2);
                stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
                str2 = String.valueOf(stringBuilder);
                str2 = str2.replaceFirst("\\[", "(");
            } else if (unitOfMeasure.equals(String.valueOf(UnitOfMeasure.DEGREES))) {
                if (angle.contains("x")) {
                    unitOfMeasureChange(false, true);
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
                    str2 = String.valueOf(stringBuilder);
                    str2 = str2.replaceFirst("\\[", "(");
                } else {
                    unitOfMeasureChange(true, false);
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
                    stringBuilder.append(")");
                    str2 = String.valueOf(stringBuilder);
                    str2 = str2.replaceFirst("\\[", "(math.radians(");
                }
            }
        }
        return str2;
    }

    public void unitOfMeasureChange(boolean degrees, boolean radians) {
        if (!degrees && radians) {
            unitOfMeasure = String.valueOf(UnitOfMeasure.RADIANS);
        } else if (degrees && !radians) {
            unitOfMeasure = String.valueOf(UnitOfMeasure.DEGREES);
        }
    }

    public static Long factorial(int i) {
        if (i < 1) return 1L;
        else return i * factorial(i - 1);
    }

    public String transformationOfTheResult(String formula) {
        for (int i = 0; i < formula.length(); i++) {

            if (formula.charAt(i) == '*' && formula.charAt(i + 1) == '*') {
                String exponent = "";

                for (int j = i + 2; j < formula.length(); j++) {
                    if (formula.charAt(j) == '/' || formula.charAt(j) == '+' || formula.charAt(j) == '-' || formula.charAt(j) == '*') {
                        break;
                    } else {
                        exponent += formula.charAt(j);
                    }
                }
                formula = formula.replace("**" + exponent, "<sup>" + exponent + "</sup>");
            }

            if (formula.charAt(i) == 'l' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 'g') {
                formula = formula.replace("log", "ln");
            }
        }
        formula = formula.replace("sympy.", "");
        formula = formula.replace("math.", "");
        formula = formula.replace("radians", "");
        formula = formula.replace("Integral", "");

        return formula;
    }

    public String graphTransformation(String formula) {

        for (int i = 0; i < formula.length(); i++) {
            if ((formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 's' && formula.charAt(i + 3) == '(')) {
                if (i - 1 >= 0 && formula.charAt(i - 1) != '.' && formula.charAt(i - 1) != 'a') {
                    formula = formula.replace("cos", "sympy.cos");
                }
                if (formula.charAt(i - 1) == 'a' && i - 2 >= 0 && formula.charAt(i - 2) != '.') {
                    formula = formula.replace("acos", "sympy.acos");
                }
            }
            if ((formula.charAt(i) == 's' && formula.charAt(i + 1) == 'i' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '(')) {
                if (i - 1 >= 0 && formula.charAt(i - 1) != '.' && formula.charAt(i - 1) != 'a') {
                    formula = formula.replace("sin", "sympy.sin");
                }
                if (formula.charAt(i - 1) == 'a' && i - 2 >= 0 && formula.charAt(i - 2) != '.') {
                    formula = formula.replace("asin", "sympy.asin");
                }
            }
            if ((formula.charAt(i) == 't' && formula.charAt(i + 1) == 'a' && formula.charAt(i + 2) == 'n' && formula.charAt(i + 3) == '(')) {
                if (i - 1 >= 0 && formula.charAt(i - 1) != '.' && formula.charAt(i - 1) != 'a') {
                    formula = formula.replace("tan", "sympy.tan");
                }
                if (formula.charAt(i - 1) == 'a' && i - 2 >= 0 && formula.charAt(i - 2) != '.') {
                    formula = formula.replace("atan", "sympy.atan");
                }
            }
            if ((formula.charAt(i) == 'c' && formula.charAt(i + 1) == 'o' && formula.charAt(i + 2) == 't' && formula.charAt(i + 3) == '(')) {
                if (i - 1 >= 0 && formula.charAt(i - 1) != '.' && formula.charAt(i - 1) != 'a') {
                    formula = formula.replace("cot", "sympy.cot");
                }
                if (formula.charAt(i - 1) == 'a' && i - 2 >= 0 && formula.charAt(i - 2) != '.') {
                    formula = formula.replace("acot", "sympy.acot");
                }
            }
        }

        return formula;
    }
}
