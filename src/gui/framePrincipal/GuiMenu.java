package gui.framePrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eventos.menu.TEGuiMenu;

public class GuiMenu extends JMenuBar{
	
	TEGuiMenu trataEventosMenu;
	
	private JMenuItem menuItemAdicionarRenda;
	private JMenuItem menuItemEditarRenda;
	private JMenuItem menuItemRemoverRenda;
	private JMenuItem menuItemAddDespesa;
	private JMenuItem menuItemEditarDespesa;
	private JMenuItem menuItemExcluirDespesa;
	private JMenuItem menuItemAddCategoria;
	private JMenuItem menuItemEditarCategoria;
	private JMenuItem menuItemExcluirCategoria;
	private JMenuItem menuItemAbrirBD;
	private JMenuItem menuItemAutor;
	
	public GuiMenu() {
		//Menu
		//setPreferredSize(new Dimension(190,20));
		
		this.trataEventosMenu = new TEGuiMenu(this);
		
		JMenu menuRenda = new JMenu("Renda");
		add(menuRenda);		

		menuItemAdicionarRenda = new JMenuItem("Adicionar Renda");
		menuRenda.add(menuItemAdicionarRenda);
		
		menuItemEditarRenda = new JMenuItem("Editar Renda");
		menuRenda.add(menuItemEditarRenda);
		
		menuItemRemoverRenda = new JMenuItem("Excluir Renda");
		menuRenda.add(menuItemRemoverRenda);
		
		
		
		JMenu menuDespesa = new JMenu("Despesa");
		add(menuDespesa);
		
		menuItemAddDespesa = new JMenuItem("Adicionar Despesa");
		menuDespesa.add(menuItemAddDespesa);
		
		menuItemEditarDespesa = new JMenuItem("Editar Despesa");
		menuDespesa.add(menuItemEditarDespesa);
		
		menuItemExcluirDespesa = new JMenuItem("Excluir Despesa");
		menuDespesa.add(menuItemExcluirDespesa);
		
		
		
		JMenu menuCategoria = new JMenu("Categoria");
		add(menuCategoria);
		
		menuItemAddCategoria = new JMenuItem("Adicionar Categoria");
		menuCategoria.add(menuItemAddCategoria);
		
		menuItemEditarCategoria = new JMenuItem("Editar Categoria");
		menuCategoria.add(menuItemEditarCategoria);
		
		menuItemExcluirCategoria = new JMenuItem("Excluir Categoria");
		menuCategoria.add(menuItemExcluirCategoria);
		

		JMenu menuExibir = new JMenu("Exibir");
		add(menuExibir);
		
		JMenu menuEditar = new JMenu("Editar");
		add(menuEditar);
		
		
		JMenu menuConfiguracoes = new JMenu("Configurações");
		add(menuConfiguracoes);
		
		menuItemAbrirBD = new JMenuItem("Banco de Dados...");
		menuConfiguracoes.add(menuItemAbrirBD);

		
		JMenu menuSobre = new JMenu("Sobre");
		add(menuSobre);
		
		menuItemAutor = new JMenuItem("Autor");
		menuSobre.add(menuItemAutor);

		
		//Adiciona o tratador de eventos
		menuItemAbrirBD.addActionListener(trataEventosMenu);
	}
	

	public JMenuItem getMenuItemAdicionarRenda() {
		return menuItemAdicionarRenda;
	}

	public JMenuItem getMenuItemEditarRenda() {
		return menuItemEditarRenda;
	}

	public JMenuItem getMenuItemRemoverRenda() {
		return menuItemRemoverRenda;
	}

	public JMenuItem getMenuItemAddDespesa() {
		return menuItemAddDespesa;
	}

	public JMenuItem getMenuItemEditarDespesa() {
		return menuItemEditarDespesa;
	}

	public JMenuItem getMenuItemExcluirDespesa() {
		return menuItemExcluirDespesa;
	}

	public JMenuItem getMenuItemAddCategoria() {
		return menuItemAddCategoria;
	}

	public JMenuItem getMenuItemEditarCategoria() {
		return menuItemEditarCategoria;
	}

	public JMenuItem getMenuItemExcluirCategoria() {
		return menuItemExcluirCategoria;
	}

	public JMenuItem getMenuItemAbrirBD() {
		return menuItemAbrirBD;
	}

	public JMenuItem getMenuItemAutor() {
		return menuItemAutor;
	}
	
}
