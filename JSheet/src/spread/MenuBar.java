import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;

/**
 * Created by Kacper on 2015-03-30.
 */
public class MenuBar extends JMenuBar {

    private ActionListener actionListener;
    private String[] iconNames,itemName,menuName;
    private int[] keyEvents;

    public MenuBar(ActionListener actionListener){

        this.actionListener = actionListener;
        initializeVars();
    }


    private void initializeVars(){

        keyEvents = new int[]{KeyEvent.VK_P, KeyEvent.VK_E, KeyEvent.VK_W, KeyEvent.VK_F, KeyEvent.VK_N, KeyEvent.VK_H};
        menuName = new String[]{"Plik","Edycja","Widok","Funkcje","Narzędzia","Help"};
    }


    private JMenuItem createJMenuItem(String str, Icon ic , KeyStroke key, boolean bool,ActionListener listener){
        JMenuItem jmi = new JMenuItem();
        if(ic != null)
            jmi = new JMenuItem(str,ic);
        else
            jmi = new JMenuItem(str);

        jmi.setAccelerator(key);
        jmi.addActionListener(listener);
        jmi.setEnabled(bool);

        return jmi;
    }


}
