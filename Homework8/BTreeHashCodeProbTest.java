

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import junit.framework.TestCase;


/**
 * A test program to complete probability tests on the hashCode method of BTree
 * 
 * @author Jessica Young Schmidt
 * @version 2013-11-11 Updated to include comparator
 * 
 */
public class BTreeHashCodeProbTest extends TestCase {

    /** random number generator, initialed by probabilisticTests() */
    Random rng;

    /** base for hash codes */
    int base = 0;
    

    public static void main(String[] args) {
        BTreeHashCodeProbTest a = new BTreeHashCodeProbTest();
        a.hashCodeTest();
    }

    /**
     * Probabilistic test for distribution of hash codes.
     */
    //@Test
    public void hashCodeTest() {
        int numFailed = 0;
        numFailed = numFailed + probabilisticTests(400, 20);
        base = -2;
        numFailed = numFailed + probabilisticTests(400, 20);
        base = 412686306;
        numFailed = numFailed + probabilisticTests(400, 20);

        System.out.println("\nNum failed: " + numFailed);
        assertTrue("hashCode quality", numFailed <= 1);
    }

    /**
     * Initializes rng
     */
    private void initializeRNGrandomly() {
        rng = new Random(System.nanoTime());
    }

    /**
     * Generate n random pairs of unequal BTree<Integer> values. If k or more
     * have the same hash code, then report failure.
     * 
     * @param n
     *            number of random pairs
     * @param k
     *            number to report failure
     * @return 0 if sameHash <k, 1 otherwise
     */
    private int probabilisticTests(int n, int k) {
        System.out.println("probabilisticTests: " + n + ", " + k);
        initializeRNGrandomly();
        int sameHash = 0;
        int equalButDiffHashCode = 0;
        int i = 0;
        while (i < n) {
            BTree<Integer> b1 = randomBTree();
            BTree<Integer> b2 = randomBTree();
            // System.out.println("[" + b1 + "] [" + b2 + "]");
            if (!(b1.equals(b2))) {
                i = i + 1;
                if (b1.hashCode() == b2.hashCode()) {
                    System.out.println("Same hashCode but not equal: [" + b1
                            + "] [" + b2 + "]\n  hashCodes: " + b1.hashCode()
                            + ", " + b2.hashCode());
                    sameHash = sameHash + 1;
                }
            }
            else {
                // b1 and b2 are equal. Check that hashCodes are equal
                if (b1.hashCode() != b2.hashCode()) {
                    equalButDiffHashCode++;
                }
            }
        }
        System.out.println("Same Hash: " + sameHash + " / " + n
                + ". Pass hashCode quality: " + (sameHash < k));

        // assertTrue("hashCode quality", sameHash < k);
        assertTrue("equal hashCodes for equal BTrees",
                equalButDiffHashCode == 0);

        if (sameHash < k) {
            return 0;
        }
        else {
            return 1;
        }

    }

    /**
     * Returns a randomly selected BTree<Integer>
     */
    private BTree<Integer> randomBTree() {
        // System.out.println("randomBTree");

        // First pick the size.
        double x = rng.nextDouble();
        double y = 0.5;
        int size = 0;
        while (y > x) {
            size = size + 1;
            y = y / 2.0;
        }
        BTree<Integer> b = BTree.binTree(new UsualInteger());
        ArrayList<Integer> al = new ArrayList<Integer>();
        // System.out.println("size: " + size);
        while (al.size() < size) {
            al.add(randomInteger());
        }
        b.build(al);
        return b;
    }

    /**
     * Returns a randomly selected Integer.
     */
    private Integer randomInteger() {
        int h = base + rng.nextInt(5);
        return new Integer(h);
    }

    /**
     * A comparator for Integer values.
     * 
     * @author CS3500
     * @version 11-05-2013
     */
    class UsualInteger implements Comparator<Integer> {

        /**
         * Compares its two arguments for order.
         * 
         * @param m
         *            first Integer to compare
         * @param n
         *            second Integer to compare
         * @return Returns a negative integer, zero, or a positive integer as m
         *         is less than, equal to, or greater than n
         */
        public int compare(Integer m, Integer n) {
            return m.compareTo(n);
        }

        /**
         * Is this <code>Comparator</code> same as the given object
         * 
         * @param o
         *            the given object
         * @return true if the given object is an instance of this class
         */
        public boolean equals(Object o) {
            return (o instanceof UsualInteger);
        }

        /**
         * There should be only one instance of this class = all are equal
         * 
         * @return the hash code same for all instances
         */
        public int hashCode() {
            return (this.toString().hashCode());
        }

        /**
         * @return name of class
         */
        public String toString() {
            return "UsualInteger";
        }
    }

}
