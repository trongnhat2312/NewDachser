package gameobject;

import java.awt.*;

/**
 * Created by Hoangelato on 20/11/2016.
 */
public class ConveyorFixed extends Conveyor {
    protected ConveyorFixed(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(sprite, posX, posY, null);
    }
}