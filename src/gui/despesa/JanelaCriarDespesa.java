package gui.despesa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventos.despesa.TEJanelaCriarDespesa;
import gui.JanelaAviso;
import gui.painelDespesas.AbasCategoria;

public class JanelaCriarDespesa extends JDialog{
	private final String TITULO_JANELA= "Criar Despesa";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaCriarDespesa trataEventosDespesa;
	private AbasCategoria abasCategoria;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoCriar;
	private JButton botaoCancelar;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JLabel labelDescricao;
	private JLabel labelValorDespesa;
	private JLabel labelDataDaDespesa;
	private JLabel labelDataDoPagamento;
	private JLabel labelTipoDoPagamento;
	private JLabel labelTipoDoPagamentoInfo;
	private JLabel labelCategoria;
	private JTextField textFieldDescricao;
	private JTextField textFieldValorDespesa;
	private JTextField textFieldDataDaDespesa;
	private JTextField textFieldDataDoPagamento;
	private JTextField textFieldTipoDoPagamentoInfo;
	private JComboBox<String> jComboBoxTipoDoPagamento; //USAR ENUM /////////////////////////
	private JComboBox<String> jComboBoxCategoria;

	public JanelaCriarDespesa(AbasCategoria abasCategoria) {
		setTitle(TITULO_JANELA);
		
		trataEventosDespesa = new TEJanelaCriarDespesa(this);
		this.abasCategoria = abasCategoria;
		
		iniciaElementos();
		
		add(criaPainelPrincipal());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}
	
	private JPanel criaPainelPrincipal(){
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		
		painelPrincipal.add(criaPainelTitulo(), BorderLayout.NORTH);
		painelPrincipal.add(criaPainelCampos(), BorderLayout.SOUTH);
		
		painelPrincipal.setBackground(Color.PINK);
		painelPrincipal.setVisible(true);
		
		return painelPrincipal;
	}
	
	
	private JPanel criaPainelTitulo(){
		final int TAM_X = this.getWidth();
		final int TAM_Y = 70;
		
		painelTitulo.setLayout(new BorderLayout(0,0));
		
		labelTitulo.setText("Criar Despesa");
		labelTitulo.setFont(new Font("serif", Font.PLAIN, 25));
		
		labelSubTitulo.setText("Campos com * são obrigatórios.");
		
		painelTitulo.add(labelTitulo, BorderLayout.WEST);
		painelTitulo.add(labelSubTitulo, BorderLayout.SOUTH);
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setBackground(Color.GREEN);
		painelTitulo.setVisible(true);
		
		return painelTitulo;
	}
	
	
	private JPanel criaPainelCampos(){
		final int TAM_X = this.getWidth();
		final int TAM_Y = 400;
		
		painelCampos.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		labelErroCampo.setText(" ");
		labelDescricao.setText("* Nome da Despesa:");
		labelValorDespesa.setText("Valor:");
		labelDataDaDespesa.setText("* Data da Despesa:");
		labelDataDoPagamento.setText("* Data do Pagamento:");
		labelTipoDoPagamento.setText("* Tipo do Pagamento:");
		labelTipoDoPagamentoInfo.setText(" ");
		labelCategoria.setText("Categoria:");
		textFieldDescricao.setPreferredSize(new Dimension(100,25));
		textFieldValorDespesa.setPreferredSize(new Dimension(100,25));
		textFieldDataDaDespesa.setPreferredSize(new Dimension(100,25));
		textFieldDataDoPagamento.setPreferredSize(new Dimension(100,25));
		textFieldTipoDoPagamentoInfo.setPreferredSize(new Dimension(100,25));
		
		for(int indice = 0; indice < 4; indice++){
			jComboBoxTipoDoPagamento.addItem( "Pagamento " + indice ); //USAR O ENUM DOS TIPOS DE PAGAMENTO ///////////////////////////
		}
		jComboBoxTipoDoPagamento.setMaximumRowCount(5);
		jComboBoxTipoDoPagamento.setSelectedIndex(0);
		jComboBoxTipoDoPagamento.setPreferredSize(new Dimension(100,25));
		jComboBoxTipoDoPagamento.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					System.out.println(e.getItem()); //COMPARAR COM O ENUM ///////////////////////////////////
			}
		});
		
		for(int indice = 0; indice < abasCategoria.getTabCount(); indice++){
			jComboBoxCategoria.addItem( abasCategoria.getTitleAt(indice) );
		}
		jComboBoxCategoria.setMaximumRowCount(5);
		jComboBoxCategoria.setSelectedIndex(abasCategoria.getSelectedIndex());
		jComboBoxCategoria.setPreferredSize(new Dimension(100,25));
		
		botaoCriar.setText("Criar");
		botaoCriar.addActionListener(trataEventosDespesa);
		
		botaoCancelar.setText("Cancelar");
		botaoCancelar.addActionListener(trataEventosDespesa);
		
		constraints.insets = new Insets(-5, -100, 30, 0);
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		constraints.ipadx = 150;
		constraints.weightx = 1;
		painelCampos.add(labelErroCampo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.ipadx = 0;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 10, 25, 0);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelCategoria, constraints);
		constraints.gridx = 1;
		painelCampos.add(jComboBoxCategoria, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		painelCampos.add(labelDescricao, constraints);
		constraints.gridx = 1;
		painelCampos.add(textFieldDescricao, constraints);
		constraints.gridx = 2;
		painelCampos.add(labelValorDespesa, constraints);
		constraints.gridx = 3;
		painelCampos.add(textFieldValorDespesa, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		painelCampos.add(labelDataDaDespesa, constraints);
		constraints.gridx = 1;
		painelCampos.add(textFieldDataDaDespesa, constraints);
		constraints.gridx = 2;
		painelCampos.add(labelDataDoPagamento, constraints);
		constraints.gridx = 3;
		painelCampos.add(textFieldDataDoPagamento, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		painelCampos.add(labelTipoDoPagamento, constraints);
		constraints.gridx = 1;
		painelCampos.add(jComboBoxTipoDoPagamento, constraints);
		constraints.gridx = 2;
		painelCampos.add(labelTipoDoPagamentoInfo, constraints);
		constraints.gridx = 3;
		painelCampos.add(textFieldTipoDoPagamentoInfo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(120, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		painelCampos.add(botaoCriar, constraints);
		constraints.gridx = 2;
		painelCampos.add(botaoCancelar, constraints);
		
		painelCampos.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelCampos.setBackground(Color.WHITE);
		painelCampos.setVisible(true);
		return painelCampos;
	}
	
	private void iniciaElementos(){
		painelPrincipal = new JPanel();
		painelCampos = new JPanel();
		painelTitulo = new JPanel();
		botaoCriar = new JButton();
		botaoCancelar = new JButton();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		labelDescricao = new JLabel();
		labelValorDespesa = new JLabel();
		labelDataDaDespesa = new JLabel();
		labelDataDoPagamento = new JLabel();
		labelTipoDoPagamento = new JLabel();
		labelTipoDoPagamentoInfo = new JLabel();
		labelCategoria = new JLabel();
		textFieldDescricao = new JTextField();
		textFieldValorDespesa = new JTextField();
		textFieldDataDaDespesa = new JTextField();
		textFieldDataDoPagamento = new JTextField();
		textFieldTipoDoPagamentoInfo = new JTextField();
		jComboBoxTipoDoPagamento = new JComboBox<String>(); //USAR ENUM ////////////////////////
		jComboBoxCategoria = new JComboBox<String>();
	}
	
	private void liberaElementos(){
		painelPrincipal = null;
		painelCampos = null;
		painelTitulo = null;
		botaoCriar = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		botaoCancelar = null;
		labelDescricao = null;
		labelValorDespesa = null;
		labelDataDaDespesa = null;
		labelDataDoPagamento = null;
		labelTipoDoPagamento = null;
		labelTipoDoPagamentoInfo = null;
		labelCategoria = null;
		textFieldDescricao = null;
		textFieldValorDespesa = null;
		textFieldDataDaDespesa = null;
		textFieldDataDoPagamento = null;
		textFieldTipoDoPagamentoInfo = null;
		jComboBoxTipoDoPagamento = null;
		jComboBoxCategoria = null;
	}
	
	public void finalizaJanelaDespesa(){
		liberaElementos();
		dispose();
	}
	
	public void criarDespesa(){
		if(validaCampos()){
			//Implementar a parte de adicionar no banco ////////////////////////////////////////////////////////
			
			//Se a condição for true, cria a aba e exibe uma janela confirmando a criação.
			/*if( abasCategoria.criarCategoria(getTextFieldDescricao().getText()) ){
				finalizaJanelaDespesa();				
			}
			else{
				new JanelaAviso("Criar categoria", "Já existe uma categoria com esse nome.");
			}*/
		}
	}
	
	private boolean validaCampos(){
		labelErroCampo.setForeground(Color.RED);
		
		String descricao = textFieldDescricao.getText(); 
		if(descricao.equals("")){
			labelErroCampo.setText("O campo \"Nome\" não pode ficar vazio.");
			return false;
		}
		else if(descricao.length() >= 25){
			labelErroCampo.setText("O campo \"Nome\" não pode ter mais que 25 caracteres.");
			return false;
		}
		else if(!descricao.matches("[a-zA-Z]{1}.*")){
			labelErroCampo.setText("O campo \"Nome\" tem que iniciar com uma letra");
			return false;
		}
		else if(!descricao.matches("([a-zA-z]|[0-9]|_|-){1,30}")){
			labelErroCampo.setText("O campo \"Nome\" só aceita letras, numeros, \"_\" e \"-\"");
			return false;
		}
		
		String meta = textFieldValorDespesa.getText();
		if(!meta.matches("[0-9]*")){
			labelErroCampo.setText("O campo \"Meta\" só aceita numeros e \".\"");
			return false;
		}
		
		return true;
	}
	
	
	public JButton getBotaoCriar() {
		return botaoCriar;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public JTextField getTextFieldDescricao() {
		return textFieldDescricao;
	}

	public JTextField gettextFieldValorDespesa() {
		return textFieldValorDespesa;
	}
	
}
