
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import tester.Tester;

/* Edwin Cowart
 * escowart@ccs.neu.edu
 */


/** A Class for stress testing the BTree Class
 *  @author Edwin Cowart
 *  @version 2013.10.20 */
public class StressTestBTree {

    /** The main class to run the Stress Test 
     * @param args : Array of Strings - main class argument */
    public static void main(String[] args) {
        StringByLex strbylex = new StringByLex();
        StringReverseByLex strbyrevlex = new StringReverseByLex();
        StringWithOutPrefixByLex swopbl = new StringWithOutPrefixByLex();

        ArrayList<Comparator<String>> scomplist = 
                new ArrayList<Comparator<String>>();
        scomplist.add(strbylex);
        scomplist.add(strbyrevlex);
        scomplist.add(swopbl);

        // Test List of Words
        String lexordertxt = "lexicographically_ordered.txt";
        String revtxt = "reverse_ordered.txt";
        String prefixtxt = "prefix_ordered.txt";
        String randtxt = "random_order.txt";

        ArrayList<String> tlist1 = new ArrayList<String>();
        tlist1.add(lexordertxt);
        tlist1.add(revtxt);
        tlist1.add(prefixtxt);
        tlist1.add(randtxt);

        int[] ia1 = new int[]{2000, 4000, 8000, 16000, 20000, 24000};

        StressTestBTree.testTiming(scomplist, tlist1, ia1);

        // Tests Classical Literature
        String iliadtxt = "iliad.txt";
        String hippotxt = "hippooath_DUPLICATES.txt";

        ArrayList<String> tlist2 = new ArrayList<String>();
        tlist2.add(iliadtxt);
        tlist2.add(hippotxt);
        

        int[] ia2 = new int[]{-1};

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
     * @param numbertoadd : An Array of Numbers 
     */
    public static void testTiming(ArrayList<Comparator<String>> scomplist,
            ArrayList<String> slist, int[] numbertoadd) {
        if (numbertoadd.length == 0) {
            return;
        }
        int alen = numbertoadd.length;
        boolean printnumstr = !(alen == 1 && numbertoadd[0] == -1);
        
        String s = "Comparator, File, ";
        if (printnumstr) {
            s += "Num Strings, ";
        }
        s += "Size(#), Build(ms), Iterate(ms), Contains(ms)";
        System.out.println(s);
        
        for (String filename : slist) {
            for (Comparator<String> comp : scomplist) {
                for (int i = 0; i < alen; i++) {
                    BTree btemp;
                    btemp = BTree.binTree(comp);

                    Iterable<String> itb = new StringIterator(filename);
                    long buildtime = StressTestBTree.testBuild(btemp, itb,
                            numbertoadd[i]);

                    long ittime = StressTestBTree
                            .testIterator(new StringIterator(filename));
                    int itcount = StressTestBTree
                            .getNumOfStrInIterator(
                                    new StringIterator(filename));

                    long containstime = StressTestBTree.testContains(btemp);

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
    public static long testBuild(BTree bt, Iterable<String> iterab, int i) {
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
    public static long testContains(BTree bt) {
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

/** A Class for testing the StressTestBTree class 
 * @author Edwin
 * @version 2013.10.20
 */
class ExamplesStressTestBTree {
    
    /** Test the StressTestBTree class 
     * @param t : Any Tester */
    public void test(Tester t) {
        ArrayList<String> alist = new ArrayList<String>();
        alist.add("a");
        alist.add("b");
        
        BTree bt;
        bt = BTree.binTree(new StringByLex());
        bt.build(alist);
        
        t.checkExpect(StressTestBTree.testBuild(bt, 
                new StringIterator("contains.txt"), -1) >= 0);
        t.checkExpect(StressTestBTree.testContains(bt) >= 0);
        t.checkExpect(StressTestBTree
                .getNumOfStrInIterator(alist.iterator()), 2);
        t.checkExpect(StressTestBTree.testIterator(alist.iterator()) >= 0);
        // Takes Too much time in submission
        //StressTestBTree.main(new String[0]);
    }
}


