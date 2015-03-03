import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Hubert on 3/3/2015.
 */
public class Hello {

    private static final Logger log = Logger.getLogger( Hello.class.getName() );
    private static String helloMsg = "Hello";

    public static void main ( String args[] ){


        log.log(Level.OFF, helloMsg);

        JFrame jFrame = new JFrame();
        jFrame.setSize(200,200);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);



    }
}
