package game.Model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List <Tiro> tiros;
	private boolean isVisivel;
	
	public Player() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		tiros = new ArrayList<Tiro>();
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
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + (altura/2)));
	}
	
	public void teclaPress(KeyEvent tecla){
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_SPACE) // Atirar
			tiroSimples();
		
		if(cod == KeyEvent.VK_W) // Pra cima
			dy = -3;
		
		if(cod == KeyEvent.VK_S) // Pra baixo
			dy = 3;
		
		if(cod == KeyEvent.VK_D) //Pra direita
			dx = 3;
		
		if(cod == KeyEvent.VK_A) //Pra esquerda
			dx = -3;
	}
	
	public void teclaSolta(KeyEvent tecla){
		int cod = tecla.getKeyCode();
		
		if(cod == KeyEvent.VK_W)
			dy = 0;
		
		if(cod == KeyEvent.VK_S)
			dy = 0;
		
		if(cod == KeyEvent.VK_D)
			dx = 0;
		
		if(cod == KeyEvent.VK_A)
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

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	
	
	
	
}
