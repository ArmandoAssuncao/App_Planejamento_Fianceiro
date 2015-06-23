package gui.painelGraficos;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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
		
		// cria o conjunto de dados
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(40.5, "maximo", "dia 1");
		ds.addValue(38.2, "maximo", "dia 2");
		ds.addValue(37.3, "maximo", "dia 3");
		ds.addValue(31.5, "maximo", "dia 4");
		ds.addValue(35.7, "maximo", "dia 5");
		ds.addValue(42.5, "maximo", "dia 6");

		// cria o gráfico
		JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Dia", 
		    "Valor", ds, PlotOrientation.VERTICAL, true, true, false);
		
		grafico.setBackgroundPaint(new Color(240,240,240));
		painelDeGraficos.add(new ChartPanel(grafico));
		
		painelDeGraficos.setPreferredSize(new Dimension(TAM_X, TAM_Y));
	}
	
	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 400;
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
	}
	
	
}
