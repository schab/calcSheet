package Gui;

import Functions.Colors;
import Functions.Fonts;
import Functions.Images;
import SheetComponent.SpreadSheet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by Kacper on 2015-04-10.
 */

public class CenterPanel extends FormPanel{

    private JTabbedPane jTabbedPane;
    private JPanel newTabContent;   // the empty JPanel of the "new tab" tab
    private ArrayList<SpreadSheet> sheetTable;
    private int tabIndex;  // used in creating unique initial tab titles
    private TaskPane taskPane;
    private MultiOptionPane multiOptionPane;

    public CenterPanel(String col, String row) {
        super(col,row);

        initializeComponents();
        initializeTabbedPane();
        initializeTaskPane();

        this.setBorder(BorderFactory.createBevelBorder(1, Colors.MyGray.color().darker(), Colors.MyGray.color().brighter()));
        this.addXY(taskPane, 1, 2);
        this.addXY(jTabbedPane, 2, 2);
    }

    private void initializeComponents(){
        sheetTable = new ArrayList<SpreadSheet>();
        jTabbedPane = new JTabbedPane();
        taskPane = new TaskPane();
        multiOptionPane = new MultiOptionPane();
    }

    private void initializeTaskPane() {
        taskPane.addComponent(abstractAction("Dodaj arkusz", "Kliknij, aby dodać arkusz.", Images.NetworkDisconnected.getIcon(32, 32), 0));
        taskPane.addComponent(abstractAction("Usuń", "Kliknij, aby usunąć arkusz.", Images.NetworkDisconnected.getIcon(32, 32), 1));
        taskPane.addComponent(abstractAction("Zmień rozmiar", "Kliknij, aby zmienić rozmiar arkusza.", Images.NetworkDisconnected.getIcon(32, 32), 2));
        taskPane.addComponent(abstractAction("Zapisz dane","Kliknij, aby zapisać dane arkusza.", Images.NetworkDisconnected.getIcon(32,32),3));
    }



    private void initializeTabbedPane(){
        SpreadSheet spreadSheet = new SpreadSheet(20, 20);
        sheetTable.add(spreadSheet);
        tabIndex = 1;
        jTabbedPane.addTab("Arkusz", spreadSheet.getScrollPane());

        jTabbedPane.setTabComponentAt(0, new TabComponent());
        // when a tab is selected, stop any active tab title editing
        jTabbedPane.getModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                stopTabNameEditing();
            }
        });

        newTabContent = new JPanel();

        // when the "new tab" tab is selected and its component is displayed, add a new tab
        newTabContent.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                addTab();
            }
        });

        jTabbedPane.addTab("+", newTabContent);
        jTabbedPane.setUI(new TabbedUI());
        jTabbedPane.setFont(Fonts.Calibri.font());
    }


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
            SpreadSheet newSheet = new SpreadSheet(col,row);
            sheetTable.add(newSheet);
            jTabbedPane.setComponentAt(newGroupIndex, newSheet.getScrollPane());

            jTabbedPane.addTab("+", newTabContent);
            TabComponent tabComp = new TabComponent();
            jTabbedPane.setTabComponentAt(newGroupIndex, tabComp);
            tabComp.startNameEditing();
        }


    }

    public void RemoveTab(){
        if(jTabbedPane.getTabCount() > 1) {
            int index = jTabbedPane.getSelectedIndex();
            jTabbedPane.setSelectedIndex(index - 1);
            jTabbedPane.removeTabAt(index);
        }else{
            multiOptionPane.showErrorPane("Nie znaleziono zakładki do usunięcia!", "Błąd!");
        }
    }

    public void ResizeTab(){

    }


    public void SaveTable(){

        int index = jTabbedPane.getSelectedIndex();
        String data = "Tytuł arkusza:["+jTabbedPane.getTitleAt(index)+"]\n\n"+ sheetTable.get(index).GetCellsData();

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(""));
        int retrival = chooser.showSaveDialog(null);

        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                fw.write(data.toString());
                fw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    private void stopTabNameEditing() {
        if(jTabbedPane.getTabCount() > 1) {
            TabComponent tabComp;
            for(int t = 0; t < jTabbedPane.getTabCount()-1; t++) {
                tabComp = (TabComponent) jTabbedPane.getTabComponentAt(t);
                tabComp.stopNameEditing();
            }
        }
    }



        private void tableFunctions(int index){
        switch(index){
            case 0:
                    taskPane.setLabelText("Dodanie Arkusza");
                    addTab();
                    jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount() - 2);

                break;

            case 1:
                taskPane.setLabelText("Usuwanie Arkusza");
                    RemoveTab();
                break;

            case 2:
                    taskPane.setLabelText("Zmiana Rozmiaru");
                    ResizeTab();
                break;

            case 3:
                    taskPane.setLabelText("Zapisywanie danych");
                    SaveTable();
                    break;
        }
    }



    private AbstractAction abstractAction(String name,String desc,Icon icon,int index){
        final String _name,_desc; _name = name; _desc = desc;
        final Icon _icon = icon;
        final int _index = index;

        return new AbstractAction() {
            {
                putValue(Action.NAME,_name);
                putValue(Action.SHORT_DESCRIPTION,_desc);
                putValue(Action.SMALL_ICON,_icon);
            }
            public void actionPerformed(ActionEvent e) {
                tableFunctions(_index);
            }

        };
    }


    public JTabbedPane getjTabbedPane(){ return jTabbedPane;}


    private class TabComponent extends JPanel{
        private JTextField title;
        private Caret editingCaret;
        private Caret nonEditingCaret;
        private Border editingBorder;
        private Border nonEditingBorder;


        public TabComponent() {
            setOpaque(false);
            title = new JTextField("Arkusz " + tabIndex);
            tabIndex++;
            title.setOpaque(false);
            final TabComponent self = this;
            title.setFont(Fonts.Calibri.font());
            title.setForeground(Colors.LightBlue.color());
            title.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 1) {
                        jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTabComponent(self));
                    } else if (evt.getClickCount() == 2) {
                        startNameEditing();
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
                public void addChangeListener(javax.swing.event.ChangeListener l) { }
                public void deinstall(javax.swing.text.JTextComponent c) { }
                public int getBlinkRate() {return -1;}
                public int getDot() {return -1;}
                public java.awt.Point getMagicCaretPosition() {return null;}
                public int      getMark() {return -1;}
                public void install(javax.swing.text.JTextComponent c) { }
                public boolean isSelectionVisible() {return false;}
                public boolean isVisible() {return false;}
                public void moveDot(int dot) { }
                public void paint(java.awt.Graphics g) { }
                public void removeChangeListener(javax.swing.event.ChangeListener l) { }
                public void setBlinkRate(int rate) { }
                public void setDot(int dot) { }
                public void setMagicCaretPosition(java.awt.Point p) { }
                public void setSelectionVisible(boolean v) { }
                public void setVisible(boolean v) { }
            };

            title.setCaret(nonEditingCaret);
            title.setEditable(false);
            editingBorder = BorderFactory.createLineBorder(Colors.Black.color(), 1);
            nonEditingBorder = BorderFactory.createEmptyBorder(0, 0, 20, 0);

            title.setBorder(nonEditingBorder);
            add(title);
        }


        private void startNameEditing() {

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

                title.setForeground(Colors.LightBlue.color());
                jTabbedPane.setTitleAt(jTabbedPane.indexOfTabComponent(this), title.getText());

            }
        }
    }
}
