package eventos.menu;

import gui.JanelaAutor;
import gui.JanelaMensagem;
import gui.categoria.JanelaCriarCategoria;
import gui.categoria.JanelaEditarCategoria;
import gui.despesa.JanelaCriarDespesa;
import gui.framePrincipal.GuiMenu;
import gui.graficos.JanelaBalancoDespesas;
import gui.graficos.JanelaBalancoMensal;
import gui.renda.JanelaCriarRenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import persistencia.PlanejamentoFinanceiroDAO;

/**
 * Trata os eventos da barra de menu
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEGuiMenu implements ActionListener{
	private GuiMenu guiMenu;
	
	/**
	 * Cria uma instância do tratador de eventos da barra de menu
	 * @param guiMenu barra de menu do tipo <code>GuiMenu</code> que será manipulada.
	 */
	public TEGuiMenu(GuiMenu guiMenu) {
		this.guiMenu = guiMenu;
	}

	/**
	 * Trata o evento de mudança do estado da barra de menu
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == guiMenu.getMenuItemApagarTudo()){
			boolean confirmar = JanelaMensagem.mostraMensagemConfirmaWarning(null, "Deletar todos dados", "Você tem certeza que deseja deletar todos os dados?\n"
																			+ "O programa sera fechado.");
			
			if(confirmar){
				PlanejamentoFinanceiroDAO BD = PlanejamentoFinanceiroDAO.getInstance();
				try {
					BD.excluiRegistros();
					BD.insereDados();
					JanelaMensagem.mostraMensagem(null, "Deletar todos dados", "A exclusão dos dados foi realizada com sucesso");
					System.exit(1);
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
			new JanelaCriarRenda(guiMenu.getIgPainelRenda());
		}
		
		else if(event.getSource() == guiMenu.getMenuItemAddCategoria()){
			new JanelaCriarCategoria(guiMenu.getIgPainelDespesas());
		}
		
		else if(event.getSource() == guiMenu.getMenuItemEditarCategoria()){
			new JanelaEditarCategoria(guiMenu.getIgPainelDespesas());
		}
		
		else if(event.getSource() == guiMenu.getMenuItemAddDespesa()){
			new JanelaCriarDespesa(guiMenu.getIgPainelDespesas());
		}
		
		else if(event.getSource() == guiMenu.getMenuItemAutor()){
			new JanelaAutor();
		}
		
		else if(event.getSource() == guiMenu.getMenuItemBalancoMensal()){
			new JanelaBalancoMensal();
		}
		
		else if(event.getSource() == guiMenu.getMenuItemBalancoMensalDespesas()){
			new JanelaBalancoDespesas();
		}
		
		
		else if(event.getSource() == guiMenu.getMenuItemAbrirBD()){
			PlanejamentoFinanceiroDAO.mostraJanelaBancoDeDados();
		}
	}

}
