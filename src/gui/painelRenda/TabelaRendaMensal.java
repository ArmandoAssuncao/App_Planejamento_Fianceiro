package gui.painelRenda;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaRendaMensal extends JTable{	
	private final static String[] nomeColunas = {"Descrição","Data","Valor"};
	DefaultTableModel modelo;
	private final int TAM_TABELA_X = 750;
	private final int TAM_TABELA_Y = 500;
	
	public TabelaRendaMensal() {
		modelo = new DefaultTableModel(nomeColunas, 0);
		modelo.addRow(new String[]{"","",""});
		modelo.removeRow(0);
		setModel(modelo);
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(TAM_TABELA_X,TAM_TABELA_Y));
		setVisible(true); //TODO apagar isto?
	}
	
	//adiciona linha tabela
	public void adicionaLinha(String descricao,String data, String valor){
		modelo.addRow(new String[]{descricao,data, valor});
	}
}