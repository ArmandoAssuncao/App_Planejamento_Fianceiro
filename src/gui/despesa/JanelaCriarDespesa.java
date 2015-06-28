package gui.despesa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import persistencia.CategoriaDAO;
import persistencia.FormaPagamentoDAO;
import classes.Categoria;
import classes.Despesa;
import classes.MetaMensal;
import validacoes.ValidarDados;
import enumeracoes.FormaPagamento;
import eventos.despesa.TEJanelaCriarDespesa;
import funcoes.Converte;
import gui.painelDespesas.AbasCategoria;
import gui.painelDespesas.IgPainelDespesas;

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
	private JComboBox<String> jComboBoxTipoDoPagamento;
	private JComboBox<String> jComboBoxCategoria;

	public JanelaCriarDespesa(IgPainelDespesas painelDespesas, AbasCategoria abasCategoria) {
		setTitle(TITULO_JANELA);
		
		trataEventosDespesa = new TEJanelaCriarDespesa(this, painelDespesas);
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
		
		labelSubTitulo.setText("Campos com * são obrigátorios.");
		
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
		labelTipoDoPagamentoInfo.setVisible(false);
		labelCategoria.setText("Categoria:");
		textFieldDescricao.setPreferredSize(new Dimension(100,25));
		textFieldValorDespesa.setPreferredSize(new Dimension(100,25));
		textFieldDataDaDespesa.setPreferredSize(new Dimension(100,25));
		textFieldDataDoPagamento.setPreferredSize(new Dimension(100,25));
		textFieldTipoDoPagamentoInfo.setPreferredSize(new Dimension(100,25));
		textFieldTipoDoPagamentoInfo.setVisible(false);
		
		for(FormaPagamento tipoPagamento : FormaPagamento.values()){
			jComboBoxTipoDoPagamento.addItem(tipoPagamento.getFormaPagamento()); //USAR O ENUM DOS TIPOS DE PAGAMENTO ///////////////////////////
		}
		jComboBoxTipoDoPagamento.setMaximumRowCount(5);
		jComboBoxTipoDoPagamento.setSelectedIndex(0);
		jComboBoxTipoDoPagamento.setPreferredSize(new Dimension(100,25));
		jComboBoxTipoDoPagamento.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					if( e.getItem().equals(FormaPagamento.PRAZO.getFormaPagamento()) ){
						labelTipoDoPagamentoInfo.setText("* Número de parcelas:");
						labelTipoDoPagamentoInfo.setVisible(true);
						textFieldTipoDoPagamentoInfo.setVisible(true);
					}
					else if( e.getItem().equals(FormaPagamento.CHEQUE.getFormaPagamento()) ){
						labelTipoDoPagamentoInfo.setText("* Número do cheque:");
						labelTipoDoPagamentoInfo.setVisible(true);
						textFieldTipoDoPagamentoInfo.setVisible(true);
					}
					else{
						labelTipoDoPagamentoInfo.setVisible(false);
						textFieldTipoDoPagamentoInfo.setVisible(false);
					}
				}
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
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(jComboBoxCategoria, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDescricao, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldDescricao, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelValorDespesa, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldValorDespesa, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDataDaDespesa, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldDataDaDespesa, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDataDoPagamento, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldDataDoPagamento, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelTipoDoPagamento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(jComboBoxTipoDoPagamento, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelTipoDoPagamentoInfo, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldTipoDoPagamentoInfo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(120, 0, 0, 0);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(botaoCriar, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.CENTER;
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
		jComboBoxTipoDoPagamento = new JComboBox<String>();
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
	
//	public void criarDespesa(){
//		if(validaCampos()){
//			//Implementar a parte de adicionar no banco ////////////////////////////////////////////////////////
//			String categoria = jComboBoxCategoria.getItemAt(jComboBoxCategoria.getSelectedIndex());
//			String descricao = textFieldDescricao.getText();
//			String valor = textFieldValorDespesa.getText();
//			String dataDaDespesa = textFieldDataDaDespesa.getText();
//			String dataDoPagamento = textFieldDataDoPagamento.getText();
//			String tipoDoPagamento = jComboBoxTipoDoPagamento.getItemAt(jComboBoxTipoDoPagamento.getSelectedIndex());
//			String parcelas = "";
//			String numeroDoCheque = "";
//			if( tipoDoPagamento.equals(FormaPagamento.PRAZO.getFormaPagamento()) )
//				parcelas = textFieldTipoDoPagamentoInfo.getText();
//			else if( tipoDoPagamento.equals(FormaPagamento.CHEQUE.getFormaPagamento()) )
//				numeroDoCheque = textFieldTipoDoPagamentoInfo.getText();
//			
//			if(abasCategoria.criarDespesa(categoria, descricao, valor, dataDaDespesa, dataDoPagamento, tipoDoPagamento, parcelas, numeroDoCheque));
//			
//			abasCategoria.setSelectedIndex(jComboBoxCategoria.getSelectedIndex());
//			
//			finalizaJanelaDespesa();
//			
//			//Se a condição for true, cria a aba e exibe uma janela confirmando a criação.
//			/*if( abasCategoria.criarCategoria(getTextFieldDescricao().getText()) ){
//				finalizaJanelaDespesa();				
//			}
//			else{
//				new JanelaAviso("Criar categoria", "J� existe uma categoria com esse nome.");
//			}*/
//		}
//	}
	
	private boolean validaCampos(){
		labelErroCampo.setForeground(Color.RED);
		
		//valida o campo descricao
		String descricao = textFieldDescricao.getText();
		if(!ValidarDados.validarVazio(descricao)){
			labelErroCampo.setText("O campo \"Nome\" não pode ficar vazio.");
			return false;
		}
		else if(!ValidarDados.validarTamanho(descricao, 25)){
			labelErroCampo.setText("O campo \"Nome\" não pode ter mais que 25 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarInicioString(descricao, "[a-zA-Z]")){
			labelErroCampo.setText("O campo \"Nome\" tem que iniciar com uma letra");
			return false;
		}
		else if(!ValidarDados.validarString(descricao, "[a-zA-z0-9_-]")){
			labelErroCampo.setText("O campo \"Nome\" só aceita letras, numeros, \"_\" e \"-\"");
			return false;
		}

		//valida o campo valor
		String valor = textFieldValorDespesa.getText();
		if(!ValidarDados.validarVazio(valor)){
			return true;
		}
		else if(!ValidarDados.validarTamanho(valor, 10)){
			labelErroCampo.setText("O campo \"Valor\" não pode ter mais que 10 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarInicioString(valor, "[0-9]")){
			labelErroCampo.setText("O campo \"Valor\" deve iniciar com um número.");
			return false;
		}
		else if(!ValidarDados.validarFimString(valor, "[0-9]")){
			labelErroCampo.setText("O campo \"Valor\" deve terminar com um número.");
			return false;
		}
		if(!ValidarDados.validarNumeroDouble(valor)){
			labelErroCampo.setText("O campo \"Valor\" só aceita número e um \".\"");
			return false;
		}
		
		return true;
	}
	
	public Despesa retornaDespesa(){
		String categoria = jComboBoxCategoria.getItemAt(jComboBoxCategoria.getSelectedIndex());
		String descricao = textFieldDescricao.getText();
		String valor = textFieldValorDespesa.getText();
		String dataDaDespesa = textFieldDataDaDespesa.getText();
		String dataDoPagamento = textFieldDataDoPagamento.getText();
		String tipoDoPagamento = jComboBoxTipoDoPagamento.getItemAt(jComboBoxTipoDoPagamento.getSelectedIndex());
		String parcelas = textFieldTipoDoPagamentoInfo.getText();
		String numeroDoCheque = textFieldTipoDoPagamentoInfo.getText();
		if(parcelas.equals("")) parcelas = "1";
		
		Despesa despesa = new Despesa();
		despesa.setDescricao(descricao);
		despesa.setDataDespesa(Converte.stringToCalendar(dataDaDespesa));
		despesa.setDataPagamento(Converte.stringToCalendar(dataDoPagamento));
		despesa.setNumeroCheque(numeroDoCheque);
		
		try{
			despesa.setValorDespesa(Double.parseDouble(valor));
			despesa.setNumeroParcelas(Integer.parseInt(parcelas));
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		try {
			despesa.setIdCategoria(new CategoriaDAO().getId(categoria));
			despesa.setIdFormaPagamento(new FormaPagamentoDAO().getId(tipoDoPagamento));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return despesa;
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
