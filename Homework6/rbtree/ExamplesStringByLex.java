package rbtree;



import tester.Tester;

/** A class that tests StringbyLength
 *  @author Edwin Cowart
 *  @version 2013.11.1 */
public class ExamplesStringByLex {
    
    private StringByLex strbylex = new StringByLex();
    
    /** Tests the Comparator
     * @param t : The Tester class */ 
    public void testStringBy(Tester t) {
        // StringByLength & StringByLex compare
        t.checkExpect(this.strbylex.compare("goodbye", "hello") < 0);
        t.checkExpect(this.strbylex.compare("hello", "hello") == 0);
        t.checkExpect(this.strbylex.compare("hello", "goodbye") > 0);
        
        // StringByLength & StringByLex equals
        t.checkExpect(this.strbylex.equals(this.strbylex));
        t.checkExpect(!this.strbylex.equals("StringByLex"));
        t.checkExpect(!this.strbylex.equals(6));
        
        String slex = "StringByLex";
        // StringByLength & StringByLex hashCode
        t.checkExpect(this.strbylex.hashCode(), slex.hashCode());
        
        // StringByLength & StringByLex toString
        t.checkExpect(this.strbylex.toString(), slex);
    }
}