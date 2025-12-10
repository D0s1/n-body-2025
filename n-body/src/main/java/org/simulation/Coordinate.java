package org.simulation;

public class Coordinate {

    private double x;
    private double y;
    public Coordinate(double  x, double y){
        this.x = x;
        this.y = y;
    }

    // This getter, setter methods normally can be automated by using lombok, but for compatibility with some IDEs we
    // won't use it.

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public void addToX(double x){
        this.x += x;
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public void addToY(double y){
        this.y += y;
    }

}
