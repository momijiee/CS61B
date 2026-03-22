package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {

    @Test
    public void testTreeAddThreeRemove() {
        AListNoResizing<Integer> goodList = new AListNoResizing<>();
        BuggyAList<Integer> badList = new BuggyAList<>();

        int N = 5000;
        for (int i=0; i<N; i++) {
            int opNum = StdRandom.uniform(0, 3);
            if (opNum == 0) {
                /* addLast */
                int randNum = StdRandom.uniform(0, 100);
                goodList.addLast(randNum);
                badList.addLast(randNum);
            } else if (opNum == 1) {
                /* getLast */
                assertEquals(goodList.size(), badList.size());
                if (goodList.size() == 0) continue;
                assertEquals(goodList.getLast(), badList.getLast());
            } else {
                /* removeLast */
                assertEquals(goodList.size(), badList.size());
                if (goodList.size() == 0) continue;
                assertEquals(goodList.removeLast(), badList.removeLast());
            }
        }

        System.out.println(N + " tests done, No bug found.");


    }
}
