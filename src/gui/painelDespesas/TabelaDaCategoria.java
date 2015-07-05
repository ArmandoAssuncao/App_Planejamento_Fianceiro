package gui.painelDespesas;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import enumeracoes.FormaPagamento;

/**Define a tabela da categoria.
 * 
* @author Armando Assunção
* @author Richardson William
*
*/
public class TabelaDaCategoria extends JTable{
//	private final int TAM_TABELA_X = 500;
//	private final int TAM_TABELA_Y = 500;
	
	private final static String[] nomeColunas = {"Descrição", "Valor", "Data da Despesa", "Data do Pagamento", "Tipo Pagamento", "Parcelas", "Número do cheque"};
	DefaultTableModel modelo;

	/**
	 * Construtor padrão;
	 */
	public TabelaDaCategoria() {
		setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);
		setOpaque(true);
		
		modelo = new DefaultTableModel(nomeColunas, 0);
		modelo.addRow(new String[]{"","","","","","",""});
		modelo.removeRow(0);
		setModel(modelo);
		
		//setPreferredSize(new Dimension(TAM_TABELA_X, TAM_TABELA_Y));
		setBackground(Color.WHITE);
		setEnabled(false);
		setVisible(true);
	}
	
	/**
	 * Adiciona uma linha na tabela da categoria de <code>Despesa</code> com os seguintes atributos
	 * @param descricao descrição da <code>Despesa</code>.
	 * @param valor valor da <code>Despesa</code>.
	 * @param dataDaDespesa data da <code>Despesa</code>.
	 * @param dataDoPagamento data do pagamento da <code>Despesa</code>.
	 * @param tipoDoPagamento tipo de pagamento da <code>Despesa</code>.
	 * @param parcelas número de parcelas da <code>Despesa</code>
	 * @param numeroDoCheque número do cheque da <code>Despesa</code>.
	 * 
	 * @see FormaPagamento
	 */
	public void adicionaLinha(String descricao, String valor, String dataDaDespesa, String dataDoPagamento, 
			String tipoDoPagamento, String parcelas, String numeroDoCheque){
		modelo.addRow(new String[]{descricao, valor, dataDaDespesa, dataDoPagamento, tipoDoPagamento, parcelas,
				numeroDoCheque});
	}
	
}
