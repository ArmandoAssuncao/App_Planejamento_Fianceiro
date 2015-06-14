package gui.categoria;

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

import eventos.categoria.NAO_USADA_TEJanelaRemoverCategoria;
import gui.JanelaAviso;
import gui.JanelaDeConfirmacao;
import gui.painelDespesas.AbasCategoria;

public class NAO_USADA_JanelaRemoverCategoria extends JDialog {
	private final String TITULO_JANELA= "Remover Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private NAO_USADA_TEJanelaRemoverCategoria trataEventosCategoria;
	private AbasCategoria abasCategoria;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoRemover;
	private JButton botaoCancelar;
	private JLabel labelDescricao;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JTextField textFieldDescricao;
	
	public NAO_USADA_JanelaRemoverCategoria(AbasCategoria abasCategoria) {
		setTitle(TITULO_JANELA);
		
		trataEventosCategoria = new NAO_USADA_TEJanelaRemoverCategoria(this);
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
		
		labelTitulo.setText("Remover Categoria");
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
		labelDescricao.setText("* Nome da Categoria:");
		textFieldDescricao.setPreferredSize(new Dimension(120,25));
		
		botaoRemover.setText("Remover");
		botaoRemover.addActionListener(trataEventosCategoria);
		
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
		constraints.insets = new Insets(0, 100, 30, -90);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDescricao, constraints);
		
		constraints.gridx = 2;
		painelCampos.add(textFieldDescricao, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(200, 100, 0, -90);
		constraints.anchor = GridBagConstraints.CENTER;
		painelCampos.add(botaoRemover, constraints);
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
		botaoRemover = new JButton();
		botaoCancelar = new JButton();
		labelDescricao = new JLabel();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		textFieldDescricao = new JTextField();
	}
	
	private void liberaElementos(){
		painelPrincipal = null;
		painelCampos = null;
		painelTitulo = null;
		botaoRemover = null;
		botaoCancelar = null;
		labelDescricao = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		textFieldDescricao = null;
	}
	
	public void finalizaJanelaCategoria(){
		liberaElementos();
		dispose();
	}
	
	/*public void removerCategoria(){
		if(validaCampos()){
			//Implementar a parte de adicionar no banco ////////////////////////////////////////////////////////
			
			//Se a condição for true, exibe uma janela de confirmação final
			if( abasCategoria.removerCategoria(getTextFieldDescricao().getText()) ){
				finalizaJanelaCategoria();
			}
			else{
				new JanelaAviso("Remover categoria", "Não existe uma categoria com esse nome.");
			}
		}
	}*/
	
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
		
		return true;
	}

	public JButton getBotaoRemover() {
		return botaoRemover;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public JTextField getTextFieldDescricao() {
		return textFieldDescricao;
	}
	
}
