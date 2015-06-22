package eventos.categoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import persistencia.CategoriaDAO;
import persistencia.BancoDeDados;
import persistencia.MetaMensalDAO;
import classes.Categoria;
import classes.MetaMensal;
import gui.JanelaMensagem;
import gui.categoria.JanelaCriarCategoria;
import gui.painelDespesas.IgPainelDespesas;

public class TEJanelaCriarCategoria  implements ActionListener{
	private JanelaCriarCategoria janelaCriarCategoria;
	private IgPainelDespesas igPainelDespesas;
	
	public TEJanelaCriarCategoria(JanelaCriarCategoria janelaCriarCategoria, IgPainelDespesas igPainelDespesas) {
		this.janelaCriarCategoria = janelaCriarCategoria;
		this.igPainelDespesas = igPainelDespesas;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//botão Criar
		if(event.getSource() == janelaCriarCategoria.getBotaoCriar()){
			if(janelaCriarCategoria.validaCampos()){
				Categoria categoria = janelaCriarCategoria.retornaCategoria();
				MetaMensal metaMensal = categoria.getMetaMensal();
				
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				MetaMensalDAO metaMensalDAO = new MetaMensalDAO();
				
				try{
					if(!categoriaDAO.exists(categoria.getDescricao()) && !metaMensalDAO.exists(metaMensal.getMesAnoMeta(), categoria.getDescricao())){
						if(categoriaDAO.inserir(categoria) == BancoDeDados.RESULTADO_SUCESSO &&
							metaMensalDAO.inserir(metaMensal, categoria.getDescricao()) == BancoDeDados.RESULTADO_SUCESSO){
							System.out.println("salvou no banco");//TODO
							
							igPainelDespesas.criarCategoria(categoria);
							
							janelaCriarCategoria.finalizaJanelaCategoria();
						}
					}
					else{
						JanelaMensagem.mostraMensagem(null, "Criar Categoria", "Já existe uma categoria com esse nome.");
					}
				}
				catch(SQLException e){
					e.printStackTrace();
					JanelaMensagem.mostraMensagemErroBD(null, e.getMessage());
				}
			}
		}//if()
		
		//botão Cancelar
		else if(event.getSource() == janelaCriarCategoria.getBotaoCancelar()){
			janelaCriarCategoria.finalizaJanelaCategoria();
		}
		
	}//actionPerformed()
}
