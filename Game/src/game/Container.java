package game;

import game.Model.Tela;
import game.Model.TelaFase;
import game.Model.TelaGameover;
import game.Model.TelaMenu;

public class Container{
	public static Tela arrTelas[];
	
	public static void main(String[] args) {
		arrTelas = new Tela[] {
				new TelaMenu(), new TelaFase(), new TelaGameover()
		};
		
		while(Tela.telaAtual != -1) {
            System.out.print("");
        	int j = verificarTelaAtividada();
            if (j != Tela.telaAtual) {
                trocarTela(j, Tela.telaAtual);
            }
        }

    }

    public static void trocarTela(int k, int i) { 
    	if(Tela.telaAtual >= 0)
    		arrTelas[i].setVisible(true);
    	
    	if (k >= 0)
            arrTelas[k].setVisible(false);

        
    }

    public static int verificarTelaAtividada() {
        int j = -1;

        for (int i = 0; i < arrTelas.length; i++) {
        	
            if (arrTelas[i].isVisible() == true) {
                j = i;
                break;
            }
        }

        return j;
	}
}