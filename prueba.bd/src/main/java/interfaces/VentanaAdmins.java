package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Evento;
import clases.Logro;
import clases.Premio;
import clases.Producto;
import clases.Usuario;
import exceptions.ConexionFallidaException;
import exceptions.UsuarioNoExisteException;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VentanaAdmins extends JPanel {
	private Ventana ventana;
	private Usuario usuario;
	Usuario usuarioActual;
	Evento evento;

	public VentanaAdmins(Ventana v, Usuario c) {
		this.ventana = v;
		this.usuarioActual = c;

		setLayout(new BorderLayout(0, 0));

		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new BorderLayout());

	
		JPanel panelSuperiorIzquierdo = new JPanel();
		panelSuperiorIzquierdo.setLayout(new BorderLayout());
		JLabel labelSuperiorIzquierdo = new JLabel("PROMOCIONES");
		panelSuperiorIzquierdo.add(labelSuperiorIzquierdo, BorderLayout.NORTH);
		panelIzquierdo.add(panelSuperiorIzquierdo, BorderLayout.NORTH);

	
		JPanel panelBotonesSuperioresIzquierdos = new JPanel();
		panelBotonesSuperioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton1 = new JButton("AÑADIR PROMOCIONES");
		panelBotonesSuperioresIzquierdos.add(boton1);
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(0, 2));

				
				JTextField nombreField = new JTextField(20);
				JTextField descripcionField = new JTextField(20);
				JTextField puntosField = new JTextField(20);
				JTextField precioField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

				
				panel.add(new JLabel("Nombre:"));
				panel.add(nombreField);
				panel.add(new JLabel("Descripción:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Puntos por compra:"));
				panel.add(puntosField);
				panel.add(new JLabel("Precio del producto:"));
				panel.add(precioField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

				
				int result = JOptionPane.showConfirmDialog(null, panel, "Agregar promocion",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						
						String nombre = nombreField.getText();
						String descripcion = descripcionField.getText();
						int puntosPorAsistir = Integer.parseInt(puntosField.getText());
						double precioEvento = Double.parseDouble(precioField.getText());
						String imagenUrl = imagenUrlField.getText();

						
						Producto.registrarProducto(nombre, descripcion, puntosPorAsistir, precioEvento, imagenUrl);

						JOptionPane.showMessageDialog(null, "Promocion registrado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ConexionFallidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});


		JButton boton3 = new JButton("BORRAR PROMOCION");
		panelBotonesSuperioresIzquierdos.add(boton3);
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 2));

				
				JTextField nombreField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

		
				panel.add(new JLabel("Nombre de la promocion:"));
				panel.add(nombreField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

				
				int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Promocion",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					
					String nombre = nombreField.getText();
					String imagenUrl = imagenUrlField.getText();

					try {
						
						Producto.borrarProducto(nombre, imagenUrl);
						JOptionPane.showMessageDialog(null, "Promocion eliminada exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | ConexionFallidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar la promocion",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});

		panelIzquierdo.add(panelBotonesSuperioresIzquierdos, BorderLayout.CENTER);


		JPanel panelInferiorIzquierdo = new JPanel();
		panelInferiorIzquierdo.setLayout(new BorderLayout());
		JLabel labelInferiorIzquierdo = new JLabel("PREMIOS");
		panelInferiorIzquierdo.add(labelInferiorIzquierdo, BorderLayout.NORTH);

		
		JPanel panelBotonesInferioresIzquierdos = new JPanel();
		panelBotonesInferioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton4 = new JButton("AÑADIR PREMIOS");
		panelBotonesInferioresIzquierdos.add(boton4);
		boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(0, 2));

				JTextField nombreField = new JTextField(20);
				JTextField marcaField = new JTextField(20);
				JTextField descripcionField = new JTextField(20);
				JTextField puntosNecesariosField = new JTextField(20);
				JTextField disponibilidadField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);
				JTextField premioDiscotecaField = new JTextField(20);

				
				panel.add(new JLabel("Nombre:"));
				panel.add(nombreField);
				panel.add(new JLabel("Marca:"));
				panel.add(marcaField);
				panel.add(new JLabel("Descripción:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Puntos necesarios:"));
				panel.add(puntosNecesariosField);
				panel.add(new JLabel("disponibilidad:"));
				panel.add(disponibilidadField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);
				panel.add(new JLabel("ID de la discoteca:"));
				panel.add(premioDiscotecaField);

			
				int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Premios",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						
						String nombre = nombreField.getText();
						String marca = nombreField.getText();
						String descripcion = descripcionField.getText();
						double puntosNecesarios = Double.parseDouble(puntosNecesariosField.getText());
						Boolean disponibilidad = Boolean.parseBoolean(disponibilidadField.getText());
						String imagenUrl = imagenUrlField.getText();
						int idDiscoteca = Integer.parseInt(premioDiscotecaField.getText());

					
						Premio.registrarPremio(nombre, marca, descripcion, puntosNecesarios, disponibilidad, imagenUrl,
								idDiscoteca);

						JOptionPane.showMessageDialog(null, "Premio registrado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ConexionFallidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		

		JButton boton6 = new JButton("BORRAR PREMIOS");
		panelBotonesInferioresIzquierdos.add(boton6);
		boton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(0, 2));

				
				JTextField nombreField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

			
				panel.add(new JLabel("Nombre del Premio:"));
				panel.add(nombreField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

	
				int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Premio",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
				
					String nombre = nombreField.getText();
					String imagenUrl = imagenUrlField.getText();

					try {
					
						Premio.borrarPremio(nombre, imagenUrl);
						JOptionPane.showMessageDialog(null, "Premio eliminado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | ConexionFallidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar premio",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});

		panelInferiorIzquierdo.add(panelBotonesInferioresIzquierdos, BorderLayout.CENTER);
		panelIzquierdo.add(panelInferiorIzquierdo, BorderLayout.SOUTH);

		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);

	
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);

		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());

	
		JPanel panelSuperiorDerecho = new JPanel();
		panelSuperiorDerecho.setLayout(new BorderLayout());
		JLabel labelSuperiorDerecho = new JLabel("EVENTOS");
		panelSuperiorDerecho.add(labelSuperiorDerecho, BorderLayout.NORTH);
		panelDerecho.add(panelSuperiorDerecho, BorderLayout.NORTH);

	
		JPanel panelBotonesSuperioresDerechos = new JPanel();
		panelBotonesSuperioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton7 = new JButton("AÑADIR EVENTOS");
		panelBotonesSuperioresDerechos.add(boton7);
		boton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(0, 2));

				JTextField nombreField = new JTextField(20);
				JTextField descripcionField = new JTextField(20);
				JTextField localidadField = new JTextField(20);
				JTextField direccionField = new JTextField(20);
				JTextField fechaField = new JTextField(20);
				JTextField puntosField = new JTextField(20);
				JTextField precioField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

				
				panel.add(new JLabel("Nombre:"));
				panel.add(nombreField);
				panel.add(new JLabel("Descripción:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Localidad:"));
				panel.add(localidadField);
				panel.add(new JLabel("Dirección:"));
				panel.add(direccionField);
				panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
				panel.add(fechaField);
				panel.add(new JLabel("Puntos por Asistir:"));
				panel.add(puntosField);
				panel.add(new JLabel("Precio del Evento:"));
				panel.add(precioField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

				
				int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Evento", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
						
						String nombre = nombreField.getText();
						String descripcion = descripcionField.getText();
						String localidad = localidadField.getText();
						String direccion = direccionField.getText();
						String fechaString = fechaField.getText();
						int puntosPorAsistir = Integer.parseInt(puntosField.getText());
						double precioEvento = Double.parseDouble(precioField.getText());
						String imagenUrl = imagenUrlField.getText();

						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = dateFormat.parse(fechaString);

					
						Evento.registrarEvento(nombre, descripcion, localidad, direccion, fecha, puntosPorAsistir,
								precioEvento, imagenUrl);

						JOptionPane.showMessageDialog(null, "Evento registrado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ConexionFallidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		

		JButton boton9 = new JButton("BORRAR EVENTOS");
		panelBotonesSuperioresDerechos.add(boton9);
		boton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				JPanel panel = new JPanel(new GridLayout(0, 2));

				
				JTextField nombreField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

		
				panel.add(new JLabel("Nombre del Evento:"));
				panel.add(nombreField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

				int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Evento",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					String nombre = nombreField.getText();
					String imagenUrl = imagenUrlField.getText();

					try {
						Evento.borrarEvento(nombre, imagenUrl);
						JOptionPane.showMessageDialog(null, "Evento eliminado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | ConexionFallidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar el evento",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});

		panelDerecho.add(panelBotonesSuperioresDerechos, BorderLayout.CENTER);

	
		JPanel panelInferiorDerecho = new JPanel();
		panelInferiorDerecho.setLayout(new BorderLayout());
		JLabel labelInferiorDerecho = new JLabel("LOGROS");
		panelInferiorDerecho.add(labelInferiorDerecho, BorderLayout.NORTH);

	
		JPanel panelBotonesInferioresDerechos = new JPanel();
		panelBotonesInferioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton10 = new JButton("AÑADIR LOGROS");
		panelBotonesInferioresDerechos.add(boton10);
		boton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panel = new JPanel(new GridLayout(0, 2));

				JTextField nombreField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);
				JTextField descripcionField = new JTextField(20);
				JTextField puntosField = new JTextField(20);
				JTextField completadoField = new JTextField(20);

				
				panel.add(new JLabel("Nombre:"));
				panel.add(nombreField);
				panel.add(new JLabel("Imagen:"));
				panel.add(imagenUrlField);
				panel.add(new JLabel("Descripción:"));
				panel.add(descripcionField);
				panel.add(new JLabel("Puntos obtenidos logro:"));
				panel.add(puntosField);
				panel.add(new JLabel("logro completado:"));
				panel.add(completadoField);

				
				int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Logros", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					try {
					
						String nombre = nombreField.getText();
						String imagenlogro = nombreField.getText();
						String descripcion = descripcionField.getText();
						int puntos = Integer.parseInt(puntosField.getText());
						Boolean logrocompletado = Boolean.parseBoolean(completadoField.getText());

						Logro.registrarLogro(nombre, imagenlogro, descripcion, puntos, logrocompletado);

						JOptionPane.showMessageDialog(null, "Logro registrado correctamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ConexionFallidaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JButton boton12 = new JButton("BORRAR LOGROS");
		panelBotonesInferioresDerechos.add(boton12);
		boton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 2));

				JTextField nombreField = new JTextField(20);
				JTextField imagenUrlField = new JTextField(20);

				panel.add(new JLabel("Nombre del Logro:"));
				panel.add(nombreField);
				panel.add(new JLabel("URL de la Imagen:"));
				panel.add(imagenUrlField);

				int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar Logro", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					String nombre = nombreField.getText();
					String imagenUrl = imagenUrlField.getText();

					try {
						Logro.borrarLogro(nombre, imagenUrl);
						JOptionPane.showMessageDialog(null, "Logro eliminado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | ConexionFallidaException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar el logro",
								JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		});

		panelInferiorDerecho.add(panelBotonesInferioresDerechos, BorderLayout.CENTER);
		panelDerecho.add(panelInferiorDerecho, BorderLayout.SOUTH);

		panelPrincipal.add(panelDerecho, BorderLayout.EAST);

		add(panelPrincipal, BorderLayout.NORTH);

	}
}