package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String failureSequence = "";

        for (int i = 0; i < 500; i++) {
            int op = StdRandom.uniform(0, 6);
            switch (op) {
                case 0:
                    int num = StdRandom.uniform(0, 500);
                    sad.addFirst(num);
                    ads.addFirst(num);
                    failureSequence = failureSequence + "addFirst(" + num + ")\n";
                    break;
                case 1:
                    int num2 = StdRandom.uniform(0, 500);
                    sad.addLast(num2);
                    ads.addLast(num2);
                    failureSequence = failureSequence + "addLast(" + num2 + ")\n";
                    break;
                case 2:
                    if (!(sad.isEmpty() || ads.isEmpty())) {
                        Integer res1 = sad.removeFirst();
                        Integer res2 = ads.removeFirst();
                        failureSequence = failureSequence + "removeFirst()\n";
                        assertEquals(failureSequence, res1, res2);
                    }
                    break;
                case 3:
                    if (!(sad.isEmpty() || ads.isEmpty())) {
                        Integer res3 = sad.removeLast();
                        Integer res4 = ads.removeLast();
                        failureSequence = failureSequence + "removeLast()\n";
                        assertEquals(failureSequence, res3, res4);
                    }
                    break;
                case 4:
                    failureSequence = failureSequence + "size()\n";
                    assertEquals(failureSequence, sad.size(), ads.size());
                    break;
                case 5:
                    failureSequence = failureSequence + "isEmpty()\n";
                    assertEquals(failureSequence, sad.isEmpty(), ads.isEmpty());
                    break;
                default:
            }
        }
    }
}
