package gameobject;


import helper.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 11/5/2016.
 */
public class Box extends GameObject {
    private double realX;       // toa do de di chuyen
    private double realY;       //  toa do de di chuyen
    private ColorBox color;
    private static double speed;
    private LogicPoint logicPointToCheck;
    public boolean isAllowedToCheck = true;

    private Direction direction = Direction.NONE;
    private static final int OFFSET_X = 12;
    private static final int OFFSET_Y = 48;

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public Direction getDirection() {
        return direction;
    }

    public Box(LogicPoint lp, ColorBox color, int level, Direction d) {
        Point p = lp.convertToPoint();
        p = new Point(p.x + 42 - 6 - 12, p.y + 62 - 3 - 48);
        realX = posX = p.x;
        realY = posY = p.y;
        logicPointToCheck = LogicPoint.convertPointToLogicPoint(new Point(posX + OFFSET_X, posY + OFFSET_Y));
        this.color = color;
        this.direction = d;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(getClass().getResource("/resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.02 + level * 0.003;
    }

    public Box(int x, int y, ColorBox color, int level, Direction d) {
        realX = posX = x;
        realY = posY = y;
        logicPointToCheck = LogicPoint.convertPointToLogicPoint(new Point(x, y));
        this.color = color;
        this.direction = d;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(getClass().getResource("/resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.02 + level * 0.003;
    }


    public ColorBox getColor() {
        return color;
    }

    public void checkToChangDirection() {
        int x = posX + OFFSET_X;
        int y = posY + OFFSET_Y;
        int xRaw, yRaw;
        LogicPoint lpRaw;

        switch (direction) {
            case UP:
//                return Math.abs(y - midMapY) < 3;

                xRaw = x + 18;
                yRaw = y - 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointToCheck.getLogicY() == lpRaw.getLogicY()) {
                    isAllowedToCheck = false;
                } else {
                    logicPointToCheck = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowedToCheck = true;
                }
                break;

            case DOWN:
//                return Math.abs(y - midMapY) < 3;
                xRaw = x - 18;
                yRaw = y + 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointToCheck.getLogicY() == lpRaw.getLogicY()) {
                    isAllowedToCheck = false;
                } else {
                    logicPointToCheck = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowedToCheck = true;
                }
                break;
            case LEFT:
//                return Math.abs(x - midMapX) < 3;
                xRaw = x - 18;
                yRaw = y - 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointToCheck.getLogicX() == lpRaw.getLogicX()) {
                    isAllowedToCheck = false;
                } else {
                    logicPointToCheck = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowedToCheck = true;
                }
                break;
            case RIGHT:
//                return Math.abs(x - midMapX) < 3;
                xRaw = x + 18;
                yRaw = y + 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointToCheck.getLogicX() == lpRaw.getLogicX()) {
                    isAllowedToCheck = false;
                } else {
                    logicPointToCheck = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowedToCheck = true;
                }
                break;
            default:
                isAllowedToCheck = false;
        }
    }

    public LogicPoint getLogicPoint() {
        return LogicPoint.convertPointToLogicPoint(new Point(posX + OFFSET_X, posY + OFFSET_Y));
    }

//    public void checkToChangDirection() {
//        LogicPoint logicMap = this.getLogicPoint();
//        int midLogicMapX = logicMap.getLogicX() + 42;
//        int midLogicMapY = logicMap.getLogicY() + 62;
//        int x = posX + OFFSET_X;
//        int y = posY + OFFSET_Y;
//        if ((x - midLogicMapX) * (x - midLogicMapX) + (y - midLogicMapY) * (y - midLogicMapY) < 5000) {
//            isAllowedToCheck = true;
//        } else isAllowedToCheck = false;
//    }

    public void movebyDirection() {
        Point p = new Point(posX, posY);
        double newRealX = realX, newRealY = realY;
        switch (direction) {
            case UP:
                newRealX = realX + 36 * speed;
                newRealY = realY - 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case DOWN:
                newRealX = realX - 36 * speed;
                newRealY = realY + 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case LEFT:
                newRealX = realX - 36 * speed;
                newRealY = realY - 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case RIGHT:
                newRealX = realX + 36 * speed;
                newRealY = realY + 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;

        }
        this.posX = p.x;
        this.posY = p.y;
        realX = newRealX;
        realY = newRealY;

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    void loadImage() {
        loadImageByColor(this.color);
    }

    private void loadImageByColor(ColorBox c) {
        try {
            switch (c) {
                case RED:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/red_box.png"));
                    break;
                case YELLOW:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/yellow_box.png"));
                    break;
                case PINK:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/pink_box.png"));
                    break;
                case BLUE:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/blue_box.png"));
                    break;
                case GREEN:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/green_box.png"));
                    break;
                case WHITE:
                    this.sprite = ImageIO.read(getClass().getResource("/resource/play button/null_box.png"));
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
