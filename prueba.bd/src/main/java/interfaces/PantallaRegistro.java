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
import exceptions.ConexionFallidaException;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioNoExisteException;

public class PantallaRegistro extends PanelMadre {
	private Ventana ventana;
	private JTextField campoNombre;
	private JTextField campoCorreo;
	private JTextField campoNick;
	private JTextField campoLocalidad;
	private JTextField campoDireccion;
	private JTextField campoApellidos;
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
		
		JLabel labelEmail = new JLabel("Email");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.fill = GridBagConstraints.VERTICAL;
		gbc_labelEmail.anchor = GridBagConstraints.WEST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 2;
		add(labelEmail, gbc_labelEmail);
		
		campoCorreo = new JTextField();
		GridBagConstraints gbc_campoEmail = new GridBagConstraints();
		gbc_campoEmail.gridwidth = 2;
		gbc_campoEmail.insets = new Insets(0, 0, 5, 5);
		gbc_campoEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoEmail.gridx = 2;
		gbc_campoEmail.gridy = 2;
		add(campoCorreo, gbc_campoEmail);
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
		
		JLabel labelApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_labelApellidos = new GridBagConstraints();
		gbc_labelApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellidos.gridx = 1;
		gbc_labelApellidos.gridy = 4;
		add(labelApellidos, gbc_labelApellidos);
		
		campoApellidos = new JTextField();
		GridBagConstraints gbc_campoApellidos = new GridBagConstraints();
		gbc_campoApellidos.gridwidth = 2;
		gbc_campoApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_campoApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoApellidos.gridx = 2;
		gbc_campoApellidos.gridy = 4;
		add(campoApellidos, gbc_campoApellidos);
		campoApellidos.setColumns(10);
		
		JLabel labelNick = new JLabel("Nick");
		GridBagConstraints gbc_labelNick = new GridBagConstraints();
		gbc_labelNick.anchor = GridBagConstraints.WEST;
		gbc_labelNick.insets = new Insets(0, 0, 5, 5);
		gbc_labelNick.gridx = 1;
		gbc_labelNick.gridy = 5;
		add(labelNick, gbc_labelNick);
		
		campoNick = new JTextField();
		GridBagConstraints gbc_campoNick = new GridBagConstraints();
		gbc_campoNick.gridwidth = 2;
		gbc_campoNick.insets = new Insets(0, 0, 5, 5);
		gbc_campoNick.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNick.gridx = 2;
		gbc_campoNick.gridy = 5;
		add(campoNick, gbc_campoNick);
		campoNick.setColumns(10);
		
		JLabel labelLocalidad = new JLabel("Localidad");
		GridBagConstraints gbc_labelLocalidad = new GridBagConstraints();
		gbc_labelLocalidad.anchor = GridBagConstraints.WEST;
		gbc_labelLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_labelLocalidad.gridx = 1;
		gbc_labelLocalidad.gridy = 6;
		add(labelLocalidad, gbc_labelLocalidad);
		
		campoLocalidad = new JTextField();
		GridBagConstraints gbc_campoLocalidad = new GridBagConstraints();
		gbc_campoLocalidad.gridwidth = 2;
		gbc_campoLocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_campoLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoLocalidad.gridx = 2;
		gbc_campoLocalidad.gridy = 6;
		add(campoLocalidad, gbc_campoLocalidad);
		campoLocalidad.setColumns(10);
		
		JLabel labeldireccion = new JLabel("Direccion");
		GridBagConstraints gbc_labeldireccion = new GridBagConstraints();
		gbc_labeldireccion.anchor = GridBagConstraints.WEST;
		gbc_labeldireccion.insets = new Insets(0, 0, 5, 5);
		gbc_labeldireccion.gridx = 1;
		gbc_labeldireccion.gridy = 7;
		add(labeldireccion, gbc_labeldireccion);
		
		campoDireccion = new JTextField();
		GridBagConstraints gbc_campoDireccion = new GridBagConstraints();
		gbc_campoDireccion.gridwidth = 2;
		gbc_campoDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_campoDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoDireccion.gridx = 2;
		gbc_campoDireccion.gridy = 7;
		add(campoDireccion, gbc_campoDireccion);
		campoDireccion.setColumns(10);
		
		JLabel labelContraseña = new JLabel("Contraseña");
		GridBagConstraints gbc_labelContraseña = new GridBagConstraints();
		gbc_labelContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_labelContraseña.anchor = GridBagConstraints.WEST;
		gbc_labelContraseña.gridx = 1;
		gbc_labelContraseña.gridy = 8;
		add(labelContraseña, gbc_labelContraseña);
		
		campoContraseña = new JPasswordField();
		GridBagConstraints gbc_campoContraseña = new GridBagConstraints();
		gbc_campoContraseña.gridwidth = 2;
		gbc_campoContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_campoContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoContraseña.gridx = 2;
		gbc_campoContraseña.gridy = 8;
		add(campoContraseña, gbc_campoContraseña);
		
		JLabel labelGenero = new JLabel("Genero");
		GridBagConstraints gbc_labelGenero = new GridBagConstraints();
		gbc_labelGenero.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenero.anchor = GridBagConstraints.WEST;
		gbc_labelGenero.gridx = 1;
		gbc_labelGenero.gridy = 9;
		add(labelGenero, gbc_labelGenero);
		
		JRadioButton radioHombre = new JRadioButton("Hombre");
		buttonGroup.add(radioHombre);
		GridBagConstraints gbc_radioHombre = new GridBagConstraints();
		gbc_radioHombre.insets = new Insets(0, 0, 5, 5);
		gbc_radioHombre.gridx = 2;
		gbc_radioHombre.gridy = 9;
		add(radioHombre, gbc_radioHombre);
		
		JRadioButton radioMujer = new JRadioButton("Mujer");
		buttonGroup.add(radioMujer);
		GridBagConstraints gbc_radioMujer = new GridBagConstraints();
		gbc_radioMujer.insets = new Insets(0, 0, 5, 5);
		gbc_radioMujer.gridx = 3;
		gbc_radioMujer.gridy = 9;
		add(radioMujer, gbc_radioMujer);
		
		JButton botonRegistrar = new JButton("Regístrate");
		botonRegistrar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        try {
		            String nombre = campoNombre.getText();
		            String contraseña = new String(campoContraseña.getPassword());
		            String nick = campoNick.getText();
		            String correo = campoCorreo.getText();
		            String localidad = campoLocalidad.getText(); 
		            String direccion = campoDireccion.getText(); 
		            String qrCode = ""; 
		            String apellidos = campoApellidos.getText(); 

		            Usuario.registrar_usuario(nick, contraseña, nombre, correo, localidad, direccion, qrCode, apellidos);
		            JOptionPane.showMessageDialog(ventana, "Registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            ventana.cambiarAPantalla(PantallaLogin.class);
		        } catch (SQLIntegrityConstraintViolationException e3) {
		            JOptionPane.showMessageDialog(ventana, "El nick o el correo ya existen", "No se pudo registrar", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException e1) {
		            JOptionPane.showMessageDialog(ventana, e1.getMessage(), "No se puede conectar a la BD", JOptionPane.ERROR_MESSAGE);
		            e1.printStackTrace();
		        } catch (NumberFormatException e2) {
		            JOptionPane.showMessageDialog(ventana, "Tienes que poner un número de teléfono que sea un número", "Número de telefono incorrecto", JOptionPane.ERROR_MESSAGE);
		        } catch (UsuarioNoExisteException e1) {
		            e1.printStackTrace();
		        } catch (ConexionFallidaException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		

		botonRegistrar.setBackground(new Color(0, 0, 120));
		botonRegistrar.setFont(new Font("Arial", Font.ITALIC, 15));
		botonRegistrar.setForeground(Color.WHITE);
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrar.gridwidth = 3;
		gbc_botonRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrar.gridx = 1;
		gbc_botonRegistrar.gridy = 13;
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
		gbc_botonLimpiar.fill = GridBagConstraints.VERTICAL;
		gbc_botonLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_botonLimpiar.gridx = 2;
		gbc_botonLimpiar.gridy = 15;
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
		gbc_botonAtras.insets = new Insets(0, 0, 5, 5);
		gbc_botonAtras.gridx = 1;
		gbc_botonAtras.gridy = 15;
		add(botonAtras, gbc_botonAtras);
	}
	
}
