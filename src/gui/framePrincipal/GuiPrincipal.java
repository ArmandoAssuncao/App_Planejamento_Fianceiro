package gui.framePrincipal;

import javax.swing.JFrame;

public class GuiPrincipal extends JFrame{
	private final static String TITULO_PRINCIPAL = "Planejamento Financeiro";
	public final int TAM_JANELA_PRINCIPAL_X = 1000;
	public final int TAM_JANELA_PRINCIPAL_Y = 700;

	PainelPrincipal painelPrincipal = new PainelPrincipal(this);
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

	public GuiMenu getGuiMenu() {
		return guiMenu;
	}
}