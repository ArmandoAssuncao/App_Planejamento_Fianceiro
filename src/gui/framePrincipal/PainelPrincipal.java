package gui.framePrincipal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Define o painel principal do aplicativo.
 *  
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class PainelPrincipal extends JPanel{
	/**
	 * Largura da janela.
	 */
	public final int TAM_PAINEL_PRINCIPAL_X = 1000;
	
	/**
	 * Altura da janela.
	 */
	public final int TAM_PAINEL_PRINCIPAL_Y = 500;
	
	AbasPrincipal abasPrincipal;

	/**
	 * Cria uma instância do painel principal.
	 * @param framePrincipal componente pai
	 */
	public PainelPrincipal(GuiPrincipal framePrincipal) {
		abasPrincipal = new AbasPrincipal(framePrincipal);
		//setLayout(new BorderLayout(0,0));

		add(abasPrincipal);
		setPreferredSize(new Dimension(TAM_PAINEL_PRINCIPAL_X, TAM_PAINEL_PRINCIPAL_Y));
		setBackground(Color.GRAY); //TODO degub APAGAR
		setVisible(true);
	}
}