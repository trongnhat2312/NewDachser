package gameobject;

import helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 20/11/2016.
 */
public class ConveyorYMid extends ConveyorMoving{
    protected ConveyorYMid(int posX, int posY) {
        super(posX, posY);
        super.animation = new AnimationHelper("resource/conveyor/y_mid/y_mid(", 199, 4);
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
