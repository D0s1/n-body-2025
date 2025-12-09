package org.simulation;

public class Movement {

    private final Coordinate directedMovement = new Coordinate(0,0);
    public Movement() {
    }

    public void pushInDirection(double x, double y){
        directedMovement.addToX(x);
        directedMovement.addToY(y);
    }

    public double getXMovement(){
        return directedMovement.getX();
    }

    public double getYMovement(){
        return directedMovement.getY();
    }

    public void reverseXMovement(){
        directedMovement.setX(-directedMovement.getX());
    }

    public void reverseYMovement(){
        directedMovement.setY(-directedMovement.getY());
    }

    public void setXMovement(double x){
        directedMovement.setX(x);
    }

    public void setYMovement(double y){
        directedMovement.setY(y);
    }

}
