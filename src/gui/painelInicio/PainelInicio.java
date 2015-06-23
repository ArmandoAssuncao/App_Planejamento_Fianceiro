package gui.painelInicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Window;

import javax.swing.JPanel;

public class PainelInicio extends JPanel{
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;

	public PainelInicio(Window framePrincipal) {
		
		
		setLayout(new GridBagLayout()); //TALVEZ
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.BLUE);
		setVisible(true);
	}
	
}
