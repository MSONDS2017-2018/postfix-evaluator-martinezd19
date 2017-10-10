package language.arith;

import language.Operand;
import language.Operator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExponentOperatorTest {

    private Operator<Integer> operator;
    private Operand<Integer>  op0;
    private Operand<Integer>  op1;

    /**
     * Runs before each test.
     */
    @Before
    public void setup() {
        operator = new ExponentOperator();
        op0 = new Operand<Integer>(5);
        op1 = new Operand<Integer>(3);
    }

    @Test(timeout = 5000)
    public void testPerformOperation()
            throws ExtremeOperandException {
        operator.setOperand(0, op0);
        operator.setOperand(1, op1);

        Operand<Integer> result = operator.performOperation();
        int              value  = result.getValue();
        assertEquals("Operator applied to 5 and 3 should produce 125.", 125,
                value);
    }

    /**
     * Ensure that operator does not crash on greater than int values
     */
    @Test(timeout = 5000, expected = ExtremeOperandException.class)
    public void testLargeOperation()
            throws ExtremeOperandException {
        operator.setOperand(0, new Operand<Integer>(10));
        operator.setOperand(1, new Operand<Integer>(15));

        Operand<Integer> result = operator.performOperation();
        int              value  = result.getValue();
    }

    @Test(timeout = 5000)
    public void testNegativeBase()
            throws ExtremeOperandException {
        operator.setOperand(0, new Operand<Integer>(-5));
        operator.setOperand(1, new Operand<Integer>(2));

        Operand<Integer> result = operator.performOperation();
        int              value  = result.getValue();
        assertEquals("Operator applied to -5 and 2 should be 25", 25, value);
    }

    @Test(timeout = 5000)
    public void testNegativeBase2()
            throws ExtremeOperandException {
        operator.setOperand(0, new Operand<Integer>(-5));
        operator.setOperand(1, new Operand<Integer>(3));

        Operand<Integer> result = operator.performOperation();
        int              value  = result.getValue();
        assertEquals("Operator applied to -5 and 3 should be -125", -125, value);
    }

    @Test(timeout = 5000)
    public void testNegativePower()
            throws ExtremeOperandException {
        operator.setOperand(0, new Operand<Integer>(5));
        operator.setOperand(1, new Operand<Integer>(-2));

        Operand<Integer> result = operator.performOperation();
        int              value  = result.getValue();
        assertEquals("Operator applied to 5 and -2 should be 0", 0, value);
    }

    @Test(timeout = 5000)
    public void testGetNumberOfArguments() {
        assertEquals("Binary operator should have 2 arguments.",
                operator.getNumberOfArguments(), 2);
    }

    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        operator.setOperand(2, op0);
        fail("Binary operator should not except input to position 2");
    }

    @Test(timeout = 5000, expected = NullPointerException.class)
    public void testNullArgumentException() {
        operator.setOperand(0, null);
        fail("Operator should not allow null arguments");
    }

    @Test(timeout = 5000, expected = IllegalStateException.class)
    public void testIllegalStateException() {
        operator.setOperand(0, op0);
        operator.setOperand(0, op0);

        fail("Operator should not allow position 0 to be set more than once");
    }

    @Test(timeout = 5000, expected = IllegalStateException.class)
    public void testIllegalStateExceptionPerform()
            throws ExtremeOperandException {
        operator.performOperation();
        fail("Operator should not compute when all arguments have not been set.");
    }

}
