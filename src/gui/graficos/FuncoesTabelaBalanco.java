package gui.graficos;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Esta classe possui métodos para manipulação de tabela <code>JTable</code>
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public final class FuncoesTabelaBalanco {
	
	private FuncoesTabelaBalanco() {}

	/**
	 * Limpa a tabela, removendo todas as linhas
	 * @param tabela <code>JTable</code> com a tabela 
	 */
	public static void limpaTabela(JTable tabela){
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		int tamanho = modelo.getRowCount();
		for(int i = tamanho-1; i >= 0; i--)
			modelo.removeRow(i);
	}
	
	/**
	 * Adiciona um título a tabela na posição indicada.
	 * @param tabela <code>JTable</code> com a tabela
	 * @param titulo <code>String</code> com o título 
	 * @param posicaoColuna <code>int</code> indicando a posição a ser inserido o título.
	 * @param alturaLinha <code>int</code> em pixels da linha
	 */
	public static void adicionarTituloTabela(JTable tabela, String titulo, int posicaoColuna, int alturaLinha){
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		String tracos = "";
		int numeroDeColunas = tabela.getColumnCount();
		String linha[] = new String[numeroDeColunas];
		for(int i = 0; i < numeroDeColunas; i++){
			if(i == posicaoColuna)
				linha[i] = "<html><b><h3><font color='black'>"+ titulo +"</font></h3></b></html>";
			else{
				linha[i] = tracos;
			}
		}
		
		modelo.addRow(linha);
		tabela.setRowHeight(tabela.getRowCount()-1, alturaLinha);
		tabela.getColumnCount();
	}
	
	/**
	 * Adiciona um título para as colunas na tabela.
	 * @param tabela <code>JTable</code> com a tabela
	 * @param titulos <code>String[]</code> com o título 
	 */
	public static void adicionarTitulosColunasTabela(JTable tabela, String[] titulos){
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		String[] titulosModificados = new String[titulos.length];
		for(int i = 0; i < titulos.length; i++){
			titulosModificados[i] = "<html><b>" + titulos[i] + "</b></html>";
		}
			
		modelo.addRow(titulosModificados);
	}

	/**Adiciona dados a tabela.
	 * @param tabela <code>JTable</code> com a tabela
	 * @param dados array de <code>String</code> com os dados a serem adicionados.
	 */
	public static void adicionarDadosTabela(JTable tabela, String[] dados){
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.addRow(dados);
	}
	
	/**
	 * Adiciona uma linha vazia na tabela.
	 * @param tabela <code>JTable</code> com a tabela
	 * @param alturaLinha <int> em pixels da linha
	 */
	public static void adicionarLinhaVaziaTabela(JTable tabela, int alturaLinha){
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.addRow(new String[]{});
		tabela.setRowHeight(tabela.getRowCount()-1, alturaLinha);
	}
}
