import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Kacper on 2015-03-30.
 */
public class ToolBar extends JToolBar {

    private ActionListener actionListener;
    private String[] toolTips;
    private Icon[] toolIcons;


    public ToolBar(ActionListener actionListener){

        this.actionListener=actionListener;
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
}
