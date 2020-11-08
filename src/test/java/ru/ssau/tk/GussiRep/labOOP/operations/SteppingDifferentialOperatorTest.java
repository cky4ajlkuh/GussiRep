package ru.ssau.tk.GussiRep.labOOP.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SteppingDifferentialOperatorTest {

    @Test
    public void testGetStep() {
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(2.);
        assertEquals(differentialOperator.getStep(), 2., 0.01);
        assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(-100.));
    }

    @Test
    public void testSetStep() {
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(1.);
        differentialOperator.setStep(2.);
        assertEquals(differentialOperator.getStep(), 2., 0.01);
        assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(-200.));
    }
}