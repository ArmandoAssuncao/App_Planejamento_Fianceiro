package gui.framePrincipal;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eventos.menu.TEGuiMenu;
import gui.painelDespesas.IgPainelDespesas;
import gui.painelRenda.IgPainelRenda;

/**
 * Classe com a barra de menu da janela principal do programa.
 * @author Armando Assunção
 * @author Richardson William
 *
 */
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
	
	private IgPainelDespesas igPainelDespesas;
	private IgPainelRenda igPainelRenda;
	
	/**
	 * Construtor padrão.
	 */
	public GuiMenu() {
		//Menu
		//setPreferredSize(new Dimension(190,20));
		
		this.trataEventosMenu = new TEGuiMenu(this);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);
		add(menuArquivo);		

		menuItemApagarTudo = new JMenuItem("Apagar todos dados...");
		menuItemApagarTudo.setMnemonic(KeyEvent.VK_A);
		menuArquivo.add(menuItemApagarTudo);
		
		menuItemSair = new JMenuItem("Sair");
		menuItemSair.setMnemonic(KeyEvent.VK_S);
		menuArquivo.add(menuItemSair);
		
		JMenu menuRenda = new JMenu("Renda");
		menuRenda.setMnemonic(KeyEvent.VK_R);
		add(menuRenda);		

		menuItemAdicionarRenda = new JMenuItem("Adicionar Renda...");
		menuItemAdicionarRenda.setMnemonic(KeyEvent.VK_A);
		menuRenda.add(menuItemAdicionarRenda);
		
		menuItemEditarRenda = new JMenuItem("Editar Renda...");
		menuItemEditarRenda.setMnemonic(KeyEvent.VK_E);
		menuRenda.add(menuItemEditarRenda);
		
		menuItemRemoverRenda = new JMenuItem("Excluir Renda...");
		menuItemRemoverRenda.setMnemonic(KeyEvent.VK_X);
		menuRenda.add(menuItemRemoverRenda);
		
		
		
		JMenu menuCategoria = new JMenu("Categoria");
		menuCategoria.setMnemonic(KeyEvent.VK_C);
		add(menuCategoria);
		
		menuItemAddCategoria = new JMenuItem("Adicionar Categoria...");
		menuItemAddCategoria.setMnemonic(KeyEvent.VK_A);
		menuCategoria.add(menuItemAddCategoria);
		
		menuItemEditarCategoria = new JMenuItem("Editar Categoria...");
		menuItemEditarCategoria.setMnemonic(KeyEvent.VK_E);
		menuCategoria.add(menuItemEditarCategoria);
		
		menuItemExcluirCategoria = new JMenuItem("Excluir Categoria...");
		menuItemExcluirCategoria.setMnemonic(KeyEvent.VK_X);
		menuCategoria.add(menuItemExcluirCategoria);
		
		
		
		JMenu menuDespesa = new JMenu("Despesa");
		menuDespesa.setMnemonic(KeyEvent.VK_D);
		add(menuDespesa);
		
		menuItemAddDespesa = new JMenuItem("Adicionar Despesa...");
		menuItemAddDespesa.setMnemonic(KeyEvent.VK_A);
		menuDespesa.add(menuItemAddDespesa);
		
		menuItemEditarDespesa = new JMenuItem("Editar Despesa...");
		menuItemEditarDespesa.setMnemonic(KeyEvent.VK_E);
		menuDespesa.add(menuItemEditarDespesa);
		
		menuItemExcluirDespesa = new JMenuItem("Excluir Despesa...");
		menuItemExcluirDespesa.setMnemonic(KeyEvent.VK_X);
		menuDespesa.add(menuItemExcluirDespesa);
		
		
		
		JMenu menuBalanco = new JMenu("Balanços");
		menuBalanco.setMnemonic(KeyEvent.VK_B);
		add(menuBalanco);
		
		menuItemBalancoMensal = new JMenuItem("Balanço Mensal...");
		menuItemBalancoMensal.setMnemonic(KeyEvent.VK_M);
		menuBalanco.add(menuItemBalancoMensal);
		
		menuItemBalancoMensalDespesas = new JMenuItem("Balanço Despesas...");
		menuItemBalancoMensalDespesas.setMnemonic(KeyEvent.VK_D);
		menuBalanco.add(menuItemBalancoMensalDespesas);
		
		
		JMenu menuConfiguracoes = new JMenu("Configurações");
		menuConfiguracoes.setMnemonic(KeyEvent.VK_O);
		add(menuConfiguracoes);
		
		menuItemAbrirBD = new JMenuItem("Banco de Dados...");
		menuConfiguracoes.add(menuItemAbrirBD);

		
		JMenu menuSobre = new JMenu("Sobre");
		menuSobre.setMnemonic(KeyEvent.VK_S);
		add(menuSobre);
		
		menuItemAutor = new JMenuItem("Autor");
		menuItemAutor.setMnemonic(KeyEvent.VK_A);
		menuSobre.add(menuItemAutor);
		
		menuBalanco.setVisible(false);
		menuItemSair.setVisible(true);
		menuItemApagarTudo.setVisible(false);
		menuItemAdicionarRenda.setVisible(true);
		menuItemEditarRenda.setVisible(false);//
		menuItemRemoverRenda.setVisible(false);//
		menuItemAddDespesa.setVisible(true);
		menuItemEditarDespesa.setVisible(false);//
		menuItemExcluirDespesa.setVisible(false);//
		menuItemAddCategoria.setVisible(true);
		menuItemEditarCategoria.setVisible(false);//
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

	/**
	 * Retorna a referência do <code>JMenuItem</code> Sair.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemSair() {
		return menuItemSair;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Apagar Tudo.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemApagarTudo() {
		return menuItemApagarTudo;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Adicionar Renda.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemAdicionarRenda() {
		return menuItemAdicionarRenda;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Adicionar Despesa.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemAddDespesa() {
		return menuItemAddDespesa;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Adicionar Categoria.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemAddCategoria() {
		return menuItemAddCategoria;
	}
	
	/**
	 * Retorna a referência do <code>JMenuItem</code> Editar Categoria.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemEditarCategoria() {
		return menuItemEditarCategoria;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Balanço Mensal.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemBalancoMensal() {
		return menuItemBalancoMensal;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Balanco Mensal Despesas.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemBalancoMensalDespesas() {
		return menuItemBalancoMensalDespesas;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Abrir BD.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemAbrirBD() {
		return menuItemAbrirBD;
	}

	/**
	 * Retorna a referência do <code>JMenuItem</code> Autor.
	 * @return referência do <code>JMenuItem</code>.
	 */
	public JMenuItem getMenuItemAutor() {
		return menuItemAutor;
	}

	
	/**
	 * Atribui a referência para <code>IgPainelDespesas</code> 
	 * @param igPainelDespesas <code>IgPainelDespesas</code>
	 */
	public void setIgPainelDespesas(IgPainelDespesas igPainelDespesas) {
		this.igPainelDespesas = igPainelDespesas;
	}
	
	/**
	 * Retorna a referência do <code>IgPainelDespesas</code>
	 * @return referência do <code>IgPainelDespesas</code>.
	 */
	public IgPainelDespesas getIgPainelDespesas() {
		return igPainelDespesas;
	}
	
	/**
	 * Atribui a referência para <code>IgPainelRenda</code>
	 * @param igPainelRenda <code>IgPainelRenda</code>
	 */
	public void setIgPainelRenda(IgPainelRenda igPainelRenda) {
		this.igPainelRenda = igPainelRenda;
	}
	
	/**
	 * Retorna a referência do <code>IgPainelRenda</code>
	 * @return referência do <code>IgPainelRenda</code>.
	 */
	public IgPainelRenda getIgPainelRenda() {
		return igPainelRenda;
	}
	
}
