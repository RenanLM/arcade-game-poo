package game.Model;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.Model.Fase;

public class TelaFase extends Tela{
	

	//private static final long serialVersionUID = 1L;

	public TelaFase() {
	
		add(new Fase());
	}
}
	