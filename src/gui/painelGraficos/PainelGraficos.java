
package gui.painelGraficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import persistencia.CategoriaDAO;
import classes.Categoria;
import eventos.painelGraficos.TEPainelGraficos;

/**
Define o painel de relatórios e gráficos.
* @author Armando Assunção
* @author Richardson William
*
*/
//TODO revisar comentario acima
public class PainelGraficos extends JPanel {
	private PainelTituloPainelGraficos painelTitulo;
	private JPanel painelDeGraficos;
	private JPanel painelBotoes;
	private JPanel painelSuperior;
	
	private TEPainelGraficos trataEventosGraficos;
	
	private JButton botaoBalancoTotal;
	private JButton botaoBalancoDespesa;
	private JButton botaoGraficoPizzaTotal;
	private JButton botaoGraficoBarraCategoria;
	private JButton botaoGraficoLinhaMetaMensal;
	private JButton botaoGraficoPizzaFormaPagamento;
	private JComboBox<String> jComboBoxCategorias;
	private JRadioButton radioButtonVerEmReal;
	private JRadioButton radioButtonVerEmPorcentagem;

	/**
	 * Construtor padrão.
	 * @param framePrincipal componente pai.
	 */
	public PainelGraficos(Window framePrincipal) {
		setLayout(new BorderLayout(0, 3));
				
		trataEventosGraficos = new TEPainelGraficos(this, framePrincipal);
		
		iniciaElementos();
		criaPainelBotoes();
		
		painelSuperior.setLayout(new BorderLayout());
		painelSuperior.setBorder(new LineBorder(Color.BLUE)); //TODO debug
		painelTitulo.setBorder(new LineBorder(Color.RED));   //TODO debug
		painelSuperior.add(painelTitulo, BorderLayout.WEST);
		
		add(painelBotoes, BorderLayout.EAST);
		add(painelDeGraficos, BorderLayout.CENTER);
		add(painelSuperior, BorderLayout.NORTH);

		painelDeGraficos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	private void iniciaElementos(){
		painelTitulo = new PainelTituloPainelGraficos();
		painelSuperior = new JPanel();
		painelBotoes = new JPanel();
		painelDeGraficos = new JPanel();
		botaoBalancoTotal = new JButton();
		botaoBalancoDespesa = new JButton();
		botaoGraficoPizzaTotal = new JButton();
		botaoGraficoBarraCategoria = new JButton();
		botaoGraficoLinhaMetaMensal = new JButton();
		botaoGraficoPizzaFormaPagamento = new JButton();
		jComboBoxCategorias = new JComboBox<String>();
		radioButtonVerEmReal = new JRadioButton();
		radioButtonVerEmPorcentagem = new JRadioButton();
	}
	
	private void criaPainelBotoes(){
		
		final int TAM_X = 200;
		final int TAM_Y = 500;
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		Font fonteBold = new Font(new JLabel().getFont().getName(), Font.BOLD, 11);
		Insets insetsBotao = new Insets(0,10,0,0);
		
		//configurações do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		
		//botao Banlanço mensal
		String labelBalancoTotal = "Balanço Mensal";
		botaoBalancoTotal.setText(labelBalancoTotal);
		botaoBalancoTotal.setMnemonic(KeyEvent.VK_B);
		ImageIcon iconeBalancoTotal = new ImageIcon("imagens/img_botaoBalancoMensal.png");
		botaoBalancoTotal.setIcon(iconeBalancoTotal);
		botaoBalancoTotal.setFont(fonteBold);
		botaoBalancoTotal.setMargin(insetsBotao);
		botaoBalancoTotal.setHorizontalAlignment(SwingConstants.LEFT);
		botaoBalancoTotal.setIconTextGap(5);
		botaoBalancoTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoTotal.setPreferredSize(new Dimension(150,50));
		botaoBalancoTotal.addActionListener(trataEventosGraficos);
		
		
		//botao Balanço mensal das despesas pela forma de pagamento
		String labelBalancoDespesa = "<html>Formas de Pagamento</html>";
		botaoBalancoDespesa.setText(labelBalancoDespesa);
		botaoBalancoDespesa.setMnemonic(KeyEvent.VK_F);
		ImageIcon iconeBalancoDespesa = new ImageIcon("imagens/img_botaoBalancoMensal.png");
		botaoBalancoDespesa.setIcon(iconeBalancoDespesa);
		botaoBalancoDespesa.setFont(fonteBold);
		botaoBalancoDespesa.setMargin(insetsBotao);
		botaoBalancoDespesa.setHorizontalAlignment(SwingConstants.LEFT);
		botaoBalancoDespesa.setIconTextGap(10);
		botaoBalancoDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoBalancoDespesa.setPreferredSize(new Dimension(150,50));
		botaoBalancoDespesa.addActionListener(trataEventosGraficos);
		
		//botao Grafico valores da receita
		String labelGraficoPizzaTotal = "Receitas";
		botaoGraficoPizzaTotal.setText(labelGraficoPizzaTotal);
		botaoGraficoPizzaTotal.setMnemonic(KeyEvent.VK_E);
		ImageIcon iconeGraficoPizzaTotal = new ImageIcon("imagens/img_botaoGraficoPizza.png");
		botaoGraficoPizzaTotal.setIcon(iconeGraficoPizzaTotal);
		botaoGraficoPizzaTotal.setFont(fonteBold);
		botaoGraficoPizzaTotal.setMargin(insetsBotao);
		botaoGraficoPizzaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoPizzaTotal.setIconTextGap(10);
		botaoGraficoPizzaTotal.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaTotal.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaTotal.addActionListener(trataEventosGraficos);
		
		//Botao Grafico Forma de Pagamento
		String labelGraficoPizzaFormaPagamento = "<html>Formas de Pagamento</html>";
		botaoGraficoPizzaFormaPagamento.setText(labelGraficoPizzaFormaPagamento);
		botaoGraficoPizzaFormaPagamento.setMnemonic(KeyEvent.VK_M);
		ImageIcon iconeGraficoPizzaFormaPagamento = new ImageIcon("imagens/img_botaoGraficoPizza.png");
		botaoGraficoPizzaFormaPagamento.setIcon(iconeGraficoPizzaFormaPagamento);
		botaoGraficoPizzaFormaPagamento.setFont(fonteBold);
		botaoGraficoPizzaFormaPagamento.setMargin(insetsBotao);
		botaoGraficoPizzaFormaPagamento.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoPizzaFormaPagamento.setIconTextGap(10);
		botaoGraficoPizzaFormaPagamento.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoPizzaFormaPagamento.setPreferredSize(new Dimension(150,50));
		botaoGraficoPizzaFormaPagamento.addActionListener(trataEventosGraficos);
		
		//Botao Grafico categorias
		String labelGraficoBarraCategoria = "Categorias";
		botaoGraficoBarraCategoria.setText(labelGraficoBarraCategoria);
		botaoGraficoBarraCategoria.setMnemonic(KeyEvent.VK_T);
		ImageIcon iconeGraficoBarraCategoria = new ImageIcon("imagens/img_botaoGraficoBarra.png");
		botaoGraficoBarraCategoria.setIcon(iconeGraficoBarraCategoria);
		botaoGraficoBarraCategoria.setFont(fonteBold);
		botaoGraficoBarraCategoria.setMargin(insetsBotao);
		botaoGraficoBarraCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		botaoGraficoBarraCategoria.setIconTextGap(10);
		botaoGraficoBarraCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoGraficoBarraCategoria.setPreferredSize(new Dimension(150,50));
		botaoGraficoBarraCategoria.addActionListener(trataEventosGraficos);
		
		radioButtonVerEmReal.setText("Real");
		radioButtonVerEmReal.setMnemonic(KeyEvent.VK_L);
		radioButtonVerEmReal.setSelected(true);
		radioButtonVerEmReal.setBackground(Color.WHITE);
		
		radioButtonVerEmPorcentagem.setText("Porcentagem");
		radioButtonVerEmPorcentagem.setMnemonic(KeyEvent.VK_P);
		radioButtonVerEmPorcentagem.setBackground(Color.WHITE);
		
		ButtonGroup buttonGroupVer = new ButtonGroup();
		buttonGroupVer.add(radioButtonVerEmReal);
		buttonGroupVer.add(radioButtonVerEmPorcentagem);
		
		//Painel com o botão e comboBox
		JPanel painelBotaoGraficoCategoria = new JPanel();
		painelBotaoGraficoCategoria.setBorder(new TitledBorder(""));
		painelBotaoGraficoCategoria.setPreferredSize(new Dimension(180,90));
		painelBotaoGraficoCategoria.setBackground(Color.WHITE);
		
		painelBotaoGraficoCategoria.add(radioButtonVerEmReal);
		painelBotaoGraficoCategoria.add(radioButtonVerEmPorcentagem);
		painelBotaoGraficoCategoria.add(botaoGraficoBarraCategoria);
		
		//Botao Grafico Metas mensal
		String labelGraficoLinhaMetaMensal = "<html>Metas Mensal</html>";
		botaoGraficoLinhaMetaMensal.setText(labelGraficoLinhaMetaMensal);
		botaoGraficoLinhaMetaMensal.setMnemonic(KeyEvent.VK_N);
		ImageIcon iconeGraficoLinhaMetaMensal = new ImageIcon("imagens/img_botaoGraficoLinha.png");
		botaoGraficoLinhaMetaMensal.setIcon(iconeGraficoLinhaMetaMensal);
		botaoGraficoLinhaMetaMensal.setFont(fonteBold);
		botaoGraficoLinhaMetaMensal.setMargin(insetsBotao);
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
		labelCategoria.setDisplayedMnemonic(KeyEvent.VK_G);
		labelCategoria.setLabelFor(jComboBoxCategorias);
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
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(5, 0, 18, 0);
		painelBotoes.add(botaoBalancoTotal, constraints);
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 18, 0);
		painelBotoes.add(botaoBalancoDespesa, constraints);
		constraints.gridy = 2;
		painelBotoes.add(botaoGraficoPizzaTotal, constraints);
		constraints.gridy = 3;
		painelBotoes.add(botaoGraficoPizzaFormaPagamento, constraints);
		constraints.gridy = 4;
		painelBotoes.add(painelBotaoGraficoCategoria, constraints);
		constraints.gridy = 5;
		painelBotoes.add(painelGraficoLinhas, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setVisible(true);
	}
	
	/**
	 * 	Adiciona um gráfico no painel  
	 * @param tituloGrafico <code>String</code> com o título do gráfico.
	 * @param campos vetor de <code>String</code> com os campos que serão adicionados ao gráfico
	 * @param valores vetor de <code>Double</code> com os dados que serão adicionados ao gráfico 
	 * @param tipoGrafico tipo de grafico a ser desenhado.
	 */
	public void adicionarGrafico(String tituloGrafico, String[] campos, Double[] valores, int tipoGrafico){
		painelDeGraficos.removeAll();
		painelDeGraficos.repaint();
		
		if(tipoGrafico == 1){	
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoPizza(tituloGrafico, campos, valores));

			painelTitulo.atualizarPainelTituloGp(campos, valores);
		}else if(tipoGrafico == 2){
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoBarra(tituloGrafico, campos, valores));
			
			painelTitulo.atualizarPainelTituloGc();
		}else if(tipoGrafico == 3){
			painelDeGraficos.add(GraficosJFreeChart.painelGraficoLinha(tituloGrafico, campos, valores));
			
			painelTitulo.atualizarPainelTituloGl(getValorjComboBoxCategorias());
		}
		painelDeGraficos.validate();
	}
	
	/**
	 * Atualiza os componentes gráficos.
	 */
	public void atualizaComponentes(){
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
		
	//Getters
	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão balanço total.
	 */
	public JButton getBotaoBalancoTotal() {
		return botaoBalancoTotal;
	}

	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão balanço despesa.
	 */
	public JButton getBotaoBalancoDespesa() {
		return botaoBalancoDespesa;
	}

	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão gráfico pizza.
	 */
	public JButton getBotaoGraficoPizzaTotal() {
		return botaoGraficoPizzaTotal;
	}

	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão gráfico de barra de <code>Categoria</code>.
	 */
	public JButton getBotaoGraficoBarraCategoria() {
		return botaoGraficoBarraCategoria;
	}

	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão gráfico de linha de <code>MetaMensal</code>.
	 */
	public JButton getBotaoGraficoLinhaMetaMensal() {
		return botaoGraficoLinhaMetaMensal;
	}

	/**
	 * Obtém a referência de um <code>JButton</code>
	 * @return botão gráfico de pizza de <code>FormaPagamento</code>.
	 */
	public JButton getBotaoGraficoPizzaFormaPagamento() {
		return botaoGraficoPizzaFormaPagamento;
	}

	/**
	 * Obtém a referência de um <code>String</code> contida no <code>JComboBox</code> Categorias.
	 * @return <code>String</code> correspondente no <code>JComboBox</code> Categorias.
	 */
	public String getValorjComboBoxCategorias() {
		return (String)jComboBoxCategorias.getSelectedItem();
	}

	/**
	 * Obtém a referência do botão de radio verEmPorcentagem.
	 * @return botão de radio
	 */
	public JRadioButton getRadioButtonVerEmPorcentagem() {
		return radioButtonVerEmPorcentagem;
	}

}
