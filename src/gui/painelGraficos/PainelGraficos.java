package gui.painelGraficos;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class PainelGraficos extends JPanel {
	private JPanel painelTitulo;
	private JPanel painelDeGraficos;
	private JPanel painelBotoes;

	public PainelGraficos(Window framePrincipal) {
		setLayout(new BorderLayout(0, 0));
		
		iniciaElementos();
		
		criaPainelTitulo();
		criaPainelDeGraficos();
		criaPainelBotoes();
		
		add(painelBotoes, BorderLayout.EAST);
		add(painelDeGraficos, BorderLayout.CENTER);
		add(painelTitulo, BorderLayout.NORTH);

		painelDeGraficos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	private void iniciaElementos(){
		painelTitulo = new JPanel();
		painelBotoes = new JPanel();
		painelDeGraficos = new JPanel();
	}
	
	private void criaPainelTitulo(){
		final int TAM_X = 500;
		final int TAM_Y = 130;
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setVisible(true);
	}
	
	private void criaPainelDeGraficos(){
		final int TAM_X = 500;
		final int TAM_Y = 130;
		
		painelDeGraficos.setPreferredSize(new Dimension(TAM_X, TAM_Y));
	}
	
	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 400;
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
	}
	
	
}
