package eventos.categoria;

import gui.JanelaMensagem;
import gui.categoria.JanelaRemoverCategoria;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.Categoria;
import persistencia.CategoriaDAO;

public class TEJanelaRemoverCategoria implements ActionListener{
	private JanelaRemoverCategoria janelaRemoverCategoria;
	private IgPainelDespesas igPainelDespesas;
	
	public TEJanelaRemoverCategoria(JanelaRemoverCategoria janelaRemoverCategoria, IgPainelDespesas igPainelDespesas) {
		this.janelaRemoverCategoria = janelaRemoverCategoria;
		this.igPainelDespesas = igPainelDespesas;
	}

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
