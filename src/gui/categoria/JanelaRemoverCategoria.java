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

import eventos.categoria.TEJanelaRemoverCategoria;
import gui.JanelaAviso;
import gui.painelDespesas.AbasCategoria;

public class JanelaRemoverCategoria extends JDialog {
	private final String TITULO_JANELA= "Remover Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private TEJanelaRemoverCategoria trataEventosCategoria;
	private AbasCategoria abasCategoria;
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
	
	public JanelaRemoverCategoria(AbasCategoria abasCategoria) {
		setTitle(TITULO_JANELA);
		
		trataEventosCategoria = new TEJanelaRemoverCategoria(this);
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
		
		labelSubTitulo.setText("Campos com * s�o obrigat�rios.");
		
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
		labelDescricaoValor.setText( abasCategoria.getTitleAt(abasCategoria.getSelectedIndex()) );
		labelMeta.setText("Meta da Categoria:");
		labelMetaValor.setText("????"); //PEGAR O VALOR DO BANCO TALVEZ ////////////////////
		
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
	
	public void finalizaJanelaCategoria(){
		liberaElementos();
		dispose();
	}
	
	public void removerCategoria(){
		//Se a condi��o for true, exibe uma janela de confirma��o final
		if( abasCategoria.removerCategoria(getlabelDescricaoValor().getText()) ){
			//Implementar a parte de adicionar no banco ////////////////////////////////////////////////////////
			
			finalizaJanelaCategoria();
		}
		else{
			new JanelaAviso("Remover categoria", "N�o existe uma categoria com esse nome.");
			finalizaJanelaCategoria();
		}
	}

	public JButton getBotaoRemover() {
		return botaoRemover;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public JLabel getlabelDescricaoValor() {
		return labelDescricaoValor;
	}

	public JLabel getLabelMetaValor() {
		return labelMetaValor;
	}
	
}
