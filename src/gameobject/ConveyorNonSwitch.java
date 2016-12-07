package gameobject;

/**
 * Created by Hoangelato on 22/11/2016.
 */
public class ConveyorNonSwitch extends ConveyorFixed {
    Direction direction;

    public ConveyorNonSwitch(int posX, int posY) {
        super(posX, posY);
    }


    public Conveyor getConveyorNonSwitchByDirection(Direction d){
        switch (d){
            case UP:
                return new ConveyorNonSwitchUp(posX,posY);
            case DOWN:
                return new ConveyorNonSwitchDown(posX,posY);
            case LEFT:
                return new ConveyorNonSwitchLeft(posX,posY);
            case RIGHT:
                return new ConveyorNonSwitchRight(posX,posY);
            default:
                return new Conveyor(posX,posY);
        }

    }

}