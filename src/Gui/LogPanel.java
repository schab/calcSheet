package Gui;

import Functions.Colors;
import Functions.Fonts;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Kacper on 2015-03-30.
 */

public class LogPanel extends JPanel{


        final static Logger logger = Logger.getLogger("logger");
        private static JTextField statusField,infoField;
        private JLabel statusLabel,infoLabel;

        public LogPanel(){
            try {
                initializeComponents();
                createLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private void initializeComponents(){
            this.setBackground(Colors.MyGray.color());
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            statusLabel = new JLabel("Status:");
            infoLabel   = new JLabel("Stan:");


            statusLabel.setFont(Fonts.Calibri.font());
            infoLabel.setFont(Fonts.Calibri.font());

            statusLabel.setForeground(Colors.LightBlue.color(0.7f).brighter());
            infoLabel.setForeground(Colors.LightBlue.color(0.7f).brighter());

            infoField = new JTextField("Start Aplikacji");
            infoField.setMaximumSize(new Dimension(500, 20));
            infoField.setMinimumSize(new Dimension(200, 20));

            infoField.putClientProperty("JComponent.sizeVariant", "small");

            statusField = new JTextField("ON");
            statusField.setMaximumSize(new Dimension(100, 20));

            statusField.putClientProperty("JComponent.sizeVariant", "small");

            infoField.setEditable(false);
            statusField.setEditable(false);

            infoField.setFont(new Font("Calibri", Font.PLAIN, 13));
            statusField.setFont(new Font("Calibri", Font.PLAIN, 13));



            infoField.setBackground(new Color(255, 255, 255));
            statusField.setBackground(new Color(255, 255, 255));




            this.add(infoLabel);
            this.add(Box.createHorizontalStrut(10));
            this.add(infoField);
            this.add(Box.createHorizontalStrut(20));

            this.add(statusLabel);
            this.add(Box.createHorizontalStrut(10));
            this.add(statusField);
            this.add(Box.createHorizontalStrut(20));

            infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
            statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 0));
        }


        private void createLogger() throws IOException{

            DOMConfigurator.configure("config.xml");
            PatternLayout patternLayout = new PatternLayout("%-5p : %d%m%n");

            ConsoleAppender capp = new ConsoleAppender(new SimpleLayout());
            FileAppender fapp = new FileAppender(patternLayout,"projektLog.log");

            logger.addAppender(fapp);
            logger.setLevel(Level.INFO);
        }


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

        public static void setInfoField(String str){
            infoField.setText(str);
        }

        public static void setStatusField(String str){
            statusField.setText(str);
        }
    
}
