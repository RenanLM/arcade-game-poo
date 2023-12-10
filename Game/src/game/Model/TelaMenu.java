package game.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TelaMenu extends Tela{
	private JButton startButton;
    private JButton quitButton;
    private JPanel painel;
    private Image fundo;

    public TelaMenu (){
        this.painel = new JPanel();
        this.startButton = new JButton();
        this.quitButton = new JButton();

        this.setTitle("MENU");
        this.startButton.setText("Start");
        this.startButton.setBackground(Color.WHITE);
        this.startButton.setBounds(700, 60, 110, 33);
        this.quitButton.setText("Quit");
        this.quitButton.setBackground(Color.WHITE);
        this.quitButton.setBounds(700, 120, 110, 33);
        //ImageIcon icon = new ImageIcon("src/res/background.jpg");
        //this.fundo = icon.getImage();
        //fundo = Toolkit.getDefaultToolkit().getImage("src/res/background.jpg");
        ImageIcon ref = new ImageIcon("src//res//background.jpg");
		fundo = ref.getImage();
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        fundo = fundo.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
         
   

        this.add(painel);
        painel.add(this.startButton);
        painel.add(this.quitButton);
        //painel.setLayout(new BorderLayout());

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
        
        /*this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
               super.paintComponent(g);
               g.drawImage(fundo, 0, 0, this);
            }
         });*/
        
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.drawImage(fundo, 0, 0, this);
    }	
}
