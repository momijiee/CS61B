package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {

    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private GuitarString[] strings;
    private double sample;

    public GuitarHero() {
        strings = new GuitarString[keyboard.length()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440 * Math.pow(2,(double) (i - 24) / 12));
        }
    }

    public void sample() {
        double sample = 0;
        for (int i = 0; i < strings.length; i++) {
            sample += strings[i].sample();
        }
        this.sample = sample;
    }

    public void pluck(char key) {
        int index = keyboard.indexOf(key);
        if (index == -1) { return;}
        strings[index].pluck();
    }

    public void tic() {
        for (int i = 0; i < strings.length; i++) {
            strings[i].tic();
        }
    }

    public double getSample() {
        return this.sample;
    }

    public static void main(String[] args) {

        GuitarHero guitarHero = new GuitarHero();

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                guitarHero.pluck(key);
            }
            /* compute the superposition of samples */
            guitarHero.sample();

            /* play the sample on standard audio */
            StdAudio.play(guitarHero.getSample());

            /* advance the simulation of each guitar string by one step */
            guitarHero.tic();
        }
    }
}
