package gui.painelDespesas;

import gui.JanelaAviso;
import gui.JanelaDeConfirmacao;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
		
		for(int i = 0; i < 50; i++) //APAGAR/////////////////////////////////////////////////
			tabela.adicionaLinha(nomeCategoria, "valor", "23/12");
		
		barraRolagem.setViewportView(tabela);
		
		add(nomeCategoria, barraRolagem);
		
		return true;
	}
	
	//Remover categoria
	public boolean removerCategoria(){
		String nomeDaCategoria = getTitleAt(getSelectedIndex());
		
		JanelaDeConfirmacao janelaDeConfirmacao = new JanelaDeConfirmacao("Remover Categoria", "Tem certeza que deseja remover a categoria \"" + nomeDaCategoria + "\"?");
		if(janelaDeConfirmacao.isConfirmar()){
			remove(getSelectedIndex());
			//Implementar a parte de remover no banco, também remover as despesas associadas/////////////////////////////////////////
			new JanelaAviso("Remover Categoria", "A categoria \"" + nomeDaCategoria + "\" foi removida com sucesso.");
		}
		
		return false;
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
	
	
}
