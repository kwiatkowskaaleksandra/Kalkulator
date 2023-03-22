package com.example.calculator;/*
 * @project Kalkulator
 * @author kola
 */

import jep.Jep;

import java.util.Arrays;

public class GraphOfTheFunction {
    public double[] listX(String function, int xMax, double xMin) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String equation = transformation.transformationOfTheEquation(function, "sympy.", String.valueOf(UnitOfMeasure.RADIANS));
        equation = transformation.graphTransformation(equation);

        int quantity = 1;
        double xm = xMin;
        while (xm <= xMax) {
            xm += 0.25;
            quantity++;
        }
        if (xMin == 0.0) {
            xMin = 0.1;
        }
        double[] wx;
        String listX = "";

        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    pi = sympy.pi
                    def f(x):
                        return\040""" + equation + """
                                                                    
                    x=[]
                    y=[]
                    a=""" + xMin + """
                    \nb=""" + xMax + """
                    \nwhile a<=b+0.25:
                    \tx.append(a)
                    \ta=a+0.25
                    """);

            listX = String.valueOf(jep.getValue("x"));
        }catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        wx = points(listX,quantity);

        return wx;
    }


    public double[] listY(String function, int xMax, int xMin, double[] listX) {
        TransformationOfTheEquation transformation = new TransformationOfTheEquation();
        String equation = transformation.transformationOfTheEquation(function, "sympy.", String.valueOf(UnitOfMeasure.RADIANS));
        equation = transformation.graphTransformation(equation);

        int quantity = 1;
        double xm = xMin;
        while (xm <= xMax) {
            xm += 0.25;
            quantity++;
        }
        double[] wy;
        String listY = "";

        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    pi = sympy.pi
                    def f(x):
                        return\040""" + equation + """
                                                                    
                    x=""" + Arrays.toString(listX) + """
                    \ny=[]
                    a=""" + xMin + """
                    \nb=""" + xMax + """
                                        
                    for i in range(len(x)):
                    \ty.append(float(f(x[i])))
                                        
                    """);

            listY = String.valueOf(jep.getValue("y"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        wy = points(listY,quantity);

        return wy;
    }

    public double[] points(String list, int quantity){
        double[] axisPoints = new double[quantity];
        list = list.replace("[","");
        list = list.replace("]",",");

        for (int i = 0, k = 0; i < list.length(); i++, k++) {
            String element = "";
            for (int j = i; j < list.length(); j++) {
                if (list.charAt(j) != ',') {
                    element += list.charAt(j);
                } else {
                    i = j + 1;
                    break;
                }
            }
            axisPoints[k] = Double.parseDouble(element);
        }
        return axisPoints;
    }
}
