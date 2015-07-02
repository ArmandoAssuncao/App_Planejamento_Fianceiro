package gui.painelDespesas;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import persistencia.CategoriaDAO;
import persistencia.DespesaDAO;
import persistencia.FormaPagamentoDAO;
import persistencia.MetaMensalDAO;
import classes.Categoria;
import classes.Despesa;
import classes.MetaMensal;
import eventos.painelDespesa.TEPainelDespesas;
import funcoes.Converte;
import gui.JanelaMensagem;

public class IgPainelDespesas extends JPanel{
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;
	
	boolean avisoMetaTotal = false;
	boolean avisoMetaDefinido = false;
	
	List<Categoria> arrayCategoria = new ArrayList<Categoria>();
	AbasCategoria abasCategoria;
	JPanel painelBotoes;
	JPanel painelTitulo;
	TEPainelDespesas trataEventosDespesas;
	
	JButton botaoNovaCategoria;
	JButton botaoExcluirCategoria;
	JButton botaoEditarCategoria;
	JButton botaoAdicionarDespesa;
	JButton botaoExcluirDespesa;
	JButton botaoEditarDespesa;
	
	//Labels do painelTitulo
	JLabel labelNomeCategoriaValor;
	JLabel labelMetaCategoriaValor;
	JLabel labelNumeroDeDespesasValor;
	JLabel labelValorTotalDespesasValor;
	JLabel labelValorTotalDespesasPorcentagemValor;
	
	public IgPainelDespesas(Window framePrincipal) {
		setLayout(new BorderLayout(0,3));
		
		trataEventosDespesas = new TEPainelDespesas(this, framePrincipal);
		iniciaElementos();
		
		iniciaValoresCategoria();
		
		criaPainelBotoes();
		criaPainelTitulo();
		
		add(painelTitulo, BorderLayout.NORTH);
		add(abasCategoria, BorderLayout.WEST);
		add(painelBotoes, BorderLayout.EAST);
		
		
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		//setBackground(Color.WHITE); TODO
		
		botaoNovaCategoria.setVisible(true);
		botaoEditarCategoria.setVisible(true);
		botaoExcluirCategoria.setVisible(false);
		botaoAdicionarDespesa.setVisible(true);
		botaoExcluirDespesa.setVisible(false);
		botaoEditarDespesa.setVisible(false);
		
		setVisible(true);
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
		String labelBotaoNovaCategoria = "Nova Categoria";
		ImageIcon iconeNovaCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoNovaCategoria.setText(labelBotaoNovaCategoria);
		botaoNovaCategoria.setIcon(iconeNovaCategoria);
		botaoNovaCategoria.setMargin(new Insets(0,-20,0,0));
		botaoNovaCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoNovaCategoria.setPreferredSize(new Dimension(150,50));
		botaoNovaCategoria.setMnemonic(KeyEvent.VK_V);
		botaoNovaCategoria.addActionListener(trataEventosDespesas);
		
		//botao Excluir Categoria
		String labelBotaoExcluirCategoria = "Excluir Categoria";
		ImageIcon iconeExcluirCategoria = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirCategoria.setText(labelBotaoExcluirCategoria);
		botaoExcluirCategoria.setIcon(iconeExcluirCategoria);
		botaoExcluirCategoria.setMargin(new Insets(0,-10,0,0));
		botaoExcluirCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirCategoria.setPreferredSize(new Dimension(150,50));
		botaoExcluirCategoria.addActionListener(trataEventosDespesas);
		
		//botao Editar Categoria
		String labelBotaoEditarCategoria = "Editar Categoria";
		ImageIcon iconeEditarCategoria = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		botaoEditarCategoria.setText(labelBotaoEditarCategoria);
		botaoEditarCategoria.setIcon(iconeEditarCategoria);
		botaoEditarCategoria.setMargin(new Insets(0,-20,0,0));
		botaoEditarCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarCategoria.setPreferredSize(new Dimension(150,50));
		botaoEditarCategoria.setMnemonic(KeyEvent.VK_E);
		botaoEditarCategoria.addActionListener(trataEventosDespesas);
		
		
		//Botao Nova Despesa
		String labelBotaoAdicionarDespesa = "Adicionar Despesa";
		ImageIcon iconeAdicionarDespesa = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoAdicionarDespesa.setText(labelBotaoAdicionarDespesa);
		botaoAdicionarDespesa.setIcon(iconeAdicionarDespesa);
		botaoAdicionarDespesa.setMargin(new Insets(0,0,0,0));
		botaoAdicionarDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoAdicionarDespesa.setPreferredSize(new Dimension(150,50));
		botaoAdicionarDespesa.setMnemonic(KeyEvent.VK_N);
		botaoAdicionarDespesa.addActionListener(trataEventosDespesas);
		
		//Botao Excluir Despesa
		String labelBotaoExcluirDespesa = "Excluir Despesa";
		ImageIcon iconeExcluirDespesa = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirDespesa.setText(labelBotaoExcluirDespesa);
		botaoExcluirDespesa.setIcon(iconeExcluirDespesa);
		botaoExcluirDespesa.setMargin(new Insets(0,0,0,0));
		botaoExcluirDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirDespesa.setPreferredSize(new Dimension(150,50));
		botaoExcluirDespesa.addActionListener(trataEventosDespesas);
		
		//Botao Editar Despesa
		String labelBotaoEditarDespesa = "Editar Despesa";
		ImageIcon iconeEditarDespesa = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		botaoEditarDespesa.setText(labelBotaoEditarDespesa);
		botaoEditarDespesa.setIcon(iconeEditarDespesa);
		botaoEditarDespesa.setMargin(new Insets(0,0,0,0));
		botaoEditarDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarDespesa.setPreferredSize(new Dimension(150,50));
		botaoEditarDespesa.addActionListener(trataEventosDespesas);
		
		//adiciona os botoes
		painelBotoes.add(botaoNovaCategoria, constraints);
		constraints.gridy = 1;
		painelBotoes.add(botaoExcluirCategoria, constraints);
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 50, 0);
		painelBotoes.add(botaoEditarCategoria, constraints);
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 0, 25, 0);
		painelBotoes.add(botaoAdicionarDespesa, constraints);
		constraints.gridy = 4;
		painelBotoes.add(botaoExcluirDespesa, constraints);
		constraints.gridy = 5;
		painelBotoes.add(botaoEditarDespesa, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setVisible(true);
	}
	
	private void criaPainelTitulo(){
		final int TAM_X = 500;
		final int TAM_Y = 130;
		
		GridBagLayout gridBag = new GridBagLayout();
		gridBag.columnWidths = new int[]{0, 0, 0, 0, 0}; //A cada coluna adicionada, aumentar um campo com {0}.
		gridBag.rowHeights = new int[]{0, 0, 0, 0}; //A cada linha adicionada, aumentar um campo com {0}.
		gridBag.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE}; //A cada coluna adicionada, aumentar um campo com {0.0}.
		gridBag.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE}; //A cada linha adicionada, aumentar um campo com {0.0}.
		
		painelTitulo.setLayout(gridBag);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0, 0, 0, 0);
		
		JLabel labelNomeCategoria = new JLabel();
		JLabel labelMetaCategoria = new JLabel();
		JLabel labelNumeroDeDespesas = new JLabel();
		JLabel labelValorTotalDespesas = new JLabel();
		JLabel labelValorTotalDespesasPorcentagem = new JLabel();
		
		labelNomeCategoria.setText("Nome da Categoria:");
		labelMetaCategoria.setText("Meta da Categoria:");
		labelNumeroDeDespesas.setText("Número de despesas:");
		labelValorTotalDespesas.setText("Total das despesas:");
		labelValorTotalDespesasPorcentagem.setText("Total das despesas %:");
		
		atualizaPainelTitulo();
		
		//Atualiza os labels do painel Titulo sempre quando muda de categoria(aba).
		abasCategoria.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(abasCategoria.getNumeroDeAbas() != 0 && arrayCategoria.size() != 0)
					atualizaPainelTitulo();
			}
		});
		
		//Define a fonte
		String fonteDefault = new JLabel().getFont().getFontName(); //Pega a fonte default do sistema
		Font fonte = new Font(fonteDefault, Font.PLAIN, 14);
		labelNomeCategoria.setFont(fonte);
		labelNomeCategoriaValor.setFont(fonte);
		labelMetaCategoria.setFont(fonte);
		labelMetaCategoriaValor.setFont(fonte);
		labelNumeroDeDespesas.setFont(fonte);
		labelNumeroDeDespesasValor.setFont(fonte);
		labelValorTotalDespesas.setFont(fonte);
		labelValorTotalDespesasValor.setFont(fonte);
		labelValorTotalDespesasPorcentagem.setFont(fonte);
		labelValorTotalDespesasPorcentagemValor.setFont(fonte);
		
		//linha 0
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets.set(10, 5, 0, 0);
		painelTitulo.add(labelNomeCategoria, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 3;
		painelTitulo.add(labelNomeCategoriaValor, constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.insets.set(10, 250, 0, 0);
		painelTitulo.add(labelMetaCategoria, constraints);
		constraints.gridx = 3;
		constraints.insets.set(10, 5, 0, 0);
		painelTitulo.add(labelMetaCategoriaValor, constraints);
		
		//linha 1
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets.set(10, 5, 0, 0);
		painelTitulo.add(labelNumeroDeDespesas, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 3;
		painelTitulo.add(labelNumeroDeDespesasValor, constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.insets.set(10, 250, 0, 0);
		painelTitulo.add(labelValorTotalDespesas, constraints);
		constraints.gridx = 3;
		constraints.insets.set(10, 5, 0, 0);
		painelTitulo.add(labelValorTotalDespesasValor, constraints);
		
		//linha 2
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.insets.set(10, 250, 0, 0);
		painelTitulo.add(labelValorTotalDespesasPorcentagem, constraints);
		constraints.gridx = 3;
		constraints.insets.set(10, 5, 0, 0);
		painelTitulo.add(labelValorTotalDespesasPorcentagemValor, constraints);
		
		Border borda = BorderFactory.createLineBorder(Color.BLACK, 2);
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setBackground(Color.LIGHT_GRAY);
		painelTitulo.setBorder(borda);
		painelTitulo.setVisible(true);
	}//painelTitulo()
	

	private void iniciaElementos(){
		abasCategoria = new AbasCategoria();
		painelBotoes = new JPanel();
		painelTitulo = new JPanel();
		botaoNovaCategoria = new JButton();
		botaoExcluirCategoria = new JButton();
		botaoEditarCategoria = new JButton();
		botaoAdicionarDespesa = new JButton();
		botaoExcluirDespesa = new JButton();
		botaoEditarDespesa = new JButton();
		labelNomeCategoriaValor = new JLabel();
		labelMetaCategoriaValor = new JLabel();
		labelNumeroDeDespesasValor = new JLabel();
		labelValorTotalDespesasValor = new JLabel();
		labelValorTotalDespesasPorcentagemValor = new JLabel();
	}
	
	private void atualizaPainelTitulo(){
		if(abasCategoria.getNumeroDeAbas() == 0){
			labelNomeCategoriaValor.setText("");
			labelMetaCategoriaValor.setText("");
			labelNumeroDeDespesasValor.setText("");
			labelValorTotalDespesasValor.setText("");
			labelValorTotalDespesasPorcentagemValor.setText("");
			return;
		}
		
		String descricao = arrayCategoria.get(abasCategoria.getSelectedIndex()).getDescricao();
		double valorMeta = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorMeta();
		double valorAlerta = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorAlerta();
		double totalDespesasPorcentagem = (abasCategoria.getValorTotalDespesas() * 100);
		if(valorMeta != 0){
			totalDespesasPorcentagem = (abasCategoria.getValorTotalDespesas() * 100)/valorMeta;
		}
		
		avisoMetaDefinido = false;
		avisoMetaTotal = false;
		if(totalDespesasPorcentagem >= 100){
			labelValorTotalDespesasPorcentagemValor.setForeground(Color.RED);
			avisoMetaDefinido = true;
			avisoMetaTotal = true;
		}
		else if(totalDespesasPorcentagem > valorAlerta){
			labelValorTotalDespesasPorcentagemValor.setForeground(Color.YELLOW);
			avisoMetaDefinido = true;
		}
		else{
			labelValorTotalDespesasPorcentagemValor.setForeground(Color.BLACK);
		}
		
		labelNomeCategoriaValor.setText(descricao);
		labelMetaCategoriaValor.setText("$" + valorMeta);
		labelNumeroDeDespesasValor.setText("" + abasCategoria.getNumeroDeDespesasDaCategoria());
		labelValorTotalDespesasValor.setText("$" + abasCategoria.getValorTotalDespesas());
		labelValorTotalDespesasPorcentagemValor.setText( String.format("%.1f%%", totalDespesasPorcentagem));
	}
	
	private void iniciaValoresCategoria(){
		List<MetaMensal> arrayMetaMensalTemp = new ArrayList<MetaMensal>();
		List<Categoria> arrayCategoriaTemp = new ArrayList<Categoria>();
		
		try {
			arrayCategoriaTemp = CategoriaDAO.todasAsCategorias();
			arrayMetaMensalTemp = MetaMensalDAO.todasAsMetasMensais();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Inicializa as abas das categoria
		for(int indice = 0; indice < arrayMetaMensalTemp.size(); indice++){
			arrayCategoriaTemp.get(indice).setMetaMensal(arrayMetaMensalTemp.get(indice));
			criarCategoria(arrayCategoriaTemp.get(indice));
		}
		
		String descricaoCategoria;
		for(int indice = 0; indice < arrayMetaMensalTemp.size(); indice++){
			descricaoCategoria = arrayCategoriaTemp.get(indice).getDescricao();
			iniciaValoresDespesa(descricaoCategoria);
		}
		
		if(abasCategoria.getNumeroDeAbas() > 0)
			abasCategoria.setSelectedIndex(0);
		
		//Faz a aba selecionada aparecer na tela
		abasCategoria.updateUI();
	
	}//iniciaValoresCategoria()
	
	private void iniciaValoresDespesa(String descricaoCategoria){
		List<Despesa> arrayDespesasTemp = new ArrayList<Despesa>();
		
		try {
			arrayDespesasTemp = DespesaDAO.todasAsDespesas();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Inicializa as despesas da categoria
		try {
			for(Despesa d : arrayDespesasTemp){
				int idCategoria = new CategoriaDAO().getId(descricaoCategoria);
				String descricao = d.getDescricao();
				String dataDespesa = Converte.calendarToString(d.getDataDespesa());
				String dataPagamento = Converte.calendarToString(d.getDataPagamento());
				String numeroCheque = d.getNumeroCheque();
				String valorDespesa = Double.toString(d.getValorDespesa());
				String numeroParcelas = Integer.toString(d.getNumeroParcelas());
				String formaPagamento = new FormaPagamentoDAO().getDescricao(d.getIdFormaPagamento());
				
				if(d.getIdCategoria() == idCategoria)
					abasCategoria.criarDespesa(descricaoCategoria, descricao, valorDespesa, dataDespesa, dataPagamento, formaPagamento, numeroParcelas, numeroCheque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//cria uma nova categoria
	public boolean criarCategoria(Categoria categoria){
		if( abasCategoria.criarCategoria(categoria.getDescricao())){
			arrayCategoria.add(categoria);
			abasCategoria.setSelectedIndex(abasCategoria.getNumeroDeAbas()-1);
			
			atualizaPainelTitulo();
			return true;
		}
		else
			return false;
	}

	public boolean editarCategoria(Categoria categoria){
		if( abasCategoria.editarCategoria(categoria.getDescricao())){
			arrayCategoria.set(abasCategoria.getSelectedIndex(), categoria);
			
			atualizaPainelTitulo();
			return true;
		}
		else
			return false;
	}
	
	public boolean removerCategoria(){
		int abaSelecionada = abasCategoria.getSelectedIndex();
		if( abasCategoria.removerCategoria(getDescricaoCategoria())){
			arrayCategoria.remove(abaSelecionada);
			
			atualizaPainelTitulo();
			return true;
		}
		
		return false;
	}
	
	public boolean criarDespesa(Despesa despesa){
		String descricao = despesa.getDescricao();
		String dataDespesa = Converte.calendarToString(despesa.getDataDespesa());
		String dataPagamento = Converte.calendarToString(despesa.getDataPagamento());
		String numeroCheque = despesa.getNumeroCheque();
		String valorDespesa = Double.toString(despesa.getValorDespesa());
		String numeroParcelas = Integer.toString(despesa.getNumeroParcelas());
		String formaPagamento;
		String nomeCategoria;
		
		try {
			nomeCategoria = new CategoriaDAO().getDescricao(despesa.getIdCategoria());
			formaPagamento = new FormaPagamentoDAO().getDescricao(despesa.getIdFormaPagamento());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if( abasCategoria.criarDespesa(nomeCategoria, descricao, valorDespesa,
				dataDespesa, dataPagamento, formaPagamento, numeroParcelas, numeroCheque)){

			exibeMsgAvisoMeta();
			
			atualizaPainelTitulo();
			return true;
		}
		else
			return false;
	}
	
	private void exibeMsgAvisoMeta(){
		double valorMeta = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorMeta();
		double valorAlerta = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorAlerta();
		double totalDespesasPorcentagem = (abasCategoria.getValorTotalDespesas() * 100)/valorMeta;
		
		if(totalDespesasPorcentagem >= 100 && !avisoMetaTotal){
			JanelaMensagem.mostraMensagemWarning(null, "Sua(s) despesa(s) utrapassaram os 100% da meta da categoria.");
			avisoMetaTotal = true;
			avisoMetaDefinido = true;
		}
		else if(totalDespesasPorcentagem >= valorAlerta && !avisoMetaDefinido){
			JanelaMensagem.mostraMensagemWarning(null, "Sua(s) despesa(s) utrapassaram os " + valorAlerta + "% defindos da meta da categoria.");
			avisoMetaDefinido = true;
		}
	}
	
	// Getters e setters
	public JButton getBotaoNovaCategoria() {
		return botaoNovaCategoria;
	}
	
	public JButton getBotaoExcluirCategoria() {
		return botaoExcluirCategoria;
	}

	public JButton getBotaoEditarCategoria() {
		return botaoEditarCategoria;
	}

	public JButton getBotaoAdicionarDespesa() {
		return botaoAdicionarDespesa;
	}

	public JButton getBotaoExcluirDespesa() {
		return botaoExcluirDespesa;
	}

	public JButton getBotaoEditarDespesa() {
		return botaoEditarDespesa;
	}

	public AbasCategoria getAbasCategoria() {
		return abasCategoria;
	}
	
	public String getDescricaoCategoria(){
		return arrayCategoria.get(abasCategoria.getSelectedIndex()).getDescricao();
	}
	
	public double getMetaCategoriaValor(){
		return arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorMeta();
	}
}