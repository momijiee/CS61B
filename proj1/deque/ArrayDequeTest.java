package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());
    }

    @Test
    public void addAndRemoveTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        lld1.addFirst(3);
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    public void getTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        assertNull(lld1.get(0));

        lld1.addFirst(1);
        lld1.addLast(3);

        assertEquals(1, (int) lld1.get(0));
        assertEquals(3, (int) lld1.get(1));
    }
}
