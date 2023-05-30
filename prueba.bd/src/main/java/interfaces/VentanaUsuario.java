package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import clases.Producto;
import clases.Usuario;
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

    public VentanaUsuario(Ventana v, Usuario c) {
    	this.usuarioActual=c;
        this.ventana = v;
        setLayout(new BorderLayout(0, 0));

        // Panel superior con franja de color y datos del usuario
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.CYAN);
        panelSuperior.setLayout(new BorderLayout());

        // Logo de la aplicación
        ImageIcon originalIconlogo = new ImageIcon("logowedrinkprueba.png");
        Image originalImagelogo = originalIconlogo.getImage();
        int resizedWidth = 100; // Cambia este valor según necesites
        int resizedHeight = 50; // Cambia este valor según necesites
        Image resizedImagelogo = originalImagelogo.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconlogo = new ImageIcon(resizedImagelogo);
        JLabel labelLogo = new JLabel(resizedIconlogo);
        panelSuperior.add(labelLogo, BorderLayout.WEST);
        

        // Datos del usuario y puntos
        JPanel panelDatosUsuario = new JPanel();
        panelDatosUsuario.setBackground(Color.BLACK);
        panelDatosUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JLabel labelUsuario = new JLabel(usuarioActual.getNick());
        Usuario.class.getName();
        
        labelUsuario.setForeground(Color.WHITE);
        panelDatosUsuario.add(labelUsuario);

        JLabel labelPuntos = new JLabel("Puntos: "+usuarioActual.getPuntosTotales());
        labelPuntos.setForeground(Color.WHITE);
        panelDatosUsuario.add(labelPuntos);

        panelSuperior.add(panelDatosUsuario, BorderLayout.EAST);

        // Panel central con barra de desplazamiento
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(panelCentral);

        // Panel de botones superior
        JPanel panelBotonesSuperior = new JPanel();
        panelBotonesSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

     // Cargar y redimensionar la imagen
        ImageIcon originalIcon = new ImageIcon("promocionpng.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(32, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Crear el botón y agregarlo al panel
        JButton boton1 = new JButton(resizedIcon);
        panelBotonesSuperior.add(boton1);

     // Crear un panel para mostrar los productos
        
          // Crear un panel para mostrar los productos
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
           // Crear un nuevo panel para los productos y agregarle un JScrollPane
        JPanel panelProductosContainer = new JPanel(new BorderLayout());
        JScrollPane scrollPaneProductos = new JScrollPane(panelProductos);
        panelProductosContainer.add(scrollPaneProductos, BorderLayout.CENTER);
		
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener productos
                List<Producto> productos = Producto.obtenerProductos();
                JTextField nombreCategoria= new JTextField("Productos en promocion");
                panelProductos.add(nombreCategoria);
                // Mostrar productos
                for (Producto producto : productos) {
                    String imageUrl = producto.getImagenUrl();
                    if (imageUrl != null && !imageUrl.isEmpty()) { // Verificar que la URL no sea nula o vacía
                        try {
                            // Cargar la imagen del producto desde la URL
                            URL url = new URL(imageUrl);
                            Image productoImage = ImageIO.read(url);
                            Image resizedProductoImage = productoImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
                            ImageIcon resizedProductoIcon = new ImageIcon(resizedProductoImage);
                            
                            // Crear un JCheckBox con la imagen y el nombre y descripción del producto
                            
                            JCheckBox productoCheckBox = new JCheckBox(producto.getNombre() +" - "+producto.getPrecioProducto()+" - "+producto.getPuntosPorCompra()+ " - " + producto.getDescripcion(), resizedProductoIcon, false);

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
        // Agregar el panel de productos al panel central.
        panelCentral.add(panelProductosContainer, BorderLayout.CENTER); // Cambio realizado aquí
        
        ImageIcon originalIcon2 = new ImageIcon("eventoslogo.jpeg");
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        
        JButton boton2 = new JButton(resizedIcon2);
        panelBotonesSuperior.add(boton2);
        
        ImageIcon originalIcon3 = new ImageIcon("premioslogo.png");
        Image originalImage3 = originalIcon3.getImage();
        Image resizedImage3 = originalImage3.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        
        JButton boton3 = new JButton(resizedIcon3);
        panelBotonesSuperior.add(boton3);
        
        ImageIcon originalIcon4 = new ImageIcon("puntoslogo.png");
        Image originalImage4 = originalIcon4.getImage();
        Image resizedImage4 = originalImage4.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
        
        JButton boton4 = new JButton(resizedIcon4);
        panelBotonesSuperior.add(boton4);

        panelCentral.add(panelBotonesSuperior, BorderLayout.NORTH);

        // Panel de botones inferior
        JPanel panelBotonesInferior = new JPanel();
        panelBotonesInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Crear y agregar botones al panel de botones inferior

        ImageIcon originalIcon5 = new ImageIcon("homelogo.png");
        Image originalImage5 = originalIcon5.getImage();
        Image resizedImage5 = originalImage5.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
        JButton boton5 = new JButton(resizedIcon5);
        panelBotonesInferior.add(boton5);
        
        
        ImageIcon originalIcon6 = new ImageIcon("regalologo.png");
        Image originalImage6 = originalIcon6.getImage();
        Image resizedImage6 = originalImage6.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);
        JButton boton6 = new JButton(resizedIcon6);
        panelBotonesInferior.add(boton6);
        
        
        ImageIcon originalIcon7 = new ImageIcon("carritologo.png");
        Image originalImage7 = originalIcon7.getImage();
        Image resizedImage7 = originalImage7.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);
        JButton boton7 = new JButton(resizedIcon7);
        panelBotonesInferior.add(boton7);
        
        ImageIcon originalIcon8 = new ImageIcon("perfillogo.png");
        Image originalImage8 = originalIcon8.getImage();
        Image resizedImage8= originalImage8.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon8 = new ImageIcon(resizedImage8);
        
        JButton boton8 = new JButton(resizedIcon8);
        panelBotonesInferior.add(boton8);

        panelCentral.add(panelBotonesInferior, BorderLayout.SOUTH);

        // Agregar paneles al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}