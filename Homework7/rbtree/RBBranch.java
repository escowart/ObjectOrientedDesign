package rbtree;



import java.util.Comparator;

/** A Class representing a Red-Black Branch Node
 *  @author Edwin Cowart
 *  @version 2013.11.9
 *  @param <T>
 */
public class RBBranch<T> extends RBTree<T> {
    /** Constructor for the Red-Black Tree given both a Comparator for Ts 
     *   and a T
     * This is the constructor for anything other than the root
     * @param comp : Any Comparator<T>
     * @param val : Any T
     */
    protected RBBranch(Comparator<T> comp, T val) {
        super(comp, val, new RBRed());
    }
    
    /** Constructor for the Red-Black Tree given both a Comparator for Ts 
     *   and a T
     *  This is the constructor for anything other than the root
     * @param comp : Any Comparator<T>
     * @param val : Any T
     * @param left : Any RBTree<T>
     * @param right : Any RBTree<T>
     */
    protected RBBranch(Comparator<T> comp, T val, RBBranch<T> left,
            RBBranch<T> right) {
        super(comp, val, new RBBlack(), left, right);
    }
    
    /** Run the given visitor over this Tree
     * @param visitor : A RBTreeVisitor<T, R>
     * @param <R> : The return type of the Visitor
     * @return : An R which is the answer to the visitor
     */
    public <R> R accept(RBTreeVisitor<T, R> visitor) {
        return visitor.visitNode(this.comp, this.color.toString(),
                this.val, this.left, this.right);
    }
    
    /** If this Red-Black Branch is a Red-Black Root?
     *   and its Red-Black color is Red set its color to Black */
    protected void ifRootToBlack() {
        // This does nothing because it is not a Root Node
    }
    
    /** Is this Red-Black Branch a Red-Black Branch?
     * @return : true
     */
    protected boolean isBranch() {
        return true;
    }
    
    /** Is this Red-Black Branch an Empty Red-Black Tree?
     * @return : false 
     */
    protected boolean isEmpty() {
        return false;
    }
    
    /** Is this Red-Black Branch a Red-Black Root?
     * @return : false
     */
    protected boolean isRoot() {
        return false;
    }
}
