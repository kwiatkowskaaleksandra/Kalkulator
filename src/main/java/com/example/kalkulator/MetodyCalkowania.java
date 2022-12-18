package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import jep.Jep;
import jep.JepException;

import javax.swing.*;

public class MetodyCalkowania {
    public static String wzorCalki;

    public String metodaAnalityczna(String wzor, String dolnaGranica, String gornaGranica) {
        String wynik;
        try (Jep jep = new Jep() {
        }) {
            System.out.println(wzor);
            jep.exec("""
                    import sympy
                    import math
                    x=sympy.Symbol('x')
                                        
                    wynik=sympy.integrate(""" + wzor + """
                    ,(x,""" + dolnaGranica + """
                    ,""" + gornaGranica + """
                    ))
                                        
                    wzorNieozn=sympy.integrate(""" + wzor + """
                    ,x)
                    """);
            wzorCalki = String.valueOf(jep.getValue("wzorNieozn"));
            if (wzor.contains("x")) {
                String w = String.valueOf(jep.getValue("wynik"));
                jep.exec("""
                        from sympy import *
                        pi = sympy.pi
                        oo = sympy.oo
                        c=float(""" + w + """
                        )
                        """);
            } else {
                jep.exec("""
                        c=float(wynik)
                        """);
            }
            wynik = String.valueOf(jep.getValue("c"));

        } catch (JepException e) {
            System.out.println("EXCEPTION: " + e);
            wynik = "";
            JOptionPane.showMessageDialog(null, "Błąd działania.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }

    public String metodaSimpsona(String wzor, String dolnaGranica, String gornaGranica, String liczbaPodprzedzialow) {
        String wynik;
        try (Jep jep = new Jep() {
        }) {
/// a - dolna granica calkowania, b- gorna granica calkowania , n - liczba podprzedziałów
            jep.exec("""
                    import math
                    import sympy
                                            
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + wzor +
                    """
                            \ndef simpson(a, b, n):
                            \tx1=0.0
                            \tx2=0.0
                            \tx0=f(a)+f(b)
                            \th=(b-a)/n
                            \twynik=0.0
                                                     
                            \tfor i in range(1,n):
                            \t\tx=(a+(i*h))
                            \t\tif i%2==0:
                            \t\t\tx2+=f(x)
                            \t\telse:
                            \t\t\tx1+=f(x)
                            \twynik=h*(x0+2*x2+4*x1)/3
                            \treturn wynik
                            c=float(simpson(""" + dolnaGranica + """
                    ,""" + gornaGranica + """
                    ,""" + liczbaPodprzedzialow + """
                    ))
                    wzorNieozn=sympy.integrate(""" + wzor + """
                    ,x)
                    """
            );
            wzorCalki = String.valueOf(jep.getValue("wzorNieozn"));
            jep.exec("print(c)");
            wynik = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            wynik = "";
            JOptionPane.showMessageDialog(null, "Błąd działania.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }

    public String metodaTrapezow(String wzor, String dolnaGranica, String gornaGranica, String liczbaPodprzedzialow) {
        String wynik;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + wzor +
                    """
                            \ndef trapez(a,b,n):
                            \tx1=0.0
                            \tx2=0.0
                            \th=(b-a)/n
                            \twynik=0.0
                            \tfor i in range(0,n+1):
                            \t\tx=(a+(i*h))
                            \t\tif i==0 or i==n:
                            \t\t\tx2+=f(x)
                            \t\telse:
                            \t\t\tx1+=f(x)
                            \twynik=h*((x2/2)+x1)
                            \treturn wynik
                            c=float(trapez(""" + dolnaGranica + """
                    ,""" + gornaGranica + """
                    ,""" + liczbaPodprzedzialow + """
                    ))
                    wzorNieozn=sympy.integrate(""" + wzor + """
                    ,x)
                    """
            );
            wzorCalki = String.valueOf(jep.getValue("wzorNieozn"));
            wynik = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            wynik = "";
            JOptionPane.showMessageDialog(null, "Błąd działania.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }

    public String metodaProstokatowZNiedomiarem(String wzor, String dolnaGranica, String gornaGranica, String liczbaPodprzedzialow) {
        String wynik;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + wzor +
                    """
                            \ndef prostokatNiedomiar(a, b, n):
                            \tsuma=0.0
                            \th=(b-a)/n
                            \twynik=0.0
                                           	
                            \tfor i in range(0,n):
                            \t\tx=(a+(i*h))
                            \t\tsuma+=f(x)
                                           			
                            \twynik=h*suma
                            \treturn wynik
                            c=float(prostokatNiedomiar(""" + dolnaGranica + """
                    ,""" + gornaGranica + """
                    ,""" + liczbaPodprzedzialow + """
                    ))
                    wzorNieozn=sympy.integrate(""" + wzor + """
                    ,x)
                    """);
            wzorCalki = String.valueOf(jep.getValue("wzorNieozn"));
            wynik = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            wynik = "";
            JOptionPane.showMessageDialog(null, "Błąd działania.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }

    public String metodaProstokatowZNadmiarem(String wzor,String dolnaGranica, String gornaGranica, String liczbaPodprzedzialow) {
        String wynik;
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                        
                    x=sympy.Symbol('x')
                    def f(x):
                    \treturn\040""" + wzor +
                    """
                            \ndef prostokatNadmiar(a, b, n):
                            \tsuma=0.0
                            \th=(b-a)/n
                            \twynik=0.0
                                           	
                            \tfor i in range(1,n+1):
                            \t\tx=(a+(i*h))
                            \t\tsuma+=f(x)
                                           			
                            \twynik=h*suma
                            \treturn wynik
                            c=float(prostokatNadmiar(""" + dolnaGranica + """
                    ,""" + gornaGranica + """
                    ,""" + liczbaPodprzedzialow + """
                    ))
                    wzorNieozn=sympy.integrate(""" + wzor + """
                    ,x)
                    """);
            wzorCalki=String.valueOf(jep.getValue("wzorNieozn"));
            wynik = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            wynik = "";
            JOptionPane.showMessageDialog(null, "Błąd działania.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }
}
