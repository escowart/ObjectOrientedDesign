

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import tester.Tester;

/** A Class for testing the BTree class 
 * @author Edwin
 * @version 2013.11.14
 */
public class ExamplesBTree {
    
    
    private StringByLex strbylex = new StringByLex();
    private StringByLength strbylen = new StringByLength();
    private IntByVal intbyval = new IntByVal();
    private BooleanByVal boolbyval = new BooleanByVal();
    
    private BTree<String> emptybt = BTree.binTree(this.strbylex);
    private BTree<String> bt0 = BTree.binTree(this.strbylex);
    private BTree<String> bt1 = BTree.binTree(this.strbylex);
    private BTree<String> bt2 = BTree.binTree(this.strbylex);
    private BTree<String> bt3 = BTree.binTree(this.strbylen);
    
    private BTree<Integer> ibt0 = BTree.binTree(this.intbyval);
    
    private BTree<Boolean> bbt0 = BTree.binTree(this.boolbyval);
    private BTree<Boolean> bbt1 = BTree.binTree(this.boolbyval);
    
    private ArrayList<String> a0 = new ArrayList<String>();

    private ArrayList<Integer> ia0 = new ArrayList<Integer>();
    private ArrayList<Integer> ia1 = new ArrayList<Integer>();
    
    private ArrayList<Boolean> ba0 = new ArrayList<Boolean>();
    private ArrayList<Boolean> ba1 = new ArrayList<Boolean>();
    
    private ArrayList<Integer> aiebt = new ArrayList<Integer>();
    private ArrayList<Integer> aibt0 = new ArrayList<Integer>();
    private ArrayList<Integer> aibt1 = new ArrayList<Integer>();
    private ArrayList<Integer> aibt2 = new ArrayList<Integer>();
    private ArrayList<Integer> aibt3 = new ArrayList<Integer>();
    
    private StressTestBTree stb = new StressTestBTree();
    
    /** Initialize the data */
    private void initData() {
        this.a0.add("four");
        this.a0.add("zebra");
        this.a0.add("normal");
        this.a0.add("yellow");
        this.a0.add("nine");
        this.a0.add("ten");
        this.a0.add("add");
        this.a0.add("current");
        this.a0.add("eight");
        this.a0.add("eight");
        
        this.ia0.add(0);
        this.ia0.add(-21);
        this.ia0.add(13);
        this.ia0.add(-11);
        this.ia0.add(5);
        this.ia0.add(3);
        this.ia0.add(-900);
        
        this.ia1.add(-400);
        this.ia1.add(300);
        
        this.ba0.add(true);
        this.ba0.add(false);
        
        this.ba1.add(true);
        this.ba1.add(false);
        
        this.bt0.build(a0);
        this.bt1.build(a0, -1);
        this.bt2.build(a0, 3);
        this.bt3.build(a0);
        
        this.ibt0.build(ia0);
        this.ibt0.build(ia1, 1);
        
        this.bbt0.build(ba0);
        this.bbt1.build(ba1, -1);
        
        this.aibt0.add(3);
        this.aibt0.add(3);
        this.aibt0.add(4);
        this.aibt0.add(4);
        this.aibt0.add(4);
        this.aibt0.add(4);
        this.aibt0.add(3);
        this.aibt0.add(3);
        this.aibt0.add(3);
        this.aibt0.add(3);

        this.aibt1.add(3);
        this.aibt1.add(3);
        this.aibt1.add(4);
        this.aibt1.add(4);
        this.aibt1.add(4);
        this.aibt1.add(4);
        this.aibt1.add(3);
        this.aibt1.add(3);
        this.aibt1.add(3);
        this.aibt1.add(3);

        this.aibt2.add(2);
        this.aibt2.add(2);
        this.aibt2.add(2);
        this.aibt2.add(2);

        this.aibt3.add(3);
        this.aibt3.add(3);
        this.aibt3.add(2);
        this.aibt3.add(2);
        this.aibt3.add(3);
        this.aibt3.add(3);
    }
    
    /** Test the StressTestBTree class 
     * @param t : Any Tester */
    public void testStressTestBTree(Tester t) {
        
        t.checkExpect(!stb.equals(""));
        
        // An ArrayList for testing the methods
        ArrayList<String> alist = new ArrayList<String>();
        alist.add("a");
        alist.add("b");
        
        // A BTree for testing the methods
        BTree<String> bt;
        bt = BTree.binTree(new StringByLex());
        bt.build(alist);
        
        // Test the above methods
        t.checkExpect(StressTestBTree.testBuild(bt, 
                new StringIterator("contains.txt"), -1) >= 0);
        t.checkExpect(StressTestBTree.testContains(bt) >= 0);
        t.checkExpect(StressTestBTree
                .getNumOfStrInIterator(alist.iterator()), 2);
        t.checkExpect(StressTestBTree.testIterator(alist.iterator()) >= 0);
        StressTestBTree.main(new String[0]);
    }
    
    /** Tests the Comparators
     * @param t : The Tester class
     */
    public void testComps(Tester t) {
        
        t.checkExpect(!this.strbylen.equal(""));
        t.checkExpect(!this.strbylen.equal(this.strbylex));
        t.checkExpect(this.strbylen.equal(this.strbylen));
        
        t.checkExpect(!this.strbylex.equal(""));
        t.checkExpect(!this.strbylex.equal(this.strbylen));
        t.checkExpect(this.strbylex.equal(this.strbylex));

        t.checkExpect(!this.boolbyval.equal(""));
        t.checkExpect(!this.boolbyval.equal(this.strbylen));
        t.checkExpect(this.boolbyval.equal(this.boolbyval));
        

        t.checkExpect(!this.intbyval.equal(""));
        t.checkExpect(!this.intbyval.equal(this.strbylen));
        t.checkExpect(this.intbyval.equal(this.intbyval));

        t.checkExpect(this.strbylen.hashCode() != this.strbylex.hashCode());
        t.checkExpect(this.strbylen.hashCode() != this.boolbyval.hashCode());
        t.checkExpect(this.boolbyval.hashCode() != this.intbyval.hashCode());
        t.checkExpect(this.intbyval.hashCode() != this.strbylex.hashCode());
        t.checkExpect(this.strbylen.hashCode(), this.strbylen.hashCode());
        t.checkExpect(this.strbylex.hashCode(), this.strbylex.hashCode());
        t.checkExpect(this.boolbyval.hashCode(), this.boolbyval.hashCode());
        t.checkExpect(this.intbyval.hashCode(), this.intbyval.hashCode());
        

        t.checkExpect(this.strbylen.toString(), "StringByLength");
        t.checkExpect(this.strbylex.toString(), "StringByLex");
        t.checkExpect(this.boolbyval.toString(), "BooleanByVal");
        t.checkExpect(this.intbyval.toString(), "IntByVal");
        
    }
    
    /** Test BTree Visitors 
     *   Red-Black Tree class 
     *   @param t : The Tester class*/ 
    public void testBTreeVisitors(Tester t) {
        this.initData();
        
        // BlackHeight
        t.checkExpect(this.emptybt.accept(new BlackHeight<String>()), 0);
        t.checkExpect(this.bt0.accept(new BlackHeight<String>()), 2);
        t.checkExpect(this.bt1.accept(new BlackHeight<String>()), 2);
        t.checkExpect(this.bt2.accept(new BlackHeight<String>()), 2);
        t.checkExpect(this.bt3.accept(new BlackHeight<String>()), 2);
        
        // Height
        t.checkExpect(this.emptybt.accept(new Height<String>()), 0);
        t.checkExpect(this.bt0.accept(new Height<String>()), 4);
        t.checkExpect(this.bt1.accept(new Height<String>()), 4);
        t.checkExpect(this.bt2.accept(new Height<String>()), 2);
        t.checkExpect(this.bt3.accept(new Height<String>()), 3);
        
        // CountNodes
        ArrayList<Integer> slist = 
            this.emptybt.accept(new CountNodes<String>());
        t.checkExpect(slist.get(0), 1);
        t.checkExpect(slist.get(1), 0);
        t.checkExpect(slist.get(2), 0);
        slist = this.bt0.accept(new CountNodes<String>());
        t.checkExpect(slist.get(0), 9);
        t.checkExpect(slist.get(1), 5);
        t.checkExpect(slist.get(2), 4);
        slist = this.bt1.accept(new CountNodes<String>());
        t.checkExpect(slist.get(0), 9);
        t.checkExpect(slist.get(1), 5);
        t.checkExpect(slist.get(2), 4);
        slist = this.bt2.accept(new CountNodes<String>());
        t.checkExpect(slist.get(0), 3);
        t.checkExpect(slist.get(1), 3);
        t.checkExpect(slist.get(2), 0);
        slist = this.bt3.accept(new CountNodes<String>());
        t.checkExpect(slist.get(0), 5);
        t.checkExpect(slist.get(1), 3);
        t.checkExpect(slist.get(2), 2);

        // PathLengths
        t.checkExpect(this.emptybt.accept(new PathLengths<String>()),
                this.aiebt);
        t.checkExpect(this.bt0.accept(new PathLengths<String>()), this.aibt0);
        t.checkExpect(this.bt1.accept(new PathLengths<String>()), this.aibt1);
        t.checkExpect(this.bt2.accept(new PathLengths<String>()), this.aibt2);
        t.checkExpect(this.bt3.accept(new PathLengths<String>()), this.aibt3);
        
        // Test toString
        t.checkExpect(this.emptybt.toString(), "");
        t.checkExpect(this.bt0.toString(), "add, current, eight, four, nine, "
                + "normal, ten, yellow, zebra");
        t.checkExpect(this.bt2.toString(), "four, normal, zebra");
        t.checkExpect(this.bt3.toString(), "ten, four, zebra, normal, current");
        t.checkExpect(this.bbt0.toString(), "false, true");
        t.checkExpect(this.bbt1.toString(), "false, true");
        t.checkExpect(this.ibt0.toString(),
                        "-900, -400, -21, -11, 0, 3, 5, 13");
        
    }
    /** Test the BTree implementation
     *   Red-Black Tree class 
     *   @param t : The Tester class*/ 
    public void testBTree(Tester t) {
        this.initData();
        
        // Test equals
        t.checkExpect(this.emptybt.equals(this.emptybt));
        t.checkExpect(!this.bt0.equals(this.emptybt));
        t.checkExpect(!this.emptybt.equals(this.bt0));
        t.checkExpect(this.bt0.equals(this.bt0));
        t.checkExpect(this.bt0.equals(this.bt1));
        t.checkExpect(this.bt1.equals(this.bt0));
        t.checkExpect(!this.bt3.equals(this.bt0));
        t.checkExpect(this.bt3.equals(this.bt3));
        t.checkExpect(!this.bt1.equals(this.bt2));
        t.checkExpect(!this.bt1.equals(""));
        t.checkExpect(this.bbt0.equals(this.bbt0));
        t.checkExpect(this.bbt0.equals(this.bbt1));
        t.checkExpect(this.bbt1.equals(this.bbt0));
        t.checkExpect(!this.bt1.equals(this.bbt0));
        t.checkExpect(!this.ibt0.equals(this.bbt0));
        t.checkExpect(this.ibt0.equals(this.ibt0));

        // Test contains
        t.checkExpect(!this.emptybt.contains("four"));
        t.checkExpect(this.bt0.contains("four"));
        t.checkExpect(this.bt0.contains("eight"));
        t.checkExpect(!this.bt0.contains("five"));
        t.checkExpect(this.bt3.contains("ten"));
        t.checkExpect(!this.bt3.contains("a"));
        t.checkExpect(this.bbt0.contains(true));
        t.checkExpect(this.bbt0.contains(false));
        t.checkExpect(this.ibt0.contains(0));
        t.checkExpect(!this.ibt0.contains(1));

        // Test hashCode
        t.checkExpect(this.emptybt.hashCode(), this.emptybt.hashCode());
        t.checkExpect(this.bt0.hashCode(), this.bt0.hashCode());
        t.checkExpect(this.bt3.hashCode(), this.bt3.hashCode());
        t.checkExpect(this.bbt0.hashCode(), this.bbt1.hashCode());
        t.checkExpect(this.ibt0.hashCode(), this.ibt0.hashCode());
        

        // Test RepOK
        t.checkExpect(this.bt0.repOK());
        t.checkExpect(this.bt1.repOK());
        t.checkExpect(this.bt2.repOK());
        t.checkExpect(this.bt3.repOK());
        t.checkExpect(this.bbt0.repOK());
        t.checkExpect(this.bbt1.repOK());
        t.checkExpect(this.ibt0.repOK());
    }
        
        /**
         * Test part 2 of BTree Red-Black Tree class
         * @param t : The Tester class
         */
    public void testBTree2(Tester t) {
        // Test Iterator
        
        Iterator<String> siter = this.bt0.iterator();
        t.checkExpect(siter.next(), "add");
        t.checkExpect(siter.next(), "current");
        t.checkExpect(siter.next(), "eight");
        t.checkExpect(siter.next(), "four");
        t.checkExpect(siter.next(), "nine");
        t.checkExpect(siter.next(), "normal");
        t.checkExpect(siter.next(), "ten");
        t.checkExpect(siter.next(), "yellow");
        t.checkExpect(siter.next(), "zebra");
        
        siter = this.bt3.iterator();
        t.checkExpect(siter.next(), "ten");
        t.checkExpect(siter.next(), "four");
        t.checkExpect(siter.next(), "zebra");
        t.checkExpect(siter.next(), "normal");
        t.checkExpect(siter.next(), "current");
        
        Iterator<Integer> iiter = this.ibt0.iterator();
        t.checkExpect(iiter.next(), -900);
        t.checkExpect(iiter.next(), -400);
        t.checkExpect(iiter.next(), -21);
        t.checkExpect(iiter.next(), -11);
        t.checkExpect(iiter.next(), 0);
        t.checkExpect(iiter.next(), 3);
        t.checkExpect(iiter.next(), 5);
        t.checkExpect(iiter.next(), 13);
        
        
        Iterator<Boolean> biter = this.bbt0.iterator();
        t.checkExpect(biter.next(), false);
        t.checkExpect(biter.next(), true);
        
        
        // checkException was not working
        try {
            siter.next();
        } 
        catch (NoSuchElementException e) {
            t.checkExpect(e, new NoSuchElementException(
                    "next: The is no next element in the "
                            + "Binary Search Tree Iterator"));
        }
        
        try {
            siter.next();
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new RuntimeException(
                    "An exception has already been thrown"
                            + ", cannot continue"));
        }
        try {
            siter.hasNext();
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new RuntimeException(
                    "An exception has already been thrown"
                            + ", cannot continue"));
        }
        
        try {
            siter.remove();
        } 
        catch (UnsupportedOperationException e) {
            t.checkExpect(e, new UnsupportedOperationException(
                    "remove: This method is not avalible"));
        }
        
        // Test Build Exceptions
        siter = this.bt1.iterator();
        siter.next();
        try {
            this.bt1.build(this.a0);
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new ConcurrentModificationException(
                    "build: Cannot build while an Iterator "
                            + "is running over this Binary Search Tree"));
        }
        
        try {
            this.bt1.build(this.a0, -1);
        } 
        catch (RuntimeException e) {
            t.checkExpect(e, new ConcurrentModificationException(
                    "build: Cannot build while an Iterator "
                            + "is running over this Binary Search Tree"));
        }
        
    }
}
