
package game.Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TelaMenu extends Tela {

    private JButton startButton;
    private JButton quitButton;
    private Image fundo;
    private Clip trilha;
    
    

    public TelaMenu() {
        
    	
    	setLayout(new BorderLayout());
        
        JPanel centralPanel = new JPanel(new GridBagLayout()); 
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.add(startButton, gbc);

        gbc.gridy = 1;
        centralPanel.add(quitButton, gbc);

        
        add(centralPanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tela.telaAtual = 1;
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tela.telaAtual = -1;
            }
        });

        carregarRecursos();

    }

    private void carregarRecursos() {
        ImageIcon ref = new ImageIcon(getClass().getResource("/res/background.jpg"));
        fundo = ref.getImage();

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        fundo = fundo.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
        
        try {
            File soundFile = new File("src//res//trilha.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            trilha = AudioSystem.getClip();
            trilha.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void iniciarMusica() {
        
        if (trilha != null) {
            trilha.loop(trilha.LOOP_CONTINUOUSLY);
            trilha.start();
        }
    }
    
    public void pararMusica() {
        
        if (trilha != null && trilha.isRunning()) {
            trilha.stop();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fundo, 0, 0, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("IMPACT", Font.PLAIN, 100));
        String mensagem = "Alien Attack on Earth";
        int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagem) / 2;
        int y = getHeight() / 4;
        g.drawString(mensagem, x, y);
    }
    
    
    
}


