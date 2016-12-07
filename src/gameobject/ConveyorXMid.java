package gameobject;

import helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class ConveyorXMid extends ConveyorMoving {
    protected ConveyorXMid(int posX, int posY) {
        super(posX, posY);
        super.animation = new AnimationHelper("resource/conveyor/x_mid/x_mid(", 199, 4);
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
