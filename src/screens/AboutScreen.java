package screens;

import game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/14/2016.
 */
public class AboutScreen extends Screen {
    private GameWindow gameWindow;
    private BufferedImage background;
    private BufferedImage homeButton;

    private Rectangle homeRect;

    public AboutScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.loadImage();
        this.makeRect();
    }

    private void loadImage() {

        try {
            background = ImageIO.read(getClass().getResource("/resource/instruction button/about_screen.png"));
            homeButton = ImageIO.read(getClass().getResource("/resource/instruction button/home button.png"));

            background = setSize(background, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
            homeButton = setSize(homeButton, 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect() {

        homeRect = new Rectangle(this.gameWindow.getWidth() / 2 - homeButton.getWidth() / 2,
                this.gameWindow.getHeight() - homeButton.getHeight(),
                homeButton.getWidth(), homeButton.getHeight());

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(homeButton, homeRect.x, homeRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (homeRect.contains(e.getX(), e.getY())) {
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
