package gui.categoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Categoria;
import classes.MetaMensal;
import eventos.categoria.TEJanelaCriarCategoria;
import funcoes.Converte;
import funcoes.ValidarDados;
import gui.painelDespesas.IgPainelDespesas;

/**
 * Cria uma GUI para a obtenção dos atributos de uma <code>Categoria</code>. 
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaCriarCategoria extends JDialog{
	private static final long serialVersionUID = -541384016797852478L;
	
	private final String TITULO_JANELA= "Nova Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaCriarCategoria trataEventosCategoria;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoCriar;
	private JButton botaoCancelar;
	private JLabel labelDescricao;
	private JLabel labelMeta;
	private JLabel labelMetaProcentagem;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JTextField textFieldDescricao;
	private JTextField textFieldMeta;
	private JTextField textFieldMetaPorcentagem;

	/**
	 * Cria uma instância do <code>JDialog</code> 
	 * @param igPainelDespesas <code>JPanel</code> com parte do conteúdo da janela. 
	 *///TODO revisar comentario
	public JanelaCriarCategoria(IgPainelDespesas igPainelDespesas) {
		setTitle(TITULO_JANELA);
		
		trataEventosCategoria = new TEJanelaCriarCategoria(this, igPainelDespesas);
		
		iniciaElementos();
		
		add(criaPainelPrincipal());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setBackground(Color.WHITE);
		setLocationRelativeTo(igPainelDespesas);
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
	 * Cria o painel de título do <code>JDialog</code>
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelTitulo(){
		final int TAM_X = 0;
		final int TAM_Y = 70;
		
		painelTitulo.setLayout(new BorderLayout(0,0));
		
		labelTitulo.setText("Nova Categoria");
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
		labelDescricao.setText("* Nome da Categoria:");
		labelMeta.setText("Meta da Categoria:");
		labelMetaProcentagem.setText("Alerta aos(%):");
		textFieldDescricao.setPreferredSize(new Dimension(120,25));
		textFieldMeta.setPreferredSize(new Dimension(120,25));
		textFieldMetaPorcentagem.setPreferredSize(new Dimension(120,25));
		
		botaoCriar.setText("Criar");
		botaoCriar.addActionListener(trataEventosCategoria);
		
		botaoCancelar.setText("Cancelar");
		botaoCancelar.addActionListener(trataEventosCategoria);
		
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
		painelCampos.add(labelMeta, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldMeta, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelMetaProcentagem, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(textFieldMetaPorcentagem, constraints);
		
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
		labelMeta = new JLabel();
		labelMetaProcentagem = new JLabel();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		textFieldDescricao = new JTextField();
		textFieldMeta = new JTextField();
		textFieldMetaPorcentagem = new JTextField();
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
		labelMeta = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		textFieldDescricao = null;
		textFieldMeta = null;
	}
	
	/**
	 * Libera os recursos alocados para a janela.
	 */
	public void finalizaJanelaCategoria(){
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

		//valida o campo meta
		String meta = textFieldMeta.getText();
		if(!ValidarDados.validarTamanho(meta, 5)){
			labelErroCampo.setText("O campo \"Meta\" não pode ter mais que 5 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarNumeroDouble(meta)){
			labelErroCampo.setText("O campo \"Meta\" só aceita números. Se precisar, use um ponto(\".\") como separador.");
			return false;
		}
		
		//valida o campo meta porcentagem
		String metaPorcentagem = textFieldMetaPorcentagem.getText();
		if(!ValidarDados.validarTamanho(metaPorcentagem, 5)){
			labelErroCampo.setText("O campo \"Alerta\" não pode ter mais que 5 caracteres.");
			return false;
		}
		else if(!ValidarDados.validarNumeroDouble(metaPorcentagem)){
			labelErroCampo.setText("O campo \"Alerta\" só aceita números. Se precisar, use um ponto(\".\") como separador.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Cria e retorna um novo objeto Categoria com valores padrão.
	 * @return novo objeto Categoria
	 */
	public Categoria retornaCategoria(){
		MetaMensal metaMensal = new MetaMensal();
		
		//Atribui o dia 01 do mes e ano atual
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int mesAtual = Calendar.getInstance().get(Calendar.MONTH)+1;
		String data = String.format("%02d/%02d/%04d", 1, mesAtual, anoAtual);
		
		metaMensal.setMesAnoMeta( Converte.stringToCalendar(data) );//TODO
		
		try{
			if(!textFieldMeta.getText().equals(""))
				metaMensal.setValor( Double.parseDouble(textFieldMeta.getText()) );
			
			if(textFieldMetaPorcentagem.getText().equals(""))
				metaMensal.setAlerta(70);
			else
				metaMensal.setAlerta(Double.parseDouble(textFieldMetaPorcentagem.getText()));
			
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		Categoria categoria = new Categoria();
		categoria.setDescricao( getTextFieldDescricao().getText() );
		categoria.setMetaMensal(metaMensal);
		
		return categoria;
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
	 * @return o campo de texto descrição.
	 */
	public JTextField getTextFieldDescricao() {
		return textFieldDescricao;
	}

	/**
	 *  Retorna uma referência de um <code>JTextField</code>.
	 * @return o campo de texto meta.
	 */
	public JTextField getTextFieldMeta() {
		return textFieldMeta;
	}	
}