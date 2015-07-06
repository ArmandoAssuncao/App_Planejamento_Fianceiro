package gui.painelRenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import persistencia.RendaDAO;
import persistencia.RendaMensalDAO;
import classes.Renda;
import classes.RendaMensal;
import eventos.painelRenda.TEPainelRenda;
import funcoes.Converte;

/**
Define o painel de <code>Renda</code>.
* @author Armando Assunção
* @author Richardson William
*
*/
public class IgPainelRenda extends JPanel {
	private static final long serialVersionUID = -5820571568093892597L;
	
	private final int TAM_TABELA_X = 750;
	private final int TAM_TABELA_Y = 500;
	
	ArrayList<RendaMensal> arrayListRendaMensal;
	private TEPainelRenda trataEventosRenda;
	private TabelaRendaMensal tabelaRendaMensal;
	private JScrollPane barraRolagem;
	
	private JPanel painelBotoes;
	private JPanel painelSuperior;
	private PainelTituloPainelRenda painelTitulo;
	
	private JButton botaoAddRenda;
	private JButton botaoExcluirRenda;
	private JButton botaoEditarRenda;
	
	/**
	 * Construtor padrão.
	 * @param framePrincipal componente pai.
	 */
	public IgPainelRenda(Window framePrincipal) {
		setLayout(new BorderLayout(0,5));

		trataEventosRenda = new TEPainelRenda(this, framePrincipal);
		iniciaElementos();
		//criaPainelTitulo();
		criaPainelBotoes();
		iniciaValoresRenda();
		
		//barra de rolagem da tabela
		barraRolagem = new JScrollPane();
		barraRolagem.setViewportView(tabelaRendaMensal);
		barraRolagem.setPreferredSize(new Dimension(TAM_TABELA_X, TAM_TABELA_Y));

		painelSuperior.setLayout(new BorderLayout());
		painelSuperior.setBackground(new Color(205, 205, 205));//TODO COR
		painelSuperior.add(painelTitulo, BorderLayout.WEST);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(barraRolagem,BorderLayout.WEST);
		add(painelBotoes,BorderLayout.EAST);
		add(tabelaRendaMensal.getTableHeader(),BorderLayout.PAGE_START);
		
		painelBotoes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));//TODO BORDA
		
		botaoExcluirRenda.setVisible(false); 
		botaoEditarRenda.setVisible(false);

		setBackground(new Color(205, 205, 205));//TODO COR
		setVisible(true);
	}

	private void iniciaElementos() {
		arrayListRendaMensal = new ArrayList<RendaMensal>();
		
		painelBotoes = new JPanel();
		painelSuperior = new JPanel();
		painelTitulo = new PainelTituloPainelRenda();
		
		botaoAddRenda = new JButton();
		botaoExcluirRenda = new JButton();
		botaoEditarRenda = new JButton();
		
		tabelaRendaMensal = new TabelaRendaMensal();
	}//iniciaElementos()

	private void criaPainelBotoes(){
		final int TAM_X = 200;
		final int TAM_Y = 0;
		
		//Define o layout
		painelBotoes.setLayout(new GridBagLayout());
		
		Font fonteBold = new Font(new JLabel().getFont().getName(), Font.BOLD, 11);
		Insets insetsBotao = new Insets(0,10,0,0);
		
		//configurações do layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0, 0, 25, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		//botao Adicionar Renda
		String labelbotaoAddRenda = "Adicionar Renda";
		ImageIcon iconeNovaCategoria = new ImageIcon("imagens/img_botaoAdicionarRenda.png");
		botaoAddRenda.setText(labelbotaoAddRenda);
		botaoAddRenda.setFont(fonteBold);
		botaoAddRenda.setMargin(insetsBotao);
		botaoAddRenda.setIcon(iconeNovaCategoria);
		botaoAddRenda.setHorizontalAlignment(SwingConstants.LEFT);
		botaoAddRenda.setIconTextGap(5);
		botaoAddRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoAddRenda.setPreferredSize(new Dimension(150,50));
		botaoAddRenda.setMnemonic(KeyEvent.VK_I);
		botaoAddRenda.addActionListener(trataEventosRenda);
		botaoAddRenda.setBackground(null);
		
		//botao Excluir Renda
		String labelBotaoExcluirRenda = "Excluir Renda";
		ImageIcon iconeExcluirRenda = new ImageIcon("imagens/img_botaoExcluirCategoria.png");
		botaoExcluirRenda.setText(labelBotaoExcluirRenda);
		botaoExcluirRenda.setFont(fonteBold);
		botaoExcluirRenda.setMargin(insetsBotao);
		botaoExcluirRenda.setIcon(iconeExcluirRenda);
		botaoExcluirRenda.setHorizontalAlignment(SwingConstants.LEFT);
		botaoExcluirRenda.setIconTextGap(5);
		botaoExcluirRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoExcluirRenda.setPreferredSize(new Dimension(150,50));
		botaoExcluirRenda.addActionListener(trataEventosRenda);
		botaoExcluirRenda.setBackground(null);
		
		//botao Editar Renda
		String labelBotaoEditarRenda = "Editar Renda";
		ImageIcon iconeEditarRenda = new ImageIcon("imagens/img_botaoEditarCategoria.png");
		botaoEditarRenda.setText(labelBotaoEditarRenda);
		botaoEditarRenda.setFont(fonteBold);
		botaoEditarRenda.setMargin(insetsBotao);
		botaoEditarRenda.setIcon(iconeEditarRenda);
		botaoEditarRenda.setHorizontalAlignment(SwingConstants.LEFT);
		botaoEditarRenda.setIconTextGap(5);
		botaoEditarRenda.setHorizontalTextPosition(JButton.RIGHT);
		botaoEditarRenda.setPreferredSize(new Dimension(150,50));
		botaoEditarRenda.addActionListener(trataEventosRenda);
		botaoEditarRenda.setBackground(null);
		
		//adiciona os bot�es
		painelBotoes.add(botaoAddRenda, constraints);
		constraints.gridy = 1;
		painelBotoes.add(botaoExcluirRenda,constraints);
		constraints.gridy = 2;
		painelBotoes.add(botaoEditarRenda,constraints);
		
		painelBotoes.setPreferredSize(new Dimension(TAM_X, TAM_Y));
		painelBotoes.setBackground(new Color(205, 205, 205));//TODO COR
		painelBotoes.setVisible(true);
	}//criaPainelBotoes

	/**
	 * Cria uma <code>Renda</code> e adiciona à tabela. 
	 * @param renda objeto <code>Renda</code> a ser adicionado
	 * @param data objeto <code>Calendar</code> a ser adicionado
	 * @return <code>true</code> em caso de sucesso na operação, <code>false</code> em caso contrário.
	 */
	public boolean criarRenda(Renda renda,Calendar data){
		RendaMensal rm = renda.obterRendaMensal(data);
		tabelaRendaMensal.adicionaLinha(renda.getDescricao(),Converte.calendarToString(rm.getDataRenda()),Double.toString(rm.getValor()));
		System.out.println("Renda criada"); //TODO debug
		
		atualizarPainelTitulo();
		return true;
	} 
	
	private void atualizarPainelTitulo() {
		//Obtem a qtd de rendas e a soma
		int qtd = tabelaRendaMensal.qtdRendasMensal();
		double soma = tabelaRendaMensal.getSomaRendasMensal();
		painelTitulo.atualizarPainel(qtd, soma);
	}

	private void iniciaValoresRenda(){
		try {
			Set<Renda> rendas = new HashSet<Renda>();
			List<Renda> rendaTemp = RendaDAO.todasAsRendas();
			List<RendaMensal> rendaMensalTemp = RendaMensalDAO.todasAsRendasMensais();
			
			for(Renda renda : rendaTemp){
				int idRenda = new RendaDAO().getId(renda.getDescricao());				
				for(RendaMensal rendaMensal : rendaMensalTemp){
					if(idRenda == new RendaMensalDAO().getId(renda.getDescricao(),Converte.calendarToString(rendaMensal.getDataRenda()))){
						renda.adicionarRendaMensal(rendaMensal);
					}
				}//for
				rendas.add(renda);
			}//for

			//Adiciona os valores de renda na interface gráfica.
			for(Iterator<Renda> iteratorRenda = rendas.iterator();iteratorRenda.hasNext();){
				Renda r = iteratorRenda.next();
				for(Iterator<RendaMensal> rmIterator = r.getRendasMensais().iterator(); rmIterator.hasNext();){
					RendaMensal rm = rmIterator.next();
					tabelaRendaMensal.adicionaLinha(r.getDescricao(),Converte.calendarToString(rm.getDataRenda()), Double.toString(rm.getValor()));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		atualizarPainelTitulo();
	}
	
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão adicionar renda.
	 */
	public JButton getBotaoAddRenda() {
		return botaoAddRenda;
	}
	
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão excluir renda.
	 */
	public JButton getBotaoExcluirRenda() {
		return botaoExcluirRenda;
	}
	
	/**
	 *  Retorna uma referência de um <code>JButton</code>.
	 * @return botão editar renda.
	 */
	public JButton getBotaoEditarRenda() {
		return botaoEditarRenda;
	}

}