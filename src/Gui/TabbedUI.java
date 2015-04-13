
package Gui;

import Functions.Colors;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * Created by Kacper on 2015-04-07.
 */

public class TabbedUI extends BasicTabbedPaneUI
{
    private static final Insets NO_INSETS = new Insets(0, 0, 0, 0);

    private int buttonHeight = 24;
    private Color tabColor = null;
    private Color background = null;

    private Color backgroundDarker1 = null;
    private Color backgroundDarker2 = null;

    private Color[] fadeColors;
    private int fadeColorCount = 3;
    private Color selectedTextColor = Colors.LightBlue.color();

    private int leftInset = 12;

    public static ComponentUI createUI(JComponent c)
    {
        return new TabbedUI();
    }

    protected void installComponents()
    {
        super.installComponents();

        tabColor = tabPane.getBackground().darker().darker();

        background = tabPane.getBackground();
        backgroundDarker1 = tabPane.getBackground().darker();
        backgroundDarker2 = tabPane.getBackground().darker().darker();

        fadeColors = new Color[fadeColorCount];
        for (int i = 0; i < fadeColorCount; i++)
        {
            int rs = tabColor.getRed();
            int gs = tabColor.getGreen();
            int bs = tabColor.getBlue();

            int rt = background.getRed();
            int gt = background.getGreen();
            int bt = background.getBlue();

            int rn = (int) Math.min(rs + (Math.abs(rt - rs) / 4 * i), 255);
            int gn = (int) Math.min(gs + (Math.abs(gt - gs) / 4 * i), 255);
            int bn = (int) Math.min(bs + (Math.abs(bt - bs) / 4 * i), 255);

            fadeColors[i] = new Color(rn, gn, bn);
        }
    }

    protected void installDefaults()
    {
        super.installDefaults();
        tabAreaInsets.left = leftInset;
        selectedTabPadInsets = new Insets(1, 1, 18, 1);
    }

    public int getTabRunCount(JTabbedPane pane)
    {
        return 1;
    }

    protected Insets getContentBorderInsets(int tabPlacement)
    {
        return NO_INSETS;
    }

    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight)
    {
        if (tabPlacement == tabIndex)
        {
            return buttonHeight;
        }
        else
        {
            return buttonHeight + (buttonHeight / 2) + 6;
        }
    }

    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
    {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + buttonHeight;
    }


    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex)
    {
        int tw = tabPane.getBounds().width;

        g.setColor(tabColor);
        g.fillRect(0, 0, tw, buttonHeight);
        g.draw3DRect(0, 0, leftInset - 1, buttonHeight, true);

        int x = rects[rects.length - 1].x + rects[rects.length - 1].width;
        g.draw3DRect(x, 0, tw - x - 1, buttonHeight, true);

        g.setColor(Color.black);
        g.drawLine(0, buttonHeight + 1, tw - 1, buttonHeight + 1);

        for (int i = 1; i <= fadeColorCount; i++)
        {
            g.setColor(fadeColors[fadeColorCount - 1]);
            g.drawLine(0, buttonHeight + 1 + i, tw - 1, buttonHeight + 1 + i);
        }

        super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int tx, int ty, int tw, int th, boolean isSelected)
    {
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(tx, 0);

        if (isSelected)
        {
            int[] x = new int[3];
            int[] y = new int[3];

            g.setColor(tabColor);

            g.fillRect(0, 0, tw, buttonHeight);
            g.draw3DRect(0, 0, tw - 1, buttonHeight, true);
            g.fillRect(buttonHeight / 2, buttonHeight, tw - buttonHeight, buttonHeight / 2 + 1);

            // Left Polygon
            x[0] = 0;
            y[0] = buttonHeight;
            x[1] = buttonHeight / 2;
            y[1] = buttonHeight + (buttonHeight / 2);
            x[2] = buttonHeight / 2;
            y[2] = buttonHeight;
            g.fillPolygon(x, y, 3);

            // Right Polygon
            x[0] = tw;
            y[0] = buttonHeight;
            x[1] = tw - buttonHeight / 2;
            y[1] = buttonHeight + (buttonHeight / 2);
            x[2] = tw - buttonHeight / 2;
            y[2] = buttonHeight;
            g.fillPolygon(x, y, 3);

            g.setColor(backgroundDarker1);
            g.drawLine(0, buttonHeight, buttonHeight / 2, buttonHeight + (buttonHeight / 2));

            g.setColor(backgroundDarker2);
            g.drawLine(0, buttonHeight + 1, buttonHeight / 2, buttonHeight + (buttonHeight / 2) + 1);
            g.drawLine(buttonHeight / 2, buttonHeight + (buttonHeight / 2) + 1, tw - buttonHeight / 2, buttonHeight + (buttonHeight / 2) + 1);
            g.drawLine(tw - buttonHeight / 2, buttonHeight + (buttonHeight / 2), tw, buttonHeight);

            g.setColor(Color.black);
            g.drawLine(buttonHeight / 2 + 1, buttonHeight + (buttonHeight / 2) + 2, tw - buttonHeight / 2 - 1, buttonHeight + (buttonHeight / 2) + 2);
            g.drawLine(tw - buttonHeight / 2 - 1, buttonHeight + (buttonHeight / 2) + 2, tw, buttonHeight + 1);

            for (int i = 1; i <= fadeColorCount; i++)
            {
                g.setColor(fadeColors[i - 1]);
                g.drawLine(buttonHeight / 2 + 2 + i, buttonHeight + (buttonHeight / 2) + 2 + i, tw - buttonHeight / 2 - 1, buttonHeight + (buttonHeight / 2) + 2 + i);
                g.drawLine(tw - buttonHeight / 2 - 1, buttonHeight + (buttonHeight / 2) + 2 + i, tw, buttonHeight + 1 + i);
            }
        }
        else
        {
            g.setColor(tabColor);

            g.fillRect(0, 0, tw, buttonHeight);
            g.draw3DRect(0, 0, tw - 1, buttonHeight, true);

            g.setColor(Color.black);
            g.drawLine(0, buttonHeight + 1, tw - 1, buttonHeight + 1);

            for (int i = 1; i <= fadeColorCount; i++)
            {
                g.setColor(fadeColors[fadeColorCount - 1]);
                g.drawLine(0, buttonHeight + 1 + i, tw - 1, buttonHeight + 1 + i);
            }
        }

        g2d.translate(-1 * tx, 0);
    }

    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected)
    {
        Rectangle r = rects[tabIndex];

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(r.x, 0);

        if (isSelected)
        {
            FontMetrics fm = getFontMetrics();
            g.setColor(selectedTextColor);
            g.drawString(title, (r.width / 2 - fm.stringWidth(title) / 2) + 1, buttonHeight / 2 + fm.getMaxDescent() + buttonHeight / 2 + 3);

        }
        else
        {
            FontMetrics fm = getFontMetrics();
            g.setColor(Color.white);
            g.drawString(title, (r.width / 2 - fm.stringWidth(title) / 2) + 1, buttonHeight / 2 + fm.getMaxDescent() + 2);
        }

        g2d.translate(-1 * r.x, 0);
    }

    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
    {
        // Do nothing
    }

    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected)
    {
        // Do nothing
    }

    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex)
    {
        // Do nothing
    }
}