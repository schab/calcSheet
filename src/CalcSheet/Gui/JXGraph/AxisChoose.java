package CalcSheet.Gui.JXGraph;

import CalcSheet.Gui.MultiOptionPane;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AxisChoose extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton osiYRadioButton;
    private JRadioButton osiXRadioButton;
    private JTextField chartName;
    private JTextField lineName;

    private List<GraphPoints<Integer,Integer,Double>> graphPoints;
    private int columnMax,rowMax;

    public AxisChoose(List<GraphPoints<Integer,Integer,Double>> graphPoints , int columnMax,int rowMax) {
        setContentPane(contentPane);
        setTitle("Przedstawienie wykresu");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.graphPoints = graphPoints;
        this.columnMax = columnMax;
        this.rowMax = rowMax;

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onOK() {
        int index = (osiXRadioButton.isSelected())?0:1;
        try {
            String title = chartName.getText();
            String colName = lineName.getText();
            MainGraph.initAndShowGUI(graphPoints, columnMax, rowMax , title, colName,index);

        }catch(NullPointerException e){
            MultiOptionPane multiOptionPane = new MultiOptionPane();
            multiOptionPane.showErrorPane("WprowadŸ wartoœci do pola tekstowego aby poprawnie utworzyæ graf","B³¹d!");
        }

        dispose();
    }

    private void onCancel() {

        dispose();
    }

}
