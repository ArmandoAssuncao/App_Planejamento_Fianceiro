package gui.painelDespesas;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

/**
 * Classe contem as categorias do aplicativo <code>Despesa</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 * 
 * @see JTabbedPane
 */
public class AbasCategoria extends JTabbedPane{
	private static final long serialVersionUID = 5381069900422879564L;
	
	private final int TAM_ABA_X = 750;
	private final int TAM_ABA_Y = 500;
	
	private TabelaDaCategoria tabela;
	private JScrollPane barraRolagem;
	
	
	/**
	 * Construtor default da classe
	 */
	public AbasCategoria() {
		
		String fonteDefault = new JLabel().getFont().getFontName(); //Pega a fonte default do sistema
		Font fonte = new Font(fonteDefault, Font.PLAIN, 12);
		setFont(fonte);
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setPreferredSize(new Dimension(TAM_ABA_X, TAM_ABA_Y));
		//setBackground(new Color(205, 205, 205));//TODO COR
		setBorder(null);
		setVisible(true);
	}
	
	/**
	 * Cria uma categoria
	 * 
	 * @param nomeCategoria <code>String</code> com o nome da categoria a ser criada.
	 * @return true <code>true</code> se criou, false <code>false</code> caso contrário.
	 */
	public boolean criarCategoria(String nomeCategoria){
		//verifica se o nome da aba ja existe
		for(int indice = 0; indice < getTabCount(); indice++){
			if(getTitleAt(indice).equalsIgnoreCase(nomeCategoria)){
				return false;
			}
		}
		
		tabela = new TabelaDaCategoria();
		barraRolagem = new JScrollPane();
		
		barraRolagem.setViewportView(tabela);
		barraRolagem.setBorder(null);
		
		add(nomeCategoria, barraRolagem);
		
		return true;
	}
	
	/**
	 * Edita uma categoria
	 * 
	 * @param nomeCategoria <code>String</code> com o nome da categoria a ser editada.
	 * @return true <code>true</code> se editou, false <code>false</code> caso contrário.
	 */
	public boolean editarCategoria(String nomeCategoria){
		//Se o nome da categoria for igual ao argumento, não faz a verificação
		if(!getTitleAt(getSelectedIndex()).equals(nomeCategoria)){
			//verifica se o nome da aba ja existe
			for(int indice = 0; indice < getTabCount(); indice++){
				if(getTitleAt(indice).equalsIgnoreCase(nomeCategoria)){
					System.out.println("Nome da categoria igual. Editar");
					return false;
				}
			}
		}

		//Muda o nome da categoria
		setTitleAt(getSelectedIndex(), nomeCategoria);
		
		return true;
	}
	
	/**
	 * Remove uma categoria
	 * 
	 * @param nomeCategoria <code>String</code> com o nome da categoria a ser removida.
	 * @return true <code>true</code> se removeu, false <code>false</code> caso contrário.
	 */
	public boolean removerCategoria(String nomeCategoria){
		remove(getSelectedIndex());
		return true;
	}
	
	/**
	 * Cria uma despesa
	 * 
	 * @param categoria <code>String</code> com o nome da categoria a ser adicionada a despesa.
	 * @param descricao <code>String</code> com o nome da despesa a ser criada.
	 * @param valor <code>String</code> com o valor da despesa a ser criada.
	 * @param dataDaDespesa <code>String</code> com a data da despesa a ser criada.
	 * @param dataDoPagamento <code>String</code> com a data do pagamento da despesa a ser criada.
	 * @param tipoDoPagamento <code>String</code> com o tipo do pagamento da despesa a ser criada.
	 * @param parcelas <code>String</code> com o numero de parcelas da despesa a ser criada.
	 * @param numeroDoCheque <code>String</code> com o numero do cheque da despesa a ser criada.
	 * @return true <code>true</code> se removeu, false <code>false</code> caso contrário.
	 */
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
	
	/** 
	 * Retornar um <code>int</code> com o o numero de abas
	 * @return <code>int</code> com o numero de abas
	 */
	public int getNumeroDeAbas(){
		return getTabCount();
	}
	
	/** 
	 * Retornar um <code>int</code> com o numero de despesas da categoria
	 * @return <code>int</code> com o o numero de despesas da categoria
	 */
	public int getNumeroDeDespesasDaCategoria(){
		TabelaDaCategoria tabela = getTabelaDaCategoriaSelecionada();
		
		return tabela.getRowCount();
	}
	
	/** 
	 * Retornar um <code>int</code> com o valor total de despesas da categoria
	 * @return <code>int</code> com o o numero de despesas da categoria
	 */
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

	/**
	 * Retorna uma referência da tabela da aba da categoria.
	 * @return referência da tabela da aba da categoria.
	 */
	public TabelaDaCategoria getTabela() {
		return tabela;
	}
	
}
