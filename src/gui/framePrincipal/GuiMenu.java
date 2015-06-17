package gui.framePrincipal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiMenu extends JMenuBar{
	public GuiMenu() {
		//Menu
		//setPreferredSize(new Dimension(190,20));	
		
		JMenu menuRenda = new JMenu("Renda");
		add(menuRenda);		

		JMenuItem menuItemAdicionarRenda = new JMenuItem("Adicionar Renda");
		menuRenda.add(menuItemAdicionarRenda);
		
		JMenuItem menuItemEditarRenda = new JMenuItem("Editar Renda");
		menuRenda.add(menuItemEditarRenda);
		
		JMenuItem menuItemRemoverRenda = new JMenuItem("Excluir Renda");
		menuRenda.add(menuItemRemoverRenda);
		
		
		
		JMenu menuDespesa = new JMenu("Despesa");
		add(menuDespesa);
		
		JMenuItem menuItemAddDespesa = new JMenuItem("Adicionar Despesa");
		menuDespesa.add(menuItemAddDespesa);
		
		JMenuItem menuItemEditarDespesa = new JMenuItem("Editar Despesa");
		menuDespesa.add(menuItemEditarDespesa);
		
		JMenuItem menuItemExcluirDespesa = new JMenuItem("Excluir Despesa");
		menuDespesa.add(menuItemExcluirDespesa);
		
		
		
		JMenu menuCategoria = new JMenu("Categoria");
		add(menuCategoria);
		
		JMenuItem menuItemAddCategoria = new JMenuItem("Adicionar Categoria");
		menuCategoria.add(menuItemAddCategoria);
		
		JMenuItem menuItemEditarCategoria = new JMenuItem("Editar Categoria");
		menuCategoria.add(menuItemEditarCategoria);
		
		JMenuItem menuItemExcluirCategoria = new JMenuItem("Excluir Categoria");
		menuCategoria.add(menuItemExcluirCategoria);
		
		

		JMenu menuExibir = new JMenu("Exibir");
		add(menuExibir);
		
		JMenu menuEditar = new JMenu("Editar");
		add(menuEditar);

		
		
		JMenu menuSobre = new JMenu("Sobre");
		add(menuSobre);
		
		JMenuItem menuItemAutor = new JMenuItem("Autor");
		menuSobre.add(menuItemAutor);
	}
}
