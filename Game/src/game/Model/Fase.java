package game.Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import game.Model.TelaMenu;
import javax.swing.Timer;
import javax.lang.model.element.RecordComponentElement;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel implements ActionListener{
	
	private Clip exploSound;
	private Image background;
	private Image fimjogo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private boolean inJogo;
	private int inimigosAbatidos = 0;
	private int recorde = 0;
	private static final String MAIOR_RECORDE = "recorde.txt";
	
	private void carregarRecorde() {
	    try {
	        File arquivo = new File(MAIOR_RECORDE);

	        if (arquivo.exists()) {
	            Scanner scanner = new Scanner(arquivo);
	            if (scanner.hasNextInt()) {
	                recorde = scanner.nextInt();
	            }
	            scanner.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void salvarRecorde() {
	    try {
	        File arquivo = new File(MAIOR_RECORDE);
	        PrintWriter writer = new PrintWriter(arquivo);
	        writer.print(recorde);
	        writer.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public Fase() {
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon ref = new ImageIcon("src//res//background.jpg");
		background = ref.getImage();
		
		ImageIcon ref2 = new ImageIcon("src//res//gameover-removebg-preview.png");
		fimjogo = ref2.getImage();
		
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        background = background.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        fimjogo = fimjogo.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        
		player = new Player();
		player.load();
		
		
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		
		carregarRecorde();		
		try {
            File soundFile = new File("src//res//explosao.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            exploSound = AudioSystem.getClip();
            exploSound.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		inJogo = true;
	}
	
	public void inicializaInimigos() {
		
		
		int coordenadas [] = new int[250];
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
			    System.out.println("Inimigo " + k + ": x = " + in.getX() + ", y = " + in.getY());
			    in.load();
			    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			
			Font minhaFonte = new Font("Impact", Font.PLAIN, 16);
		    graficos.setFont(minhaFonte);
			graficos.setColor(Color.WHITE);
			graficos.drawString("Inimigos Abatidos: " + inimigosAbatidos, 10, 20);
			graficos.drawString("Recorde: " + recorde, 10, 40);

		}else {
			
			graficos.drawImage(fimjogo, 0, 0, null);
			//Tela.telaAtual = 2;
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
		
		
		boolean colisaoDetectada = false;

		for (int k = 0; k < enemy1.size(); k++) {
		    Enemy1 tempEnemy1 = enemy1.get(k);
		    enemy1Form = tempEnemy1.getBounds();
		    
		    if (naveForm.intersects(enemy1Form)) {
		        if (!colisaoDetectada) {
		            player.setVisivel(false);
		            tempEnemy1.setVisible(false);
		            inJogo = false;
		            inimigosAbatidos++;

		            if (exploSound != null) {
		                if (!exploSound.isRunning()) {
		                    exploSound.stop();
		                    exploSound.setFramePosition(0);
		                    exploSound.start();
		                }
		            }

		            colisaoDetectada = true;
		        }
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
					
					if(inimigosAbatidos % 25 == 0)
						tempEnemy1.setVELOCIDADE(1);
				}
			}
			
			if (inimigosAbatidos > recorde) {
		        recorde = inimigosAbatidos;
		        salvarRecorde();
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
