package gameobject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 28/11/2016.
 */
public class ConveyorNonSwitchRight extends ConveyorNonSwitch {
    protected ConveyorNonSwitchRight(int posX, int posY) {
        super(posX, posY);
        loadImage();
    }

    @Override
    public void loadImage() {
        super.loadImage();
        try {
            this.sprite = ImageIO.read(new File("resource/conveyor/nonswitch/nonswitch_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

