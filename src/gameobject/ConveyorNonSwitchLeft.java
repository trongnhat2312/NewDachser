package gameobject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 30/11/2016.
 */
public class ConveyorNonSwitchLeft extends ConveyorNonSwitch {
    protected ConveyorNonSwitchLeft(int posX, int posY) {
        super(posX, posY);
        loadImage();
    }

    @Override
    public void loadImage() {
        super.loadImage();
        try {
            this.sprite = ImageIO.read(getClass().getResource("/resource/conveyor/nonswitch/nonswitch_left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

