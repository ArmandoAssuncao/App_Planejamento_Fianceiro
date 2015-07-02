package eventos.painelDespesa;

import gui.categoria.JanelaCriarCategoria;
import gui.categoria.JanelaEditarCategoria;
import gui.categoria.JanelaRemoverCategoria;
import gui.despesa.JanelaCriarDespesa;
import gui.painelDespesas.IgPainelDespesas;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trata os eventos do painel de despesas
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see ActionListener
 */
public class TEPainelDespesas implements ActionListener{
	private IgPainelDespesas painelDespesas;
	Window janelaPai;
	/**
	 * Cria uma instância do tratador de eventos da igDespesas.
	 * 
	 * @param painelDespesas painel que será manipulado.
	 * @param janelaPai janela onde o painelDespesas está contido.
	 */
	public TEPainelDespesas(IgPainelDespesas painelDespesas, Window janelaPai) {
		this.janelaPai = janelaPai;
		this.painelDespesas = painelDespesas;
	}
	
	/**
	 * Trata o evento de mudança do estado
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == painelDespesas.getBotaoNovaCategoria()){
			new JanelaCriarCategoria(painelDespesas);
		}
		else if(e.getSource() == painelDespesas.getBotaoExcluirCategoria()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0)
				new JanelaRemoverCategoria(painelDespesas);
		}
		else if(e.getSource() == painelDespesas.getBotaoEditarCategoria()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0)
				new JanelaEditarCategoria(painelDespesas);
		}
		else if(e.getSource() == painelDespesas.getBotaoAdicionarDespesa()){
			if(painelDespesas.getAbasCategoria().getNumeroDeAbas() != 0){
				new JanelaCriarDespesa(painelDespesas, painelDespesas.getAbasCategoria());
				//painelDespesas.atualizaPainelTitulo();
			}
				
		}
	}
	
}
