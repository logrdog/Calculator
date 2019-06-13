/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

/**
 *
 * @author logan
 */
public class TestDriver {
    public static void main(String[] args){
        
        ArithmeticGUI gui = new ArithmeticGUI();
        GameController gameC = new GameController();
        Game game = new Game();
        
        //String str = gui.toString();
        
        String[][] arr = gameC.getArray();
        
        /*for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                gameC.makeBlank(i, j);
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }*/
        gameC.removePosition(5, 5);
        System.out.println(gameC.checkRight(5, 5));
        System.out.println(gameC.checkUp(5, 5));
        System.out.println(gameC.checkDown(5, 5));
        System.out.println("\n");
        System.out.println(gameC.getGrid());
        System.out.println("\n");
        //System.out.println(str);
    }
}
