/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockey;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Logs
 */
public class Puck {
    private int x, y, width = 20, height = 20;
    
    private int dX, dY;
    
    int p1score = 0, p2score = 0;
    
    private Random rand;
    
    private Hockey hockey;
    
    public Puck(Hockey hockey){
        this.hockey = hockey;
        
        this.rand = new Random();
        
        setPuck();
    }
    
    public void setPuck(){
        this.x = (int)hockey.getWidth() / 2 - this.width / 2;
        this.y = (int)hockey.getHeight() / 2 - this.width / 2;
        
        this.dY = rand.nextInt(4)+1;
        
        if(dY == 0){
            dY = 1;
        }
        
        if(rand.nextBoolean()){
            dX = 1;
        }
        else{
            dX = -1;
        }
    }
    
    public int checkCollision(Paddle paddle){
        if(this.x < paddle.getX() + paddle.getPaddleWidth() && this.x + width > paddle.getX() && this.y < paddle.getY()+paddle.getPaddleHeight()&&this.y + height > paddle.getY()){
            return 1;
        }
        return 0;
    }
    
    public void update(Paddle p1, Paddle p2){
        int speed = 4;
        
        this.x += dX;
        this.y += dY;
        
        if(this.y + dY < 0){
                this.dY = speed;
        }
        if(this.y + height - dY > hockey.getHeight()){
            this.dY = -speed;
        }
        if((this.x + width - dX > hockey.getWidth())&&
                (this.y + height -dY < (hockey.getHeight()/2-10)||this.y + height - dY > (hockey.getHeight()/2+10))){
            this.dX = -speed;
        }
        //if((this.x + width - dX > hockey.getWidth())&&
                //(this.y + height -dY > (hockey.getHeight()/2-10)&&this.y + height - dY < (hockey.getHeight()/2+10))){
        if(this.x + width - dX > hockey.getWidth()){     
            p1score++;
            p1.setScore(p1score);
            setPuck();
        }
        if((this.x + width - dX < 0)&&
                (this.y + height -dY < (hockey.getHeight()/2-10)||this.y + height - dY > (hockey.getHeight()/2+10))){
            this.dX = speed;
        }
        //if((this.x + width - dX < 0)&&
               //(this.y + height -dY > (hockey.getHeight()/2-10)&&this.y + height - dY < (hockey.getHeight()/2+10))){
        if(this.x + width - dX < 0){       
            p2score++;
            p2.setScore(p2score);
            setPuck();
        }
        
        if(checkCollision(p2)==1){
            this.dX = -dX;
        }
        
        if(checkCollision(p1)==1){
            this.dX =-dX;
        }
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
   
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
    }
}
