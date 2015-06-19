package gui.painelRenda;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaRendaMensal extends JTable{
	//private final int TAM_TABELA_X = 500;
	//private final int TAM_TABELA_Y = 500;
	
	private final static String[] nomeColunas = {"Data","Valor"};
	DefaultTableModel modelo;

	public TabelaRendaMensal() {
		
		modelo = new DefaultTableModel(nomeColunas, 0);
	//	modelo.addRow(new String[]{"",""});
	//	modelo.removeRow(0);
		setModel(modelo);
		
		//setPreferredSize(new Dimension(TAM_TABELA_X, TAM_TABELA_Y));
		setBackground(Color.WHITE);
		setVisible(true); //TODO apagar isto?
	}
	
	//adiciona linha tabela
	public void adicionaLinha(String data, String valor){
		modelo.addRow(new String[]{data, valor});
	}

}