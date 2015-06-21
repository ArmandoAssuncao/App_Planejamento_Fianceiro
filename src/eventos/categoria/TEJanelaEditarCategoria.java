package eventos.categoria;

import gui.JanelaMensagem;
import gui.categoria.JanelaEditarCategoria;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import persistencia.BancoDeDados;
import persistencia.CategoriaDAO;
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
				MetaMensal antigaMetaMensal = antigaCategoria.getMetaMensal();
				
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				//MetaMensalDAO metaMensalDAO = new MetaMensalDAO();
				
				try{
					if(!categoriaDAO.exists(novaCategoria.getDescricao())){ //TODO && metaMensalDAO.exists()
						if(categoriaDAO.atualizarDados(novaCategoria, antigaCategoria.getDescricao())){
							System.out.println("Atualizou o banco");
							
							igPainelDespesas.editarCategoria(novaCategoria);
							
							janelaEditarCategoria.finalizaJanelaCategoria();
						}
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
		
	}
	
}
