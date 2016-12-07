package gameobject;

import java.awt.*;

/**
 * Created by Hoangelato on 16/11/2016.
 */
public class Conveyor extends GameObject {

    public static Direction convertIndexToDirection(int index) {
        switch (index) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            default:
                return Direction.NONE;
        }

    }

    public Conveyor getConveyorByType(int type) {
        switch (type) {
            case ConveyorEnd.TYPE_X_END:
                return new ConveyorXEnd(this.posX,this.posY);
            case ConveyorMoving.TYPE_X_MID:
                return new ConveyorXMid(this.posX, this.posY);
//            case TYPE_Y_END:
//                return new ConveyorYEnd(this.posX,this.posY);
            case ConveyorMoving.TYPE_Y_MID:
                return new ConveyorYMid(this.posX, this.posY);


            default:
                return new Conveyor(0, 0);
        }
    }


    @Override
    public void loadImage() {
    }


    public Conveyor(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }



    public void update() {
        //animation.update();
    }


    @Override
    public void draw(Graphics g) {
        //animation.draw(g,this.posX,this.posY);
    }


}
