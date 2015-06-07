package gui.framePrincipal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PainelPrincipal extends JPanel{
	public final int TAM_PAINEL_PRINCIPAL_X = 1000;
	public final int TAM_PAINEL_PRINCIPAL_Y = 500;
	
	AbasPrincipal abasPrincipal = new AbasPrincipal();

	public PainelPrincipal() {
		//setLayout(new BorderLayout(0,0));

		add(abasPrincipal);
		setPreferredSize(new Dimension(TAM_PAINEL_PRINCIPAL_X, TAM_PAINEL_PRINCIPAL_Y));
		setBackground(Color.GRAY); //APAGAR
		setVisible(true);
	}
}