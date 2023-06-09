package interfaces;

import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import clases.Evento;
import clases.Logro;
import clases.Premio;
import clases.Producto;
import clases.Usuario;
import clases.UsuarioPuntos;
import exceptions.ConexionFallidaException;
import clases.Producto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VentanaUsuario extends JPanel {
	private Ventana ventana;
	private Usuario usuario;
	Usuario usuarioActual;
	JPanel panelProductos;

	public VentanaUsuario(Ventana v, Usuario c) {
		this.usuarioActual = c;
		this.ventana = v;
		setLayout(new BorderLayout(0, 0));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.CYAN);
		panelSuperior.setLayout(new BorderLayout());

		ImageIcon originalIconlogo = new ImageIcon("logowedrinkprueba.png");
		Image originalImagelogo = originalIconlogo.getImage();
		int resizedWidth = 100; 
		int resizedHeight = 50; 
		Image resizedImagelogo = originalImagelogo.getScaledInstance(resizedWidth, resizedHeight,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIconlogo = new ImageIcon(resizedImagelogo);
		JLabel labelLogo = new JLabel(resizedIconlogo);
		panelSuperior.add(labelLogo, BorderLayout.WEST);

		
		JPanel panelDatosUsuario = new JPanel();
		panelDatosUsuario.setBackground(Color.BLACK);
		panelDatosUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

		JLabel labelUsuario = new JLabel(usuarioActual.getNick());
		Usuario.class.getName();

		labelUsuario.setForeground(Color.WHITE);
		panelDatosUsuario.add(labelUsuario);

		JLabel labelPuntos = new JLabel("Puntos: " + usuarioActual.getPuntosTotales());
		labelPuntos.setForeground(Color.WHITE);
		panelDatosUsuario.add(labelPuntos);

		panelSuperior.add(panelDatosUsuario, BorderLayout.EAST);

		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(panelCentral);

		
		JPanel panelBotonesSuperior = new JPanel();
		panelBotonesSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	
		ImageIcon originalIcon = new ImageIcon("promocionpng.png");
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(32, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

	
		JButton boton1 = new JButton(resizedIcon);
		panelBotonesSuperior.add(boton1);

		
		panelProductos = new JPanel();
		panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
		// Crear un nuevo panel para los productos y agregarle un JScrollPane
		JPanel panelProductosContainer = new JPanel(new BorderLayout());
		JScrollPane scrollPaneProductos = new JScrollPane(panelProductos);
		panelProductosContainer.add(scrollPaneProductos, BorderLayout.CENTER);

		boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
				
				List<Producto> productos = Producto.obtenerProductos();
				JTextField nombreCategoria = new JTextField("Productos en promocion");
				panelProductos.add(nombreCategoria);
			
				for (Producto producto : productos) {
					String imageUrl = producto.getImagenUrl();
					if (imageUrl != null && !imageUrl.isEmpty()) { 
						try {
							
							URL url = new URL(imageUrl);
							Image productoImage = ImageIO.read(url);
							Image resizedProductoImage = productoImage.getScaledInstance(25, 25,
									java.awt.Image.SCALE_SMOOTH);
							ImageIcon resizedProductoIcon = new ImageIcon(resizedProductoImage);

							
							JCheckBox productoCheckBox = new JCheckBox(
									producto.getNombre() + " - " + producto.getPrecioProducto() + " - "
											+ producto.getPuntosPorCompra() + " - " + producto.getDescripcion(),
									resizedProductoIcon, false);

							panelProductos.add(productoCheckBox);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});

		panelCentral.add(panelBotonesSuperior, BorderLayout.NORTH);
		
		panelCentral.add(panelProductosContainer, BorderLayout.CENTER); 

		ImageIcon originalIcon2 = new ImageIcon("eventoslogo.jpeg");
		Image originalImage2 = originalIcon2.getImage();
		Image resizedImage2 = originalImage2.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);

		JButton boton2 = new JButton(resizedIcon2);
		panelBotonesSuperior.add(boton2);
		boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
			
				List<Evento> eventos = Evento.obtenerEventos();
				JTextField nombreCategoria = new JTextField("Eventos");
				panelProductos.add(nombreCategoria);
			
				for (Evento evento : eventos) {
					String imageUrl = evento.getImagenUrl();
					if (imageUrl != null && !imageUrl.isEmpty()) { 
						try {
						
							URL url = new URL(imageUrl);
							Image productoEvento = ImageIO.read(url);
							Image resizedEventoImage = productoEvento.getScaledInstance(25, 25,
									java.awt.Image.SCALE_SMOOTH);
							ImageIcon resizedEventoIcon = new ImageIcon(resizedEventoImage);

							
							JCheckBox eventoCheckBox = new JCheckBox(
									evento.getNombre() + " - " + evento.getLocalidad() + " - " + evento.getFecha(),
									resizedEventoIcon);

							panelProductos.add(eventoCheckBox);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});

		ImageIcon originalIcon3 = new ImageIcon("premioslogo.png");
		Image originalImage3 = originalIcon3.getImage();
		Image resizedImage3 = originalImage3.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);

		JButton boton3 = new JButton(resizedIcon3);
		panelBotonesSuperior.add(boton3);
		boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
			
				List<Logro> logros = Logro.obtenerLogros();
				JTextField nombreCategoria = new JTextField("Logros");
				panelProductos.add(nombreCategoria);
			
				for (Logro logro : logros) {
					String imageUrl = logro.getImagenUrl();
					if (imageUrl != null && !imageUrl.isEmpty()) { 
						try {
							
							URL url = new URL(imageUrl);
							Image logroimg = ImageIO.read(url);
							Image resizedLogroImage = logroimg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
							ImageIcon resizedLogroIcon = new ImageIcon(resizedLogroImage);

							
							JCheckBox logroCheckBox = new JCheckBox(logro.getNombre() + " - " + logro.getDescripcion()
									+ " - " + logro.getPuntosObtenidosLogros(), resizedLogroIcon);

							panelProductos.add(logroCheckBox);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});

		ImageIcon originalIcon4 = new ImageIcon("puntoslogo.png");
		Image originalImage4 = originalIcon4.getImage();
		Image resizedImage4 = originalImage4.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
		JButton boton4 = new JButton(resizedIcon4);
		panelBotonesSuperior.add(boton4);
		panelCentral.add(panelBotonesSuperior, BorderLayout.NORTH);
		boton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
				
				List<UsuarioPuntos> listaPuntos = UsuarioPuntos.obtenerPuntosTotales();
				JTextField nombreCategoria = new JTextField("Ranking de puntos copeo");
				panelProductos.add(nombreCategoria);
				
				for (UsuarioPuntos usuarioPuntos : listaPuntos) {
					Usuario usuario = usuarioPuntos.getUsuarioPnts();
					int puntosTotales = usuarioPuntos.getPuntosTotales();
					String infoUsuario = String.format("Nick: %s, Nombre: %s, Puntos Totales: %d", usuario.getNick(),
							usuario.getNombre(), puntosTotales);
					JCheckBox checkBox = new JCheckBox(infoUsuario);
					panelProductos.add(checkBox);
				}

				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});
		
		JPanel panelBotonesInferior = new JPanel();
		panelBotonesInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		
		ImageIcon originalIcon5 = new ImageIcon("homelogo.png");
		Image originalImage5 = originalIcon5.getImage();
		Image resizedImage5 = originalImage5.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
		JButton boton5 = new JButton(resizedIcon5);
		panelBotonesInferior.add(boton5);
		boton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
				List<Producto> productos = Producto.obtenerProductos();
				JTextField nombreCategoria = new JTextField("Productos en promocion en tu localidad");
				panelProductos.add(nombreCategoria);
				
				for (Producto producto : productos) {
					
					String imageUrl = producto.getImagenUrl();
					if (imageUrl != null && !imageUrl.isEmpty()) { 
						try {
							
							URL url = new URL(imageUrl);
							Image productoImage = ImageIO.read(url);
							Image resizedProductoImage = productoImage.getScaledInstance(25, 25,
									java.awt.Image.SCALE_SMOOTH);
							ImageIcon resizedProductoIcon = new ImageIcon(resizedProductoImage);

							
							JCheckBox productoCheckBox = new JCheckBox(
									producto.getNombre() + " - " + producto.getPrecioProducto() + " - "
											+ producto.getPuntosPorCompra() + " - " + producto.getDescripcion(),
									resizedProductoIcon, false);

							panelProductos.add(productoCheckBox);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				panelProductos.revalidate();
				panelProductos.repaint();

				List<Evento> eventos = Evento.obtenerEventos();
				JTextField nombreCategoria1 = new JTextField("Eventos en tu localidad");
				panelProductos.add(nombreCategoria1);
				
				for (Evento evento : eventos) {
					if (evento.getLocalidad().equals(usuarioActual.getLocalidad())) {
						String imageUrl = evento.getImagenUrl();
						if (imageUrl != null && !imageUrl.isEmpty()) { 
							try {
								
								URL url = new URL(imageUrl);
								Image productoEvento = ImageIO.read(url);
								Image resizedEventoImage = productoEvento.getScaledInstance(25, 25,
										java.awt.Image.SCALE_SMOOTH);
								ImageIcon resizedEventoIcon = new ImageIcon(resizedEventoImage);

								
								JCheckBox eventoCheckBox = new JCheckBox(
										evento.getNombre() + " - " + evento.getLocalidad() + " - " + evento.getFecha(),
										resizedEventoIcon);

								panelProductos.add(eventoCheckBox);

							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
				panelProductos.revalidate();
				panelProductos.repaint();
			}
		}

		);

		ImageIcon originalIcon6 = new ImageIcon("regalologo.png");
		Image originalImage6 = originalIcon6.getImage();
		Image resizedImage6 = originalImage6.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);
		JButton boton6 = new JButton(resizedIcon6);
		panelBotonesInferior.add(boton6);
		boton6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();
			
				List<Premio> premios = Premio.obtenerPremios();
				JTextField nombreCategoria = new JTextField("Premios disponibles");
				panelProductos.add(nombreCategoria);
			
				for (Premio premio : premios) {
					String imageUrl = premio.getImagenUrl();
					if (imageUrl != null && !imageUrl.isEmpty()) { 
						try {
						
							URL url = new URL(imageUrl);
							Image premioImage = ImageIO.read(url);
							Image resizedPremioImage = premioImage.getScaledInstance(25, 25,
									java.awt.Image.SCALE_SMOOTH);
							ImageIcon resizedPremioIcon = new ImageIcon(resizedPremioImage);

						
							JCheckBox productoCheckBox = new JCheckBox(
									premio.getNombre() + " - " + premio.getPuntosNecesarios() + " - "
											+ premio.getMarca() + " - " + premio.getDescripcion() + " - "
											+ premio.getPremioDiscoteca() + " - " + premio.isDisponibilidad() + " - ",
									resizedPremioIcon);

							panelProductos.add(productoCheckBox);

						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});

		ImageIcon originalIcon7 = new ImageIcon("carritologo.png");
		Image originalImage7 = originalIcon7.getImage();
		Image resizedImage7 = originalImage7.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);
		JButton boton7 = new JButton(resizedIcon7);
		panelBotonesInferior.add(boton7);

		ImageIcon originalIcon8 = new ImageIcon("perfillogo.png");
		Image originalImage8 = originalIcon8.getImage();
		Image resizedImage8 = originalImage8.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon8 = new ImageIcon(resizedImage8);

		JButton boton8 = new JButton(resizedIcon8);

		panelBotonesInferior.add(boton8);

		panelCentral.add(panelBotonesInferior, BorderLayout.SOUTH);

		boton8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarPanelProductos();

				JPanel panelInfoUsuario = new JPanel();
				panelInfoUsuario.setLayout(new BoxLayout(panelInfoUsuario, BoxLayout.Y_AXIS));

				JLabel labelNick = new JLabel("Nick: " + usuarioActual.getNick());
				JLabel labelNombre = new JLabel("Nombre: " + usuarioActual.getNombre());
				JLabel labelApellidos = new JLabel("Apellidos: " + usuarioActual.getApellidos());
				JLabel labelCorreo = new JLabel("Correo: " + usuarioActual.getCorreo());
				JLabel labelPuntos = new JLabel("Puntos: " + usuarioActual.getPuntosTotales());

				JTextField textFieldNick = new JTextField(usuarioActual.getNick());
				JTextField textFieldNombre = new JTextField(usuarioActual.getNombre());
				JTextField textFieldApellidos = new JTextField(usuarioActual.getApellidos());
				JTextField textFieldCorreo = new JTextField(usuarioActual.getCorreo());

				JPasswordField passwordFieldActual = new JPasswordField();
				JLabel labelContraseñaActual = new JLabel("Contraseña actual:");

				JPasswordField passwordFieldNueva = new JPasswordField();
				JLabel labelNuevaContraseña = new JLabel("Nueva contraseña:");

				JButton botonGuardarCambios = new JButton("Guardar cambios");

				botonGuardarCambios.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					
						char[] contraseñaActualChars = passwordFieldActual.getPassword();
						String contraseñaActual = new String(contraseñaActualChars);
						if (!contraseñaActual.equals(usuarioActual.getContraseña())) {
							JOptionPane.showMessageDialog(null, "Error de contraseña actual", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						
						char[] nuevaContraseñaChars = passwordFieldNueva.getPassword();
						String nuevaContraseña = new String(nuevaContraseñaChars);

						try {
							usuarioActual.setNick(textFieldNick.getText());
							usuarioActual.setNombre(textFieldNombre.getText());
							usuarioActual.setApellidos(textFieldApellidos.getText());
							usuarioActual.setCorreo(textFieldCorreo.getText());

							
							if (!nuevaContraseña.isEmpty()) {
								usuarioActual.setContraseña(nuevaContraseña);
							}

							
						} catch (ConexionFallidaException e1) {
							e1.printStackTrace();
						}
					}
				});

				panelInfoUsuario.add(labelNick);
				panelInfoUsuario.add(textFieldNick);
				panelInfoUsuario.add(labelNombre);
				panelInfoUsuario.add(textFieldNombre);
				panelInfoUsuario.add(labelApellidos);
				panelInfoUsuario.add(textFieldApellidos);
				panelInfoUsuario.add(labelCorreo);
				panelInfoUsuario.add(textFieldCorreo);
				panelInfoUsuario.add(labelPuntos);
				panelInfoUsuario.add(labelContraseñaActual);
				panelInfoUsuario.add(passwordFieldActual);
				panelInfoUsuario.add(labelNuevaContraseña);
				panelInfoUsuario.add(passwordFieldNueva);
				panelInfoUsuario.add(botonGuardarCambios);

				
				panelProductos.add(panelInfoUsuario);

			
				panelProductos.revalidate();
				panelProductos.repaint();
			}
		});

		
		add(panelSuperior, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void limpiarPanelProductos() {
		panelProductos.removeAll();
		panelProductos.revalidate();
		panelProductos.repaint();
	}
}