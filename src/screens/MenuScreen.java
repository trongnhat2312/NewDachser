package screens;

import game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/13/2016.
 */
public class MenuScreen extends Screen {
    private BufferedImage background, playImage, instructionImage, aboutImage, exitImage, createmapImage;
    private Rectangle playRect, instructionRect, aboutRect, exitRect, createmapRect;
    private GameWindow gameWindow;
    private final Point firstButtonLocation;
    private final Dimension buttonSize;

    public MenuScreen(GameWindow gameWindow) throws IOException {
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(300, this.gameWindow.windowSize.height / 8);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.windowSize.width / 2 - this.buttonSize.width / 2,
                this.gameWindow.windowSize.height / 2 - 5 * this.buttonSize.height / 2);
        makeRect();

    }

    private void loadImage() {
        try {
            playImage = ImageIO.read(getClass().getResource("/resource/menu button/play button.png"));
            createmapImage = ImageIO.read(getClass().getResource("/resource/menu button/create map button.png"));
            instructionImage = ImageIO.read(getClass().getResource("/resource/menu button/instruction button.png"));
            aboutImage = ImageIO.read(getClass().getResource("/resource/menu button/about button.png"));
            exitImage = ImageIO.read(getClass().getResource("/resource/menu button/exit button.png"));
            background = ImageIO.read(getClass().getResource("/resource/image/image 96.bmp"));


            playImage = setSize(playImage, buttonSize.width, buttonSize.height);
            createmapImage = setSize(createmapImage, buttonSize.width, buttonSize.height);
            instructionImage = setSize(instructionImage, buttonSize.width, buttonSize.height);
            aboutImage = setSize(aboutImage, buttonSize.width, buttonSize.height);
            exitImage = setSize(exitImage, buttonSize.width, buttonSize.height);
            background = setSize(background, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect() {

        playRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y, playImage.getWidth(), playImage.getHeight());
        createmapRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + buttonSize.height, playImage.getWidth(), playImage.getHeight());
        instructionRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 2 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
        aboutRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 3 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
        exitRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 4 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
    }

    @Override
    public void update() {
        //if click play push Gameplay Screen

        //if click about push About Screen
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(playImage, playRect.x, playRect.y, null);
        g.drawImage(createmapImage, createmapRect.x, createmapRect.y, null);
        g.drawImage(instructionImage, instructionRect.x, instructionRect.y, null);
        g.drawImage(aboutImage, aboutRect.x, aboutRect.y, null);
        g.drawImage(exitImage, exitRect.x, exitRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(), e.getY())) {
            // kich vao play
            MapChosenScreen mapChosenScreen = new MapChosenScreen(gameWindow);

            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(mapChosenScreen);
            GameManager.getInstance().getStackScreen().push(mapChosenScreen);
        }
        if (createmapRect.contains(e.getX(), e.getY())) {
            CreateMapScreen createMapScreen = new CreateMapScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(createMapScreen);
            GameManager.getInstance().getStackScreen().push(createMapScreen);
        }
        if (aboutRect.contains(e.getX(), e.getY())) {
            //kich vao about
            AboutScreen aboutScreen = new AboutScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(aboutScreen);
            GameManager.getInstance().getStackScreen().push(aboutScreen);
//            System.out.println(GameManager.getInstance().getStackScreen().size());
        }
        if (instructionRect.contains(e.getX(), e.getY())) {
            //kich vao instruction
            GameInstructionScreen gameInstructionScreen = new GameInstructionScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(gameInstructionScreen);
            GameManager.getInstance().getStackScreen().push(gameInstructionScreen);
//            System.out.println(GameManager.getInstance().getStackScreen().size());
        }
        if (exitRect.contains(e.getX(), e.getY())) {
            //kich vao exit
            System.exit(1);
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
