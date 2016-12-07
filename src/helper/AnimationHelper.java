package helper;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoangelato on 16/11/2016.
 */
public class AnimationHelper {
    private ArrayList<BufferedImage> frames = new ArrayList<>();
    private int index = 0;
    private int countTime = 0;
    private int time;

    public AnimationHelper(String imagestring, int time, int imageNumber){
        this.time = time;
        try {
            for (int i = 1; i < imageNumber+1; i++){
                frames.add(ImageIO.read(new File(imagestring+i+").png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(){
        countTime += 17;
        if (countTime > this.time) {
            countTime = 0;
            if (index == frames.size() - 1) {
                index = 0;
            } else {
                index++;
            }
        }

    }
    public void draw(Graphics g, int posX, int posY){
        g.drawImage(frames.get(index), posX, posY, null);
    }
}
