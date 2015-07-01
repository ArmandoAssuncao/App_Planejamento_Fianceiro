package gui.graficos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import persistencia.MetaMensalDAO;

/**
 * Cria uma GUI para exibir o balanço mensal do mês.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaBalancoDespesas extends JDialog {
	private final String TITULO_JANELA= "Balanço Tipo de Despesas";
	private final int TAM_JANELA_X = 900;
	private final int TAM_JANELA_Y = 600;
	private final int NUM_COLUNAS = 7;
	
	private TEJanelaBalancoDespesas teJanelaBalancoDespesas;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private JComboBox<String> jComboBoxMes;
	
	/**
	 * Cria uma instância do <code>JDialog</code>
	 */
	public JanelaBalancoDespesas() {
		setTitle(TITULO_JANELA);
		
		this.teJanelaBalancoDespesas = new TEJanelaBalancoDespesas(this);
		
		//iniciaElementos();
		
		add(criaPainelOeste(), BorderLayout.WEST);
		add(criaPainelLeste(), BorderLayout.EAST);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}
	
	/**
	 * Cria o painel oeste do <code>JDialog</code>.
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelOeste(){
		JPanel painelBalanco = new JPanel(new BorderLayout(0,0));

		tabela = new JTable();
		modelo = new DefaultTableModel(0, NUM_COLUNAS);
		tabela.setRowHeight(20);
		tabela.setModel(modelo);
		tabela.setTableHeader(null);
		
		String fonteDefault = new JTable().getFont().getFontName(); //Pega a fonte default do sistema
		Font fonte = new Font(fonteDefault, Font.PLAIN, 12);
		tabela.setFont(fonte);
		
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		  
		esquerda.setHorizontalAlignment(SwingConstants.LEFT);
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		direita.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		tabela.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		
		
		JScrollPane barraRolagem = new JScrollPane();
		barraRolagem.setViewportView(tabela);
		
		painelBalanco.add(barraRolagem);
		painelBalanco.setPreferredSize(new Dimension(780, 600));
		return painelBalanco;
	}
	
	private JPanel criaPainelLeste(){
		JPanel painelBotoes = new JPanel();
		
		JLabel labelData = new JLabel("Data:");
		jComboBoxMes = new JComboBox<String>();
		
		List<Calendar> arrayDatas = new ArrayList<>();
		try {
			arrayDatas = new MetaMensalDAO().getDatasMesAno();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < arrayDatas.size(); i++){
			int mes = arrayDatas.get(i).get(Calendar.MONTH) +1;
			int ano = arrayDatas.get(i).get(Calendar.YEAR);
			jComboBoxMes.addItem("01/"+mes+"/"+ano);
		}
		
		jComboBoxMes.addActionListener(teJanelaBalancoDespesas);
		
		painelBotoes.add(labelData);
		painelBotoes.add(jComboBoxMes);
		return painelBotoes;
	}
	
	public void limpaTabela(){
		int tamanho = modelo.getRowCount();
		for(int i = tamanho-1; i >= 0; i--)
			modelo.removeRow(i);
	}
	
	public void adicionarTituloTabela(String titulo, int posicaoColuna){
		String tracos = ".....................................";
		String linha[] = new String[NUM_COLUNAS];
		for(int i = 0; i < NUM_COLUNAS; i++){
			if(i == posicaoColuna)
				linha[i] = titulo;
			else{
				linha[i] = tracos;
			}
		}
		modelo.addRow(linha);
		tabela.setRowHeight(tabela.getRowCount()-1, 40);
	}
	
	public void adicionarDadosTabela(String[] dados){
		modelo.addRow(dados);
	}
	
	public void adicionarLinhaVaziaTabela(int espacamento){
		modelo.addRow(new String[]{});
		tabela.setRowHeight(tabela.getRowCount()-1, espacamento);
	}
	
	public JComboBox<String> getjComboBoxMes() {
		return jComboBoxMes;
	}

	public String getTextoJComboBoxMes() {
		return jComboBoxMes.getItemAt(jComboBoxMes.getSelectedIndex());
	}
	
}