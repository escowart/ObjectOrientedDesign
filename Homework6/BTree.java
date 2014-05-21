

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import rbtree.RBTree;

/* Edwin Cowart
 * escowart@ccs.neu.edu
 * I could not put StringByLen & StringByLex in the default package 
 *  and import it for testing Red-Black Tree so I put it in both packages
 * web-cat is saying my Problem Coverage is 93% 
 *  and the only thing it tells me as a hint is "b1 equals b2"
 * It appears web-cat randomly selects test not to run 
 *  and takes off points for lack of coverage on the test 
 *   and on the code it was testing
 */

/** A Class Representing a Binary Search Tree
 *  @author Edwin Cowart
 *  @version 2013.11.1
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
public abstract class BTree implements Iterable<String> {
    
    private RBTree tree;
    
    /** Constructor for the BTree given a Comparator for Strings
     * @param comp : Any Comparator<String>
     */
    protected BTree(Comparator<String> comp) {
        this.tree = RBTree.binTree(comp);
    }
    
    /**
     * Factory method to generate 
     * an empty binary search tree
     * with the given <code>Comparator</code>
     *
     * @param comp the given <code>Comparator</code>
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static BTree binTree(Comparator<String> comp) {
        return new PseudoTree(comp);
    }

    /**
     * Modifies: 
     * this binary search tree by inserting the 
     * <code>String</code>s from the given 
     * <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     */
    public void build(Iterable<String> iter) {
        try {
            this.tree.build(iter);
        }
        catch (ConcurrentModificationException e) {
            throw new ConcurrentModificationException(
                    "build: Cannot build while an Iterator " +
                    "is running over this Binary Search Tree");
        }
    }

    /**
     * Modifies: 
     * this binary search tree by inserting the 
     * first numStrings <code>String</code>s from 
     * the given <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     * @param numStrings number of <code>String</code>s
     *        to iterate through and add to BTree
     *        If numStrings is negative or larger than the number of 
     *        <code>String</code>s in iter then all <code>String</code>s 
     *        in iter should be inserted into the tree 
     */
    public void build(Iterable<String> iter, int numStrings) {
        try {
            this.tree.build(iter, numStrings);
        }
        catch (ConcurrentModificationException e) {
            throw new ConcurrentModificationException(
                    "build: Cannot build while an Iterator " +
                    "is running over this Binary Search Tree");
        }
    }

    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(String s) {
        return this.tree.contains(s);
    }

    /**
     * Effect: 
     * Produces false if o is not an instance of BTree.
     * Produces true if this tree and the given BTree 
     * contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>.
     * So if the first tree was built with Strings 
     * "hello" "bye" and "aloha" ordered
     * lexicographically,  and the second tree was built 
     * with <code>String</code>s "aloha" "hello" and "bye"  
     * and ordered lexicographically, 
     * the result would be true.
     *
     * @param o the object to compare with this
     * @return : A boolean of whether the given object is equal to this BTree
     */
    public boolean equals(Object o) {
        if (o instanceof BTree) {
            BTree bt = (BTree) o;
            return this.tree.equals(bt.tree);
        }
        return false;
    }

    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * @return : An int which is the hashCode of this BTree
     */
    public int hashCode() {
        String s = "BTree";
        return s.hashCode() + this.tree.hashCode();
    }
    
    /** Create a Binary Tree Iterator from this Binary Tree
     * @return : The Iterator for this Binary Tree */
    public BTreeIterator iterator() {
        return new BTreeIterator(this);
    }
    
    /** Is the representation of the Binary Tree valid
     * @return : A boolean of whether the representation is okay */
    public boolean repOK() {
        return (this instanceof BTree) && this.tree != null
                && (this.tree instanceof RBTree) && this.tree.repOK();
    }
    
    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * @return : A String which is the String form of this class
     */
    public String toString() {
        String str = "";
        for (String s : this) {
            str += s + ", ";
        }
        int size = str.length();
        if (!str.isEmpty()) {
            str = str.substring(0, size - 2);
        }
        return str;
    }
    
    /** A class representing a Binary Search Tree Iterator
     * @author Edwin Cowart
     * @version 2013.11.1
     */
    class BTreeIterator implements Iterator<String> {
        
        private Iterator<String> it;
        
        /** Construct a Binary Search Tree Iterator 
         *  with the given Binary Search Tree
         * @param bt : Any BTree
         */
        private BTreeIterator(BTree bt) {
            this.it = bt.tree.iterator();
        }
        
        /** Does this Binary Tree Iterator have 
         * any more elements to iteratre through?
         * @return : A boolean of whether the iterator has any more elements */
        public boolean hasNext() {
            return it.hasNext();
        }
        
        /** Get the next element of this Binary Tree Iterator.
         *  If there are none throw an exception.
         *  @return : A String of the next element in the iterator
         */
        public String next() {
            try {
                return it.next();
            }
            catch (NoSuchElementException e) {
                throw new NoSuchElementException(
                        "next: The is no next element in the "
                                + "Binary Search Tree Iterator");
            }
        }
        
        /** Remove the last element that was iterated over.
         * Always throws an exception because it has not been implemented
         */
        public void remove() {
            it.remove();
        }
    }
}
