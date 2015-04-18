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

    private ArrayList<JComponent> jtbElements;
   // private String[] fontList = { "Arial", "Times new Roman", "Tahoma", "Celtic", "Gisha" };
    private String[] fontSizeList = {"4","6","8","10","12","14","16","18","20","22","24","26","28","30","32" };
    private FontChooserComboBox fccb;

    private String cols;
    private String rows;
    private FormPanel jPanel;

    public ToolBar(String x,String y){
        this.cols = x;
        this.rows = y;
        jPanel = new FormPanel(cols,rows);
        jtbElements = new ArrayList<JComponent>();


        this.initializeToolBarComponents();
        this.setFloatable(false);

    }

    private void initializeToolBarComponents(){


        fccb = new FontChooserComboBox();
        fccb.addActionListener(this);

        // first row

        jtbElements.add(0, this.createToolBarButton("New", Images.New.getIcon(20, 20), true, this));
        jtbElements.add(1, this.createToolBarButton("Open", Images.Open.getIcon(20, 20), true, this));
        jtbElements.add(2, this.createToolBarButton("Save", Images.Save.getIcon(20, 20), true, this));
        jtbElements.add(3, this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
        jtbElements.add(4, new JSeparator(VERTICAL));
        jtbElements.add(5, fccb);
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

        Object g = e.getSource();



        if(e.getSource().equals(jtbElements.get(0))){

            System.out.print("dziala");
        }


    }



}
