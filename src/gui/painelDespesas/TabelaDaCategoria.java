package gui.painelDespesas;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaDaCategoria extends JTable{
	private final int TAM_TABELA_X = 500;
	private final int TAM_TABELA_Y = 500;
	
	private final static String[] nomeColunas = {"Descrição", "Valor", "Data da Despesa", "Data do Pagamento", "Tipo Pagamento", "Número do Cheque", "Parcelas"};
	DefaultTableModel modelo;

	public TabelaDaCategoria() {
		
		modelo = new DefaultTableModel(nomeColunas, 0);
		modelo.addRow(new String[]{"","","","","","",""});
		modelo.removeRow(0);
		setModel(modelo);
		
		//setPreferredSize(new Dimension(TAM_TABELA_X, TAM_TABELA_Y));
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	//adiciona linha à tabela
	public void adicionaLinha(String nome, String valor, String data){
		modelo.addRow(new String[]{nome, valor, data});
	}
	
	
}
