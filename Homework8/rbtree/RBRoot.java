package rbtree;


import java.util.Comparator;

/** A Class representing a Red-Black Root Node
 *  @author Edwin Cowart
 *  @version 2013.11.14
 *  @param <T> : The type of values this Root Contains
 */
class RBRoot<T> extends RBTree<T> {
    /** Constructor for the Red-Black Tree given a Comparator for Ts
     * This is the root constructor only
     * @param comp : Any Comparator<T>
     */
    protected RBRoot(Comparator<T> comp) {
        super(comp, new RBBlack());
    }
    
    /** Run the given visitor over this Tree
     * @param visitor : A RBTreeVisitor<T, R>
     * @param <R> : The return type of the Visitor
     * @return : An R which is the answer to the visitor
     */
    public <R> R accept(RBTreeVisitor<T, R> visitor) {
        if (this.isEmpty()) {
            return visitor.visitEmpty(this.comp, "BLACK");
        }
        return visitor.visitNode(this.comp, this.color.toString(),
                this.val, this.left, this.right);
    }
    
    /** If this Red-Black Root is a Red-Black Root 
     * and its Red-Black color is Red set its color to Black */
    protected void ifRootToBlack() {
        if (!this.isEmpty() && this.color.isRed()) {
            this.color = new RBBlack();
        }
    }

    /** Is this Red-Black Root a Red-Black Branch?
     * @return : false
     */
    protected boolean isBranch() {
        return false;
    }
    
    /** Is this Red-Black Root an empty tree?
     * @return : A boolean of whether this tree is empty */
    protected boolean isEmpty() {
        return this.val == null;
    }
    
    /** Is this Red-Black Root a Red-Black Root
     * @return : true
     */
    protected boolean isRoot() {
        return true;
    }
}