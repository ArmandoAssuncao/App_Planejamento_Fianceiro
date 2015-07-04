package gui.painelRenda;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaRendaMensal extends JTable{	
	private final static String[] nomeColunas = {"Descrição","Data","Valor"};
	DefaultTableModel modelo;
	public final int TAM_TABELA_X = 750;
	public final int TAM_TABELA_Y = 500;
	
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
	
	//adiciona linha tabela
	public void adicionaLinha(String descricao,String data, String valor){
		modelo.addRow(new String[]{descricao,data, valor});
	}
	
	public int qtdRendasMensal(){
		Set<String> renda = new HashSet<String>();
		for(int i=0;i<getRowCount();i++)
			renda.add(modelo.getValueAt(i, 0).toString());
		
		return renda.size();
	}

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