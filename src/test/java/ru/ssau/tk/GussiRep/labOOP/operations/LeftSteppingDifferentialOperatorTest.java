package ru.ssau.tk.GussiRep.labOOP.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 1;
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1, 0.01);

    }
}