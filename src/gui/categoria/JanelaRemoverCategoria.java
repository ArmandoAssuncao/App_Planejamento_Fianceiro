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

import classes.Categoria;
import eventos.categoria.TEJanelaRemoverCategoria;
import gui.painelDespesas.IgPainelDespesas;

/**
 * Cria uma GUI para a remoção de uma <code>Categoria</code>. 
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class JanelaRemoverCategoria extends JDialog {
	private final String TITULO_JANELA= "Remover Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaRemoverCategoria trataEventosCategoria;
	IgPainelDespesas igPainelDespesas;
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoRemover;
	private JButton botaoCancelar;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JLabel labelErroCampo;
	private JLabel labelDescricao;
	private JLabel labelDescricaoValor;
	private JLabel labelMeta;
	private JLabel labelMetaValor;
	
	/**
	 * Cria uma instância do <code>JDialog</code> 
	 * @param igPainelDespesas <code>JPanel</code> com parte do conteúdo da janela. 
	 *///TODO revisar comentario
	public JanelaRemoverCategoria(IgPainelDespesas igPainelDespesas) {
		setTitle(TITULO_JANELA);
		
		trataEventosCategoria = new TEJanelaRemoverCategoria(this, igPainelDespesas);
		this.igPainelDespesas = igPainelDespesas;
		
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
	
	/**
	 * Cria o painel principal do <code>JDialog</code>.
	 * @return retorna o painel criado.
	 */
	private JPanel criaPainelPrincipal(){
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		
		painelPrincipal.add(criaPainelTitulo(), BorderLayout.NORTH);
		painelPrincipal.add(criaPainelCampos(), BorderLayout.SOUTH);
		
		painelPrincipal.setBackground(Color.PINK);
		painelPrincipal.setVisible(true);
		
		return painelPrincipal;
	}
	
	/**
	 * Cria o painel de título do <code>JDialog</code>
	 * @return retorna o painel criado.
	 */
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
		labelDescricaoValor.setText( igPainelDespesas.getDescricaoCategoria() );
		labelMeta.setText("Meta da Categoria:");
		labelMetaValor.setText( String.valueOf(igPainelDespesas.getMetaCategoriaValor()) );
		
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
		constraints.insets = new Insets(0, 130, 10, -120);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelDescricao, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(labelDescricaoValor, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(labelMeta, constraints);
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.LINE_START;
		painelCampos.add(labelMetaValor, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(250, 130, 0, -120);
		constraints.anchor = GridBagConstraints.LINE_END;
		painelCampos.add(botaoRemover, constraints);
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
		botaoRemover = new JButton();
		botaoCancelar = new JButton();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		labelErroCampo = new JLabel();
		labelDescricao = new JLabel();
		labelDescricaoValor = new JLabel();
		labelMeta = new JLabel();
		labelMetaValor = new JLabel();
	}
	
	/**
	 * Libera os recursos alocados para as variáveis de instância da classe
	 */
	private void liberaElementos(){
		painelPrincipal = null;
		painelCampos = null;
		painelTitulo = null;
		botaoRemover = null;
		botaoCancelar = null;
		labelTitulo = null;
		labelSubTitulo = null;
		labelErroCampo = null;
		labelDescricao = null;
		labelDescricaoValor = null;
		labelMeta = null;
		labelMetaValor = null;
	}
	
	/**
	 * Libera os recursos alocados para a janela.
	 */
	public void finalizaJanelaCategoria(){
		liberaElementos();
		dispose();
	}

	/**
	 *  Retorna a referência de um <code>JButton</code>.
	 * @return botão remover
	 */
	public JButton getBotaoRemover() {
		return botaoRemover;
	}
	
	/**
	 *  Retorna a referência de um <code>JButton</code>.
	 * @return botão cancelar
	 */
	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	/**
	 *  Retorna a referência de um <code>JLabel</code>.
	 * @return label descrição
	 */
	public JLabel getlabelDescricaoValor() {
		return labelDescricaoValor;
	}

	/**
	 *  Retorna a referência de um <code>JLabel</code>.
	 * @return label meta
	 */
	public JLabel getLabelMetaValor() {
		return labelMetaValor;
	}
	
	/**
	 * Cria e retorna um novo objeto Categoria
	 * @return novo objeto Categoria
	 */
	//TODO por que a classe remover categoria tem um metodo retorna categoria?
	public Categoria retornaCategoria(){
		Categoria categoria = new Categoria();
		categoria.setDescricao( labelDescricaoValor.getText() );
		
		return categoria;
	}
}
