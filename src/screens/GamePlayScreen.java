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
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    private GameWindow gameWindow;
    private BufferedImage background, backBtn, pauseBtn, gameoverImg, stImage, winImage, loseImage;
    private Rectangle backRect, backgroundRect, pauseRect, stRect;

    private GamePlayManager gamePlayManager;

    private int timeLeft;
    private boolean isPaused = false;
    private boolean isWon = false;


    private int highScore = 100;
    private static final int fps = 60;
    private int thisFPS = 0;
    private final Dimension buttonSize = new Dimension(50, 50);
    private final Point pointO = new Point(8, 31);

    public GamePlayScreen(GameWindow gameWindow, File mapFile) {
        this.gameWindow = gameWindow;
        loadImage();
        makeRect();
//        setHighScore();
        gamePlayManager = new GamePlayManager(mapFile);
        this.timeLeft = gamePlayManager.time;
    }

    private void makeRect() {
        backgroundRect = new Rectangle(8, pointO.y, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
        stRect = new Rectangle(8, pointO.y, stImage.getWidth(), stImage.getHeight());
        backRect = new Rectangle(1220, pointO.y, this.buttonSize.width, this.buttonSize.height);
        pauseRect = new Rectangle(this.gameWindow.windowSize.width / 2 - pauseBtn.getWidth() / 2, pointO.y,
                pauseBtn.getWidth(), pauseBtn.getHeight());


    }


    private void loadImage() {
        try {
            background = ImageIO.read(getClass().getResource("/resource/Image/play_background.png"));
            gameoverImg = ImageIO.read(getClass().getResource("/resource/menu button/gameover_icon.png"));
            backBtn = ImageIO.read(getClass().getResource("/resource/Create map button/Button_back.png"));
            pauseBtn = ImageIO.read(getClass().getResource("/resource/menu button/pause.png"));
            stImage = ImageIO.read(getClass().getResource("/resource/play button/st_board.png"));
            winImage = ImageIO.read(getClass().getResource("/resource/play button/win.png"));
            loseImage = ImageIO.read(getClass().getResource("/resource/play button/lose.png"));


            pauseBtn = setSize(pauseBtn, new Dimension(120, 50));
            backBtn = setSize(backBtn, this.buttonSize);
            stImage = setSize(stImage, new Dimension(360, 30));
            loseImage = setSize(loseImage,new Dimension(250, 250));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void switchPauseState() {
        if (isPaused) isPaused = false;
        else isPaused = true;
    }

    @Override
    public void update() {
        if (!isPaused && !isWon) {
            for (Conveyor[] conveyorArray : gamePlayManager.conveyor) {
                for (Conveyor conveyor : conveyorArray) {
                    conveyor.update();
                }
            }
            thisFPS += 1;
            if (thisFPS >= fps) {
                thisFPS = 0;
                if (timeLeft > 0) timeLeft--;

            }
            gamePlayManager.makeBox();
            if (!gamePlayManager.boxOnMapList.isEmpty()) {
                for (Box b : gamePlayManager.boxOnMapList) {
                    gamePlayManager.updateDirectionForBox(b);
                    b.movebyDirection();
                }
                gamePlayManager.checkBoxToEnd();
            }

            if (gamePlayManager.completedBoxes == gamePlayManager.numberOfBoxes)
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
        if (timeLeft != 0) {
            if (!isWon) {
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

                    g.drawImage(pauseBtn, pauseRect.x, pauseRect.y, null);
                }
            } else {
                g.drawImage(winImage, this.gameWindow.getWidth() / 2 - winImage.getWidth() / 2,
                        this.gameWindow.getHeight() / 2 - winImage.getHeight() / 2, null);
            }
        } else {
            g.drawImage(loseImage, this.gameWindow.getWidth() / 2 - loseImage.getWidth() / 2, 150, null);
            g.drawImage(gameoverImg, this.gameWindow.getWidth() / 2 - gameoverImg.getWidth() / 2, 300, null);
        }

        g.drawImage(backBtn, backRect.x, backRect.y, null);
        g.drawImage(stImage, stRect.x, stRect.y, null);

        g.setColor(Color.YELLOW);
        g.drawString("" + gamePlayManager.score, 200, 48);

        g.drawString("" + timeLeft, 310, 48);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point pointClicked = new Point(e.getX(), e.getY());

        for (ConveyorSwitch conveyorSwitch : gamePlayManager.conveyorSwitchList) {
            if (conveyorSwitch.clickArea.contains(pointClicked)) {
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
            int chosen = showConfirmDialog(this, "Continue?", "Pause", OK_CANCEL_OPTION, QUESTION_MESSAGE);
            if (chosen == OK_OPTION) {
                switchPauseState();
            } else {
                this.gameWindow.removeMouseListener(this);
                GameManager.getInstance().getStackScreen().pop();
                this.gameWindow.addMouseListener(GameManager.getInstance().getStackScreen().peek());
            }
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
