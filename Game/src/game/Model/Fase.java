package game.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel implements ActionListener{
	
	private Image background;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private boolean inJogo;
	private int inimigosAbatidos = 0;
	
	public Fase() {
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon ref = new ImageIcon("src//res//background.jpg");
		background = ref.getImage();
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        background = background.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
		
		player = new Player();
		player.load();
		
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		
		inJogo = true;
	}
	
	public void inicializaInimigos() {
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		int coordenadas [] = new int[40];
		enemy1 = new ArrayList<Enemy1>();
		
		for (int i = 0; i < coordenadas.length; i++) {
		    int x = (int)(Math.random() * 8000 + 1024);  
		    int y = (int)(Math.random() * 680);  

		    enemy1.add(new Enemy1(x, y));
		    
		}

	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		
		if(inJogo){
			graficos.drawImage(background, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			
			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
			}
			
			for (int k = 0; k < enemy1.size(); k++) {
			    Enemy1 in = enemy1.get(k);
			    System.out.println("Inimigo " + k + ": x = " + in.getX() + ", y = " + in.getY());  // Checando as posicoes dos inimigos
			    in.load();
			    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			
			graficos.drawString("Inimigos Abatidos: " + inimigosAbatidos, 10, 20);

		}else {
			ImageIcon fimjogo = new ImageIcon("src//res//gameover.png");
			graficos.drawImage(fimjogo.getImage(), 0, 0, null);
		}
		
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if(m.isVisible())
				m.update();
			else
				tiros.remove(i);			
		}
		
		Iterator<Enemy1> iterator = enemy1.iterator();
		while (iterator.hasNext()) {
		    Enemy1 in = iterator.next();
		    if (in.isVisible()) {
		        in.update();
		    } else {
		        iterator.remove();
		    }
		}

		checarColisoes();
		repaint();
	}
	
	public void checarColisoes() {
		Rectangle naveForm = player.getBounds();
		Rectangle enemy1Form;
		Rectangle tiroForm;
		
		for (int k = 0; k < enemy1.size(); k++) {
			Enemy1 tempEnemy1 = enemy1.get(k);
			enemy1Form = tempEnemy1.getBounds();
			if(naveForm.intersects(enemy1Form)) {
				player.setVisivel(false);
				tempEnemy1.setVisible(false);
				inJogo = false;
				inimigosAbatidos++;
			}	
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro tempTiro = tiros.get(i);
			tiroForm = tempTiro.getBounds();
			for (int j = 0; j < enemy1.size(); j++) {
				Enemy1 tempEnemy1 = enemy1.get(j);
				enemy1Form = tempEnemy1.getBounds();
				if(tiroForm.intersects(enemy1Form)) {
					tempEnemy1.setVisible(false);
					tempTiro.setVisible(false);
					inimigosAbatidos++;
				}
			}
		}
		
	}
	
	private class TecladoAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			player.teclaPress(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.teclaSolta(e);
		}
	}
}	
