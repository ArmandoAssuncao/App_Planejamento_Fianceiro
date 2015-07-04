package gui.painelDespesas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Painel de título da janela <code>IgPainelDespeas</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see IgPainelDespesas
 */
public class PainelTituloPainelDespesas extends JPanel {
	private JLabel valorTotalDespesasLabel;
	private JLabel alertaLabel;
	private JLabel nomeCategoriaLabel;
	private JLabel valorQtdDespesasLabel;
	private JProgressBar metaProgressBar;
	private JLabel gastoPrevistoLabel;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelDespesas() {
		setSize(750, 140);
		setPreferredSize(new Dimension(750,140));
		setLayout(new BorderLayout(0, 0));
		
		metaProgressBar = new JProgressBar();
		metaProgressBar.setStringPainted(true);
		add(metaProgressBar, BorderLayout.SOUTH);
		
		nomeCategoriaLabel = new JLabel();
		nomeCategoriaLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		nomeCategoriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(nomeCategoriaLabel, BorderLayout.NORTH);
		
		JPanel centroPanel = new JPanel();
		add(centroPanel, BorderLayout.CENTER);
		centroPanel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][]", "[][][][][][]"));
		
		valorQtdDespesasLabel = new JLabel();
		valorQtdDespesasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		centroPanel.add(valorQtdDespesasLabel, "cell 1 1");
		
		JLabel qtdDespesasLabel = new JLabel("despesas cadatradas");
		centroPanel.add(qtdDespesasLabel, "cell 2 1");
		
		valorTotalDespesasLabel = new JLabel();
		valorTotalDespesasLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		centroPanel.add(valorTotalDespesasLabel, "cell 6 1 2 1");
		
		JLabel totalDespesasLabel = new JLabel("total das despesas");
		centroPanel.add(totalDespesasLabel, "cell 8 1");
		
		gastoPrevistoLabel = new JLabel("");
		gastoPrevistoLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		gastoPrevistoLabel.setEnabled(true);
		centroPanel.add(gastoPrevistoLabel, "cell 13 1");
		
		JLabel metaLabel = new JLabel("de gasto previsto");
		centroPanel.add(metaLabel, "cell 14 1");
		
		alertaLabel = new JLabel();
		alertaLabel.setToolTipText("");
		alertaLabel.setForeground(Color.BLACK);
		centroPanel.add(alertaLabel, "cell 8 5,alignx center,aligny baseline");
		
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel de despesas.
	 * @param nomeCategoria nome da categoria
	 * @param qtdDespesas quantidade de despesas cadastradas
	 * @param somaDespesas soma de todas as despesas
	 * @param metaPorcentagem meta em porcentagem de gastos definida pelo usuário 
	 * @param valorMetaDinheiro valor estimado de gastos em dinheiro
	 */
	public void atualizarPainel(String nomeCategoria,int qtdDespesas, double somaDespesas, double metaPorcentagem,
			double valorMetaDinheiro){
		System.out.printf("\n%s %d %f %f %f",nomeCategoria,qtdDespesas,somaDespesas,metaPorcentagem,valorMetaDinheiro );
		nomeCategoriaLabel.setText(nomeCategoria);
		valorQtdDespesasLabel.setText(Integer.toString(qtdDespesas));
		valorTotalDespesasLabel.setText("R$ " + Double.toString(somaDespesas));
		gastoPrevistoLabel.setText("R$ " + Double.toString(valorMetaDinheiro));
		
		double porcentagemGasta = somaDespesas/valorMetaDinheiro*100;
		
		/*Define a cor da barra de progresso, entre verde, amarelo e vermelho. E exibe uma
		 * mensagem de alerta, se necessário. 
		 */
		 if (porcentagemGasta >= 100){
				metaProgressBar.setForeground(Color.RED);
				alertaLabel.setText("Você ultrapassou a meta de gastos em 100%");
		}else if(porcentagemGasta > metaPorcentagem){
			metaProgressBar.setForeground(new Color(204, 204, 0)); //Amarelo Escuro
			alertaLabel.setText("Você ultrapassou a margem de segurança de gastos");	
		}else{
			metaProgressBar.setForeground(new Color(99,209,62)); //Verde Escuro
			alertaLabel.setText("");	
		}
		metaProgressBar.setValue((int)porcentagemGasta);
	}//atualizarPainel
}//class PainelTituloPainelDespesas
