/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockey;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Logs
 */
public class Goal {
    public int goalNum;
    public int x, y, width=30, height=700;
    public int score;
    
    public Goal(Hockey hockey, int goalNum){
        this.goalNum = goalNum;
        
        if(goalNum == 1){
            this.x = -20;
        }
        
        if(goalNum == 2){
            this.x = (int)hockey.getWidth()-20;
        }
        this.y = (int)hockey.getHeight() / 2 - this.height / 2;
    }
    
    public int getGoalWidth(){
        return this.width;
    }
    
    public int getGoalHeight(){
        return this.height;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }
}
