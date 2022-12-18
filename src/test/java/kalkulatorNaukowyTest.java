import com.example.kalkulator.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;

class kalkulatorNaukowyTest {
    kalkulatorNaukowy naukowy = new kalkulatorNaukowy();

    public static String pressedJed = String.valueOf(Jednostka.RADIANY);
    static String przeksztalconeWpisaneDzialanie ;
    boolean pochodna;

    @Test
    void wynikKalOnActionTest1(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        pochodna=false;
        pressedJed = String.valueOf(Jednostka.RADIANY);
        String wpisaneDzialanie="2%-1.3+sqrt[2,6/2]*3^[1.2]-log[3][9]+ln[2]*e+sin[80]+tan[π]+4!+sqrt[3,log[2][3]]+π*2/3+asin[22]";
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikKalOnActionTest2(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String wpisaneDzialanie="2-1.3+sqrt[2,x]-2^[?]";
        pochodna=false;
        pressedJed= String.valueOf(Jednostka.NONE);
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikKalOnActionTest3(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        pochodna=false;
        pressedJed = String.valueOf(Jednostka.STOPNIE);
        String wpisaneDzialanie="2%-1.3+sqrt[2,6/2]*3^[1.2]/2-log[3][9]+ln[2]*e+sin[80]+tan[π]+4!+sqrt[3,log[2][3]]+π*2/3+asin[22]+(2*π/3)";
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(przeksztalconeWpisaneDzialanie));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikKalOnActionTest4(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        pochodna=false;
        pressedJed = String.valueOf(Jednostka.STOPNIE);
        String wpisaneDzialanie="2^[x]";
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(przeksztalconeWpisaneDzialanie));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikKalOnActionTest5(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        pochodna=false;
        pressedJed = "Jednostka";
        String wpisaneDzialanie="cos[40]";
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(przeksztalconeWpisaneDzialanie));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikPochOnActionTest3(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String wpisaneDzialanie="";
        pochodna=true;
        pressedJed= String.valueOf(Jednostka.RADIANY);
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikPochOnActionTest4(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String wpisaneDzialanie="2";
        pochodna=true;
        pressedJed= String.valueOf(Jednostka.RADIANY);
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikPochOnActionTest5(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String wpisaneDzialanie="2*x";
        pochodna=true;
        pressedJed= String.valueOf(Jednostka.RADIANY);
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wynikPochOnActionTest6(){
        PrzeksztalcenieRownania przeksztalcenieRownania = new PrzeksztalcenieRownania();
        String wpisaneDzialanie="xcc";
        pochodna=true;
        pressedJed= String.valueOf(Jednostka.RADIANY);
        przeksztalconeWpisaneDzialanie = przeksztalcenieRownania.przeksztalcenieRownania(wpisaneDzialanie,"sympy.",pressedJed);

        if(naukowy.wpisaneDzialanieCheck(wpisaneDzialanie,pochodna,pressedJed)){
            if(!pochodna && !naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatora(przeksztalconeWpisaneDzialanie)));
            }
            if(pochodna && !naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie).equals("")){
                System.out.println("rownanie: "+przeksztalcenieRownania.zmianaRownania(wpisaneDzialanie));
                System.out.println("wynik: "+przeksztalcenieRownania.przeksztalcenieWyniku(naukowy.wynikKalkulatoraPochodna(przeksztalconeWpisaneDzialanie)));
            }
        }
    }

    @Test
    void wykresTest(){
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie="2*3",wynik="";
        int xMinSpinner=0;
        int xMaxSpinner=5;

        if(!naukowy.wykresCheck(wpisaneDzialanie,xMaxSpinner,xMinSpinner,wynik)){
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
        String wpisaneDzialanie="5^[x]";
        int xMinSpinner=-1;
        int xMaxSpinner=5;
        String wynik ="ln[1/x]";

        if(!naukowy.wykresCheck(wpisaneDzialanie, xMaxSpinner, xMinSpinner,wynik)){
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
        String wpisaneDzialanie="log[2][3]";
        int xMinSpinner=-1;
        int xMaxSpinner=5;
        String wynik ="ln[1/x]";

        if(!naukowy.wykresCheck(wpisaneDzialanie, xMaxSpinner, xMinSpinner,wynik)){
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
    void wykresTest4(){
        wykresFunkcji wykres = new wykresFunkcji();
        String wpisaneDzialanie="", wynik="";
        int xMinSpinner=-1;
        int xMaxSpinner=5;

        if(!naukowy.wykresCheck(wpisaneDzialanie,xMaxSpinner,xMinSpinner,wynik)){
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