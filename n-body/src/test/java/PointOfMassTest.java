import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.simulation.PointOfMass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointOfMassTest {

    @Test
    void testSimpleCase(){
        PointOfMass firstPoint = new PointOfMass(0,0, 0, 0,1);
        PointOfMass secondPoint = new PointOfMass(0,0,0,0,1);
        firstPoint.calculateMovementWithOtherPoint(secondPoint);
        firstPoint.updatePos();
        assertEquals(0, firstPoint.getCoordinate().getX());
    }

    @Test
    void testBiggerMass(){
        PointOfMass firstPoint = new PointOfMass(0,0,0,0,1);
        PointOfMass secondPoint = new PointOfMass(100,0,0,0,10);
        firstPoint.calculateMovementWithOtherPoint(secondPoint);
        secondPoint.calculateMovementWithOtherPoint(firstPoint);
        firstPoint.updatePos();
        secondPoint.updatePos();
        assertTrue(firstPoint.getMovement().getXMovement()>secondPoint.getMovement().getXMovement());
    }


}
