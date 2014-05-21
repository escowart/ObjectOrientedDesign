package rbtree;


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/** A Class Representing a Red-Black Tree 
 *  The lowest element in the BTree is at the 0 index
 *  Every index but the first is 1 + the previous lower index 
 *  @author Edwin Cowart
 *  @version 2013.10.29
 *   
 * Abstraction Function:
 * AF(c) = if c.str = null then [] else AF(c.left) + [c.str] + AF(c.right)
 * 
 * rep Invariant:
 * I(c) = c.comp != null 
 *         && (isRoot => c.color.isBlack 
 *                        && (c.str = null => c.left = null 
 *                                             && c.right = null))
 *         && (isBranch => c.str != null)
 *         && (c.leftBlackHeight = c.rightBlackHeight)
 *         && (c.color.isRed => c.leftColor.isBlack && c.rightColor.isBlack)
 *         && (c.left != null => I(c.left) && c.left.greatest < c.str)
 *         && (c.right != null => I(c.right) && c.right.least > c.str)
 * 
 */
public abstract class RBTree implements Iterable<String> {

    /** The Comparator for the Sorting of the Red-Black Tree  */
    protected Comparator<String> comp;
    
    /** The String of this node of the Red-Black Tree  */
    protected String str;
    
    /** The Red-Black Color of the node */
    protected IRBColor color;
    
    /** The left branch of the Red-Black Tree  */
    protected RBBranch left;
    
    /** The right branch of the Red-Black Tree */
    protected RBBranch right;
    
    /** The number of active iterators over this Red-Black Tree */
    protected int active = 0;
    
    /** Has this run of the code gone bad i.e. thrown an exception */
    protected boolean bad = false;
    
    /** The size of this Red-Black Tree */
    public int size;
    
    /**
     * Construct a Red-Black Tree given both a Comparator for Strings
     * and a Red-Black Color 
     * @param comp : Any Comparator<String>
     * @param color : A Red or Black
     */
    protected RBTree(Comparator<String> comp, IRBColor color) {
        this.comp = comp;
        this.color = color;
        this.size = 0;
    }
    
    /** Constructor for the Red-Black Tree given both a Comparator for Strings,
     *  a String, A Red-Black Color
     * @param comp : Any Comparator<String>
     * @param str : Any String
     * @param color : A Red or Black
     */
    protected RBTree(Comparator<String> comp, String str, IRBColor color) {
        this.comp = comp;
        this.str = str;
        this.color = color;
        this.size = 1;
    }
    
    /** Construct a Red-Black Tree given both a Comparator for Strings,
     *  a String, A Red-Black Color, A left branch and a right branch
     * @param comp : Any Comparator<String>
     * @param str : Any String
     * @param color : A Red or Black
     * @param left : A RBBranch, left branch
     * @param right : A RBBranch, right branch
     */
    protected RBTree(Comparator<String> comp, String str, IRBColor color,
            RBBranch left, RBBranch right) {
        this.comp = comp;
        this.str = str;
        this.color = color;
        this.left = left;
        this.right = right;
        this.size = 1;
        if (this.hasLeft()) {
            this.size += this.left.size;
        }
        if (this.hasRight()) {
            this.size += this.right.size;
        }
    }
    
    /** Balance this Node of a Red-Black Tree if there is an imbalance.
     *  It should only have one imbalance */
    protected void balance() {
        if (this.color.isBlack()) {
            boolean cont = true;
            // Left Imbalance
            if (this.hasLeft() && this.left.color.isRed()) {
                // Black - Left/Red - Left/Red imbalance 
                if (this.left.hasLeft() && this.left.left.color.isRed()) {
                    this.balanceLL();
                    cont = false;
                }
                // Black - Left/Red - Right/Red imbalance 
                else if (this.left.hasRight() 
                        && this.left.right.color.isRed()) {
                    this.balanceLR();
                    cont = false;
                }
            } 
            // Right Imbalances
            if (cont && this.hasRight() && this.right.color.isRed()) {
                // Black - Right/Red - Left/Red imbalance 
                if (this.right.hasLeft() && this.right.left.color.isRed()) {
                    this.balanceRL();
                } 
                // Black - Right/Red - Right/Red imbalance 
                else if (this.right.hasRight()
                        && this.right.right.color.isRed()) {
                    this.balanceRR();
                }
            }
            // If this is the root then set its color to black
            this.ifRootToBlack();
        }
    }
    
    /**
     * Balances the Black - Left/Red - Left/Red imbalance 
     *  This node must be a valid Black - Left/Red - Left/Red imbalance
     * [[[A s2-Red B] s1-Red C] s0-Black D] -> 
     * [[A s2-Black B] s1-Red [C s0-Black D]
     */
    protected void balanceLL() {
        // [[[A s2-Red B] s1-Red C] s0-Black D]
        String s0 = this.str; // = s0
        RBBranch d = this.right; // = D
        // s1 = this.left.str;
        RBBranch c = this.left.right; // = C
        // s2 = this.left.left.str;
        // A = this.left.left.left;
        // B = this.left.left.right;

        // [[A s2-Black B] s1-Red [C s0-Black D]
        this.str = this.left.str; // = s1
        this.color = new RBRed();
        this.left = new RBBranch(this.comp, this.left.left.str, // = s2
                this.left.left.left, // = A
                this.left.left.right); // = B
        this.right = new RBBranch(this.comp, s0, // = s0
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Left/Red - Right/Red imbalance 
     *  This node must be a valid Black - Left/Red - Right/Red imbalance
     * [[A s1-Red [B s2-Red C]] s0-Black D] -> 
     * [[A s1-Black B] s2-Red [C s0-Black D]
     */
    protected void balanceLR() {
        // [[A s1-Red [B s2-Red C]] s0-Black D]
        String s0 = this.str; // = s0
        RBBranch d = this.right; // = D
        // s1 = this.left.str;
        // A = this.left.left;
        // s2 = this.left.right.str;
        // B = this.left.right.left;
        RBBranch c = this.left.right.right; // = C

        // [[A s1-Black B] s2-Red [C s0-Black D]]
        this.str = this.left.right.str; // = s2
        this.color = new RBRed();
        this.left = new RBBranch(this.comp, this.left.str, // = s1
                this.left.left, // = A
                this.left.right.left); // = B
        this.right = new RBBranch(this.comp, s0, // = s0
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Right/Red - Left/Red imbalance 
     *  This node must be a valid Black - Right/Red - Left/Red imbalance
     * [A s0-Black [[B s2-Red C] s1-Red D]] -> 
     * [[A s0-Black B] s2-Red [C s1-Black D]
     */
    protected void balanceRL() {
        // [A s0-Black [[B s2-Red C] s1-Red D]]
        // s0 = this.str;
        // A = this.left;
        String s1 = this.right.str; // = s1
        RBBranch d = this.right.right; // = D
        // s2 = this.right.left.str;
        // B = this.right.left.left;
        RBBranch c = this.right.left.right; // = C

        // [[A s0-Black B] s2-Red [C s1-Black D]
        String strtemp = this.str; // = s0
        this.str = this.right.left.str; // = s2
        this.color = new RBRed();
        this.left = new RBBranch(this.comp, strtemp, // = s0
                this.left, // = A
                this.right.left.left); // = B
        this.right = new RBBranch(this.comp, s1, // = s1
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Right/Red - Right/Red imbalance 
     *  This node must be a valid Black - Right/Red - Right/Red imbalance
     * [A s0-Black [B s1-Red [C s2-Red D]]] -> 
     * [[A s0-Black B] s1-Red [C s2-Black D]
     */
    protected void balanceRR() {
        // [A s0-Black [B s1-Red [C s2-Red D]]]
        // s0 = this.str;
        // A = this.left;
        // s1 = this.right.str;
        // B = this.right.left;
        String s2 = this.right.right.str;
        RBBranch c = this.right.right.left;
        RBBranch d = this.right.right.right;

        // [[A s0-Black B] s1-Red [C s2-Black D]
        String strtemp = this.str; // = s0
        this.str = this.right.str; // = s1
        this.color = new RBRed();
        this.left = new RBBranch(this.comp, strtemp, // = s0
                this.left, // = A
                this.right.left); // = B
        this.right = new RBBranch(this.comp, s2, // = s2
                c, // = C
                d); // = D
    }
    
    /**
     * Make a Red-Black Tree given a String Comparator
     * 
     * @param comp :
     *            Any String Comparator
     * @return : An empty binary tree with the given comparator as its
     *         Comparator
     */
    public static RBTree binTree(Comparator<String> comp) {
        return new RBRoot(comp);
    }

    /** Insert the Elements of the given Iterable to this Red-Black Tree
     * @param iter : Any Iterable<String> */
    public void build(Iterable<String> iter) {
        if (this.active == 0) {
            for (String s : iter) {
                if (!this.contains(s)) {
                    this.insert(s);
                }
            }
        }
        else {
            bad = true;
            throw new ConcurrentModificationException(
                    "build: Cannot build while an Iterator " +
                    "is running over this Red-Black Tree");
        }
    }

    /**
     * Modifies: 
     * this Red-Black Tree by inserting the 
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
        if (this.active == 0) {
            Iterator<String> iterator = iter.iterator();
            while (numStrings != 0 && iterator.hasNext()) {
                String s = iterator.next();
                if (!this.contains(s)) {
                    this.insert(s);
                    numStrings--;
                }
            }
        }
        else {
            bad = true;
            throw new ConcurrentModificationException(
                    "build: Cannot build while an Iterator " +
                    "is running over this Red-Black Tree");
        }
    }

    /**
     * Effect:
     * Checks to see if this Red-Black Tree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(String s) {
        if (this.isEmpty()) {
            return false;
        } 
        else {
            int i = this.comp.compare(s, this.str);
            return i == 0
                    || ((i < 0) && this.hasLeft() && this.left.contains(s))
                    || ((i > 0) && this.hasRight() && this.right.contains(s));
        }
    }

    /** Is this Red-Black Tree equal to the given Object
     * @param obj : Any Object
     * @return : a boolean of whether they are equal */
    public boolean equals(Object obj) {
        if (obj instanceof RBTree) {
            RBTree bt = (RBTree) obj;
            if (this.comp.equals(bt.comp) && this.size == bt.size) {
                Iterator<String> it0 = this.iterator();
                Iterator<String> it1 = this.iterator();
                for (int i = 0; i < this.size; i++) {
                    if (!it0.next().equals(it1.next())) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /** Get the String at the given index
     * @param i : An index of the element
     * @return : The String element */
    protected String get(int i) {
        if (i < 0 || i >= this.size) {
            this.bad = true;
            throw new NoSuchElementException(
                    "get : There is no element " + i 
                    + " within this Binary Tree");
        }
        else {
            // Abstraction here was a possible cause of a Stack Overflow
            if ((this.hasLeft() && this.left.size == i) 
                    || (this.left == null && i == 0)) {
                return this.str;
            }
            else if (this.hasLeft() && this.left.size > i) {
                return this.left.get(i);
            }
            else if (this.left == null) {
                return this.right.get(i - 1);
            }
            else {
                return this.right.get(i - (1 + this.left.size));
            }
        }
    }

    /** Get the greatest value in the Red-Black Tree
     * @return : A String with the greatest value in RBTree */
    protected String greatest() {
        if (this.hasRight()) {
            return this.right.greatest();
        }
        else {
            return this.str;
        }
    }

    /**
     * Creates an integer from this Red-Black Tree which will sometimes be
     * unique
     * @return : an int of the hashCodes of each element
     */
    public int hashCode() {
        int h = this.comp.hashCode();
        for (int i = 0; i < this.size; i++) {
            h += this.get(i).hashCode();
        }
        return h;
    }

    /** Does this Red-Black Tree have a left Node 
     *  (Something other than null in the left position)
     *  @return a boolean of the above question */
    protected boolean hasLeft() {
        return this.left != null;
    }

    /** Does this BTree have a right Node 
     *  (Something other than null in the right position)
     *  @return a boolean of the above question */
    protected boolean hasRight() {
        return this.right != null;
    }
    

    /** If this Red-Black Tree is a Red-Black Root 
     * and its Red-Black color is Red set its color to Black */
    protected abstract void ifRootToBlack();
    
    /** Insert the given String into this Red-Black Tree 
     * @param s : Any String other than those already in the Red-Black Tree
     * @return : A boolean of whether the String was inserted */
    protected boolean insert(String s) {
        if (this.isEmpty()) {
            this.str = s;
        }
        else {
            int c = comp.compare(s, this.str);
            if (c == 0) {
                return false;
            } 
            else if (c < 0) {
                if (this.hasLeft()) {
                    if (this.left.insert(s)) {
                        this.balance();
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    this.left = new RBBranch(this.comp, s);
                }
            } 
            else { // if (c > 0)
                if (this.hasRight()) {
                    if (this.right.insert(s)) {
                        this.balance();
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    this.right = new RBBranch(this.comp, s);
                }
            }
        }
        this.size++;
        return true;
    }
    
    /**
     * Is this Red-Black Tree a Red-Black Branch
     * 
     * @return : A boolean of whether this Red-Black Tree is Red-Black Branch
     */
    protected abstract boolean isBranch();
    
    /** Is this Red-Black Tree an empty tree?
     * @return : a boolean of whether this tree is empty */
    protected abstract boolean isEmpty();

    /** Is this Red-Black Tree a Red-Black Root
     * @return : A boolean of whether this Red-Black Tree is Red-Black Root
     */
    protected abstract boolean isRoot();
    
    /** Create a Red-Black Tree Iterator from this Binary Tree
     * @return : The Iterator for this Red-Black Tree */
    public RBTreeIterator iterator() {
        return new RBTreeIterator(this);
    }

    /** Get the least value in the Red-Black Tree
     * @return : A String with the least value in Red-Black Tree */
    protected String least() {
        if (this.hasLeft()) {
            return this.left.least();
        }
        else {
            return this.str;
        }
    }
    
    /** Get the Black height of the left path of this Red-Black Tree.
     *  An empty node i.e. a null counts as a Black
     * @return : an int of the left Black height */
    protected int leftBlackHeight() {
        if (this.hasLeft()) {
            if (this.left.color.isBlack()) {
                return 1 + this.left.leftBlackHeight();
            }
            else {
                return this.left.leftBlackHeight();
            }
        }
        else {
            return 1;
        }
    }
    
    /** Get the color of the left node of this Tree.
     *  An empty node i.e. a null is Red-Black color of Black
     * @return : Red-Black color, Black
     */
    protected IRBColor leftColor() {
        if (this.hasLeft()) {
            return this.left.color;
        }
        else {
            return new RBBlack();
        }
    }

    /** Is the representation of the Red-Black Tree valid
     * @return : A boolean of whether the representation is okay */
    public boolean repOK() {
        // I(c) = c.comp != null
        // Is this a RBTree and the comp is not null
        return (this instanceof RBTree) && (this.comp != null)
                // && (isRoot => c.color.isBlack
                //                && (c.str = null => c.left = null
                //                && c.right = null))
                // Is this a RBRoot and if so is this color Black 
                //  and is this a correct empty node
                && (!(this.isRoot() && (this instanceof RBRoot)) || this.color
                        .isBlack()
                        && (this.str != null || this.left == null
                                && this.right == null))
                                
                // && (isBranch => c.str != null)
                // If this is a Branch then its String should not be null
                && (!(this.isBranch() && (this instanceof RBRoot)) 
                        || this.str != null)

                // && (c.leftBlackHeight = c.rightBlackHeight)
                // Is the number of Black Nodes 
                //       in the completely left and right paths
                && (this.leftBlackHeight() == this.rightBlackHeight())
                
                // && (c.color.isRed => c.leftColor.isBlack &&
                // c.rightColor.isBlack)
                // If this Nodes color is Red then it cannot have a Red Node
                && (!this.color.isRed() || (this.leftColor().isBlack() && this
                        .leftColor().isBlack()))

                // && (c.left != null => I(c.left) && c.left.greatest < c.str)
                // If this has a left node then insure its rep is okay and
                //  the greatest element in the left Branch 
                //  is less than the element of this node
                && (this.left == null || (this.left.repOK() && this.comp
                        .compare(this.left.greatest(), this.str) < 0))

                // && (c.right != null => I(c.right) && c.right.least > c.str)
                // If this has a right node then insure its rep is okay and
                //  the least element in the right Branch 
                //  is less than the element of this node
                && (this.right == null || (this.right.repOK() && this.comp
                        .compare(this.right.least(), this.str) > 0));
                
    }
    
    /**
     * Get the Black height of the left path of this Red-Black Tree. An empty
     * node i.e. a null counts as a black
     * 
     * @return : an int of the left Black height
     */
    protected int rightBlackHeight() {
        if (this.hasRight()) {
            if (this.right.color.isBlack()) {
                return 1 + this.right.rightBlackHeight();
            }
            else {
                return this.right.rightBlackHeight();
            }
        }
        else {
            return 1;
        }
    }
    
    /** Get the color of the right node of this Tree.
     *  An empty node i.e. a null is Red-Black color of Black
     * @return : Red-Black color, Black
     */
    protected IRBColor rightColor() {
        if (this.hasRight()) {
            return this.right.color;
        }
        else {
            return new RBBlack();
        }
    }
    
    /** Create a String version of all the elements in this Red-Black Tree
     *  in order of the Comparator
     * @return : A String of all the elements */
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } 
        else if (this.hasLeft() && this.hasRight()) {
            return "[" + this.left.toString() + "  " + this.str + "/"
                    + this.color.toString() + "  " + this.right.toString()
                    + "]";
        } 
        else if (this.hasLeft()) {
            return "[" + this.left.toString() + "  " + this.str + "/"
                    + this.color.toString() + "  null]";
        } 
        else if (this.hasRight()) {
            return "[null  " + this.str + "/" + this.color.toString() + "  "
                    + this.right.toString() + "]";
        } 
        else {
            return "[null  " + this.str + "/" + this.color.toString()
                    + "  null]";
        }
    }
    
    /** A class representing the Red-Black Tree Iterator which 
     *  can iterate through any binary tree in order
     * @author Edwin Cowart
     * @version 2013.10.6
     */
    class RBTreeIterator implements Iterator<String> {
        
        /** The Red-Black Tree being iterated over */
        private RBTree bt;
        
        /** The next index in the iterator */
        private int index;
        
        /** Construct a Red-Black Tree Iterator with the given Binary Tree
         * @param bt : Any RBTree */
        private RBTreeIterator(RBTree bt) {
            this.bt = bt;
            if (!bt.isEmpty()) {
                active++;
            }
            index = 0;
        }

        /** Does the Red-Black Tree Iterator have a next element?
         * @return : boolean of whether this Iterator has a next element */
        public boolean hasNext() {
            if (bad) {
                throw new RuntimeException(
                        "An exception has already been thrown" +
                        ", cannot continue");
            }
            else {
                return this.bt.size > index;
            }
        }

        /** Return the next element in the Red-Black Tree Iterator
         * @return : String of the next element */
        public String next() {
            if (bad) {
                throw new RuntimeException(
                        "An exception has already been thrown" +
                        ", cannot continue");
            }
            else if (hasNext()) {
                if (index == this.bt.size - 1) {
                    active--;
                }
                return this.bt.get(index++);
            } 
            else {
                bad = true;
                throw new NoSuchElementException(
                        "next: The is no next element in the " +
                        "Red-Black Tree Iterator");
            }
        }

        /** Remove the previous element from this Red-Black Tree Iterator */
        public void remove() {
            bad = true;
            throw new UnsupportedOperationException(
                    "remove: This method is not avalible");
        }
    }
}



