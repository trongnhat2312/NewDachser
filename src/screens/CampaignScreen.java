package screens;

import game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nhat on 02/12/2016.
 */
public class CampaignScreen extends Screen {

    private final Point firstButtonLocation;
    private BufferedImage background, level_1_Image, level_2_Image, level_3_Image, level_4_Image, level_5_Image, homeImage;

    private Rectangle level_1_Rect;
    private Rectangle level_2_Rect;
    private Rectangle level_3_Rect;
    private Rectangle level_4_Rect;
    private Rectangle level_5_Rect;
    private Rectangle homeRect;

    private GameWindow gameWindow;
    private final Dimension buttonSize;

    public CampaignScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(200, 200);
        loadImage();
        firstButtonLocation = new Point((this.gameWindow.windowSize.width / 2) - 5 * this.buttonSize.width / 2 - 40,
                this.gameWindow.windowSize.height / 2 - this.buttonSize.height / 2);
        makeRect();
    }

    private void makeRect() {
        level_1_Rect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y,
                level_1_Image.getWidth(), level_1_Image.getHeight());
        level_2_Rect = new Rectangle(firstButtonLocation.x + buttonSize.width + 20, firstButtonLocation.y,
                level_1_Image.getWidth(), level_1_Image.getHeight());
        level_3_Rect = new Rectangle(firstButtonLocation.x + 2 * (buttonSize.width + 20), firstButtonLocation.y,
                level_1_Image.getWidth(), level_1_Image.getHeight());
        level_4_Rect = new Rectangle(firstButtonLocation.x + 3 * (buttonSize.width + 20), firstButtonLocation.y,
                level_1_Image.getWidth(), level_1_Image.getHeight());
        level_5_Rect = new Rectangle(firstButtonLocation.x + 4 * (buttonSize.width + 20), firstButtonLocation.y,
                level_1_Image.getWidth(), level_1_Image.getHeight());

        homeRect = new Rectangle(this.gameWindow.getWidth() / 2 - homeImage.getWidth() / 2,
                this.gameWindow.getHeight() - homeImage.getHeight(),
                homeImage.getWidth(), homeImage.getHeight());

    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("resource/image/image 96.bmp"));
            level_1_Image = ImageIO.read(new File("resource/play button/level_01.png"));
            level_2_Image = ImageIO.read(new File("resource/play button/level_02.png"));
            level_3_Image = ImageIO.read(new File("resource/play button/level_03.png"));
            level_4_Image = ImageIO.read(new File("resource/play button/level_04.png"));
            level_5_Image = ImageIO.read(new File("resource/play button/level_05.png"));
            homeImage = ImageIO.read(new File("resource/instruction button/home button.png"));

            level_1_Image = setSize(level_1_Image, buttonSize.width, buttonSize.height);
            level_2_Image = setSize(level_2_Image, buttonSize.width, buttonSize.height);
            level_3_Image = setSize(level_3_Image, buttonSize.width, buttonSize.height);
            level_4_Image = setSize(level_4_Image, buttonSize.width, buttonSize.height);
            level_5_Image = setSize(level_5_Image, buttonSize.width, buttonSize.height);
            homeImage = setSize(homeImage, 100, 100);
            background = setSize(background, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(level_1_Image, level_1_Rect.x, level_1_Rect.y, null);
        g.drawImage(level_2_Image, level_2_Rect.x, level_2_Rect.y, null);
        g.drawImage(level_3_Image, level_3_Rect.x, level_3_Rect.y, null);
        g.drawImage(level_4_Image, level_4_Rect.x, level_4_Rect.y, null);
        g.drawImage(level_5_Image, level_5_Rect.x, level_5_Rect.y, null);

        g.drawImage(homeImage, homeRect.x, homeRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (level_1_Rect.contains(e.getX(), e.getY())) {
            File mapFile = new File("resource/CampaignMap/map1.pam");
            GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gamePlayScreen);
            GameManager.getInstance().getStackScreen().push(gamePlayScreen);
        }
        if (level_2_Rect.contains(e.getX(), e.getY())) {
            File mapFile = new File("resource/CampaignMap/map2.pam");
            GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gamePlayScreen);
            GameManager.getInstance().getStackScreen().push(gamePlayScreen);
        }
        if (level_3_Rect.contains(e.getX(), e.getY())) {
            File mapFile = new File("resource/CampaignMap/map3.pam");
            GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gamePlayScreen);
            GameManager.getInstance().getStackScreen().push(gamePlayScreen);
        }
        if (level_4_Rect.contains(e.getX(), e.getY())) {
            File mapFile = new File("resource/CampaignMap/map4.pam");
            GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gamePlayScreen);
            GameManager.getInstance().getStackScreen().push(gamePlayScreen);
        }
        if (level_5_Rect.contains(e.getX(), e.getY())) {
            File mapFile = new File("resource/CampaignMap/map5.pam");
            GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gamePlayScreen);
            GameManager.getInstance().getStackScreen().push(gamePlayScreen);
        }
        if (homeRect.contains(e.getX(), e.getY())) {
            System.out.println("click home");
            this.gameWindow.removeMouseListener(this);
            GameManager.getInstance().getStackScreen().pop();
            this.gameWindow.addMouseListener(GameManager.getInstance().getStackScreen().peek());
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
