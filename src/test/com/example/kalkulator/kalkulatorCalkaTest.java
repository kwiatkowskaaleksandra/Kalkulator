package com.example.kalkulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class kalkulatorCalkaTest extends MetodyCalkowania {
    public String metoda;
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;

    kalkulatorCalka calka = new kalkulatorCalka();
    String ukrytyWzorCalki;

    @Test
    void testPoprawnaCalka() {
        String wpisanaCalka = "sin[x]+cos[π]";
        String grDolna = "1";
        String grGorna = "12";
        String liczbaPodprzedzialow = "4";
        String jednostka = "Stopnie";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertEquals("-9.187972366282045", wynikMetodaProstokatowNiedomiar);

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertEquals("-12.977593099004954", wynikMetodaProstokatowNadmiar);

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertEquals("-11.0827827326435", wynikMetodaTrapezow);

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertEquals("-11.78425351625917", wynikMetodaSimpsona);

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertEquals("-11.3035516528644", wynikMetodaAnalityczna);

    }

    @Test
    void testBledneGranice() {
        String wpisanaCalka = "2";
        String grDolna = "m5";
        String grGorna = "w1";
        String liczbaPodprzedzialow = "12.2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testPustePola() {
        String wpisanaCalka = "";
        String grDolna = "";
        String grGorna = "";
        String liczbaPodprzedzialow = "";
        String jednostka = "";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testBlednaLiczbaPodprzedzialow() {
        String wpisanaCalka = "2";
        String grDolna = "-∞";
        String grGorna = "+∞";
        String liczbaPodprzedzialow = "-1";
        String jednostka = "";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));
    }

    @Test
    void testNieusunietyZnakSpecjalny() {
        String wpisanaCalka = "2*cos[?]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testFunkcjaTrygBezJednostki() {
        String wpisanaCalka = "2*cos[10]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Jednostka";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testBledneDzialanie() {
        String wpisanaCalka = "rownanie";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testStringLiczbaPodprzedzialow() {
        String wpisanaCalka = "2*cos[x]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "podprzedzialy";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));
    }

    @Test
    void testPustaLiczbaPodprzedzialow() {
        String wpisanaCalka = "2*cos[x]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));
    }

    @Test
    void tesstPusteDzialanie() {
        String wpisanaCalka = "";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testBlednaGranicaDolna() {
        String wpisanaCalka = "2*cos[x]";
        String grDolna = "∞+cos[x]";
        String grGorna = "-4";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    @Test
    void testBlednaGranicaGorna() {
        String wpisanaCalka = "2*cos[x]";
        String grDolna = "3";
        String grGorna = "-sin[3]*∞";
        String liczbaPodprzedzialow = "2";
        String jednostka = "Radiany";

        String wynikMetodaProstokatowNiedomiar = wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNiedomiar));

        String wynikMetodaProstokatowNadmiar = wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaProstokatowNadmiar));

        String wynikMetodaTrapezow = wynikCalkaMetodaTrapezowTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaTrapezow));

        String wynikMetodaSimpsona = wynikCalkaMetodaSimpsonaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaSimpsona));

        String wynikMetodaAnalityczna = wynikCalkaMetodaAnalitycznaTest(wpisanaCalka, grDolna, grGorna, liczbaPodprzedzialow, jednostka);
        Assertions.assertFalse(Boolean.parseBoolean(wynikMetodaAnalityczna));
    }

    String wynikCalkaMetodaProstokatowNiedomiarTest(String wpisanaCalkaWzor, String granicaDolna, String granicaGorna, String lPodprzedzialow, String jednostka) {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda prostokątów z niedomiarem";
        String wynik = "";
        if (calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)) {
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna = (String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna = (String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: " + przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: " + przeksztalcenie.zmianaRownania(granicaDolna));

            if (metoda.equals("Metoda prostokątów z niedomiarem")) {
                if (calka.liczbaPodprzedzialowCheck(lPodprzedzialow)) {
                    wynikPrzeksztalcenie(przeksztalcenie, granicaDolna, granicaGorna, wpisanaCalkaWzor);
                    if (!metodaProstokatowZNiedomiarem(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow).equals("")) {
                        System.out.println("wzor: " + przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        wynik = metodaProstokatowZNiedomiarem(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow);
                    }
                }
            }
        }
        return wynik;
    }


    String wynikCalkaMetodaProstokatowNadmiarTest(String wpisanaCalkaWzor, String granicaDolna, String granicaGorna, String lPodprzedzialow, String jednostka) {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda prostokątów z nadmiarem";
        String wynik = "";
        if (calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)) {
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna = (String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna = (String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: " + przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: " + przeksztalcenie.zmianaRownania(granicaDolna));

            if (metoda.equals("Metoda prostokątów z nadmiarem")) {
                if (calka.liczbaPodprzedzialowCheck(lPodprzedzialow)) {
                    wynikPrzeksztalcenie(przeksztalcenie, granicaDolna, granicaGorna, wpisanaCalkaWzor);
                    if (!metodaProstokatowZNadmiarem(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow).equals("")) {
                        System.out.println("wzor: " + przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        wynik = metodaProstokatowZNadmiarem(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow);
                    }
                }
            }
        }
        return wynik;
    }


    String wynikCalkaMetodaTrapezowTest(String wpisanaCalkaWzor, String granicaDolna, String granicaGorna, String lPodprzedzialow, String jednostka) {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda trapezów";
        String wynik = "";
        if (calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)) {
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna = (String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna = (String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: " + przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: " + przeksztalcenie.zmianaRownania(granicaDolna));

            if (metoda.equals("Metoda trapezów")) {
                if (calka.liczbaPodprzedzialowCheck(lPodprzedzialow)) {
                    wynikPrzeksztalcenie(przeksztalcenie, granicaDolna, granicaGorna, wpisanaCalkaWzor);
                    if (!metodaTrapezow(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow).equals("")) {
                        System.out.println("wzor: " + przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        wynik = metodaTrapezow(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow);
                    }
                }
            }
        }
        return wynik;
    }


    String wynikCalkaMetodaSimpsonaTest(String wpisanaCalkaWzor, String granicaDolna, String granicaGorna, String lPodprzedzialow, String jednostka) {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda Simpsona";
        String wynik = "";
        if (calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)) {
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna = (String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna = (String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: " + przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: " + przeksztalcenie.zmianaRownania(granicaDolna));

            if (metoda.equals("Metoda Simpsona")) {
                if (calka.liczbaPodprzedzialowCheck(lPodprzedzialow)) {
                    wynikPrzeksztalcenie(przeksztalcenie, granicaDolna, granicaGorna, wpisanaCalkaWzor);
                    if (!metodaSimpsona(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow).equals("")) {
                        System.out.println("wzor: " + przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        wynik = metodaSimpsona(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, lPodprzedzialow);
                    }
                }
            }
        }
        return wynik;
    }


    String wynikCalkaMetodaAnalitycznaTest(String wpisanaCalkaWzor, String granicaDolna, String granicaGorna, String lPodprzedzialow, String jednostka) {
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda analityczna";
        String wynik = "";
        if (calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)) {
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna = (String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna = (String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: " + przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: " + przeksztalcenie.zmianaRownania(granicaDolna));

            if (metoda.equals("Metoda analityczna")) {
                wynikPrzeksztalcenie(przeksztalcenie, granicaDolna, granicaGorna, wpisanaCalkaWzor);
                if (!metodaAnalityczna(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona).equals("")) {
                    System.out.println("wzor: " + przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                    wynik = metodaAnalityczna(ukrytyWzorCalki, granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona);
                }
            }
        }
        return wynik;
    }

    public String wynikPrzeksztalcenie(PrzeksztalcenieRownania przeksztalcenie, String grDolna, String grGorna, String wpisanaCalka) {
        String jednostka = "";
        granicaDolnaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(grDolna, "sympy.", pressedJed);
        granicaGornaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(grGorna, "sympy.", pressedJed);
        ukrytyWzorCalki = przeksztalcenie.przeksztalcenieRownania(wpisanaCalka, "sympy.", pressedJed);
        if (Objects.equals(przeksztalcenie.pressedJednostka, "RADIANY")) {
            jednostka = "Radiany";
        } else if (Objects.equals(przeksztalcenie.pressedJednostka, "STOPNIE")) {
            jednostka = "Stopnie";
        }
        return jednostka;
    }

    @Test
    void wykresTest() {
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie = "2*x";
        int xMinSpinner = 1;
        int xMaxSpinner = 5;

        if (calka.wykresCheck(wpisaneDzialanie, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = wykres.listaX(wpisaneDzialanie, xMaxSpinner, xMinSpinner);
                double[] y = wykres.listaY(wpisaneDzialanie, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Wykres");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("exx" + w);
            }
        }
    }

    @Test
    void wykresTest2() {
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie = "x*ln[1]";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;

        if (calka.wykresCheck(wpisaneDzialanie, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = wykres.listaX(wpisaneDzialanie, xMaxSpinner, xMinSpinner);
                double[] y = wykres.listaY(wpisaneDzialanie, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Wykres");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("exx" + w);
            }
        }
    }

    @Test
    void wykresTest3() {
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie = "";
        int xMinSpinner = -1;
        int xMaxSpinner = 5;

        if (calka.wykresCheck(wpisaneDzialanie, xMaxSpinner, xMinSpinner)) {
            try {
                Plot2DPanel plotPanel = new Plot2DPanel();
                double[] x = wykres.listaX(wpisaneDzialanie, xMaxSpinner, xMinSpinner);
                double[] y = wykres.listaY(wpisaneDzialanie, xMaxSpinner, xMinSpinner, x);

                plotPanel.addLegend("SOUTH");
                plotPanel.addLinePlot("f(x)", Color.BLUE, x, y);

                JFrame frame = new JFrame("Wykres");
                frame.setContentPane(plotPanel);
                frame.setSize(700, 700);
                frame.setVisible(true);
            } catch (Exception w) {
                System.out.println("exx" + w);
            }
        }
    }
}