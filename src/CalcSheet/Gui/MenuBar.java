package CalcSheet.Gui;

import CalcSheet.Functions.Colors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Kacper on 2015-03-30.
 */
public class MenuBar extends JMenuBar implements ActionListener {


    /**
     * Klasa implementujaca menu
     */

    private JMenu[] jMenus;
    private JMenuItem[][] menuItems;


    /**
     * Konstruktor bezparametrowy inicjalizujacy
     * komponenty oraz ustawiajacy elementy wizualne ( tlo, czcionka)
     */

    public MenuBar() {

        initializeVars();

        this.setBackground(Colors.MyGray.color());
        this.setForeground(Colors.LightBlue.color(0.7f).brighter());

    }

    /**
     * Metoda inicjalizujaca komponenty
     *
     */

    private void initializeVars() {

        jMenus = new JMenu[menuNames.length];
        menuItems = new JMenuItem[menuNames.length][itemNames.length];

        for (int i = 0; i < menuNames.length; i++) {
            jMenus[i] = createJMenu(menuNames[i], keyEvents[i], true);
            for (int j = 0; j < itemNames[i].length; j++) {
                menuItems[i][j] = createJMenuItem(itemNames[i][j], null, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK), true);
                jMenus[i].add(menuItems[i][j]);
                jMenus[i].setForeground(Colors.LightBlue.color(0.7f).brighter());
            }

            this.add(jMenus[i]);
        }
    }


    /**
     * Metoda tworzy obiekt typu JMenuItem
     *
     * @param str  nazwa obiektu
     * @param ic   ikona do wyświetlenia wraz z nazwą
     * @param key  klawisz dostępu do danego obiektu
     * @param bool aktywacja danego podmenu
     * @return zwraca utworzony obiektu
     */

    private JMenuItem createJMenuItem(String str, Icon ic, KeyStroke key, boolean bool) {
        JMenuItem jmi = new JMenuItem();
        if (ic != null)
            jmi = new JMenuItem(str, ic);
        else
            jmi = new JMenuItem(str);

        jmi.setAccelerator(key);
        jmi.addActionListener(this);
        jmi.setEnabled(bool);

        return jmi;
    }


    /**
     * Metoda tworząca obiekt typu JMenu
     *
     * @param str      zmienna określająca nazwę
     * @param keyEvent zmienna określająca klawisz skrótu
     * @param bool     zmienna logiczna określająca czy menu jest aktywne
     * @return zwraca utworzony obiekt typu JMenu
     */

    public JMenu createJMenu(String str, int keyEvent, boolean bool) {
        JMenu jMenu = new JMenu(str);
        jMenu.setMnemonic(keyEvent);
        jMenu.setEnabled(bool);
        return jMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LogPanel.setInfo(getClass(), "wybrano opcje " + e.getActionCommand());
    }


    public int[] keyEvents = new int[]{KeyEvent.VK_P, KeyEvent.VK_E, KeyEvent.VK_W, KeyEvent.VK_F, KeyEvent.VK_N, KeyEvent.VK_H};
    public String[] menuNames = new String[]{"Plik", "Edycja", "Widok", "Funkcje", "Narzędzia", "Pomoc"};
    public String[][] itemNames = new String[][]
            {
                    {"Empty", "Empty", "Empty"},
                    {"Empty", "Empty", "Empty", "Empty"},
                    {"Empty", "Empty", "Empty"},
                    {"Empty", "Empty", "Empty", "Empty", "Empty"},
                    {"Empty", "Empty"},
                    {"Empty", "W Empty", "Empty", "Empty"}
            };


}
