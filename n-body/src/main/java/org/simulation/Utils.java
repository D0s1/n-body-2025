package org.simulation;

public class Utils {
    public static double generateRandomValue(double min, double max){
        return Math.random()*(max - min) + min;
    }
}
