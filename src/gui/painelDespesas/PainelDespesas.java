package gui.painelDespesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eventos.painelDespesa.TEPainelDespesas;

public class PainelDespesas extends JPanel{
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;
	
	AbasCategoria abasCategoria;
	JPanel painelBotoes;
	JPanel painelTitulo;
	TEPainelDespesas trataEventosDespesas;
	
	JButton botaoNovaCategoria;
	JButton botaoExcluirCategoria;
	JButton botaoEditarCategoria;
	JButton botaoAdicionarDespesa;
	JButton botaoExcluirDespesa;
	JButton botaoEditarDespesa;
	
	public PainelDespesas(Window framePrincipal) {
		setLayout(new BorderLayout(0,5));
		
		trataEventosDespesas = new TEPainelDespesas(this, framePrincipal);
		iniciaElementos();
		
		criaAbaCategoria("Esportes");
		for(int i = 0; i < 14; i++) //APAGAR //////////////////////////////////////////////
			criaAbaCategoria("Educação"+i);
		criaPainelBotoes();
		criaPainelTitulo();
		
		add(painelTitulo, BorderLayout.NORTH);
		add(abasCategoria, BorderLayout.WEST);
		add(painelBotoes, BorderLayout.EAST);
		
		
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.WHITE);
		setVisible(true);
	}

	//cria uma nova aba
	private void criaAbaCategoria(String nomeCategoria){
		abasCategoria.criarCategoria(nomeCategoria);
	}
	
	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 400;
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		//configurações do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0, 0, 25, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Nova Categoria
		String labelBotaoNovaCategoria = "Nova Categoria";
		ImageIcon iconeNovaCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoNovaCategoria.setText(labelBotaoNovaCategoria);
		botaoNovaCategoria.setIcon(iconeNovaCategoria);
		botaoNovaCategoria.setMargin(new Insets(0,-20,0,0));
		botaoNovaCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoNovaCategoria.setBackground(Color.GREEN);
		botaoNovaCategoria.setPreferredSize(new Dimension(150,50));
		botaoNovaCategoria.addActionListener(trataEventosDespesas);
		
		//botao Excluir Categoria
		String labelBotaoExcluirCategoria = "Excluir Categoria";
		ImageIcon iconeExcluirCategoria = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirCategoria.setText(labelBotaoExcluirCategoria);
		botaoExcluirCategoria.setIcon(iconeExcluirCategoria);
		botaoExcluirCategoria.setMargin(new Insets(0,-10,0,0));
		botaoExcluirCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirCategoria.setBackground(Color.GREEN);
		botaoExcluirCategoria.setPreferredSize(new Dimension(150,50));
		botaoExcluirCategoria.addActionListener(trataEventosDespesas);
		
		//botao Editar Categoria
		String labelBotaoEditarCategoria = "Editar Categoria";
		ImageIcon iconeEditarCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoEditarCategoria.setText(labelBotaoEditarCategoria);
		botaoEditarCategoria.setIcon(iconeEditarCategoria);
		botaoEditarCategoria.setMargin(new Insets(0,-20,0,0));
		botaoEditarCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarCategoria.setBackground(Color.GREEN);
		botaoEditarCategoria.setPreferredSize(new Dimension(150,50));
		botaoEditarCategoria.addActionListener(trataEventosDespesas);
		
		
		//Botão Nova Despesa
		String labelBotaoAdicionarDespesa = "Adicionar Despesa";
		ImageIcon iconeAdicionarDespesa = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoAdicionarDespesa.setText(labelBotaoAdicionarDespesa);
		botaoAdicionarDespesa.setIcon(iconeAdicionarDespesa);
		botaoAdicionarDespesa.setMargin(new Insets(0,0,0,0));
		botaoAdicionarDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoAdicionarDespesa.setBackground(Color.GREEN);
		botaoAdicionarDespesa.setPreferredSize(new Dimension(150,50));
		botaoAdicionarDespesa.addActionListener(trataEventosDespesas);
		
		//Botão Excluir Despesa
		String labelBotaoExcluirDespesa = "Excluir Despesa";
		ImageIcon iconeExcluirDespesa = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirDespesa.setText(labelBotaoExcluirDespesa);
		botaoExcluirDespesa.setIcon(iconeExcluirDespesa);
		botaoExcluirDespesa.setMargin(new Insets(0,0,0,0));
		botaoExcluirDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirDespesa.setBackground(Color.GREEN);
		botaoExcluirDespesa.setPreferredSize(new Dimension(150,50));
		botaoExcluirDespesa.addActionListener(trataEventosDespesas);
		
		//Botão Editar Despesa
		String labelBotaoEditarDespesa = "Editar Despesa";
		ImageIcon iconeEditarDespesa = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoEditarDespesa.setText(labelBotaoEditarDespesa);
		botaoEditarDespesa.setIcon(iconeEditarDespesa);
		botaoEditarDespesa.setMargin(new Insets(0,0,0,0));
		botaoEditarDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarDespesa.setBackground(Color.GREEN);
		botaoEditarDespesa.setPreferredSize(new Dimension(150,50));
		botaoEditarDespesa.addActionListener(trataEventosDespesas);
		
		//adiciona os botões
		painelBotoes.add(botaoNovaCategoria, constraints);
		constraints.gridy = 1;
		painelBotoes.add(botaoExcluirCategoria, constraints);
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 50, 0);
		painelBotoes.add(botaoEditarCategoria, constraints);
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 0, 25, 0);
		painelBotoes.add(botaoAdicionarDespesa, constraints);
		constraints.gridy = 4;
		painelBotoes.add(botaoExcluirDespesa, constraints);
		constraints.gridy = 5;
		painelBotoes.add(botaoEditarDespesa, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setVisible(true);
	}
	
	private void criaPainelTitulo(){
		final int TAM_X = 300;
		final int TAM_Y = 100;
		
		Border borda = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setBackground(Color.LIGHT_GRAY);
		painelTitulo.setBorder(borda);
		painelTitulo.setVisible(true);
	}

	private void iniciaElementos(){
		abasCategoria = new AbasCategoria();
		painelBotoes = new JPanel();
		painelTitulo = new JPanel();
		botaoNovaCategoria = new JButton();
		botaoExcluirCategoria = new JButton();
		botaoEditarCategoria = new JButton();
		botaoAdicionarDespesa = new JButton();
		botaoExcluirDespesa = new JButton();
		botaoEditarDespesa = new JButton();
		
	}
	
	public JButton getBotaoNovaCategoria() {
		return botaoNovaCategoria;
	}
	
	public JButton getBotaoExcluirCategoria() {
		return botaoExcluirCategoria;
	}

	public JButton getBotaoEditarCategoria() {
		return botaoEditarCategoria;
	}

	public JButton getBotaoAdicionarDespesa() {
		return botaoAdicionarDespesa;
	}

	public JButton getBotaoExcluirDespesa() {
		return botaoExcluirDespesa;
	}

	public JButton getBotaoEditarDespesa() {
		return botaoEditarDespesa;
	}

	public AbasCategoria getAbasCategoria() {
		return abasCategoria;
	}
	
}