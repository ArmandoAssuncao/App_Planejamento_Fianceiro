
package gui.painelGraficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import classes.Despesa;
import classes.MetaMensal;
import persistencia.CategoriaDAO;
import persistencia.DespesaDAO;
import persistencia.MetaMensalDAO;
import net.miginfocom.swing.MigLayout;

/**
 * Painel de título da janela <code>PainelGraficos</code>.
 * 
 * @author Armando Assunção
 * @author Richardson William
 *
 *@see PainelGraficos
 */
public class PainelTituloPainelGraficos extends JPanel {
	private JLabel tituloLabel;
	private JPanel panel;
	private JLabel valorDadosUmLabel;
	private JLabel valorDadosTresLabel;
	private JLabel valorDadosDoisLabel;
	private JLabel valorDadosQuatroLabel;
	private JLabel valorCamposQuatroLabel;
	private JLabel valorCamposTresLabel;
	private JLabel valorCamposUmLabel;
	private JLabel valorCamposDoisLabel;
	private JLabel valorDadosCincoLabel;
	private JLabel valorCamposCincoLabel;
	
//	/*Define a variável de metas do mês estática, para evitar a pesquisa repetititva ao banco de dados.
//	 * Com a variável sendo estática somente é feito o acesso ao banco uma vez.
//	 */
//	private static List<MetaMensal> listaMetasDoMes;

	/**
	 * Construtor padrão.
	 */
	public PainelTituloPainelGraficos() {
		setPreferredSize(new Dimension(750,140));
		
		setBorder(new LineBorder(Color.RED)); //TODO debug, apagar
		
		setLayout(new BorderLayout(0, 0));
		
		tituloLabel = new JLabel("Relatório");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(tituloLabel, BorderLayout.NORTH);
		
		JPanel centroPanel = new JPanel();
		add(centroPanel, BorderLayout.CENTER);
		centroPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(7, 0, 736, 85);
		centroPanel.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][]"));
		
		valorDadosUmLabel = new JLabel("< >");
		valorDadosUmLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosUmLabel, "cell 0 0,alignx right,aligny center");
		
		valorCamposUmLabel = new JLabel("campo1");
		panel.add(valorCamposUmLabel, "cell 1 0,alignx left,aligny center");
		
		valorDadosDoisLabel = new JLabel("< >");
		valorDadosDoisLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosDoisLabel, "cell 9 0,alignx right,aligny center");
		
		valorCamposDoisLabel = new JLabel("campo2");
		panel.add(valorCamposDoisLabel, "cell 10 0,alignx left,aligny center");
		
		valorDadosCincoLabel = new JLabel("< >");
		valorDadosCincoLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosCincoLabel, "cell 18 0,alignx right");
		
		valorCamposCincoLabel = new JLabel("campo5");
		panel.add(valorCamposCincoLabel, "cell 19 0,alignx left");
		
		valorDadosTresLabel = new JLabel("< >");
		valorDadosTresLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosTresLabel, "cell 0 1,alignx right,aligny center");
		
		valorCamposTresLabel = new JLabel("campo3");
		panel.add(valorCamposTresLabel, "cell 1 1,alignx left,aligny center");
		
		valorDadosQuatroLabel = new JLabel("< >");
		valorDadosQuatroLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(valorDadosQuatroLabel, "cell 9 1,alignx right,aligny center");
		
		valorCamposQuatroLabel = new JLabel("campo4");
		panel.add(valorCamposQuatroLabel, "cell 10 1,alignx left,aligny center");
		
		setVisible(true);
	}//construtor
	
	/**
	 * Atualiza o painel título da aba gráficos, quando o gráfico gerado for de pizza.
	 * @param valores Valores numéricos a serem colocados no painel de título
	 * @param campos Campos correspondentes aos valores a serem colocados na barra de título
	 */
	public void atualizarPainelTituloGp(String[] campos, Double[] valores){
		valorCamposUmLabel.setText(campos[0]);
		valorDadosUmLabel.setText("R$ "+ String.format("%.2f", valores[0]));
		
		valorCamposDoisLabel.setText(campos[1]);
		valorDadosDoisLabel.setText("R$ "+ String.format("%.2f", valores[1]));
		
		valorCamposTresLabel.setText(campos[2]);
		valorDadosTresLabel.setText("R$ "+ String.format("%.2f", valores[2]));
		
		valorCamposQuatroLabel.setText(campos[3]);
		valorDadosQuatroLabel.setText("R$ "+ String.format("%.2f", valores[3]));
		
		valorCamposCincoLabel.setText("");
		valorDadosCincoLabel.setText("");
	}//atualizarPainel
	
	/**
	 * Atualiza o painel título da aba gráficos, quando o gráfico gerado for de linha.
	 * @param nomeCategoria nome da Categoria que está no gráfico de linha.
	 */
	public void atualizarPainelTituloGl(String nomeCategoria){
		valorCamposUmLabel.setText("");
		valorDadosUmLabel.setText(nomeCategoria);
		
		Double[] valores = obtemValoresMetaCategoria(nomeCategoria);
		
		if(valores[1] > 0)
			valorCamposDoisLabel.setText("para o alerta de gastos");
		else
			valorCamposDoisLabel.setText("desde o alerta de gastos");
		valorDadosDoisLabel.setText("R$ "+ String.format("%.2f", 50.0f));
		
		valorCamposTresLabel.setText("");
		valorDadosTresLabel.setText("");
		
		valorCamposQuatroLabel.setText("");
		valorDadosQuatroLabel.setText("");
		
		valorCamposCincoLabel.setText("limite de gastos");
		valorDadosCincoLabel.setText("R$ "+ String.format("%.2f", valores[0]));
	}

	/**
	 * 
	 * @return Retorna um array de Double com as seguintes informações:
	 *  	   posicao 0: valor da meta mensal.
	 * 		   posicao 1: quanto falta para atingir a meta mensal.
	 */
	private Double[] obtemValoresMetaCategoria(String nomeCategoria) {
		Double[] valores = new Double[2];
		
		try {
			
			List<MetaMensal> listaMetasDoMes = MetaMensalDAO.metaMensalDoMesAno(Calendar.getInstance());
			
			//Ordena as metas de acordo com o Id.
			listaMetasDoMes.sort(new Comparator<MetaMensal>() {

				@Override
				public int compare(MetaMensal o1, MetaMensal o2) {
					int id1 = o1.getId(),id2 = o2.getId();
					if(id1<id2) 
						return -1;
					else if(id1>id2)
						return 1;
					return 0;
				}
			});
			
			//Obtem o ID da categoria, que é o mesmo ID da metaMensal
			int idCategoria = new CategoriaDAO().getId(nomeCategoria);
			
			/*Armazena o valor da metaMensal na posicao  0 do array.
			 * O indice da categoria é o seu ID -1. ( Valor do Id menos um).
			 */
			valores[0] = listaMetasDoMes.get(idCategoria-1).getValor();
			
			//TODO obter a soma dos gastos da categoria indicada pelo  ID.
			List<Despesa> listaDespesas = DespesaDAO.despesasDoMesAno(Calendar.getInstance());

			listaDespesas.sort(new Comparator<Despesa>() {

				@Override
				public int compare(Despesa o1, Despesa o2) {
					int id1 = o1.getIdCategoria(),id2 = o2.getIdCategoria();
					if(id1<id2) 
						return -1;
					else if(id1>id2)
						return 1;
					return 0;
				}
			});
//			
			
			
			double somaDespesasDaCategoria =0;
			for(int i=0;i<listaDespesas.size();i++){
				Despesa d = listaDespesas.get(i);
				if(d.getIdCategoria() == idCategoria){
					somaDespesasDaCategoria += d.getValorDespesa();
				}	
			}
			
			valores[1] = valores[0]- somaDespesasDaCategoria;
			
		} catch (SQLException e) {
			System.out.println("Erro ao acessar metas mensais.");
			return null;
		}
		System.out.println("valores="+valores[0]+"   "+valores[1]);
		return valores;
	}
}//class PainelTituloPainelGraficos 