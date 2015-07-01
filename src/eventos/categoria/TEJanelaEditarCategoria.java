package eventos.categoria;

import gui.JanelaMensagem;
import gui.categoria.JanelaEditarCategoria;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import persistencia.CategoriaDAO;
import persistencia.MetaMensalDAO;
import classes.Categoria;
import classes.MetaMensal;

public class TEJanelaEditarCategoria  implements ActionListener{
	private JanelaEditarCategoria janelaEditarCategoria;
	private IgPainelDespesas igPainelDespesas;
	
	public TEJanelaEditarCategoria(JanelaEditarCategoria janelaEditarCategoria, IgPainelDespesas igPainelDespesas) {
		this.janelaEditarCategoria = janelaEditarCategoria;
		this.igPainelDespesas = igPainelDespesas;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//botão Criar
		if(event.getSource() == janelaEditarCategoria.getBotaoEditar()){
			if(janelaEditarCategoria.validaCampos()){
				Categoria novaCategoria = janelaEditarCategoria.retornaNovaCategoria();
				MetaMensal novaMetaMensal = novaCategoria.getMetaMensal();
				
				Categoria antigaCategoria = janelaEditarCategoria.retornaAntigaCategoria();
				
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				MetaMensalDAO metaMensalDAO = new MetaMensalDAO();
				
				//verifica se a nova e antiga descricao são iguais
				boolean descricoesIguais = novaCategoria.getDescricao().equals(antigaCategoria.getDescricao());
				
				try{
					if(descricoesIguais || (!categoriaDAO.exists(novaCategoria.getDescricao()) &&
						!metaMensalDAO.exists(novaMetaMensal.getMesAnoMeta(), novaCategoria.getDescricao()))){
						
						if(categoriaDAO.atualizarDados(novaCategoria, antigaCategoria.getDescricao()) &&
							metaMensalDAO.atualizarDados(novaMetaMensal, novaMetaMensal.getMesAnoMeta(), novaCategoria.getDescricao())){
							System.out.println("Atualizou o banco");//TODO
							
							igPainelDespesas.editarCategoria(novaCategoria);
							
							janelaEditarCategoria.finalizaJanelaCategoria();
						}
					}
					else{
						JanelaMensagem.mostraMensagem(null, "Editar Categoria", "Já existe uma categoria com esse nome.");//Não é executado
					}
				}
				catch(SQLException e){
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
				}
			}
		}//if()
		
		else if(event.getSource() == janelaEditarCategoria.getBotaoCancelar()){
			janelaEditarCategoria.finalizaJanelaCategoria();
		}
		
	}//actionperformed()
}//class
