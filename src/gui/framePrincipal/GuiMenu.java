package gui.framePrincipal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eventos.menu.TEGuiMenu;

public class GuiMenu extends JMenuBar{
	
	TEGuiMenu trataEventosMenu;
	
	private JMenuItem menuItemSair;
	private JMenuItem menuItemApagarTudo;
	private JMenuItem menuItemAdicionarRenda;
	private JMenuItem menuItemEditarRenda;
	private JMenuItem menuItemRemoverRenda;
	private JMenuItem menuItemAddDespesa;
	private JMenuItem menuItemEditarDespesa;
	private JMenuItem menuItemExcluirDespesa;
	private JMenuItem menuItemAddCategoria;
	private JMenuItem menuItemEditarCategoria;
	private JMenuItem menuItemExcluirCategoria;
	private JMenuItem menuItemBalancoMensal;
	private JMenuItem menuItemBalancoMensalDespesas;
	private JMenuItem menuItemAbrirBD;
	private JMenuItem menuItemAutor;
	
	public GuiMenu() {
		//Menu
		//setPreferredSize(new Dimension(190,20));
		
		this.trataEventosMenu = new TEGuiMenu(this);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		add(menuArquivo);		

		menuItemApagarTudo = new JMenuItem("Apagar todos dados...");
		menuArquivo.add(menuItemApagarTudo);
		
		menuItemSair = new JMenuItem("Sair");
		menuArquivo.add(menuItemSair);
		
		JMenu menuRenda = new JMenu("Renda");
		add(menuRenda);		

		menuItemAdicionarRenda = new JMenuItem("Adicionar Renda");
		menuRenda.add(menuItemAdicionarRenda);
		
		menuItemEditarRenda = new JMenuItem("Editar Renda");
		menuRenda.add(menuItemEditarRenda);
		
		menuItemRemoverRenda = new JMenuItem("Excluir Renda");
		menuRenda.add(menuItemRemoverRenda);
		
		
		
		JMenu menuCategoria = new JMenu("Categoria");
		add(menuCategoria);
		
		menuItemAddCategoria = new JMenuItem("Adicionar Categoria");
		menuCategoria.add(menuItemAddCategoria);
		
		menuItemEditarCategoria = new JMenuItem("Editar Categoria");
		menuCategoria.add(menuItemEditarCategoria);
		
		menuItemExcluirCategoria = new JMenuItem("Excluir Categoria");
		menuCategoria.add(menuItemExcluirCategoria);
		
		
		
		JMenu menuDespesa = new JMenu("Despesa");
		add(menuDespesa);
		
		menuItemAddDespesa = new JMenuItem("Adicionar Despesa");
		menuDespesa.add(menuItemAddDespesa);
		
		menuItemEditarDespesa = new JMenuItem("Editar Despesa");
		menuDespesa.add(menuItemEditarDespesa);
		
		menuItemExcluirDespesa = new JMenuItem("Excluir Despesa");
		menuDespesa.add(menuItemExcluirDespesa);
		
		
		
		JMenu menuBalanco = new JMenu("Balanços");
		add(menuBalanco);
		
		menuItemBalancoMensal = new JMenuItem("Balanço Mensal...");
		menuBalanco.add(menuItemBalancoMensal);
		
		menuItemBalancoMensalDespesas = new JMenuItem("Balanço Despesas...");
		menuBalanco.add(menuItemBalancoMensalDespesas);
		
		
		JMenu menuConfiguracoes = new JMenu("Configurações");
		add(menuConfiguracoes);
		
		menuItemAbrirBD = new JMenuItem("Banco de Dados...");
		menuConfiguracoes.add(menuItemAbrirBD);

		
		JMenu menuSobre = new JMenu("Sobre");
		add(menuSobre);
		
		menuItemAutor = new JMenuItem("Autor");
		menuSobre.add(menuItemAutor);
		
		menuItemSair.setVisible(true);
		menuItemApagarTudo.setVisible(false);
		menuItemAdicionarRenda.setVisible(true);
		menuItemEditarRenda.setVisible(false);//
		menuItemRemoverRenda.setVisible(false);//
		menuItemAddDespesa.setVisible(true);
		menuItemEditarDespesa.setVisible(false);//
		menuItemExcluirDespesa.setVisible(false);//
		menuItemAddCategoria.setVisible(true);
		menuItemEditarCategoria.setVisible(true);
		menuItemExcluirCategoria.setVisible(false);//
		menuItemBalancoMensal.setVisible(true);
		menuItemBalancoMensalDespesas.setVisible(true);
		menuItemAbrirBD.setVisible(true); //TODO mudar pra false
		menuItemAutor.setVisible(true);
		
		//Adiciona o tratador de eventos
		menuItemSair.addActionListener(trataEventosMenu);
		menuItemApagarTudo.addActionListener(trataEventosMenu);
		
		menuItemAdicionarRenda.addActionListener(trataEventosMenu);
		menuItemEditarRenda.addActionListener(trataEventosMenu);
		menuItemRemoverRenda.addActionListener(trataEventosMenu);
		
		menuItemAddDespesa.addActionListener(trataEventosMenu);
		menuItemEditarDespesa.addActionListener(trataEventosMenu);
		menuItemExcluirDespesa.addActionListener(trataEventosMenu);
		
		menuItemAddCategoria.addActionListener(trataEventosMenu);
		menuItemEditarCategoria.addActionListener(trataEventosMenu);
		menuItemExcluirCategoria.addActionListener(trataEventosMenu);
		
		menuItemBalancoMensal.addActionListener(trataEventosMenu);
		menuItemBalancoMensalDespesas.addActionListener(trataEventosMenu);
		
		menuItemAbrirBD.addActionListener(trataEventosMenu);
		menuItemAutor.addActionListener(trataEventosMenu);
	}

	public JMenuItem getMenuItemSair() {
		return menuItemSair;
	}

	public JMenuItem getMenuItemApagarTudo() {
		return menuItemApagarTudo;
	}

	public JMenuItem getMenuItemAdicionarRenda() {
		return menuItemAdicionarRenda;
	}

	public JMenuItem getMenuItemAddDespesa() {
		return menuItemAddDespesa;
	}

	public JMenuItem getMenuItemAddCategoria() {
		return menuItemAddCategoria;
	}

	public JMenuItem getMenuItemBalancoMensal() {
		return menuItemBalancoMensal;
	}

	public JMenuItem getMenuItemBalancoMensalDespesas() {
		return menuItemBalancoMensalDespesas;
	}

	public JMenuItem getMenuItemAbrirBD() {
		return menuItemAbrirBD;
	}

	public JMenuItem getMenuItemAutor() {
		return menuItemAutor;
	}
	
}
