package Gui;

import Functions.Colors;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Kacper on 2015-03-30.
 */

public class LogPanel extends JPanel{


    /**
     * Klasa odpowiadajaca za panel z logiem aplikacji
     *
     */


        final static Logger logger = Logger.getLogger("logger");
        private static JLabel statusField,infoField;
        private JLabel statusLabel,infoLabel;



    /**
     *  Konstruktor bezparametrowy inicjalizujacy komponenty
     *
     */

        public LogPanel(){
            try {
                initializeComponents();
                createLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    /**
     * Metoda inicjalizujaca komponenty
     *
     */

        private void initializeComponents(){
            this.setBackground(Colors.MyGray.color());
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            statusLabel = new JLabel("Status:");
            infoLabel   = new JLabel("Stan:");
            infoField = new JLabel("Start Aplikacji");
            statusField = new JLabel("ON");

            infoField.setMaximumSize(new Dimension(500,11));
            statusField.setMaximumSize(new Dimension(200,11));
            infoField.setMinimumSize(new Dimension(200, 11));
            statusField.setMinimumSize(new Dimension(100,11));

            statusLabel.setForeground(Colors.LightBlue.color(0.7f).brighter());
            infoLabel.setForeground(Colors.LightBlue.color(0.7f).brighter());
            infoField.setForeground(Colors.LightBlue.color(0.7f).brighter());
            statusField.setForeground(Colors.LightBlue.color(0.7f).brighter());



//            infoField.putClientProperty("JComponent.sizeVariant", "small");


//            statusField.putClientProperty("JComponent.sizeVariant", "small");

            infoField.setFont(new Font("Calibri", Font.PLAIN, 11));
            statusField.setFont(new Font("Calibri", Font.PLAIN,11));
            statusLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
            infoLabel.setFont(new Font("Calibri", Font.PLAIN, 11));


            infoField.setBackground(new Color(255, 255, 255));
            statusField.setBackground(new Color(255, 255, 255));

            this.add(Box.createHorizontalStrut(20));
            this.add(infoLabel);
            this.add(infoField);
            this.add(Box.createHorizontalStrut(10));

            this.add(statusLabel);
            this.add(statusField);
            this.add(Box.createHorizontalStrut(10));

        }


    /**
     * Metoda obslugujaca loger i strukture xml
     *
     */

        private void createLogger() throws IOException{

            DOMConfigurator.configure("config.xml");
            PatternLayout patternLayout = new PatternLayout("%-5p : %d%m%n");

            ConsoleAppender capp = new ConsoleAppender(new SimpleLayout());
            FileAppender fapp = new FileAppender(patternLayout,"projektLog.log");

            logger.addAppender(fapp);
            logger.setLevel(Level.INFO);
        }


    /**
     *  Metoda ustalajaca dany stan
     *  @param t
     *  @param str
     */

        public static void setInfo(Class t , String str){
            String string = t.toString() + ":: " + str;
            try {
                logger.info(": " + string);
                setInfoField(string);
                setStatusField("Wykonano");
            }catch(Exception e){
                logger.setLevel(Level.ERROR);
                logger.info(e.getMessage());
                setStatusField("B³¹d");
            }
            }

    /**
     * Ustawianie pola "Info"
     * @param str
     */

        public static void setInfoField(String str){
            infoField.setText(str);
        }

    /**
     * Ustawienie pola "Status"
     *@param str
     */

        public static void setStatusField(String str){
            statusField.setText(str);
        }

}
