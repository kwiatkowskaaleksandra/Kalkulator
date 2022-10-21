package com.example.kalkulator;/*
 * @project Kalkulator
 * @author kola
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorCalka implements Initializable {

    @FXML
    public Label granicaDolnaWzor;
    @FXML
    public Label granicaGornaWzor;
    @FXML
    public TextField liczbaPodprzedzialow;
    @FXML
    public TextField granicaDolna;
    @FXML
    public TextField granicaGorna;
    @FXML
    public TextField wynikCalka;
    @FXML
    public TextField wpisanaCalka;
    @FXML
    public TextField ukrytyWzorCalki;
    @FXML
    public WebView webView;
    @FXML
    public RadioMenuItem radianItem;
    @FXML
    public RadioMenuItem stopnieItem;
    @FXML
    public RadioMenuItem mAnalitycznaItem;
    @FXML
    public RadioMenuItem mSimpsonaItem;
    @FXML
    public RadioMenuItem mTrapezowItem;
    @FXML
    public RadioMenuItem mProstokataNadomItem;
    @FXML
    public RadioMenuItem mProstokataNiedomItem;
    @FXML
    public MenuBar menuBar;


    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;

    private static String pressedField = String.valueOf(KilknietePole.CALKA);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mAnalitycznaItem.setSelected(true);
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
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
                if (!mAnalitycznaItem.isSelected()) {
                    komunikat = "Granice nieskończoności mogą być jedynie użyte przy całkowaniu metodą analityczną.";
                    er = true;
                    throw new Exception(komunikat);
                }
            }

            if(wpisanaCalka.getText().matches("(.*)cos(.*)") || wpisanaCalka.getText().matches("(.*)sin(.*)") || wpisanaCalka.getText().matches("(.*)tan(.*)") || wpisanaCalka.getText().matches("(.*)cot(.*)") ||
                    granicaGorna.getText().matches("(.*)cos(.*)") || granicaGorna.getText().matches("(.*)sin(.*)") || granicaGorna.getText().matches("(.*)tan(.*)") || granicaGorna.getText().matches("(.*)cot(.*)") ||
            granicaDolna.getText().matches("(.*)cos(.*)") || granicaDolna.getText().matches("(.*)sin(.*)") || granicaDolna.getText().matches("(.*)tan(.*)") || granicaDolna.getText().matches("(.*)cot(.*)"))
            {
                if(!radianItem.isSelected() && !stopnieItem.isSelected()){
                    komunikat="Proszę wybrać jedną jednostkę: stopnie lub radiany.";
                    er=true;
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
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        WebEngine webEngine = webView.getEngine();
        if (!graniceCheck()) {
            granicaGornaWzor.setText(przeksztalcenieRownania.zmianaRownania(granicaGorna.getText()));
            granicaDolnaWzor.setText(przeksztalcenieRownania.zmianaRownania(granicaDolna.getText()));

           // wartoscCalki.setText(przeksztalcenieRownania.zmianaRownania(wpisanaCalka.getText()));
            webEngine.loadContent("<p>("+przeksztalcenieRownania.zmianaRownania(wpisanaCalka.getText())+")dx</p>","text/html");

            if(mProstokataNiedomItem.isSelected()){
                liczbaPodprzedzialowCheck();
                granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNiedomiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mProstokataNadomItem.isSelected()){
                liczbaPodprzedzialowCheck();
                granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                wynikCalka.setText(metodyCalkowania.metodaProstokatowZNadmiarem(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mTrapezowItem.isSelected()){
                liczbaPodprzedzialowCheck();
                granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                wynikCalka.setText(metodyCalkowania.metodaTrapezow(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mSimpsonaItem.isSelected()){
                liczbaPodprzedzialowCheck();
                granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "math.");
                granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "math.");
                ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "math."));
                wynikCalka.setText(metodyCalkowania.metodaSimpsona(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona, liczbaPodprzedzialow.getText()));
            }else if(mAnalitycznaItem.isSelected()){
                liczbaPodprzedzialow.clear();
                granicaDolnaPrzeksztalcona = przeksztalcenieRownania(granicaDolna.getText(), "sympy.");
                granicaGornaPrzeksztalcona = przeksztalcenieRownania(granicaGorna.getText(), "sympy.");
                ukrytyWzorCalki.setText(przeksztalcenieRownania(wpisanaCalka.getText(), "sympy."));
                wynikCalka.setText(metodyCalkowania.metodaAnalityczna(ukrytyWzorCalki.getText(), granicaDolnaPrzeksztalcona, granicaGornaPrzeksztalcona));
            }else{
                JOptionPane.showMessageDialog(null, "Proszę wybrać metodę całkowania.", "Alert", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    public String przeksztalcenieRownania(String wzor, String lib) {
        // wartoscCalki.setText(wpisanaCalka.getText());

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

    wzor = wzor.replace(kat, "("+funkcjeTrygonometryczne(kat)+")");
    System.out.println(wzor);



            }
        }
        return wzor;
    }

    public String funkcjeTrygonometryczne(String kat) {
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
            if (radianItem.isSelected()) {
                str2 = str2.replace("]", ")");
                str2 = str2.replace("[", "(");
            } else if (stopnieItem.isSelected()) {
                if(kat.contains("x")){
                    stopnieItem.setSelected(false);
                    radianItem.setSelected(true);
                    str2 = str2.replace("]", ")");
                    str2 = str2.replace("[", "(");
                }else {
                    str2 = str2.replace("]", "))");
                    str2 = str2.replace("[", "(math.radians(");
                }
            }
        }
        return str2;
    }

    public static Long silnia(int i) {
        if (i < 1) return 1L;
        else return i * silnia(i - 1);
    }

    public void piOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "π");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "π");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "π");
        }
    }

    public void plusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "+");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "+");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "+");
        }
    }

    public void minusOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "-");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "-");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "-");
        }
    }

    public void razyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "*");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "*");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "*");
        }
    }

    public void dzielenieOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "/");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "/");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "/");
        }
    }

    public void nawiastLewyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "(");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "(");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "(");
        }
    }

    public void nawiasPrawyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + ")");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + ")");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + ")");
        }
    }

    public void silniaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "!");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "!");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "!");
        }
    }

    public void eulerOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "e");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "e");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "e");
        }
    }

    public void logarytmOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "log[?][?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "log[?][?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "log[?][?]");
        }
    }

    public void logarytmNaturalnyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "ln[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "ln[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "ln[?]");
        }
    }

    public void pierwiastekKwadratowyOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[2,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[2,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[2,?]");
        }
    }

    public void potegaStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[?]");
        }
    }

    public void pierwiastekStopniaNOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sqrt[?,?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sqrt[?,?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sqrt[?,?]");
        }
    }

    public void potegaKwadratowaOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "^[2]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "^[2]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "^[2]");
        }
    }

    public void sinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "sin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "sin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "sin[?]");
        }
    }

    public void cosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cos[?]");
        }
    }

    public void cotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "cot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "cot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "cot[?]");
        }
    }

    public void atanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "atan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "atan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "atan[?]");
        }
    }

    public void acosOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acos[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acos[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acos[?]");
        }
    }

    public void acotOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "acot[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "acot[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "acot[?]");
        }
    }

    public void asinOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "asin[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "asin[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "asin[?]");
        }
    }

    public void tanOnAction() {
        switch (pressedField) {
            case "GRANICA_GORNA" -> granicaGorna.setText(granicaGorna.getText() + "tan[?]");
            case "GRANICA_DOLNA" -> granicaDolna.setText(granicaDolna.getText() + "tan[?]");
            case "CALKA" -> wpisanaCalka.setText(wpisanaCalka.getText() + "tan[?]");
        }
    }

    public void granicaDolnaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_DOLNA);
    }

    public void calkaPressed() {
        pressedField = String.valueOf(KilknietePole.CALKA);
    }

    public void granicaGornaPressed() {
        pressedField = String.valueOf(KilknietePole.GRANICA_GORNA);
    }

    public void nieskonczonoscOnAction() {
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

    public void menuGlowneItemOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Home.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.UTILITY);
            menuStage.setScene(new Scene(root, 606,614));
            menuStage.setTitle("Kalkulator");
            menuStage.setResizable(false);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void kalkulatorNaukowyOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorNaukowy.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.UTILITY);
            menuStage.setScene(new Scene(root, 606,614));
            menuStage.setTitle("Kalkulator Naukowy");
            menuStage.setResizable(false);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void zamknijOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
}