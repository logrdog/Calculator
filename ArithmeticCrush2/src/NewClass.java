
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
public class NewClass {
    public static void main(String[] args){
        LinkedList<String> name = new LinkedList<String>();
        name.add("casper");
        name.add("10000leaguesunder");
        name.add("\"huckleberryfin\"");
        name.add("tokillamockingbird");
        name.add("1981");
        
        String target = "1981";
        String replace = "Cliffard big red";
        
        String str = "";
        int count = 0;
        Iterator<String> itr = name.iterator();
        while(itr.hasNext()){
            str += itr.next() + "-";
            count++;
            if(itr.next().compareTo(target)>0){
                itr.remove();
                
                name.set(count, replace);
            }
        }
        
        System.out.println(str);
    }
}
