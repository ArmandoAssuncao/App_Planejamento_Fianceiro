package gui.framePrincipal;

import gui.abasPrincipal.AbasPrincipal;
import gui.painelPrincipal.PainelPrincipal;

import javax.swing.JFrame;

public class GuiPrincipal extends JFrame{
	private final static String TITULO_PRINCIPAL = "Planejamento Financeiro";
	public final int TAM_JANELA_PRINCIPAL_X = 1200;
	public final int TAM_JANELA_PRINCIPAL_Y = 700;

	PainelPrincipal painelPrincipal = new PainelPrincipal();
	GuiMenu guiMenu = new GuiMenu();
	
	public GuiPrincipal() {
		super(TITULO_PRINCIPAL);
		
		//adiciona o menu
		setJMenuBar(guiMenu);
		
		add(painelPrincipal);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(TAM_JANELA_PRINCIPAL_X, TAM_JANELA_PRINCIPAL_Y);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
