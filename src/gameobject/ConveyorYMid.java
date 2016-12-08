package gameobject;

import helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 20/11/2016.
 */
public class ConveyorYMid extends ConveyorMoving{
    protected ConveyorYMid(int posX, int posY, int level) {
        super(posX, posY);
        if (167 - 8 * level > 17) {
            super.animation = new AnimationHelper("/resource/conveyor/y_mid/y_mid(", 167 - 8 * level, 4);
        } else {
            super.animation = new AnimationHelper("/resource/conveyor/y_mid/y_mid(", 17, 4);
        }
    }
    public void update() {
        super.update();
        // animation.update();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
