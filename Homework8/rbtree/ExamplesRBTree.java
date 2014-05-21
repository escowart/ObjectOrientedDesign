package rbtree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import tester.Tester;

/** A class the tester the RBTree class
 *  @author Edwin Cowart
 *  @version 2013.11.14 */
public class ExamplesRBTree {

    /** Construct a tester for the RBTree classes
     */
    public ExamplesRBTree() {
        this.initTree();
    }

    private StringByLex strbylex;
    private StringByLength strbylen;
    private RBTree<String> tree0;
    private RBTree<String> tree1;
    private RBTree<String> tree2;
    private RBTree<String> tree3;
    private RBTree<String> tree4;
    private RBTree<String> tree5;
    private RBTree<String> tree6;
    
    private ArrayList<String> a1;
    private ArrayList<String> a2;
    private ArrayList<String> a3;
    
    private PathLengths<String> pathlens;
    private Height<String> height;
    private BlackHeight<String> blackheight;
    private CountNodes<String> countnode;
    
    private ArrayList<Integer> t0pl;
    private ArrayList<Integer> t4pl;
    private ArrayList<Integer> t6pl;
    
    private ArrayList<Integer> t0cn;
    private ArrayList<Integer> t4cn;
    private ArrayList<Integer> t6cn;
    
    private int[] aupper;
    private int[] eupper;
    private int[] alower;
    private int[] elower;
    private int[] amid;
    private int[] emid;
    
    private int[] sw0;
    private int[] sw1;
    private int[] sw5;
    private int[] sw31;
    private int[] sw32;
    private int[] sw33;
    private int[] sw63;
    private int[] sw64;
    private int[] sw991;
    private int[] sw992;
    private int[] sw993;
    
    private int[] zeroonearray;
    private int[] onezeorarray;
    
    /** Initialize the data */
    private void initData() {
        strbylex = new StringByLex();
        strbylen = new StringByLength();
        tree0 = RBTree.binTree(this.strbylex);
        tree1 = RBTree.binTree(this.strbylex);
        tree2 = RBTree.binTree(this.strbylex);
        tree3 = RBTree.binTree(this.strbylex);
        tree4 = RBTree.binTree(this.strbylex);
        tree5 = RBTree.binTree(this.strbylen);
        tree6 = RBTree.binTree(this.strbylen);
        
        a1 = new ArrayList<String>();
        a2 = new ArrayList<String>();
        a3 = new ArrayList<String>();
        
        pathlens = new PathLengths<String>();
        height = new Height<String>();
        blackheight = new BlackHeight<String>();
        countnode = new CountNodes<String>();
        
        t0pl = new ArrayList<Integer>();
        t4pl = new ArrayList<Integer>();
        t6pl = new ArrayList<Integer>();
        
        t0cn = new ArrayList<Integer>();
        t4cn = new ArrayList<Integer>();
        t6cn = new ArrayList<Integer>();
        
        this.a1.add("night");
        this.a1.add("add");
        this.a1.add("add");
        this.a1.add("add");
        this.a1.add("sub");
        this.a1.add("abb");
        this.a1.add("stuff");
        this.a1.add("a");
        this.a1.add("took");
        this.a1.add("yams");

        this.a1.add("a");
        this.a1.add("a");
        this.a1.add("a");
        this.a1.add("a");
        this.a2.add("yell");
        this.a2.add("yell");
        this.a2.add("yell");
        this.a2.add("zoo");
        this.a2.add("z");
        this.a2.add("zzz");
        this.a2.add("1");
        this.a2.add("12");
        this.a2.add("123");
        
        this.a3.add("4444");
        this.a3.add("333");
        this.a3.add("1");
        this.a3.add("22");
        this.a3.add("55555");
        this.a3.add("7777777");
        this.a3.add("666666");
        
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(3);
        this.t4pl.add(3);
        this.t4pl.add(4);
        this.t4pl.add(4);
        this.t4pl.add(5);
        this.t4pl.add(5);
        this.t4pl.add(5);
        this.t4pl.add(5);

        this.t6pl.add(2);
        this.t6pl.add(3);
        this.t6pl.add(3);
        this.t6pl.add(3);
        this.t6pl.add(3);
        this.t6pl.add(4);
        this.t6pl.add(4);
        this.t6pl.add(3);
        
        this.t0cn.add(0);
        this.t0cn.add(0);
        this.t0cn.add(0);
        
        this.t4cn.add(15);
        this.t4cn.add(11);
        this.t4cn.add(4);
        
        this.t6cn.add(7);
        this.t6cn.add(4);
        this.t6cn.add(3);
        
        aupper = RBTree.intToBitList((int) (Math.pow(2, 32) - 1));
        eupper = new int[32];
        Arrays.fill(eupper, 1);
        
        alower = RBTree.intToBitList((int) (-1 * Math.pow(2, 32)));
        elower = new int[32];
        Arrays.fill(elower, 0);
        
        amid = RBTree.intToBitList(0);
        emid = new int[32];
        Arrays.fill(elower, 0);
        emid[31] = 1;
        
        sw0 = new int[32];
        sw1 = new int[32];
        sw5 = new int[32];
        sw31 = new int[32];
        sw32 = new int[32];
        sw33 = new int[32];
        sw63 = new int[32];
        sw64 = new int[32];
        sw991 = new int[32];
        sw992 = new int[32];
        sw993 = new int[32];
        for (int i = 0; i < 32; i++) {
            sw0[i] = i;
            sw1[i] = i + 1;
            sw5[i] = i + 5;
            sw31[i] = i - 1;
            sw32[i] = i + 1;
            sw33[i] = i + 2;
            sw63[i] = i;
            sw64[i] = i + 2;
            sw991[i] = i - 2;
            sw992[i] = i + 1;
            sw993[i] = i + 2;
        }
        // 0 -> 0 -> 1,2,3 ... 30,31,0
        
        // 1- > 1 -> 1,2,3 ... 30,31,0
        sw1[31] = 0;
        
        // 5 -> 5 -> 5,6,7 ... 30,31,0,1,2,3,4
        sw5[27] = 0;
        sw5[28] = 1;
        sw5[29] = 2;
        sw5[30] = 3;
        sw5[31] = 4;
        
        // 31 -> 31 -> 31,0,1,2 ... 28,29,30
        sw31[0] = 31;
        
        // 32 -> 0,1 -> 0,2,3,4 ... 28,29,30,31,1
        sw32[0] = 0;
        sw32[31] = 1;
        
        // 33 -> 1,1 -> 1,3,4,5 ... 29,30,31,0,2
        sw33[0] = 1;
        sw33[30] = 0;
        sw33[31] = 2;

        // 63 -> 31,1 -> 31,1,2,3 ... 29,30,0
        sw63[0] = 31;
        sw63[31] = 0;
        
        // 64 -> 0,2 -> 0,3,4,5, ... 30,31,1,2
        sw64[0] = 0;
        sw64[30] = 1;
        sw64[31] = 2;
        
        // 991 -> 31,30 -> 31,30,0,1 ... 27,28,29
        sw991[0] = 31;
        sw991[1] = 30;
        
        // 992 -> 0,0,1 -> 0,1,3,4,5 ... 30,31,2
        sw992[0] = 0;
        sw992[1] = 1;
        sw992[31] = 2;
        
        // 933 -> 1,0,1 -> 1,2,4,5,6 ... 30,31,0,3
        sw993[0] = 1;
        sw993[1] = 2;
        sw993[30] = 0;
        sw993[31] = 3;
        
        zeroonearray = new int[32];
        onezeorarray = new int[32];
        for (int i = 0; i < 32; i++) {
            if (i % 2 == 0) {
                zeroonearray[i] = 0;
                onezeorarray[i] = 1;
            }
            else {
                zeroonearray[i] = 1;
                onezeorarray[i] = 0;
            }
        }
    }
    
    /** Initialize the trees */
    private void initTree() {
        initData();
        tree1.build(this.a1);
        tree2.build(this.a1, -1);
        tree3.build(this.a1, 2);
        tree4.build(this.a1);
        tree4.build(this.a2);
        tree6.build(this.a3);
    }
    
    /** Test hashCode helper methods
     * @param t : The Tester class
     */
    public void testHashCodeHelpers(Tester t) {
        t.checkExpect(alower, elower);
        t.checkExpect(aupper, eupper);
        t.checkExpect(amid, emid);

        t.checkExpect(RBTree.bitListToInt(alower), (int) (-1 * Math.pow(
                        2, 32)));
        t.checkExpect(RBTree.bitListToInt(aupper), 
                (int) (Math.pow(2, 32) - 1));
        t.checkExpect(RBTree.bitListToInt(amid), 0);

        RBTree.bitListAddSecToFirst(alower, alower);
        t.checkExpect(alower, elower);

        RBTree.bitListAddSecToFirst(aupper, alower);
        t.checkExpect(aupper, eupper);

        RBTree.bitListAddSecToFirst(aupper, amid);
        t.checkExpect(aupper, amid);

        RBTree.bitListAddSecToFirst(emid, emid);
        amid[0] = 1;
        amid[31] = 0;
        t.checkExpect(emid, amid);

        t.checkExpect(RBTree.getSwapList(0), sw0);
        t.checkExpect(RBTree.getSwapList(1), sw1);
        t.checkExpect(RBTree.getSwapList(5), sw5);
        t.checkExpect(RBTree.getSwapList(31), sw31);
        t.checkExpect(RBTree.getSwapList(32), sw32);
        t.checkExpect(RBTree.getSwapList(33), sw33);
        t.checkExpect(RBTree.getSwapList(63), sw63);
        t.checkExpect(RBTree.getSwapList(64), sw64);
        t.checkExpect(RBTree.getSwapList(991), sw991);
        t.checkExpect(RBTree.getSwapList(992), sw992);
        t.checkExpect(RBTree.getSwapList(993), sw993);

        t.checkExpect(RBTree.reorderBitList(0, onezeorarray), onezeorarray);
        t.checkExpect(RBTree.reorderBitList(1, onezeorarray), zeroonearray);
        t.checkExpect(RBTree.reorderBitList(1, zeroonearray), onezeorarray);
        t.checkExpect(RBTree.reorderBitList(31, onezeorarray), zeroonearray);
        t.checkExpect(RBTree.reorderBitList(31, zeroonearray), onezeorarray);
    }
    /**
     * Tests the hashCode
     * 
     * @param t :
     *            The Tester class
     */
    public void testHashCode(Tester t) {
        /*
        t.checkExpect(this.tree0.hashCode(), this.tree0.hashCode());
        t.checkExpect(this.tree1.hashCode(), this.tree1.hashCode());
        t.checkExpect(this.tree2.hashCode(), this.tree2.hashCode());
        t.checkExpect(this.tree3.hashCode(), this.tree3.hashCode());
        t.checkExpect(this.tree4.hashCode(), this.tree4.hashCode());
        t.checkExpect(this.tree5.hashCode(), this.tree5.hashCode());
        t.checkExpect(this.tree6.hashCode(), this.tree6.hashCode());
        
        t.checkExpect(this.tree0.hashCode() != this.tree1.hashCode());
        t.checkExpect(this.tree0.hashCode() != this.tree2.hashCode());
        t.checkExpect(this.tree0.hashCode() != this.tree3.hashCode());
        t.checkExpect(this.tree0.hashCode() != this.tree4.hashCode());
        t.checkExpect(this.tree1.hashCode() != this.tree3.hashCode());
        t.checkExpect(this.tree1.hashCode() != this.tree4.hashCode());
        */
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

        t.checkExpect(this.strbylen.hashCode() != this.strbylex.hashCode());
        t.checkExpect(this.strbylen.hashCode(), this.strbylen.hashCode());
        t.checkExpect(this.strbylex.hashCode(), this.strbylex.hashCode());
        
        t.checkExpect(this.strbylen.toString(), "StringByLength");
        t.checkExpect(this.strbylex.toString(), "StringByLex");
    }
    
    /** Tests the IRBColor
     * @param t : The Tester class
     */
    public void testColors(Tester t) {
        RBRed red = new RBRed();
        RBBlack black = new RBBlack();

        t.checkExpect(!red.equals("RED"));
        t.checkExpect(!red.equals(black));
        t.checkExpect(red.equals(red));
        t.checkExpect(!red.isBlack());
        t.checkExpect(red.isRed());
        t.checkExpect(red.hashCode(), "RED".hashCode());
        t.checkExpect(red.toString(), "RED");
        
        t.checkExpect(!black.equals("BLACK"));
        t.checkExpect(!black.equals(red));
        t.checkExpect(black.equals(black));
        t.checkExpect(black.isBlack());
        t.checkExpect(!black.isRed());
        t.checkExpect(black.hashCode(), "BLACK".hashCode());
        t.checkExpect(black.toString(), "BLACK");
        
        
    }
    
    /** Tests RBTreeVisitor
     * @param t : The Tester class
     */
    public void testVistor(Tester t) {
        
        t.checkExpect(this.tree0.accept(this.pathlens), this.t0pl);
        t.checkExpect(this.tree4.accept(this.pathlens), this.t4pl);
        t.checkExpect(this.tree6.accept(this.pathlens), this.t6pl);

        t.checkExpect(this.tree0.accept(this.countnode), this.t0cn);
        t.checkExpect(this.tree4.accept(this.countnode), this.t4cn);
        t.checkExpect(this.tree6.accept(this.countnode), this.t6cn);

        t.checkExpect(this.tree0.accept(this.blackheight), 0);
        t.checkExpect(this.tree1.accept(this.blackheight), this.tree1
                .leftBlackHeight() + 1);
        t.checkExpect(this.tree4.accept(this.blackheight), this.tree4
                .leftBlackHeight() + 1);
        t.checkExpect(this.tree6.accept(this.blackheight), this.tree6
                .leftBlackHeight() + 1);

        t.checkExpect(this.tree0.accept(this.height), 0);
        t.checkExpect(this.tree1.accept(this.height), 4);
        t.checkExpect(this.tree4.accept(this.height), 5);
        t.checkExpect(this.tree6.accept(this.height), 4);
    }
    
    /** Tests empty Tree
     * @param t : The Tester class */ 
    public void testEmptyTree(Tester t) {
        // Black Box tests
        RBTree<String> temptree = this.tree0;
        this.tree0.balance();
        t.checkExpect(this.tree0, temptree);
        t.checkExpect(!this.tree0.contains("five"));
        t.checkExpect(this.tree0.equals(this.tree0));
        t.checkExpect(!this.tree0.equals(this.tree1));
        t.checkExpect(this.tree0.repOK());
        t.checkExpect(this.tree0.toString(), "[]");
        t.checkExpect(this.tree0.active, 0);
        Iterator<String> it = this.tree0.iterator();
        t.checkExpect(this.tree0.active, 0);
        t.checkExpect(!it.hasNext());

        // Test the Exceptions for Iterator
        t.checkException(new NoSuchElementException(
                "next: The is no next element in the "
                        + "Red-Black Tree Iterator"), 
                        it, "next");
        t.checkExpect(this.tree0.bad);
        t.checkException(new RuntimeException(
                "An exception has already been thrown" + ", cannot continue"),
                it, "next");
        t.checkException(new RuntimeException(
                "An exception has already been thrown" + ", cannot continue"),
                it, "hasNext");
        this.tree0.bad = false;
        t.checkException(new UnsupportedOperationException(
                "remove: This method is not avalible"), it, "remove");
        t.checkExpect(this.tree0.bad);
        this.tree0.bad = false;
        
        // Test Fields for Empty
        t.checkExpect(this.tree0.size, 0);
        t.checkExpect(this.tree0.color, new RBBlack());
        t.checkExpect(this.tree0.comp, this.strbylex);
        t.checkExpect(this.tree0.left, null);
        t.checkExpect(this.tree0.right, null);
        t.checkExpect(this.tree0.val, null);
          
        // Test protected methods
        t.checkExpect(!this.tree0.hasLeft());
        t.checkExpect(!this.tree0.hasRight());
        t.checkExpect(!this.tree0.isBranch());
        t.checkExpect(this.tree0.isEmpty());
        t.checkExpect(this.tree0.isRoot());
        t.checkExpect(this.tree0.greatest(), null);
        
        IRBColor tempcolor = this.tree0.color;
        this.tree0.ifRootToBlack();
        t.checkExpect(tempcolor, this.tree0.color);
        
        t.checkExpect(this.tree0.least(), null);
        t.checkExpect(this.tree0.leftBlackHeight(), 0);
        t.checkExpect(this.tree0.leftColor(), new RBBlack());
        t.checkExpect(this.tree0.rightBlackHeight(), 0);
        t.checkExpect(this.tree0.rightColor(), new RBBlack());
        
        t.checkException(new NoSuchElementException(
                "get : There is no element 0 within this Binary Tree"),
                this.tree0, "get", 0);
        
        // Test Insert on Empty
        this.tree0.insert("");
        t.checkExpect(this.tree0.repOK());
        t.checkExpect(!this.tree0.isEmpty());
        t.checkExpect(this.tree0.size, 1);
        t.checkExpect(this.tree0.contains(""));
        
        // Test build exceptions
        Iterator<String> siter = this.tree0.iterator();
        t.checkException(new ConcurrentModificationException(
                "build: Cannot build while an Iterator "
                        + "is running over this Red-Black Tree"), this.tree0,
                "build", this.a1);
        t.checkException(new ConcurrentModificationException(
                "build: Cannot build while an Iterator "
                        + "is running over this Red-Black Tree"), this.tree0,
                "build", this.a1, 1);
        t.checkExpect(siter, siter);
    }

    /** Tests RBTree public methods and fields
     * @param t : The Tester class */ 
    public void testRBTreePublic(Tester t) {
        // Test Size
        t.checkExpect(this.tree1.size, 8);
        t.checkExpect(this.tree2.size, 8);
        t.checkExpect(this.tree3.size, 2);
        t.checkExpect(this.tree6.size, 7);

        // Test RepOK
        t.checkExpect(this.tree1.repOK());
        t.checkExpect(this.tree2.repOK());
        t.checkExpect(this.tree3.repOK());
        t.checkExpect(this.tree6.repOK());

        // Test hashCode, equals, contains, and toString
        t.checkExpect(this.tree1.hashCode(), this.tree2.hashCode());
        t.checkExpect(this.tree1.hashCode(), this.tree1.hashCode());
        t.checkExpect(this.tree1.hashCode() != this.tree4.hashCode());
        t.checkExpect(this.tree2.hashCode() != this.tree4.hashCode());
        t.checkExpect(this.tree6.hashCode(), this.tree6.hashCode());
        t.checkExpect(this.tree1.equals(this.tree2));
        t.checkExpect(!this.tree1.equals(this.tree3));
        t.checkExpect(!this.tree4.equals(this.tree3));
        t.checkExpect(!this.tree1.equals(this.tree4));
        t.checkExpect(!this.tree1.equals(this.tree6));
        t.checkExpect(this.tree6.equals(this.tree6));
        t.checkExpect(!this.tree4.equals(this.tree1));
        t.checkExpect(!this.tree5.equals(this.tree1));
        t.checkExpect(!this.tree1.equals(this.tree5));
        t.checkExpect(!this.tree4.equals(""));
        t.checkExpect(this.tree1.contains("night"));
        t.checkExpect(this.tree1.contains("stuff"));
        t.checkExpect(!this.tree1.contains("might"));
        t.checkExpect(!this.tree1.contains("ten"));
        t.checkExpect(this.tree1.toString(),
                "[[[[null  a/RED  null]  abb/BLACK  null]  "
                        + "add/BLACK  [null  night/BLACK  null]]  stuff/BLACK"
                        + "  [[null  sub/BLACK  null]  took/BLACK  [null  "
                        + "yams/BLACK  null]]]");
        t.checkExpect(this.tree6.toString(), 
                "[[null  1/BLACK  [null  22/RED  null]]  333/BLACK  [[null  " +
                "4444/BLACK  null]  55555/RED  [[null  666666/RED  null]  " +
                "7777777/BLACK  null]]]");

        // Test/Stack Iterator
        Iterator<String> iter = this.tree1.iterator();
        t.checkExpect(iter.next(), "a");
        t.checkExpect(iter.next(), "abb");
        t.checkExpect(iter.next(), "add");
        t.checkExpect(iter.next(), "night");
        t.checkExpect(iter.next(), "stuff");
        t.checkExpect(iter.next(), "sub");
        t.checkExpect(this.tree1.active, 1);
        t.checkExpect(iter.next(), "took");
        t.checkExpect(iter.next(), "yams");
        t.checkExpect(!iter.hasNext());

        Stack<String> stk0 = this.tree1.toStack();
        t.checkExpect(stk0.pop(), "a");
        t.checkExpect(stk0.pop(), "abb");
        t.checkExpect(stk0.pop(), "add");
        t.checkExpect(stk0.pop(), "night");
        t.checkExpect(stk0.pop(), "stuff");
        t.checkExpect(stk0.pop(), "sub");
        t.checkExpect(this.tree1.active, 0);
        t.checkExpect(stk0.pop(), "took");
        t.checkExpect(stk0.pop(), "yams");
        t.checkExpect(stk0.empty());
        
        iter = this.tree6.iterator();
        t.checkExpect(iter.next(), "1");
        t.checkExpect(iter.next(), "22");
        t.checkExpect(iter.next(), "333");
        t.checkExpect(iter.next(), "4444");
        t.checkExpect(iter.next(), "55555");
        t.checkExpect(iter.next(), "666666");
        t.checkExpect(iter.next(), "7777777");
        
        stk0.push("123");
        stk0.push("321");
        stk0 = this.tree6.toStack(stk0);
        t.checkExpect(stk0.pop(), "1");
        t.checkExpect(stk0.pop(), "22");
        t.checkExpect(stk0.pop(), "333");
        t.checkExpect(stk0.pop(), "4444");
        t.checkExpect(stk0.pop(), "55555");
        t.checkExpect(stk0.pop(), "666666");
        t.checkExpect(stk0.pop(), "7777777");
        t.checkExpect(stk0.pop(), "321");
        t.checkExpect(stk0.pop(), "123");

        // Test the get over the index limit
        t.checkExpect(!this.tree1.bad);
        t.checkException(new NoSuchElementException(
                "get : There is no element 8 within this Binary Tree"),
                this.tree1, "get", 8);
        t.checkExpect(this.tree1.bad);
        this.tree1.bad = false;
    }
    
    /** Tests RBTree protected fields
     * @param t : The Tester class */ 
    public void testRBTreeFields(Tester t) {
        // Test the fields
        t.checkExpect(this.tree1.active, 0);
        t.checkExpect(this.tree1.color, new RBBlack());
        t.checkExpect(this.tree1.val, "stuff");
        t.checkExpect(this.tree1.comp, this.strbylex);

        RBBranch<String> leftleft0 = new RBBranch<String>(this.strbylex, "a");
        RBBranch<String> left0 = new RBBranch<String>(this.strbylex, "abb",
                leftleft0, null);
        RBBranch<String> right0 = new RBBranch<String>(this.strbylex, "night",
                null, null);
        RBBranch<String> bt0 = new RBBranch<String>(this.strbylex, "add",
                left0, right0);
        t.checkExpect(this.tree1.left, bt0);

        t.checkExpect(this.tree1.active, 0);
        t.checkExpect(this.tree1.color, new RBBlack());
        t.checkExpect(this.tree1.val, "stuff");

        RBBranch<String> left1 = new RBBranch<String>(this.strbylex, "sub",
                null, null);
        RBBranch<String> right1 = new RBBranch<String>(this.strbylex, "yams",
                null, null);
        RBBranch<String> bt1 = new RBBranch<String>(this.strbylex, "took",
                left1, right1);
        t.checkExpect(this.tree1.right, bt1);
        

        IRBColor c0 = left1.color;
        left1.ifRootToBlack();
        t.checkExpect(left1.color, c0);

    }
    
    /** Tests RBTree protected methods
     * @param t : The Tester class */ 
    public void testRBTreeMethods(Tester t) {
        // Test the protected methods
        t.checkExpect(this.tree1.hasLeft());
        t.checkExpect(this.tree1.left.isBranch());
        t.checkExpect(!this.tree1.left.isRoot());
        t.checkExpect(this.tree1.hasRight());
        t.checkExpect(this.tree1.isRoot());
        t.checkExpect(!this.tree1.isBranch());
        t.checkExpect(!this.tree1.isEmpty());
        t.checkExpect(this.tree1.get(0), "a");
        t.checkExpect(this.tree1.get(5), "sub");
        t.checkExpect(this.tree1.greatest(), "yams");

        t.checkExpect(this.tree6.hasLeft());
        t.checkExpect(this.tree6.left.isBranch());
        t.checkExpect(!this.tree6.left.isRoot());
        t.checkExpect(this.tree6.hasRight());
        t.checkExpect(this.tree6.isRoot());
        t.checkExpect(!this.tree6.isBranch());
        t.checkExpect(!this.tree6.isEmpty());
        t.checkExpect(this.tree6.get(0), "1");
        t.checkExpect(this.tree6.get(5), "666666");
        t.checkExpect(this.tree6.greatest(), "7777777");
        
        IRBColor c = this.tree1.color;
        this.tree1.ifRootToBlack();
        t.checkExpect(this.tree1.color, c);

        this.tree1.color = new RBRed();
        this.tree1.ifRootToBlack();
        t.checkExpect(this.tree1.color, new RBBlack());

        t.checkExpect(this.tree1.least(), "a");
        t.checkExpect(this.tree1.leftBlackHeight(), 2);
        t.checkExpect(this.tree1.leftColor(), new RBBlack());
        t.checkExpect(this.tree1.rightBlackHeight(), 2);
        t.checkExpect(this.tree1.rightColor(), new RBBlack());

        c = this.tree6.color;
        this.tree6.ifRootToBlack();
        t.checkExpect(this.tree6.color, c);

        this.tree6.color = new RBRed();
        this.tree6.ifRootToBlack();
        t.checkExpect(this.tree6.color, new RBBlack());

        t.checkExpect(this.tree6.least(), "1");
        t.checkExpect(this.tree6.leftBlackHeight(), 1);
        t.checkExpect(this.tree6.leftColor(), new RBBlack());
        t.checkExpect(this.tree6.rightBlackHeight(), 1);
        t.checkExpect(this.tree6.rightColor(), new RBRed());
        
        t.checkExpect(this.tree1.size, 8);
        this.tree1.insert("yams");
        t.checkExpect(this.tree1.repOK());
        t.checkExpect(this.tree1.size, 8);
        this.tree1.insert("aaaa");
        t.checkExpect(this.tree1.repOK());
        t.checkExpect(this.tree1.size, 9);
        this.tree1.insert("zzzzzz");
        t.checkExpect(this.tree1.repOK());
        t.checkExpect(this.tree1.size, 10);
        
        this.tree5.comp = null;
        t.checkExpect(!this.tree5.repOK());
    }
    
    /** Tests RBTree balance method
     * @param t : The Tester class */ 
    public void testRBTreeBalance(Tester t) {

        // Test LL balance
        RBTree<String> testroot = RBTree.binTree(this.strbylex);
        testroot.insert("november");
        testroot.insert("hotel");
        RBBranch<String> newnode0 = 
            new RBBranch<String>(this.strbylex, "echo");
        testroot.left.left = newnode0;
        t.checkExpect(testroot.toString(),
                "[[[null  echo/RED  null]  hotel/RED  null]"
                        + "  november/BLACK  null]");
        testroot.balance();
        t.checkExpect(testroot.toString(), "[[null  echo/BLACK  null]  "
                + "hotel/BLACK  [null  november/BLACK  null]]");

        // Test LR balance
        testroot.insert("foxtrot");
        RBBranch<String> newnode1 = 
            new RBBranch<String>(this.strbylex, "golf");
        testroot.left.right.right = newnode1;
        t.checkExpect(testroot.toString(), "[[null  echo/BLACK  [null  "
                + "foxtrot/RED  [null  golf/RED  null]]]  hotel/BLACK  "
                + "[null  november/BLACK  null]]");
        testroot.left.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/BLACK  null]  "
                + "foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK  "
                + "[null  november/BLACK  null]]");

        // Test RL
        testroot.insert("lemma");
        RBBranch<String> newnode2 = 
            new RBBranch<String>(this.strbylex, "india");
        testroot.right.left.left = newnode2;
        t.checkExpect(testroot.toString(), "[[[null  echo/BLACK  null]  "
                + "foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK  "
                + "[[[null  india/RED  null]  lemma/RED  null]  november/BLACK"
                + "  null]]");
        testroot.right.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/BLACK  null]  "
                + "foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK  "
                + "[[null  india/BLACK  null]  lemma/RED  "
                + "[null  november/BLACK  null]]]");

        // Test RR
        testroot.insert("xray");
        RBBranch<String> newnode3 = 
            new RBBranch<String>(this.strbylex, "zulu");
        testroot.right.right.right.right = newnode3;
        t.checkExpect(testroot.toString(), "[[[null  echo/BLACK  null]  "
                + "foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK  "
                + "[[null  india/BLACK  null]  lemma/RED  [null  november/"
                + "BLACK  [null  xray/RED  [null  zulu/RED  null]]]]]");
        testroot.right.right.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/BLACK  null]"
                + "  foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK"
                + "  [[null  india/BLACK  null]  lemma/RED  [[null  "
                + "november/BLACK  null]  xray/RED  [null  zulu/BLACK  "
                + "null]]]]");
        testroot.balance();
        t.checkExpect(testroot.toString(), "[[[[null  echo/BLACK  null]"
                + "  foxtrot/RED  [null  golf/BLACK  null]]  hotel/BLACK "
                + " [null  india/BLACK  null]]  lemma/BLACK  [[null  "
                + "november/BLACK  null]  xray/BLACK  [null  zulu/BLACK "
                + " null]]]");
    }
}