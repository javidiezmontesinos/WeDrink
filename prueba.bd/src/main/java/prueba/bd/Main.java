package prueba.bd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

import clases.Usuario;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioNoExisteException;
import utils.DAO;
/**
 * Ejercicio POJOS
 * @author Javi Díez Montesinos - 1 DAM MAÑANA
 *
 */
public class Main {

	public static void main(String[] args) throws UsuarioNoExisteException, ContraseñaInvalidaException {
		Scanner sc = new Scanner(System.in);
		Usuario usuario;
		byte opcion = 0;
		do {
			System.out.println("¿Qué quieres hacer en la bd?\n\t0- Salir" + "\n\t1- Registrar usuario"
					+ "\n\t2- Login usuario" + "\n\t3- Ver Login usuario");
			opcion = Byte.parseByte(sc.nextLine());
			switch (opcion) {
			case 1:
				System.out.println("Dime email");
				String email = sc.nextLine();
				System.out.println("Dime nick");
				String nick = sc.nextLine();
				System.out.println("Dime contraseña");
				String pass = sc.nextLine();
				try {
					usuario = new Usuario(email, nick, pass);
					System.out.println("el Usuario se ha registrado correctamente");
					escribirFichero("./usuarios.log", " usuario " + usuario.getEmail()
							+ " registrado con exito con fecha " + LocalDateTime.now().toString() + "\n");
				} catch (SQLException e) {
					System.out.println("El usuario no se ha podido registrar");
					e.printStackTrace();
				}
				break;
			case 2:
				boolean login = false;
				do {
					System.out.println("Dime el email del usuario");
					String emailLogin = sc.nextLine();
					System.out.println("Dime la contraseña del usuario");
					String passLogin = sc.nextLine();

					try {
						usuario = new Usuario(emailLogin, passLogin);
						System.out.println("Usuario login con éxito");
						escribirFichero("./usuarios.log", "Usuario " + usuario.getEmail() + " login con éxito en: "
								+ LocalDateTime.now().toString() + "\n");
						login = true;
					} catch (SQLException e) {
						System.out.println("Error al hacer login con este usuario.");
						e.printStackTrace();
					}
				} while (!login);
				break;
			case 3:
				try {
					String contenidoFichero = leerFichero("./usuarios.log");
					System.out.println(contenidoFichero);
				} catch (IOException e) {
					System.out.println("Error al leer el fichero de log de los usuarios");
					e.printStackTrace();
				}
				break;
			}
		} while (opcion != 0);

		sc.close();
	}

	// funcion para escribir ficheros
	private static void escribirFichero(String nombreFichero, String mensaje) {
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(nombreFichero, true))) {
			bf.write(mensaje);
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero");
			e.printStackTrace();
		}
	}
	// funcion para leer ficheros
	private static String leerFichero(String nombreFichero) throws IOException {
		String fichero = "";
		try (Scanner sc = new Scanner(new java.io.File(fichero))) {
			while (sc.hasNextLine()) {
				fichero += sc.nextLine() + "\n";
			}

		}
		return fichero;
	}
}