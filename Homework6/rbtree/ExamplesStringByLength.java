package rbtree;


import tester.Tester;

/** A class that tests StringbyLength
 *  @author Edwin Cowart
 *  @version 2013.11.1 */
public class ExamplesStringByLength {
    
    private StringByLength strbylen = new StringByLength();
    
    /** Tests the Comparator
     * @param t : The Tester class */ 
    public void testStringBy(Tester t) {
        // StringByLength & StringByLex compare 
        t.checkExpect(this.strbylen.compare("goodbye", "hello") > 0);
        t.checkExpect(this.strbylen.compare("goodbye", "goodbye") == 0);
        t.checkExpect(this.strbylen.compare("hello", "goodbye") < 0);
        
        // StringByLength & StringByLex equals
        t.checkExpect(this.strbylen.equals(this.strbylen));
        t.checkExpect(!this.strbylen.equals("StrByLen"));
        t.checkExpect(!this.strbylen.equals(6));
        
        String slen = "StringByLength";
        // StringByLength & StringByLex hashCode
        t.checkExpect(this.strbylen.hashCode(), slen.hashCode());
        
        // StringByLength & StringByLex toString
        t.checkExpect(this.strbylen.toString(), slen);
    }
}
