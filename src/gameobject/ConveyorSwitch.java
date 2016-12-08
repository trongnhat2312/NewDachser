package gameobject;

import helper.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Hoangelato on 22/11/2016.
 */
public class ConveyorSwitch extends ConveyorFixed {
    private Direction direction = Direction.NONE;
    ArrayList<BufferedImage> sprites;
    private LogicPoint logicPoint;
    BufferedImage spriteUp, spriteDown, spriteLeft, spriteRight;
    public Rectangle clickArea = new Rectangle();
    public ArrayList<Direction> probableDirections = new ArrayList<Direction>();


    public ConveyorSwitch(LogicPoint logicPoint, Direction direction) {
        super(logicPoint.convertToPoint().x, logicPoint.convertToPoint().y);
        this.direction = direction;
        clickArea = new Rectangle(posX + 24, posY + 32 - 9, 36, 18);
        loadImage();
    }

    public ConveyorSwitch(int posX, int posY, Direction direction) {
        super(posX, posY);
        this.logicPoint = LogicPoint.convertPointToLogicPoint(new Point(posX + 42, posY + 44));
        this.direction = direction;
        clickArea = new Rectangle(posX + 24, posY + 32 - 9, 36, 18);
        loadImage();
    }

    public LogicPoint getLogicPoint() {
        return logicPoint;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void loadImage() {
        super.loadImage();
        sprites = new ArrayList<>();
        //for
        try {
            spriteUp = ImageIO.read(getClass().getResource("/resource/conveyor/switch/switch_up.png"));
            spriteDown = ImageIO.read(getClass().getResource("/resource/conveyor/switch/switch_down.png"));
            spriteLeft = ImageIO.read(getClass().getResource("/resource/conveyor/switch/switch_left.png"));
            spriteRight = ImageIO.read(getClass().getResource("/resource/conveyor/switch/switch_right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        sprites.add(spriteUp);
        sprites.add(spriteRight);
        sprites.add(spriteDown);
        sprites.add(spriteLeft);

        loadSpriteByDirection(this.direction);


    }


    private void loadSpriteByDirection(Direction d) {
        switch (d) {
            case UP:
                sprite = sprites.get(0);
                break;
            case RIGHT:
                sprite = sprites.get(1);
                break;
            case DOWN:
                sprite = sprites.get(2);
                break;
            case LEFT:
                sprite = sprites.get(3);
                break;

            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawRect(posX + 24, posY + 32 - 9, 36, 18);
    }


    int index;

    public void changeDirection() {
        for (int i = 0; i < probableDirections.size(); i++) {
            if (direction == probableDirections.get(i)) {
                direction = probableDirections.get((i + 1) % probableDirections.size());
                loadSpriteByDirection(direction);
                return;
            } else continue;
        }

    }
}
