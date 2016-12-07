import game.GameWindow;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Hoangelato on 01/11/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}