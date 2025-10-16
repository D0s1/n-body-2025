package org.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.*;

public class Model {
    public List<PointOfMass> pointsOfMass = new ArrayList<>();

    private GraphicInterface graphics = new GraphicInterface();
    public Model(int numOfPoints, int minWeight, int maxWeight) {
        createModel(numOfPoints, minWeight, maxWeight);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::simulateModel, 0, 4, TimeUnit.MILLISECONDS);
    }

    private void createModel(int numOfPoints, int minWeight, int maxWeight) {
        for (int i = 0; i < numOfPoints; i++) {
            pointsOfMass.add(new PointOfMass(Utils.generateRandomValue(0, 2000), Utils.generateRandomValue(0, 2000),
                    Utils.generateRandomValue(-1, 1),Utils.generateRandomValue(-1, 1),
                    Utils.generateRandomValue(minWeight, maxWeight)));
        }
    }

    private void simulateModel() {
        pointsOfMass.forEach(point -> pointsOfMass.stream().filter(p2 ->
                !point.equals(p2)).forEach(point::calculateMovementWithOtherPoint));
        pointsOfMass.forEach(PointOfMass::updatePos);
        // Check for merges!
        pointsOfMass.forEach(point -> pointsOfMass.stream().filter(p2 ->
                !point.equals(p2)).forEach(point::canBeMerged));
        // Merge!
        pointsOfMass.forEach(PointOfMass::merge);
        // New List
        pointsOfMass= pointsOfMass.stream().filter(point -> !point.willBeMerged).collect(Collectors.toList());
        graphics.setPoints(pointsOfMass);
    }


}
