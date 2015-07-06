package gui.painelInicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Window;

import javax.swing.JPanel;
/**
 * Define o painel da tela inicial do aplicativo.
 * @author Armando Assunção
 * @author Richardson William
 */
public class PainelInicio extends JPanel{
	private static final long serialVersionUID = 6712709322873708218L;

	private final int TAM_PAINEL_X = 800;
	private final int TAM_PAINEL_Y = 600;

	/** 
	 * Construtor 
	 * @param framePrincipal componente pai.
	 */
	//TODO verificar comentario acima
	public PainelInicio(Window framePrincipal) {
		setLayout(new GridBagLayout()); //TODO TALVEZ
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.BLUE);
		setVisible(true);
	}
	
}
