package Gui;

import Functions.Images;
import sun.swing.ImageIconUIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kacper on 2015-03-30.
 */
public class ToolBar extends JToolBar implements ActionListener{

    private String[] toolTips;
    private Icon[] toolIcons;
    private ActionListener actionListener;
    private ArrayList<JComponent> jtbElements;



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

        jtbElements.add(0, this.createToolBarButton("New", Images.New.getIcon(20, 20), true, this));
        jtbElements.add(1, this.createToolBarButton("Open", Images.Open.getIcon(20, 20), true, this));
        jtbElements.add(2, this.createToolBarButton("Save", Images.Save.getIcon(20, 20), true, this));
        jtbElements.add(3, this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
        jtbElements.add(4, new JSeparator(VERTICAL));

        //    private static final String jtbColumns =   "10px,24px,5px,24px,5px,24px,5px,24px,5px,pref:grow,10px";

//        private static final String jtbRows    =   "8px,24px,6px,24px,8px"
        jPanel.addXY(jtbElements.get(0) , 2,2);
        jPanel.addXY(jtbElements.get(1) , 4,2);
        jPanel.addXY(jtbElements.get(2) , 6,2);
        jPanel.addXY(jtbElements.get(3) , 8,2);
        jPanel.addXY(jtbElements.get(4) , 10,2);


//        this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
//        new JSeparator(SwingConstants.VERTICAL));
//        new JComboBox<String>());

//        jPanel.addXY(this.createToolBarButton("Print", Images.Print.getIcon(20, 20), true, this));
//        jPanel.addXY(new JSeparator(SwingConstants.VERTICAL));
//        jPanel.addXY(new JComboBox<String>());

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
