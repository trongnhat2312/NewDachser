package gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoangelato on 20/11/2016.
 */
public class ConveyorYEnd extends ConveyorEnd{
    public ConveyorYEnd(int posX, int posY) {
        super(posX, posY);
        loadImage();
    }

    public void update() {
        super.update();
        //animation.update();
    }

    @Override
    public void loadImage() {
        super.loadImage();
        sprites = new ArrayList<>();
        try {
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/blue_y_end.png")));
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/green_y_end.png")));
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/red_y_end.png")));
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/yellow_y_end.png")));
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/pink_y_end.png")));
            sprites.add(ImageIO.read(new File("resource/conveyor/y_end/white_y_end.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadImageByColor(this.color);
    }

    public void draw(Graphics g) {
        super.draw(g);
//        animation.draw(g,this.posX,this.posY);
    }
}
