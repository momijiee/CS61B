package flik;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        int j = 0;
        for (int k = 0; k < 500; k++) {
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
            i++;
            j++;
        }
        System.out.println("i is " + i);
    }
}
