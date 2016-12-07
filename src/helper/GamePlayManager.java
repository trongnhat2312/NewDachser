package helper;

/**
 * Created by admin on 12/2/2016.
 */



import gameobject.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static createmap.MapCodeConst.*;
import static gameobject.ColorBox.*;


/**
 * Created by Hoangelato on 01/12/2016.
 */
public class GamePlayManager {
    public Conveyor[][] conveyor = new Conveyor[36][36];
    public Vector<ConveyorSwitch> conveyorSwitchList = new Vector<ConveyorSwitch>();
    public Vector<ConveyorEnd> validConveyorEndList = new Vector<ConveyorEnd>();
    public BufferedImage truck, plane, ship, tree, tree1, water, road, source;

    public int map[][];
    private int maxNumberOfBoxesOnMap = 0; //tuy thuoc vao so End con can nhan box
    private int numberOfBoxes;
    public int time;
    private int level;
    int nextBoxTime = 2000;
    int countTime = 0;

    Random random = new Random();

    public Stack<Box> boxWaitingList = new Stack<Box>();
    public Vector<Box> boxOnMapList = new Vector<Box>();
    //
    public int score = 0;
    LogicPoint startBoxLogicPoint;


    public GamePlayManager(File mapFile) {
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                conveyor[i][j] = new Conveyor(0, 0);
            }
        }

        loadMap(mapFile);
        getImagesForMap();


    }

    public static ColorBox convertIndexToColor(int index) {
        switch (index) {
            case 0:
                return BLUE;
            case 1:
                return GREEN;
            case 2:
                return RED;
            case 3:
                return YELLOW;
            case 4:
                return PINK;
            default:
                return WHITE;
        }

    }


    public void update() {
    }

    public void getImagesForMap() {
        try {
            truck = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
            plane = ImageIO.read(new File("resource/Create map button/Map_plane.png"));
            ship = ImageIO.read(new File("resource/Create map button/Map_ship.png"));
            tree = ImageIO.read(new File("resource/Create map button/Map_tree.png"));
            tree1 = ImageIO.read(new File("resource/Create map button/Map_tree_1.png"));
            water = ImageIO.read(new File("resource/Create map button/Map_water.png"));
            road = ImageIO.read(new File("resource/Create map button/Map_road.png"));
            source = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(File mapFile) {
        map = new int[36][36];
        try {

            FileReader f = new FileReader(mapFile);
            BufferedReader reader = new BufferedReader(f);

            time = Integer.parseInt(reader.readLine());
            numberOfBoxes = Integer.parseInt(reader.readLine());
            level = Integer.parseInt(reader.readLine());
            int j = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                for (int i = 0; i < lineSplit.length; i++) {
                    map[j][i] = Integer.parseInt(lineSplit[i]);
                }
                line = reader.readLine();
                j++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int sum = 15; sum <= 60; sum++) {
            for (int i = 0; i <= sum; i++) {
                int j = sum - i;
                if ((i <= 35) && (j <= 35)) {
                    LogicPoint lp = new LogicPoint(i, j);
                    Point point = lp.convertToPoint();
                    if (map[i][j] != 0 && map[i][j] != 100) {
                        if (getDirectionFromMapCode(map[i][j]) != Direction.NONE) {
                            Conveyor conveyorToAdd = getConveyorFromMapCode(map[i][j], point.x, point.y);
                            conveyor[i][j] = conveyorToAdd;
                            if (isSwitch(conveyorToAdd)) conveyorSwitchList.add((ConveyorSwitch) conveyorToAdd);
//                            conveyor.add(conveyorToAdd);
                            if (isEnd(conveyorToAdd) && isValidEnd(i, j)) {
                                validConveyorEndList.add((ConveyorEnd) conveyorToAdd);
                            }


                        }
                    }
                    if (map[i][j] == SOURCE) startBoxLogicPoint = startBoxLogicPoint(i, j);
                }
            }
        }

        getProbableDirectionForAllSwitches();

        startBoxManager();

    }

    private LogicPoint startBoxLogicPoint(int i, int j) {
        if (map[i][j - 1] == CONVEYOR_UP) {
            return new LogicPoint(i, j - 1);
        }
        if (map[i][j + 1] == CONVEYOR_DOWN) {
            return new LogicPoint(i, j + 1);
        }
        if (map[i + 1][j] == CONVEYOR_RIGHT) {
            return new LogicPoint(i + 1, j);
        }
        if (map[i - 1][j] == CONVEYOR_LEFT) {
            return new LogicPoint(i - 1, j);
        }
        return new LogicPoint();

    }

    public void getProbableDirectionForAllSwitches() {
        for (ConveyorSwitch conveyorSwitch : conveyorSwitchList) {
            getProbableDirection(conveyorSwitch);
        }
    }

    private void startBoxManager() {
        for (int i = 0; i < numberOfBoxes; i++) {
            int r = random.nextInt(5);
            ColorBox colorBox = GamePlayManager.convertIndexToColor(r);
            int index = i % validConveyorEndList.size();
            validConveyorEndList.get(index).addToStack(colorBox);
        }

        for (ConveyorEnd conveyorEnd : validConveyorEndList) {
            if (conveyorEnd.getStackColor().empty()) {
                conveyorEnd.setColor(WHITE);
            } else {
                conveyorEnd.setColor(conveyorEnd.getStackColor().peek());
            }
        }
        Collections.shuffle(validConveyorEndList);
        for (ConveyorEnd conveyorEnd : validConveyorEndList) {
            if (conveyorEnd.getColor() != WHITE) {
                int mapCode = map[startBoxLogicPoint.getLogicX()][startBoxLogicPoint.getLogicY()];
                Box box = new Box(startBoxLogicPoint, conveyorEnd.getColor(), level, getDirectionFromMapCode(mapCode));
                boxWaitingList.push(box);
                maxNumberOfBoxesOnMap++;
            }
        }
        for (Box box : boxWaitingList) {
            updateDirectionForBox(box);
        }
    }

    private boolean isExistPTS(int mapCode) {
        if (mapCode == TRUCK || mapCode == PLANE || mapCode == SHIP || mapCode == FORBIDDEN) return true;
        return false;
    }

    private boolean isValidEnd(int i, int j) {
        switch (map[i][j]) {
            case END_DOWN:
                return isExistPTS(map[i][j + 1]);
            case END_UP:
                return isExistPTS(map[i][j - 1]);
            case END_LEFT:
                return isExistPTS(map[i - 1][j]);
            case END_RIGHT:
                return isExistPTS(map[i + 1][j]);
            default:
                return false;
        }
    }
//    public void getImagesForMap() {
//        try {
//            truck = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
//            plane = ImageIO.read(new File("resource/Create map button/Map_plane.png"));
//            ship = ImageIO.read(new File("resource/Create map button/Map_ship.png"));
//            tree = ImageIO.read(new File("resource/Create map button/Map_tree.png"));
//            tree1 = ImageIO.read(new File("resource/Create map button/Map_tree_1.png"));
//            water = ImageIO.read(new File("resource/Create map button/Map_water.png"));
//            road = ImageIO.read(new File("resource/Create map button/Map_road.png"));
//            source = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void makeBox() {
        countTime += 17;

//        int makeBoxTime = rand.nextInt()*2000 + 3000;
//        while (countTime <= makeBoxTime){

//        }

        if (!boxWaitingList.isEmpty() && boxOnMapList.size() < maxNumberOfBoxesOnMap && countTime > nextBoxTime) {
            boxOnMapList.add(boxWaitingList.pop());
            countTime = 0;
            nextBoxTime = (int) (Math.round(Math.random() * 2000) + 3000);
        }
    }

    public void updateDirectionForBox(Box box) {
        if (box.isAllowedToCheck) {
            box.setDirection(getDirectionFromMapCode(map[box.getLogicPoint().getLogicX()][box.getLogicPoint().getLogicY()]));
        }
        box.checkToChangDirection();

    }

    public void checkBoxToEnd() {
        for (int i = 0; i < boxOnMapList.size(); i++) {
            Box box = boxOnMapList.get(i);
            LogicPoint position = box.getLogicPoint();
            int mapCode = map[position.getLogicX()][position.getLogicY()];
            switch (mapCode) {
                case END_DOWN:
                case END_LEFT:
                case END_RIGHT:
                case END_UP:
                    ConveyorEnd conveyorEnd = (ConveyorEnd) conveyor[position.getLogicX()][position.getLogicY()];
                    if (box.getColor() == conveyorEnd.getColor()) {
                        boxOnMapList.removeElementAt(i);
                        score += 10;
                        System.out.println("cong diem");
                        ColorBox nextColor = conveyorEnd.nextColorNeedToReceive();
                        if (nextColor != WHITE) {
                            boxWaitingList.push(new Box(startBoxLogicPoint, nextColor, level, getDirectionFromMapCode(mapCode)));
                        } else {
                            maxNumberOfBoxesOnMap--;
                        }
                    } else {
                        boxWaitingList.push(new Box(startBoxLogicPoint, box.getColor(), level, getDirectionFromMapCode(mapCode)));
                        if (score >= 10) {
                            score -= 10;
                        }
                        boxOnMapList.removeElementAt(i);

                    }

//                    for (ConveyorEnd conveyorEnd : validConveyorEndList) {
//                        LogicPoint conveyorPosition = conveyorEnd.getLogicPoint();
//                        if ((conveyorPosition.getLogicX() == position.getLogicX())
//                                && (conveyorPosition.getLogicY() == position.getLogicY())) {
//                            if(box.getColor() == conveyorEnd.getColor()) {
//                                boxOnMapList.removeElementAt(i);
//                                score += 100;
//                                ColorBox nextColor = conveyorEnd.nextColorNeedToReceive();
//                                if (nextColor != WHITE) {
//                                    boxWaitingList.push(new Box(startBoxLogicPoint, nextColor, level));
//                                }
//                            }else {
//                                boxOnMapList.removeElementAt(i);
//                            }
//                        }
            }
        }
//            if (box.isAllowedToCheck) {
//                box.setDirection(getDirectionFromMapCode(map[box.getLogicPoint().getLogicX()][box.getLogicPoint().getLogicY()]));
//            }
//            box.checkToChangDirection();
//        }
    }


    private boolean isSwitch(Conveyor c) {
        if (c instanceof ConveyorSwitch) return true;
        return false;
    }

    private boolean isEnd(Conveyor c) {
        if ((c instanceof ConveyorXEnd) || (c instanceof ConveyorYEnd)) return true;
        return false;
    }


    void getProbableDirection(ConveyorSwitch conveyorSwitch) {
        ArrayList<Direction> returnList = new ArrayList<Direction>();

        int logicX = conveyorSwitch.getLogicPoint().getLogicX();
        int logicY = conveyorSwitch.getLogicPoint().getLogicY();

        boolean[] isValidDirection = new boolean[4];

        int[] neighborsMapCode = new int[4];
        neighborsMapCode[0] = map[logicX][logicY - 1];
        neighborsMapCode[1] = map[logicX + 1][logicY];
        neighborsMapCode[2] = map[logicX][logicY + 1];
        neighborsMapCode[3] = map[logicX - 1][logicY];

        for (int i = 0; i < 4; ++i) {
            if (getDirectionFromMapCode(neighborsMapCode[i]) == Direction.NONE) {
                isValidDirection[i] = false;
            } else if (getDirectionFromMapCode(neighborsMapCode[i]) == Conveyor.convertIndexToDirection((i + 2) % 4)) {
                isValidDirection[i] = false;
            } else isValidDirection[i] = true;
        }

        for (int i = 0; i < 4; i++) {
            if (isValidDirection[i]) {
                returnList.add(Conveyor.convertIndexToDirection(i));
            }
        }

        conveyorSwitch.probableDirections = returnList;
        //System.out.println(returnList);
    }

    private Conveyor getConveyorFromMapCode(int mapCode, int posX, int posY) {
        switch (mapCode) {
            case CONVEYOR_UP:
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
            case CONVEYOR_DOWN:
                return new ConveyorMoving(posX, posY).getConveyorByType(getConveyorTypeFromMapcode(mapCode));
            case NONSWITCH_DOWN:
            case NONSWITCH_LEFT:
            case NONSWITCH_RIGHT:
            case NONSWITCH_UP:
                return new ConveyorNonSwitch(posX, posY).getConveyorNonSwitchByDirection(getDirectionFromMapCode(mapCode));
            case SWITCH_DOWN:
            case SWITCH_LEFT:
            case SWITCH_UP:
            case SWITCH_RIGHT:
                return new ConveyorSwitch(posX, posY, getDirectionFromMapCode(mapCode));
            case END_DOWN:
            case END_UP:
                return new ConveyorYEnd(posX, posY);
            case END_LEFT:
            case END_RIGHT:
                return new ConveyorXEnd(posX, posY);
        }
        return new Conveyor(posX, posY);
    }

    public Direction getDirectionFromMapCode(int mapCode) {
        switch (mapCode) {
            case NONSWITCH_DOWN:
            case SWITCH_DOWN:
            case CONVEYOR_DOWN:
            case END_DOWN:
                return Direction.DOWN;
            case NONSWITCH_LEFT:
            case SWITCH_LEFT:
            case CONVEYOR_LEFT:
            case END_LEFT:
                return Direction.LEFT;
            case NONSWITCH_RIGHT:
            case SWITCH_RIGHT:
            case CONVEYOR_RIGHT:
            case END_RIGHT:
                return Direction.RIGHT;
            case NONSWITCH_UP:
            case SWITCH_UP:
            case CONVEYOR_UP:
            case END_UP:
                return Direction.UP;
            default:
                return Direction.NONE;
        }

    }

    private int getConveyorTypeFromMapcode(int mapCode) {
        switch (mapCode) {
            case CONVEYOR_UP:
            case CONVEYOR_DOWN:
                return ConveyorMoving.TYPE_Y_MID;
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
                return ConveyorMoving.TYPE_X_MID;
            case END_UP:
            case END_DOWN:
                return ConveyorEnd.TYPE_Y_END;
            case END_LEFT:
            case END_RIGHT:
                return ConveyorEnd.TYPE_X_END;
            default:
                return 0;
        }
    }


}
