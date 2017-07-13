/**
 * Created by justinelsemah on 2017-07-13.
 */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> stringArray = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            stringArray.enqueue(StdIn.readString());
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(stringArray.dequeue());
        }
    }
}
