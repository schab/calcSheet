package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kacper on 2015-03-30.
 */
public class ToolBar extends JToolBar implements ActionListener{

    private String[] toolTips;
    private Icon[] toolIcons;


    public ToolBar(){
        this.initializeToolBarComponents();
    }

    private void initializeToolBarComponents(){}

    private JButton createToolBarButton(String str , Icon ic , boolean bool,ActionListener actionListener){
        JButton jb = new JButton("",ic);
        jb.setToolTipText(str);
        jb.addActionListener(actionListener);
        jb.setEnabled(bool);
        return jb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
