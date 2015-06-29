package gui.framePrincipal;

import gui.painelDespesas.IgPainelDespesas;
import gui.painelGraficos.PainelGraficos;
import gui.painelInicio.PainelInicio;
import gui.painelRenda.IgPainelRenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTabbedPane;

public class AbasPrincipal extends JTabbedPane{
	private final int TAM_PAINEL_X = 990;
	private final int TAM_PAINEL_Y = 645;
	
	private final String NOME_ABA_1 = "<html>&nbsp;I<br>N<br>&nbsp;Í<br>C<br>&nbsp;I<br>O";
	//private final String NOME_ABA_1 = "<html>C<br>O<br>M<br>E<br>Ç<br>O";
	private final String NOME_ABA_2 = "<html>R<br>E<br>N<br>D<br>A";
	private final String NOME_ABA_3 = "<html>D<br>E<br>S<br>P<br>E<br>S<br>A<br>S"; 
	private final String NOME_ABA_4 = "<html>G<br>R<br>A<br>F<br>&nbsp;í<br>C<br>O<br>S"; 
	
	PainelInicio painelInicio;
	IgPainelDespesas painelDespesas;
	PainelGraficos painelGraficos;
    IgPainelRenda igPainelRenda;
	
	public AbasPrincipal(Window framePrincipal){
		painelInicio = new PainelInicio(framePrincipal);
		painelDespesas = new IgPainelDespesas(framePrincipal);
		painelGraficos = new PainelGraficos(framePrincipal);
        igPainelRenda = new IgPainelRenda(framePrincipal);
		
     //   String fonteDefault = new JLabel().getFont().getFontName(); //Pega a fonte default do sistema
		Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		setFont(fonte);
        
		add(NOME_ABA_1, painelInicio);
		add(NOME_ABA_2, igPainelRenda);
		add(NOME_ABA_3, painelDespesas);
		add(NOME_ABA_4, painelGraficos);
		
		setTabPlacement(JTabbedPane.LEFT);
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.RED);
		setVisible(true);
	}
}
