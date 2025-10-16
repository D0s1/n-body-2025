package org.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.*;

public class Model {
    public List<PointOfMass> pointsOfMass = new ArrayList<>();
    private final GraphicInterface graphics = new GraphicInterface();

    public Model(int numOfPoints, int minWeight, int maxWeight) {
        createModel(numOfPoints, minWeight, maxWeight);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::simulateModel, 0, 4, TimeUnit.MILLISECONDS);
    }

    private void createModel(int numOfPoints, int minWeight, int maxWeight) {
        for (int i = 0; i < numOfPoints; i++) {
            pointsOfMass.add(new PointOfMass(Utils.generateRandomValue(0, 1000), Utils.generateRandomValue(0, 1000),
                    Utils.generateRandomValue(-1, 1),Utils.generateRandomValue(-1, 1),
                    Utils.generateRandomValue(minWeight, maxWeight)));
        }
    }

    private void simulateModel() {
        simulateGravity();
        mergePoints();
    }

    private void simulateGravity() {
        pointsOfMass.forEach(point -> pointsOfMass.stream().filter(p2 ->
                !point.equals(p2)).forEach(point::calculateMovementWithOtherPoint));
        pointsOfMass.forEach(PointOfMass::updatePos);
    }

    public void mergePoints(){
        pointsOfMass.forEach(point -> pointsOfMass.stream().filter(p2 ->
                !point.equals(p2)).forEach(point::canBeMerged));
        pointsOfMass.forEach(PointOfMass::merge);
        pointsOfMass= pointsOfMass.stream().filter(point -> !point.willBeMerged).collect(Collectors.toList());
        graphics.setPoints(pointsOfMass);
    }


}
