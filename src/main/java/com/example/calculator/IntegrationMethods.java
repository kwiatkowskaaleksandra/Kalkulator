package com.example.calculator;/*
 * @project Kalkulator
 * @author kola
 */

import jep.Jep;
import jep.JepException;

import javax.swing.*;

public class IntegrationMethods {
    public static String integralFormula;

    public String analyticalMethod(String formula, String lowerLimit, String upperLimit) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    import math
                    x=sympy.Symbol('x')
                                        
                    result=sympy.integrate(""" + formula + """
                    ,(x,""" + lowerLimit + """
                    ,""" + upperLimit + """
                    ))
                                        
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """);
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));
            if (formula.contains("x")) {
                String w = String.valueOf(jep.getValue("result"));
                jep.exec("""
                        from sympy import *
                        pi = sympy.pi
                        oo = sympy.oo
                        c=float(""" + w + """
                        )
                        """);
            } else {
                jep.exec("""
                        c=float(result)
                        """);
            }
            result = String.valueOf(jep.getValue("c"));

        } catch (JepException e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public String SimpsonRule(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals) {
        String result;
        try (Jep jep = new Jep() {
        }) {
/// a - lower limit of integration, b- upper limit of integration , n - number of subintervals
            jep.exec("""
                    import math
                    import sympy
                                            
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + formula +
                    """
                            \ndef simpson(a, b, n):
                            \tx1=0.0
                            \tx2=0.0
                            \tx0=f(a)+f(b)
                            \th=(b-a)/n
                            \tresult=0.0
                                                     
                            \tfor i in range(1,n):
                            \t\tx=(a+(i*h))
                            \t\tif i%2==0:
                            \t\t\tx2+=f(x)
                            \t\telse:
                            \t\t\tx1+=f(x)
                            \tresult=h*(x0+2*x2+4*x1)/3
                            \treturn result
                            c=float(simpson(""" + lowerLimit + """
                    ,""" + upperLimit + """
                    ,""" + numberOfSubintervals + """
                    ))
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """
            );
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));
            result = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public String trapezoidalRule(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + formula +
                    """
                            \ndef trapezoidalRule(a,b,n):
                            \tx1=0.0
                            \tx2=0.0
                            \th=(b-a)/n
                            \tresult=0.0
                            \tfor i in range(0,n+1):
                            \t\tx=(a+(i*h))
                            \t\tif i==0 or i==n:
                            \t\t\tx2+=f(x)
                            \t\telse:
                            \t\t\tx1+=f(x)
                            \tresult=h*((x2/2)+x1)
                            \treturn result
                            c=float(trapezoidalRule(""" + lowerLimit + """
                    ,""" + upperLimit + """
                    ,""" + numberOfSubintervals + """
                    ))
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """
            );
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));
            result = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public String underflowingRectanglesRule(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + formula +
                    """
                            \ndef underflowingRectanglesRule(a, b, n):
                            \tsum=0.0
                            \th=(b-a)/n
                            \tresult=0.0
                                           	
                            \tfor i in range(0,n):
                            \t\tx=(a+(i*h))
                            \t\tsum+=f(x)
                                           			
                            \tresult=h*sum
                            \treturn result
                            c=float(underflowingRectanglesRule(""" + lowerLimit + """
                    ,""" + upperLimit + """
                    ,""" + numberOfSubintervals + """
                    ))
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """);
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));
            result = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public String rectanglesWithExcessRule(String formula, String lowerLimit, String upperLimit, String numberOfSubintervals) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + formula +
                    """
                            \ndef rectanglesWithExcessRule(a, b, n):
                            \tsum=0.0
                            \th=(b-a)/n
                            \tresult=0.0
                                           	
                            \tfor i in range(1,n+1):
                            \t\tx=(a+(i*h))
                            \t\tsum+=f(x)
                                           			
                            \tresult=h*sum
                            \treturn result
                            c=float(rectanglesWithExcessRule(""" + lowerLimit + """
                    ,""" + upperLimit + """
                    ,""" + numberOfSubintervals + """
                    ))
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """);
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));
            result = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public String indefiniteIntegral(String formula) {
        String result;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import sympy
                    import math
                    x=sympy.Symbol('x')
                               
                    formulaIndefiniteIntegral=sympy.integrate(""" + formula + """
                    ,x)
                    """);
            integralFormula = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));

            result = String.valueOf(jep.getValue("formulaIndefiniteIntegral"));

        } catch (JepException e) {
            System.out.println("EXCEPTION: " + e);
            result = "";
            JOptionPane.showMessageDialog(null, "Error.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }
}
