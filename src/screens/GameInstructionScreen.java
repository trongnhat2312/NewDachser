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
public class GameInstructionScreen extends Screen{
    private GameWindow gameWindow;
    private BufferedImage instructionScreen;
    private BufferedImage previousButton, nextButton, homeButton;
    private Rectangle nextRect, previousRect, homeRect;
    private BufferedImage[] instruction = new BufferedImage[11];
    private int currentImageNumber = 1;
    private int maxImageNumber = instruction.length;
    private int minImageNumber = 1;

    public GameInstructionScreen(GameWindow gameWindow){

        this.gameWindow = gameWindow;
        this.loadImage();
        this.makeRect();
        instructionScreen = instruction[currentImageNumber - 1];
    }

    private void loadImage(){

        try {
            nextButton = ImageIO.read(getClass().getResource("/resource/instruction button/next button.png"));
            previousButton = ImageIO.read(getClass().getResource("/resource/instruction button/back button.png"));
            homeButton = ImageIO.read(getClass().getResource("/resource/instruction button/home button.png"));

            for (int i = 0; i < instruction.length; i++) {
                instruction[i] = ImageIO.read(getClass().getResource("/resource/instruction button/instrution_" + (i + 1) +".png"));
                instruction[i] = setSize(instruction[i], this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
            }
            previousButton = setSize(previousButton,70, 70);
            nextButton = setSize(nextButton, 70, 70);
            homeButton = setSize(homeButton, 100, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        previousRect = new Rectangle(8 ,
                this.gameWindow.getHeight()/2 - previousButton.getHeight()/2,
                previousButton.getWidth(), previousButton.getHeight());

        nextRect = new Rectangle(this.gameWindow.getWidth() - nextButton.getWidth() - 8,
                this.gameWindow.getHeight()/2 - nextButton.getHeight()/2,
                nextButton.getWidth(),nextButton.getHeight());

        homeRect = new Rectangle(this.gameWindow.getWidth()/2 - homeButton.getWidth()/2,
                this.gameWindow.getHeight() - homeButton.getHeight(),
                homeButton.getWidth(), homeButton.getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(instructionScreen, 0, 0, null);
        if(currentImageNumber > minImageNumber) {
            g.drawImage(previousButton, previousRect.x, previousRect.y, null);
        }
        if (currentImageNumber < maxImageNumber) {
            g.drawImage(nextButton, nextRect.x, nextRect.y, null);
        }
        g.drawImage(homeButton, homeRect.x, homeRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (nextRect.contains(e.getX(),e.getY())){
            if (currentImageNumber < maxImageNumber) {
                currentImageNumber += 1;
                instructionScreen = instruction[currentImageNumber - 1];
            }
        }
        if (previousRect.contains(e.getX(),e.getY())){
            if (currentImageNumber > minImageNumber) {
                currentImageNumber -= 1;
                instructionScreen = instruction[currentImageNumber - 1];
            }
        }

        if (homeRect.contains(e.getX(), e.getY())){
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