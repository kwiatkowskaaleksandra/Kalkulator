package com.example.kalkulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jep.Jep;
import org.python.core.PyException;
import org.python.indexer.ast.NSubscript;
import org.python.util.PythonInterpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kalkulatorNaukowy implements Initializable {

    @FXML
    public RadioMenuItem radianItem;
    @FXML
    public RadioMenuItem stopnieItem;
    @FXML
    public TextField wpisaneDzialanie;
    @FXML
    public TextField przeksztalconeDzialanie;
    @FXML
    public TextField wynikDzialania;
    @FXML
    public MenuBar menuBar;
    @FXML
    public WebView webView;

    static String przeksztalconeWpisaneDzialanie =null;
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);

    public void wynikOnAction() throws PyException {
        WebEngine webEngine = webView.getEngine();
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        if(stopnieItem.isSelected()){
            pressedJed= String.valueOf(Jednostka.STOPNIE);
        }else if(radianItem.isSelected()){
            pressedJed=String.valueOf(Jednostka.RADIANY);
        }
        webEngine.loadContent("<p>"+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie.getText())+"</p>","text/html");
        przeksztalconeWpisaneDzialanie=przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie.getText(),"math.",pressedJed);
        wynikDzialania.setText(wynikKalkulatora(przeksztalconeWpisaneDzialanie));


    }

    public String wynikKalkulatora(String wzor){
        String wynik="";
        try(Jep jep = new Jep() {} ){
            jep.exec("""
                    import math
                    import sympy
                    c=float("""+wzor+"""
                    )
                    """);
            wynik=String.valueOf(jep.getValue("c"));
        }catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return  wynik;
    }

    public void piOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"π");
    }

    public void plusOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"+");
    }

    public void minusOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"-");
    }

    public void razyOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"*");
    }

    public void dzielenieOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"/");
    }

    public void nawiastLewyOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"(");
    }

    public void nawiasPrawyOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+")");
    }

    public void silniaOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"!");
    }

    public void eulerOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"e");
    }

    public void logarytmOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"log[?][?]");
    }

    public void logarytmNaturalnyOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"ln[?]");
    }

    public void pierwiastekKwadratowyOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"sqrt[2,?]");
    }

    public void pierwiastekStopniaNOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"sqrt[?,?]");
    }

    public void potegaStopniaNOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"^[?]");
    }

    public void potegaKwadratowaOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"^[2]");
    }

    public void sinOnAction(){
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"sin[?]");
    }

    public void cosOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"cos[?]");
    }

    public void tanOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"tan[?]");
    }

    public void cotOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"cot[?]");
    }

    public void asinOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"asin[?]");
    }

    public void atanOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"atan[?]");
    }

    public void acosOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"acos[?]");
    }

    public void acotOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"acot[?]");
    }

    public void nieskonczonoscOnAction() {
        wpisaneDzialanie.setText(wpisaneDzialanie.getText()+"∞");
    }

    public void menuGlowneOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Home.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 606,614));
            menuStage.setTitle("Kalkulator");
            menuStage.setResizable(false);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void kalkulatorCalkaOnAction() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorCalka.fxml")));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 930, 600));
            menuStage.setTitle("Kalkulator całek");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
    }
}