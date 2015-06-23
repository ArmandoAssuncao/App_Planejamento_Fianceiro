package gui.painelRenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classes.RendaMensal;
import eventos.painelRenda.TEPainelRenda;

public class IgPainelRenda extends JPanel {
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;
	

	ArrayList<RendaMensal> arrayListRendaMensal;
	private TEPainelRenda trataEventosRenda;
	private AbasRenda abasRenda;
	private TabelaRendaMensal tabelaRendaMensal;
	
	private JPanel painelBotoes;
	private JPanel painelTitulo;
	
	private JButton botaoAddRenda;
	private JButton botaoExcluirRenda;
	private JButton botaoEditarRenda;
	
	public IgPainelRenda(Window framePrincipal) {
		setLayout(new BorderLayout(0,5));

		trataEventosRenda = new TEPainelRenda(this, framePrincipal);
		iniciaElementos();
		criaPainelTitulo();
		criaPainelBotoes();
			
		for(int i = 0;i<15;i++)//TODO apagar 
		tabelaRendaMensal.adicionaLinha("Data", "valor");
		
		add(painelTitulo, BorderLayout.NORTH);
		add(abasRenda, BorderLayout.WEST);
		add(painelBotoes,BorderLayout.EAST);
		
		
		setBackground(Color.GREEN);
	}

	private void iniciaElementos() {
		tabelaRendaMensal = new TabelaRendaMensal();
		arrayListRendaMensal = new ArrayList<RendaMensal>();
		
		abasRenda = new AbasRenda();
		
		for(int i = 0;i<15;i++)
			abasRenda.criarAbaRenda("Renda "+i);
		
		painelBotoes = new JPanel();
		painelTitulo = new JPanel();
		
		botaoAddRenda = new JButton();
		botaoExcluirRenda = new JButton();
		botaoEditarRenda = new JButton();
		
		tabelaRendaMensal = new TabelaRendaMensal();
		tabelaRendaMensal.setPreferredSize(new Dimension(900, 500));
		tabelaRendaMensal.adicionaLinha("data", "valor");
	}//iniciaElementos()

	private void criaPainelTitulo(){
		final int TAM_X = 300;
		final int TAM_Y = 130;
		
		Border borda = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		painelTitulo.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelTitulo.setBackground(Color.LIGHT_GRAY);
		painelTitulo.setBorder(borda);
		painelTitulo.setVisible(true);
	}

	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 400;
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		//configura��es do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0, 0, 25, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Adicionar Renda
		String labelbotaoAddRenda = "Adicionar Renda";
		ImageIcon iconeNovaCategoria = new ImageIcon("imagens/img_botaoNovaCategoria.png");
		botaoAddRenda.setText(labelbotaoAddRenda);
		botaoAddRenda.setIcon(iconeNovaCategoria);
		botaoAddRenda.setMargin(new Insets(0,-20,0,0));
		botaoAddRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoAddRenda.setBackground(Color.GREEN);
		botaoAddRenda.setPreferredSize(new Dimension(150,50));
		botaoAddRenda.addActionListener(trataEventosRenda);
		
		//botao Excluir Renda
		String labelBotaoExcluirRenda = "Excluir Renda";
		ImageIcon iconeExcluirRenda = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirRenda.setText(labelBotaoExcluirRenda);
		botaoExcluirRenda.setIcon(iconeExcluirRenda);
		botaoExcluirRenda.setMargin(new Insets(0,-10,0,0));
		botaoExcluirRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirRenda.setBackground(Color.GREEN);
		botaoExcluirRenda.setPreferredSize(new Dimension(150,50));
		botaoExcluirRenda.addActionListener(trataEventosRenda);
		
		//botao Editar Renda
		String labelBotaoEditarRenda = "Editar Renda";
		ImageIcon iconeEditarRenda = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		botaoEditarRenda.setText(labelBotaoEditarRenda);
		botaoEditarRenda.setIcon(iconeEditarRenda);
		botaoEditarRenda.setMargin(new Insets(0,-20,0,0));
		botaoEditarRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarRenda.setBackground(Color.GREEN);
		botaoEditarRenda.setPreferredSize(new Dimension(150,50));
		botaoEditarRenda.addActionListener(trataEventosRenda);
		
		//adiciona os bot�es
		painelBotoes.add(botaoAddRenda, constraints);
		constraints.gridy = 1;
		painelBotoes.add(botaoExcluirRenda,constraints);
		constraints.gridy = 2;
		painelBotoes.add(botaoEditarRenda,constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(Color.RED); //TODO Apagar
//		painelBotoes.setVisible(true);  //TODO isso � realmente necess�rio?
	}//criaPainelBotoes

	
	public boolean criarAbaRenda(RendaMensal rendaMensal){
		/*if(abasRenda.criarAbaRenda(rendaMensal.getDescricao())){
			arrayListRendaMensal.add(rendaMensal);
			abasRenda.setSelectedIndex(abasRenda.getNumeroDeAbas()-1);
		
			return true;
		}*/
		return true;
	}
	
	public JButton getBotaoAddRenda() {
		return botaoAddRenda;
	}

	public JButton getBotaoExcluirRenda() {
		return botaoExcluirRenda;
	}

	public JButton getBotaoEditarRenda() {
		return botaoEditarRenda;
	}
}
