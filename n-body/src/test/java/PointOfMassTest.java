import org.junit.jupiter.api.Test;
import org.simulation.PointOfMass;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointOfMassTest {

    @Test
    void testSameCordsSpeed(){
        PointOfMass firstPoint = new PointOfMass(0,0, 0, 0,1);
        PointOfMass secondPoint = new PointOfMass(0,0,0,0,1);
        firstPoint.calculateMovementWithOtherPoint(secondPoint);
        firstPoint.updatePos();
        assertEquals(0, firstPoint.getCoordinate().getX());
    }

    @Test
    void testBiggerMassLessSpeed(){
        PointOfMass firstPoint = new PointOfMass(0,0,0,0,1);
        PointOfMass secondPoint = new PointOfMass(100,0,0,0,10);
        firstPoint.calculateMovementWithOtherPoint(secondPoint);
        secondPoint.calculateMovementWithOtherPoint(firstPoint);
        firstPoint.updatePos();
        secondPoint.updatePos();
        assertTrue(firstPoint.getMovement().getXMovement()>secondPoint.getMovement().getXMovement());
    }

    @Test
    void posUpdated(){
        PointOfMass firstPoint = new PointOfMass(0,0,0,0,1);
        PointOfMass secondPoint = new PointOfMass(100,0,0,0,10);
        firstPoint.calculateMovementWithOtherPoint(secondPoint);
        secondPoint.calculateMovementWithOtherPoint(firstPoint);
        firstPoint.updatePos();
        secondPoint.updatePos();
        assertTrue(firstPoint.getCoordinate().getX() > 0 && secondPoint.getCoordinate().getX() < 100);
    }

    @Test
    void testMergeOfPoints(){
        PointOfMass firstPoint = new PointOfMass(0,0, 0, 0,1);
        PointOfMass secondPoint = new PointOfMass(0,0,0,0,1);
        firstPoint.canBeMerged(secondPoint);
        firstPoint.merge();
        assertEquals(2, firstPoint.getWeight());
    }

    @Test
    void testMergeOfPointsOnlyFlagsSecondPoint(){
        PointOfMass firstPoint = new PointOfMass(0,0, 0, 0,1);
        PointOfMass secondPoint = new PointOfMass(0,0,0,0,1);
        firstPoint.canBeMerged(secondPoint);
        firstPoint.merge();
        assertTrue(secondPoint.willBeMerged);
        assertFalse(firstPoint.willBeMerged);
    }

    @Test
    void testMultipleMerges(){
        PointOfMass firstPoint = new PointOfMass(0,0, 0, 0,1);
        PointOfMass secondPoint = new PointOfMass(0,0,0,0,1);
        PointOfMass thirdPoint = new PointOfMass(0,0,0,0,1);
        firstPoint.canBeMerged(secondPoint);
        firstPoint.canBeMerged(thirdPoint);
        firstPoint.merge();
        assertEquals(3, firstPoint.getWeight());
        assertFalse(firstPoint.willBeMerged);
        assertTrue(secondPoint.willBeMerged);
        assertTrue(thirdPoint.willBeMerged);
    }


}
