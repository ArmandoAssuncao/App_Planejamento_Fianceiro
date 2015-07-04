package gui.painelGraficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficosJFreeChart {
	
	/** Retorna um painel com o grafico do tipo Pizza
	 * @param tituloDoGrafico <code>String</code> com o titulo do gráfico.
	 * @param campos <code>String[]</code> com os campos do grafico.
	 * @param valores <code>String[]</code> com os valores dos campos do grafico.
	 * @return um <code>JPanel</code> com o grafico
	 */
	public static JPanel painelGraficoPizza(String tituloDoGrafico, String[] campos, Double[] valores){
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		for(int i = 0; i < campos.length; i++){
			pieDataset.setValue(campos[i], new Double(valores[i]));
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
	
	public static JPanel painelGraficoLinha(String tituloDoGrafico, String[] campos, Double[] valores){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		for(int i = 0; i < valores.length; i++)
			dataSet.addValue(i, "maximo", valores[i]);
		
		XYSeries series = new XYSeries("Gastos");
		XYSeries series2 = new XYSeries("Meta");

		series.add(0, 0);
		series.add(50+400, valores[1]+400);
		
		series2.add(600, 300);
		series2.add(00, 300);
		
		XYSeriesCollection dados = new XYSeriesCollection(series);
		dados.addSeries(series2);
		
		// cria o gráfico
		JFreeChart grafico = ChartFactory.createXYLineChart(tituloDoGrafico, // chart title
		         "hjk", // domain axis label
		         "Meta", // range axis label
		         dados, // data
		         PlotOrientation.HORIZONTAL, // orientation
		         true, // include legend
		         true, // tooltips
		         false);
		
		grafico.setBackgroundPaint(new Color(240,240,240));
		
		ChartPanel painelGrafico = new ChartPanel(grafico);
		
		return painelGrafico;
	}
	
	public static JPanel painelGraficoBarra(String tituloDoGrafico, String[] campos, Double[] valores){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int i = 0; i < campos.length; i++){
			dataset.addValue(valores[i], campos[i], "Categoria");
		}
		
		JFreeChart grafico = ChartFactory.createBarChart3D(tituloDoGrafico, // chart title
		         "", // domain axis label
		         "Meta", // range axis label
		         dataset, // data
		         PlotOrientation.VERTICAL, // orientation
		         true, // include legend
		         true, // tooltips
		         true); // URLs
		
		
		//grafico.setBorderVisible(true);
		grafico.setBackgroundPaint(new Color(240,240,240));
		
        CategoryPlot plot = (CategoryPlot) grafico.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.WHITE);
       
        ChartPanel painelGrafico = new ChartPanel(grafico);
        painelGrafico.setPreferredSize(new Dimension(730,450));
		
		return painelGrafico;
	}
	

}
