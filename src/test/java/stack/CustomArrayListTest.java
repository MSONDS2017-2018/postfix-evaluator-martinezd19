package stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomArrayListTest {

    private CustomArrayList<Integer> array;

    @Before
    public void setup() {
        array = new CustomArrayList<>();
    }

    @Test(timeout = 5000)
    public void testArray() {
        assertTrue("Array should be empty after being constructed.",
                array.isEmpty());
        assertEquals(
                "Array should contain zero elements after being constructed.",
                0, array.size());

        array.add(Integer.valueOf(8));
        array.add(Integer.valueOf(10));

        assertFalse("Array should not be empty after elements are added",
                array.isEmpty());
        assertEquals("Array size should be 2", 2, array.size());

        assertEquals("Array index 0 should be 8", Integer.valueOf(8),
                array.get(0));
        assertEquals("Array index 1 should be 10", Integer.valueOf(10),
                array.get(1));

        array.set(1, Integer.valueOf(19));

        assertEquals("Array index 1 should be 19 after set",
                Integer.valueOf(19), array.get(1));

        array.remove(0);

        assertEquals("Array index 0 should be 19 after remove",
                Integer.valueOf(19), array.get(0));
        assertEquals("Array should be size 1 after remove", 1, array.size());

        array.add(Integer.valueOf(1));
        array.add(Integer.valueOf(2));
        array.add(Integer.valueOf(3));
        array.add(Integer.valueOf(4));

        assertEquals("Array index 4 should be 4 after multiple add",
                Integer.valueOf(4), array.get(4));
        assertEquals("Array should be size 5 after multiple add", 5,
                array.size());
    }

    @Test(timeout = 5000, expected = ArrayIndexOutOfBoundsException.class)
    public void testArrayIndex() {
        array.get(0);
    }

    @Test(timeout = 5000, expected = ArrayIndexOutOfBoundsException.class)
    public void testArrayIndexAfterRemove() {
        array.add(Integer.valueOf(5));
        array.remove(0);
        array.remove(0);
    }

}
