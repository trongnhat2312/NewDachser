package createmap;


import helper.LogicPoint;

/**
 * Created by Admin on 11/24/2016.
 */
public class Operation {
    private int code;
    private LogicPoint p1;
    private LogicPoint p2;
    public Operation(int code, LogicPoint p1, LogicPoint p2){
        this.code = code;
        this.p1 = p1;
        this.p2 = p2;
    }
    public int getCode(){
        return this.code;
    }
    public LogicPoint getP1(){
        return this.p1;
    }
    public LogicPoint getP2(){
        return this.p2;
    }
}
