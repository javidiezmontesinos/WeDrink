package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;

public class VentanaUsuario extends JFrame {
	public VentanaUsuario() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Ventana Usuario");
	        setSize(800, 600);
	        setResizable(false);

	        // Panel principal
	        JPanel panelPrincipal = new JPanel();
	        panelPrincipal.setLayout(new BorderLayout());

	        // Panel superior con franja de color y datos del usuario
	        JPanel panelSuperior = new JPanel();
	        panelSuperior.setBackground(Color.BLUE);
	        panelSuperior.setLayout(new BorderLayout());

	        // Logo de la aplicación
	        JLabel labelLogo = new JLabel(new ImageIcon("ruta/imagen/logo.png"));
	        panelSuperior.add(labelLogo, BorderLayout.WEST);

	        // Datos del usuario y puntos
	        JPanel panelDatosUsuario = new JPanel();
	        panelDatosUsuario.setBackground(Color.BLUE);
	        panelDatosUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

	        JLabel labelUsuario = new JLabel("Usuario: NombreUsuario");
	        labelUsuario.setForeground(Color.WHITE);
	        panelDatosUsuario.add(labelUsuario);

	        JLabel labelPuntos = new JLabel("Puntos: 100");
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

	        // Crear y agregar botones al panel de botones superior
	        JButton boton1 = new JButton(new ImageIcon("ruta/imagen/boton1.png"));
	        panelBotonesSuperior.add(boton1);

	        JButton boton2 = new JButton(new ImageIcon("ruta/imagen/boton2.png"));
	        panelBotonesSuperior.add(boton2);

	        JButton boton3 = new JButton(new ImageIcon("ruta/imagen/boton3.png"));
	        panelBotonesSuperior.add(boton3);

	        JButton boton4 = new JButton(new ImageIcon("ruta/imagen/boton4.png"));
	        panelBotonesSuperior.add(boton4);

	        panelCentral.add(panelBotonesSuperior, BorderLayout.NORTH);

	        // Panel de botones inferior
	        JPanel panelBotonesInferior = new JPanel();
	        panelBotonesInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

	        // Crear y agregar botones al panel de botones inferior
	        JButton boton5 = new JButton(new ImageIcon("ruta/imagen/boton5.png"));
	        panelBotonesInferior.add(boton5);

	        JButton boton6 = new JButton(new ImageIcon("ruta/imagen/boton6.png"));
	        panelBotonesInferior.add(boton6);

	        JButton boton7 = new JButton(new ImageIcon("ruta/imagen/boton7.png"));
	        panelBotonesInferior.add(boton7);

	        JButton boton8 = new JButton(new ImageIcon("ruta/imagen/boton8.png"));
	        panelBotonesInferior.add(boton8);

	        panelCentral.add(panelBotonesInferior, BorderLayout.SOUTH);

	        // Agregar paneles al panel principal
	        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
	        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

	        // Agregar panel principal a la ventana
	        setContentPane(panelPrincipal);
	    }

}
