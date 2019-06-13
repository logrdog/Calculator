package javafxapplication3;


import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author logan
 */
public class Game {
    private Random rand = new Random(); 
    private String[][] gameStrings = new String[8][8];
    private String[] strarr = new String[]{"+","-","/","*","&"};
    private int randInt;
    
    public Game(){
        setGame();
    }
    
    public void setGame(){
        for(int row = 0; row < gameStrings.length; row++){
            for(int col = 0; col < gameStrings[row].length; col++){
                randInt = rand.nextInt(5);
                gameStrings[row][col] = strarr[randInt];
            }
        }
    }
    
    public String[][] getGame(){
        return gameStrings;
    }
    
    public void changeChar(int x, int y){
        for(int row = 0; row < gameStrings.length; row++){
            for(int col = 0; col < gameStrings[row].length; col++){
                randInt = rand.nextInt(5);
                gameStrings[x][y] = strarr[randInt];
            }
        }
    }
    
    public String toString(){
        String text = "";
        for(int i =0; i < gameStrings.length; i++){
            for(int j =0; j < gameStrings[i].length; j++){
                text += gameStrings[i][j];
            }
            text+="\n";
        }
        return text;
    }
}
