package Functions;

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
    Quit("quit.png");

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

    Icon getIcon() {
        return new ImageIcon(getImage());
    }

    BufferedImage getImage(int width, int height) {
        return GraphicsUtilities.createThumbnail(getImage(), width, height);
    }

    public Icon getIcon(int width, int height) {
        return new ImageIcon(getImage(width, height));
    }
}
