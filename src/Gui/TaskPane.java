package Gui;

import Functions.Colors;
import Functions.Fonts;
import Functions.Images;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.painter.MattePainter;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

/**
 * Created by Kacper on 2015-04-07.
 */

public class TaskPane extends JXPanel{

    final JXLabel label;
    private JXTaskPane taskPane;

    public TaskPane(){
        label = new JXLabel();
        label.setFont(Fonts.Calibri.font());
        label.setText("Wybierz funkcje:");
        label.setIcon(Images.NetworkDisconnected.getIcon(32, 32));
        label.setHorizontalAlignment(JXLabel.LEFT);
        label.setBackgroundPainter(getPainter());

        changeUIdefaults();

        JXTaskPaneContainer taskpanecontainer = new JXTaskPaneContainer();

        taskPane = new JXTaskPane();
        taskPane.setTitle("Funkcje Arkusza");
        taskPane.setIcon(Images.Quit.getIcon(24, 24));

        taskPane.add(label);

        taskpanecontainer.add(taskPane);

        this.setAlpha(1.0f);

        this.add(taskpanecontainer, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(250, 200));

    }

    public void addComponent(AbstractAction abstractAction){
        taskPane.add(abstractAction);
    }

    public void setLabelText(String txt){
        label.setText(txt);
    }

    private void changeUIdefaults(){
        UIManager.put("TaskPaneContainer.useGradient", Boolean.FALSE);
        UIManager.put("TaskPaneContainer.background", Colors.MyGray.color(0.0001f));

        // setting taskpane defaults
        UIManager.put("TaskPane.font", new FontUIResource(new Font("Calibri", Font.BOLD, 18)));
        UIManager.put("TaskPane.titleBackgroundGradientStart", Colors.MyGray.color());
        UIManager.put("TaskPane.titleBackgroundGradientEnd", Colors.MyGray.color());


    }

    public org.jdesktop.swingx.painter.Painter getPainter() {
        int width = 100;
        int height = 100;
        Color color1 = Colors.VeryLightBlue.color(0.4f);
        Color color2 = Colors.LightBlue.color(0.8f);

        LinearGradientPaint gradientPaint =
                new LinearGradientPaint(0.0f, 0.0f, width, height,
                        new float[]{0.0f, 1.0f},
                        new Color[]{color1, color2});
        MattePainter mattePainter = new MattePainter(gradientPaint);
        return mattePainter;
    }

}
