package gameobject;

import helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class ConveyorXMid extends ConveyorMoving {
    protected ConveyorXMid(int posX, int posY, int level) {
        super(posX, posY);
        if (167 - 8 * level > 17) {
            super.animation = new AnimationHelper("/resource/conveyor/x_mid/x_mid(", 167 - 8 * level, 4);
        } else {
            super.animation = new AnimationHelper("/resource/conveyor/x_mid/x_mid(", 17, 4);
        }

    }
    public void update() {
        super.update();
        // animation.update();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //      animation.draw(g,this.posX,this.posY);
    }
}
