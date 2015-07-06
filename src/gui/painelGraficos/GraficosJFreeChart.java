package gui.painelGraficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 * Gerencia a criação de gráficos.
 * @author Armando Assunção
 * @author Richardson William
 *
 */
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
	
	/**
	 * Configura o painel do gráfico de linha
	 * @param tituloDoGrafico <code>String</code> com o título do gráfico
	 * @param campos array de <code>String</code> com os dados do gráfico
	 * @param valores array de <code>Double</code> com os valores do gráfico
	 * @return retorna o painel criado
	 */
	public static JPanel painelGraficoLinha(String tituloDoGrafico, String[] campos, Double[] valores){
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		
		dataSet.addValue(0, "", valores[0]);
		
		XYSeries series = new XYSeries("Gastos: " + valores[0]);
		series.add(0, 0);
		series.add(0, valores[0]);
		
		XYSeriesCollection dados = new XYSeriesCollection(series);
		
		// cria o gráfico
		JFreeChart grafico = ChartFactory.createXYLineChart(tituloDoGrafico, // chart title
		         "", // domain axis label
		         "Valor", // range axis label
		         dados, // data
		         PlotOrientation.HORIZONTAL, // orientation
		         true, // include legend
		         true, // tooltips
		         false);
		
		//Linha de marcação
		Marker target = new ValueMarker(valores[2]);
		target.setPaint(Color.RED);
	    target.setLabel("100%");
        target.setLabelAnchor(RectangleAnchor.RIGHT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setLabelFont(new Font(target.getLabelFont().getName(), Font.BOLD, 11));
        
        //Linha de marcação 2
        double porcentagem = valores[2]/100*valores[1];
		Marker target2 = new ValueMarker(porcentagem);
		target2.setPaint(Color.YELLOW);
		target2.setLabel(valores[1] + "%");
		target2.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
		target2.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
		target2.setLabelFont(new Font(target2.getLabelFont().getName(), Font.BOLD, 11));
		
		
		XYPlot plot = grafico.getXYPlot();
		plot.addRangeMarker(target);
		plot.addRangeMarker(target2);
		
		plot.getDomainAxis().setAxisLineVisible(false);
		plot.getDomainAxis().setTickLabelPaint(new Color(240,240,240));
		
		//Define a cor da linha
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(20, 150, 255));
		
		
		grafico.setBackgroundPaint(new Color(240,240,240));

		ChartPanel painelGrafico = new ChartPanel(grafico);		
		return painelGrafico;
	}
	
	/**
	 * Configura o painel do gráfico de barras
	 * @param tituloDoGrafico <code>String</code> com o título do gráfico
	 * @param campos array de <code>String</code> com os dados do gráfico
	 * @param valores array de <code>Double</code> com os valores do gráfico
	 * @return retorna o painel criado
	 */
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
