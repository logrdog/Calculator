/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockey;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Logs
 */
public class RenderScreen extends JPanel {
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Hockey.hockey.render((Graphics2D)g);
    }
}
