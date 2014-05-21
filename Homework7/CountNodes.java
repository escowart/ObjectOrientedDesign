import java.util.ArrayList;
import java.util.Comparator;
import rbtree.RBTree;
import rbtree.RBTreeVisitor;

/** A Visitor that calculates and ArrayList of the number of Empty Node,
 *  Non-Empty Black Nodes, and Non-Empty Red Nodes of the Red-Black Tree
 * @author Edwin Cowart
 * @version 2013.11.9
 * @param <T>
 */
public class CountNodes<T> implements RBTreeVisitor<T, ArrayList<Integer>> {
    /**
     * Get an ArrayList of the number of Empty Node,
     *  Non-Empty Black Nodes, and Non-Empty Red Nodes of the Empty Tree
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @return An ArrayList<Integer> of 1, 0, 0
     */
    public ArrayList<Integer> visitEmpty(Comparator<T> comp, String color) {
        ArrayList<Integer> ilist = new ArrayList<Integer>();
        ilist.add(new Integer(1));
        ilist.add(new Integer(0));
        ilist.add(new Integer(0));
        return ilist;
    }
    
    /**
     * Get an ArrayList of the number of Empty Node,
     *  Non-Empty Black Nodes, and Non-Empty Red Nodes of the Tree
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @param data the label/value for the node
     * @param left the left subtree of the node
     * @param right the right subtree of the node
     * @return The element ArrayList<Integer> 
     *  of the number of Empty, Black, and Red Nodes
     */
    public ArrayList<Integer> visitNode(Comparator<T> comp, String color,
            T data, RBTree<T> left, RBTree<T> right) {
        ArrayList<Integer> ilist = new ArrayList<Integer>();
        Integer t = 1;
        Integer b = 0;
        Integer r = 0;
        if (left != null) {
            ArrayList<Integer> list = left.accept(this);
            t += list.get(0);
            b += list.get(1);
            r += list.get(2);
        }
        if (right != null) {
            ArrayList<Integer> list = right.accept(this);
            t += list.get(0);
            b += list.get(1);
            r += list.get(2);
        }
        if (color.equals("BLACK")) {
            b += 1;
        } 
        else {
            r += 1;
        }
        ilist.add(t);
        ilist.add(b);
        ilist.add(r);
        return ilist;
    }
}
