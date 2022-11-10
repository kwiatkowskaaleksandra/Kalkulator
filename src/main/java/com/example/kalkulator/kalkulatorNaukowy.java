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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

import javax.swing.*;
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
    @FXML
    public ImageView wroc;
    @FXML
    public ListView<String> listViewHistoria;
    @FXML
    public Button xID;
    @FXML
    public Button zeroOnAction;
    @FXML
    public Button jedenOnAction;
    @FXML
    public Button dwaOnAction;
    @FXML
    public Button trzyOnAction;
    @FXML
    public Button czteryOnAction;
    @FXML
    public Button piecOnAction;
    @FXML
    public Button szescOnAction;
    @FXML
    public Button siedemOnAction;
    @FXML
    public Button osiemOnAction;
    @FXML
    public Button dziewiecOnAction;
    double x, y;
    static String przeksztalconeWpisaneDzialanie = null;
    static String wzorFunkcji = null;
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    public static int clickedWpisaneDzialanie = 0;

    public void wynikOnAction() throws PyException {
        WebEngine webEngine = webView.getEngine();
        WebEngine webEngine2 = webView2.getEngine();

        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        if (jednostkaChoiceBox.getValue().equals("Stopnie")) {
            pressedJed = String.valueOf(Jednostka.STOPNIE);
        } else if (jednostkaChoiceBox.getValue().equals("Radiany")) {
            pressedJed = String.valueOf(Jednostka.RADIANY);
        }
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie.getText(), "math.", pressedJed);

        if (!wpisaneDzialanieCheck(wpisaneDzialanie.getText())) {
            if (pochodnaCheckBox.isSelected()) {
                webEngine.loadContent("<p scroll=\"no\">(" + przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie.getText()) + ")'</p>", "text/html");
                webEngine2.loadContent("<p scroll=\"no\">" + przeksztalcenieRownania.zmianaRownania(wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)) + "</p>", "text/html");
            } else {
                webEngine.loadContent("<p scroll=\"no\">" + przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie.getText()) + "</p>", "text/html");
                webEngine2.loadContent("<p scroll=\"no\">" + przeksztalcenieRownania.zmianaRownania(wynikKalkulatora(przeksztalconeWpisaneDzialanie)) + "</p>", "text/html");
            }
            listViewHistoria.getItems().addAll(wpisaneDzialanie.getText());
        }
    }

    public boolean wpisaneDzialanieCheck(String dzialanie) {
        boolean blad = false;
        String komunikat = "";
        try {

            if(dzialanie.isEmpty()){
                komunikat = "Proszę wpisać działanie.";
                blad = true;
                throw new Exception(komunikat);
            }

            if (dzialanie.matches("(.*)\\?(.*)")) {
                komunikat = "Błędnie wpisane działanie. W miejsce '?' należy wpisać wartość liczbową.";
                blad = true;
                throw new Exception(komunikat);
            }

            if (!pochodnaCheckBox.isSelected() && dzialanie.matches("(.*)x(.*)")) {
                komunikat = "Błędnie wpisane działanie. Zmienna 'x' może zostać użyta tylko podczas obliczania pochodnej. \nNależy przejść do menu i zaznaczyć odpowiednią funkcję lub poprawić wpisane działanie.";
                blad = true;
                throw new Exception(komunikat);
            }

            if (jednostkaChoiceBox.getValue().equals("Jednostka") && (dzialanie.matches("(.*)cos(.*)") || dzialanie.matches("(.*)sin(.*)") || dzialanie.matches("(.*)tan(.*)") || dzialanie.matches("(.*)cot(.*)")) && !pochodnaCheckBox.isSelected()) {
                komunikat = "Przy korzystaniu z funkcji trygonometrycznych należy wybrać jednostkę w jakiej został podany kąt: stopnie lub radiany.\nNależy przejść do menu i w zakładce 'Jednostka' wybrać odpowiednią opcję.";
                blad = true;
                throw new Exception(komunikat);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, komunikat, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return blad;
    }
    public void wykres() throws PyException {
        try (Jep jep = new Jep() {}) {
            jep.exec("""
                    import numpy as np
                    import matplotlib.pyplot as plt
                                        
                    a=np.linspace(-4,2,6)
                    b=a*2
                    plt.plot(a,b)
                    plt.show()
                    print(a)
                    del a
                    del b
                    """
            );
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void wykresJava(){

    }

    public String wynikKalkulatoraPochodna(String wzor) {
        try (Jep jep = new Jep() {}) {
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
            JOptionPane.showMessageDialog(null, "Błąd działania. "+e, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wzorFunkcji;
    }

    public String wynikKalkulatora(String wzor) {
        String wynik = "";
        try (Jep jep = new Jep() {}) {
            jep.exec("""
                    import math
                    import sympy
                    c=round(float(""" + wzor + """
                    ),15)
                    """);
            wynik = String.valueOf(jep.getValue("c"));
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            JOptionPane.showMessageDialog(null, "Błąd działania. "+e, "Alert", JOptionPane.WARNING_MESSAGE);
        }
        return wynik;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());
        webView2.getEngine().setUserStyleSheetLocation(Objects.requireNonNull(getClass().getResource("css/webView.css")).toString());

        menuBoczne();
        choiceBoxJednostka();
        cyfryOnAction();
        EventHandler<KeyEvent> handler = new EventHandler<>() {
            private boolean willConsume = false;

            @Override
            public void handle(KeyEvent event) {
                if (willConsume) {
                    event.consume();
                }

                if (event.getCode() == KeyCode.B || event.getCode() == KeyCode.D || event.getCode() == KeyCode.F || event.getCode() == KeyCode.H || event.getCode() == KeyCode.J || event.getCode() == KeyCode.K || event.getCode() == KeyCode.M ||
                        event.getCode() == KeyCode.P || event.getCode() == KeyCode.U || event.getCode() == KeyCode.V || event.getCode() == KeyCode.W || event.getCode() == KeyCode.Y || event.getCode() == KeyCode.Z) {
                    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                        willConsume = true;
                    } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                        willConsume = false;
                    }
                }
                clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
            }
        };
        wpisaneDzialanie.addEventFilter(KeyEvent.ANY, handler);
        EventHandler<MouseEvent> mouseEvent = event -> clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        wpisaneDzialanie.addEventFilter(MouseEvent.ANY,mouseEvent);

    }

    public void menuBoczne() {
        zamknij.setOnMouseClicked(event -> System.exit(0));

        slider.setTranslateX(222);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(222);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuZamknij.setVisible(true);
            });
        });

        menuZamknij.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(222);

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuZamknij.setVisible(false);
            });
        });

        vBoxHistoria.setTranslateX(222);
        historiaButton.setOnMouseClicked(event -> {
            vBoxHistoria.setVisible(true);
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxHistoria);
            transition.setToX(0);
            transition.play();
        });

        wroc.setOnMouseClicked(event -> {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), vBoxHistoria);
            transition.setToX(222);
            transition.play();
            //vBoxMenu.setVisible(true);
            transition.setOnFinished((ActionEvent e) -> vBoxHistoria.setVisible(false));
        });
    }

    public void choiceBoxJednostka() {
        String a = "Jednostka";
        String b = "Radiany";
        String c = "Stopnie";
        jednostkaChoiceBox.getItems().addAll(b, c);
        jednostkaChoiceBox.setValue(a);
    }

    public void cyfryOnAction() {
        zeroOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "0");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        jedenOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "1");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        dwaOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "2");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        trzyOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "3");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        czteryOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "4");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        piecOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "5");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        szescOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "6");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        siedemOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "7");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        osiemOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "8");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });

        dziewiecOnAction.setOnAction(actionEvent -> {
            wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "9");
            clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
        });
    }

    public void pomocOnAction() {
        kalkulatorCalka.rodzaj = "Kalkulator naukowy";
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/pomoc.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Pomoc");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = menuStage.getX() - event.getSceneX();
                y = menuStage.getY() - event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX() + x);
                menuStage.setY(event.getSceneY() + y);
            });

            menuStage.setScene(new Scene(root, 600, 600));
            menuStage.setX(1200);
            menuStage.setY(300);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void kalkulatorCalkaOnAction() {
        Stage stage = (Stage) kalkulatorCalkaButton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/kalkulatorCalka.fxml")));
            Stage menuStage = new Stage();
            menuStage.setTitle("Kalkulator całek");
            menuStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = menuStage.getX() - event.getSceneX();
                y = menuStage.getY() - event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                menuStage.setX(event.getSceneX() + x);
                menuStage.setY(event.getSceneY() + y);
            });

            menuStage.setScene(new Scene(root, 1035, 653));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void historiaOnMouseClicked() {
        listViewHistoria.setOnMouseClicked(event2 -> wpisaneDzialanie.setText(listViewHistoria.getSelectionModel().getSelectedItem()));
    }

    public void piOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "π");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void plusOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "+");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void minusOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "-");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void razyOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "*");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void dzielenieOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "/");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void nawiastLewyOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "(");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void nawiasPrawyOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, ")");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void silniaOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "!");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void eulerOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "e");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void logarytmOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "log[?][?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void logarytmNaturalnyOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "ln[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void pierwiastekKwadratowyOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "sqrt[2,?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void pierwiastekStopniaNOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "sqrt[?,?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void potegaStopniaNOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "^[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void potegaKwadratowaOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "^[2]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void sinOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "sin[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void cosOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "cos[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void tanOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "tan[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void cotOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "cot[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void asinOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "asin[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void atanOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "atan[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void acosOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "acos[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void acotOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "acot[?]");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void procentOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "%");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void kropkaOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, ".");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void przecinekOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, ",");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void xOnAction() {
        wpisaneDzialanie.insertText(clickedWpisaneDzialanie, "x");
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

    public void usunCaloscOnAction() {
        wpisaneDzialanie.clear();
        webView.getEngine().load("");
        webView2.getEngine().load("");
    }

    public void usunOstatnieOnAction() {
        if (wpisaneDzialanie.getLength() > 0) {
            wpisaneDzialanie.setText(wpisaneDzialanie.getText(0, wpisaneDzialanie.getLength() - 1));
        }
    }

    public void xDisable() {
        if (pochodnaCheckBox.isSelected()) {
            xID.setDisable(false);
        } else {
            xID.setDisable(true);
        }
    }

    public void clickedOnAction() {
        clickedWpisaneDzialanie = wpisaneDzialanie.getCaretPosition();
    }

}