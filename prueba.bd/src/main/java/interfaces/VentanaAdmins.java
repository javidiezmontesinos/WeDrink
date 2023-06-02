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

		// Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		// Panel izquierdo
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new BorderLayout());

		// Label superior izquierdo
		JPanel panelSuperiorIzquierdo = new JPanel();
		panelSuperiorIzquierdo.setLayout(new BorderLayout());
		JLabel labelSuperiorIzquierdo = new JLabel("PROMOCIONES");
		panelSuperiorIzquierdo.add(labelSuperiorIzquierdo, BorderLayout.NORTH);
		panelIzquierdo.add(panelSuperiorIzquierdo, BorderLayout.NORTH);

		// Botones superiores izquierdos
		JPanel panelBotonesSuperioresIzquierdos = new JPanel();
		panelBotonesSuperioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton1 = new JButton("AÑADIR PROMOCIONES");
		panelBotonesSuperioresIzquierdos.add(boton1);
		boton1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Crear un panel para la ventana emergente
		        JPanel panel = new JPanel(new GridLayout(0, 2));

		        // Crear los campos de texto para ingresar los datos del evento
		        JTextField nombreField = new JTextField(20);
		        JTextField descripcionField = new JTextField(20);
		        JTextField puntosField = new JTextField(20);
		        JTextField precioField = new JTextField(20);
		        JTextField imagenUrlField = new JTextField(20);

		        // Agregar las etiquetas y los campos de texto al panel
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

		        // Mostrar la ventana emergente y esperar la respuesta del usuario
		        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar promocion", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                // Obtener los valores ingresados por el usuario
		                String nombre = nombreField.getText();
		                String descripcion = descripcionField.getText();
		                int puntosPorAsistir = Integer.parseInt(puntosField.getText());
		                double precioEvento = Double.parseDouble(precioField.getText());
		                String imagenUrl = imagenUrlField.getText();

		                // Registrar el evento (aquí debes implementar tu propia lógica)
		                Producto.registrarProducto(nombre, descripcion, puntosPorAsistir, precioEvento, imagenUrl);

		                JOptionPane.showMessageDialog(null, "Promocion registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            } catch (NumberFormatException e2) {
		                JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error", JOptionPane.ERROR_MESSAGE);
		            } 
		            // Aquí puedes manejar las excepciones que puedas tener al registrar el evento
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

		JButton boton2 = new JButton("EDITAR PROMOCIONES");
		panelBotonesSuperioresIzquierdos.add(boton2);

		JButton boton3 = new JButton("BORRAR PROMOCION");
		panelBotonesSuperioresIzquierdos.add(boton3);

		panelIzquierdo.add(panelBotonesSuperioresIzquierdos, BorderLayout.CENTER);

		// Label inferior izquierdo
		JPanel panelInferiorIzquierdo = new JPanel();
		panelInferiorIzquierdo.setLayout(new BorderLayout());
		JLabel labelInferiorIzquierdo = new JLabel("PREMIOS");
		panelInferiorIzquierdo.add(labelInferiorIzquierdo, BorderLayout.NORTH);

		// Botones inferiores izquierdos
		JPanel panelBotonesInferioresIzquierdos = new JPanel();
		panelBotonesInferioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton4 = new JButton("AÑADIR PREMIOS");
		panelBotonesInferioresIzquierdos.add(boton4);
		boton4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Crear un panel para la ventana emergente
		        JPanel panel = new JPanel(new GridLayout(0, 2));

		        
		        JTextField nombreField = new JTextField(20);
		        JTextField marcaField = new JTextField(20);
		        JTextField descripcionField = new JTextField(20);
		        JTextField puntosNecesariosField = new JTextField(20);
		        JTextField disponibilidadField = new JTextField(20);
		        JTextField imagenUrlField = new JTextField(20);
		        JTextField premioDiscotecaField= new JTextField(20);

		        // Agregar las etiquetas y los campos de texto al panel
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

		        // Mostrar la ventana emergente y esperar la respuesta del usuario
		        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Premios", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                // Obtener los valores ingresados por el usuario
		                String nombre = nombreField.getText();
		                String marca = nombreField.getText();
		                String descripcion = descripcionField.getText();
		                double puntosNecesarios = Double.parseDouble(puntosNecesariosField.getText());
		                Boolean disponibilidad = Boolean.parseBoolean(disponibilidadField.getText());
		                String imagenUrl = imagenUrlField.getText();
		                int idDiscoteca= Integer.parseInt(premioDiscotecaField.getText());

		                // Registrar el evento (aquí debes implementar tu propia lógica)
		                Premio.registrarPremio(nombre, marca, descripcion, puntosNecesarios, disponibilidad,imagenUrl,idDiscoteca);

		                JOptionPane.showMessageDialog(null, "Premio registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            } catch (NumberFormatException e2) {
		                JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error", JOptionPane.ERROR_MESSAGE);
		            } 
		            // Aquí puedes manejar las excepciones que puedas tener al registrar el evento
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


		JButton boton5 = new JButton("EDITAR PREMIOS");
		panelBotonesInferioresIzquierdos.add(boton5);

		JButton boton6 = new JButton("BORRAR PREMIOS");
		panelBotonesInferioresIzquierdos.add(boton6);

		panelInferiorIzquierdo.add(panelBotonesInferioresIzquierdos, BorderLayout.CENTER);
		panelIzquierdo.add(panelInferiorIzquierdo, BorderLayout.SOUTH);

		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);

		// Panel central con scroll
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);

		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		// Panel derecho
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());

		// Label superior derecho
		JPanel panelSuperiorDerecho = new JPanel();
		panelSuperiorDerecho.setLayout(new BorderLayout());
		JLabel labelSuperiorDerecho = new JLabel("EVENTOS");
		panelSuperiorDerecho.add(labelSuperiorDerecho, BorderLayout.NORTH);
		panelDerecho.add(panelSuperiorDerecho, BorderLayout.NORTH);

		// Botones superiores derechos
		JPanel panelBotonesSuperioresDerechos = new JPanel();
		panelBotonesSuperioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton7 = new JButton("AÑADIR EVENTOS");
		panelBotonesSuperioresDerechos.add(boton7);
		boton7.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Crear un panel para la ventana emergente
		        JPanel panel = new JPanel(new GridLayout(0, 2));

		        // Crear los campos de texto para ingresar los datos del evento
		        JTextField nombreField = new JTextField(20);
		        JTextField descripcionField = new JTextField(20);
		        JTextField localidadField = new JTextField(20);
		        JTextField direccionField = new JTextField(20);
		        JTextField fechaField = new JTextField(20);
		        JTextField puntosField = new JTextField(20);
		        JTextField precioField = new JTextField(20);
		        JTextField imagenUrlField = new JTextField(20);

		        // Agregar las etiquetas y los campos de texto al panel
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

		        // Mostrar la ventana emergente y esperar la respuesta del usuario
		        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Evento", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            try {
		                // Obtener los valores ingresados por el usuario
		                String nombre = nombreField.getText();
		                String descripcion = descripcionField.getText();
		                String localidad = localidadField.getText();
		                String direccion = direccionField.getText();
		                String fechaString = fechaField.getText();
		                int puntosPorAsistir = Integer.parseInt(puntosField.getText());
		                double precioEvento = Double.parseDouble(precioField.getText());
		                String imagenUrl = imagenUrlField.getText();

		                // Convertir la fecha ingresada en un objeto Date
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		                Date fecha = dateFormat.parse(fechaString);

		                // Registrar el evento (aquí debes implementar tu propia lógica)
		                Evento.registrarEvento(nombre, descripcion, localidad, direccion, fecha, puntosPorAsistir, precioEvento, imagenUrl);

		                JOptionPane.showMessageDialog(null, "Evento registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            } catch (ParseException e1) {
		                JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException e2) {
		                JOptionPane.showMessageDialog(null, "Error al convertir los valores numericos", "Error", JOptionPane.ERROR_MESSAGE);
		            } 
		            // Aquí puedes manejar las excepciones que puedas tener al registrar el evento
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
		JButton boton8 = new JButton("EDITAR EVENTOS");
		panelBotonesSuperioresDerechos.add(boton8);
		boton8.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Crear un panel para la ventana emergente
		        JPanel panel = new JPanel(new GridLayout(0, 2));

		        // Crear los campos de texto para editar los datos del evento
		        JTextField nombreField = new JTextField(evento.getNombre(), 20);
		        JTextField descripcionField = new JTextField(evento.getDescripcion(), 20);
		        JTextField localidadField = new JTextField(evento.getLocalidad(), 20);
		        JTextField direccionField = new JTextField(evento.getDirecion(), 20);
		        JTextField fechaField = new JTextField(evento.getFecha().toString(), 20);
		        JTextField puntosField = new JTextField(String.valueOf(evento.getPuntosPorAsistir()), 20);
		        JTextField precioField = new JTextField(String.valueOf(evento.getPrecioEvento()), 20);
		        JTextField imagenUrlField = new JTextField(evento.getImagenUrl(), 20);

		        // Agregar las etiquetas y los campos de texto al panel
		        
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

		        // Mostrar la ventana emergente y esperar la respuesta del usuario
		        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Evento", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            // Obtener los valores ingresados por el usuario
		            
		            String descripcion = descripcionField.getText();
		            String localidad = localidadField.getText();
		            String direccion = direccionField.getText();
		            String fechaString = fechaField.getText();
		            int puntosPorAsistir = Integer.parseInt(puntosField.getText());
		            double precioEvento = Double.parseDouble(precioField.getText());
		            String imagenUrl = imagenUrlField.getText();

		            // Convertir la fecha ingresada en un objeto Date
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            Date fecha = null;
		            try {
		                fecha = dateFormat.parse(fechaString);
		            } catch (ParseException ex) {
		                ex.printStackTrace();
		            }

		            // Actualizar los datos del evento con los valores ingresados
		            
		            evento.setDescripcion(descripcion);
		            evento.setLocalidad(localidad);
		            evento.setDirecion(direccion);
		            evento.setFecha(fecha);
		            evento.setPuntosPorAsistir(puntosPorAsistir);
		            evento.setPrecioEvento(precioEvento);
		            evento.setImagenUrl(imagenUrl);

		            // Llamar al método para actualizar el evento en la base de datos
		            evento.actualizarEvento();
		        }
		    }
		});
		
		
		JButton boton9 = new JButton("BORRAR EVENTOS");
		panelBotonesSuperioresDerechos.add(boton9);

		panelDerecho.add(panelBotonesSuperioresDerechos, BorderLayout.CENTER);

		// Label inferior derecho
		JPanel panelInferiorDerecho = new JPanel();
		panelInferiorDerecho.setLayout(new BorderLayout());
		JLabel labelInferiorDerecho = new JLabel("LOGROS");
		panelInferiorDerecho.add(labelInferiorDerecho, BorderLayout.NORTH);

		// Botones inferiores derechos
		JPanel panelBotonesInferioresDerechos = new JPanel();
		panelBotonesInferioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

		JButton boton10 = new JButton("AÑADIR LOGROS");
		panelBotonesInferioresDerechos.add(boton10);

		JButton boton11 = new JButton("EDITAR LOGROS");
		panelBotonesInferioresDerechos.add(boton11);

		JButton boton12 = new JButton("BORRAR LOGROS");
		panelBotonesInferioresDerechos.add(boton12);

		panelInferiorDerecho.add(panelBotonesInferioresDerechos, BorderLayout.CENTER);
		panelDerecho.add(panelInferiorDerecho, BorderLayout.SOUTH);

		panelPrincipal.add(panelDerecho, BorderLayout.EAST);

		// Agregar panel principal a la ventana
		add(panelPrincipal, BorderLayout.NORTH);

	}
}