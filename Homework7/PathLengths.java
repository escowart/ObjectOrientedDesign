

import java.util.ArrayList;
import java.util.Comparator;
import rbtree.RBTree;
import rbtree.RBTreeVisitor;

/** A Visitor that calculates the Path Length of
 *  each Branch of the given Red-Black Tree
 * @author Edwin Cowart
 * @version 2013.11.9
 * @param <T>
 */
public class PathLengths<T> implements RBTreeVisitor<T, ArrayList<Integer>> {
    /**
     * Get the Path Length of each Branch of the Empty Tree 
     *  of the given Comparator and String
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @return The Empty ArrayList<Integer>
     */
    public ArrayList<Integer> visitEmpty(Comparator<T> comp, String color) {
        return new ArrayList<Integer>();
    }
    
    /**
     * Get the Path Length of each Branch of the Tree 
     *  of the given Comparator, String, Data, Left, Right Branches
     * @param comp the Comparator for the whole tree
     * @param color the color of the node, which should be "RED"
     *        or "BLACK"
     * @param data the label/value for the node
     * @param left the left subtree of the node
     * @param right the right subtree of the node
     * @return The ArrayList<Integer> of path lengths
     */
    public ArrayList<Integer> visitNode(Comparator<T> comp, String color,
            T data, RBTree<T> left, RBTree<T> right) {
        if (left != null && right != null) {
            ArrayList<Integer> ilist = left.accept(this);
            ilist.addAll(right.accept(this));
            return PathLengths.addOneToAll(ilist);
        } 
        else if (left != null) {
            ArrayList<Integer> ilist = PathLengths.addOneToAll(left
                    .accept(this));
            ilist.add(1);
            return ilist;
        } 
        else if (right != null) {
            ArrayList<Integer> ilist = new ArrayList<Integer>();
            ilist.add(1);
            ilist.addAll(PathLengths.addOneToAll(right.accept(this)));
            return ilist;
        } 
        else {
            ArrayList<Integer> ilist = new ArrayList<Integer>();
            ilist.add(1);
            ilist.add(1);
            return ilist;
        }
    }
    
    /**
     * Add One to Every member of the given Array List of Integer Modifies the
     * given list
     * 
     * @param ilist :
     *            ArrayList<Integer>
     * @return : An ArrayList<Integer> with every element having 1 added to it
     */
    private static ArrayList<Integer> addOneToAll(ArrayList<Integer> ilist) {
        for (int i = 0; i < ilist.size() ; i++) {
            ilist.set(i, new Integer(ilist.get(i).intValue() + 1));
        }
        return ilist;
    }
}
