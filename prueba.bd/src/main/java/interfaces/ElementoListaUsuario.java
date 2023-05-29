package interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;

public class ElementoListaUsuario extends JPanel{
	Ventana ventana;
	Usuario usuarioActual;
	public ElementoListaUsuario(Ventana v, Usuario c) {
		this.ventana=v;
		this.usuarioActual=c;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 78, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelEmail = new JLabel(usuarioActual.getCorreo());
		labelEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.WEST;
		gbc_labelEmail.gridwidth = 4;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 0;
		gbc_labelEmail.gridy = 0;
		add(labelEmail, gbc_labelEmail);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton.gridheight = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel labelNombre = new JLabel(usuarioActual.getNombre());
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 3;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 0;
		gbc_labelNombre.gridy = 1;
		add(labelNombre, gbc_labelNombre);
		
		JLabel labelNick = new JLabel(""+usuarioActual.getNick());
		GridBagConstraints gbc_labelNick = new GridBagConstraints();
		gbc_labelNick.gridwidth = 3;
		gbc_labelNick.insets = new Insets(0, 0, 0, 5);
		gbc_labelNick.gridx = 0;
		gbc_labelNick.gridy = 2;
		add(labelNick, gbc_labelNick);
		ventana=v;
		usuarioActual=c;
	}
	

}
