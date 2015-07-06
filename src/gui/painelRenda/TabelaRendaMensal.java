package gui.painelRenda;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
  Define a tabela de rendas mensais.
* @author Armando Assunção
* @author Richardson William
*
*/
public class TabelaRendaMensal extends JTable{	
	private static final long serialVersionUID = -1747064781487045370L;
	
	private final static String[] nomeColunas = {"Descrição","Data","Valor"};
	private DefaultTableModel modelo;
	
	/**
	 * Construtor padrão;
	 */
	public TabelaRendaMensal() {
		setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);
		setOpaque(true);
		
		modelo = new DefaultTableModel(nomeColunas, 0);
		setModel(modelo);
		
		setBackground(Color.WHITE);
		//setPreferredSize(new Dimension(TAM_TABELA_X,TAM_TABELA_Y));
		setEnabled(false);
		setVisible(true); //TODO apagar isto?
		
	}
	
	/**
	 * Adiciona uma nova linha na tabela.
	 * @param descricao descrição da renda.
	 * @param data data da renda.
	 * @param valor valor da renda.
	 */
	//adiciona linha tabela
	public void adicionaLinha(String descricao,String data, String valor){
		modelo.addRow(new String[]{descricao,data, valor});
	}
	
	/**
	 * Calcula e retorna a quantidade de rendas mensal há na tabela.
	 * @return quantidade de rendas mensal.
	 */
	public int qtdRendasMensal(){
		Set<String> renda = new HashSet<String>();
		for(int i=0;i<getRowCount();i++)
			renda.add(modelo.getValueAt(i, 0).toString());
		
		return renda.size();
	}

	/**
	 * Calcula e retorna a soma das rendas do mês
	 * @return soma das rendas do mês
	 */
	public double getSomaRendasMensal() {
		/*Como na soluçao utilizada os dados carregados para a tabela sao apenas do mes atual, 
		 * a soma das rendas do mes será apenas a soma de todos os valores da tabela.
		 */
		double soma = 0;
		for(int i=0;i<getRowCount();i++)
			soma += Double.parseDouble(modelo.getValueAt(i,2).toString());
		return soma;
	}
}