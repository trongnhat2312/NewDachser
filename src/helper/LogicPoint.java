package helper;

import java.awt.*;

/**
 * Created by Nhat on 23/11/2016.
 */
public class LogicPoint {
    public static final int baseX = 620;
    public static final int baseY = -275;
    public static final int offsetX = 42;
    public static final int offsetY = 44;
    private int logicX;
    private int logicY;

    public static LogicPoint convertPointToLogicPoint(Point point) {
        int x = point.x;
        int y = point.y;
        LogicPoint logicPoint = new LogicPoint(point.x, point.y);
        logicPoint.logicX = (2 * (y - baseY) + (x - baseX)) / 72;
        logicPoint.logicY = (2 * (y - baseY) - (x - baseX)) / 72;
        return logicPoint;
    }

    public LogicPoint() {
        this(0, 0);
    }

    public LogicPoint(int logicX, int logicY) {
        this.logicX = logicX;
        this.logicY = logicY;
    }

    public Point convertToPoint() {
        int baseImageX = baseX - offsetX;
        int baseImageY = baseY - offsetY;
        Point point = new Point();

        point.x = baseImageX + 36 * (logicX - logicY);
        point.y = baseImageY + 18 * (logicX + logicY);
        return point;
    }

    public int getLogicX() {
        return logicX;
    }

    public int getLogicY() {
        return logicY;
    }


//    public static void main(String[] args) {
//        int a = 200;
//        int b = 300;
//        Point p = new Point(a,b);
//        LogicPoint lp = LogicPoint.convertPointToLogicPoint(p);
//        System.out.println("convert(" + a + "," + b + ")" + lp.logicX + "," + lp.logicY);
//    }
}
