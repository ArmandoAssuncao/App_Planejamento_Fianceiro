package gui.graficos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Cria uma GUI para exibir o balanço mensal do mês.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaBalancoMensal extends JDialog {
	private final String TITULO_JANELA= "Nova Categoria";
	private final int TAM_JANELA_X = 800;
	private final int TAM_JANELA_Y = 600;
	
	private TEJanelaBalancoMensal teJanelaBalancoMensal;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private JComboBox<String> jComboBoxMes;
	
	/**
	 * Cria uma instância do <code>JDialog</code>
	 */
	public JanelaBalancoMensal() {
		setTitle(TITULO_JANELA);
		
		this.teJanelaBalancoMensal = new TEJanelaBalancoMensal(this);
		
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
		modelo = new DefaultTableModel(0, 7);
		tabela.setRowHeight(20);
		tabela.setModel(modelo);
		tabela.setTableHeader(null);
		
		String fonteDefault = new JTable().getFont().getFontName(); //Pega a fonte default do sistema
		Font fonte = new Font(fonteDefault, Font.PLAIN, 14);
		tabela.setFont(fonte);
		modelo.addRow(new String[]{"aa"});
		
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		  
		esquerda.setHorizontalAlignment(SwingConstants.LEFT);
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		direita.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tabela.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tabela.getColumnModel().getColumn(1).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(2).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(3).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(4).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(5).setCellRenderer(direita);
		tabela.getColumnModel().getColumn(6).setCellRenderer(direita);
		
		
		JScrollPane barraRolagem = new JScrollPane();
		barraRolagem.setViewportView(tabela);
		
		painelBalanco.add(barraRolagem);
		painelBalanco.setPreferredSize(new Dimension(700, 600));
		return painelBalanco;
	}
	
	private JPanel criaPainelLeste(){
		JPanel painelBotoes = new JPanel();
		
		jComboBoxMes = new JComboBox<String>();
		jComboBoxMes.addItem("10/10/2010");
		jComboBoxMes.addItem("10/10/2011");
		
		jComboBoxMes.addActionListener(teJanelaBalancoMensal);
		
		painelBotoes.add(jComboBoxMes);
		return painelBotoes;
	}
	
	public void adicionarTituloTabela(String titulo){
		tabela.setRowHeight(tabela.getRowCount(), 30);
		modelo.addRow(new String[]{titulo});
	}
	
	public void adicionarDadosTabela(String[] dados){
		modelo.addRow(dados);
	}
	
	public void adicionarLinhaVaziaTabela(){
		modelo.addRow(new String[]{});
	}
	
	public JComboBox<String> getjComboBoxMes() {
		return jComboBoxMes;
	}

	public String getTextoJComboBoxMes() {
		return jComboBoxMes.getItemAt(jComboBoxMes.getSelectedIndex());
	}
	
}
