package gui.framePrincipal;

import javax.swing.JFrame;

/**
 * Define a GUI inicial do aplicativo.
 *  
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class GuiPrincipal extends JFrame{
	private final static String TITULO_PRINCIPAL = "Planejamento Financeiro";
	/**
	 * Largura da janela.
	 */
	public final int TAM_JANELA_PRINCIPAL_X = 1000;
	
	/**
	 * Altura da janela.
	 */
	public final int TAM_JANELA_PRINCIPAL_Y = 700;

	private GuiMenu guiMenu = new GuiMenu();
	private PainelPrincipal painelPrincipal = new PainelPrincipal(this);
	
	/**
	 * Construtor padrão
	 */
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

	/**
	 *  Retorna a referência de um <code>GuiMenu</code>.
	 * @return o objeto <code>GuiMenu</code> da janela.
	 */
	public GuiMenu getGuiMenu() {
		return guiMenu;
	}
}