package gui.framePrincipal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiMenu extends JMenuBar{
	public GuiMenu() {
		//Menu
		//setPreferredSize(new Dimension(190,20));
		
		JMenu menuExibir = new JMenu("Exibir");
		add(menuExibir);
		
		JMenu menuEditar = new JMenu("Editar");
		add(menuEditar);
		
		JMenu menuSobre = new JMenu("Sobre");
		add(menuSobre);
		
		JMenuItem menuItemAutor = new JMenuItem("Autor");
		menuSobre.add(menuItemAutor);
	}
}
