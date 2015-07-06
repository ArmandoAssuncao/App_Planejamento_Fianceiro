package gui.framePrincipal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Define a GUI inicial do aplicativo.
 *  
 * @author Armando Assunção
 * @author Richardson William
 *
 */
public class GuiPrincipal extends JFrame{
	private static final long serialVersionUID = -340886102589057719L;
	
	private final static String TITULO_PRINCIPAL = "Planejamento Financeiro";
	
	private final int TAM_JANELA_PRINCIPAL_X = 1000;
	private final int TAM_JANELA_PRINCIPAL_Y = 700;

	private GuiMenu guiMenu = new GuiMenu();
	private PainelPrincipal painelPrincipal = new PainelPrincipal(this);
	
	/**
	 * Construtor padrão
	 */
	public GuiPrincipal() {
		super(TITULO_PRINCIPAL);
		
		//Modifica o ícone do aplicativo
		ImageIcon img = new ImageIcon("imagens/icone.png");
		this.setIconImage(img.getImage());
		
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