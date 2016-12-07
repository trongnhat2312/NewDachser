package gameobject;

import helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 19/11/2016.
 */
public class ConveyorMoving extends Conveyor {
    //    public static final int TYPE_X_END = 1;
    public static final int TYPE_X_MID = 2;
    //    public static final int TYPE_Y_END = 3;
    public static final int TYPE_Y_MID = 4;

    public ConveyorMoving(int posX, int posY) {
        super(posX, posY);
    }

    AnimationHelper animation;

    @Override
    public void update() {
        animation.update();

    }

    @Override
    public void draw(Graphics g) {
        animation.draw(g, posX, posY);
    }

}
