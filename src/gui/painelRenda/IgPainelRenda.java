package gui.painelRenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import persistencia.RendaDAO;
import persistencia.RendaMensalDAO;
import classes.Renda;
import classes.RendaMensal;
import eventos.painelRenda.TEPainelRenda;

public class IgPainelRenda extends JPanel {
	public final int TAM_PAINEL_X = 800;
	public final int TAM_PAINEL_Y = 600;
	
	ArrayList<RendaMensal> arrayListRendaMensal;
	private TEPainelRenda trataEventosRenda;
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
		iniciaValoresRenda();
		criaPainelTitulo();
		criaPainelBotoes();
			
		for(int i = 1;i<15;i++)//TODO apagar 
		tabelaRendaMensal.adicionaLinha("Descrição","Data", "valor");
		
		add(painelTitulo, BorderLayout.NORTH);
		add(tabelaRendaMensal,BorderLayout.WEST);
		add(painelBotoes,BorderLayout.EAST);
		
		setVisible(true);
		setBackground(Color.GREEN);
	}

	private void iniciaElementos() {
		arrayListRendaMensal = new ArrayList<RendaMensal>();
		
		painelBotoes = new JPanel();
		painelTitulo = new JPanel();
		
		botaoAddRenda = new JButton();
		botaoExcluirRenda = new JButton();
		botaoEditarRenda = new JButton();
		
		tabelaRendaMensal = new TabelaRendaMensal();
		tabelaRendaMensal.adicionaLinha("Descrição","data", "valor");
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
		
		//configurações do layout
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
		botaoAddRenda.setPreferredSize(new Dimension(150,50));
		botaoAddRenda.addActionListener(trataEventosRenda);
		
		//botao Excluir Renda
		String labelBotaoExcluirRenda = "Excluir Renda";
		ImageIcon iconeExcluirRenda = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirRenda.setText(labelBotaoExcluirRenda);
		botaoExcluirRenda.setIcon(iconeExcluirRenda);
		botaoExcluirRenda.setMargin(new Insets(0,-10,0,0));
		botaoExcluirRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirRenda.setPreferredSize(new Dimension(150,50));
		botaoExcluirRenda.addActionListener(trataEventosRenda);
		
		//botao Editar Renda
		String labelBotaoEditarRenda = "Editar Renda";
		ImageIcon iconeEditarRenda = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		botaoEditarRenda.setText(labelBotaoEditarRenda);
		botaoEditarRenda.setIcon(iconeEditarRenda);
		botaoEditarRenda.setMargin(new Insets(0,-20,0,0));
		botaoEditarRenda.setHorizontalTextPosition(JButton.RIGHT);
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

	private void iniciaValoresRenda(){
		try {
			Set<Renda> rendas = new HashSet<Renda>();
			List<Renda> rendaTemp = RendaDAO.todasAsRendas();
			List<RendaMensal> rendaMensalTemp = RendaMensalDAO.todasAsRendasMensais();
			for(Renda renda : rendaTemp){
				int idRenda = new RendaDAO().getId(renda.getDescricao());
				
				System.out.println("idRenda="+idRenda);
				
				for(RendaMensal rendaMensal : rendaMensalTemp){
					
					System.out.println("idrendaMensal="+new RendaMensalDAO().getId(renda.getDescricao()));
					
					if(idRenda == new RendaMensalDAO().getId(renda.getDescricao())){
//						System.out.println("inserido na lista");
//						renda.adicionarRendaMensal(rendaMensal);
//						rendas.add(renda);
						//TODO arrumar isto 
						System.out.println(renda+"  "+rendaMensal);
					}
				}//for
			}//for
			System.out.println("llist rendas\n\n" +rendas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
