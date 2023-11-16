package game.Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel implements ActionListener{
	
	private Image background;
	private Player player;
	private Timer timer;
	
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
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(background, 0, 0, null);
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
		
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(),m.getX(),m.getY(),this);
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
		
		repaint();
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
