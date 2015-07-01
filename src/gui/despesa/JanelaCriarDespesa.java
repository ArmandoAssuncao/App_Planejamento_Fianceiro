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
import validacoes.ValidarDados;
import classes.Despesa;

import com.toedter.calendar.JDateChooser;

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
	private JLabel labelNumeroDeParcelas;
	private JLabel labelNumeroDoCheque;
	private JLabel labelCategoria;
	private JTextField textFieldDescricao;
	private JTextField textFieldValorDespesa;
	private JDateChooser jDateChooserDataDaDespesa;
	private JDateChooser jDateChooserDataDoPagamento;
	private JTextField textFieldNumeroDeParcelas;
	private JTextField textFieldNumeroDoCheque;
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
		labelNumeroDeParcelas.setText("* Número de parcelas:");
		labelNumeroDoCheque.setText("* Número do Cheque:");
		labelCategoria.setText("Categoria:");
		textFieldDescricao.setPreferredSize(new Dimension(100,25));
		textFieldValorDespesa.setPreferredSize(new Dimension(100,25));
		jDateChooserDataDaDespesa.setPreferredSize(new Dimension(100,25));
		jDateChooserDataDoPagamento.setPreferredSize(new Dimension(100,25));
		textFieldNumeroDeParcelas.setPreferredSize(new Dimension(100,25));
		textFieldNumeroDoCheque.setPreferredSize(new Dimension(100,25));
		labelNumeroDeParcelas.setEnabled(false);
		labelNumeroDoCheque.setEnabled(false);
		textFieldNumeroDeParcelas.setEnabled(false);
		textFieldNumeroDoCheque.setEnabled(false);
		
		for(FormaPagamento tipoPagamento : FormaPagamento.values()){
			jComboBoxTipoDoPagamento.addItem(tipoPagamento.getFormaPagamento());
		}
		jComboBoxTipoDoPagamento.setMaximumRowCount(5);
		jComboBoxTipoDoPagamento.setSelectedIndex(0);
		jComboBoxTipoDoPagamento.setPreferredSize(new Dimension(100,25));
		jComboBoxTipoDoPagamento.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					if( e.getItem().equals(FormaPagamento.PRAZO.getFormaPagamento()) ){
						labelNumeroDeParcelas.setEnabled(true);
						textFieldNumeroDeParcelas.setEnabled(true);
						labelNumeroDoCheque.setEnabled(false);
						textFieldNumeroDoCheque.setEnabled(false);
					}
					else if( e.getItem().equals(FormaPagamento.CHEQUE.getFormaPagamento()) ){
						labelNumeroDeParcelas.setEnabled(true);
						textFieldNumeroDeParcelas.setEnabled(true);
						labelNumeroDoCheque.setEnabled(true);
						textFieldNumeroDoCheque.setEnabled(true);
					}
					else{
						labelNumeroDeParcelas.setEnabled(false);
						labelNumeroDoCheque.setEnabled(false);
						textFieldNumeroDeParcelas.setEnabled(false);
						textFieldNumeroDoCheque.setEnabled(false);
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
		painelCampos.add(jDateChooserDataDaDespesa, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDataDoPagamento, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(jDateChooserDataDoPagamento, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelTipoDoPagamento, constraints);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(jComboBoxTipoDoPagamento, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelNumeroDeParcelas, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldNumeroDeParcelas, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelNumeroDoCheque, constraints);
		constraints.gridx = 3;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldNumeroDoCheque, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.insets = new Insets(60, 0, 0, 0);
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
		labelNumeroDeParcelas = new JLabel();
		labelNumeroDoCheque = new JLabel();
		labelCategoria = new JLabel();
		textFieldDescricao = new JTextField();
		textFieldValorDespesa = new JTextField();
		jDateChooserDataDaDespesa = new JDateChooser();
		jDateChooserDataDoPagamento = new JDateChooser();
		textFieldNumeroDeParcelas = new JTextField();
		textFieldNumeroDoCheque = new JTextField();
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
		labelNumeroDeParcelas = null;
		labelNumeroDoCheque = null;
		labelCategoria = null;
		textFieldDescricao = null;
		textFieldValorDespesa = null;
		jDateChooserDataDaDespesa = null;
		jDateChooserDataDoPagamento = null;
		textFieldNumeroDeParcelas = null;
		textFieldNumeroDoCheque = null;
		jComboBoxTipoDoPagamento = null;
		jComboBoxCategoria = null;
	}
	
	public void finalizaJanelaDespesa(){
		liberaElementos();
		dispose();
	}
	
	public boolean validaCampos(){
		labelErroCampo.setForeground(Color.RED);
		
		//valida o campo descricao
		String descricao = textFieldDescricao.getText();
		if(!ValidarDados.validarVazio(descricao)){
			labelErroCampo.setText("O campo \"Nome\" não pode ficar vazio.");
			textFieldDescricao.requestFocusInWindow();
			return false;
		}
		else if(!ValidarDados.validarTamanho(descricao, 25)){
			labelErroCampo.setText("O campo \"Nome\" não pode ter mais que 25 caracteres.");
			textFieldDescricao.requestFocusInWindow();
			return false;
		}

		//valida o campo valor
		String valor = textFieldValorDespesa.getText();
		if(!ValidarDados.validarTamanho(valor, 10)){
			labelErroCampo.setText("O campo \"Valor\" não pode ter mais que 10 caracteres.");
			textFieldValorDespesa.requestFocusInWindow();
			return false;
		}
		else if(!ValidarDados.validarNumeroDouble(valor)){
			labelErroCampo.setText("O campo \"Valor\" só aceita números. Se precisar, use um ponto(\".\") como separador.");
			textFieldValorDespesa.requestFocusInWindow();
			return false;
		}
		
		//valida o campo data da Despesa
		if(jDateChooserDataDaDespesa.getCalendar() == null){
			labelErroCampo.setText("Campo \"Data da Despesa\" incorreto, insira a data no formato DD/MM/AAAA");
			jDateChooserDataDaDespesa.requestFocusInWindow();
			return false;
		}
		
		//valida o campo data do Pagamento
		if(jDateChooserDataDoPagamento.getCalendar() == null){
			labelErroCampo.setText("Campo \"Data do Pagamento\" incorreto, insira a data no formato DD/MM/AAAA");
			jDateChooserDataDoPagamento.grabFocus();
			jDateChooserDataDoPagamento.requestFocusInWindow();
			return false;
		}
		
		//valida o campo Parcelas
		if(textFieldNumeroDeParcelas.isEnabled()){
			String parcelas = textFieldNumeroDeParcelas.getText();
			if(!ValidarDados.validarVazio(parcelas)){
				labelErroCampo.setText("O campo \"Numero de parcelas\" não pode ficar vazio.");
				return false;
			}
			else if(!ValidarDados.validarTamanho(parcelas, 5)){
				labelErroCampo.setText("O campo \"Numero do cheque\" não pode ter mais que 5 caracteres.");
				return false;
			}
			else if(!parcelas.matches("[1,9]([0-9]){0,4}")){
				labelErroCampo.setText("O campo \"Numero de Parcelas\" só aceita números iniciando de 1.");
				return false;
			}
		}
		
		//valida o campo Numero do cheque
		if(textFieldNumeroDoCheque.isEnabled()){
			String cheque = textFieldNumeroDoCheque.getText();
			if(!ValidarDados.validarVazio(cheque)){
				labelErroCampo.setText("O campo \"Numero do cheque\" não pode ficar vazio.");
				return false;
			}
			else if(!ValidarDados.validarTamanho(cheque, 30)){
				labelErroCampo.setText("O campo \"Numero do cheque\" não pode ter mais que 30 caracteres.");
				return false;
			}
			if(!cheque.matches("[0-9]{1,30}")){
				labelErroCampo.setText("O campo \"Numero do cheque\" só aceita números");
				return false;
			}
		}
		
		return true;
	}
	
	public Despesa retornaDespesa(){
		String categoria = jComboBoxCategoria.getItemAt(jComboBoxCategoria.getSelectedIndex());
		String descricao = textFieldDescricao.getText();
		String valor = textFieldValorDespesa.getText();
		Calendar dataDaDespesa = jDateChooserDataDaDespesa.getCalendar();
		Calendar dataDoPagamento = jDateChooserDataDoPagamento.getCalendar();
		String tipoDoPagamento = jComboBoxTipoDoPagamento.getItemAt(jComboBoxTipoDoPagamento.getSelectedIndex());
		String parcelas = textFieldNumeroDeParcelas.getText();
		String numeroDoCheque = textFieldNumeroDoCheque.getText();
		if(parcelas.equals("")) parcelas = "1";
		
		System.out.println(Converte.calendarToString(dataDoPagamento));
		
		Despesa despesa = new Despesa();
		despesa.setDescricao(descricao);
		despesa.setDataDespesa(dataDaDespesa);
		despesa.setDataPagamento(dataDoPagamento);
		despesa.setNumeroCheque(numeroDoCheque);
		
		try{
			if(!valor.equals(""))
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
