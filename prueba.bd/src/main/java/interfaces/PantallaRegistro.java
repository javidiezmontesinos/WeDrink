package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioNoExisteException;

public class PantallaRegistro extends JFrame {
	private Ventana ventana;
	private JTextField campoNombre;
	private JTextField campoCorreo;
	private JTextField campoNick;
	private JPasswordField campoContraseña;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public PantallaRegistro(Ventana v) {
		this.ventana=v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 39, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelTitulo = new JLabel("Regístrate");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 41));
		GridBagConstraints gbc_labelTitulo = new GridBagConstraints();
		gbc_labelTitulo.gridheight = 2;
		gbc_labelTitulo.gridwidth = 3;
		gbc_labelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitulo.gridx = 1;
		gbc_labelTitulo.gridy = 0;
		add(labelTitulo, gbc_labelTitulo);
	
		
		JLabel labelCorreo= new JLabel("Correo");
		GridBagConstraints gbc_labelCorreo = new GridBagConstraints();
		gbc_labelCorreo.fill = GridBagConstraints.VERTICAL;
		gbc_labelCorreo.anchor = GridBagConstraints.WEST;
		gbc_labelCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_labelCorreo.gridx = 1;
		gbc_labelCorreo.gridy = 2;
		add(labelCorreo, gbc_labelCorreo);
		
		campoCorreo = new JTextField();
		GridBagConstraints gbc_campoCorreo = new GridBagConstraints();
		gbc_campoCorreo.gridwidth = 2;
		gbc_campoCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_campoCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCorreo.gridx = 2;
		gbc_campoCorreo.gridy = 2;
		add(campoCorreo, gbc_campoCorreo);
		campoCorreo.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 3;
		add(labelNombre, gbc_labelNombre);
		
		campoNombre = new JTextField();
		GridBagConstraints gbc_campoNombre = new GridBagConstraints();
		gbc_campoNombre.gridwidth = 2;
		gbc_campoNombre.insets = new Insets(0, 0, 5, 5);
		gbc_campoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNombre.gridx = 2;
		gbc_campoNombre.gridy = 3;
		add(campoNombre, gbc_campoNombre);
		campoNombre.setColumns(10);
		
		JLabel labelNick = new JLabel("Nick");
		GridBagConstraints gbc_labelNick = new GridBagConstraints();
		gbc_labelNick.anchor = GridBagConstraints.WEST;
		gbc_labelNick.insets = new Insets(0, 0, 5, 5);
		gbc_labelNick.gridx = 1;
		gbc_labelNick.gridy = 4;
		add(labelNick, gbc_labelNick);
		
		campoNick = new JTextField();
		GridBagConstraints gbc_campoNick = new GridBagConstraints();
		gbc_campoNick.gridwidth = 2;
		gbc_campoNick.insets = new Insets(0, 0, 5, 5);
		gbc_campoNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNick.gridx = 2;
		gbc_campoNick.gridy = 4;
		add(campoNick, gbc_campoNick);
		campoNick.setColumns(10);
		
		JLabel labelContraseña = new JLabel("Contraseña");
		GridBagConstraints gbc_labelContraseña = new GridBagConstraints();
		gbc_labelContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_labelContraseña.anchor = GridBagConstraints.WEST;
		gbc_labelContraseña.gridx = 1;
		gbc_labelContraseña.gridy = 5;
		add(labelContraseña, gbc_labelContraseña);
		
		campoContraseña = new JPasswordField();
		GridBagConstraints gbc_campoContraseña = new GridBagConstraints();
		gbc_campoContraseña.gridwidth = 2;
		gbc_campoContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_campoContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoContraseña.gridx = 2;
		gbc_campoContraseña.gridy = 5;
		add(campoContraseña, gbc_campoContraseña);
		
		JLabel labelGenero = new JLabel("Genero");
		GridBagConstraints gbc_labelGenero = new GridBagConstraints();
		gbc_labelGenero.gridwidth = 3;
		gbc_labelGenero.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenero.gridx = 1;
		gbc_labelGenero.gridy = 6;
		add(labelGenero, gbc_labelGenero);
		
		JRadioButton radioHombre = new JRadioButton("Hombre");
		buttonGroup.add(radioHombre);
		GridBagConstraints gbc_radioHombre = new GridBagConstraints();
		gbc_radioHombre.insets = new Insets(0, 0, 5, 5);
		gbc_radioHombre.gridx = 1;
		gbc_radioHombre.gridy = 7;
		add(radioHombre, gbc_radioHombre);
		
		JRadioButton radioMujer = new JRadioButton("Mujer");
		buttonGroup.add(radioMujer);
		GridBagConstraints gbc_radioMujer = new GridBagConstraints();
		gbc_radioMujer.insets = new Insets(0, 0, 5, 5);
		gbc_radioMujer.gridx = 2;
		gbc_radioMujer.gridy = 7;
		add(radioMujer, gbc_radioMujer);
		
		JRadioButton radioOtro = new JRadioButton("Otro");
		buttonGroup.add(radioOtro);
		GridBagConstraints gbc_radioOtro = new GridBagConstraints();
		gbc_radioOtro.insets = new Insets(0, 0, 5, 5);
		gbc_radioOtro.gridx = 3;
		gbc_radioOtro.gridy = 7;
		add(radioOtro, gbc_radioOtro);
		
		
		
		JButton botonRegistrar = new JButton("Regístrate");
		botonRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)  {
				
				/*if(radioHombre.isSelected()) {
					
				} //Lo mismo con radio mujer y radio otro ...*/
				try {
					String nombre=campoNombre.getText().toString();
					String contraseña=new String(campoContraseña.getPassword());
					String nick=campoNick.getText();
					String correo=campoCorreo.getText();
					
					new Usuario(correo,contraseña);
					JOptionPane.showMessageDialog(ventana,"Registrado correctamente",
							"Éxito",JOptionPane.INFORMATION_MESSAGE);
					ventana.cambiarAPantalla(PantallaLogin.class);
				} catch (SQLIntegrityConstraintViolationException e3) {
					JOptionPane.showMessageDialog(ventana,"El email ya existe",
							"No se pudo registrar",JOptionPane.ERROR_MESSAGE);
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(ventana,e1.getMessage(),
							"No se puede conectar a la BD",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (UsuarioNoExisteException e1) {
					JOptionPane.showMessageDialog(ventana,e1.getMessage(),
							"Usuario no existe",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (ContraseñaInvalidaException e1) {
					JOptionPane.showMessageDialog(ventana,e1.getMessage(),
							"Contraseña Incorrecta, intentelo de nuevo",JOptionPane.ERROR_MESSAGE);				}
				
			}
		});
		botonRegistrar.setBackground(new Color(0, 0, 153));
		botonRegistrar.setFont(new Font("Arial", Font.ITALIC, 18));
		botonRegistrar.setForeground(Color.WHITE);
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrar.gridwidth = 3;
		gbc_botonRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrar.gridx = 1;
		gbc_botonRegistrar.gridy = 8;
		add(botonRegistrar, gbc_botonRegistrar);
		
		JButton botonLimpiar = new JButton("Limpiar formulario");
		botonLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoNombre.setText("");
				campoCorreo.setText("");
				campoNick.setText("");
				campoContraseña.setText("");
				buttonGroup.clearSelection();
			}
		});
		GridBagConstraints gbc_botonLimpiar = new GridBagConstraints();
		gbc_botonLimpiar.gridwidth = 3;
		gbc_botonLimpiar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_botonLimpiar.gridx = 1;
		gbc_botonLimpiar.gridy = 9;
		add(botonLimpiar, gbc_botonLimpiar);
		
		JButton botonAtras = new JButton("Cancelar");
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaLogin.class);
			}
		});
		GridBagConstraints gbc_botonAtras = new GridBagConstraints();
		gbc_botonAtras.fill = GridBagConstraints.VERTICAL;
		gbc_botonAtras.insets = new Insets(0, 0, 0, 5);
		gbc_botonAtras.gridx = 2;
		gbc_botonAtras.gridy = 11;
		add(botonAtras, gbc_botonAtras);
	}
	
}