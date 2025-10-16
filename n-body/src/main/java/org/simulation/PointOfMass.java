package org.simulation;

import java.util.ArrayList;
import java.util.List;

public class PointOfMass {

    private final Coordinate coordinate;
    private final Movement movement = new Movement() ;
    private double weight;
    // We use g = 1 for simulation purposes, in reality it would be 9.81
    private double g = 1;
    private final double sizeOfSystem = 1000;
    private final List<PointOfMass> canBeMergedWith = new ArrayList<>();
    public boolean willBeMerged = false;

    public PointOfMass(double xCord, double yCord, double xSpeed, double ySpeed,double weight){
        this.weight = weight;
        movement.setXMovement(xSpeed);
        movement.setYMovement(ySpeed);
        coordinate = new Coordinate(xCord, yCord);
    }

    public double getWeight() {
        return weight;
    }

    public double getSize(){
         // We calculate with density of 1/300 for simulation
        double size = Math.cbrt((3 * weight * 300) / (4 * Math.PI));
        return Math.cbrt((3 * weight * 300) / (4 * Math.PI));
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public Movement getMovement(){
        return movement;
    }

    public void calculateMovementWithOtherPoint(PointOfMass pointToCalculateWith){
        double distance = getDistance(pointToCalculateWith.getCoordinate());
        double acceleration  = distance == 0 ? 0 : g * pointToCalculateWith.getWeight() / Math.pow(distance,2);
        double xDistance = pointToCalculateWith.getCoordinate().getX() - coordinate.getX();
        double yDistance = pointToCalculateWith.getCoordinate().getY() - coordinate.getY();
        double xDirection = xDistance == 0 ? 0:  xDistance / distance;
        double yDirection = yDistance == 0 ? 0 : yDistance / distance;
        movement.pushInDirection(xDirection*acceleration, yDirection*acceleration);
    }

    public double getDistance(Coordinate coordinateOfOtherPoint){
        return Math.sqrt(Math.pow(coordinateOfOtherPoint.getX() -
                coordinate.getX(),2) + Math.pow(coordinateOfOtherPoint.getY() - coordinate.getY(),2));
    }

    public void updatePos(){
        canBeMergedWith.clear();
        double newX = coordinate.getX()+movement.getXMovement();
        updateWithinSystem(newX, true);
        double newY = coordinate.getY() + movement.getYMovement();
        updateWithinSystem(newY, false);
    }


    public void canBeMerged(PointOfMass pointToMerge){
        double distance = getDistance(pointToMerge.getCoordinate());
        boolean canBeMerged = (this.getSize() + pointToMerge.getSize())> distance;
        if (canBeMerged){
            canBeMergedWith.add(pointToMerge);
        }
    }

    public void merge(){
        if (!willBeMerged){
            canBeMergedWith.forEach(this::mergePoints);
        }
    }
    public void mergePoints(PointOfMass pointToMerge){
        if (!pointToMerge.willBeMerged && !this.equals(pointToMerge)) {
            pointToMerge.willBeMerged = true;
            pointToMerge.canBeMergedWith.forEach(this::mergePoints);
            calculateMomentumAfterMerge(pointToMerge);
            calculateNewPositionAfterMerge(pointToMerge);
            weight += pointToMerge.getWeight();
        }
    }

    private void calculateMomentumAfterMerge(PointOfMass pointToMerge) {
        movement.setXMovement((movement.getXMovement() * weight + pointToMerge.getMovement().getXMovement() * pointToMerge.weight) /
                (weight +pointToMerge.getWeight()));
        movement.setYMovement((movement.getYMovement() * weight + pointToMerge.getMovement().getYMovement() * pointToMerge.weight) /
                (weight +pointToMerge.getWeight()));

    }

    private void calculateNewPositionAfterMerge(PointOfMass pointToMerge) {
        coordinate.setX((weight * coordinate.getX() + pointToMerge.weight* pointToMerge.getCoordinate().getX()) /
                (weight +pointToMerge.getWeight()));
        coordinate.setY((weight * coordinate.getY() + pointToMerge.weight* pointToMerge.getCoordinate().getY()) /
                (weight +pointToMerge.getWeight()));
    }

    private void updateWithinSystem(double newCord, boolean isX) {
        while (newCord > sizeOfSystem || newCord<0){
            if (isX) {
                movement.reverseXMovement();
            } else {
                movement.reverseYMovement();
            }
            newCord = (newCord > sizeOfSystem) ? sizeOfSystem * 2 - newCord : -newCord;
        }
        if (isX) {
            coordinate.setX(newCord);
        } else {
            coordinate.setY(newCord);
        }
    }


}
