package gui.Categoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eventos.TEJanelaEditarCategoria;
import gui.JanelaAviso;
import gui.painelDespesas.AbasCategoria;

public class JanelaEditarCategoria extends JDialog{
	private final String TITULO_JANELA= "Editar Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaEditarCategoria trataEventosCategoria;
	private AbasCategoria abasCategoria;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoEditar;
	private JButton botaoCancelar;
	private JLabel labelNovaDescricao;
	private JLabel labelNovaMeta;
	private JLabel labelAntigaDescricao;
	private JLabel labelAntigaMeta;
	private JLabel labelAntigaDescricaoValor;
	private JLabel labelAntigaMetaValor;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JTextField textFieldNovaDescricao;
	private JTextField textFieldNovaMeta;

	public JanelaEditarCategoria(AbasCategoria abasCategoria) {
		setTitle(TITULO_JANELA);
		
		trataEventosCategoria = new TEJanelaEditarCategoria(this);
		this.abasCategoria = abasCategoria;
		
		iniciaElementos();
		
		add(criaPainelPrincipal());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setBackground(Color.PINK);
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
		
		labelTitulo.setText("Editar Categoria");
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
		labelNovaDescricao.setText("* Novo Nome da Categoria:");
		labelNovaMeta.setText("Nova Meta da Categoria:");
		labelAntigaDescricao.setText("Antigo Nome da Categoria:");
		labelAntigaDescricaoValor.setText( abasCategoria.getTitleAt(abasCategoria.getSelectedIndex()) );
		labelAntigaMeta.setText("Antiga Meta:");
		labelAntigaMetaValor.setText("????"); //PEGAR O VALOR DO BANCO TALVEZ /////////////////////////////////////////////////////////
		textFieldNovaDescricao.setPreferredSize(new Dimension(120,25));
		textFieldNovaMeta.setPreferredSize(new Dimension(120,25));
		
		botaoEditar.setText("Editar");
		botaoEditar.addActionListener(trataEventosCategoria);
		
		botaoCancelar.setText("Cancelar");
		botaoCancelar.addActionListener(trataEventosCategoria);
		
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
		constraints.insets = new Insets(0, 100, 10, -90);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelAntigaDescricao, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(labelAntigaDescricaoValor, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelAntigaMeta, constraints);
		
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(labelAntigaMetaValor, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.LINE_END;
		/*constraints.gridwidth = 1;
		constraints.ipadx = 0;
		constraints.weightx = 0;
		constraints.insets = new Insets(0, 100, 30, -90);
		constraints.anchor = GridBagConstraints.LINE_END;*/
		painelCampos.add(labelNovaDescricao, constraints);
		
		constraints.gridx = 2;
		painelCampos.add(textFieldNovaDescricao, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		painelCampos.add(labelNovaMeta, constraints);
		
		constraints.gridx = 2;
		painelCampos.add(textFieldNovaMeta, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(180, 100, 0, -90);
		constraints.anchor = GridBagConstraints.CENTER;
		painelCampos.add(botaoEditar, constraints);
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
		botaoEditar = new JButton();
		botaoCancelar = new JButton();
		labelAntigaDescricao = new JLabel();
		labelAntigaMeta = new JLabel();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		textFieldNovaDescricao = new JTextField();
		textFieldNovaMeta = new JTextField();
		labelNovaDescricao = new JLabel();
		labelNovaMeta = new JLabel();
		labelAntigaDescricaoValor = new JLabel();
		labelAntigaMetaValor = new JLabel();
	}
	
	private void liberaElementos(){
		painelPrincipal = null;
		painelCampos = null;
		painelTitulo = null;
		botaoEditar = null;
		botaoCancelar = null;
		labelAntigaDescricao = null;
		labelAntigaMeta = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		textFieldNovaDescricao = null;
		textFieldNovaMeta = null;
		labelNovaDescricao = null;
		labelNovaMeta = null;
		labelAntigaDescricaoValor = null;
		labelAntigaMetaValor = null;
	}
	
	public void finalizaJanelaCategoria(){
		liberaElementos();
		dispose();
	}
	
	public void editarCategoria(){
		if(validaCampos()){
			//Implementar a parte de adicionar no banco ////////////////////////////////////////////////////////
			
			//Se a condição for true, cria a aba e exibe uma janela confirmando a criação.
			if( abasCategoria.editarCategoria(getTextFieldNovaDescricao().getText()) ){
				finalizaJanelaCategoria();
			}
			else{
				new JanelaAviso("Editar categoria", "Já existe uma categoria com esse nome.");
			}
		}
	}
	
	private boolean validaCampos(){
		labelErroCampo.setForeground(Color.RED);
		
		String descricao = textFieldNovaDescricao.getText(); 
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
		
		String meta = textFieldNovaMeta.getText();
		if(!meta.matches("[0-9]*")){
			labelErroCampo.setText("O campo \"Meta\" só aceita numeros");
			return false;
		}
		
		return true;
	}
	
	
	public JButton getBotaoEditar() {
		return botaoEditar;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public JTextField getTextFieldNovaDescricao() {
		return textFieldNovaDescricao;
	}

	public JTextField getTextFieldNovaMeta() {
		return textFieldNovaMeta;
	}
	
}