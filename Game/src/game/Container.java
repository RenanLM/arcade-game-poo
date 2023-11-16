package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.Model.Fase;

public class Container extends JFrame{
	

	//private static final long serialVersionUID = 1L;

	public Container() {
		
		add(new Fase());
		setTitle("SDUR"); //Titulo da janela
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Tamanho da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Parar o programa qnd fechar a janela
		setLocationRelativeTo(null); // Carregar a tela no centro
		this.setResizable(false); // Travar a tela na resolucao padrao
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Container());
	}
	
}