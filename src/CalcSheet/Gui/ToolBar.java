package CalcSheet.Gui;

import CalcSheet.Functions.Images;
import CalcSheet.Gui.JXGraph.AxisChoose;
import CalcSheet.Gui.JXGraph.GraphPoints;
import CalcSheet.SheetComponent.SheetCell;
import CalcSheet.SheetComponent.SpreadSheet;

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
        jtbElements = new ArrayList<>();


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
        jtbElements.add(0, this.createToolBarButton("New", Images.New.getIcon(20,20), true, this));
        jtbElements.add(1, this.createToolBarButton("Open", Images.Open.getIcon(20, 20), true, this));
        jtbElements.add(2, this.createToolBarButton("Save", Images.Save.getIcon(20, 20), true, this));
        jtbElements.add(3, this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
        jtbElements.add(4, new JSeparator(VERTICAL));
        jtbElements.add(5, fccb);
        jtbElements.add(6,fsl);
        jtbElements.add(7, new JSeparator(VERTICAL));
        jtbElements.add(8, this.createToolBarButton("Bold", Images.Bold.getIcon(20, 20), true, this));
        jtbElements.add(9, this.createToolBarButton("Italic", Images.Italic.getIcon(20, 20), true, this));
        jtbElements.add(10, new JSeparator(VERTICAL));
        jtbElements.add(11, this.createToolBarButton("Align Left", Images.Left.getIcon(20, 20), true, this));
        jtbElements.add(12, this.createToolBarButton("Center Horizontally", Images.Middle.getIcon(20, 20), true, this));
        jtbElements.add(13, this.createToolBarButton("Align Right", Images.Right.getIcon(20, 20), true, this));
        jtbElements.add(14, new JSeparator(VERTICAL));
        jtbElements.add(15, this.createToolBarButton("Background Color", Images.Paint.getIcon(20, 20), true, this));
        jtbElements.add(16, this.createToolBarButton("Font Color", Images.Textcolor.getIcon(20, 20), true, this));
        jtbElements.add(17, new JSeparator(VERTICAL));
        jtbElements.add(18, this.createToolBarButton("Sum", Images.Sum.getIcon(20, 20), true, this));
        jtbElements.add(19, this.createToolBarButton("Matrix", Images.Matrix.getIcon(20, 20), true, this));
        jtbElements.add(20, this.createToolBarButton("Sinus", Images.Sin.getIcon(20, 20), true, this));
        jtbElements.add(21, this.createToolBarButton("Cosinus", Images.Cos.getIcon(20, 20), true, this));
        jtbElements.add(22, this.createToolBarButton("Tangens", Images.Tg.getIcon(20, 20), true, this));
        jtbElements.add(23, this.createToolBarButton("Cotangens", Images.Ctg.getIcon(20, 20), true, this));
        jtbElements.add(24, new JSeparator(VERTICAL));
        jtbElements.add(25, this.createToolBarButton("Chart", Images.Graph.getIcon(20, 20), true, this));


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0, panelColumn = 2; i <= 25; i++, panelColumn += 2) {

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
        String strInfo = "";


        if (e.getSource().equals(jtbElements.get(25))) {

            GraphPoints<Integer, Integer, Double> graphPoints;
            java.util.List<GraphPoints<Integer, Integer, Double>> points = new ArrayList<>();

            int colMax = 0, rowMax = 0;
            int columnM = 0;
            int col = 0, row = 0;
            int rowM = 0;

            strInfo = "Utworzenie grafu";

            try {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    if (ii == 0) {
                        colMax = sc.column;
                        rowMax = sc.row;
                    }

                    col = sc.column - colMax;
                    row = sc.row - rowMax;

                    columnM = (columnM < col) ? col : columnM;
                    rowM = (rowM < row) ? row : rowM;
                    graphPoints = new GraphPoints<>();
                    graphPoints.setAll(col, row, new Double(sc.value.toString()));
                    points.add(graphPoints);
                }
                columnM += 1;
                rowM += 1;
                AxisChoose axisChoose = new AxisChoose(points,columnM,rowM);

            } catch (NullPointerException r) {
                MultiOptionPane multiOptionPane = new MultiOptionPane();
                multiOptionPane.showErrorPane("Wybrane pole jest puste !", "B³¹d");
                strInfo += ":Wyst¹pi³ b³¹d";
            }

        }


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

            strInfo = "Rozpoczêto drukowanie arkusza";
        }


        if (e.getSource().equals(jtbElements.get(5))) {
            String fontName = fccb.getSelectedFontName();
            fontName = (fontName != null ? fontName : "");
            if (spreadSheet._selection != null) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    sc.setFont(new Font(fontName, selectedFont.getStyle(), selectedFont.getSize()));
                    spreadSheet.repaint();

                }
                strInfo = "Czcionka wybranych komórek zosta³a zmieniona";
            }


        }

        if (e.getSource().equals(jtbElements.get(6))) {
            int fontSizeIndex = fsl.getSelectedIndex();
            for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                Font selectedFont = sc.getFont();
                sc.setFont(new Font(selectedFont.getName(), selectedFont.getStyle(), fontSizeList[fontSizeIndex]));
                spreadSheet.repaint();
            }

            strInfo = "Wielkoœæ czcionki wybranych komórek zosta³a zmieniona";

        }

        if (e.getSource().equals(jtbElements.get(8))) {
            if (isBold) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if (isItalic)
                        sc.setFont(new Font(selectedFont.getName(), Font.PLAIN + Font.ITALIC, selectedFont.getSize()));
                    else sc.setFont(new Font(selectedFont.getName(), Font.PLAIN, selectedFont.getSize()));
                    spreadSheet.repaint();
                    isBold = false;
                }
                strInfo = "Czcionka wybranych komórek nie jest ju¿ pogrubiona";

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
                strInfo = "Czcionka wybranych komórek zosta³a pogrubiona";

            }


        }

        if (e.getSource().equals(jtbElements.get(9))) {
            if (isItalic) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if (isBold)
                        sc.setFont(new Font(selectedFont.getName(), Font.PLAIN + Font.BOLD, selectedFont.getSize()));
                    else sc.setFont(new Font(selectedFont.getName(), Font.PLAIN, selectedFont.getSize()));
                    spreadSheet.repaint();
                    isItalic = false;
                }
                strInfo = "Czcionka wybranych komórek nie jest ju¿ pochylona";

            } else {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    Font selectedFont = sc.getFont();
                    if (isBold)
                        sc.setFont(new Font(selectedFont.getName(), Font.ITALIC + Font.BOLD, selectedFont.getSize()));
                    else sc.setFont(new Font(selectedFont.getName(), Font.ITALIC, selectedFont.getSize()));
                    spreadSheet.repaint();
                    isItalic = true;
                }
                strInfo = "Czcionka wybranych komórek zosta³a pochylona";

            }
        }

        if (e.getSource().equals(jtbElements.get(11))) {


            if (spreadSheet._selection != null)
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    sc.setTextAligment(SwingConstants.LEFT);

                }
            strInfo = "Wyrównanie komórek do lewej";
            spreadSheet.repaint();
        }

        if (e.getSource().equals(jtbElements.get(12))) {

            if (spreadSheet._selection != null)
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    sc.setTextAligment(SwingConstants.CENTER);

                }
            strInfo = "Wyrównanie komórek do œrodka";

            spreadSheet.repaint();
        }

        if (e.getSource().equals(jtbElements.get(13))) {

            if (spreadSheet._selection != null)
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    sc.setTextAligment(SwingConstants.RIGHT);
                }
            strInfo = "Wyrównanie komórek do prawej";

            spreadSheet.repaint();
        }


        if (e.getSource().equals(jtbElements.get(15))) {
            if (spreadSheet._selection != null) {
                if (_colorWindow == null) new JWindow();

                Color col = JColorChooser.showDialog(_colorWindow, "Kolor tla", null);
                if (col != null)
                    for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                        SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                        sc.setBackground(col);
                    }
                strInfo = "Kolor t³a wybranych komórek zosta³ zmieniony";

                spreadSheet.repaint();
            }

        }

        if (e.getSource().equals(jtbElements.get(16))) {
            if (spreadSheet._selection != null) {
                if (_colorWindow == null) new JWindow();

                Color col = JColorChooser.showDialog(_colorWindow, "Kolor czcionki", null);
                if (col != null)
                    for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                        SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                        sc.setForeground(col);
                    }
                strInfo = "Kolor czcionki wybranych komórek zosta³ zmieniony";
                spreadSheet.repaint();
            }

        }

        if (e.getSource().equals(jtbElements.get(18))) {
            if (spreadSheet._selection != null) {
                double sum = 0.0;
                MultiOptionPane multiOptionPane = new MultiOptionPane();
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];
                    sum += new Double(sc.getValue().toString());
                }
                strInfo = "Obliczono sumê zaznaczonych komórek równ¹:"+sum;

                multiOptionPane.showErrorPane("Suma zaznaczonych komórek := " + sum, " Wynik sumy");
            }
        }

        if (e.getSource().equals(jtbElements.get(20))) {
            if (spreadSheet._selection != null) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    double wynikKomorki = Math.sin(new Double(sc.getValue().toString()));
                    spreadSheet.setValueAt(String.valueOf(wynikKomorki),sc.row,sc.column);

                }
                strInfo = "Obliczono wartoœæ sinus zaznaczonych komórek";

            }
        }

        if (e.getSource().equals(jtbElements.get(21))) {
            if (spreadSheet._selection != null) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    double wynikKomorki = Math.cos(new Double(sc.getValue().toString()));
                    spreadSheet.setValueAt(String.valueOf(wynikKomorki),sc.row,sc.column);
                }
                strInfo = "Obliczono wartoœæ cos zaznaczonych komórek";

            }
        }

        if (e.getSource().equals(jtbElements.get(22))) {
            if (spreadSheet._selection != null) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    double wynikKomorki = Math.tan(new Double(sc.getValue().toString()));
                    spreadSheet.setValueAt(String.valueOf(wynikKomorki),sc.row,sc.column);

                }
                strInfo = "Obliczono wartoœæ tangens zaznaczonych komórek";

            }
        }

        if (e.getSource().equals(jtbElements.get(23))) {
            if (spreadSheet._selection != null) {
                for (int ii = 0; ii < spreadSheet._selection.length; ii++) {
                    SheetCell sc = (SheetCell) spreadSheet._selection[ii];

                    double ctg = 1/Math.tan(new Double(sc.getValue().toString()));
                    spreadSheet.setValueAt(String.valueOf(ctg),sc.row,sc.column);

                }
                strInfo = "Obliczono wartoœæ cotangens zaznaczonych komórek";
            }
        }

        LogPanel.setInfo(getClass(), strInfo);
    }
}



