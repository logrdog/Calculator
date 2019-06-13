/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcgui;

import java.util.Stack;

/**
 *
 * @author logan
 */
public class CalcStack {
    private Stack<Integer> topNumber;
    private Stack<Integer> bottomNumber;
    private Stack<Integer> resultStack;

    public CalcStack(){
        topNumber = new Stack<Integer>();
        bottomNumber = new Stack<Integer>();
        resultStack = new Stack<Integer>();
    }
    /**
     * 
     * @param str 
     * sets top stack
     */
    public void setStackTop(String str){
        String num = str;
        char ch = 'a';
        int convert = 0;
        int count = topNumber.size();
        while(count + num.length() < getGreaterSize()){
                topNumber.push(0);
                count++;
        }
        for(int i = 0; i < num.length(); i++){   
            ch = num.charAt(i);
            convert = Character.getNumericValue(ch);
            topNumber.push(convert);
            
        }
    }
    /** 
     * 
     * @param str 
        sets bottom stack
     */
    public void setStackBottom(String str){
        String num = str;
        char ch = 'a';
        int convert = 0;
        int count = bottomNumber.size();
        while(count + num.length() < getGreaterSize()){
                bottomNumber.push(0);
                count++;
        }
        for(int i = 0; i < num.length(); i++){ 
            ch = num.charAt(i);
            convert = Character.getNumericValue(ch);
            bottomNumber.push(convert);
        }
        
    }
    /** gets the size of top
      @return integer
    */
    public int getTopSize(){
        return topNumber.size();
    }
    /** gets the size of bottom
      @return integer
    */
    public int getBottomSize(){
        return bottomNumber.size();
    }
    /** @return integer
      finds the greater size of the two stacks
    */
    public int getGreaterSize(){
        int size = 0;
        int top = topNumber.size();
        int bottom = bottomNumber.size();
        if(top > bottom){
            size = top;
        }else{
            size = bottom;
        }
        return size;
    }
    
    /** @return integer
      finds the greater size of the two stacks
    */
    public int getGreaterSize(String str, String str2){
        int size = 0;
        int top = str.length();
        int bottom = str.length();
        if(top > bottom){
            size = top;
        }else{
            size = bottom;
        }
        return size;
    }
    
    /** 
     * adds two values together using a stack pushing into resultStack
     */
    public void add(){
        int place = 0;
        int carry = 0;
        int value = 0;
        while(!topNumber.empty() && !bottomNumber.empty()){
            int top = topNumber.pop(); int bottom = bottomNumber.pop();
            
            System.out.print("top:"+top+"//");
            System.out.print("bottom:"+bottom+"//");
            
            if(top + bottom + carry < 10){
                value = top + bottom +carry;
                carry = 0;
            }else{
                value = ((top + bottom +carry)%10);
                carry = 1;   
            }
            System.out.println(value);
            System.out.println(carry);
            resultStack.push(value);
            if(topNumber.isEmpty()&&carry != 0){
                resultStack.push(carry);
            }
            
            place++; 
        }
    }
    
    /** 
      subtracts two values and pushes them into result Stack
    */
    public void subtract(){
        int place = 0;
        int borrow = 0;
        int value = 0;
        
        while(!topNumber.empty() && !bottomNumber.empty()){
            int top = topNumber.pop();
            int bottom = bottomNumber.pop();
            System.out.print(top+"//");
            System.out.print(bottom+"//");
            System.out.print(borrow+"//");
            value = (top - bottom) - borrow;
           
            if(value < 0){
                value += 10;
                borrow = 1;
            }else{
                borrow = 0;
            }
            
            System.out.print("value:"+value);
            resultStack.push(value);
            
            if(topNumber.isEmpty() && resultStack.peek() == 0){
                resultStack.pop();
            }
            place++;
        }
    }
    
    /** 
     returns String of resultStack
     * @return String
    */
    public String getString(){
        String str = "";
        
        while(!resultStack.empty()){
            str += resultStack.pop();
        }
        
        return str;
    }
}
