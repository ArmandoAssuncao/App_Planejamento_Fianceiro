package gui.painelGraficos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import eventos.painelGraficos.TEPainelGraficos;

public class PainelGraficos extends JPanel {
	private JPanel painelTitulo;
	private JPanel painelDeGraficos;
	private JPanel painelBotoes;
	
	TEPainelGraficos trataEventosGraficos;
	
	private JButton botaoBalancoTotal;
	private JButton botaoBalancoDespesa;
	private JButton botaoGraficoPizzaTotal;
	private JButton botaoGraficoBarraCategoria;
	private JButton botaoGraficoLinhaMetaMensal;
	private JButton botaoGraficoPizzaFormaPagamento;

	public PainelGraficos(Window framePrincipal) {
		setLayout(new BorderLayout(0, 3));
		
		trataEventosGraficos = new TEPainelGraficos(this, framePrincipal);
		
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
		botaoBalancoTotal = new JButton();
		botaoBalancoDespesa = new JButton();
		botaoGraficoPizzaTotal = new JButton();
		botaoGraficoBarraCategoria = new JButton();
		botaoGraficoLinhaMetaMensal = new JButton();
		botaoGraficoPizzaFormaPagamento = new JButton();
		
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
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		//configurações do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0, 0, 25, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Nova Categoria
		String labelBalancoTotal = "Balanço Mensal";
		botaoBalancoTotal.setText(labelBalancoTotal);
		//ImageIcon iconeBalancoTotal = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		//botaoBalancoTotal.setIcon(iconeBalancoTotal);
		//botaoBalancoTotal.setMargin(new Insets(0,-20,0,0));
		//botaoBalancoTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoTotal.setPreferredSize(new Dimension(150,50));
		botaoBalancoTotal.addActionListener(trataEventosGraficos);
		
		//botao Excluir Categoria
		String labelBalancoDespesa = "<html>Balanço Mensal <br>Forma de Pagamento</html>";
		botaoBalancoDespesa.setText(labelBalancoDespesa);
		//ImageIcon iconeBalancoDespesa = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		//botaoBalancoDespesa.setIcon(iconeBalancoDespesa);
		//botaoBalancoDespesa.setMargin(new Insets(0,-10,0,0));
		//botaoBalancoDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoDespesa.setPreferredSize(new Dimension(150,50));
		botaoBalancoDespesa.addActionListener(trataEventosGraficos);
		
		//botao Editar Categoria
		String labelGraficoPizzaTotal = "<html>Grafico<br>Valores da Receita</html>";
		botaoGraficoPizzaTotal.setText(labelGraficoPizzaTotal);
		//ImageIcon iconeGraficoPizzaTotal = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		//botaoGraficoPizzaTotal.setIcon(iconeGraficoPizzaTotal);
		//botaoGraficoPizzaTotal.setMargin(new Insets(0,-20,0,0));
		//botaoGraficoPizzaTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaTotal.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaTotal.addActionListener(trataEventosGraficos);
		
		
		//Botao Nova Despesa
		String labelGraficoBarraCategoria = "<html>Grafico<br>Categorias";
		botaoGraficoBarraCategoria.setText(labelGraficoBarraCategoria);
//		ImageIcon iconeGraficoBarraCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
//		botaoGraficoBarraCategoria.setIcon(iconeGraficoBarraCategoria);
//		botaoGraficoBarraCategoria.setMargin(new Insets(0,0,0,0));
//		botaoGraficoBarraCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoBarraCategoria.setPreferredSize(new Dimension(150,50));
		botaoGraficoBarraCategoria.addActionListener(trataEventosGraficos);
		
		//Botao Excluir Despesa
		String labelGraficoLinhaMetaMensal = "<html>Grafico<br>Metas Mensal";
		botaoGraficoLinhaMetaMensal.setText(labelGraficoLinhaMetaMensal);
//		ImageIcon iconeGraficoLinhaMetaMensal = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
//		botaoGraficoLinhaMetaMensal.setIcon(iconeGraficoLinhaMetaMensal);
//		botaoGraficoLinhaMetaMensal.setMargin(new Insets(0,0,0,0));
//		botaoGraficoLinhaMetaMensal.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoLinhaMetaMensal.setPreferredSize(new Dimension(150,50));
		botaoGraficoLinhaMetaMensal.addActionListener(trataEventosGraficos);
		
		//Botao Editar Despesa
		String labelGraficoPizzaFormaPagamento = "<html>Grafico<br>Formas de Pagamento";
		botaoGraficoPizzaFormaPagamento.setText(labelGraficoPizzaFormaPagamento);
//		ImageIcon iconeGraficoPizzaFormaPagamento = new ImageIcon("imagens/img_botaoEditarCategoria.png");
//		botaoGraficoPizzaFormaPagamento.setIcon(iconeGraficoPizzaFormaPagamento);
//		botaoGraficoPizzaFormaPagamento.setMargin(new Insets(0,0,0,0));
//		botaoGraficoPizzaFormaPagamento.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaFormaPagamento.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaFormaPagamento.addActionListener(trataEventosGraficos);
		
		//adiciona os botoes
		painelBotoes.add(botaoBalancoTotal, constraints);
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 50, 0);
		painelBotoes.add(botaoBalancoDespesa, constraints);
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 25, 0);
		painelBotoes.add(botaoGraficoPizzaTotal, constraints);
		constraints.gridy = 3;
		painelBotoes.add(botaoGraficoBarraCategoria, constraints);
		constraints.gridy = 4;
		painelBotoes.add(botaoGraficoLinhaMetaMensal, constraints);
		constraints.gridy = 5;
		painelBotoes.add(botaoGraficoPizzaFormaPagamento, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setVisible(true);
	}

	public JButton getBotaoBalancoTotal() {
		return botaoBalancoTotal;
	}

	public JButton getBotaoBalancoDespesa() {
		return botaoBalancoDespesa;
	}

	public JButton getBotaoGraficoPizzaTotal() {
		return botaoGraficoPizzaTotal;
	}

	public JButton getBotaoGraficoBarraCategoria() {
		return botaoGraficoBarraCategoria;
	}

	public JButton getBotaoGraficoLinhaMetaMensal() {
		return botaoGraficoLinhaMetaMensal;
	}

	public JButton getBotaoGraficoPizzaFormaPagamento() {
		return botaoGraficoPizzaFormaPagamento;
	}
	
}
