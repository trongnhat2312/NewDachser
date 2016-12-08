package gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class ConveyorXEnd extends ConveyorEnd {


    public ConveyorXEnd(int posX, int posY) {
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
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/blue_x_end.png")));
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/green_x_end.png")));
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/red_x_end.png")));
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/yellow_x_end.png")));
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/pink_x_end.png")));
            sprites.add(ImageIO.read(getClass().getResource("/resource/conveyor/x_end/white_x_end.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadImageByColor(this.color);
    }


    public void draw(Graphics g) {
        super.draw(g);
    }

}
