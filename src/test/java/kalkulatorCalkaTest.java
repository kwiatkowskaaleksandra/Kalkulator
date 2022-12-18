import com.example.kalkulator.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Test;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;


class kalkulatorCalkaTest extends MetodyCalkowania {
    public String metoda;
    public static String pressedJed = String.valueOf(Jednostka.STOPNIE);
    static String granicaGornaPrzeksztalcona = null;
    static String granicaDolnaPrzeksztalcona = null;

    kalkulatorCalka calka = new kalkulatorCalka();
    String ukrytyWzorCalki;

    @Test
    void test1(){
        String wpisanaCalka="sin[x]+cos[π]";
        String grDolna = "1";
        String grGorna = "12";
        String liczbaPodprzedzialow = "4";
        String  jednostka ="Stopnie";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test2(){
        String wpisanaCalka="2";
        String grDolna = "m";
        String grGorna = "w";
        String liczbaPodprzedzialow = "12.2";
        String  jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test3(){
        String wpisanaCalka="";
        String grDolna = "";
        String grGorna = "";
        String liczbaPodprzedzialow = "";
        String  jednostka ="";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test4(){
        String wpisanaCalka="2";
        String grDolna = "-∞";
        String grGorna = "+∞";
        String liczbaPodprzedzialow = "-1";
        String  jednostka ="";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test5(){
        String wpisanaCalka="2*cos[?]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test6(){
        String wpisanaCalka="2*cos[?]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Jednostka";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test7(){
        String wpisanaCalka="rownanie";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test8(){
        String wpisanaCalka="2*cos[x]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "podprzedzialy";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test9(){
        String wpisanaCalka="2*cos[x]";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test10(){
        String wpisanaCalka="";
        String grDolna = "-12";
        String grGorna = "2";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test11(){
        String wpisanaCalka="2*cos[x]";
        String grDolna = "∞+cos[x]";
        String grGorna = "-4";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    @Test
    void test12(){
        String wpisanaCalka="2*cos[x]";
        String grDolna = "3";
        String grGorna = "-sin[3]*∞";
        String liczbaPodprzedzialow = "2";
        String jednostka ="Radiany";

        wynikCalkaMetodaProstokatowNiedomiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaProstokatowNadmiarTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaTrapezowTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaSimpsonaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
        wynikCalkaMetodaAnalitycznaTest(wpisanaCalka,grDolna,grGorna,liczbaPodprzedzialow,jednostka);
    }

    void wynikCalkaMetodaProstokatowNiedomiarTest(String wpisanaCalkaWzor,String granicaDolna, String granicaGorna,String lPodprzedzialow, String jednostka){
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda prostokątów z niedomiarem";

        if(calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)){
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna=(String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna=(String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: "+przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: "+przeksztalcenie.zmianaRownania(granicaDolna));

            if(metoda.equals("Metoda prostokątów z niedomiarem")){
                if(calka.liczbaPodprzedzialowCheck(lPodprzedzialow)){
                    wynikPrzeksztalcenie(przeksztalcenie,granicaDolna,granicaGorna,wpisanaCalkaWzor);
                    if(!metodaProstokatowZNiedomiarem(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow).equals("")){
                        System.out.println("wzor: "+przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        System.out.println("wynik calki: "+metodaProstokatowZNiedomiarem(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow));
                    }
                }
            }
        }
    }


    void wynikCalkaMetodaProstokatowNadmiarTest(String wpisanaCalkaWzor,String granicaDolna, String granicaGorna,String lPodprzedzialow, String jednostka){
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda prostokątów z nadmiarem";

        if(calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)){
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna=(String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna=(String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: "+przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: "+przeksztalcenie.zmianaRownania(granicaDolna));

            if(metoda.equals("Metoda prostokątów z nadmiarem")){
                if(calka.liczbaPodprzedzialowCheck(lPodprzedzialow)){
                    wynikPrzeksztalcenie(przeksztalcenie,granicaDolna,granicaGorna,wpisanaCalkaWzor);
                    if(!metodaProstokatowZNadmiarem(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow).equals("")){
                        System.out.println("wzor: "+przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        System.out.println("wynik calki: "+metodaProstokatowZNadmiarem(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow));
                    }
                }
            }
        }
    }


    void wynikCalkaMetodaTrapezowTest(String wpisanaCalkaWzor,String granicaDolna, String granicaGorna,String lPodprzedzialow, String jednostka){
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda trapezów";

        if(calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)){
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna=(String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna=(String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: "+przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: "+przeksztalcenie.zmianaRownania(granicaDolna));

            if(metoda.equals("Metoda trapezów")){
                if(calka.liczbaPodprzedzialowCheck(lPodprzedzialow)){
                    wynikPrzeksztalcenie(przeksztalcenie,granicaDolna,granicaGorna,wpisanaCalkaWzor);
                    if(!metodaTrapezow(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow).equals("")){
                        System.out.println("wzor: "+przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        System.out.println("wynik calki: "+metodaTrapezow(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow));
                    }
                }
            }
        }
    }


    void wynikCalkaMetodaSimpsonaTest(String wpisanaCalkaWzor,String granicaDolna, String granicaGorna,String lPodprzedzialow, String jednostka){
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda Simpsona";

        if(calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)){
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna=(String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna=(String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: "+przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: "+przeksztalcenie.zmianaRownania(granicaDolna));

            if(metoda.equals("Metoda Simpsona")){
                if(calka.liczbaPodprzedzialowCheck(lPodprzedzialow)){
                    wynikPrzeksztalcenie(przeksztalcenie,granicaDolna,granicaGorna,wpisanaCalkaWzor);
                    if(!metodaSimpsona(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow).equals("")){
                        System.out.println("wzor: "+przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        System.out.println("wynik calki: "+metodaSimpsona(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona,lPodprzedzialow));
                    }
                }
            }
        }
    }


    void wynikCalkaMetodaAnalitycznaTest(String wpisanaCalkaWzor,String granicaDolna, String granicaGorna,String lPodprzedzialow, String jednostka){
        PrzeksztalcenieRownania przeksztalcenie = new PrzeksztalcenieRownania();
        metoda = "Metoda analityczna";

        if(calka.wpisanaCalkaGraniceCheck(granicaDolna, granicaGorna, wpisanaCalkaWzor, metoda, jednostka)){
            if (granicaDolna.matches("[0-9]+")) {
                granicaDolna=(String.valueOf(Float.valueOf(granicaDolna)));
            }
            if (granicaGorna.matches("[0-9]+")) {
                granicaGorna=(String.valueOf(Float.valueOf(granicaGorna)));
            }

            if (jednostka.equals("Stopnie")) {
                pressedJed = String.valueOf(Jednostka.STOPNIE);
            } else if (jednostka.equals("Radiany")) {
                pressedJed = String.valueOf(Jednostka.RADIANY);
            }

            System.out.println("granica gorna wzor: "+przeksztalcenie.zmianaRownania(granicaGorna));
            System.out.println("granica dolna wzor: "+przeksztalcenie.zmianaRownania(granicaDolna));

            if(metoda.equals("Metoda analityczna")){
                wynikPrzeksztalcenie(przeksztalcenie,granicaDolna,granicaGorna,wpisanaCalkaWzor);
                    if(!metodaAnalityczna(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona).equals("")){
                        System.out.println("wzor: "+przeksztalcenie.przeksztalcenieWyniku(wzorCalki));
                        System.out.println("wynik calki: "+metodaAnalityczna(ukrytyWzorCalki,granicaDolnaPrzeksztalcona,granicaGornaPrzeksztalcona));
                    }
            }
        }
    }

    public String wynikPrzeksztalcenie(PrzeksztalcenieRownania przeksztalcenie,String grDolna, String grGorna, String wpisanaCalka) {
        String jednostka="";
        granicaDolnaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(grDolna, "sympy.", pressedJed);
        granicaGornaPrzeksztalcona = przeksztalcenie.przeksztalcenieRownania(grGorna, "sympy.", pressedJed);
        ukrytyWzorCalki = przeksztalcenie.przeksztalcenieRownania(wpisanaCalka, "sympy.", pressedJed);
        if (Objects.equals(przeksztalcenie.pressedJednostka, "RADIANY")) {
           jednostka="Radiany";
        } else if (Objects.equals(przeksztalcenie.pressedJednostka, "STOPNIE")) {
            jednostka="Stopnie";
        }
        return jednostka;
    }

    @Test
    void wykresTest(){
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie="2*x";
        int xMinSpinner=1;
        int xMaxSpinner=5;

        if(calka.wykresCheck(wpisaneDzialanie,xMaxSpinner,xMinSpinner)){
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
    void wykresTest2(){
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie="x*ln[1]";
        int xMinSpinner=-1;
        int xMaxSpinner=5;

        if(calka.wykresCheck(wpisaneDzialanie,xMaxSpinner,xMinSpinner)){
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
    void wykresTest3(){
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie="";
        int xMinSpinner=-1;
        int xMaxSpinner=5;

        if(calka.wykresCheck(wpisaneDzialanie,xMaxSpinner,xMinSpinner)){
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