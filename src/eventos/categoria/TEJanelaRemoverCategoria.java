package eventos.categoria;

import gui.JanelaMensagem;
import gui.categoria.JanelaRemoverCategoria;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.Categoria;
import persistencia.CategoriaDAO;

/**
 *  * Classe para tratar os eventos da janela Remover Categoria.

 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEJanelaRemoverCategoria implements ActionListener{
	private JanelaRemoverCategoria janelaRemoverCategoria;
	private IgPainelDespesas igPainelDespesas;
	
	/**
	 * Cria uma instância do tratador de eventos da janela.
	 * 
	 * @param janelaRemoverCategoria janela de onde vem os eventos.
	 * @param igPainelDespesas painel interno da janela //TODO está certo?
	 */
	public TEJanelaRemoverCategoria(JanelaRemoverCategoria janelaRemoverCategoria, IgPainelDespesas igPainelDespesas) {
		this.janelaRemoverCategoria = janelaRemoverCategoria;
		this.igPainelDespesas = igPainelDespesas;
	}
	
	/**
	 * Trata os eventos de clique da janela.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == janelaRemoverCategoria.getBotaoRemover()){
			Categoria categoria = janelaRemoverCategoria.retornaCategoria();
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			
			boolean remover = JanelaMensagem.mostraMensagemConfirmaWarning(null, "Remover Categoria", "Tem certeza que deseja remover a categoria: " + categoria.getDescricao());
			
			try {
				if(remover)
					if(categoriaDAO.excluir(categoria.getDescricao())){
						igPainelDespesas.removerCategoria();
						
						JanelaMensagem.mostraMensagem(null, "Remover Categoria", "A categoria foi removida com sucesso.");
						
						janelaRemoverCategoria.finalizaJanelaCategoria();
					}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(event.getSource() == janelaRemoverCategoria.getBotaoCancelar()){
			janelaRemoverCategoria.finalizaJanelaCategoria();
		}
		
	}
}
