package gui;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaAviso extends JDialog {
	
	public JanelaAviso(String titulo, String msgAviso) {
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
		botaoFechar.setBounds(129, 88, 89, 23);
		botaoFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaAviso.this.dispose();
			}
		});
		
		panel.add(textArea);
		panel.add(botaoFechar);
		
		setSize(350, 150);
		setLocationRelativeTo(null); //Passar janela pai aqui TALVEZ //////////////////////////
		setType(Type.POPUP);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
