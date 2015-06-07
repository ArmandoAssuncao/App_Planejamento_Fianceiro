package gui.framePrincipal;

import gui.painelDespesas.PainelDespesas;
import gui.painelGraficos.PainelGraficos;
import gui.painelInicio.PainelInicio;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTabbedPane;


public class AbasPrincipal extends JTabbedPane{
	private final int TAM_PAINEL_X = 1150;
	private final int TAM_PAINEL_Y = 645;
	
	private final String NOME_ABA_1 = "<html>I<br>N<br>I<br>C<br>I<br>O";
	private final String NOME_ABA_2 = "<html>D<br>E<br>S<br>P<br>E<br>S<br>A<br>S"; 
	private final String NOME_ABA_3 = "<html>G<br>R<br>A<br>F<br>I<br>C<br>O<br>S"; 
	
	PainelInicio painelInicio = new PainelInicio();
	PainelDespesas painelDespesas = new PainelDespesas();
	PainelGraficos painelGraficos = new PainelGraficos();
	
	public AbasPrincipal(){
		
		add(NOME_ABA_1, painelInicio);
		add(NOME_ABA_2, painelDespesas);
		add(NOME_ABA_3, painelGraficos);
		
		setTabPlacement(JTabbedPane.LEFT);
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.RED);
		setVisible(true);
	}
	
}
