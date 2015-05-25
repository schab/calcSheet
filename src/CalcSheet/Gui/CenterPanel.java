package CalcSheet.Gui;

import CalcSheet.Functions.Colors;
import CalcSheet.Functions.Fonts;
import CalcSheet.SheetComponent.SpreadSheet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Kacper on 2015-04-10.
 * Klasa będąca kontenerem dla komponentów projektu
 */

public class CenterPanel extends FormPanel implements Printable{

    private JTabbedPane jTabbedPane;            // Panel zakładek arkusza
    private JPanel newTabContent;               // Nowa zakładka komponentu spreadsheet
    private ArrayList<SpreadSheet> sheetTable;  // Lista przechowująca dany utworzony arkusz
    private int tabIndex;                       // Index zakładki
    private MultiOptionPane multiOptionPane;    // Opcojnalny dialog
    private int location;
    private FormulaBar formulaBar;              // Pole formuły odpowiadające za wprowadzanie obliczeń
    private PopMenu popupMenu;                  // Menu kontekstowe dla PPM


    /**
     * Konstruktor głównego panelu tworzący FormLayout
     * @param col       - ilośc kolumn Formy
     * @param row       - ilość wierszy Formy
     * @param formulaBar - Komponent pola formuły
     */
    public CenterPanel(String col, String row, FormulaBar formulaBar) {
        super(col, row);
        this.location = JTabbedPane.BOTTOM;
        this.formulaBar = formulaBar;
        initializeComponents();
        initializeTabbedPane();
        setupTabTraversalKeys();
        this.addXY(jTabbedPane, 1, 2);
    }


    /**
     * Inicjalizacja komponentów panelu
     */

    private void initializeComponents(){
        sheetTable = new ArrayList<SpreadSheet>();
        jTabbedPane = new JTabbedPane(location);
        multiOptionPane = new MultiOptionPane();
    }


    private void initializeTabbedPane(){
        SpreadSheet spreadSheet = new SpreadSheet(20, 20,formulaBar);
        popupMenu = new PopMenu();
        sheetTable.add(spreadSheet);
        tabIndex = 1;
        jTabbedPane.setComponentPopupMenu(popupMenu);
        jTabbedPane.addTab("Arkusz", spreadSheet.getScrollPane());

        jTabbedPane.setTabComponentAt(0, new TabComponent());
        // when a tab is selected, stop any active tab title editing
        jTabbedPane.getModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                stopTabNameEditing();
            }
        });

        newTabContent = new JPanel();
        newTabContent.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                addTab();
            }
        });
        jTabbedPane.addTab("+", newTabContent);

        jTabbedPane.setFont(Fonts.CalibriSmall.font());
        jTabbedPane.setOpaque(false);
    }

    /**
     * Metoda addTab odpowiadająca za dodanie nowej zakładki
     */
    public void addTab() {
        multiOptionPane.showDialog();
        int col = multiOptionPane.getColumns() , row = multiOptionPane.getRows();

        if(col>0 && row>0)
        {
            stopTabNameEditing();

            for(int i = 0; i < jTabbedPane.getTabCount()-1; i++) {
                SpreadSheet table = sheetTable.get(i);
                if(table.isEditing())
                    table.getCellEditor().stopCellEditing();
                table.clearSelection();
            }

            // set the tab's component to a JTable first, to allow newTabContent to be added to the new "new tab" tab
            int newGroupIndex = jTabbedPane.getTabCount()-1;
            SpreadSheet newSheet = new SpreadSheet(row,col,formulaBar);
            sheetTable.add(newSheet);
            jTabbedPane.setComponentAt(newGroupIndex, newSheet.getScrollPane());
            jTabbedPane.addTab("+", newTabContent);
            TabComponent tabComp = new TabComponent();
            jTabbedPane.setTabComponentAt(newGroupIndex, tabComp);
            tabComp.startNameEditing();

            LogPanel.setInfo(getClass(), "Dodano nowy arkusz");
        }
    }

    /**
     * Metoda RemoveTab odpowiadająca za usunięcie wybranej zakładki
     */

    public void RemoveTab(){
        if(jTabbedPane.getTabCount() > 1) {
            int index = jTabbedPane.getSelectedIndex();
            jTabbedPane.setSelectedIndex(index - 1);
            jTabbedPane.removeTabAt(index);
            LogPanel.setInfo(getClass(), "Arkusz został usunięty");

        }else{
            multiOptionPane.showErrorPane("Nie znaleziono zakładki do usunięcia!", "Błąd!");
            LogPanel.setInfo(getClass(), "Nie znaleziono zakładki do usunięcia!");

        }


    }

    /**
     * Metoda SaveTable odpowiadająca za zapisanie danych arkusza do pliku
     */

    public void SaveTable(){

        int index = jTabbedPane.getSelectedIndex();
        String data = "Tytuł arkusza:["+jTabbedPane.getTitleAt(index)+"]\n\n"+ sheetTable.get(index).GetCellsData();

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(""));
        int retrival = chooser.showSaveDialog(null);

        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile());
                fw.write(data.toString());
                fw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        LogPanel.setInfo(getClass(), "Arkusz został zapisany");

    }

    public void generateScores(int maxScore){
        Random random = new Random();
        int col = this.getSelectedSpreadSheet().getColumnCount();
        int row = this.getSelectedSpreadSheet().getRowCount();

        for (int i = 0; i < col; i++)
            for(int j=0;j<row;j++) {
                Object a = String.valueOf(random.nextDouble() * maxScore);
                this.getSelectedSpreadSheet().setValueAt(a, j, i);

            }

        LogPanel.setInfo(getClass(), "Wygenerowanie przykładowych wartości arkusza");

    }

    /**
     * Metoda ResizeTab odpowiadająca za zmianę wielkości arkusza
     */

    public void ResizeTab(){

        stopTabNameEditing();

        for(int i = 0; i < jTabbedPane.getTabCount()-1; i++) {
            SpreadSheet table = sheetTable.get(i);
            if(table.isEditing())
                table.getCellEditor().stopCellEditing();
            table.clearSelection();
        }

        int index = jTabbedPane.getSelectedIndex();
        SpreadSheet temporarySheet = sheetTable.get(index);
        multiOptionPane.showDialog();
        int col = multiOptionPane.getColumns() , row = multiOptionPane.getRows();

        if(col>0 && row>0) {
            SpreadSheet newSheet = new SpreadSheet(col, row, formulaBar);
            for(int i = 0 ; i < newSheet.getRowCount();i++)
                if(temporarySheet.getRowCount() > i)
                    for(int j = 0 ; j < newSheet.getColumnCount();j++) {
                        if (temporarySheet.getColumnCount() > j) {
                            Object str = temporarySheet.getValueAt(i, j).toString();
                            if (str != null)
                                newSheet.setValueAt(str, i, j);
                        }
                    }

            sheetTable.remove(index);
            sheetTable.add(index, newSheet);
            RemoveTab();
            jTabbedPane.setComponentAt(index, newSheet.getScrollPane());

            jTabbedPane.addTab("+", newTabContent);
            TabComponent tabComp = new TabComponent();
            jTabbedPane.setTabComponentAt(index, tabComp);
            tabComp.startNameEditing();
            jTabbedPane.setSelectedIndex(index);

            LogPanel.setInfo(getClass(), "Zmiana wielkości arkusza");


        }
    }

    /**
     * Metoda stopTabNameEditing odpowiadająca za zatrzymanie edytowania pola tytułu zakładki podczas tworzenia nowej
     */

    private void stopTabNameEditing() {
        if(jTabbedPane.getTabCount() > 1) {
            TabComponent tabComp;
            for(int t = 0; t < jTabbedPane.getTabCount()-1; t++) {
                tabComp = (TabComponent) jTabbedPane.getTabComponentAt(t);
                tabComp.stopNameEditing();
            }
        }
    }
    public JTabbedPane getjTabbedPane(){ return jTabbedPane;}

    /**
     * Metoda print odpowiadająca za drukowanie zawartości arkusza
     * @param g            - grafika
     * @param pf           - Format strony
     * @param page         - ilość stron
     * @return             - zwracana wartość enum wskazująca czy dana strona istnieje
     * @throws PrinterException
     */
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        int index = jTabbedPane.getSelectedIndex();
        String title = "Tytuł arkusza: " + jTabbedPane.getTitleAt(index);
        String data = "";

        if (page > 0) {
            return NO_SUCH_PAGE;
        }


        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());


        g.drawString(title,  30, 30);

        int rowPosition = 50;


        for(int i = 0; i < sheetTable.get(index).getRowCount(); i++){

            for(int j = 0; j < sheetTable.get(index).getColumnCount(); j++){

                if(sheetTable.get(index).getValueAt(i,j) != (""))
                {
                    data += " " + sheetTable.get(index).getValueAt(i, j);
                    g.drawString(data,   30 ,rowPosition);
                }

            }
            rowPosition += 20;
            data = "";

        }
        // tell the caller that this page is part
        // of the printed document
        return PAGE_EXISTS;
    }

    /**
     * Metoda getSelectedSpreadSheet
     * @return typ zwracany SpreadSheet, zwraca aktualnie wybrany arkusz przez użytkownika
     */
    public SpreadSheet getSelectedSpreadSheet(){ return sheetTable.get(jTabbedPane.getSelectedIndex());}

    /**
     * Klasa TabComponet jest komponentem nowej zakładki, dająca funkcjonalność jej edytowania czy też dodawania
     */

    private class TabComponent extends JPanel{
        private JTextField title;
        private Caret editingCaret;
        private Caret nonEditingCaret;
        private Border editingBorder;
        private Border nonEditingBorder;


        public TabComponent() {
            setOpaque(false);
            this.setInheritsPopupMenu(true);
            title = new JTextField("Arkusz " + tabIndex);
            tabIndex++;
            title.setOpaque(false);
            final TabComponent self = this;
            title.setFont(Fonts.CalibriSmall.font());
            title.setForeground(Colors.Black.color(0.5f));
            title.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getButton()!=3){
                        if (evt.getClickCount() == 1) {
                            jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTabComponent(self));
                        } else if (evt.getClickCount() == 2) {
                            startNameEditing();
                        }
                    }
                }
            });

            title.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    stopNameEditing();
                }
            });
            title.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent evt) {
                    stopNameEditing();
                }
            });
            editingCaret = title.getCaret();
            nonEditingCaret = new Caret() {  // no-op implementation just to disable the caret and selection
                public void addChangeListener(ChangeListener l) { }
                public void deinstall(javax.swing.text.JTextComponent c) { }
                public int getBlinkRate() {return -1;}
                public int getDot() {return -1;}
                public Point getMagicCaretPosition() {return null;}
                public int      getMark() {return -1;}
                public void install(javax.swing.text.JTextComponent c) { }
                public boolean isSelectionVisible() {return false;}
                public boolean isVisible() {return false;}
                public void moveDot(int dot) { }
                public void paint(Graphics g) { }
                public void removeChangeListener(ChangeListener l) { }
                public void setBlinkRate(int rate) { }
                public void setDot(int dot) { }
                public void setMagicCaretPosition(Point p) { }
                public void setSelectionVisible(boolean v) { }
                public void setVisible(boolean v) { }
            };

            title.setCaret(nonEditingCaret);
            title.setEditable(false);
            editingBorder = BorderFactory.createLineBorder(Colors.Black.color(), 1);
            nonEditingBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);

            title.setBorder(nonEditingBorder);
            add(title);
        }
        public void startNameEditing() {

            title.setForeground(Colors.Black.color(0.7f));
            title.requestFocusInWindow();
            title.setEditable(true);
            title.setCaret(editingCaret);
            title.selectAll();
            title.setBorder(editingBorder);
            title.setOpaque(true);
            // set the text field to stay fixed at its current size
            FontMetrics metrics = title.getFontMetrics(title.getFont());
            int width = title.getPreferredSize().width;
            int height = title.getPreferredSize().height;
            // the 'm' char is the default measure for text componenet column width
            int columnWidth = metrics.charWidth('m');
            int columns = (int) (title.getPreferredSize().getWidth() / columnWidth);
            // add one extra column so that all the text is displayed
            title.setColumns(columns + 11);
        }
        private void stopNameEditing() {
            if(title.isEditable()) {
                title.setEditable(false);
                title.setBorder(nonEditingBorder);
                title.setOpaque(false);
                // set columns to 0 so that the text field will adjust its size correctly
                title.setColumns(0);
                title.setSelectionStart(0);
                title.setSelectionEnd(0);
                title.setCaret(nonEditingCaret);

                title.setForeground(Colors.Black.color(0.5f));
                jTabbedPane.setTitleAt(jTabbedPane.indexOfTabComponent(this), title.getText());

            }
        }
    }


    /**
     * Klasa PopMenu odpowiadająca za utworzenie menu kontekstowego pojawiajacego się po PPM zawierająca takie funkcje
     * jak RESIZE,NEWTAB,DELETE,SAVE
     */

    private class PopMenu   extends JPopupMenu implements ActionListener{

        static private final String _RESIZE= "Zmień rozmiar";
        static private final String _NEWTAB= "Dodaj arkusz";
        static private final String _DELETE= "Usuń arkusz";
        static private final String _SAVE  = "Zapisz arkusz";
        static private final String _RANDOM = "Wygeneruj wartości";

        public PopMenu(){

            JMenuItem item = new JMenuItem(_RESIZE);
            item.addActionListener(this);
            add(item);
            item = new JMenuItem(_NEWTAB);
            item.addActionListener(this);
            add(item);

            item = new JMenuItem(_DELETE);
            item.addActionListener(this);
            add(item);

            item = new JMenuItem(_SAVE);
            item.addActionListener(this);
            add(item);

            item = new JMenuItem(_RANDOM);
            item.addActionListener(this);
            add(item);

            pack();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()==_NEWTAB)
                addTab();
            else if(e.getActionCommand() == _DELETE)
                RemoveTab();
            else if(e.getActionCommand() == _RESIZE)
                ResizeTab();
            else if(e.getActionCommand() == _SAVE)
                SaveTable();
            else if(e.getActionCommand() == _RANDOM)
                generateScores(1000);
        }
    }


    /**
     * Utworzona metoda służy do ustalenia przycisków za pomocą,
     * których użytkownik bedzie mógł się poruszać po zakładkach
     */

    private void setupTabTraversalKeys() {

        KeyStroke ctrlTab = KeyStroke.getKeyStroke("ctrl TAB");
        KeyStroke ctrlShiftTab = KeyStroke.getKeyStroke("ctrl shift TAB");

        // Remove ctrl-tab from normal focus traversal
        Set<AWTKeyStroke> forwardKeys = new HashSet<AWTKeyStroke>(jTabbedPane.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.remove(ctrlTab);
        jTabbedPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

        // Remove ctrl-shift-tab from normal focus traversal
        Set<AWTKeyStroke> backwardKeys = new HashSet<AWTKeyStroke>(jTabbedPane.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        backwardKeys.remove(ctrlShiftTab);
        jTabbedPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);

        // Add keys to the tab's input map
        InputMap inputMap = jTabbedPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(ctrlTab, "navigateNext");
        inputMap.put(ctrlShiftTab, "navigatePrevious");
    }

    /**
     * Metoda getDataFromSpreadSheet
     * @return Lista typu double
     */
    public ArrayList<Double> getDataFromSpreadSheet(){
        int index = jTabbedPane.getSelectedIndex();
        SpreadSheet spr = sheetTable.get(index);
        ArrayList<Double> list = new ArrayList<>();
        for(int i = 0; i  < spr.getRowCount() ; i++) {
            try {
                double x = (double)spr.getValueAt(i,0);
                list.add(i, x);
            }catch(Exception e){
                System.err.print(e.getMessage());
            }
        }

        return list;
    }
}
