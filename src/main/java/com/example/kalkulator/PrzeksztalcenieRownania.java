package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrzeksztalcenieRownania {
    public String pressedJednostka = String.valueOf(Jednostka.NONE);

    public String zmianaRownania(String wzor) {

        for (int i = 0; i < wzor.length(); i++) {

            if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'n' && wzor.charAt(i + 2) == '[') {

                int ileOtwierajacych = 0, ileZamykajacych = 0;
                String liczba = "";

                for (int j = i + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '[') {
                        ileOtwierajacych++;
                    }
                    if (wzor.charAt(j) == ']') {
                        ileZamykajacych++;
                    }
                    if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                        liczba += wzor.charAt(j);
                    } else {
                        liczba += "]";
                        break;
                    }
                }

                String nowaLiczba = liczba.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(nowaLiczba);
                str.setCharAt(str.lastIndexOf("]"), ')');
                nowaLiczba = String.valueOf(str);

                wzor = wzor.replace("ln" + liczba, "ln" + nowaLiczba);

            } else if (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'q' && wzor.charAt(i + 2) == 'r' && wzor.charAt(i + 3) == 't') {
                String stopienPierwiastka = "", pierwiastek = "";
                int k = 0;
                int ileOtwierajacych = 1, ileZamykajacych = 0;

                for (int j = i + 5; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ',') {
                        k = j;
                        break;
                    } else {
                        stopienPierwiastka += wzor.charAt(j);
                    }
                }

                for (int j = k + 1; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '[') {
                        ileOtwierajacych++;
                    } else if (wzor.charAt(j) == ']') {
                        ileZamykajacych++;
                    }
                    if (ileOtwierajacych >= 1 && ileOtwierajacych != ileZamykajacych) {
                        pierwiastek += wzor.charAt(j);
                    } else break;
                }

                wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + pierwiastek + "]", "<sup>" + stopienPierwiastka + "</sup>&#8730;(" + pierwiastek + ")");

            } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 'g' && wzor.charAt(i + 3) == '[') {
                String podstawaLogarytm = "", liczbaLogarytm = "";
                int k = 0;
                int ileOtwierajacych = 0, ileZamykajacych = 0;

                for (int j = i + 3; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '[') {
                        ileOtwierajacych++;
                    }
                    if (wzor.charAt(j) == ']') {
                        ileZamykajacych++;
                    }
                    if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                        podstawaLogarytm += wzor.charAt(j);
                    } else {
                        podstawaLogarytm += "]";
                        k = j;
                        break;
                    }
                }
                String nowaPodstawaLogarytm = podstawaLogarytm.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(nowaPodstawaLogarytm);
                str.setCharAt(str.lastIndexOf("]"), ')');
                nowaPodstawaLogarytm = String.valueOf(str);

                ileOtwierajacych = 0;
                ileZamykajacych = 0;
                for (int j = k + 1; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '[') {
                        ileOtwierajacych++;
                    }
                    if (wzor.charAt(j) == ']') {
                        ileZamykajacych++;
                    }
                    if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                        liczbaLogarytm += wzor.charAt(j);
                    } else {
                        break;
                    }
                }
                String nowaLiczbaLogarytm = liczbaLogarytm.replaceFirst("\\[", "(");

                wzor = wzor.replace("log" + podstawaLogarytm + liczbaLogarytm + "]", "log<sub>" + nowaPodstawaLogarytm + "</sub>" + nowaLiczbaLogarytm + ")");

            } else if (wzor.charAt(i) == '^') {
                String wykladnik = "";

                //Zapisanie wykladnika
                for (int j = i + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        wykladnik += wzor.charAt(j);
                    }
                }
                wzor = wzor.replace("^[" + wykladnik + "]", "<sup>" + wykladnik + "</sup>");

            } else if (((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 's' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'i' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 't' && wzor.charAt(i + 1) == 'a' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 't' && wzor.charAt(i + 3) == '[')) ||
                    (wzor.charAt(i) == 'a' && (wzor.charAt(i + 1) == 's' || wzor.charAt(i + 1) == 'c' || wzor.charAt(i + 1) == 't') && (wzor.charAt(i + 2) == 'i' || wzor.charAt(i + 2) == 'o' || wzor.charAt(i + 2) == 'a') && (wzor.charAt(i + 3) == 'n' || wzor.charAt(i + 3) == 's' || wzor.charAt(i + 3) == 't') && wzor.charAt(i + 4) == '[')) {
                String kat = "";

                int ileOtwierajacych = 0, ileZamykajacych = 0;

                for (int j = i; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '[') {
                        ileOtwierajacych++;
                    }
                    if (wzor.charAt(j) == ']') {
                        ileZamykajacych++;
                    }
                    if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                        kat += wzor.charAt(j);
                    } else {
                        kat += "]";
                        break;
                    }

                }
                String nowyKat = kat.replaceFirst("\\[", "(");
                StringBuilder str = new StringBuilder(nowyKat);
                int lastIndex = nowyKat.lastIndexOf("]");
                str.setCharAt(lastIndex, ')');
                nowyKat = String.valueOf(str);
                wzor = wzor.replace(kat, nowyKat);
            }
        }
        return wzor;
    }


    public String przeksztalcenieRownania(String wzor, String lib, String jednostka) {
        try {
            Pattern kropka = Pattern.compile(".");
            String licznik = "";
            String kat;

            for (int i = 0; i < wzor.length(); i++) {
                if (wzor.charAt(i) == '/') {
                    for (int j = i - 1; j >= 0; j--) {
                        if (wzor.charAt(j) == '+' || wzor.charAt(j) == 'π' || wzor.charAt(j) == '-' || wzor.charAt(j) == '*' || wzor.charAt(j) == '/' || wzor.charAt(j) == 'x' || wzor.charAt(j) == '(' || wzor.charAt(j) == ')' || wzor.charAt(j) == '[' || wzor.charAt(j) == ']') break;
                        else licznik += wzor.charAt(j);
                    }

                    Matcher kropkaUlamka = kropka.matcher(licznik);

                    if (kropkaUlamka.find()) {
                        if (!kropkaUlamka.find()) {
                            StringBuilder str = new StringBuilder(wzor);
                            str.insert(i, ".");
                            wzor = String.valueOf(str);
                        }
                    }
                    licznik = "";
                }

                if (wzor.charAt(i) == '^') {
                    String wykladnik = "";
                    for (int j = i + 2; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == ']') {
                            break;
                        } else {
                            wykladnik += wzor.charAt(j);
                        }
                    }
                    wzor = wzor.replace("^[" + wykladnik + "]", "**(" + wykladnik + ")");
                }

                if (wzor.charAt(i) == '∞') {
                    wzor = wzor.replace("∞", lib + "oo");
                }

                if (wzor.charAt(i) == '%') {
                    wzor = wzor.replace("%", "/100");
                }

            }

            for (int i = 0; i < wzor.length(); i++) {

                if (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'q' && wzor.charAt(i + 2) == 'r' && wzor.charAt(i + 3) == 't' && wzor.charAt(i + 4) == '[') {
                    String stopienPierwiastka = "", liczbaPierwiastkowana = "";
                    int k = 0;
                    int ileOtwierajacych = 1, ileZamykajacych = 0;

                    for (int j = i + 5; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == ',') {
                            k = j;
                            break;
                        } else stopienPierwiastka += wzor.charAt(j);
                    }

                    for (int j = k + 1; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == '[') {
                            ileOtwierajacych++;
                        }
                        if (wzor.charAt(j) == ']') {
                            ileZamykajacych++;
                        }
                        if (ileOtwierajacych >= 1 && ileOtwierajacych != ileZamykajacych) {
                            liczbaPierwiastkowana += wzor.charAt(j);
                        } else {
                            break;
                        }
                    }

                    wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + liczbaPierwiastkowana + "]", "(" + liczbaPierwiastkowana + ")**(1./(" + stopienPierwiastka + "))");
                } else if (wzor.charAt(i) == 'π') {
                    wzor = wzor.replace("π", lib + "pi");
                } else if (wzor.charAt(i) == '!') {
                    String liczbaSilni = "";

                    //Pętla która odczutuje co jest przed !
                    for (int j = i - 1; j >= 0; j--) {

                        //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaSilni
                        if (!String.valueOf(wzor.charAt(j)).equals("+") && !String.valueOf(wzor.charAt(j)).equals("-") && !String.valueOf(wzor.charAt(j)).equals("/") && !String.valueOf(wzor.charAt(j)).equals("*") && !String.valueOf(wzor.charAt(j)).equals("(") && !String.valueOf(wzor.charAt(j)).equals(")")) {
                            liczbaSilni += wzor.charAt(j);
                        } else break;
                    }

                    //Odwócenie liczbySilni, bo w pętli wyżej zapisuje się od tyłu i dlatego trzeba ją odwrócić
                    StringBuilder liczbaSilniOdwrocona = new StringBuilder();
                    liczbaSilniOdwrocona.append(liczbaSilni);
                    liczbaSilniOdwrocona.reverse();

                    //Wywołanie funkcji do liczenia silni
                    Long wynikSilni = silnia(Integer.parseInt(String.valueOf(liczbaSilniOdwrocona)));
                    wzor = wzor.replace(liczbaSilniOdwrocona + "!", String.valueOf(wynikSilni));
                } else if (wzor.charAt(i) == 'e') {
                    wzor = wzor.replace("e", String.valueOf(Math.E));
                } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 'g' && wzor.charAt(i + 3) == '[') {

                    String podstawaLogarytm = "", liczbaLogarytm = "";
                    int k = 0;
                    int ileOtwierajacych = 0, ileZamykajacych = 0;

                    for (int j = i + 3; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == '[') {
                            ileOtwierajacych++;
                        }
                        if (wzor.charAt(j) == ']') {
                            ileZamykajacych++;
                        }
                        if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                            podstawaLogarytm += wzor.charAt(j);
                        } else {
                            podstawaLogarytm += "]";
                            k = j;
                            break;
                        }
                    }
                    String nowaPodstawaLogarytm = podstawaLogarytm.replaceFirst("\\[", "");
                    StringBuilder str = new StringBuilder(nowaPodstawaLogarytm);
                    str.setCharAt(str.lastIndexOf("]"), ')');
                    nowaPodstawaLogarytm = String.valueOf(str);

                    ileOtwierajacych = 0;
                    ileZamykajacych = 0;
                    for (int j = k + 1; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == '[') {
                            ileOtwierajacych++;
                        }
                        if (wzor.charAt(j) == ']') {
                            ileZamykajacych++;
                        }
                        if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                            liczbaLogarytm += wzor.charAt(j);
                        } else {
                            break;
                        }
                    }
                    String nowaLiczbaLogarytm = liczbaLogarytm.replaceFirst("\\[", "(");

                    wzor = wzor.replace("log" + podstawaLogarytm + liczbaLogarytm + "]", lib + "log" + nowaLiczbaLogarytm + "," + nowaPodstawaLogarytm + "");

                } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'n' && wzor.charAt(i + 2) == '[') {
                    int ileOtwierajacych = 0, ileZamykajacych = 0;
                    String liczba = "";

                    for (int j = i + 2; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == '[') {
                            ileOtwierajacych++;
                        }
                        if (wzor.charAt(j) == ']') {
                            ileZamykajacych++;
                        }
                        if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                            liczba += wzor.charAt(j);
                        } else {
                            liczba += "]";
                            break;
                        }
                    }

                    String nowaLiczba = liczba.replaceFirst("\\[", "(");
                    StringBuilder str = new StringBuilder(nowaLiczba);
                    str.setCharAt(str.lastIndexOf("]"), ')');
                    nowaLiczba = String.valueOf(str);

                    wzor = wzor.replace("ln" + liczba, lib + "log" + nowaLiczba);
                } else if ((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 's' && wzor.charAt(i + 3) == '[') ||
                        (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'i' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                        (wzor.charAt(i) == 't' && wzor.charAt(i + 1) == 'a' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                        (wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 't' && wzor.charAt(i + 3) == '[')) {
                    kat = "";
                    int ileOtwierajacych = 0, ileZamykajacych = 0;

                    for (int j = i; j < wzor.length(); j++) {
                        if (wzor.charAt(j) == '[') {
                            ileOtwierajacych++;
                        }
                        if (wzor.charAt(j) == ']') {
                            ileZamykajacych++;
                        }
                        if (ileOtwierajacych == 0 || ileOtwierajacych != ileZamykajacych) {
                            kat += wzor.charAt(j);
                        } else {
                            kat += "]";
                            break;
                        }
                    }

                    StringBuilder str;
                    StringBuilder strLib;
                    String nowyKat;
                    if (i - 1 >= 0 && wzor.charAt(i - 1) == 'a') {
                        str = new StringBuilder(kat);
                        strLib = new StringBuilder("a");
                        strLib.append(str);
                        kat = String.valueOf(strLib);

                        str = new StringBuilder(kat);
                        strLib = new StringBuilder("sympy.");
                        strLib.append(str);
                        nowyKat = String.valueOf(strLib);
                    } else {
                        str = new StringBuilder(kat);
                        strLib = new StringBuilder("sympy.");
                        strLib.append(str);
                        nowyKat = String.valueOf(strLib);
                    }
                    if(!jednostka.equals("Jednostka")) {
                        wzor = wzor.replace(kat, nowyKat);
                        wzor = wzor.replace(nowyKat, "(" + funkcjeTrygonometryczne(nowyKat, jednostka) + ")");
                    }else throw new Exception();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception "+e);
            JOptionPane.showMessageDialog(null, "Błąd działania." , "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wzor;
    }


    public String funkcjeTrygonometryczne(String kat, String jednostka) {
        String nowyKat;
        String str2;
        StringBuilder str;

        if (kat.contains("π")) {
            nowyKat = kat.replace("π", "sympy.pi");
            str = new StringBuilder(nowyKat);
            str2 = String.valueOf(str);
            StringBuilder stringBuilder = new StringBuilder(str2);
            stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
            str2 = String.valueOf(stringBuilder);
            str2 = str2.replaceFirst("\\[", "(");
        } else {
            str = new StringBuilder(kat);
            str2 = String.valueOf(str);
            if (jednostka.equals(String.valueOf(Jednostka.RADIANY))) {
                StringBuilder stringBuilder = new StringBuilder(str2);
                stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
                str2 = String.valueOf(stringBuilder);
                str2 = str2.replaceFirst("\\[", "(");
            } else if (jednostka.equals(String.valueOf(Jednostka.STOPNIE))) {
                if (kat.contains("x")) {
                    zmianaJednostki(false, true);
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    stringBuilder.setCharAt(str2.lastIndexOf("]"), ')');
                    str2 = String.valueOf(stringBuilder);
                    str2 = str2.replaceFirst("\\[", "(");
                } else {
                    zmianaJednostki(true, false);
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

    public void zmianaJednostki(boolean stopnie, boolean radiany) {
        if (!stopnie && radiany) {
            pressedJednostka = String.valueOf(Jednostka.RADIANY);
        } else if (stopnie && !radiany) {
            pressedJednostka = String.valueOf(Jednostka.STOPNIE);
        }
    }

    public static Long silnia(int i) {
        if (i < 1) return 1L;
        else return i * silnia(i - 1);
    }

    public String przeksztalcenieWyniku(String wzor) {
        for (int i = 0; i < wzor.length(); i++) {

            if (wzor.charAt(i) == '*' && wzor.charAt(i + 1) == '*') {
                String wykladnik = "";

                for (int j = i + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == '/' || wzor.charAt(j) == '+' || wzor.charAt(j) == '-' || wzor.charAt(j) == '*') {
                        break;
                    } else {
                        wykladnik += wzor.charAt(j);
                    }
                }
                wzor = wzor.replace("**" + wykladnik, "<sup>" + wykladnik + "</sup>");
            }

            if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 'g') {
                wzor = wzor.replace("log", "ln");
            }
        }
        wzor = wzor.replace("sympy." ,"");
        wzor = wzor.replace("math." ,"");
        wzor = wzor.replace("radians" ,"");
        return wzor;
    }

    public String wykresPrzeksztalcenie(String wzor){
        String kat = "";
        for(int i=0; i<wzor.length();i++){
            if ((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 's' && wzor.charAt(i + 3) == '(')) {
              if(i-1>=0 && wzor.charAt(i-1)!='.' && wzor.charAt(i-1)!='a'){
                  wzor=wzor.replace("cos","sympy.cos");
              }
              if(wzor.charAt(i-1)=='a' && i-2>=0 && wzor.charAt(i-2)!='.' ){
                    wzor=wzor.replace("acos","sympy.acos");
                }
            }
            if ((wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'i' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '(')) {
                if(i-1>=0 && wzor.charAt(i-1)!='.' && wzor.charAt(i-1)!='a'){
                    wzor=wzor.replace("sin","sympy.sin");
                }
                if(wzor.charAt(i-1)=='a' && i-2>=0 && wzor.charAt(i-2)!='.' ){
                    wzor=wzor.replace("asin","sympy.asin");
                }
            }
            if ((wzor.charAt(i) == 't' && wzor.charAt(i + 1) == 'a' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '(')) {
                if(i-1>=0 && wzor.charAt(i-1)!='.' && wzor.charAt(i-1)!='a'){
                    wzor=wzor.replace("tan","sympy.tan");
                }
                if(wzor.charAt(i-1)=='a' && i-2>=0 && wzor.charAt(i-2)!='.' ){
                    wzor=wzor.replace("atan","sympy.atan");
                }
            }
            if ((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 't' && wzor.charAt(i + 3) == '(')) {
                if(i-1>=0 && wzor.charAt(i-1)!='.' && wzor.charAt(i-1)!='a'){
                    wzor=wzor.replace("cot","sympy.cot");
                }
                if(wzor.charAt(i-1)=='a' && i-2>=0 && wzor.charAt(i-2)!='.' ){
                    wzor=wzor.replace("acot","sympy.acot");
                }
            }
        }


        return wzor;
    }
}
