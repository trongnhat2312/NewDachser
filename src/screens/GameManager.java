package screens;

import java.util.Stack;

/**
 * Created by Admin on 11/13/2016.
 */
public class GameManager {
    private static GameManager sharePointer = new GameManager();
    private Stack<Screen> stackScreen;

    private GameManager() {
        stackScreen = new Stack<>();
    }

    public static GameManager getInstance() {
        return sharePointer;
    }

    public Stack<Screen> getStackScreen() {
        return stackScreen;
    }
}

