/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author logan
 */
public class ArithmeticGUI extends Application {
    private Random rand = new Random();
    private GameController gameC = new GameController();
    private GridPane root = new GridPane();
    private Button[][] btnArray = new Button[8][8];
    private String[][] btnStr = gameC.getArray();
    private int width = 700;
    private int height = 550;
   
    private Button resetButton;
    private Button winningButton;
    private EventHandler<ActionEvent> eventHandler;
    private Game game;
    private Scene scene;
    
    @Override
    public void start(Stage primaryStage) {

        eventHandler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try{
                    for(int row = 0; row < btnArray.length; row++){
                        for(int col = 0; col < btnArray[row].length; col++){
                            
                            if(((gameC.amountMatchRight(row, col)>=1&&gameC.amountMatchLeft(row, col)>=1)||(gameC.amountMatchDown(row, col)>=1&&gameC.amountMatchUp(row, col)>=1))&&
                                    event.getSource().equals(btnArray[row][col])){
                                
                                boolean stopcheck = false;  
                                if(gameC.amountMatchRight(row, col)>=1&& gameC.amountMatchLeft(row, col)>=1){
                                     int count = gameC.amountMatchRight(row, col);
                                     int pos = 0;
                                     stopcheck = true;
                                     root.getChildren().remove(btnArray[row][col]);
                                     while(pos<=count){
                                         btnArray[row][col+pos].setText(gameC.changeChar(row, col+pos));
                                         btnArray[row][col-pos].setText(gameC.changeChar(row, col-pos));
                                         if(pos == count){
                                             root.getChildren().remove(btnArray[row-pos][col]);
                                             root.getChildren().add(btnArray[row][col]);
                                             gameC.removePosition(pos, col);
                                         }
                                         pos++;
                                     }
                                     
                                /*} else if(stopcheck != true && gameC.amountMatchLeft(row, col)>=1){
                                        int count = 0;
                                        int pos = gameC.amountMatchLeft(row, col);
                                        stopcheck = true;
                                        root.getChildren().remove(btnArray[row][col]);
                                        gameC.removePosition(row, col-(pos/2));
                                        while(pos>=count){
                                         btnArray[row][col-pos].setText(gameC.changeChar(row, col-pos));
                                         
                                         pos--;
                                        }*/                          
                                } else if(stopcheck != true && gameC.amountMatchDown(row, col)>=1){
                                        int count = gameC.amountMatchDown(row, col);
                                        int pos = 0;
                                        stopcheck = true;
                                        root.getChildren().remove(btnArray[row][col]);
                                        
                                        while(pos<=count){
                                         btnArray[row+pos][col].setText(gameC.changeChar(row+pos, col));
                                         if(pos == count){
                                             
                                            root.getChildren().remove(btnArray[row-pos][col]);
                                            root.getChildren().add(btnArray[row][col]);
                                            gameC.removePosition(pos, col);
                                         }
                                         pos++;
                                        }                          
                                } else if(stopcheck != true && gameC.amountMatchUp(row, col)>=1){
                                        int count = 0;
                                        int pos = gameC.amountMatchUp(row, col);
                                        stopcheck = true;
                                        root.getChildren().remove(btnArray[row][col]);
                                        
                                        while(pos>=count){
                                         btnArray[row-pos][col].setText(gameC.changeChar(row-pos, col));
                                         if(pos == count){
                                             
                                            root.getChildren().remove(btnArray[pos][col]);
                                            root.getChildren().add(btnArray[row][col]);
                                            gameC.removePosition(pos, col);
                                         }
                                         pos--;
                                        }                           
                                }
                                
                            }
                            
                            System.out.println("-------------");
                            System.out.print(gameC.getGrid());
                            String[][] temp = gameC.getArray();
                            if(checkArray(temp)==0){
                                        root.getChildren().clear();
                                        winningButton = new Button();
                                        winningButton.setText("YOU WIN!!");
                                        winningButton.setPrefHeight(300);
                                        winningButton.setPrefWidth(300);
                                        root.getChildren().addAll(winningButton, resetButton);
                                    }
                        }
                    }
                    
                }catch(Exception ex){
                    
                }
                
                if(event.getSource().equals(resetButton)){
                    root.getChildren().clear();
                    gameC = new GameController();
                    initGrid();
                }
                
            }
        };
        
        scene = new Scene(initGrid(), width, height);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Arithmetic Crush!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public GridPane initGrid(){
        for(int row = 0; row < btnArray.length; row++){
            for(int col = 0; col < btnArray[row].length; col++){
                btnArray[row][col] = new Button();               
                btnArray[row][col].setPrefHeight(75);
                btnArray[row][col].setPrefWidth(75);
                btnArray[row][col].setText(gameC.getPosition(row, col));
                btnArray[row][col].addEventHandler(ActionEvent.ACTION, eventHandler);
                root.add(btnArray[row][col], col, row);
                
            }
        }
        resetButton = new Button();
        resetButton.setText("Reset Game.");
        resetButton.addEventHandler(ActionEvent.ACTION, eventHandler);
        root.add(resetButton, 9, 0);
        return root;
    }
    
    
    public void removeButton(int x, int y){
        root.getChildren().remove(btnArray[x][y]);
    }
    
    public void replace(int x, int y, Button b){
        root.getChildren().add(btnArray[x][y] = b);
    }
    
    public void setScene(){
        scene = new Scene(initGrid(), width, height);
    }
    
    public String toString(){
        String text = "";
        for(int i = 0; i < btnArray.length; i++){
            for(int j = 0; j < btnArray[i].length; j++){
                text += btnArray[i][j].getText();
            }
            text+="\n";
        }
        return text;
    }
    
    public boolean checkAboveLocation(int _row, int _col){
        boolean play = true;
        for(int row = _row; row < btnArray.length; row++){
            for(int col = _col; col < btnArray[row].length; col++){
                if(gameC.amountMatchRight(row, col) == 0 && gameC.amountMatchLeft(row, col) == 0 && 
                        gameC.amountMatchUp(row, col) == 0 && gameC.amountMatchDown(row, col) == 0){
                    play = false;
                }
            }
        }
        return play;
    }
    
    public boolean checkBelowLocation(int _row, int _col){
        boolean play = true;
        for(int row = _row; row >= 0; row--){
            for(int col = _col; col >= 0; col--){
                if(gameC.amountMatchRight(row, col) == 0 && gameC.amountMatchLeft(row, col) == 0 &&
                        gameC.amountMatchUp(row, col) == 0 && gameC.amountMatchDown(row, col) == 0){
                    play = false;
                }
            }
        }
        return play;
    }
    
    public int checkArray(String[][] arr){
        int moves = 0;
        boolean play = true;
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[row].length; col++){
                if((gameC.amountMatchRight(row, col) >= 1&&gameC.amountMatchLeft(row, col) >= 1)||(gameC.amountMatchDown(row, col) >= 1&&gameC.amountMatchUp(row, col) >= 1)){
                    moves++;
                    play = false;
                }
            }
        }
        return moves;
    }
}
