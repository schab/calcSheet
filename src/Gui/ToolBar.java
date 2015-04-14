package Gui;

import Functions.Images;
import sun.swing.ImageIconUIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Kacper on 2015-03-30.
 */
public class ToolBar extends JToolBar implements ActionListener{

    private String[] toolTips;
    private Icon[] toolIcons;
    private ActionListener actionListener;

    public ToolBar(){

        this.initializeToolBarComponents();




    }

    private void initializeToolBarComponents(){

        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(0);


        JPanel buttonPanelUp = new JPanel();
        JPanel buttonPanelDown = new JPanel();

        buttonPanelUp.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanelDown.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttonPanelUp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        buttonPanelDown.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        this.setLayout(gridLayout);
        this.add(buttonPanelUp);
        this.add(buttonPanelDown);

        buttonPanelUp.add(this.createToolBarButton("New", Images.New.getIcon(8,8), true, this));
        buttonPanelUp.add(this.createToolBarButton("Open", Images.Open.getIcon(8, 8), true, this));
        buttonPanelUp.add(this.createToolBarButton("Save", Images.Save.getIcon(8, 8), true, this));
        buttonPanelUp.add(this.createToolBarButton("Print", Images.Print.getIcon(8, 8), true, this));
        buttonPanelUp.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPanelUp.add(new JComboBox<String>());







    }

    private JButton createToolBarButton(String str , Icon ic , boolean bool,ActionListener actionListener){
        JButton jb = new JButton("",ic);
        jb.setSize(10,10);
        jb.setToolTipText(str);
        jb.addActionListener(actionListener);
        jb.setEnabled(bool);
        return jb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
