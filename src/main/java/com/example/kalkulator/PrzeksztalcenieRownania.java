package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

public class PrzeksztalcenieRownania {
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
}
