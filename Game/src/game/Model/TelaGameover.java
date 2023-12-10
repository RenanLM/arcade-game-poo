package game.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class TelaGameover extends Tela{
	private Image fundo;
	
	public TelaGameover() {
		ImageIcon ref = new ImageIcon("src//res//gameover.jpg");
		Image fundo = ref.getImage();
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        fundo = fundo.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fundo, 0, 0, null);
	}
}
