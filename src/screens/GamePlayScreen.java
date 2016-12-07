package screens;


import createmap.MapCodeConst;
import game.GameWindow;
import gameobject.Box;
import gameobject.Conveyor;
import gameobject.ConveyorSwitch;
import gameobject.Direction;
import helper.GamePlayManager;
import helper.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    private GameWindow gameWindow;
    private BufferedImage background, backBtn, pauseBtn, gameoverImg;
    private Rectangle backRect, backgroundRect, pauseRect;

    private GamePlayManager gamePlayManager;

    private int timeLeft;
    private boolean isPaused = false;
    private boolean isWon = false;


    private int highScore = 100;
    private static final int fps = 60;
    private int thisFPS = 0;
    private final Dimension buttonSize = new Dimension(50, 50);
    private final Point pointO = new Point(10, 30);

    public GamePlayScreen(GameWindow gameWindow, File mapFile) {
        this.gameWindow = gameWindow;
        loadImage();
        makeRect();
//        setHighScore();
        gamePlayManager = new GamePlayManager(mapFile);
        this.timeLeft = gamePlayManager.time;
    }

    private void makeRect() {
        backgroundRect = new Rectangle(8, 31, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
        backRect = new Rectangle(1220, pointO.y, this.buttonSize.width, this.buttonSize.height);
        pauseRect = new Rectangle(1000, pointO.y,this.buttonSize.width, this.buttonSize.height);

    }

//    void loadHighScore() {
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader("resource/highscore.hs"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String line = null;
//        try {
//            if (br != null) {
//                line = br.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (line != null)
//            highScore = Integer.parseInt(line);
//
//
//    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("resource/Image/play_background.png"));
            gameoverImg = ImageIO.read(new File("resource/menu button/gameover_icon.png"));
            backBtn = ImageIO.read(new File("resource/Create map button/Button_back.png"));
            pauseBtn = ImageIO.read(new File("resource/menu button/pause.png"));


            pauseBtn = setSize(pauseBtn,this.buttonSize);
            backBtn = setSize(backBtn, this.buttonSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void switchPauseState(){
        if (isPaused) isPaused = false;
        else isPaused = true;
    }

    @Override
    public void update() {
        if (!isPaused&&!isWon) {
            for (Conveyor[] conveyorArray : gamePlayManager.conveyor) {
                for (Conveyor conveyor : conveyorArray) {
                    conveyor.update();
                }
            }
            thisFPS += 1;
            if (thisFPS >= fps) {
                thisFPS = 0;
                if (timeLeft>0) timeLeft--;

            }


//        for (ConveyorSwitch conveyorSwitch : gamePlayManager.conveyorSwitchList) {
//            conveyorSwitch.update();
//        }

//        System.out.println(gamePlayManager.box1.getDirection());
//        gamePlayManager.box1.movebyDirection();
            gamePlayManager.makeBox();
            if (!gamePlayManager.boxOnMapList.isEmpty()) {
                for (Box b : gamePlayManager.boxOnMapList) {
                    gamePlayManager.updateDirectionForBox(b);
                    b.movebyDirection();
                }
                gamePlayManager.checkBoxToEnd();
            }


            if (gamePlayManager.completedBoxes ==gamePlayManager.numberOfBoxes)
                isWon = true;
        }


    }

//    private void setHighScore() {
//        highScore = 60;
//
//        try {
//            File file = new File("/resource/highscore.hs");
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(highScore);
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(background, backgroundRect.x, backgroundRect.y, null);
        if (timeLeft!=0) {
            if(!isWon) {
                if (!isPaused) {
                    for (int sum = 15; sum < 60; sum++) {
                        for (int i = 0; i <= sum; i++) {
                            int j = sum - i;
                            if (i <= 35 && j <= 35) {
                                LogicPoint lp = new LogicPoint(i, j);
                                Point p = lp.convertToPoint();
                                if (gamePlayManager.getDirectionFromMapCode(gamePlayManager.map[i][j]) != Direction.NONE) {
                                    gamePlayManager.conveyor[i][j].draw(g);
                                }
                                switch (gamePlayManager.map[i][j]) {
                                    case MapCodeConst.PLANE:
                                        g.drawImage(gamePlayManager.plane, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.TRUCK:
                                        g.drawImage(gamePlayManager.truck, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.SHIP:
                                        g.drawImage(gamePlayManager.ship, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.TREE:
                                        g.drawImage(gamePlayManager.tree, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.TREE_1:
                                        g.drawImage(gamePlayManager.tree1, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.WATER:
                                        g.drawImage(gamePlayManager.water, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.ROAD:
                                        g.drawImage(gamePlayManager.road, p.x, p.y, null);
                                        break;
                                    case MapCodeConst.SOURCE:
                                        g.drawImage(gamePlayManager.source, p.x - 72, p.y + 18, null);
                                        break;
                                }
                            }
                        }
                    }

                    if (!gamePlayManager.boxOnMapList.isEmpty()) {
                        for (Box b : gamePlayManager.boxOnMapList) {
                            b.draw(g);
                        }
                    }
                }
            }else {
                g.drawString("Ban dep trai nhu Kenny Sang vay",600,250);
                // hien cai gi thi hien o day
                // can luu diem gi luu o day luon
            }
        } else {
            g.drawString("Choi ngu vl thua cmnr",600,250);
            g.drawImage(gameoverImg,600,300,null);
        }


//        System.out.println(gamePlayManager.boxOnMapList.elementAt(0).getColor().toString());
//        g.setColor(Color.BLACK);

        g.drawImage(backBtn, backRect.x, backRect.y, null);
        g.drawImage(pauseBtn, pauseRect.x, pauseRect.y, null);

        g.drawString("Score: " + gamePlayManager.score + "\t Time:" + timeLeft + "\t High Score: " + highScore, 40, 40);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point pointClicked = new Point(e.getX(), e.getY());
//
//        System.out.println("p =" + e.getX() + "," + e.getY());
//        LogicPoint f = LogicPoint.convertPointToLogicPoint(e.getPoint());
//        System.out.println("logic p =" + f.getLogicX() + "," + f.getLogicY());
//


        for (ConveyorSwitch conveyorSwitch : gamePlayManager.conveyorSwitchList) {
            if (conveyorSwitch.clickArea.contains(pointClicked)) {
//                gamePlayManager.getProbableDirectionForAllSwitches();
                conveyorSwitch.changeDirection();
                int x = conveyorSwitch.getLogicPoint().getLogicX();
                int y = conveyorSwitch.getLogicPoint().getLogicY();
                switch (conveyorSwitch.getDirection()) {
                    case UP:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_UP;
                        break;
                    case RIGHT:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_RIGHT;
                        break;
                    case DOWN:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_DOWN;
                        break;
                    case LEFT:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_LEFT;
                        break;


                }
            }

        }
        if (backRect.contains(e.getX(), e.getY())) {
            this.gameWindow.removeMouseListener(this);
            GameManager.getInstance().getStackScreen().pop();
            this.gameWindow.addMouseListener(GameManager.getInstance().getStackScreen().peek());
        }

        if (pauseRect.contains(e.getX(), e.getY())) {
            switchPauseState();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
