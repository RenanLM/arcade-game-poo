package game.Model;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	
	public Player() {
		this.x = 100;
		this.y = 100;
	}
	
	public void load() {
		ImageIcon ref = new ImageIcon("src//res//spacenave1.png");
		imagem = ref.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void teclaPress(KeyEvent tecla){
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_UP)
			dy = -3;
		
		if(cod == KeyEvent.VK_DOWN)
			dy = 3;
		
		if(cod == KeyEvent.VK_RIGHT)
			dx = 3;
		
		if(cod == KeyEvent.VK_LEFT)
			dx = -3;
	}
	
	public void teclaSolta(KeyEvent tecla){
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_UP)
			dy = 0;
		
		if(cod == KeyEvent.VK_DOWN)
			dy = 0;
		
		if(cod == KeyEvent.VK_RIGHT)
			dx = 0;
		
		if(cod == KeyEvent.VK_LEFT)
			dx = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
}
