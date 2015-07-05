package gui.renda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Renda;
import classes.RendaMensal;

import com.toedter.calendar.JDateChooser;

import eventos.renda.TEJanelaCriarRenda;
import funcoes.ValidarDados;
import gui.painelRenda.IgPainelRenda;

/**
 * Cria uma GUI para a obtenção dos atributos de uma <code>Renda</code>. 
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaCriarRenda extends JDialog{
	private final String TITULO_JANELA= "Nova Renda";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaCriarRenda trataEventosRenda;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoCriar;
	private JButton botaoCancelar;
	private JLabel labelDescricao;
	private JLabel labelValor;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JTextField textFieldDescricao;
	private JTextField textFieldValor;
	private JDateChooser dataJDateChooser;
	private JLabel labelData;

	/**
	 * Cria uma instância do <code>JDialog</code> 
	 * @param igPainelRenda <code>JPanel</code> com parte do conteúdo da janela. //TODO revisar comentario
	 */
	public JanelaCriarRenda(IgPainelRenda igPainelRenda) {
		setTitle(TITULO_JANELA);
		
		trataEventosRenda = new TEJanelaCriarRenda(this, igPainelRenda);
		
		iniciaElementos();
		
		add(criaPainelPrincipal());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setBackground(Color.WHITE);
		setLocationRelativeTo(igPainelRenda);
		setResizable(false);
		setModal(true);
		setVisible(true);
	}//construtor
	
	/**
	 * Cria o painel principal do <code>JDialog</code>.
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelPrincipal(){
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		
		painelPrincipal.add(criaPainelTitulo(), BorderLayout.NORTH);
		painelPrincipal.add(criaPainelCampos(), BorderLayout.SOUTH);
		
		painelPrincipal.setBackground(Color.WHITE);
		painelPrincipal.setVisible(true);
		
		return painelPrincipal;
	}
	
	/**
	 * Criao o painel de título do <code>JDialog</code>
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelTitulo(){
		final int TAM_X = this.getWidth();
		final int TAM_Y = 70;
		
		painelTitulo.setLayout(new BorderLayout(0,0));
		
		labelTitulo.setText("Nova Renda");
		labelTitulo.setFont(new Font("serif", Font.PLAIN, 25));
		
		labelSubTitulo.setText("Campos com * são obrigatórios");
		
		painelTitulo.add(labelTitulo, BorderLayout.WEST);
		painelTitulo.add(labelSubTitulo, BorderLayout.SOUTH);
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setBackground(Color.GREEN); 
		painelTitulo.setVisible(true);
		
		return painelTitulo;
	}
	
	/**
	 * Cria o painel para armazenar os campos do <code>JDialog</code>
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelCampos(){
		final int TAM_X = this.getWidth();
		final int TAM_Y = 400;
		
		painelCampos.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		labelErroCampo.setText(" ");
		labelDescricao.setText("* Nome da Renda:");
		labelValor.setText("* Valor:");
		labelData.setText("* Data de recebimento: ");
		textFieldDescricao.setPreferredSize(new Dimension(120,25));
		textFieldValor.setPreferredSize(new Dimension(120,25));
		
		botaoCriar.setText("Criar");
		botaoCriar.addActionListener(trataEventosRenda);
		
		botaoCancelar.setText("Cancelar");
		botaoCancelar.addActionListener(trataEventosRenda);
		
		//constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(-5, -100, 30, 0);
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		constraints.ipadx = 150;
		constraints.weightx = 1;
		painelCampos.add(labelErroCampo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.ipadx = 0;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 120, 30, -110);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDescricao, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldDescricao, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelValor, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldValor, constraints);
		
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelData, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(dataJDateChooser, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(150, 120, 0, -110);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(botaoCriar, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(botaoCancelar, constraints);
		
		
		
		painelCampos.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelCampos.setBackground(Color.WHITE);
		painelCampos.setVisible(true);
		return painelCampos;
	}


	/**
	 * Inicia as variáveis de instância do <code>JDialog</code>
	 */
	private void iniciaElementos(){
		painelPrincipal = new JPanel();
		painelCampos = new JPanel();
		painelTitulo = new JPanel();
		botaoCriar = new JButton();
		botaoCancelar = new JButton();
		labelDescricao = new JLabel();
		labelValor = new JLabel();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		textFieldDescricao = new JTextField();
		textFieldValor = new JTextField();
		dataJDateChooser = new JDateChooser();
		labelData = new JLabel();
	}
	
	/**
	 * Libera os recursos alocados para as variáveis de instância da classe
	 */
	private void liberaElementos(){
		painelPrincipal = null;
		painelCampos = null;
		painelTitulo = null;
		botaoCriar = null;
		botaoCancelar = null;
		labelDescricao = null;
		labelValor = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		textFieldDescricao = null;
		textFieldValor = null;
		dataJDateChooser = null;
	}
	
	/**
	 * Libera os recursos alocados para a janela.
	 */
	public void finalizaJanelaRenda(){
		liberaElementos();
		dispose();
	}
	
	/**
	 * Valida os campos digitados pelo usuário.
	 * @return <code>true</code> se os dados estão corretos, <code>fase</code> caso contrário
	 */
	public boolean validaCampos(){
		labelErroCampo.setForeground(Color.RED);
		
		//valida o campo descricao
		String descricao = textFieldDescricao.getText();
		System.out.println(descricao);
		if(!ValidarDados.validarVazio(descricao)){
			labelErroCampo.setText("O campo \"Nome\" não pode ficar vazio.");
			return false;
		}
		else if(!ValidarDados.validarTamanho(descricao, 25)){
			labelErroCampo.setText("O campo \"Nome\" não pode ter mais que 25 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarString(descricao)){
			labelErroCampo.setText("O campo \"Nome\" só aceita letras, numeros, \"_\" e \"-\"");
			return false;
		}

		//valida o campo valor
		String valor = textFieldValor.getText();
		if(!ValidarDados.validarVazio(valor)){
			labelErroCampo.setText("O campo \"Valor\" não pode ficar vazio.");
			return false;
		}else if(!ValidarDados.validarTamanho(valor, 5)){
			labelErroCampo.setText("O campo \"Valor\" não pode ter mais que 5 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarNumeroDouble(valor)){
			labelErroCampo.setText("O campo \"Meta\" só aceita números. Se precisar, use um ponto(\".\") como separador.");
			return false;
		}
		
		//valida o campo dataJDateChooser
		Date data = dataJDateChooser.getDate();
		if(data == null){
				labelErroCampo.setText("Campo \"Data\" preenchido incorretamente.");
				return false;
		}
		return true;
	}
	
	/**
	 * Cria e retorna um novo objeto Renda com valores inseridos pelo usuário.
	 * @return novo objeto Renda
	 */
	public Renda retornaRenda(){
		RendaMensal rendaMensal = new RendaMensal();
		rendaMensal.setValor(Double.parseDouble(textFieldValor.getText()));
		rendaMensal.setDataRenda(dataJDateChooser.getCalendar());

		Renda renda = new Renda();
		renda.adicionarRendaMensal(rendaMensal);
		renda.setDescricao(textFieldDescricao.getText());
		
		return renda;
	}

	// Getters e setters
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão criar.
	 */
	public JButton getBotaoCriar() {
		return botaoCriar;
	}

	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão cancelar.
	 */
	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	/**
	 *  Retorna uma referência de um <code>JTextField</code>.
	 * @return o campo de texto descrição
	 */
	public JTextField getTextFieldDescricao() {
		return textFieldDescricao;
	}

	/**
	 *  Retorna uma referência de um <code>JTextField</code>.
	 * @return o campo de texto valor
	 */
	public JTextField getTextFieldValor() {
		return textFieldValor;
	}

	/**
	 *  Retorna uma referência de um <code>JDateChooser</code>.
	 * @return o JDateChooser.
	 */
	public JDateChooser getDataJDateChooser() {
		return dataJDateChooser;
	}	
}