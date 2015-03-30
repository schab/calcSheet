import javax.swing.*;
import java.awt.*;

/**
 * Created by Kacper on 2015-03-30.
 */
public class mainTest {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        JPanel panel;
        LogPanel pane = new LogPanel();
        frame.setLayout(new BorderLayout());

        panel = (JPanel) frame.getContentPane();
        panel.add(pane);
        frame.setContentPane(panel);


        frame.setVisible(true);
    }
}
