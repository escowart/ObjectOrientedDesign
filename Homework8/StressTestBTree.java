

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/** A Class for stress testing the BTree Class
 *  @author Edwin Cowart
 *  @version 2013.11.14 */
public class StressTestBTree {

    /** The main class to run the Stress Test 
     * @param args : Array of Strings - main class argument */
    public static void main(String[] args) {
        // Initialize the Comparators
        StringByLex strbylex = new StringByLex();
        StringReverseByLex strbyrevlex = new StringReverseByLex();
        StringWithOutPrefixByLex swopbl = new StringWithOutPrefixByLex();

        // Put the Comparators into an ArrayList
        ArrayList<Comparator<String>> scomplist = 
                new ArrayList<Comparator<String>>();
        scomplist.add(strbylex);
        scomplist.add(strbyrevlex);
        scomplist.add(swopbl);

        // Initialize the Names of the Files
        String lexordertxt = "lexicographically_ordered.txt";
        String revtxt = "reverse_ordered.txt";
        String prefixtxt = "prefix_ordered.txt";
        String randtxt = "random_order.txt";

        // Put the Names of the Files into an ArrayList
        ArrayList<String> tlist1 = new ArrayList<String>();
        tlist1.add(lexordertxt);
        tlist1.add(revtxt);
        tlist1.add(prefixtxt);
        tlist1.add(randtxt);

        // Initialize an Array of the number of elements 
        //  that should be added in each case
        int[] ia1 = new int[]{2000, 4000, 8000, 16000, 20000, 24000};

        // Call the timing function on the ArrayList of Comparators, 
        //  the ArrayList of File Names, and the Array of Number of Elements
        StressTestBTree.testTiming(scomplist, tlist1, ia1);

        // Initialize the File Names of the Classic Literature
        String iliadtxt = "iliad.txt";
        String hippotxt = "hippooath_DUPLICATES.txt";

        // Put the Names of the Classic Lit. into an ArrayList
        ArrayList<String> tlist2 = new ArrayList<String>();
        tlist2.add(iliadtxt);
        tlist2.add(hippotxt);
        
        // Make an Array of the number of elements that 
        //  should be iterated through. -1 represents all
        int[] ia2 = new int[]{-1};

        // Call the timing function on the ArrayList of Comparators, the
        //  ArrayList of Classic Lit. File Names and the Array of -1
        StressTestBTree.testTiming(scomplist, tlist2, ia2);

    }

    /** Run the Time Tester on Binary Trees constructed with 
     *  each Comparator in scomplist 
     *  and then built with each file whose name is given in slist
     *  but only builds with each number of elements given in numbertoadd
     *  (if numbertoadd has one element that is -1 
     *    then Num of Strings is not printed)
     * @param scomplist : Any ArrayList of Comparators of Strings
     * @param slist : Any ArrayList of Strings
     * @param numbertoadd : An Non-Empty Array of Numbers 
     */
    public static void testTiming(ArrayList<Comparator<String>> scomplist,
            ArrayList<String> slist, int[] numbertoadd) {
        
        // The length of the Array numbertoadd
        int alen = numbertoadd.length;
        // Whether the numbertoadd Array is an Array of one element, -1
        boolean printnumstr = !(alen == 1 && numbertoadd[0] == -1);
        
        // Print the Header of the timer method
        String s = "Comparator, File, ";
        if (printnumstr) {
            s += "Num Strings, ";
        }
        s += "Size(#), Build(ms), Iterate(ms), Contains(ms)";
        System.out.println(s);
        
        // Run the timer method over the data
        for (String filename : slist) {
            for (Comparator<String> comp : scomplist) {
                for (int i = 0; i < alen; i++) {
                    // Construct a BTree of Strings with the current comp
                    BTree<String> btemp;
                    btemp = BTree.binTree(comp);

                    // Get the timing of the build method with 
                    //  the current filename's String which will 
                    //  each be added to btemp
                    Iterable<String> itb = new StringIterator(filename);
                    long buildtime = StressTestBTree.testBuild(btemp, itb,
                            numbertoadd[i]);

                    // Get the timing of the Iteration test
                    long ittime = StressTestBTree
                            .testIterator(new StringIterator(filename));
                    // Get the number of Strings that were added to the BTree
                    int itcount = StressTestBTree
                            .getNumOfStrInIterator(
                                    new StringIterator(filename));

                    // Get the timing of the contains method on btemp
                    long containstime = StressTestBTree.testContains(btemp);

                    // Print the results of this test in the triple nested for
                    System.out.print(comp.toString() + ", " + filename + ", ");
                    if (printnumstr) {
                        System.out.print(numbertoadd[i] + ", ");
                    }
                    System.out.println(itcount + ", " + buildtime + ", "
                            + ittime + ", " + containstime);
                }
            }
        }
        System.out.println();
    }
    
    /** How long does the build method for the given Binary Tree take in ms.
     *   Modifies the Binary Tree to build with the Strings in the file
     * @param bt : A BTree with its comp != null
     * @param iterab : Any Iterable of Strings
     * @param i : Any int
     * @return : A long of the time the build method took in ms
     */
    public static long testBuild(BTree<String> bt, Iterable<String> iterab,
            int i) {
        long t = System.currentTimeMillis();
        bt.build(iterab, i);
        return System.currentTimeMillis() - t;
    }
    
    /** Test the contains methods on the given Binary Tree
     *  On the string in contains.txt
     * @param bt : Any Binary Tree
     * @return : An integer representing the amount of time in ms
     *  it took to run contains on every string in contains.txt
     */
    public static long testContains(BTree<String> bt) {
        Iterator<String> itcomp = new StringIterator("contains.txt");
        long t = System.currentTimeMillis();
        while (itcomp.hasNext()) {
            bt.contains(itcomp.next());
        }
        return System.currentTimeMillis() - t;
    }
    
    /** How long does the iterator take to iterate to run in ms.
     *   Modifies The iterator so it has nothing left in it
     * @param it : Any Iterator of Strings
     * @return : A long of the time the iteration took in ms
     */
    public static long testIterator(Iterator<String> it) {
        long t = System.currentTimeMillis();
        while (it.hasNext()) {
            it.next();
        }
        return System.currentTimeMillis() - t;
    }
    
    /** Get the number of Strings in the given String Iterator
     * @param it : Any Iterator<String>
     * @return : An Integer of the number of elements in the iterator
     */
    public static int getNumOfStrInIterator(Iterator<String> it) {
        int i = 0;
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }
}


