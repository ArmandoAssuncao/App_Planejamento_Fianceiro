package gui.painelDespesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eventos.TEPainelDespesas;

public class PainelDespesas extends JPanel{
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;
	
	AbaCategoria abaCategoria = new AbaCategoria();
	JPanel painelBotoes = new JPanel();
	JPanel painelTitulo = new JPanel();
	TEPainelDespesas trataEventosDespesas;
	
	JButton botaoAdicionarDespesa;
	JButton botaoNovaCategoria;
	
	public PainelDespesas() {
		trataEventosDespesas = new TEPainelDespesas(this);
		
		setLayout(new BorderLayout(0,5));
		
		criaAbaCategoria("Esportes");
		for(int i = 0; i < 14; i++) //APAGAR
			criaAbaCategoria("Educação");
		criaPainelBotoes();
		criaPainelTitulo();
		
		add(painelTitulo, BorderLayout.NORTH);
		add(abaCategoria, BorderLayout.WEST);
		add(painelBotoes, BorderLayout.EAST);
		
		
		setPreferredSize(new Dimension(TAM_PAINEL_X, TAM_PAINEL_Y));
		setBackground(Color.BLUE);
		setVisible(true);
	}

	//cria uma nova aba
	private void criaAbaCategoria(String nomeCategoria){
		abaCategoria.criarCategoria(nomeCategoria);
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
		constraints.insets = new Insets(0, 0, 150, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Nova Categoria
		ImageIcon iconeNovaCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		String labelBotaoNovaCategoria = "Nova Categoria";
		botaoNovaCategoria = new JButton(labelBotaoNovaCategoria, iconeNovaCategoria);
		botaoNovaCategoria.setMargin(new Insets(0,-20,0,0));
		botaoNovaCategoria.setHorizontalTextPosition(JButton.RIGHT);
		botaoNovaCategoria.setBackground(Color.GREEN);
		botaoNovaCategoria.setPreferredSize(new Dimension(150,50));
		botaoNovaCategoria.addActionListener(trataEventosDespesas);
		
		//Botão Nova Despesa
		ImageIcon iconeAdicionarDespesa = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		String labelBotaoAdicionarDespesa = "Adicionar Despesa";
		botaoAdicionarDespesa = new JButton(labelBotaoAdicionarDespesa, iconeAdicionarDespesa);
		botaoAdicionarDespesa.setMargin(new Insets(0,0,0,0));
		botaoAdicionarDespesa.setHorizontalTextPosition(JButton.RIGHT);
		botaoAdicionarDespesa.setBackground(Color.GREEN);
		botaoAdicionarDespesa.setPreferredSize(new Dimension(150,50));
		
		//adiciona os botões
		painelBotoes.add(botaoNovaCategoria, constraints);
		constraints.gridy = 1;
		painelBotoes.add(botaoAdicionarDespesa, constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.BLUE);
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

	public JButton getBotaoAdicionarDespesa() {
		return botaoAdicionarDespesa;
	}

	public JButton getBotaoNovaCategoria() {
		return botaoNovaCategoria;
	}
	
}