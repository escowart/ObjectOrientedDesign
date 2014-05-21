import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

/* Edwin Cowart
 * escowart@ccs.neu.edu
 */

/** A Class Representing a Binary Tree 
 *  The lowest element in the BTree is at the 0 index
 *  Every index but the first is 1 + the previous lower index 
 *  @author Edwin Cowart
 *  @version 2013.10.8
 *   
 * Abstraction Function:
 * AF(c) = if c.str = null then [] else AF(c.left) + [c.str] + AF(c.right)
 * 
 * rep Invariant:
 * I(c) = c.comp != null && 
 *        (c.str = null ||
 *         ((c.left != null => I(c.left) && c.left.greatest < c.str) &&
 *          (c.right != null => I(c.right) && c.right.least > c.str)))
 */
public class BTree implements Iterable<String> {

    /** The Comparator for the Sorting of the Binary Tree */
    private Comparator<String> comp;
    
    /** The String of this node of the Binary Tree */
    private String str;
    
    /** The left branch of the Binary Tree */
    private BTree left;
    
    /** The right branch of the Binary Tree */
    private BTree right;
    
    /** The number of active iterators over this Binary Tree */
    private int active = 0;
    
    /** Has this run of the code gone bad i.e. thrown an exception */
    private boolean bad = false;
    
    /** Constructor for the BTree given a Comparator for Strings
     * @param comp : Any Comparator<String>
     */
    private BTree(Comparator<String> comp) {
        this.comp = comp;
    }
    
    /** Constructor for the BTree given both a Comparator for Strings 
     *  and a String 
     * @param comp : Any Comparator<String>
     * @param str : Any String
     */
    private BTree(Comparator<String> comp, String str) {
        this.comp = comp;
        this.str = str;
    }
    
    /** Is the representation of the Binary Tree valid
     * @return : A boolean of whether the representation is okay */
    public boolean repOK() {
        return (this instanceof BTree)
                && (this.comp != null)
                && (((this.str == null) && (this.left == null) 
                        && (this.right == null))
                        || ((this.str instanceof String)
                        && ((this.left == null)
                        || ((this.left instanceof BTree)
                                && this.left.repOK() && (this.comp.compare(
                                        this.left.greatest(), this.str) < 0)))
                                && ((this.right == null) 
                                        || ((this.right instanceof BTree)
                                        && this.right.repOK() 
                                        && (this.comp.compare(this.right
                                                .least(), this.str) > 0)))));
    }
        
    
    /** Get the greatest value in the Binary Tree
     * @return : A String with the greatest value in BTree */
    private String greatest() {
        if (this.hasRight()) {
            return this.right.greatest();
        }
        else {
            return this.str;
        }
    }
    
    /** Get the least value in the Binary Tree
     * @return : A String with the least value in BTree */
    private String least() {
        if (this.hasLeft()) {
            return this.left.least();
        }
        else {
            return this.str;
        }
    }
    
    /** Make a binary tree given a String Comparator 
     * @param comp : Any String Comparator
     * @return : An empty binary tree with the given comparator 
     *  as its Comparator */
    public static BTree binTree(Comparator<String> comp) {
        return new BTree(comp);
    }
    
    /** Insert the Elements of the given Iterable to this BTree
     * @param iter : Any Iterable<String> */
    public void build(Iterable<String> iter) {
        if (this.active == 0) {
            for (String s : iter) {
                this.insert(s);
            }
        }
        else {
            throw new ConcurrentModificationException(
                    "build: Cannot build while an Iterator " +
                    "is running over this BTree");
        }
    }

    /** Create a Binary Tree Iterator from this Binary Tree
     * @return : The Iterator for this Binary Tree */
    public BTreeIterator iterator() {
        return new BTreeIterator(this);
    }
    
    /** Insert the given String into this BTree
     * @param s : Any String other than those already in the BTree */
    private void insert(String s) {
        if (this.isEmpty()) {
            this.str = s;
        } 
        else if (comp.compare(s, this.str) < 0) {
            if (this.hasLeft()) {
                this.left.insert(s);
            } 
            else {
                this.left = new BTree(this.comp, s);
            }
        }
        else if (comp.compare(s, this.str) > 0) {
            if (this.hasRight()) {
                this.right.insert(s);
            } 
            else {
                this.right = new BTree(this.comp, s);
            }
        }
    }
    
    /** Does this BTree have a left Node 
     *  (Something other than null in the left position)
     *  @return a boolean of the above question */
    private boolean hasLeft() {
        return this.left != null;
    }
    
    /** Does this BTree have a right Node 
     *  (Something other than null in the right position)
     *  @return a boolean of the above question */
    private boolean hasRight() {
        return this.right != null;
    }
    
    /** How many elements does this BTree Have?
     * @return : an int which is the number of elements in the BTree */
    private int size() {
        if (this.isEmpty()) {
            return 0;
        }
        else {
            return 1 + this.leftSize() + this.rightSize();
        }
    }
    
    /** How many elements does the left branch of this BTree Have?
     * @return : an int which is the number of elements it the left branch */
    private int leftSize() {
        if (this.hasLeft()) {
            return this.left.size();
        }
        else {
            return 0;
        }
    }
    
    /** How many elements does the right branch of this BTree Have?
     * @return : an int which is the number of elements it the right branch */
    private int rightSize() {
        if (this.hasRight()) {
            return this.right.size();
        }
        else {
            return 0;
        }
    }
    
    /** Get the String at the given index
     * @param i : An index of the element
     * @return : The String element */
    private String get(int i) {
        if (i < 0 || i >= this.size()) {
            this.bad = true;
            throw new NoSuchElementException(
                    "get : There is no element " + i 
                    + " within this Binary Tree");
        }
        else {
            int lsize = this.leftSize() ;
            if (lsize == i) {
                return this.str;
            }
            else if (lsize > i) {
                return this.left.get(i);
            }
            else {
                return this.right.get(i - (1 + lsize));
            }
        }
    }
    
    /** Is this Binary Tree an empty tree?
     * @return : a boolean of whether this tree is empty */
    private boolean isEmpty() {
        return this.str == null;
    }
    
    /** Is this Binary Tree equal to the given Object
     * @param obj : Any Object
     * @return : a boolean of whether they are equal */
    public boolean equals(Object obj) {
        if (obj instanceof BTree) {
            BTree bt = (BTree) obj;
            if (this.comp.equals(bt.comp) && this.size() == bt.size()) {
                Iterator<String> it0 = this.iterator();
                Iterator<String> it1 = this.iterator();
                for (int i = 0; i < this.size(); i++) {
                    if (!it0.next().equals(it1.next())) {
                        return false;
                    }
                }
                return true;
            } 
            else {
                return false;
            }
        } 
        else {
            return false;
        }
    }
    
    /** Create a String version of all the elements in this Binary Tree 
     *  in order of the Comparator
     * @return : A String of all the elements */
    public String toString() {
        int i;
        String s = "";
        if (!this.isEmpty()) {
            for (i = 0; i < this.size() - 1; i++) {
                s += this.get(i) + ", ";
            }
            s += this.get(i);
        }
        return s;
    }
    
    /** Creates an integer from this Binary Tree which will sometimes be unique
     * @return : an int of the hashCodes of each element */
    public int hashCode() {
        int h = 0;
        for (int i = 0; i < this.size(); i++) {
            h += this.get(i).hashCode();
        }
        return h;
    }
    
    /** A class representing the Binary Tree Iterator which 
     *  can iterate through any binary tree in order
     * @author Edwin Cowart
     * @version 2013.10.6
     */
    class BTreeIterator implements Iterator<String> {
        
        /** The Binary Tree being iterated over */
        private BTree bt;
        
        /** The next index in the iterator */
        private int index;
        
        /** Construct a Binary Tree Iterator with the given Binary Tree
         * @param bt : Any BTree */
        private BTreeIterator(BTree bt) {
            this.bt = bt;
            active++;
            index = 0;
        }

        /** Does the BTreeIterator have a next element?
         * @return : boolean of whether this Iterator has a next element */
        public boolean hasNext() {
            if (bad) {
                throw new RuntimeException(
                        "An exception has already been thrown" +
                        ", cannot continue");
            }
            else {
                return this.bt.size() > index;
            }
        }

        /** Return the next element in the Binary Tree Iterator
         * @return : String of the next element */
        public String next() {
            if (bad) {
                throw new RuntimeException(
                        "An exception has already been thrown" +
                        ", cannot continue");
            }
            else if (hasNext()) {
                if (index == this.bt.size() - 1) {
                    active--;
                }
                return this.bt.get(index++);
            } 
            else {
                bad = true;
                throw new NoSuchElementException(
                        "next: The is no next element in the " +
                        "Binary Tree Iterator");
            }
        }

        /** Remove the previous element from this Binary Tree Iterator */
        public void remove() {
            bad = true;
            throw new UnsupportedOperationException(
                    "remove: This method is not avalible");
        }
        
    }

}

/** A Class Representing a Comparator that compares Strings by their lengths
*  @author Edwin Cowart
*  @version 2013.10.8 */
class StringByLength implements Comparator<String> {

    /** Compare the lengths of the two given strings
     * @param s0 : Any String 
     * @param s1 : Any String
     * @return : 
     * - 0 if their lengths are equal
     * - -int if the first String's, s0's, length is shorter
     * - +int if the first String's, s0's, length is longer */
    public int compare(String s0, String s1) {
        return s0.length() - s1.length();
    }
    
    /** Is the given object equal to the StringByLength Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a StringByLength */
    public boolean equal(Object obj) {
        return obj instanceof StringByLength;
    }
    
}

/** A Class Representing a Comparator that compares Strings 
 *   by their lexicographical order
*  @author Edwin Cowart
*  @version 2013.10.8 */
class StringByLex implements Comparator<String> {

    /** Compare the lexicographical order of the two given strings
     * @param s0 : Any String 
     * @param s1 : Any String
     * @return : 
     * - 0 if their the same String 
     * - -int if the first String, s0, comes first 
     * - +int if the first String, s0, comes second */
    public int compare(String s0, String s1) {
        return s0.compareTo(s1);
    }
    
    /** Is the given object equal to the StringByLex Comparator
     * @param obj : Any Object
     * @return : boolean of whether the given object is a StringByLex */
    public boolean equal(Object obj) {
        return obj instanceof StringByLex;
    }
    
}

/** A Class for testing the BTree class 
 * @author Edwin
 * @version 2013.10.8
 */
class ExamplesBTree {

    private StringByLex slex = new StringByLex();
    private StringByLength slen = new StringByLength();
    
    private BTree e;
    private BTree b0;
    private BTree b1;
    private BTree b11;
    private BTree b2;
    private BTree b3;
    private BTree b4;
    
    private ArrayList<String> a0 = new ArrayList<String>();
    private ArrayList<String> a1 = new ArrayList<String>();
    private ArrayList<String> a2 = new ArrayList<String>();
    private ArrayList<String> a3 = new ArrayList<String>();
    private ArrayList<String> a4 = new ArrayList<String>();
    
    /** Initialize the Arrays to Be sent into the BTree */
    private void initA() {
        a0.add("");
        
        a1.add("e");
        a1.add("a");
        a1.add("h");
        a1.add("y");
        a1.add("w");
        a1.add("f");
        a1.add("d");
        a1.add("v");
        

        a2.add("a");
        a2.add("b");
        a2.add("c");
        a2.add("d");
        a2.add("e");
        a2.add("f");
        a2.add("g");
        
        a3.add("sevense");
        a3.add("sixsix");
        a3.add("fivef");
        a3.add("four");
        a3.add("thr");
        a3.add("tw");
        a3.add("o");
        a3.add("");
        
        a4.add("thr");
        a4.add("four");
        a4.add("o");
        a4.add("tw");
        a4.add("");
        a4.add("sixsix");
        a4.add("fivef");
        a4.add("nineninen");
    }
    
    /** Initialize the Binary Tree which will be tested later */
    private void initB() {
        this.initA();
        e = BTree.binTree(slex);
        b0 = BTree.binTree(slex);
        b1 = BTree.binTree(slex);
        b11 = BTree.binTree(slex);
        b2 = BTree.binTree(slex);
        b3 = BTree.binTree(slen);
        b4 = BTree.binTree(slen);
        
        b1.build(a1);
        b11.build(a1);
        b2.build(a2);
        b3.build(a3);
        b4.build(a4);
        b4.build(a3);
    }
    
    /** Test the Methods in the above class
     * @param t : The Tester */
    public void testAll(Tester t) {
        /** The empty BTree Iterator */
        Iterator<String> eit;
        
        /** The b1 BTree Iterator */
        Iterator<String> b1it;
        
        /** The b2 BTree Iterator */
        Iterator<String> b2it;
        
        /** The empty BTree Iterator */
        Iterator<String> b22it;
        
        initB();
        t.checkExpect(e.repOK());
        t.checkExpect(b0.repOK());
        t.checkExpect(b1.repOK());
        t.checkExpect(b2.repOK());
        t.checkExpect(b3.repOK());
        t.checkExpect(b4.repOK());

        t.checkExpect(!e.equals(b1));
        t.checkExpect(!b1.equals(e));
        t.checkExpect(!b0.equals(new Integer(5)));
        t.checkExpect(!b0.equals(b1));
        t.checkExpect(b1.equals(b1));
        t.checkExpect(!b1.equals(b2));
        t.checkExpect(!b1.equals(b3));
        t.checkExpect(b11.equals(b1));
        t.checkExpect(b1.equals(b11));

        t.checkExpect(e.hashCode(), 0);
        t.checkExpect(b11.hashCode(), b1.hashCode());
        t.checkExpect(b1.hashCode(), b1.hashCode());
        t.checkExpect(b1.hashCode() != b2.hashCode());
        
        t.checkExpect(e.toString(), "");
        t.checkExpect(b0.toString(), "");
        t.checkExpect(b1.toString(), "a, d, e, f, h, v, w, y");
        t.checkExpect(b2.toString(), ", o, tw, thr, four, fivef, "
                + "sixsix, sevense");
        
        eit = e.iterator();

        t.checkExpect(!eit.hasNext());
        t.checkException(new NoSuchElementException("next: The is no next " +
                "element in the Binary Tree Iterator"), eit, "next");
        
        b1it = b1.iterator();
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "a");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "d");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "e");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "f");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "h");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "v");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "w");
        t.checkExpect(b1it.hasNext());
        t.checkExpect(b1it.next(), "y");
        t.checkExpect(!b1it.hasNext());
        

        b2it = b2.iterator();
        b22it = b2.iterator();
        while (b2it.hasNext()) {
            t.checkExpect(b2it.next(), b22it.next());
        }
        
        t.checkException(new NoSuchElementException("next: The is no next "
                + "element in the Binary Tree Iterator"), b1it, "next");
        b1it = b1.iterator();
        t.checkException(new ConcurrentModificationException(
                "build: Cannot build while an Iterator " +
                "is running over this BTree"), b1, "build", a1);
        t.checkException(new UnsupportedOperationException(
                "remove: This method is not avalible"), b1it, "remove");
        t.checkException(new RuntimeException(
                "An exception has already been thrown" +
                ", cannot continue"), b1it, "hasNext");
        t.checkException(new RuntimeException(
                "An exception has already been thrown" +
                ", cannot continue"), b1it, "next");
    }
}
