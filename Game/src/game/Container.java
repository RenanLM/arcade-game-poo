package game;

import javax.swing.JFrame;

import game.Model.Fase;

public class Container extends JFrame{
	

	//private static final long serialVersionUID = 1L;

	public Container() {
		
		add(new Fase());
		setTitle("Is that a gay pride flag?"); //Titulo da janela
		setSize(1366,768); // Tamanho da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Parar o programa qnd fechar a janela
		setLocationRelativeTo(null); // Carregar a tela no centro
		this.setResizable(false); // Travar a tela na resolucao padrao
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Container();
	}
	
}
