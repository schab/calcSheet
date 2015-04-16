package Gui;

import Functions.Images;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kacper on 2015-03-30.
 */
public class ToolBar extends JToolBar implements ActionListener{

    private String[] toolTips;
    private Icon[] toolIcons;
    private ActionListener actionListener;
    private ArrayList<JComponent> jtbElements;
    private String[] fontList = { "Arial", "Times new Roman", "Tahoma", "Celtic", "Gisha" };
    private String[] fontSizeList = {"4","6","8","10","12","14","16","18","20","22","24","26","28","30","32" };




    private String cols;
    private String rows;
    private FormPanel jPanel;

    public ToolBar(String x,String y){
        this.cols = x;
        this.rows = y;
        jPanel = new FormPanel(cols,rows);
        jtbElements = new ArrayList<JComponent>();


        this.initializeToolBarComponents();
    }

    private void initializeToolBarComponents(){

        // first row

        jtbElements.add(0, this.createToolBarButton("New", Images.New.getIcon(20, 20), true, this));
        jtbElements.add(1, this.createToolBarButton("Open", Images.Open.getIcon(20, 20), true, this));
        jtbElements.add(2, this.createToolBarButton("Save", Images.Save.getIcon(20, 20), true, this));
        jtbElements.add(3, this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
        jtbElements.add(4, new JSeparator(VERTICAL));
        jtbElements.add(5, new JComboBox(fontList));
        jtbElements.add(6, new JComboBox(fontSizeList));
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

        for(int i = 0,  panelColumn = 2; i <= 26; i++, panelColumn+=2){

            jPanel.addXY(jtbElements.get(i) , panelColumn,2);

        }

//        jPanel.addXY(jtbElements.get(0) , 2,2);
//        jPanel.addXY(jtbElements.get(1), 4, 2);
//        jPanel.addXY(jtbElements.get(2), 6, 2);
//        jPanel.addXY(jtbElements.get(3), 8, 2);
//        jPanel.addXY(jtbElements.get(4), 10, 2);
//        jPanel.addXY(jtbElements.get(5) , 12,2);
//        jPanel.addXY(jtbElements.get(6) , 14,2);
//        jPanel.addXY(jtbElements.get(7) , 16,2);
//        jPanel.addXY(jtbElements.get(8) , 18,2);
//        jPanel.addXY(jtbElements.get(9) , 20,2);
//        jPanel.addXY(jtbElements.get(10) , 22,2);
//        jPanel.addXY(jtbElements.get(11) , 24,2);
//        jPanel.addXY(jtbElements.get(12) , 26,2);
//        jPanel.addXY(jtbElements.get(13) , 28,2);
//        jPanel.addXY(jtbElements.get(14) , 30,2);
//        jPanel.addXY(jtbElements.get(15) , 32,2);
//        jPanel.addXY(jtbElements.get(16) , 34,2);
//        jPanel.addXY(jtbElements.get(17) , 36,2);
//        jPanel.addXY(jtbElements.get(18) , 38,2);
//        jPanel.addXY(jtbElements.get(19) , 40,2);
//        jPanel.addXY(jtbElements.get(20) , 42,2);
//        jPanel.addXY(jtbElements.get(21) , 44,2);
//        jPanel.addXY(jtbElements.get(22) , 46,2);
//        jPanel.addXY(jtbElements.get(23) , 48,2);
//        jPanel.addXY(jtbElements.get(24) , 50,2);
//        jPanel.addXY(jtbElements.get(25) , 52,2);
//        jPanel.addXY(jtbElements.get(26) , 54,2);




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.add(jPanel);




    }


    private JButton createToolBarButton(String str , Icon ic , boolean bool,ActionListener actionListener){
        JButton jb = new JButton("",ic);
        jb.setToolTipText(str);
        jb.addActionListener(actionListener);
        jb.setEnabled(bool);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        return jb;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
