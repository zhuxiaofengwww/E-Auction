/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclasses;

/**
 *
 * @author Preston
 */
public class Customer {
    String id="";
    String first="";
    String last="";    
    String email="";
    String password="";
    
    public Customer()
    {
        
    }
    
    public Customer(String i, String f, String l, String e, String p)
    {
        id = i;
        first = f;        
        last = l;        
        email = e;
        password = p;
    }
    
    public String toString()
    {
      return first+ " "+last;
    }
    
}
