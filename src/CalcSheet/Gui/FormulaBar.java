package CalcSheet.Gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kacper on 2015-04-18.
 */


public class FormulaBar extends JToolBar implements ActionListener {

    private JTextField formulaField;
    private JButton cell;
    private JLabel jLabel;

    private String formula;

    private final String cols = "10px,80px,10px,pref:grow,10px";
    private final String rows = "5px,pref:grow,5px";

    private FormPanel formPanel;


    public FormulaBar() {
        formPanel = new FormPanel(cols, rows);
        formula = null;
        initializeComponents();
    }

    private void initializeComponents() {
        formulaField = new JTextField();
        cell = new JButton("");
        jLabel = new JLabel(":");
        formPanel.addXY(cell, 2, 2);
        formPanel.addXY(formulaField, 4, 2);
        formPanel.addXY(jLabel, 3, 2);

        cell.setEnabled(false);
        cell.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        this.add(formPanel);
        this.setFloatable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setFormulaField(String str) {
        formulaField.setText(str);
    }
    public void setCellButton(String str) {
        cell.setText(str);
    }
    public String getFormulaField() {
        return formulaField.getText();
    }
}
