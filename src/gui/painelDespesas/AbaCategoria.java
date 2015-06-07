package gui.painelDespesas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class AbaCategoria extends JTabbedPane{
	private final int TAM_ABA_X = 900;
	private final int TAM_ABA_Y = 500;
	
	private TabelaDaCategoria tabela;
	private JScrollPane barraRolagem;
	
	
	public AbaCategoria() {
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setPreferredSize(new Dimension(TAM_ABA_X, TAM_ABA_Y));
		setBackground(Color.GREEN);
		setVisible(true);
	}
	
	//Cria nova Categoria
	public void criarCategoria(String nomeCategoria){
		tabela = new TabelaDaCategoria();
		barraRolagem = new JScrollPane();
		
		for(int i = 0; i < 50; i++) //APAGAR
			tabela.adicionaLinha(nomeCategoria, "valor", "23/12");
		
		barraRolagem.setViewportView(tabela);
		
		add(nomeCategoria, barraRolagem);
	}
	
}
