package screens;

import com.sun.glass.ui.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.peer.MouseInfoPeer;

/**
 * Created by PC on 17/11/2016.
 */
public abstract class Screen extends JPanel implements MouseListener {
    public abstract void update();

    public abstract void draw(Graphics g);

    final BufferedImage resize(BufferedImage sbi, int n) {

        if (sbi != null) {
            int w = sbi.getWidth();
            int h = sbi.getHeight();
            Image tmp = sbi.getScaledInstance(w / n, h / n, Image.SCALE_SMOOTH);
            BufferedImage dbi = new BufferedImage(w / n, h / n, BufferedImage.TYPE_INT_ARGB);
            dbi.getGraphics().drawImage(tmp, 0, 0, null);
            return dbi;
        }
        return null;
    }

    final BufferedImage setSize(BufferedImage sbi, int w, int h) {

        if (sbi != null) {
            Image tmp = sbi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            BufferedImage dbi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dbi.getGraphics().drawImage(tmp, 0, 0, null);
            return dbi;
        }
        return null;
    }
    final BufferedImage setSize(BufferedImage sbi, Dimension size) {
        return setSize(sbi, size.width, size.height);
    }
}