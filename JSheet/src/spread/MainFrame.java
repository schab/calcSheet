import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Kacper on 2015-03-30.
 */
public class MainFrame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public MainFrame(){
        this.setTitle("Projekt zespołowy: calcSheet");


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
//                CenterPanel.logger.setInfo("Wyłączenie aplikacji");
                dispose();
                System.exit(0);
            }
        });

        //Przypisanie rozmiaru preferowanego ramki i pobranie rozdzielczości pulpitu
        Dimension myFrameSize = new Dimension(WIDTH,HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Ustalenie czy rozdzielczość ramki(w,h) jest większa od rozdzielczości pulpitu
        if(myFrameSize.width > screenSize.width) myFrameSize.width = screenSize.width;
        if(myFrameSize.height > screenSize.height) myFrameSize.height = screenSize.height;

        // Określenie rozmiaru i umieszczenie go na środku ekranu
        setSize(myFrameSize);
        setLocation(
                (screenSize.width-myFrameSize.width)/2,
                (screenSize.height - myFrameSize.height)/2
        );


        this.setVisible(true);
    }

    private JPanel createJPanel(){
        JPanel jPanel = null;
        return jPanel;
    }
//    private JToolBar createJToolBar(){
//    }

//    private void initializeComponents(){
//    }

}
