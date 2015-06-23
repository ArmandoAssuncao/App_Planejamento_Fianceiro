package gui.painelRenda;

import gui.JanelaAviso;
import gui.JanelaDeConfirmacao;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

public class AbasRenda extends JTabbedPane{
	private final int TAM_ABA_X = 750;
	private final int TAM_ABA_Y = 500;
	
	private TabelaRendaMensal tabelaRendaMensal;
	private JScrollPane barraRolagem;
	
	
	public AbasRenda() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setPreferredSize(new Dimension(TAM_ABA_X, TAM_ABA_Y));
		//setBackground(Color.GRAY);
		setVisible(true); //TODO 
	}
	
	public boolean criarAbaRenda(String nomeAbaRenda){
		//verifica se o nome da aba ja existe
		for(int indice = 0; indice < getTabCount(); indice++){
			if(getTitleAt(indice).equalsIgnoreCase(nomeAbaRenda)){
				System.out.println("Nome da aba renda igual");
				return false;
			}
		}
		
		tabelaRendaMensal = new TabelaRendaMensal();
		barraRolagem = new JScrollPane();
		
		for(int i = 0; i < 40; i++) //TODO APAGAR///////////////////////
			tabelaRendaMensal.adicionaLinha("DATA " +nomeAbaRenda + " " + i, "VALOR");
		
		barraRolagem.setViewportView(tabelaRendaMensal);
		
		add(nomeAbaRenda, barraRolagem);
		
		return true;
	}
	
	//Editar Aba
	public boolean editarAbaRenda(String nomeAbaRenda){
		//Se o nome da categoria for igual ao argumento, não faz a verificação
		if(!getTitleAt(getSelectedIndex()).equals(nomeAbaRenda)){
			//verifica se o nome da aba ja existe
			for(int indice = 0; indice < getTabCount(); indice++){
				if(getTitleAt(indice).equalsIgnoreCase(nomeAbaRenda)){
					System.out.println("Nome da aba renda igual. Editar");
					return false;
				}
			}
		}

		//Muda o nome da categoria
		setTitleAt(getSelectedIndex(), nomeAbaRenda);
		
		return true;
	}
	
	//Remover Aba
	public boolean removerAba(String nomeAba){
		JanelaDeConfirmacao janelaDeConfirmacao = new JanelaDeConfirmacao("Remover Renda", 
				"Tem certeza que deseja remover a aba \"" + nomeAba + "\" e todo o seu conteúdo?");
		if(!janelaDeConfirmacao.isConfirmar()){
			return false;
		}
		
		remove(getSelectedIndex());
		new JanelaAviso("Remover Renda", "A aba  \"" + nomeAba + "\" foi removida com sucesso.");
		
		return true;
	}
	
	public boolean criarRendaMensal(String nomeAbaRenda, String data, String valor){
		JScrollPane conteudo = null;
		
		//Verifica se exite uma categoria com o nome passado no argumento.
		for(int indice = 0; indice < getTabCount(); indice++){
			if(nomeAbaRenda.equals(getTitleAt(indice))){
				conteudo = (JScrollPane)getComponentAt(indice);
				break;
			}
		}
		
		if(conteudo == null) return false;
		
		JViewport viewport = conteudo.getViewport(); 
		TabelaRendaMensal tabela = (TabelaRendaMensal)viewport.getView(); 

		tabela.adicionaLinha(data, valor);
		
		return true;
	}
	
	public int getNumeroDeAbas(){
		return getTabCount();
	}
	
	public int getNumeroDeValoresdaAbaRenda(){
		TabelaRendaMensal tabela = getTabelaDaRendaSelecionada();
		
		return tabela.getRowCount();
	}
	
	public double getValorTotalRenda(){
		TabelaRendaMensal tabela = getTabelaDaRendaSelecionada();
		double valor = 0;
		
		try{
			for(int i = 0; i < tabela.getRowCount(); i++){
				valor += Double.parseDouble((String)tabela.getValueAt(i, 1));
			}
		}
		catch(NumberFormatException e){
			System.err.println("Conversão de String para double falhou.\ngetValorTotalRenda().");
		}
		
		return valor;
	}
	
	private TabelaRendaMensal getTabelaDaRendaSelecionada(){
		JScrollPane conteudo = null;
		
		conteudo = (JScrollPane)getSelectedComponent();
		
		JViewport viewport = conteudo.getViewport();
		TabelaRendaMensal tabela = (TabelaRendaMensal)viewport.getView();
		
		return tabela;
	}
	
}
