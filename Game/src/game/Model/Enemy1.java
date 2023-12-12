package game.Model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisible;
	
	private static int VELOCIDADE = 2;
	
	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}
	
	public void load() {
		ImageIcon ref = new ImageIcon("src//res//enemy1.png");
		imagem = ref.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		this.x -= VELOCIDADE;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE += vELOCIDADE;
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