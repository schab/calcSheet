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
            this.setBackground(new Color(242,242,220));
            this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

            statusLabel = new JLabel("Status:");
            infoLabel   = new JLabel("Stan:");

            statusLabel.setFont(new Font("Calibri",Font.BOLD, 14));
            infoLabel.setFont(new Font("Calibri",Font.BOLD,14));

            infoField = new JTextField("Start Aplikacji");
            infoField.setMinimumSize(new Dimension(200,20));
            infoField.putClientProperty("JComponent.sizeVariant", "small");

            statusField = new JTextField("ON");
            statusField.setMinimumSize(new Dimension(100,20));
            statusField.putClientProperty("JComponent.sizeVariant", "small");

            infoField.setEditable(false);
            statusField.setEditable(false);

            infoField.setFont(new Font("Calibri",Font.PLAIN,13));
            statusField.setFont(new Font("Calibri",Font.PLAIN,13));

            infoField.setBackground(new Color(255,255,255));
            statusField.setBackground(new Color(255,255,255));


            this.add(infoLabel);
            this.add(Box.createHorizontalStrut(10));
            this.add(infoField);
            this.add(Box.createHorizontalStrut(20));

            this.add(statusLabel);
            this.add(Box.createHorizontalStrut(10));
            this.add(statusField);
            this.add(Box.createHorizontalStrut(20));
        }


        private void createLogger() throws IOException{

            DOMConfigurator.configure("config.xml");
            PatternLayout patternLayout = new PatternLayout("%-5p [%t]: %d%m%n");

            @SuppressWarnings("unused")
            ConsoleAppender capp = new ConsoleAppender(new SimpleLayout());
            FileAppender fapp = new FileAppender(patternLayout,"logPorjekt.log");

            logger.addAppender(fapp);
            logger.setLevel(Level.INFO);
            logger.info(": Uruchomiono aplikacje");
        }


        public void setInfo(String str){
            logger.info(": "+ str);
        }
    
}
