package gui.painelDespesas;

import gui.JanelaAviso;
import gui.JanelaDeConfirmacao;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

public class AbasCategoria extends JTabbedPane{
	private final int TAM_ABA_X = 900;
	private final int TAM_ABA_Y = 500;
	
	private TabelaDaCategoria tabela;
	private JScrollPane barraRolagem;
	
	
	public AbasCategoria() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setPreferredSize(new Dimension(TAM_ABA_X, TAM_ABA_Y));
		//setBackground(Color.GRAY);
		setVisible(true);
	}
	
	//Cria nova Categoria
	public boolean criarCategoria(String nomeCategoria){
		//verifica se o nome da aba ja existe
		for(int indice = 0; indice < getTabCount(); indice++){
			if(getTitleAt(indice).equalsIgnoreCase(nomeCategoria)){
				System.out.println("Nome da categoria igual");
				return false;
			}
		}
		
		tabela = new TabelaDaCategoria();
		barraRolagem = new JScrollPane();
		
		for(int i = 0; i < 20; i++) //APAGAR/////////////////////////////////////////////////
			tabela.adicionaLinha(nomeCategoria + " " + i, "2.0", "23/12", "1", "1", "1", "1");
		
		barraRolagem.setViewportView(tabela);
		
		add(nomeCategoria, barraRolagem);
		
		return true;
	}
	
	//Remover categoria
	public boolean removerCategoria(String nomeCategoria){
		JanelaDeConfirmacao janelaDeConfirmacao = new JanelaDeConfirmacao("Remover Categoria", "Tem certeza que deseja remover a categoria \"" + nomeCategoria + "\"?");
		if(!janelaDeConfirmacao.isConfirmar()){
			return false;
		}
		
		remove(getSelectedIndex());
		new JanelaAviso("Remover Categoria", "A categoria \"" + nomeCategoria + "\" foi removida com sucesso.");
		
		return true;
	}
	
	//Editar Categoria
	public boolean editarCategoria(String nomeCategoria){
		//verifica se o nome da aba ja existe
		for(int indice = 0; indice < getTabCount(); indice++){
			if(getTitleAt(indice).equalsIgnoreCase(nomeCategoria)){
				System.out.println("Nome da categoria igual. Editar");
				return false;
			}
		}

		//Muda o nome da categoria
		setTitleAt(getSelectedIndex(), nomeCategoria);
		
		return true;
	}
	
	public boolean criarDespesa(String categoria, String descricao, String valor, String dataDaDespesa, String dataDoPagamento, String tipoDoPagamento, String parcelas, String numeroDoCheque){
		JScrollPane conteudo = null;
		
		//Verifica se exite uma categoria com o nome passado no argumento.
		for(int indice = 0; indice < getTabCount(); indice++){
			if(categoria.equals(getTitleAt(indice))){
				conteudo = (JScrollPane)getComponentAt(indice);
				break;
			}
		}
		
		if(conteudo == null) return false;
		
		JViewport viewport = conteudo.getViewport(); 
		TabelaDaCategoria tabela = (TabelaDaCategoria)viewport.getView(); 

		tabela.adicionaLinha(descricao, valor, dataDaDespesa, dataDoPagamento, tipoDoPagamento, parcelas, numeroDoCheque);
		
		return true;
	}
	
	public int getNumeroDeAbas(){
		return getTabCount();
	}
	
	public int getNumeroDeDespesasDaCategoria(){
		TabelaDaCategoria tabela = getTabelaDaCategoriaSelecionada();
		
		return tabela.getRowCount();
	}
	
	public double getValorTotalDespesas(){
		TabelaDaCategoria tabela = getTabelaDaCategoriaSelecionada();
		double valor = 0;
		
		try{
			for(int i = 0; i < tabela.getRowCount(); i++){
				valor += Double.parseDouble((String)tabela.getValueAt(i, 1));
			}
		}
		catch(NumberFormatException e){
			System.err.println("Conversão de String para double falhou.\ngetValorTotalDespesas().");
		}
		
		return valor;
	}
	
	private TabelaDaCategoria getTabelaDaCategoriaSelecionada(){
		JScrollPane conteudo = null;
		
		conteudo = (JScrollPane)getSelectedComponent();
		
		JViewport viewport = conteudo.getViewport();
		TabelaDaCategoria tabela = (TabelaDaCategoria)viewport.getView();
		
		return tabela;
	}
	
}
