package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedStackTest {

    private StackInterface<Integer> stack;

    @Before
    public void setup() {
        stack = new LinkedStack<Integer>();
    }

    @Test(timeout = 5000)
    public void testStack() {
        assertTrue("Stack should be empty after being constructed.",
                stack.isEmpty());
        assertEquals(
                "Stack should contain zero elements after being constructed.",
                0, stack.size());

        stack.push(5);
        assertFalse("Stack should not be empty.", stack.isEmpty());
        assertEquals("The top element should be 5", new Integer(5),
                stack.top());
        assertEquals("The stack should contain one element.", 1, stack.size());

        stack.push(4);
        assertEquals("The top element should be 4", new Integer(4),
                stack.top());
        assertEquals("The stack should contain two elements.", 2, stack.size());

        Integer t = stack.pop();
        assertEquals("The popped element should be 4", new Integer(4), t);
        assertEquals("The top element should be 5", new Integer(5),
                stack.top());
        assertEquals("The stack should contain one element.", 1, stack.size());
        assertFalse("The stack should not be empty.", stack.isEmpty());

        t = stack.pop();
        assertEquals("The popped element should be 5", new Integer(5), t);
        assertTrue("The stack should be empty.", stack.isEmpty());
    }

    @Test(timeout = 5000, expected = StackUnderflowException.class)
    public void testStackUnderflowPop() {
        stack.pop();
    }

    @Test(timeout = 5000, expected = StackUnderflowException.class)
    public void testStackUnderflowTop() {
        stack.top();
    }

}
