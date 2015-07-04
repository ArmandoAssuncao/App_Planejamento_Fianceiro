package gui.painelGraficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import classes.Categoria;
import persistencia.CategoriaDAO;
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
	JComboBox<String> jComboBoxCategorias;

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
		jComboBoxCategorias = new JComboBox<String>();
	}
	
	private void criaPainelTitulo(){
		final int TAM_X = 500;
		final int TAM_Y = 130;
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setVisible(true);
	}
	
	private void criaPainelDeGraficos(){
		//final int TAM_X = 700;
		//final int TAM_Y = 130;
		
		//painelDeGraficos.setPreferredSize(new Dimension(TAM_X, TAM_Y));
	}
	
	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 500;
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		//configurações do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 0, 25, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Banlanço mensal
		String labelBalancoTotal = "Balanço Mensal";
		botaoBalancoTotal.setText(labelBalancoTotal);
		ImageIcon iconeBalancoTotal = new ImageIcon("imagens/img_botaoBalancoMensal.png");
		botaoBalancoTotal.setIcon(iconeBalancoTotal);
		botaoBalancoTotal.setHorizontalAlignment(SwingConstants.LEFT);
		botaoBalancoTotal.setIconTextGap(10);
		botaoBalancoTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoTotal.setPreferredSize(new Dimension(150,50));
		botaoBalancoTotal.addActionListener(trataEventosGraficos);
		
		//botao Balanço mensal das despesas pela forma de pagamento
		String labelBalancoDespesa = "<html>Formas de Pagamento</html>";
		botaoBalancoDespesa.setText(labelBalancoDespesa);
		ImageIcon iconeBalancoDespesa = new ImageIcon("imagens/img_botaoBalancoMensal.png");
		botaoBalancoDespesa.setIcon(iconeBalancoDespesa);
		botaoBalancoDespesa.setHorizontalAlignment(SwingConstants.LEFT);
		botaoBalancoDespesa.setIconTextGap(10);
		botaoBalancoDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoDespesa.setPreferredSize(new Dimension(150,50));
		botaoBalancoDespesa.addActionListener(trataEventosGraficos);
		
		//botao Grafico valores da receita
		String labelGraficoPizzaTotal = "Receitas";
		botaoGraficoPizzaTotal.setText(labelGraficoPizzaTotal);
		ImageIcon iconeGraficoPizzaTotal = new ImageIcon("imagens/img_botaoGraficoPizza.png");
		botaoGraficoPizzaTotal.setIcon(iconeGraficoPizzaTotal);
		botaoGraficoPizzaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoPizzaTotal.setIconTextGap(10);
		botaoGraficoPizzaTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaTotal.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaTotal.addActionListener(trataEventosGraficos);
		
		
		//Botao Grafico categorias
		String labelGraficoBarraCategoria = "Categorias";
		botaoGraficoBarraCategoria.setText(labelGraficoBarraCategoria);
		ImageIcon iconeGraficoBarraCategoria = new ImageIcon("imagens/img_botaoGraficoBarra.png");
		botaoGraficoBarraCategoria.setIcon(iconeGraficoBarraCategoria);
		botaoGraficoBarraCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoBarraCategoria.setIconTextGap(10);
		botaoGraficoBarraCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoBarraCategoria.setPreferredSize(new Dimension(150,50));
		botaoGraficoBarraCategoria.addActionListener(trataEventosGraficos);
		
		//Botao Grafico Forma de Pagamento
		String labelGraficoPizzaFormaPagamento = "<html>Formas de Pagamento</html>";
		botaoGraficoPizzaFormaPagamento.setText(labelGraficoPizzaFormaPagamento);
		ImageIcon iconeGraficoPizzaFormaPagamento = new ImageIcon("imagens/img_botaoGraficoPizza.png");
		botaoGraficoPizzaFormaPagamento.setIcon(iconeGraficoPizzaFormaPagamento);
		botaoGraficoPizzaFormaPagamento.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoPizzaFormaPagamento.setIconTextGap(10);
		botaoGraficoPizzaFormaPagamento.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaFormaPagamento.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaFormaPagamento.addActionListener(trataEventosGraficos);
		
		//Botao Grafico Metas mensal
		String labelGraficoLinhaMetaMensal = "<html>Metas Mensal</html>";
		botaoGraficoLinhaMetaMensal.setText(labelGraficoLinhaMetaMensal);
		ImageIcon iconeGraficoLinhaMetaMensal = new ImageIcon("imagens/img_botaoGraficoLinha.png");
		botaoGraficoLinhaMetaMensal.setIcon(iconeGraficoLinhaMetaMensal);
		botaoGraficoLinhaMetaMensal.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoLinhaMetaMensal.setIconTextGap(10);
		botaoGraficoLinhaMetaMensal.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoLinhaMetaMensal.setPreferredSize(new Dimension(150,50));
		botaoGraficoLinhaMetaMensal.addActionListener(trataEventosGraficos);
		
		//Painel com o botão e comboBox
		JPanel painelGraficoLinhas = new JPanel();
		painelGraficoLinhas.setBorder(new TitledBorder(""));
		painelGraficoLinhas.setPreferredSize(new Dimension(180,90));
		painelGraficoLinhas.setBackground(Color.WHITE);
		JLabel labelCategoria = new JLabel("Categoria:");
		labelCategoria.setFont(new Font(labelCategoria.getFont().getFontName(), Font.PLAIN, 10));
		
		jComboBoxCategorias.setMaximumRowCount(5);
		jComboBoxCategorias.setPreferredSize(new Dimension(99,20));
		
		List<Categoria> arrayCategorias = null;
		try {
			arrayCategorias = CategoriaDAO.todasAsCategorias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < arrayCategorias.size(); i++){
			jComboBoxCategorias.addItem(arrayCategorias.get(i).getDescricao());
		}
		
		painelGraficoLinhas.add(labelCategoria);
		painelGraficoLinhas.add(jComboBoxCategorias);
		painelGraficoLinhas.add(botaoGraficoLinhaMetaMensal);
		
		
		
		//adiciona os botoes
		painelBotoes.add(botaoBalancoTotal, constraints);
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 20, 0);
		painelBotoes.add(botaoBalancoDespesa, constraints);
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 25, 0);
		painelBotoes.add(botaoGraficoPizzaTotal, constraints);
		constraints.gridy = 3;
		painelBotoes.add(botaoGraficoBarraCategoria, constraints);
		constraints.gridy = 4;
		painelBotoes.add(botaoGraficoPizzaFormaPagamento, constraints);
		constraints.gridy = 5;
		painelBotoes.add(painelGraficoLinhas, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setVisible(true);
	}
	
	public void adicionarGrafico(String tituloGrafico, String[] campos, Double[] valores, int tipoGrafico){
		painelDeGraficos.removeAll();
		painelDeGraficos.repaint();
		
		if(tipoGrafico == 1)
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoPizza(tituloGrafico, campos, valores));
		else if(tipoGrafico == 2)
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoBarra(tituloGrafico, campos, valores));
		else if(tipoGrafico == 3)
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoLinha(tituloGrafico, campos, valores));
		
		painelDeGraficos.validate();
	}
	
	public void AtualizaComponentes(){
		jComboBoxCategorias.removeAllItems();
		
		List<Categoria> arrayCategorias = null;
		try {
			arrayCategorias = CategoriaDAO.todasAsCategorias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < arrayCategorias.size(); i++){
			jComboBoxCategorias.addItem(arrayCategorias.get(i).getDescricao());
		}
		
		painelDeGraficos.removeAll();
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

	public String getValorjComboBoxCategorias() {
		return (String)jComboBoxCategorias.getSelectedItem();
	}
	
}
