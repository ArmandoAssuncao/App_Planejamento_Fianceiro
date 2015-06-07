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

public class JanelaCriarCategoria extends JDialog{
	private final String TITULO_JANELA= "Nova Categoria";
	private final int TAM_JANELA_X = 500;
	private final int TAM_JANELA_Y = 500;
	
	private JPanel painelPrincipal;
	private JPanel painelTitulo;
	private JPanel painelCampos;
	
	private JButton botaoCriar;
	private JButton botaoCancelar;
	private JLabel labelDescricao;
	private JLabel labelMeta;
	private JLabel labelTitulo;
	private JLabel labelSubTitulo;
	private JTextField textFieldDescricao;
	private JTextField textFieldMeta;

	public JanelaCriarCategoria() {
		setTitle(TITULO_JANELA);
		
		iniciaElementos();
		
		add(criaPainelPrincipal());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(TAM_JANELA_X, TAM_JANELA_Y);
		setBackground(Color.PINK);
		setLocationRelativeTo(null);
		setResizable(false);
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
		
		labelTitulo.setText("Nova Categoria");
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
		constraints.insets = new Insets(0, 0, 30, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;

		
		botaoCriar.setText("Criar");
		botaoCancelar.setText("Cancelar");
		labelDescricao.setText("* Nome da Categoria:");
		labelMeta.setText("Meta da Categoria:");
		textFieldDescricao.setPreferredSize(new Dimension(150,25));
		textFieldMeta.setPreferredSize(new Dimension(150,25));
		
		
		painelCampos.add(labelDescricao, constraints);
		constraints.gridx = 1;
		painelCampos.add(textFieldDescricao, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		painelCampos.add(labelMeta, constraints);
		constraints.gridx = 1;
		painelCampos.add(textFieldMeta, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(200, 0, 0, 10);
		constraints.anchor = GridBagConstraints.CENTER;
		painelCampos.add(botaoCriar, constraints);
		constraints.gridx = 1;
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
		labelDescricao = new JLabel();
		labelMeta = new JLabel();
		labelTitulo = new JLabel();
		labelSubTitulo = new JLabel();
		textFieldDescricao = new JTextField();
		textFieldMeta = new JTextField();
	}
	
	
}