package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.python.util.PythonInterpreter;

import javax.swing.*;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.text.AttributedString;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorCalka implements Initializable {

    @FXML
    public Label granicaDolnaWzor;
    @FXML
    public Label granicaGornaWzor;
    @FXML
    public ChoiceBox<String> metodaCalkowania;
    @FXML
    public TextField liczbaPodprzedzialow;
    @FXML
    public TextField granicaDolna;
    @FXML
    public TextField granicaGorna;
    @FXML
    public TextField wartoscCalki;
    @FXML
    public TextField wynikCalka;
    @FXML
    public TextField wpisanaCalka;
    @FXML
    public TextField ukrytyWzorCalki;
    @FXML
    public RadioButton radianyButton;
    @FXML
    public RadioButton stopnieButton;
    @FXML
    public WebView webView;

    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;


    private static String pressedField = String.valueOf(KilknietePole.CALKA);
    Unicode unicode = new Unicode();

    public void ChoiceBoxMetodaCalk() {
        ObservableList rodzajMetodyCalkowania = FXCollections.observableArrayList();
        rodzajMetodyCalkowania.removeAll(rodzajMetodyCalkowania);
        String a = "Metoda całkowania";
        String b = "Metoda prostokątów z niedomiarem (c. numeryczne)";
        String c = "Metoda prostokątów z nadmiarem (c. numeryczne)";
        String d = "Metoda trapezów (c. numeryczne)";
        String e = "Metoda Simpsona (c.numeryczne)";
        String f = "Metoda analityczna";
        rodzajMetodyCalkowania.addAll(b, c, d, e, f);
        metodaCalkowania.getItems().addAll(rodzajMetodyCalkowania);
        metodaCalkowania.setValue(a);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBoxMetodaCalk();
    }

    public void liczbaPodprzedzialowCheck() {
        String komunikat = "";
        try {
            if (liczbaPodprzedzialow.getText().isEmpty()) {
                komunikat = "Liczba podprzedziałów nie może pozostać pusta.";
                throw new Exception(komunikat);
            } else if (!liczbaPodprzedzialow.getText().matches("[0-9]+")) {
                komunikat = "Liczba podprzedziałów nie może zawierać liter i znaków specjalnych. Należy podać liczbę naturalną.";
                throw new Exception(komunikat);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean graniceCheck() {
        String komunikat = "";
        boolean er = false;
        try {
            if (granicaDolna.getText().isEmpty() || granicaGorna.getText().isEmpty()) {
                komunikat = "Granica całki nie może pozostać pusta.";
                throw new Exception(komunikat);
            }/*else
        if(!granicaDolna.getText().matches("[0-9]+") && !granicaDolna.getText().matches("cos.*") && !granicaDolna.getText().matches("sin.*")){
            JOptionPane.showMessageDialog(null,"Liczba podprzedziałów nie może zawierać liter i znaków specjalnych. Należy podać liczbę naturalną.","Alert",JOptionPane.WARNING_MESSAGE);
        }*/

            if (granicaDolna.getText().matches("[0-9]+")) {
                granicaDolna.setText(String.valueOf(Float.valueOf(granicaDolna.getText())));
            }
            if (granicaGorna.getText().matches("[0-9]+")) {
                granicaGorna.setText(String.valueOf(Float.valueOf(granicaGorna.getText())));
            }

            if (granicaDolna.getText().matches("(.*)∞(.*)")) {
                if (!granicaDolna.getText().equals("-∞") && !granicaDolna.getText().equals("+∞")) {
                    komunikat = "Błędna wartość dolnej granicy.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }
            if (granicaGorna.getText().matches("(.*)∞(.*)")) {
                if (!granicaGorna.getText().equals("-∞") && !granicaGorna.getText().equals("+∞")) {
                    komunikat = "Błędna wartość górnej granicy.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }
            if (granicaGorna.getText().matches("(.*)∞") || granicaDolna.getText().matches("(.*)∞")) {
                if (!metodaCalkowania.getValue().equals("Metoda analityczna")) {
                    komunikat = "Granice nieskończoności mogą być jedynie użyte przy całkowaniu metodą analityczną.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return er;
    }

    public void wynikOnAction() {
        MetodyCalkowania metodyCalkowania = new MetodyCalkowania();

        if (!graniceCheck()) {
            granicaGornaWzor.setText(zmianaRownania(granicaGorna.getText()));
            granicaDolnaWzor.setText(zmianaRownania(granicaDolna.getText()));

            wartoscCalki.setText(zmianaRownania(wpisanaCalka.getText()));

            switch (metodaCalkowania.getValue()) {
                case "Metoda prostokątów z niedomiarem (c. numeryczne)" -> {
                    liczbaPodprzedzialowCheck();
                    granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                    granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                    ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                    wynikCalka.setText(metodyCalkowania.metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                }
                case "Metoda prostokątów z nadmiarem (c. numeryczne)" -> {
                    liczbaPodprzedzialowCheck();
                    granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                    granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                    ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                    wynikCalka.setText(metodyCalkowania.metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                }
                case "Metoda trapezów (c. numeryczne)" -> {
                    liczbaPodprzedzialowCheck();
                    granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                    granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                    ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                    wynikCalka.setText(metodyCalkowania.metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                }
                case "Metoda Simpsona (c.numeryczne)" -> {
                    liczbaPodprzedzialowCheck();
                    granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                    granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                    ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                    wynikCalka.setText(metodyCalkowania.metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
                }
                case "Metoda analityczna" -> {
                    liczbaPodprzedzialow.clear();
                    granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "sympy.");
                    granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "sympy.");
                    ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "sympy."));
                    wynikCalka.setText(metodyCalkowania.metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona));
                }
                default ->
                        JOptionPane.showMessageDialog(null, "Proszę wybrać metodę całkowania.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public String przeksztalcenieRownania(String wzor, String lib) {
        PythonInterpreter interpreter = new PythonInterpreter();
        // wartoscCalki.setText(wpisanaCalka.getText());

        Pattern kropka = Pattern.compile(".");

        String licznik = "";

        String kat = "";

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
                wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + liczbaPierwiastkowana + "]", liczbaPierwiastkowana + "**(1./(" + stopienPierwiastka + "))");
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

                wzor = wzor.replace(kat, "("+funkcjeTrygonometryczne(kat)+")");
                System.out.println(wzor);
            }
        }
        return wzor;
    }

    public String funkcjeTrygonometryczne(String kat) {
        String nowyKat = "";

        StringBuilder str;
        StringBuilder strLib;
        String str2 = "";
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
            if (stopnieButton.isSelected() && !radianyButton.isSelected()) {
                str2 = str2.replace("]", ")");
                str2 = str2.replace("[", "(");
            } else if (!stopnieButton.isSelected() && radianyButton.isSelected()) {
                str2 = str2.replace("]", "))");
                str2 = str2.replace("[", "(math.radians(");
            }
        }
        return str2;
    }

    public static Long silnia(int i) {
        if (i < 1) return 1L;
        else return i * silnia(i - 1);
    }

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
                String stopienPierwiastka = "", stopienPierwiastkaKodowany = "", pierwiastek = "";
                int k = 0;

                for (int j = i + 5; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ',') {
                        k = j;
                        break;
                    } else {
                        stopienPierwiastka += wzor.charAt(j);
                    }
                }

                for (int j = 0; j < stopienPierwiastka.length(); j++) {
                    stopienPierwiastkaKodowany += unicode.kodowanieIndeksGorny(String.valueOf(stopienPierwiastka.charAt(j)));
                }

                for (int j = k + 1; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        pierwiastek += wzor.charAt(j);
                    }
                }

                wzor = wzor.replace("sqrt[" + stopienPierwiastka + "," + pierwiastek + "]", stopienPierwiastkaKodowany + "√" + pierwiastek);

            } else if (wzor.charAt(i) == 'l' && wzor.charAt(i + 1) == 'o' && wzor.charAt(i + 2) == 'g' && wzor.charAt(i + 3) == '[') {
                String podstawaLog = "", podstawaLogKodowana = "", liczbaLog = "";
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

                for (int j = 0; j < podstawaLog.length(); j++) {
                    podstawaLogKodowana += unicode.kodowanieIndeksDolny(String.valueOf(podstawaLog.charAt(j)));
                }

                wzor = wzor.replace("log[" + podstawaLog + "][" + liczbaLog + "]", "log" + podstawaLogKodowana + liczbaLog);

            } else if (wzor.charAt(i) == '^') {
                String wykladnik = "", wykladnikKodowany = "";

                //Zapisanie wykladnika
                for (int j = i + 2; j < wzor.length(); j++) {
                    if (wzor.charAt(j) == ']') {
                        break;
                    } else {
                        wykladnik += wzor.charAt(j);
                    }
                }
                //Kodowanie wykladnika
                for (int j = 0; j < wykladnik.length(); j++) {
                    wykladnikKodowany += unicode.kodowanieIndeksGorny(String.valueOf(wykladnik.charAt(j)));
                }

                wzor = wzor.replace("^[" + wykladnik + "]", wykladnikKodowany);

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

                if (kat.matches("(.*)^(.*)")) {
                    kat += "]";
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

    public void piOnAction(ActionEvent event) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "π");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "π");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "π");
        }
    }

    public void plusOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "+");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "+");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "+");
        }
    }

    public void minusOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "-");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "-");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "-");
        }
    }

    public void razyOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "*");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "*");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "*");
        }
    }

    public void dzielenieOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "/");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "/");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "/");
        }
    }

    public void nawiastLewyOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "(");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "(");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "(");
        }
    }

    public void nawiasPrawyOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + ")");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + ")");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + ")");
        }
    }

    public void silniaOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "!");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "!");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "!");
        }
    }

    public void eulerOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "e");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "e");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "e");
        }
    }

    public void logarytmOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "log[?][?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "log[?][?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "log[?][?]");
        }
    }

    public void logarytmNaturalnyOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "ln[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "ln[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "ln[?]");
        }
    }

    public void pierwiastekKwadratowyOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[2,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[2,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[2,?]");
        }
    }

    public void potegaStopniaNOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[?]");
        }
    }

    public void pierwiastekStopniaNOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[?,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[?,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[?,?]");
        }
    }

    public void potegaKwadratowaOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[2]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[2]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[2]");
        }
    }

    public void sinOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sin[?]");
        }
    }

    public void cosOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cos[?]");
        }
    }

    public void cotOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cot[?]");
        }
    }

    public void atanOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "atan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "atan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "atan[?]");
        }
    }

    public void acosOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acos[?]");
        }
    }

    public void acotOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acot[?]");
        }
    }

    public void asinOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "asin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "asin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "asin[?]");
        }
    }

    public void tanOnAction(ActionEvent actionEvent) {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "tan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "tan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "tan[?]");
        }
    }

    public void granicaDolnaPressed(MouseEvent mouseEvent) {
        pressedField = String.valueOf(KilknietePole.GRANICA_DOLNA);
    }

    public void calkaPressed(MouseEvent mouseEvent) {
        pressedField = String.valueOf(KilknietePole.CALKA);
    }

    public void granicaGornaPressed(MouseEvent mouseEvent) {
        pressedField = String.valueOf(KilknietePole.GRANICA_GORNA);
    }

    public void nieskonczonoscOnAction(ActionEvent actionEvent) {
        String komunikat = "";
        try {
            switch (pressedField) {
                case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "∞");
                case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "∞");
                case "CALKA" -> {
                    komunikat = "Znak nieskończoności nie może zostać wpisany we wzór całki.";
                    throw new Exception(komunikat);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
}