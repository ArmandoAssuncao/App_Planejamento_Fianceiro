package gui.painelDespesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
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
import gui.framePrincipal.GuiPrincipal;

/**
	Define o painel de <code>Despesa</code>.
* @author Armando Assunção
* @author Richardson William
*
*/
public class IgPainelDespesas extends JPanel{
	/**
	 * Largura do painel.
	 */
	public final int TAM_PAINEL_X = 800;
	/**
	 * Altura do painel.
	 */
	public final int TAM_PAINEL_Y = 600;
	
	boolean avisoMetaTotal = false;
	boolean avisoMetaDefinido = false;
	
	private List<Categoria> arrayCategoria = new ArrayList<Categoria>();
	private AbasCategoria abasCategoria;
	private JPanel painelBotoes;
	private JPanel painelSuperior;
	private PainelTituloPainelDespesas painelTitulo;
	private TEPainelDespesas trataEventosDespesas;
	
	private JButton botaoNovaCategoria;
	private JButton botaoExcluirCategoria;
	private JButton botaoEditarCategoria;
	private JButton botaoAdicionarDespesa;
	private JButton botaoExcluirDespesa;
	private JButton botaoEditarDespesa;
	
	/**
	 * Construtor padrão.
	 * @param framePrincipal componente pai.
	 */
	public IgPainelDespesas(GuiPrincipal framePrincipal) {
		setLayout(new BorderLayout(0,3));
		
		framePrincipal.getGuiMenu().setIgPainelDespesas(this);
		
		trataEventosDespesas = new TEPainelDespesas(this, framePrincipal);
		iniciaElementos();
		
		iniciaValoresCategoria();
		
		criaPainelBotoes();
		
		criaPainelTitulo();
		
		painelSuperior.setLayout(new BorderLayout());
		painelSuperior.setBorder(new LineBorder(Color.BLUE)); //TODO debug
		painelTitulo.setBorder(new LineBorder(Color.RED));   //TODO debug
		painelSuperior.add(painelTitulo, BorderLayout.WEST);
		
		add(painelSuperior, BorderLayout.NORTH);
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
	}//construtor
	
	
	private void iniciaElementos(){
		abasCategoria = new AbasCategoria();
		painelBotoes = new JPanel();
		painelSuperior = new JPanel();
		painelTitulo = new PainelTituloPainelDespesas();
		botaoNovaCategoria = new JButton();
		botaoExcluirCategoria = new JButton();
		botaoEditarCategoria = new JButton();
		botaoAdicionarDespesa = new JButton();
		botaoExcluirDespesa = new JButton();
		botaoEditarDespesa = new JButton();
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
		constraints.insets = new Insets(0, 0, 25, 0);
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
		//Atualiza os labels do painel Titulo sempre quando muda de categoria(aba).
		abasCategoria.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(abasCategoria.getNumeroDeAbas() != 0 && arrayCategoria.size() != 0)
					atualizaPainelTitulo();
			}
		});
		
		/*Após adicionar o tratador de eventos chama o método para atualizar o 
		 * painel para corresponder com a aba selecionada.
		 */
		atualizaPainelTitulo();
	}
	

	/**
	 * Atualiza o painel de título da janela.
	 */
	private void atualizaPainelTitulo(){
		String descricao = arrayCategoria.get(abasCategoria.getSelectedIndex()).getDescricao();
		double valorAlerta = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorAlerta();
		double somaDespesas = abasCategoria.getValorTotalDespesas();
		int totalDespesas = abasCategoria.getNumeroDeDespesasDaCategoria();
		double valorMetaPorcentagem = arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorMeta();
		
		painelTitulo.atualizarPainel(descricao,totalDespesas,somaDespesas,valorAlerta,valorMetaPorcentagem);
	}//atualizaPainelTitulo
	
	/**
	 * Inicia as abas de <code>Categoria</code>.
	 */
	private void iniciaValoresCategoria(){
		List<MetaMensal> arrayMetaMensalTemp = new ArrayList<MetaMensal>();
		List<Categoria> arrayCategoriaTemp = new ArrayList<Categoria>();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		MetaMensalDAO metaMensalDAO = new MetaMensalDAO();
		
		//Inicia só as metas desse mês
		Calendar mesAno = Calendar.getInstance();
		try {
			arrayCategoriaTemp = CategoriaDAO.todasAsCategorias();
			arrayMetaMensalTemp = MetaMensalDAO.metaMensalDoMesAno(mesAno);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Inicia as metas caso o mês mude
		if(arrayMetaMensalTemp.size() == 0){
			String descricao;
			for(int i = 0; i < arrayCategoriaTemp.size(); i++){
				descricao = arrayCategoriaTemp.get(i).getDescricao();
				MetaMensal metaMensal = null;
				try {
					metaMensal = new MetaMensal(categoriaDAO.getId(descricao), mesAno, 0.0, 70.0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				arrayMetaMensalTemp.add(metaMensal);
				metaMensalDAO.inserir(metaMensal, descricao);
			}
		}
		
		//Inicializa as abas das categoria
		for(int indice = 0; indice < arrayMetaMensalTemp.size(); indice++){
			int idCategoria = 1;
			try {
				idCategoria = categoriaDAO.getId(arrayCategoriaTemp.get(indice).getDescricao());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for(int j = 0; j < arrayMetaMensalTemp.size(); j++){
				if(idCategoria == arrayMetaMensalTemp.get(j).getId()){
					arrayCategoriaTemp.get(indice).setMetaMensal(arrayMetaMensalTemp.get(j));
					break;
				}
			}
			
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
		
		//Inicia só as despesas desse mês
		Calendar mesAno = Calendar.getInstance();
		try {
			arrayDespesasTemp = DespesaDAO.despesasDoMesAno(mesAno);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		int idCategoria = 0;
		try{
			idCategoria = new CategoriaDAO().getId(descricaoCategoria);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		//Inicializa as despesas da categoria
			for(Despesa d : arrayDespesasTemp){
				if(d.getIdCategoria() == idCategoria){
					String descricao = d.getDescricao();
					String dataDespesa = Converte.calendarToString(d.getDataDespesa());
					String dataPagamento = Converte.calendarToString(d.getDataPagamento());
					String numeroCheque = d.getNumeroCheque();
					String valorDespesa = Double.toString(d.getValorDespesa());
					String numeroParcelas = Integer.toString(d.getNumeroParcelas());
					String formaPagamento = new FormaPagamentoDAO().getDescricao(d.getIdFormaPagamento());
					abasCategoria.criarDespesa(descricaoCategoria, descricao, valorDespesa, dataDespesa, dataPagamento, formaPagamento, numeroParcelas, numeroCheque);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria uma <code>Categoria</code> e adiciona a aba correspondente
	 * @param categoria objeto <code>Categoria</code> que será adicionado.
	 * @return <code>true</code> em caso de sucesso na operação, <code>false</code> em caso contrário.
	 */
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

	/**
	 * Edita a <code>Categoria</code> selecionada.
	 * @param categoria objeto <code>Categoria</code> com os novos atributos.
	 * @return <code>true</code> em caso de sucesso na operação, <code>false</code> em caso contrário.
	 */
	public boolean editarCategoria(Categoria categoria){
		if( abasCategoria.editarCategoria(categoria.getDescricao())){
			arrayCategoria.set(abasCategoria.getSelectedIndex(), categoria);
			
			atualizaPainelTitulo();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Remove a Categoria selecionada.
	 * @return <code>true</code> em caso de sucesso na operação, <code>false</code> em caso contrário.
	 */
	public boolean removerCategoria(){
		int abaSelecionada = abasCategoria.getSelectedIndex();
		if( abasCategoria.removerCategoria(getDescricaoCategoria())){
			arrayCategoria.remove(abaSelecionada);
			
			atualizaPainelTitulo();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Adiciona o objeto despesa na tabela e o salva no banco de dados.
	 * @param despesa objeto que será salvo.
	 * @return <code>true</code> em caso de sucesso na operação, <code>false</code> em caso contrário.
	 */
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
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão nova categoria.
	 */
	public JButton getBotaoNovaCategoria() {
		return botaoNovaCategoria;
	}
	
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão excluir categoria.
	 */
	public JButton getBotaoExcluirCategoria() {
		return botaoExcluirCategoria;
	}

	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão editar categoria.
	 */
	public JButton getBotaoEditarCategoria() {
		return botaoEditarCategoria;
	}

	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão adicionar despesa.
	 */
	public JButton getBotaoAdicionarDespesa() {
		return botaoAdicionarDespesa;
	}

	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão excluir despesa.
	 */
	public JButton getBotaoExcluirDespesa() {
		return botaoExcluirDespesa;
	}

	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão editar despesa.
	 */
	public JButton getBotaoEditarDespesa() {
		return botaoEditarDespesa;
	}

	/**
	 *  Retorna uma referência de uma <code>AbasCategoria</code>.
	 * @return objeto <code>AbasCategoria</code>.
	 */
	public AbasCategoria getAbasCategoria() {
		return abasCategoria;
	}
	
	/**
	 * Retorna a descrição da <code>Categoria</code>.
	 * @return <code>String</code> com a descrição da categoria.
	 */
	public String getDescricaoCategoria(){
		return arrayCategoria.get(abasCategoria.getSelectedIndex()).getDescricao();
	}
	
	/**
	 * Retorna o valor da meta da categoria.
	 * @return <code>double</code> com a meta da categoria.
	 */
	public double getMetaCategoriaValor(){
		return arrayCategoria.get(abasCategoria.getSelectedIndex()).getValorMeta();
	}
}