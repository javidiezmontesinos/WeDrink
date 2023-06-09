package interfaces;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;

public class Ventana extends JFrame {

	protected Usuario clienteLogado;

	public Ventana() {
		this.setSize(600, 500);
		this.setTitle("WeDrink");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PantallaLogin(this));
		this.setIconImage(new ImageIcon(".\\logowedrink.jpg").getImage());
		this.setVisible(true);
	}

	public void cambiarAPantalla(Class<?> clase) {
	    this.getContentPane().setVisible(false);
	    if (clase.equals(PantallaLogin.class)) {
	        this.setContentPane(new PantallaLogin(this));
	    }
	    if (clase.equals(PantallaRegistro.class)) {
	        this.setContentPane(new PantallaRegistro(this));
	    }
	    if (clienteLogado != null && !(clienteLogado.getNick().equals("admin"))) {
	        if (clase.equals(VentanaUsuario.class)) {
	            this.setContentPane(new VentanaUsuario(this, clienteLogado));
	        }
	    } else {
	        if (clase.equals(VentanaAdmins.class)) {
	            this.setContentPane(new VentanaAdmins(this, clienteLogado));
	        }
	    }

	    this.getContentPane().setVisible(true);
	}
}
