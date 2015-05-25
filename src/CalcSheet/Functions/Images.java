package CalcSheet.Functions;

import org.jdesktop.swingx.util.GraphicsUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Kacper on 2015-04-07.
 */
public enum Images {

    NetworkConnected("NetworkConnected.png"),
    NetworkDisconnected("NetworkDisconnected.png"),
    Quit("quit.png"),
    Bold("Ikony/32x32/bold1.png"),
    Cos("Ikony/32x32/cos1.png"),
    Ctg("Ikony/32x32/ctg1.png"),
    Graph("Ikony/32x32/graph1.png"),
    Italic("Ikony/32x32/Italic1.png"),
    Left("Ikony/32x32/left1.png"),
    Matrix("Ikony/32x32/matrix1.png"),
    Middle("Ikony/32x32/middle1.png"),
    New("Ikony/32x32/new1.png"),
    Open("Ikony/32x32/open1.png"),
    Paint("Ikony/32x32/paint1.png"),
    Print("Ikony/32x32/print1.png"),
    Right("Ikony/32x32/right1.png"),
    Save("Ikony/32x32/save1.png"),
    Sin("Ikony/32x32/sin1.png"),
    Sum("Ikony/32x32/sum1.png"),
    Textcolor("Ikony/32x32/textcolor1.png"),
    Tg("Ikony/32x32/tg1.png"),
    Underline("Ikony/32x32/underline1.png");
    String imagefilename;

    Images(String name) {
        imagefilename = name;
    }

    BufferedImage getImage() {
        try {
            return ImageIO.read(ClassLoader.getSystemResourceAsStream(imagefilename));
        }
        catch (IOException e) {
            return null;
        }
    }

    public Icon getIcon() {
        return new ImageIcon(getImage());
    }

    BufferedImage getImage(int width, int height) {
        return GraphicsUtilities.createThumbnail(getImage(), width, height);
    }


    public Icon getIcon(int width, int height) {
        return new ImageIcon(getImage(width, height));
    }
}
