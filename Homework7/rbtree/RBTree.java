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
 *  @param <T> : The Type the Red-Black Tree has values of
 *   
 * Abstraction Function:
 * AF(c) = if c.val = null then [] else AF(c.left) + [c.val] + AF(c.right)
 * 
 * rep Invariant:
 * I(c) = c.comp != null && c.color != null
 *         && (isRoot => c.color.isBlack 
 *                        && (c.val = null => c.left = null 
 *                                             && c.right = null))
 *         && (isBranch => c.val != null)
 *         && (c.leftBlackHeight = c.rightBlackHeight)
 *         && (c.color.isRed => c.leftColor.isBlack && c.rightColor.isBlack)
 *         && (c.left != null => I(c.left) && c.left.greatest < c.val)
 *         && (c.right != null => I(c.right) && c.right.least > c.val)
 * 
 */
public abstract class RBTree<T> implements Iterable<T> {

    /** The Comparator of Ts for the Sorting of the Red-Black Tree  */
    protected Comparator<T> comp;
    
    /** The T value of this node of the Red-Black Tree  */
    protected T val;
    
    /** The Red-Black Color of the node */
    protected IRBColor color;
    
    /** The left Red-Black Branch<T> of the Red-Black Tree  */
    protected RBBranch<T> left;
    
    /** The right Red-Black Branch<T> of the Red-Black Tree */
    protected RBBranch<T> right;
    
    /** The number of active iterators over this Red-Black Tree */
    protected int active = 0;
    
    /** Has this run of the code gone bad i.e. thrown an exception */
    protected boolean bad = false;
    
    /** The size of this Red-Black Tree */
    public int size;
    
    /**
     * Construct a Red-Black Tree given both a Comparator for Ts
     *  and a Red-Black Color 
     * @param comp : Any Comparator<T>
     * @param color : A Red or Black
     */
    protected RBTree(Comparator<T> comp, IRBColor color) {
        this.comp = comp;
        this.color = color;
        this.size = 0;
    }
    
    /** Constructor for the Red-Black Tree given both a Comparator for Ts,
     *  a T, A Red-Black Color
     * @param comp : Any Comparator<T>
     * @param val : Any T
     * @param color : A Red or Black
     */
    protected RBTree(Comparator<T> comp, T val, IRBColor color) {
        this.comp = comp;
        this.val = val;
        this.color = color;
        this.size = 1;
    }
    
    /** Construct a Red-Black Tree given both a Comparator for Ts,
     *   a T, a Red-Black Color, a left Red-Black Branch<T> 
     *   and a right Red-Black Branch<T>
     * @param comp : Any Comparator<T>
     * @param val : Any T
     * @param color : A Red or Black
     * @param left : A RBBranch<T>, left branch
     * @param right : A RBBranch<T>, right branch
     */
    protected RBTree(Comparator<T> comp, T val, IRBColor color,
            RBBranch<T> left, RBBranch<T> right) {
        this.comp = comp;
        this.val = val;
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
     * [[[A t2-Red B] t1-Red C] t0-Black D] -> 
     * [[A t2-Black B] t1-Red [C t0-Black D]
     */
    protected void balanceLL() {
        // [[[A t2-Red B] t1-Red C] t0-Black D]
        T t0 = this.val; // = t0
        RBBranch<T> d = this.right; // = D
        // t1 = this.left.val;
        RBBranch<T> c = this.left.right; // = C
        // t2 = this.left.left.val;
        // A = this.left.left.left;
        // B = this.left.left.right;

        // [[A t2-Black B] t1-Red [C t0-Black D]
        this.val = this.left.val; // = t1
        this.color = new RBRed();
        this.left = new RBBranch<T>(this.comp, this.left.left.val, // = t2
                this.left.left.left, // = A
                this.left.left.right); // = B
        this.right = new RBBranch<T>(this.comp, t0, // = t0
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Left/Red - Right/Red imbalance 
     *  This node must be a valid Black - Left/Red - Right/Red imbalance
     * [[A t1-Red [B t2-Red C]] t0-Black D] -> 
     * [[A t1-Black B] t2-Red [C t0-Black D]
     */
    protected void balanceLR() {
        // [[A 1-Red [B t2-Red C]] t0-Black D]
        T t0 = this.val; // = t0
        RBBranch<T> d = this.right; // = D
        // t1 = this.left.val;
        // A = this.left.left;
        // t2 = this.left.right.val;
        // B = this.left.right.left;
        RBBranch<T> c = this.left.right.right; // = C

        // [[A t1-Black B] t2-Red [C t0-Black D]]
        this.val = this.left.right.val; // = t2
        this.color = new RBRed();
        this.left = new RBBranch<T>(this.comp, this.left.val, // = t1
                this.left.left, // = A
                this.left.right.left); // = B
        this.right = new RBBranch<T>(this.comp, t0, // = t0
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Right/Red - Left/Red imbalance 
     *  This node must be a valid Black - Right/Red - Left/Red imbalance
     * [A t0-Black [[B t2-Red C] t1-Red D]] -> 
     * [[A t0-Black B] t2-Red [C t1-Black D]
     */
    protected void balanceRL() {
        // [A t0-Black [[B t2-Red C] t1-Red D]]
        T t0 = this.val;
        // A = this.left;
        T t1 = this.right.val; // = t1
        RBBranch<T> d = this.right.right; // = D
        // t2 = this.right.left.val;
        // B = this.right.left.left;
        RBBranch<T> c = this.right.left.right; // = C

        // [[A t0-Black B] t2-Red [C t1-Black D]
        this.val = this.right.left.val; // = t2
        this.color = new RBRed();
        this.left = new RBBranch<T>(this.comp, t0, // = t0
                this.left, // = A
                this.right.left.left); // = B
        this.right = new RBBranch<T>(this.comp, t1, // = t1
                c, // = C
                d); // = D
    }
    
    /**
     * Balances the Black - Right/Red - Right/Red imbalance 
     *  This node must be a valid Black - Right/Red - Right/Red imbalance
     * [A t0-Black [B t1-Red [C t2-Red D]]] -> 
     * [[A t0-Black B] t1-Red [C t2-Black D]
     */
    protected void balanceRR() {
        // [A t0-Black [B t1-Red [C t2-Red D]]]
        T t0 = this.val;
        // A = this.left;
        // t1 = this.right.val;
        // B = this.right.left;
        T t2 = this.right.right.val;
        RBBranch<T> c = this.right.right.left;
        RBBranch<T> d = this.right.right.right;

        // [[A t0-Black B] t1-Red [C t2-Black D]
        this.val = this.right.val; // = t1
        this.color = new RBRed();
        this.left = new RBBranch<T>(this.comp, t0, // = t0
                this.left, // = A
                this.right.left); // = B
        this.right = new RBBranch<T>(this.comp, t2, // = t2
                c, // = C
                d); // = D
    }
    
    /** Run the given visitor over this Red-Black Tree
     * @param visitor : A RBTreeVisitor<T, R>
     * @param <R> : The return type of the Visitor
     * @return : An R which is the value the Visitor produces
     */
    public abstract <R> R accept(RBTreeVisitor<T, R> visitor);
    
    /**
     * Make a Red-Black Tree given a T Comparator
     * 
     * @param comp : Any Comparator<T>
     * @param <T> : The Type of the Tree being returned
     * @return : An empty binary tree with the given comparator as its
     *         Comparator
     */
    public static <T> RBTree<T> binTree(Comparator<T> comp) {
        return new RBRoot<T>(comp);
    }

    /** Insert the Elements of the given Iterable to this Red-Black Tree
     * @param iter : Any Iterable<T> */
    public void build(Iterable<T> iter) {
        if (this.active == 0) {
            for (T t : iter) {
                if (!this.contains(t)) {
                    this.insert(t);
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
     * first numTs <code>T</code>s from 
     * the given <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     * @param numTs number of <code>T</code>s
     *        to iterate through and add to BTree
     *        If numTs is negative or larger than the number of 
     *        <code>T</code>s in iter then all <code>T</code>s 
     *        in iter should be inserted into the tree 
     */
    public void build(Iterable<T> iter, int numTs) {
        if (this.active == 0) {
            Iterator<T> iterator = iter.iterator();
            while (numTs != 0 && iterator.hasNext()) {
                T t = iterator.next();
                if (!this.contains(t)) {
                    this.insert(t);
                    numTs--;
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
     * Checks to see if this Red-Black Tree contains t
     * @param t <code>T</code> to look for in this
     * @return : A boolean of whether this Red-Black Tree contains t
     */
    public boolean contains(T t) {
        if (this.isEmpty()) {
            return false;
        } 
        else {
            int i = this.comp.compare(t, this.val);
            return i == 0
                    || ((i < 0) && this.hasLeft() && this.left.contains(t))
                    || ((i > 0) && this.hasRight() && this.right.contains(t));
        }
    }

    /** Is this Red-Black Tree equal to the given Object
     * @param obj : Any Object
     * @return : a boolean of whether they are equal */
    public boolean equals(Object obj) {
        if (obj instanceof RBTree) {
            RBTree<?> bt = (RBTree<?>) obj;
            if (this.size == bt.size) {
                Iterator<T> it0 = this.iterator();
                Iterator<?> it1 = bt.iterator();
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

    /** Get the T at the given index
     * @param i : An index of the element
     * @return : The T element */
    protected T get(int i) {
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
                return this.val;
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
     * @return : A T with the greatest value in Red-Black Tree */
    protected T greatest() {
        if (this.hasRight()) {
            return this.right.greatest();
        }
        else {
            return this.val;
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

    /** Does this Red-Black Tree have a left Node?
     *  (Something other than null in the left position)
     *  @return a boolean of the above question */
    protected boolean hasLeft() {
        return this.left != null;
    }

    /** Does this BTree have a right Node?
     *  (Something other than null in the right position)
     *  @return a boolean of the above question */
    protected boolean hasRight() {
        return this.right != null;
    }
    

    /** If this Red-Black Tree is a Red-Black Root
     * and its Red-Black color is Red set its color to Black */
    protected abstract void ifRootToBlack();
    
    /** Insert the given T into this Red-Black Tree 
     * @param t : Any String other than those already in the Red-Black Tree
     * @return : A boolean of whether the T was inserted */
    protected boolean insert(T t) {
        if (this.isEmpty()) {
            this.val = t;
        }
        else {
            int c = comp.compare(t, this.val);
            if (c == 0) {
                return false;
            } 
            else if (c < 0) {
                if (this.hasLeft()) {
                    if (this.left.insert(t)) {
                        this.balance();
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    this.left = new RBBranch<T>(this.comp, t);
                }
            } 
            else { // if (c > 0)
                if (this.hasRight()) {
                    if (this.right.insert(t)) {
                        this.balance();
                    } 
                    else {
                        return false;
                    }
                } 
                else {
                    this.right = new RBBranch<T>(this.comp, t);
                }
            }
        }
        this.size++;
        return true;
    }
    
    /**
     * Is this Red-Black Tree a Red-Black Branch?     * 
     * @return : A boolean of whether this Red-Black Tree is Red-Black Branch
     */
    protected abstract boolean isBranch();
    
    /** Is this Red-Black Tree an empty tree?
     * @return : a boolean of whether this Red-Black Tree is Empty */
    protected abstract boolean isEmpty();

    /** Is this Red-Black Tree a Red-Black Root
     * @return : A boolean of whether this Red-Black Tree is a Red-Black Root
     */
    protected abstract boolean isRoot();
    
    /** Create a Red-Black Tree Iterator from this Red-Black Tree
     * @return : The Iterator for this Red-Black Tree */
    public RBTreeIterator iterator() {
        return new RBTreeIterator(this);
    }

    /** Get the least value in the Red-Black Tree
     * @return : A T with the least value in Red-Black Tree */
    protected T least() {
        if (this.hasLeft()) {
            return this.left.least();
        }
        else {
            return this.val;
        }
    }
    
    /** Get the Black height of the left path of this Red-Black Tree.
     *   An empty node i.e. does not count
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
            return 0;
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
        // I(c) = c.comp != null && c.color != null
        // Is this a RBTree and the comp is not null
        return (this instanceof RBTree) && (this.comp != null)
                && (this.color != null)
                // && (isRoot => c.color.isBlack
                //                && (c.val = null => c.left = null
                //                && c.right = null))
                // Is this a RBRoot and if so is this color Black 
                //  and is this a correct empty node
                && (!(this.isRoot() && (this instanceof RBRoot)) || this.color
                        .isBlack()
                        && (this.val != null || this.left == null
                                && this.right == null))
                                
                // && (isBranch => c.val != null)
                // If this is a Branch then its String should not be null
                && (!(this.isBranch() && (this instanceof RBRoot)) 
                        || this.val != null)

                // && (c.leftBlackHeight = c.rightBlackHeight)
                // Is the number of Black Nodes 
                //       in the completely left and right paths
                && (this.leftBlackHeight() == this.rightBlackHeight())
                
                // && (c.color.isRed => c.leftColor.isBlack &&
                //   c.rightColor.isBlack)
                // If this Nodes color is Red then it cannot have a Red Node
                && (!this.color.isRed() || (this.leftColor().isBlack() && this
                        .leftColor().isBlack()))

                // && (c.left != null => I(c.left) && c.left.greatest < c.val)
                // If this has a left node then insure its rep is okay and
                //  the greatest element in the left Branch 
                //  is less than the element of this node
                && (this.left == null || (this.left.repOK() && this.comp
                        .compare(this.left.greatest(), this.val) < 0))

                // && (c.right != null => I(c.right) && c.right.least > c.val)
                // If this has a right node then insure its rep is okay and
                //  the least element in the right Branch 
                //  is less than the element of this node
                && (this.right == null || (this.right.repOK() && this.comp
                        .compare(this.right.least(), this.val) > 0));
                
    }
    
    /**
     * Get the Black height of the left path of this Red-Black Tree.
     *  An empty node i.e. a null does not count
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
            return 0;
        }
    }
    
    /** Get the color of the right node of this Tree.
     *   An empty node i.e. a null is Red-Black color of Black
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
            return "[" + this.left.toString() + "  " + this.val.toString()
                    + "/" + this.color.toString() + "  "
                    + this.right.toString() + "]";
        } 
        else if (this.hasLeft()) {
            return "[" + this.left.toString() + "  " + this.val.toString()
                    + "/" + this.color.toString() + "  null]";
        } 
        else if (this.hasRight()) {
            return "[null  " + this.val.toString() + "/"
                    + this.color.toString() + "  " + this.right.toString()
                    + "]";
        } 
        else {
            return "[null  " + this.val.toString() + "/"
                    + this.color.toString() + "  null]";
        }
    }
    
    /** A class representing the Red-Black Tree Iterator which 
     *  can iterate through any Red-Black tree in order
     * @author Edwin Cowart
     * @version 2013.11.9
     */
    class RBTreeIterator implements Iterator<T> {
        
        /** The Red-Black Tree being iterated over */
        private RBTree<T> bt;
        
        /** The next index in the iterator */
        private int index;
        
        /** Construct a Red-Black Tree Iterator with the given Binary Tree
         * @param bt : Any RBTree */
        private RBTreeIterator(RBTree<T> bt) {
            this.bt = bt;
            if (!bt.isEmpty()) {
                active++;
            }
            index = 0;
        }

        /** Does the Red-Black Tree Iterator have a next element?
         * @return : A boolean of whether this Iterator has a next element */
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
         * @return : A T of the next element */
        public T next() {
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



