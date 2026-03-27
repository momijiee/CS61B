package deque;

import org.junit.Test;

import java.util.Comparator;
import static org.junit.Assert.*;

public class LengthComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test
    public void testString() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new LengthComparator());
        mad.addFirst("Love");
        mad.addFirst("Sacrifice");
        mad.addLast("forgiveness");
        assertEquals(mad.max(), "forgiveness");
    }
}
