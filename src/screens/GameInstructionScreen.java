package screens;

import game.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    private BufferedImage background;
    private BufferedImage backButton, nextButton, homeButton;
    private BufferedImage guideBox;
    private BufferedImage[] instruction = new BufferedImage[10];
    private int currentImageNumber = 1;
    private int maxImageNumber = 3;
    private int minImageNumber = 1;

    Rectangle nextRect, previousRect, homeRect;
    private final Dimension guideSize = new Dimension(500,500); // size cua man hinh huong dan
    private final Rectangle box;   // vi tri cua man hinh con huong dan
    public GameInstructionScreen(GameWindow gameWindow){
        JPanel jpanel = new JPanel();
        jpanel.setPreferredSize(new Dimension(200, 50));
        jpanel.setBackground(Color.blue);
        this.add(jpanel, BorderLayout.NORTH);
        this.gameWindow = gameWindow;
        box = new Rectangle(this.gameWindow.windowSize.width / 2 - guideSize.width / 2 ,
                this.gameWindow.windowSize.height / 2 - guideSize.height / 2, guideSize.width,guideSize.height);
        this.loadImage();
        this.makeRect();
    }

    private void loadImage(){

        try {
            background = ImageIO.read(new File("resource/instruction button/instruction background.png"));
            nextButton = ImageIO.read(new File("resource/instruction button/next button.png"));
            backButton = ImageIO.read(new File("resource/instruction button/back button.png"));
            homeButton = ImageIO.read(new File("resource/instruction button/home button.png"));

            instruction[1] = ImageIO.read(new File("resource/instruction button/instruction1.png"));
            instruction[2] = ImageIO.read(new File("resource/instruction button/instruction2.png"));
            instruction[3] = ImageIO.read(new File("resource/instruction button/instruction3.png"));

            background = setSize(background, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
            backButton = setSize(backButton,50, 50);
            nextButton = setSize(nextButton, 50, 50);
            homeButton = setSize(homeButton, 100, 100);
            guideBox = setSize(instruction[1],box.width,box.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        previousRect = new Rectangle(box.x ,box.y + guideBox.getHeight(), backButton.getWidth(), backButton.getHeight());
        nextRect = new Rectangle(box.x + guideBox.getWidth() - nextButton.getWidth(), box.y + guideBox.getHeight(),nextButton.getWidth(),nextButton.getHeight());
        homeRect = new Rectangle(this.gameWindow.getWidth()/2 - homeButton.getWidth()/2, this.gameWindow.getHeight() - homeButton.getHeight(), homeButton.getWidth(), homeButton.getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(guideBox, box.x , box.y, null);
        g.drawImage(backButton, previousRect.x, previousRect.y, null);
        g.drawImage(nextButton, nextRect.x, nextRect.y, null);
        g.drawImage(homeButton, homeRect.x, homeRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (nextRect.contains(e.getX(),e.getY())){
            if (currentImageNumber < maxImageNumber) {
                currentImageNumber += 1;
                guideBox = setSize(instruction[currentImageNumber],box.width, box.height);
            }
        }
        if (previousRect.contains(e.getX(),e.getY())){
            if (currentImageNumber > minImageNumber) {
                currentImageNumber -= 1;
                guideBox = setSize(instruction[currentImageNumber],box.width, box.height);
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