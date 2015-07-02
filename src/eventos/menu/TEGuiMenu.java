package eventos.menu;

import gui.JanelaMensagem;
import gui.framePrincipal.GuiMenu;
import gui.renda.JanelaCriarRenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import persistencia.PlanejamentoFinanceiroDAO;

public class TEGuiMenu implements ActionListener{
	private GuiMenu guiMenu;
	
	public TEGuiMenu(GuiMenu guiMenu) {
		this.guiMenu = guiMenu;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == guiMenu.getMenuItemApagarTudo()){
			boolean confirmar = JanelaMensagem.mostraMensagemConfirmaWarning(null, "Deletar todos dados", "Você tem certeza que deseja deletar todos os dados?\n"
																			+ "O programa sera fechado.");
			
			if(confirmar){
				PlanejamentoFinanceiroDAO BD = PlanejamentoFinanceiroDAO.getInstance();
				try {
					BD.excluiRegistros();
					JanelaMensagem.mostraMensagem(null, "Deletar todos dados", "A exclusão dos dados foi realizada com sucesso");
				}
				catch (IOException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErro(null, "Erro ao excluir os dados: " + e.getMessage());
				}
				catch (SQLException e) {
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, "Erro ao excluir os dados" + e.getMessage());
				}
			}
		}
		
		else if(event.getSource() == guiMenu.getMenuItemSair()){
			System.exit(1);
		}
		
		else if(event.getSource() == guiMenu.getMenuItemAdicionarRenda()){
			//new JanelaCriarRenda(igPainelRenda)
		}
		
		
		else if(event.getSource() == guiMenu.getMenuItemAbrirBD()){
			PlanejamentoFinanceiroDAO.mostraJanelaBancoDeDados();
		}
	}

}
