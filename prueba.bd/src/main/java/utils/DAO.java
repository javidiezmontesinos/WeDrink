package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

/**
 * Clase de acceso a base de datos, abstracta que permite hacer de forma simple
 * y sin preoucparse de la sintaxis sql de las operaciones crud sobre una base
 * de datos
 * 
 * @author javid
 *
 */
public abstract class DAO {
	/**
	 * Objeto conexion desde el que va a referenciar a la bd de conectar usar y
	 * desconectar lo antes posible
	 */
	private static Connection connection;

	/**
	 * Funcion privada que abre una conexion con un servidor de una base de datos
	 * Las propiedades de la base de datos deben estar definidas en un fichero
	 * ./bdconfig.ini con el siguiente formato: 1 linea : ip o dns del servidor 2
	 * linea: puerto 3 linea: nombre bd 4 linea: usuario bd 5 linea: contraseña bd
	 * 
	 * @return statement listo para hacer la consulta que necesitamos
	 */
	private static Statement connect() {
		try {
			BufferedReader lector = new BufferedReader(new FileReader("./bdconfig.ini"));
			String ip = lector.readLine();
			int puerto = Integer.parseInt(lector.readLine());
			String nombreBD = lector.readLine();
			String usuario = lector.readLine();
			String password = lector.readLine();
			lector.close();
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBD, usuario,
					password);
			return connection.createStatement();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int insert(String table, HashMap<String, Object> campos) throws SQLException {
		Statement querier = connect();
		// insert into cliente(email,contraseña) values ('svsvsvv@gmail.com,'1313jj')
		String query = "Insert into " + table + " (";
		Iterator it = campos.keySet().iterator();
		while (it.hasNext()) {
			String clave = (String) it.next();
			query += clave + ",";
		}
		query = query.substring(0, query.length() - 1) + ") values (";
		it = campos.values().iterator();
		while (it.hasNext()) {
			Object elemento = it.next();
			if (elemento.getClass() != String.class && elemento.getClass() != Character.class) {
				query += elemento + ",";
			} else {
				query += "'" + (String) elemento + "',";
			}

		}
		query = query.substring(0, query.length() - 1) + ")";
		if (Config.verboseMode) {
			System.out.println(query);
		}

		int ret = querier.executeUpdate(query);
		disconnect(querier);
		return ret;
	}

	public static int delete(String table, HashMap<String, Object> campos) throws SQLException {
		Statement querier = connect();
		// delete from usuer where email='qaddada'...
		String query = "delete from " + table + " where ";
		Iterator it = campos.entrySet().iterator();
		while (it.hasNext()) {
			Entry actual = (Entry) it.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += actual.getKey() + " = " + (String) actual.getValue() + " and ";
			} else {
				query += actual.getKey() + " = '" + (String) actual.getValue() + "' and ";
			}
		}
		query = query.substring(0, query.length() - 5);
		if (Config.verboseMode) {
			System.out.println(query);
		}
		int ret = querier.executeUpdate(query);
		disconnect(querier);
		return ret;
	}

	public static ArrayList<Object> consultar(String tabla, HashMap<String, Object> restricciones,
			LinkedHashSet<String> columnasSelect) throws SQLException {
		Statement smt = connect();

		String query = "select ";
		Iterator ith = columnasSelect.iterator();
		while (ith.hasNext()) {
			query += (String) ith.next() + ",";
		}
		// select email, nombre,password...
		Iterator itm = restricciones.entrySet().iterator();
		while (itm.hasNext()) {
			Entry actual = (Entry) itm.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query += actual.getKey() + " = " + (String) actual.getValue() + " and ";
			} else {
				query += actual.getKey() + " = '" + actual.getValue() + "' and ";
			}

		}
		if (restricciones.size() > 0) {
			query = query.substring(0, query.length() - 5);
		}
		query = query.substring(0, query.length() - 5);
		System.out.println(query);
		ResultSet cursor = smt.executeQuery(query);
		ArrayList<Object> fila = new ArrayList<Object>();
		while (cursor.next()) {
			Iterator hsCols = columnasSelect.iterator();
			while (hsCols.hasNext()) {
				String nombreCol = (String) hsCols.next();
				try {
					fila.add(cursor.getInt(cursor.findColumn(nombreCol)));
				} catch (NumberFormatException | SQLException e) {
					fila.add(cursor.getString(cursor.findColumn(nombreCol)));
				}
			}

		}
		disconnect(smt);
		return fila;
	}
	public static int update(String tabla,HashMap<String,Object>datosAModificar,HashMap<String,Object>restricciones) throws SQLException {
		String query="update "+tabla+" set ";
		Iterator itm= datosAModificar.entrySet().iterator();
		while(itm.hasNext()) {
			Entry actual=(Entry)itm.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query+=actual.getKey()+" = "+actual.getValue()+",";
			}else {
				query+=actual.getKey()+" = '"+actual.getValue()+"',";
			}
			
		}
		query=query.substring(0,query.length()-1)+" where ";
		Iterator itr=restricciones.entrySet().iterator();
		while(itr.hasNext()) {
			Entry actual=(Entry)itr.next();
			if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
				query+=actual.getKey()+" = "+actual.getValue()+" and ";
			}else {
				query+=actual.getKey()+" = '"+actual.getValue()+"' and ";
			}
			
		}
		query=query.substring(0,query.length()-5);
		
		Statement smt=connect();
		System.out.println(query);
		int ret=smt.executeUpdate(query);
		disconnect(smt);
		
		return ret;
	}

	/*
	 * Funcion privada que cierra en su interior tanto el statement como la conexion
	 * de la base de datos
	 * 
	 * @param smt
	 */
	private static void disconnect(Statement smt) {
		try {
			smt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}