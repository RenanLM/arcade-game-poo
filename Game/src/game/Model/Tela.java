package game.Model;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Tela extends JFrame{
	public static int telaAtual = 0;
	
	public Tela() {
		setTitle("Alien Attack on Earth"); //Titulo da janela
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Tamanho da janela
		//int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        //int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		//setSize(1024,728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Parar o programa qnd fechar a janela
		setLocationRelativeTo(null); // Carregar a tela no centro
		this.setResizable(false); // Travar a tela na resolucao padrao

	    setVisible(false);
		
		ImageIcon icon = new ImageIcon("src//res//spacenave1.png");
		setIconImage(icon.getImage());
	}
}
