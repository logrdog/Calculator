
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author logan
 */
public class Book {
    LinkedList<String> name;
    
    public Book(){
        name = new LinkedList<String>();
        name.add("casper");
        name.add("10000leaguesunder");
        name.add("\"huckleberryfin\"");
        name.add("tokillamockingbird");
        name.add("1981");
    }
    
    public Iterator iterator(){
         return name.iterator();
    }
}
