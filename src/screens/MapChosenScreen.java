package screens;

import game.GameWindow;
import com.sun.glass.ui.Size;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nhat on 02/12/2016.
 */
public class MapChosenScreen extends Screen {
    private final Point firstButtonLocation;
    private BufferedImage background;

    private BufferedImage campaignImage, customMapImage, homeImage;
    private Rectangle campaignRect, customMapRect, homeRect;
    private GameWindow gameWindow;
    private final Dimension buttonSize;

    public MapChosenScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(300, this.gameWindow.windowSize.height / 8);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.windowSize.width / 2 - this.buttonSize.width / 2,
                this.gameWindow.windowSize.height / 2 - this.buttonSize.height);
        makeRect();
    }

    private void makeRect() {
        campaignRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y,
                campaignImage.getWidth(), campaignImage.getHeight());
        customMapRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + buttonSize.height,
                customMapImage.getWidth(), customMapImage.getHeight());
        homeRect = new Rectangle(this.gameWindow.getWidth() / 2 - homeImage.getWidth() / 2,
                this.gameWindow.getHeight() - homeImage.getHeight(),
                homeImage.getWidth(), homeImage.getHeight());

    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("resource/image/image 96.bmp"));
            campaignImage = ImageIO.read(new File("resource/menu button/campaign_button.png"));
            customMapImage = ImageIO.read(new File("resource/menu button/custom map_button.png"));
            homeImage = ImageIO.read(new File("resource/instruction button/home button.png"));

            campaignImage = setSize(campaignImage, buttonSize.width, buttonSize.height);
            customMapImage = setSize(customMapImage, buttonSize.width, buttonSize.height);
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
        g.drawImage(campaignImage, campaignRect.x, campaignRect.y, null);
        g.drawImage(customMapImage, customMapRect.x, customMapRect.y, null);
        g.drawImage(homeImage, homeRect.x, homeRect.y, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (campaignRect.contains(e.getX(), e.getY())) {
            // kich vao play
            CampaignScreen campaignScreen = new CampaignScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(campaignScreen);
            GameManager.getInstance().getStackScreen().push(campaignScreen);
        }
        if (customMapRect.contains(e.getX(), e.getY())) {
            JFileChooser chooserMap = new JFileChooser("resource/Map");
            int rVal = chooserMap.showOpenDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                File mapFile = chooserMap.getSelectedFile();
                GamePlayScreen gamePlayScreen = new GamePlayScreen(gameWindow, mapFile);
                gameWindow.removeMouseListener(this);
                gameWindow.addMouseListener(gamePlayScreen);
                GameManager.getInstance().getStackScreen().push(gamePlayScreen);
            }
            return;
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
