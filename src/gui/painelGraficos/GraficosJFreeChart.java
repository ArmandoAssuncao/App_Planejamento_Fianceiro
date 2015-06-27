package gui.painelGraficos;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraficosJFreeChart {
	
	/** Retorna um painel com o grafico do tipo Pizza
	 * @param tituloDoGrafico <code>String</code> com o titulo do gráfico.
	 * @param campos <code>String[]</code> com os campos do grafico.
	 * @param valores <code>String[]</code> com os valores dos campos do grafico.
	 * @return um <code>JPanel</code> com o grafico
	 */
	public static JPanel painelGraficoPizza(String tituloDoGrafico, String[] campos, int[] valores){
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		for(int i = 0; i < campos.length; i++){
			pieDataset.setValue(campos[i], new Integer(valores[i]));
		}
		 
		JFreeChart grafico = ChartFactory.createPieChart3D(
		tituloDoGrafico, // Title
		pieDataset, // Dataset
		true, // Show legend
		false, // Use tooltips
		false); // Configure chart to generate URLs?
		
		grafico.setBackgroundPaint(new Color(240,240,240));
		return new ChartPanel(grafico);
	}
	
	
	
	/*private JPanel criaPainelDeGraficos(){
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
		
		return new ChartPanel(grafico);
	}*/
}
