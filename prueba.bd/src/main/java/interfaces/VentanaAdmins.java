package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VentanaAdmins extends JFrame {
	public VentanaAdmins() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Ventana");
	        setSize(800, 600);
	        setResizable(false);

	        // Panel principal
	        JPanel panelPrincipal = new JPanel();
	        panelPrincipal.setLayout(new BorderLayout());

	        // Panel izquierdo
	        JPanel panelIzquierdo = new JPanel();
	        panelIzquierdo.setLayout(new BorderLayout());

	        // Label superior izquierdo
	        JPanel panelSuperiorIzquierdo = new JPanel();
	        panelSuperiorIzquierdo.setLayout(new BorderLayout());
	        JLabel labelSuperiorIzquierdo = new JLabel("Texto superior izquierdo");
	        panelSuperiorIzquierdo.add(labelSuperiorIzquierdo, BorderLayout.NORTH);
	        panelIzquierdo.add(panelSuperiorIzquierdo, BorderLayout.NORTH);

	        // Botones superiores izquierdos
	        JPanel panelBotonesSuperioresIzquierdos = new JPanel();
	        panelBotonesSuperioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

	        JButton boton1 = new JButton("Botón 1");
	        panelBotonesSuperioresIzquierdos.add(boton1);

	        JButton boton2 = new JButton("Botón 2");
	        panelBotonesSuperioresIzquierdos.add(boton2);

	        JButton boton3 = new JButton("Botón 3");
	        panelBotonesSuperioresIzquierdos.add(boton3);

	        panelIzquierdo.add(panelBotonesSuperioresIzquierdos, BorderLayout.CENTER);

	        // Label inferior izquierdo
	        JPanel panelInferiorIzquierdo = new JPanel();
	        panelInferiorIzquierdo.setLayout(new BorderLayout());
	        JLabel labelInferiorIzquierdo = new JLabel("Texto inferior izquierdo");
	        panelInferiorIzquierdo.add(labelInferiorIzquierdo, BorderLayout.NORTH);

	        // Botones inferiores izquierdos
	        JPanel panelBotonesInferioresIzquierdos = new JPanel();
	        panelBotonesInferioresIzquierdos.setLayout(new GridLayout(3, 1, 0, 10));

	        JButton boton4 = new JButton("Botón 4");
	        panelBotonesInferioresIzquierdos.add(boton4);

	        JButton boton5 = new JButton("Botón 5");
	        panelBotonesInferioresIzquierdos.add(boton5);

	        JButton boton6 = new JButton("Botón 6");
	        panelBotonesInferioresIzquierdos.add(boton6);

	        panelInferiorIzquierdo.add(panelBotonesInferioresIzquierdos, BorderLayout.CENTER);
	        panelIzquierdo.add(panelInferiorIzquierdo, BorderLayout.SOUTH);

	        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);

	        // Panel central con scroll
	        JPanel panelCentral = new JPanel();
	        panelCentral.setLayout(new BorderLayout());

	        JLabel labelCentral = new JLabel("Label con scroll");
	        JScrollPane scrollPane = new JScrollPane(labelCentral);
	        panelCentral.add(scrollPane, BorderLayout.CENTER);

	        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

	        // Panel derecho
	        JPanel panelDerecho = new JPanel();
	        panelDerecho.setLayout(new BorderLayout());

	        // Label superior derecho
	        JPanel panelSuperiorDerecho = new JPanel();
	        panelSuperiorDerecho.setLayout(new BorderLayout());
	        JLabel labelSuperiorDerecho = new JLabel("Texto superior derecho");
	        panelSuperiorDerecho.add(labelSuperiorDerecho, BorderLayout.NORTH);
	        panelDerecho.add(panelSuperiorDerecho, BorderLayout.NORTH);

	        // Botones superiores derechos
	        JPanel panelBotonesSuperioresDerechos = new JPanel();
	        panelBotonesSuperioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

	        JButton boton7 = new JButton("Botón 7");
	        panelBotonesSuperioresDerechos.add(boton7);

	        JButton boton8 = new JButton("Botón 8");
	        panelBotonesSuperioresDerechos.add(boton8);

	        JButton boton9 = new JButton("Botón 9");
	        panelBotonesSuperioresDerechos.add(boton9);

	        panelDerecho.add(panelBotonesSuperioresDerechos, BorderLayout.CENTER);

	        // Label inferior derecho
	        JPanel panelInferiorDerecho = new JPanel();
	        panelInferiorDerecho.setLayout(new BorderLayout());
	        JLabel labelInferiorDerecho = new JLabel("Texto inferior derecho");
	        panelInferiorDerecho.add(labelInferiorDerecho, BorderLayout.NORTH);

	        // Botones inferiores derechos
	        JPanel panelBotonesInferioresDerechos = new JPanel();
	        panelBotonesInferioresDerechos.setLayout(new GridLayout(3, 1, 0, 10));

	        JButton boton10 = new JButton("Botón 10");
	        panelBotonesInferioresDerechos.add(boton10);

	        JButton boton11 = new JButton("Botón 11");
	        panelBotonesInferioresDerechos.add(boton11);

	        JButton boton12 = new JButton("Botón 12");
	        panelBotonesInferioresDerechos.add(boton12);

	        panelInferiorDerecho.add(panelBotonesInferioresDerechos, BorderLayout.CENTER);
	        panelDerecho.add(panelInferiorDerecho, BorderLayout.SOUTH);

	        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

	        // Agregar panel principal a la ventana
	        setContentPane(panelPrincipal);
	    }

}