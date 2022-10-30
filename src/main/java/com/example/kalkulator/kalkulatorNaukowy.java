package com.example.kalkulator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jep.Jep;
import org.python.core.PyException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class kalkulatorNaukowy implements Initializable {

    @FXML
    public TextField wpisaneDzialanie;
    @FXML
    public WebView webView;
    @FXML
    public WebView webView2;
    static String przeksztalconeWpisaneDzialanie =null;
    static String wzorFunkcji =null;
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    @FXML
    public Label menu;
    @FXML
    public Label menuZamknij;
    @FXML
    public ImageView zamknij;
    @FXML
    public AnchorPane slider;
    @FXML
    public Button kalkulatorCalkaButton;
    @FXML
    public CheckBox pochodnaCheckBox;
    @FXML
    public ChoiceBox<String> jednostkaChoiceBox;
    @FXML
    public Button pomocButton;
    @FXML
    public Button historiaButton;
    @FXML
    public VBox vBoxHistoria;
    @FXML
    public VBox vBoxMenu;
    public ImageView wroc;
    public ListView<String> listViewHistoria;
    double x,y;

    public void wynikOnAction() throws PyException {
        WebEngine webEngine = webView.getEngine();
        WebEngine webEngine2 = webView2.getEngine();

        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        if(jednostkaChoiceBox.getValue().equals("Stopnie")){
            pressedJed= String.valueOf(Jednostka.STOPNIE);
        }else if(jednostkaChoiceBox.getValue().equals("Radiany")){
            pressedJed=String.valueOf(Jednostka.RADIANY);
        }
        przeksztalconeWpisaneDzialanie=przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie.getText(),"math.",pressedJed);


        if(pochodnaCheckBox.isSelected()){
            webEngine.loadContent("<p>("+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie.getText())+")'</p>","text/html");
            webEngine2.loadContent("<p>"+przeksztalcenieRownania.zmianaRownania(wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie))+"</p>", "text/html");
        }else{
            webEngine.loadContent("<p>"+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie.getText())+"</p>","text/html");
            webEngine2.loadContent("<p>"+przeksztalcenieRownania.zmianaRownania(wynikKalkulatora(przeksztalconeWpisaneDzialanie))+"</p>", "text/html");
        }
        listViewHistoria.getItems().addAll(wpisaneDzialanie.getText());

    }

    public void wykres(){
        WebEngine webEngine2 = webView2.getEngine();
        try(Jep jep = new Jep() {} ){
            jep.exec("""
                    from numpy import *
                    import matplotlib.pyplot as plt
                    
                    a=linspace(-4,2,6)
                    b=a*2
                    plt.plot(a,b)
                    plt.show()
                    """);

            // wzorFunkcji=String.valueOf(jep.getValue("c"));
            //System.out.println(wzorFunkcji);
            //webEngine2.loadContent("<p>"+przeksztalcenieRownania.zmianaRownania(wzorFunkcji)+"</p>", "text/html");


        }catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }
    public String wynikKalkulatoraPochodna(String wzor) {
        String wynik = "";
        try (Jep jep = new Jep() {
        }) {
            jep.exec("""
                    import math
                    import sympy
                    x=sympy.Symbol('x')
                    c=(""" + wzor + """
                    ).diff(x)
                    """);
            wzorFunkcji = String.valueOf(jep.getValue("c"));
            System.out.println(wzorFunkcji);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return wzorFunkcji;
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

    public void kalkulatorCalkaOnAction() {
        Stage stage = (Stage) kalkulatorCalkaButton.getScene().getWindow();
        stage.close();

        try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorCalka.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator całek");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x=menuStage.getX()-event.getSceneX();
                y=menuStage.getY()-event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX()+x);
                menuStage.setY(event.getSceneY()+y);
            });

            menuStage.setScene(new Scene(root, 972, 600));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());

        menuBoczne();
        choiceBoxJednostka();


    }

    public void choiceBoxJednostka(){
        String a ="Jednostka";
        String b ="Radiany";
        String c ="Stopnie";
        jednostkaChoiceBox.getItems().addAll(b,c);
        jednostkaChoiceBox.setValue(a);
    }

    public void menuBoczne(){
        zamknij.setOnMouseClicked(event-> System.exit(0));

        slider.setTranslateX(222);
        menu.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(222);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuZamknij.setVisible(true);
            });
        });

        menuZamknij.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(222);

            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuZamknij.setVisible(false);
            });
        });

        vBoxHistoria.setTranslateX(222);
        historiaButton.setOnMouseClicked(event->{
            vBoxHistoria.setVisible(true);
            TranslateTransition transition=new TranslateTransition(Duration.seconds(0.4),vBoxHistoria);
            transition.setToX(0);
            transition.play();
        });

        wroc.setOnMouseClicked(event->{
            TranslateTransition transition=new TranslateTransition(Duration.seconds(0.4),vBoxHistoria);
            transition.setToX(222);
            transition.play();
            //vBoxMenu.setVisible(true);
            transition.setOnFinished((ActionEvent e)-> vBoxHistoria.setVisible(false));
        });
    }


    public void pomocOnAction() {
        kalkulatorCalka.rodzaj="Kalkulator naukowy";
     try{
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/pomoc.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Pomoc");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x=menuStage.getX()-event.getSceneX();
                y=menuStage.getY()-event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX()+x);
                menuStage.setY(event.getSceneY()+y);
            });

            menuStage.setScene(new Scene(root, 500, 600));
            menuStage.setX(1400);
            menuStage.setY(300);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void historiaOnMouseClicked() {
        listViewHistoria.setOnMouseClicked(event2-> wpisaneDzialanie.setText( listViewHistoria.getSelectionModel().getSelectedItem()));
    }
}