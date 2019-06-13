/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockey;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Logs
 */
public class Hockey implements ActionListener, KeyListener {
    
    
    public static Hockey hockey;
    
    private int width = 1000, height = 700;
    
    private Paddle p1, p2;
    
    private Puck puck;
    
    private Goal goal1, goal2;
    
    private boolean bot = false;
    
    private boolean w, s, up, down;
    
    private int gameState = 0, winning_score = 7, playerWon, botCooldown = 10, botMove=0;
    
    private int botMoves;
    
    private Random rand;
    
    private JFrame frame;
    
    private Timer timer;
    
    JLabel label;
    
    public RenderScreen render = new RenderScreen();
    
    public Hockey(){
        timer = new Timer(20, this);
        
        rand = new Random();
        
        frame = new JFrame("Air Hockey");
        
        render = new RenderScreen();
        
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(render);
        frame.addKeyListener(this);
        frame.setResizable(false);
        
        
        timer.start();
    }
    
    public void start(){
        gameState = 2;
        p1 = new Paddle(this, 1);
        p2 = new Paddle(this, 2);
        puck = new Puck(this);
        goal1 = new Goal(this, 1);
        goal2 = new Goal(this, 2);
    }
    
     public double getHeight(){
        return frame.getHeight();
    }
    
    public double getWidth(){
        return frame.getWidth();
    }

    public void render(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", 1, 50));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(gameState == 0){
            
            g.drawString("Hockey", width/2, 50);
            g.drawString("Press Space to play", width / 2-150, height /2-25);
            g.drawString("Press shift to play with bot", width / 2-200, height /2+25);
        }
        if(gameState == 1){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", 1, 50));
            g.drawString("paused", width / 2 - 100, height / 2 -25);
        }
        if(gameState == 1 || gameState == 2){
            g.setColor(Color.WHITE);
            
            g.setStroke(new BasicStroke(5f));
            
            g.drawLine(width / 2, 0, width / 2, height);
            
            g.drawOval(width / 2 - 150, width / 2 - 150, 300, 300);
            
            g.setFont(new Font("Times New Roman" , 1, 50));
            
            g.drawString(String.valueOf(p1.getScore()), width / 2 -90, 50);
            g.drawString(String.valueOf(p2.getScore()), width / 2 +90, 50);
            
            puck.update(p1, p2);
            
            p1.render(g);
            p2.render(g);
            puck.render(g);
            goal1.render(g);
            goal2.render(g);
        }
        if(gameState == 3){
            g.setColor(Color.WHITE);
            
            g.setFont(new Font("Times New Roman", 1, 50));
            
            g.drawString("Hockey", width/2 -75, 50);
            
            if(bot && playerWon == 2){
                g.drawString("You lost :(", width/2 -170, 200);
            }else{
                g.drawString("Player" + playerWon + "wins!", width /2 -165, 200);
            }
            g.setFont(new Font("Times New Roman", 1 , 30));
            
            g.drawString("press SPACE to play again", width/ 2 - 185, height /2 -25);
            g.drawString("press ESC for menu", width /2 - 140, height / 2 + 25);
        }
    }
    
    public void update(){
        if(p1.getScore() >= winning_score){
            playerWon = 1;
            gameState = 3;
        }
        
        if(p2.getScore() >= winning_score){
            gameState = 3;
            playerWon = 2;
        }
        
        if(w){
            p1.move(true);
        }
        if(s){
            p1.move(false);
        }
        
        if(up){
            p2.move(true);
            }
        if(down){
            p2.move(false);
        }
        puck.update(p1, p2);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        hockey = new Hockey();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameState == 2){
            update();
        }
        render.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int id = e.getKeyCode();
       
       if(id == KeyEvent.VK_W){
           w = true;
       }else if(id == KeyEvent.VK_S){
           s = true;
       }else if(id == KeyEvent.VK_UP){
           up = true;
       }else if(id == KeyEvent.VK_DOWN){
           down = true;
       }else if(id == KeyEvent.VK_ESCAPE && (gameState == 0||gameState == 3)){    
           gameState = 0;
       }else if (id == KeyEvent.VK_SHIFT && gameState == 0){
           bot = true;
       }else if(id == KeyEvent.VK_SPACE){
           if(gameState == 0 || gameState == 3){
               start();
           }else if(gameState == 1){
               gameState = 2;
               timer.start();
           }else if (gameState == 2){
               gameState = 1;
               timer.stop();
           }
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        
        if(id == KeyEvent.VK_W){
            w = false;
        }else if(id == KeyEvent.VK_S){
            s = false;
        }else if(id == KeyEvent.VK_UP){
            up = false;
        }else if(id == KeyEvent.VK_DOWN){
            down = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

}
