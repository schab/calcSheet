import Gui.*;
import Gui.MenuBar;
import SheetComponent.SpreadSheet;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Kacper on 2015-03-30.
 */
public class MainFrame extends JFrame {

    private static final int WIDTH = 1110;
    private static final int HEIGHT = 600;

    private static final String formColumns =   "pref:grow";
    private static final String formRows    =   "40px,10px,pref:grow,15px";

    private static final String tabbedColumns = "pref:grow";
    private static final String tabbedRows    = "10px,pref:grow,10px";

    private static final String jtbColumns =   "10px,24px,5px,24px,5px,24px,5px,24px,5px,10px,5px,140px,5px,50px,5px, " +
                                                "5px,5px,24px,5px,24px,5px,24px,5px,10px,5px,24px,5px,24px,5px,24px,5px," +
                                                "10px,5px,24px,5px,24px,5px," +
                                                "10px,5px,24px,5px,24px,5px,24px,5px,24px,5px,24px,5px,24px,5px,10px,5px,24px,5px,pref:grow,10px";
    private static final String jtbRows    =   "8px,24px,6px,24px,8px";


    private static final String title = "Projekt zespołowy: calcSheet";

    public MainFrame(){
        this.setTitle(title);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                logPanel.setInfo(getClass(), "Wyłączenie aplikacji");
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
        setMinimumSize(myFrameSize);

        setLocation(
                (screenSize.width - myFrameSize.width) / 2,
                (screenSize.height - myFrameSize.height) / 2
        );

        initializeComponents();
        this.setJMenuBar(jMenuBar);
        this.setContentPane(jPanel);

        logPanel.setInfo(getClass(), "Uruchomiono aplikacje " + getTitle());
        this.setVisible(true);
    }


    private void initializeComponents(){

        jPanel = new FormPanel(formColumns,formRows);
        logPanel = new LogPanel();
        centerPanel = new CenterPanel(tabbedColumns,tabbedRows);
        jtb = new ToolBar(jtbColumns,jtbRows);
        jMenuBar = new MenuBar();


        jPanel.addXY(centerPanel, 1, 3);
        jPanel.addXY(logPanel, 1, 4);
        jPanel.addXY(jtb,1,1);

    }


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                initLookAndFeel();
                new MainFrame();
            }});


    }

    public static void initLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );
        }
        catch(UnsupportedLookAndFeelException e)
        {

        }
        catch(ClassNotFoundException e)
        {

        }
        catch(InstantiationException e)
        {

        }
        catch(IllegalAccessException e)
        {

        }
    }



    private LogPanel logPanel;
    private FormPanel jPanel;
    private JToolBar jtb;
    private JMenuBar jMenuBar;
    private CenterPanel centerPanel;


    private SpreadSheet spreadSheet;

}
