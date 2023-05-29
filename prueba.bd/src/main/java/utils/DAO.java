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

import exceptions.ConexionFallidaException;

/**
 * Clase de acceso a base de datos, abstracta que permite hacer de forma simple
 * y sin preoucparse de la sintaxis sql de las operaciones crud sobre una base
 * de datos
 * 
 * @author javid
 *
 */
public abstract class DAO {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/wedrink";
	private static final String DATABASE_USER = "root";
	private static final String DATABASE_PASSWORD = "basket10";

	public static Connection connect() throws ConexionFallidaException {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
			return connection;
		} catch (SQLException ex) {
			throw new ConexionFallidaException("Fallo al conectar a la base de datos.");
		}
	}
/*
	public static int insert(String table, HashMap<String, Object> campos) throws SQLException, ConexionFallidaException {
		Connection connection = connect();
		Statement querier = connection.createStatement();

		String query = "Insert into " + table + " (";
		Iterator<String> keyIterator = campos.keySet().iterator();
		while (keyIterator.hasNext()) {
			String clave = keyIterator.next();
			query += clave + ",";
		}
		query = query.substring(0, query.length() - 1) + ") values (";
		Iterator<Object> valueIterator = campos.values().iterator();
		while (valueIterator.hasNext()) {
			Object elemento = valueIterator.next();
			if (elemento.getClass() != String.class && elemento.getClass() != Character.class) {
				query += elemento + ",";
			} else {
				query += "'" + elemento + "',";
			}
		}
		query = query.substring(0, query.length() - 1) + ")";
		if (Config.verboseMode) {
			System.out.println(query);
		}

		int ret = querier.executeUpdate(query);
		disconnect(querier, connection);
		return ret;
	}

	public static void disconnect(Statement statement, Connection connection) throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	public static int delete(String table, HashMap<String, Object> campos) throws SQLException, ConexionFallidaException {
	    Connection connection = connect();
	    Statement querier = connection.createStatement();
	    
	    // delete from user where email='qaddada'...
	    String query = "delete from " + table + " where ";
	    Iterator<Entry<String, Object>> it = campos.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, Object> actual = it.next();
	        if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
	            query += actual.getKey() + " = " + actual.getValue() + " and ";
	        } else {
	            query += actual.getKey() + " = '" + actual.getValue() + "' and ";
	        }
	    }
	    query = query.substring(0, query.length() - 5);
	    if (Config.verboseMode) {
	        System.out.println(query);
	    }
	    int ret = querier.executeUpdate(query);
	    disconnect(querier, connection);
	    return ret;
	}
	public static ArrayList<Object> consultar(String tabla, HashMap<String, Object> restricciones,
	        LinkedHashSet<String> columnasSelect) throws SQLException, ConexionFallidaException {
	    Connection connection = connect();
	    Statement smt = connection.createStatement();

	    String query = "select ";
	    Iterator<String> ith = columnasSelect.iterator();
	    while (ith.hasNext()) {
	        query += ith.next() + ",";
	    }
	    query = query.substring(0, query.length() - 1) + " from " + tabla + " where ";
	    Iterator<Entry<String, Object>> itm = restricciones.entrySet().iterator();
	    while (itm.hasNext()) {
	        Entry<String, Object> actual = itm.next();
	        Object valor = actual.getValue();
	        if (valor != null) {
	            if (valor.getClass() != String.class && valor.getClass() != Character.class) {
	                query += actual.getKey() + " = " + valor + " and ";
	            } else {
	                query += actual.getKey() + " = '" + valor + "' and ";
	            }
	        }
	    }
	    if (restricciones.size() > 0) {
	        query = query.substring(0, query.length() - 5);
	    }
	    System.out.println(query);
	    ResultSet cursor = smt.executeQuery(query);
	    ArrayList<Object> fila = new ArrayList<Object>();
	    while (cursor.next()) {
	        for(String nombreCol : columnasSelect) {
	            try {
	                fila.add(cursor.getInt(cursor.findColumn(nombreCol)));
	            } catch (NumberFormatException | SQLException e) {
	                fila.add(cursor.getString(cursor.findColumn(nombreCol)));
	            }
	        }
	    }
	    disconnect(smt, connection);
	    return fila;
	}

	public static int update(String tabla,HashMap<String,Object>datosAModificar,HashMap<String,Object>restricciones) throws SQLException, ConexionFallidaException {
	    Connection connection = connect();
	    Statement smt = connection.createStatement();

	    String query="update "+tabla+" set ";
	    Iterator<Entry<String, Object>> itm= datosAModificar.entrySet().iterator();
	    while(itm.hasNext()) {
	        Entry<String, Object> actual = itm.next();
	        if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
	            query+=actual.getKey()+" = "+actual.getValue()+",";
	        }else {
	            query+=actual.getKey()+" = '"+actual.getValue()+"',";
	        }

	    }
	    query=query.substring(0,query.length()-1)+" where ";
	    Iterator<Entry<String, Object>> itr=restricciones.entrySet().iterator();
	    while(itr.hasNext()) {
	        Entry<String, Object> actual = itr.next();
	        if (actual.getValue().getClass() != String.class && actual.getValue().getClass() != Character.class) {
	            query+=actual.getKey()+" = "+actual.getValue()+" and ";
	        }else {
	            query+=actual.getKey()+" = '"+actual.getValue()+"' and ";
	        }

	    }
	    query=query.substring(0,query.length()-5);

	    System.out.println(query);
	    int ret=smt.executeUpdate(query);
	    disconnect(smt, connection);

	    return ret;
	}
*/
}