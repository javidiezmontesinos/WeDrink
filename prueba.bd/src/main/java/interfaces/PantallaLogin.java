package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import exceptions.UsuarioNoExisteException;
import exceptions.ConexionFallidaException;
import exceptions.ContraseñaInvalidaException;

public class PantallaLogin extends PanelMadre {
	private JTextField campoUsuario;
	private JPasswordField campoContraseña;
	private Ventana ventana;

	public PantallaLogin(Ventana v) {
		this.ventana = v;
		setLayout(null);

		JButton botonLogin = new JButton("Iniciar Sesión");
		botonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String correo = campoUsuario.getText();
				String contraseña = new String(campoContraseña.getPassword());
				System.out.println(correo + " : " + contraseña);
				try {
					String email = campoUsuario.getText();
					String password = new String(campoContraseña.getPassword());

					if (email.isEmpty() || password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos obligatorios");
						return;
					}
					Usuario usuario = new Usuario(correo, contraseña);

					if (usuario.getContraseña().trim().equalsIgnoreCase(contraseña.trim())) {
						ventana.clienteLogado = usuario;
						JOptionPane.showMessageDialog(ventana, "Bienvenid@, " + ventana.clienteLogado.getNick(),
								"Inicio de sesión exitoso", JOptionPane.INFORMATION_MESSAGE);

						if (ventana.clienteLogado.getNick().equalsIgnoreCase("admin")) {
							ventana.cambiarAPantalla(VentanaAdmins.class);
						} else {
							ventana.cambiarAPantalla(VentanaUsuario.class);
						}
					} else {
						JOptionPane.showMessageDialog(ventana, "La contraseña no es correcta", "Login fallido",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Login fallido", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (UsuarioNoExisteException e1) {
					JOptionPane.showMessageDialog(ventana, "El cliente no existe", "Login fallido",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (ContraseñaInvalidaException e1) {
					JOptionPane.showMessageDialog(ventana, "La contraseña no es correcta", "Login fallido",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (ConexionFallidaException e1) {
					JOptionPane.showMessageDialog(ventana, "Error al conectar a la base de datos", "Login fallido",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});

		botonLogin.setBounds(100, 150, 200, 30);
		add(botonLogin);

		botonLogin.setToolTipText("Pínchame para iniciar sesión");
		botonLogin.setForeground(new Color(0, 64, 0));
		botonLogin.setFont(new Font("Arial", Font.BOLD, 18));
		botonLogin.setBackground(new Color(70, 193, 91));
		botonLogin.setBounds(320, 269, 192, 93);
		add(botonLogin);

		JButton botonRegistro = new JButton("Regístrate");
		botonRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.cambiarAPantalla(PantallaRegistro.class);
			}
		});
		botonRegistro.setToolTipText("Pínchame para ir a registrarte");
		botonRegistro.setForeground(new Color(255, 255, 255));
		botonRegistro.setFont(new Font("Arial", Font.BOLD, 18));
		botonRegistro.setBackground(new Color(0, 64, 128));
		botonRegistro.setBounds(90, 269, 192, 93);
		add(botonRegistro);

		campoUsuario = new JTextField();
		campoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		campoUsuario.setFont(new Font("Arial", Font.ITALIC, 25));
		campoUsuario.setBounds(37, 68, 529, 61);
		add(campoUsuario);
		campoUsuario.setColumns(10);

		JLabel etiquetaUsuario = new JLabel("Correo Electrónico");
		etiquetaUsuario.setFont(new Font("SansSerif", Font.PLAIN, 26));
		etiquetaUsuario.setBounds(36, 37, 224, 39);
		add(etiquetaUsuario);

		campoContraseña = new JPasswordField();
		campoContraseña.setFont(new Font("Arial", Font.ITALIC, 25));
		campoContraseña.setBounds(37, 179, 529, 61);
		add(campoContraseña);

		JLabel etiquetaContraseña = new JLabel("Contraseña");
		etiquetaContraseña.setFont(new Font("SansSerif", Font.PLAIN, 26));
		etiquetaContraseña.setBounds(37, 140, 224, 39);
		add(etiquetaContraseña);
	}
}
