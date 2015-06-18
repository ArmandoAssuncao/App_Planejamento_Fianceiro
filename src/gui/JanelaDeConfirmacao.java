package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JanelaDeConfirmacao extends JDialog {
	private boolean confirmar;
	
	public JanelaDeConfirmacao(String titulo, String msgAviso) {
		setTitle(titulo);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(53, 11, 243, 62);
		textArea.setText(msgAviso);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFont(new Font("Serif", Font.BOLD, 15));
		textArea.setPreferredSize(new Dimension(50,50));
		
		
		JButton botaoFechar = new JButton("Fechar");
		botaoFechar.setBounds(187, 88, 89, 23);
		botaoFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JanelaDeConfirmacao.this.dispose();
			}
		});
		
		JButton botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setBounds(74, 88, 89, 23);
		botaoConfirmar.setMargin(new Insets(0, 0, 0, 0));
		botaoConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmar = true;
				JanelaDeConfirmacao.this.dispose();
			}
		});
		
		
		panel.add(textArea);
		panel.add(botaoFechar);
		panel.add(botaoConfirmar);
		
		setSize(350, 150);
		setLocationRelativeTo(null); //Passar janela pai aqui TALVEZ //////////////////////////
		setType(Type.POPUP);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public boolean isConfirmar() {
		return confirmar;
	}
	
}
