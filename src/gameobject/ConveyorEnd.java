package gameobject;

import helper.LogicPoint;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Nhat on 02/12/2016.
 */
public class ConveyorEnd extends ConveyorFixed {
    public static final int TYPE_X_END = 1;
    public static final int TYPE_Y_END = 3;
    protected ColorBox color;
    protected ArrayList<BufferedImage> sprites;

    protected Stack<ColorBox> stackColor = new Stack<ColorBox>();

    public ConveyorEnd(int posX, int posY) {
        super(posX, posY);
        color = ColorBox.WHITE;
    }

    public ConveyorEnd(int posX, int posY, ColorBox color) {
        super(posX, posY);
        this.color = color;
    }

    public void setColor(ColorBox color) {
        this.color = color;
    }

    public Stack<ColorBox> getStackColor() {
        return stackColor;
    }

    public ColorBox getColor() {
        return color;
    }

    public void addToStack(ColorBox colorBox) {
        stackColor.push(colorBox);
        this.color = colorBox;
    }

    public LogicPoint getLogicPoint() {
        return LogicPoint.convertPointToLogicPoint(new Point(posX + 42, posY + 62));
    }


    protected void loadImageByColor(ColorBox color) {
        switch (color) {
            case BLUE:
                sprite = sprites.get(0);
                break;
            case GREEN:
                sprite = sprites.get(1);
                break;
            case RED:
                sprite = sprites.get(2);
                break;
            case YELLOW:
                sprite = sprites.get(3);
                break;
            case PINK:
                sprite = sprites.get(4);
                break;
            default:
                sprite = sprites.get(5);
        }
    }

    public ColorBox nextColorNeedToReceive() {
        stackColor.pop();
        if (!stackColor.isEmpty()) {
            this.color = stackColor.peek();
        } else {
            this.color = ColorBox.WHITE;
        }
        return this.color;
    }

    @Override
    public void update() {
        loadImageByColor(this.color);
    }
}
