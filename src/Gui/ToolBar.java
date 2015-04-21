package Gui;

import Functions.Images;
import Gui.Graph.GraphFrame;
import SheetComponent.SheetCell;
import SheetComponent.SpreadSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;


/**
 * Created by Kacper on 2015-03-30.
 */

public class ToolBar extends JToolBar implements ActionListener {

    private ArrayList<JComponent> jtbElements;
    private Integer[] fontSizeList = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48};
    private FontChooserComboBox fccb;
    private JComboBox fsl;

    private String cols;
    private String rows;
    private FormPanel jPanel;
    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isUnderline = false;
    private CenterPanel centerPanel;
    private JWindow     _colorWindow;

    public ToolBar(CenterPanel centerPanel, String x, String y) {
        this.cols = x;
        this.rows = y;
        this.centerPanel = centerPanel;
        jPanel = new FormPanel(cols, rows);
        jtbElements = new ArrayList<JComponent>();


        this.initializeToolBarComponents();
        this.setFloatable(false);

    }


    private void initializeToolBarComponents() {

        fccb = new FontChooserComboBox();
        fccb.setSelectedItem("Calibri");
        fsl = new JComboBox(fontSizeList);
        fsl.setSelectedItem(centerPanel.getSelectedSpreadSheet().get_cellFont().getSize());

        fccb.addActionListener(this);
        fsl.addActionListener(this);


        // first row

        jtbElements.add(0, this.createToolBarButton("New", Images.New.getIcon(20, 20), true, this));
        jtbElements.add(1, this.createToolBarButton("Open", Images.Open.getIcon(20, 20), true, this));
        jtbElements.add(2, this.createToolBarButton("Save", Images.Save.getIcon(20, 20), true, this));
        jtbElements.add(3, this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
        jtbElements.add(4, new JSeparator(VERTICAL));
        jtbElements.add(5, fccb);
        jtbElements.add(6,fsl);
        jtbElements.add(7, new JSeparator(VERTICAL));
        jtbElements.add(8, this.createToolBarButton("Bold", Images.Bold.getIcon(20, 20), true, this));
        jtbElements.add(9, this.createToolBarButton("Italic", Images.Italic.getIcon(20, 20), true, this));
        jtbElements.add(10, this.createToolBarButton("Underline", Images.Underline.getIcon(20, 20), true, this));
        jtbElements.add(11, new JSeparator(VERTICAL));
        jtbElements.add(12, this.createToolBarButton("Align Left", Images.Left.getIcon(20, 20), true, this));
        jtbElements.add(13, this.createToolBarButton("Center Horizontally", Images.Middle.getIcon(20, 20), true, this));
        jtbElements.add(14, this.createToolBarButton("Align Right", Images.Right.getIcon(20, 20), true, this));
        jtbElements.add(15, new JSeparator(VERTICAL));
        jtbElements.add(16, this.createToolBarButton("Background Color", Images.Paint.getIcon(20, 20), true, this));
        jtbElements.add(17, this.createToolBarButton("Font Color", Images.Textcolor.getIcon(20, 20), true, this));
        jtbElements.add(18, new JSeparator(VERTICAL));
        jtbElements.add(19, this.createToolBarButton("Sum", Images.Sum.getIcon(20, 20), true, this));
        jtbElements.add(20, this.createToolBarButton("Matrix", Images.Matrix.getIcon(20, 20), true, this));
        jtbElements.add(21, this.createToolBarButton("Sinus", Images.Sin.getIcon(20, 20), true, this));
        jtbElements.add(22, this.createToolBarButton("Cosinus", Images.Cos.getIcon(20, 20), true, this));
        jtbElements.add(23, this.createToolBarButton("Tangens", Images.Tg.getIcon(20, 20), true, this));
        jtbElements.add(24, this.createToolBarButton("Cotangens", Images.Ctg.getIcon(20, 20), true, this));
        jtbElements.add(25, new JSeparator(VERTICAL));
        jtbElements.add(26, this.createToolBarButton("Chart", Images.Graph.getIcon(20, 20), true, this));


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0, panelColumn = 2; i <= 26; i++, panelColumn += 2) {

            jPanel.addXY(jtbElements.get(i), panelColumn, 2);

        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.add(jPanel);


    }
    private JButton createToolBarButton(String str, Icon ic, boolean bool, ActionListener actionListener) {
        JButton jb = new JButton("", ic);
        jb.setToolTipText(str);
        jb.addActionListener(actionListener);
        jb.setEnabled(bool);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);


        return jb;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        SpreadSheet spreadSheet = centerPanel.getSelectedSpreadSheet();


        if (e.getSource().equals(jtbElements.get(26)))
            new GraphFrame().setVisible(true);


        if (e.getSource().equals(jtbElements.get(0))) {
            centerPanel.addTab();
        }

        if (e.getSource().equals(jtbElements.get(2))) {

            centerPanel.SaveTable();
        }

        if (e.getSource().equals(jtbElements.get(3))) {

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(centerPanel);
            boolean ok = job.printDialog();
            if (ok) {
                try {
                    job.print();
                } catch (PrinterException ex) {
              /* The job did not successfully complete */
                }
            }
        }


        if (e.getSource().equals(jtbElements.get(5))) {
            String fontName = fccb.getSelectedFontName();
            fontName = (fontName != null ? fontName : "");
            if(spreadSheet._selection != null)
            for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                Font selectedFont = sc.getFont();
                sc.setFont(new Font(fontName, selectedFont.getStyle(), selectedFont.getSize()));
                spreadSheet.repaint();
            }


        }

        if (e.getSource().equals(jtbElements.get(6))) {
            int fontSizeIndex = fsl.getSelectedIndex();
            for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                Font selectedFont = sc.getFont();
                sc.setFont( new Font(selectedFont.getName(), selectedFont.getStyle(), fontSizeList[fontSizeIndex]));
                spreadSheet.repaint();
            }
        }

        if (e.getSource().equals(jtbElements.get(8))) {
            if (isBold) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if (isItalic)
                    sc.setFont(new Font(selectedFont.getName(), Font.PLAIN + Font.ITALIC, selectedFont.getSize()));
                    else  sc.setFont(new Font(selectedFont.getName(), Font.PLAIN , selectedFont.getSize()));
                    spreadSheet.repaint();
                    isBold = false;
                }
            } else {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if (isItalic)
                    sc.setFont(new Font(selectedFont.getName(), Font.BOLD + Font.ITALIC, selectedFont.getSize()));
                    else sc.setFont(new Font(selectedFont.getName(), Font.BOLD, selectedFont.getSize()));
                    spreadSheet.repaint();
                    isBold = true;
                }
            }
        }

        if (e.getSource().equals(jtbElements.get(9))) {
            if (isItalic) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if(isBold)
                    sc.setFont(new Font(selectedFont.getName(), Font.PLAIN + Font.BOLD, selectedFont.getSize()));
                    else  sc.setFont(new Font(selectedFont.getName(), Font.PLAIN , selectedFont.getSize()));
                    spreadSheet.repaint();
                    isItalic = false;
                }
            } else {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if(isBold)
                    sc.setFont(new Font(selectedFont.getName(), Font.ITALIC + Font.BOLD, selectedFont.getSize()));
                    else  sc.setFont(new Font(selectedFont.getName(), Font.ITALIC , selectedFont.getSize()));
                    spreadSheet.repaint();
                    isItalic = true;
                }
            }
        }

        if (e.getSource().equals(jtbElements.get(12))) {


                if(spreadSheet._selection != null)
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    sc.setTextAligment(SwingConstants.LEFT);

                }
                spreadSheet.repaint();
        }

        if (e.getSource().equals(jtbElements.get(13))) {

            if(spreadSheet._selection != null)
            for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                sc.setTextAligment(SwingConstants.CENTER);

            }
            spreadSheet.repaint();
        }

        if (e.getSource().equals(jtbElements.get(14))) {

            if(spreadSheet._selection != null)
            for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                sc.setTextAligment(SwingConstants.RIGHT);
            }
            spreadSheet.repaint();
        }


        if (e.getSource().equals(jtbElements.get(16))) {
            if(spreadSheet._selection != null) {
                if (_colorWindow == null) new JWindow();

                Color col = JColorChooser.showDialog(_colorWindow, "Kolor tla", null);
                if (col != null)
                    for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                        SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                        sc.setBackground(col);
                    }
                spreadSheet.repaint();
            }

        }

        if (e.getSource().equals(jtbElements.get(17))) {
            if(spreadSheet._selection != null) {
                if (_colorWindow == null) new JWindow();

                Color col = JColorChooser.showDialog(_colorWindow, "Kolor czcionki", null);
                if (col != null)
                    for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                        SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                        sc.setForeground(col);
                    }
                spreadSheet.repaint();
            }

        }
    }
}



