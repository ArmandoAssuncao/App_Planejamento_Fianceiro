package app;

import gui.framePrincipal.GuiPrincipal;
import gui.temas.Tema;

/**
 * Esta classe inicia o aplicativo de gerenciamento financeiro.
 * 
 * @author Armando Assuncao
 * @author Richardson William
 *
 */
public class PlanejamentoFinanceiro {
	
	/**
	 * Cria uma instância do programa
	 * @param args <code>String[]</code> com os argumentos de linha de comando. Não são utilizados.
	 */
	public static void main(String[] args) {
		Tema.mudaTema();
		new GuiPrincipal();
	}
}
