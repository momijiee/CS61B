package flik;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlik {

    @Test
    public void RandomTestFilk() {
        for (int i = 0; i < 500; i++) {
            int op = StdRandom.uniform(0, 2);
            if (op == 0) {
                /* test equal numbers */
                int num1 = StdRandom.uniform(0, 500);
                boolean res = Flik.isSameNumber(num1, num1);
                assertTrue(res);
            }
            else if (op == 1) {
                /* test inequal numbers */
                int num1 = StdRandom.uniform(0, 500);
                int num2 = StdRandom.uniform(0, 500);
                if (num1 == num2) continue;
                boolean res = Flik.isSameNumber(num1, num2);
                res = !res;
                assertTrue(res);
            }
        }
    }

    @Test
    public void TestNotEqualNums() {
        int num1 = 29;
        int num2 = 67;
        boolean res = Flik.isSameNumber(num1, num2);
        assertTrue(!res);
    }

    @Test
    public void TestEqualNums() {
        int num1 = 167;
        int num2 = 167;
        boolean res = Flik.isSameNumber(num1, num2);
        assertTrue(res);
    }
}
