package eventos.menu;

import gui.framePrincipal.GuiMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencia.BDPlanejamentoFinanceiro;

public class TEGuiMenu implements ActionListener{
	private GuiMenu guiMenu;
	
	public TEGuiMenu(GuiMenu guiMenu) {
		this.guiMenu = guiMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if(e.getSource() == guiMenu.getMenuItemAbrirBD()){
			BDPlanejamentoFinanceiro.mostraJanelaBancoDeDados();
			System.out.println("asd");
		}
	}
	
}
