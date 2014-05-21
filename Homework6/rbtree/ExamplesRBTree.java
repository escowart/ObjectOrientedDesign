package rbtree;


import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

/** A class the tester the RBTree class
 *  @author Edwin Cowart
 *  @version 2013.11.1 */
public class ExamplesRBTree {

    /** Construct a tester for the RBTree classes
     */
    public ExamplesRBTree() {
        this.initTree();
    }
    
    private StringByLex strbylex = new StringByLex();
    private StringByLength strbylen = new StringByLength();
    private RBTree tree0 = RBTree.binTree(this.strbylex);
    private RBTree tree1 = RBTree.binTree(this.strbylex);
    private RBTree tree2 = RBTree.binTree(this.strbylex);
    private RBTree tree3 = RBTree.binTree(this.strbylex);
    private RBTree tree4 = RBTree.binTree(this.strbylex);
    private RBTree tree5 = RBTree.binTree(this.strbylen);
    
    private ArrayList<String> a1 = new ArrayList<String>();

    private ArrayList<String> a2 = new ArrayList<String>();
    
    /** Initialize the data */
    private void initData() {
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
    }
    
    /** Initialize the trees */
    private void initTree() {
        initData();
        tree1.build(this.a1);
        tree2.build(this.a1, -1);
        tree3.build(this.a1, 2);
        tree4.build(this.a1);
        tree4.build(this.a2);
    }
    
    /** Tests empty Tree
     * @param t : The Tester class */ 
    public void testEmptyTree(Tester t) {
        // Black Box tests
        RBTree temptree = this.tree0;
        this.tree0.balance();
        t.checkExpect(this.tree0, temptree);
        t.checkExpect(!this.tree0.contains("five"));
        t.checkExpect(this.tree0.equals(this.tree0));
        t.checkExpect(!this.tree0.equals(this.tree1));
        t.checkExpect(this.tree0.hashCode(), this.strbylex.hashCode());
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
        t.checkExpect(this.tree0.str, null);
          
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
        t.checkExpect(this.tree0.leftBlackHeight(), 1);
        t.checkExpect(this.tree0.leftColor(), new RBBlack());
        t.checkExpect(this.tree0.rightBlackHeight(), 1);
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

        // Test RepOK
        t.checkExpect(this.tree1.repOK());
        t.checkExpect(this.tree2.repOK());
        t.checkExpect(this.tree3.repOK());

        // Test hashCode, equals, contains, and toString
        t.checkExpect(this.tree1.hashCode(), this.tree2.hashCode());
        t.checkExpect(this.tree1.equals(this.tree2));
        t.checkExpect(!this.tree1.equals(this.tree3));
        t.checkExpect(!this.tree4.equals(this.tree3));
        t.checkExpect(!this.tree1.equals(this.tree4));
        t.checkExpect(!this.tree4.equals(this.tree1));
        t.checkExpect(!this.tree5.equals(this.tree1));
        t.checkExpect(!this.tree1.equals(this.tree5));
        t.checkExpect(!this.tree4.equals(""));
        t.checkExpect(this.tree1.contains("night"));
        t.checkExpect(this.tree1.contains("stuff"));
        t.checkExpect(!this.tree1.contains("might"));
        t.checkExpect(!this.tree1.contains("ten"));
        t.checkExpect(this.tree1.toString(),
                "[[[[null  a/Red  null]  abb/Black  null]  "
                        + "add/Black  [null  night/Black  null]]  stuff/Black"
                        + "  [[null  sub/Black  null]  took/Black  [null  "
                        + "yams/Black  null]]]");

        // Test Iterator
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
        t.checkExpect(this.tree1.str, "stuff");
        t.checkExpect(this.tree1.comp, this.strbylex);

        RBBranch leftleft0 = new RBBranch(this.strbylex, "a");
        RBBranch left0 = new RBBranch(this.strbylex, "abb", leftleft0, null);
        RBBranch right0 = new RBBranch(this.strbylex, "night", null, null);
        RBBranch bt0 = new RBBranch(this.strbylex, "add", left0, right0);
        t.checkExpect(this.tree1.left, bt0);

        t.checkExpect(this.tree1.active, 0);
        t.checkExpect(this.tree1.color, new RBBlack());
        t.checkExpect(this.tree1.str, "stuff");

        RBBranch left1 = new RBBranch(this.strbylex, "sub", null, null);
        RBBranch right1 = new RBBranch(this.strbylex, "yams", null, null);
        RBBranch bt1 = new RBBranch(this.strbylex, "took", left1, right1);
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

        IRBColor c = this.tree1.color;
        this.tree1.ifRootToBlack();
        t.checkExpect(this.tree1.color, c);

        this.tree1.color = new RBRed();
        this.tree1.ifRootToBlack();
        t.checkExpect(this.tree1.color, new RBBlack());

        t.checkExpect(this.tree1.least(), "a");
        t.checkExpect(this.tree1.leftBlackHeight(), 3);
        t.checkExpect(this.tree1.leftColor(), new RBBlack());
        t.checkExpect(this.tree1.rightBlackHeight(), 3);
        t.checkExpect(this.tree1.rightColor(), new RBBlack());

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
        
    }
    
    /** Tests RBTree balance method
     * @param t : The Tester class */ 
    public void testRBTreeBalance(Tester t) {
    
        // Test LL balance
        RBTree testroot = RBTree.binTree(this.strbylex);
        testroot.insert("november");
        testroot.insert("hotel");
        RBBranch newnode0 = new RBBranch(this.strbylex, "echo");
        testroot.left.left = newnode0;
        t.checkExpect(testroot.toString(),
                "[[[null  echo/Red  null]  hotel/Red  null]"
                        + "  november/Black  null]");
        testroot.balance();
        t.checkExpect(testroot.toString(), "[[null  echo/Black  null]  "
                + "hotel/Black  [null  november/Black  null]]");

        // Test LR balance
        testroot.insert("foxtrot");
        RBBranch newnode1 = new RBBranch(this.strbylex, "golf");
        testroot.left.right.right = newnode1;
        t.checkExpect(testroot.toString(), "[[null  echo/Black  [null  "
                + "foxtrot/Red  [null  golf/Red  null]]]  hotel/Black  "
                + "[null  november/Black  null]]");
        testroot.left.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/Black  null]  "
                + "foxtrot/Red  [null  golf/Black  null]]  hotel/Black  "
                + "[null  november/Black  null]]");

        // Test RL
        testroot.insert("lemma");
        RBBranch newnode2 = new RBBranch(this.strbylex, "india");
        testroot.right.left.left = newnode2;
        t.checkExpect(testroot.toString(), "[[[null  echo/Black  null]  "
                + "foxtrot/Red  [null  golf/Black  null]]  hotel/Black  "
                + "[[[null  india/Red  null]  lemma/Red  null]  november/Black"
                + "  null]]");
        testroot.right.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/Black  null]  "
                + "foxtrot/Red  [null  golf/Black  null]]  hotel/Black  "
                + "[[null  india/Black  null]  lemma/Red  "
                + "[null  november/Black  null]]]");

        // Test RR
        testroot.insert("xray");
        RBBranch newnode3 = new RBBranch(this.strbylex, "zulu");
        testroot.right.right.right.right = newnode3;
        t.checkExpect(testroot.toString(), "[[[null  echo/Black  null]  "
                + "foxtrot/Red  [null  golf/Black  null]]  hotel/Black  "
                + "[[null  india/Black  null]  lemma/Red  [null  november/"
                + "Black  [null  xray/Red  [null  zulu/Red  null]]]]]");
        testroot.right.right.balance();
        t.checkExpect(testroot.toString(), "[[[null  echo/Black  null]"
                + "  foxtrot/Red  [null  golf/Black  null]]  hotel/Black"
                + "  [[null  india/Black  null]  lemma/Red  [[null  "
                + "november/Black  null]  xray/Red  [null  zulu/Black  "
                + "null]]]]");
        testroot.balance();
        t.checkExpect(testroot.toString(), "[[[[null  echo/Black  null]"
                + "  foxtrot/Red  [null  golf/Black  null]]  hotel/Black "
                + " [null  india/Black  null]]  lemma/Black  [[null  "
                + "november/Black  null]  xray/Black  [null  zulu/Black "
                + " null]]]");
        
        // Mis. Test
        this.tree5.comp = null;
        t.checkExpect(!this.tree5.repOK());
    }
}