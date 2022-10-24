package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrzeksztalcenieRownania {
    public String pressedJednostka = String.valueOf(Jednostka.NONE);
    public String zmianaRownania(String wzor) {

        for (int i = 0; i < wzor.length(); i++) {

            if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'n' && wzor.charAt(i + 2) == '[') {
                String liczba = "";
                for (int j = i + 3; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        liczba += wzor.charAt(j);
                    }
                }

                wzor = wzor.replace("ln[" + liczba + "]", "ln(" + liczba + ")");

            } else if (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'q' && wzor.charAt(i + 2) == 'r' && wzor.charAt(i + 3) == 't') {
                String stopienPierwiastka = "",  pierwiastek = "";
                int k = 0;

                for (int j = i + 5; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ',') {
                        k = j;
                        break;
                    } else {
                        stopienPierwiastka += wzor.charAt(j);
                    }
                }

                for (int j = k + 1; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        pierwiastek += wzor.charAt(j);
                    }
                }

                if(pierwiastek.matches("(.*)\\[(.*)")){
                    pierwiastek+="]";
                }
                wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + pierwiastek + "]", "<sup>"+stopienPierwiastka + "</sup>&#8730;(" + pierwiastek+")");

            } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 'g' && wzor.charAt(i + 3) == '[') {
                String podstawaLog = "", liczbaLog = "";
                int k = 0;

                for (int j = i + 4; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        k = j;
                        break;
                    } else {
                        podstawaLog += wzor.charAt(j);
                    }
                }

                for (int j = k + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        liczbaLog += wzor.charAt(j);
                    }
                }

                if(podstawaLog.matches("(.*)sin(.*)") || podstawaLog.matches("(.*)cos(.*)") || podstawaLog.matches("(.*)tan(.*)") || podstawaLog.matches("(.*)cot(.*)")){
                    podstawaLog+="]";
                    liczbaLog=liczbaLog.replace("[","");
                }

                if(liczbaLog.matches("(.*)sin(.*)") || liczbaLog.matches("(.*)cos(.*)") || liczbaLog.matches("(.*)tan(.*)") || liczbaLog.matches("(.*)cot(.*)")){
                    liczbaLog+="]";
                }

                wzor = wzor.replace("log[" + podstawaLog + "][" + liczbaLog + "]", "log<sub>" + podstawaLog+"</sub>" + liczbaLog);

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
                System.out.println(wykladnik);
                wzor = wzor.replace("^[" + wykladnik + "]", "<sup>"+wykladnik+"</sup>");

            } else if ((((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 's') ||
                    (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'i' && wzor.charAt(i + 2) == 'n') ||
                    (wzor.charAt(i) == 't' && wzor.charAt(i + 1) == 'a' && wzor.charAt(i + 2) == 'n') ||
                    (wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 't')) ||
                    (wzor.charAt(i) == 'a' && (wzor.charAt(i + 1) == 's' || wzor.charAt(i + 1) == 'c' || wzor.charAt(i + 1) == 't') && (wzor.charAt(i + 2) == 'i' || wzor.charAt(i + 2) == 'o' || wzor.charAt(i + 2) == 'a') && (wzor.charAt(i + 3) == 'n' || wzor.charAt(i + 3) == 's' || wzor.charAt(i + 3) == 't')))) {
                String kat = "";

                for (int j = i; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        kat += "]";
                        break;
                    } else {
                        kat += wzor.charAt(j);
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
        Pattern kropka = Pattern.compile(".");
        String licznik = "";
        String kat;

        for (int i = 0; i < wzor.length(); i++) {
            if (wzor.charAt(i) == '/') {
                for (int j = i - 1; j >= 0; j--) {
                    if (wzor.charAt(j) == '+' || wzor.charAt(j) == 'π' || wzor.charAt(j) == '-' || wzor.charAt(j) == '*' || wzor.charAt(j) == '/' || wzor.charAt(j) == 'x' || wzor.charAt(j) == '(' || wzor.charAt(j) == ')' || wzor.charAt(j) == '[' || wzor.charAt(j) == ']') {
                        break;
                    } else {
                        licznik += wzor.charAt(j);
                    }
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
                wzor = wzor.replace("∞", "sympy.oo");
            }

        }

        for (int i = 0; i < wzor.length(); i++) {

            if (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'q' && wzor.charAt(i + 2) == 'r' && wzor.charAt(i + 3) == 't') {
                String stopienPierwiastka = "", liczbaPierwiastkowana = "";
                int k = 0;

                for (int j = i + 5; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ',') {
                        k = j;
                        break;
                    } else stopienPierwiastka += wzor.charAt(j);
                }
                for (int j = k + 1; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else liczbaPierwiastkowana += wzor.charAt(j);
                }
                wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + liczbaPierwiastkowana + "]", "("+liczbaPierwiastkowana + ")**(1./(" + stopienPierwiastka + "))");
            } else if (wzor.charAt(i) == 'π') {
                wzor = wzor.replace("π", lib + "pi");
            } else if (wzor.charAt(i) == '!') {
                String liczbaSilni = "";

                //Pętla która odczutuje co jest przed !
                for (int j = i - 1; j >= 0; j--) {

                    //Pętla warunkowa jeśli aktualny znak jest równy +,-,/,* to pętla for zostaje przerwana w przeciwnym wypadku znak jest zapisany do liczbaSilni
                    if (!String.valueOf(wzor.charAt(j)).equals("+") && !String.valueOf(wzor.charAt(j)).equals("-") && !String.valueOf(wzor.charAt(j)).equals("/") && !String.valueOf(wzor.charAt(j)).equals("*")) {
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

                for (int j = i + 4; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        k = j;
                        break;
                    } else {
                        podstawaLogarytm += wzor.charAt(j);
                    }
                }

                for (int j = k + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        liczbaLogarytm += wzor.charAt(j);
                    }
                }

                if(podstawaLogarytm.matches("(.*)sin(.*)") || podstawaLogarytm.matches("(.*)cos(.*)") || podstawaLogarytm.matches("(.*)tan(.*)") || podstawaLogarytm.matches("(.*)cot(.*)")){
                    podstawaLogarytm+="]";
                    liczbaLogarytm=liczbaLogarytm.replace("[","");
                }
                if(liczbaLogarytm.matches("(.*)sin(.*)") || liczbaLogarytm.matches("(.*)cos(.*)") || liczbaLogarytm.matches("(.*)tan(.*)") || liczbaLogarytm.matches("(.*)cot(.*)")){
                    liczbaLogarytm+="]";
                }
                wzor = wzor.replace("log[" + podstawaLogarytm + "][" + liczbaLogarytm + "]", "sympy.log(" + liczbaLogarytm + "," + podstawaLogarytm + ")");

            } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'n' && wzor.charAt(i+2)=='[') {
                int k = 0;
                for (int j = i; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        k = j;
                        break;
                    }
                }

                StringBuilder str = new StringBuilder(wzor);
                str.setCharAt(k, ')');
                wzor = String.valueOf(str);
                wzor = wzor.replace("ln[", lib + "log(");

            } else if (((wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 's' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 's' && wzor.charAt(i + 1) == 'i' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 't' && wzor.charAt(i + 1) == 'a' && wzor.charAt(i + 2) == 'n' && wzor.charAt(i + 3) == '[') ||
                    (wzor.charAt(i) == 'c' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 't' && wzor.charAt(i + 3) == '[')) ||
                    (wzor.charAt(i) == 'a' && (wzor.charAt(i + 1) == 's' || wzor.charAt(i + 1) == 'c' || wzor.charAt(i + 1) == 't') && (wzor.charAt(i + 2) == 'i' || wzor.charAt(i + 2) == 'o' || wzor.charAt(i + 2) == 'a') && (wzor.charAt(i + 3) == 'n' || wzor.charAt(i + 3) == 's' || wzor.charAt(i + 3) == 't') && wzor.charAt(i + 4) == '[')) {
                kat = "";


                for (int j = i; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        kat += "]";
                        break;
                    } else {
                        kat += wzor.charAt(j);
                    }
                }

                if(kat.charAt(kat.length()-1)!=']'){
                    kat += "]";
                }

                wzor = wzor.replace(kat, "("+funkcjeTrygonometryczne(kat,jednostka)+")");
                System.out.println(wzor);

            }
        }
        return wzor;
    }

    public String funkcjeTrygonometryczne(String kat, String jednostka) {
        String nowyKat;
        String str2;
        StringBuilder str;
        StringBuilder strLib;

        if (kat.contains("π")) {
            nowyKat = kat.replace("π", "sympy.pi");
            str = new StringBuilder(nowyKat);
            strLib = new StringBuilder("sympy.");
            strLib.append(str);
            str2 = String.valueOf(strLib);
            str2 = str2.replace("]", ")");
            str2 = str2.replace("[", "(");
        } else {
            str = new StringBuilder(kat);
            strLib = new StringBuilder("sympy.");
            strLib.append(str);
            str2 = String.valueOf(strLib);
            if (jednostka.equals(String.valueOf(Jednostka.RADIANY))) {
                str2 = str2.replace("]", ")");
                str2 = str2.replace("[", "(");
            } else if (jednostka.equals(String.valueOf(Jednostka.STOPNIE))) {
                if(kat.contains("x")){
                    zmianaJednostki(false,true);
                    str2 = str2.replace("]", ")");
                    str2 = str2.replace("[", "(");
                }else {
                    zmianaJednostki(true,false);
                    str2 = str2.replace("]", "))");
                    str2 = str2.replace("[", "(math.radians(");
                }
            }
        }
        return str2;
    }

    public void zmianaJednostki(boolean stopnie, boolean radiany){
        if(!stopnie && radiany) {
            pressedJednostka= String.valueOf(Jednostka.RADIANY);
        }else if(stopnie && !radiany){
            pressedJednostka= String.valueOf(Jednostka.STOPNIE);
        }
    }

    public static Long silnia(int i) {
        if (i < 1) return 1L;
        else return i * silnia(i - 1);
    }

}
