package gui.painelGraficos;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
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
		true, // Use tooltips
		false); // Configure chart to generate URLs?
		
		//Obtem a referencia do Grafico para definir a transparencia.
		PiePlot3D plot = (PiePlot3D) grafico.getPlot();
		plot.setForegroundAlpha(0.60F);
		
		grafico.setBackgroundPaint(new Color(240,240,240));
		return new ChartPanel(grafico);
	}
	
	public static JPanel painelGraficoLinha(String tituloDoGrafico, String[] campos, int[] valores){
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
	}
	
	public static JPanel painelGraficoBarra(String tituloDoGrafico, String[] campos, int[] valores){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(new Double(30.0), "SO", "Windows");
		dataset.addValue(new Double(20.0), "SO", "Linux");
		dataset.addValue(new Double(10.5), "SO", "Solaris");
		dataset.addValue(new Double(4.0), "SO", "Mac");
		
		JFreeChart grafico = ChartFactory.createBarChart(tituloDoGrafico, // chart title
		         null, // domain axis label
		         null, // range axis label
		         dataset, // data
		         PlotOrientation.VERTICAL, // orientation
		         false, // include legend
		         true, // tooltips?
		         true); // URLs?)
		
		grafico.setBackgroundPaint(new Color(240,240,240));
		
		return new ChartPanel(grafico);
	}
	

}
