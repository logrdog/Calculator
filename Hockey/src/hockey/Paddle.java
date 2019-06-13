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
public class Paddle {
    public int paddleNum;
    public int x, y, width=50, height=50;
    public int score;
    
    private Hockey hockey;
    
    public Paddle(Hockey hockey, int paddleNum){
        this.paddleNum = paddleNum;
        
        if(paddleNum == 1){
            this.x = 10;
        }
        
        if(paddleNum == 2){
            this.x = (int)hockey.getWidth() - 70;
        }
        this.y = (int)hockey.getHeight() / 2 - this.height / 2;
    }
    
    public void move(boolean up){
        int speed = 5;
        
        if(up == true){
            if(this.y - speed > 0){
                this.y-=speed;
            }
            else{
                this.y=0;
            }
        }
        if(up == false){
            if(this.y + height <= 700){
                this.y+=speed;
            }
            else{
                this.y= (int)hockey.getHeight() - height;
            }
        }
    }
    
    public int getPaddleWidth(){
        return this.width;
    }
    
    public int getPaddleHeight(){
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
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
