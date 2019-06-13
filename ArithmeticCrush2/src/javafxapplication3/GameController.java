/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.util.Random;

/**
 *
 * @author logan
 */
public class GameController {
    private Game game;
    private String[][] checkArr;
    
    public GameController(){
        game = new Game();
        checkArr = game.getGame();
    }
    
    public boolean checkRight(int _row, int _col){
        boolean flag = false;
        boolean validFlag = false;
        int count = 0;
        //for(int col = _col; col < checkArr[_row].length && flag != true && validFlag != true; col++){
           if((_col < checkArr[_row].length-1) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row][_col+1]))){
                count++;
                if(count == 1){
                    validFlag = true;
                }
           }else{
               count = 0;
           }
        return validFlag;
    }
    
    public int amountMatchRight(int _row, int _col){
        boolean flag = false;
        int count = 0;
        for(int col = _col; col < checkArr[_row].length && flag != true; col++){
           if((col < checkArr[_row].length-1) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row][col+1]))){
                count++;
           }else{
               flag = true;
            }
        }
        return count;
    }
    
    
    public boolean checkLeft(int _row, int _col){
        boolean flag = false;
        boolean validFlag = false;
        int count = 0;
        if(_col < checkArr[_row].length){
            //for(int col = _col; col > 0 && flag != true && validFlag != true; col--){
                if((_col > 0) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row][_col-1]))){
                    
                    count++;
                    if(count == 1){
                        validFlag = true;
                    }
                }else{
                    count = 0;
                }    
            }
        return validFlag;
    }
    
    public int amountMatchLeft(int _row, int _col){
        boolean flag = false;
        int count = 0;
        if(_col < checkArr[_row].length){
            for(int col = _col; col > 0 && flag != true; col--){
                if((col > 0) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row][col-1]))){
                    
                count++;
           }else{
               flag = true;
            }
            
            }
        }
        return count;
    }
    
    public boolean checkUp(int _row, int _col){
        boolean flag = false;
        boolean validFlag = false;
        int count = 0;
        //for(int row = _row; row > 0 && flag != true && validFlag != true; row--){
           if((_row > 0) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row-1][_col]))){ 
                count++;
                if(count == 1){
                    validFlag = true;
                }
            }else{
               count = 0;
            }
        return validFlag;
    }
    
    
     public int amountMatchUp(int _row, int _col){
        boolean flag = false;
        int count = 0;
        for(int row = _row; row > 0 && flag != true; row--){
           if((row > 0) &&
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[row-1][_col]))){ 
                count++;
            }else{
               flag = true;
            }
        }
        return count;
    }
    
    public boolean checkDown(int _row, int _col){
        boolean flag = false;
        boolean validFlag = false;
        int count = 0;
        //for(int row = _row; row < checkArr.length && flag != true && validFlag != true; row++){
        if((_row < checkArr.length) && 
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[_row+1][_col]))){ 
                count++;
                if(count == 1){
                    validFlag = true;
                }
            }else{
               count = 0;
            }
        return validFlag;
    }
    
     public int amountMatchDown(int _row, int _col){
        boolean flag = false;
        int count = 0;
        for(int row = _row; row < checkArr.length && flag != true; row++){
           if((row < checkArr.length-1) && 
                   (checkArr[_row][_col].equalsIgnoreCase(checkArr[row+1][_col]))){ 
                count++;
            }else{
               flag = true;
            }
        }
        return count;
    }
    
    public boolean remove(int _row, int _col){
        boolean flag = false;
        if(amountMatchLeft(_row,_col)>=2||amountMatchRight(_row,_col)>=2||amountMatchUp(_row,_col)>=2||amountMatchDown(_row,_col)>=2){
            flag = true;
        }
        return flag;
    }
    
    public void makeBlank(int _row, int _col){
        if((amountMatchLeft(_row,_col)>=1&&amountMatchRight(_row,_col)>=1)||(amountMatchUp(_row,_col)>=1&&amountMatchDown(_row,_col)>=1)){
            checkArr[_row][_col] = "null";
        }
    }
    
    public String changeChar(int x, int y){
        Random rand = new Random();
        int randInt = 0;
        String[] strarr = new String[]{"+","-","/","*","&",};
        for(int row = 0; row < checkArr.length; row++){
            for(int col = 0; col < checkArr[row].length; col++){
                randInt = rand.nextInt(5);
                checkArr[x][y] = strarr[randInt];
            }
        }
        return checkArr[x][y];
    }
    
    
    public String getGrid(){
        String text = "";
        for(int i =0; i < checkArr.length; i++){
            for(int j =0; j < checkArr[i].length; j++){
                text += checkArr[i][j];
            }
            text+="\n";
        }
        return text;
    }
    
    public String[][] getArray(){
        return checkArr;
    }
    
    public String getPosition(int row, int col){
        return checkArr[row][col];
    }
    
    public void removePosition(int row, int col){
        checkArr[row][col]="null";
    }
    
    public void cleanBoard(){
       
        for(int row = 0; row < checkArr.length; row++){
            for(int col = 0; col < checkArr[row].length; col++){
                
                makeBlank(row, col);
                
                }
        }
    }
    
    public String[][] getCleanBoard(){
        String[][] temp = checkArr;
        cleanBoard();
        return temp;
    }
    
}
