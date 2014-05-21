package rbtree;


import java.util.Comparator;


/** A Class representing a Red-Black Root Node
 *  @author Edwin Cowart
 *  @version 2013.10.31
 */
class RBRoot extends RBTree {
    /** Constructor for the Red-Black Tree given a Comparator for Strings
     * This is the root constructor only
     * @param comp : Any Comparator<String>
     */
    protected RBRoot(Comparator<String> comp) {
        super(comp, new RBBlack());
    }
    
    /** If this Red-Black Root is a Red-Black Root 
     * and its Red-Black color is Red set its color to Black */
    protected void ifRootToBlack() {
        if (!this.isEmpty() && this.color.isRed()) {
            this.color = new RBBlack();
        }
    }

    /** Is this Red-Black Root a Red-Black Branch
     * @return : A boolean of whether this Red-Black Root is Red-Black Branch
     */
    protected boolean isBranch() {
        return false;
    }
    
    /** Is this Red-Black Root an empty tree?
     * @return : a boolean of whether this tree is empty */
    protected boolean isEmpty() {
        return this.str == null;
    }
    
    /** Is this Red-Black Root a Red-Black Root
     * @return : A boolean of whether this Red-Black Root is Red-Black Root
     */
    protected boolean isRoot() {
        return true;
    }
}