package eventos.menu;

import gui.framePrincipal.GuiMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencia.PlanejamentoFinanceiroDAO;

public class TEGuiMenu implements ActionListener{
	private GuiMenu guiMenu;
	
	public TEGuiMenu(GuiMenu guiMenu) {
		this.guiMenu = guiMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == guiMenu.getMenuItemAbrirBD()){
			PlanejamentoFinanceiroDAO.mostraJanelaBancoDeDados();
		}
	}
	
}
