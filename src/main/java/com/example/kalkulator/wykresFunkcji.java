package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import jep.Jep;

import java.util.Arrays;

public class wykresFunkcji {
    public double[] listaX(String funkcja, int xMax, double xMin) {
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String rownanie = przeksztalcenieRownania.przeksztalcenieRownania(funkcja, "sympy.", String.valueOf(Jednostka.RADIANY));
        int ilosc = 1;
        double xm = xMin;
        while (xm <= xMax) {
            xm += 0.25;
            ilosc++;
        }
        if (xMin == 0.0) {
            xMin = 0.1;
        }
        double[] wx = new double[ilosc];
        String listaX;

        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    pi = sympy.pi
                    def f(x):
                        return\040""" + rownanie + """
                                                                    
                    x=[]
                    y=[]
                    a=""" + xMin + """
                    \nb=""" + xMax + """
                    \nwhile a<=b+0.25:
                    \tx.append(a)
                    \ta=a+0.25
                    """);

            listaX = String.valueOf(jep.getValue("x"));
        }
        listaX = listaX.replace("[", "");
        listaX = listaX.replace("]", ",");
        for (int i = 0, k = 0; i < listaX.length(); i++, k++) {
            String element = "";
            for (int j = i; j < listaX.length(); j++) {
                if (listaX.charAt(j) != ',') {
                    element += listaX.charAt(j);
                } else {
                    i = j + 1;
                    break;
                }
            }
            wx[k] = Double.parseDouble(element);
        }
        return wx;
    }


    public double[] listaY(String funkcja, int xMax, int xMin, double[] listaX) {
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String rownanie = przeksztalcenieRownania.przeksztalcenieRownania(funkcja, "sympy.", String.valueOf(Jednostka.RADIANY));
        int ilosc = 1;
        double xm = xMin;
        while (xm <= xMax) {
            xm += 0.25;
            ilosc++;
        }
        double[] wy = new double[ilosc];
        String listaY = "";

        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    pi = sympy.pi
                    def f(x):
                        return\040""" + rownanie + """
                                                                    
                    x=""" + Arrays.toString(listaX) + """
                    \ny=[]
                    a=""" + xMin + """
                    \nb=""" + xMax + """
                                        
                    for i in range(len(x)):
                    \ty.append(float(f(x[i])))
                                        
                    """);

            listaY = String.valueOf(jep.getValue("y"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        listaY = listaY.replace("[", "");
        listaY = listaY.replace("]", ",");
        System.out.println(listaY);
        for (int i = 0, k = 0; i < listaY.length(); i++, k++) {
            String element = "";
            for (int j = i; j < listaY.length(); j++) {
                if (listaY.charAt(j) != ',') {
                    element += listaY.charAt(j);
                } else {
                    i = j + 1;
                    break;
                }
            }
            wy[k] = Double.parseDouble(element);
        }
        return wy;
    }
}
