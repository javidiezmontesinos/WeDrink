package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import exceptions.ConexionFallidaException;
import exceptions.ContraseñaInvalidaException;
import exceptions.UsuarioNoExisteException;
import utils.DAO;

public class Usuario extends SuperClaseLugar {
    private String nick;
    private String qrCode;
    private String apellidos;
    private String correo;
    private String contraseña;
    private HashMap<String, ClienteDiscoteca> puntosEnDiscoteca;
    private HashMap<String, UsuarioPuntos> puntosTotales;

    public Usuario(String nombre, String descripcion, String localidad, String direccion, String nick,
            String qrCode, String contraseña, String apellidos, String correo,
            HashMap<String, ClienteDiscoteca> puntosEnDiscoteca, HashMap<String, UsuarioPuntos> puntosTotales) {
        super(nombre, descripcion, localidad, direccion);
        this.nick = nick;
        this.qrCode = qrCode;
        this.contraseña = contraseña;
        this.apellidos = apellidos;
        this.correo = correo;
        this.puntosEnDiscoteca = puntosEnDiscoteca;
        this.puntosTotales = puntosTotales;
    }

    public Usuario(String correo, String contraseña)
            throws SQLException, UsuarioNoExisteException, ContraseñaInvalidaException, ConexionFallidaException {
        super(null, null, null, null);
        Connection connection = null;
        try {
            connection = DAO.connect();
        } catch (ConexionFallidaException e1) {
            e1.printStackTrace();
        }

        String query = "SELECT nick, nombre, contraseña, localidad, direccion, qrUsuario, apellidos FROM usuario WHERE correo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String contraseñaAlmacenada = resultSet.getString("contraseña");
                if (contraseñaAlmacenada.equals(contraseña)) {
                    this.nick = resultSet.getString("nick");
                    this.nombre = resultSet.getString("nombre");
                    this.correo = correo;
                    this.contraseña = contraseña;
                    this.localidad = resultSet.getString("localidad");
                    this.direccion = resultSet.getString("direccion");
                    this.qrCode = resultSet.getString("qrUsuario");
                    this.apellidos = resultSet.getString("apellidos");
                } else {
                    throw new ContraseñaInvalidaException("CONTRASEÑA INVALIDA");
                }
            } else {
                throw new UsuarioNoExisteException("SIN DATOS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void verEventosCercanos() throws ConexionFallidaException {
        List<Evento> eventosCercanos = obtenerEventosCercanos();

        if (eventosCercanos.isEmpty()) {
            System.out.println("No hay eventos cercanos en tu localidad.");
        } else {
            System.out.println("Eventos cercanos en " + localidad + ":");
            for (Evento evento : eventosCercanos) {
                System.out.println(evento.getNombre() + " - " + evento.getFecha());
            }
        }
    }

    private List<Evento> obtenerEventosCercanos() throws ConexionFallidaException {
        List<Evento> eventosCercanos = new ArrayList<>();

        try (Connection connection = DAO.connect()) {
            String sql = "SELECT nombre, fecha FROM eventos WHERE localidad = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, localidad);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String nombre = rs.getString("nombre");
                        Date fecha = rs.getDate("fecha");
                        String descripcion = rs.getString("descripcion");
                        String localidad = rs.getString("localidad");
                        //eventosCercanos.add(new Evento(nombre, descripcion, localidad, fecha));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventosCercanos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) throws ConexionFallidaException {
        this.contraseña = contraseña;
        // Actualizar en la base de datos
        try (Connection connection = DAO.connect()) {
            String query = "UPDATE Usuario SET contraseña = ? WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contraseña);
            statement.setString(2, correo);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El campo 'contraseña' se actualizó en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void registrar_usuario(String nick, String contraseña, String nombre, String correo, String localidad, String direccion, String qrCode, String apellidos)
            throws SQLException, UsuarioNoExisteException, ConexionFallidaException {
        try (Connection connection = DAO.connect()) {
            // Verifica si el nombre de usuario ya existe
            String checkQuery = "SELECT * FROM Usuario WHERE nick = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, nick);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                throw new SQLException("El nombre de usuario ya existe");
            }

            String query = "INSERT INTO Usuario (nick, nombre, correo, contraseña, localidad, direccion, qrUsuario, apellidos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nick);
            statement.setString(2, nombre);
            statement.setString(3, correo);
            statement.setString(4, contraseña);
            statement.setString(5, localidad);
            statement.setString(6, direccion);
            statement.setString(7, qrCode);
            statement.setString(8, apellidos);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario registrado exitosamente!");
            }
        }
    }
    
    

    public String getNick() {
        return nick;
    }

    public void iniciar_sesion(String correo, String contraseña) {
        Connection connection = null;
        try {
            connection = DAO.connect();
        } catch (ConexionFallidaException e1) {
            e1.printStackTrace();
        }

        String query = "SELECT * FROM Usuario WHERE correo = ? AND contraseña = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, correo);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("¡Inicio de sesión exitoso!");
                this.nick = resultSet.getString("nick");
                this.nombre = resultSet.getString("nombre");
                this.correo = resultSet.getString("correo");
                this.contraseña = resultSet.getString("contraseña");
                this.localidad = resultSet.getString("localidad");
                this.direccion = resultSet.getString("direccion");
                this.qrCode = resultSet.getString("qrUsuario");
                this.apellidos = resultSet.getString("apellidos");
            } else {
                System.out.println("¡Correo o contraseña incorrectos!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void comprarProducto(Producto productoComprado) {
        
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNick(String nick) throws ConexionFallidaException {
        this.nick = nick;
        // Actualizar en la base de datos
        try (Connection connection = DAO.connect()) {
            String query = "UPDATE Usuario SET nick = ? WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nick);
            statement.setString(2, correo);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El campo 'nick' se actualizó en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setCorreo(String correo) throws ConexionFallidaException {
        this.correo = correo;
        // Actualizar en la base de datos
        try (Connection connection = DAO.connect()) {
            String query = "UPDATE Usuario SET correo = ? WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, correo);
            statement.setString(2, correo);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El campo 'correo' se actualizó en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setApellidos(String apellidos) throws ConexionFallidaException {
        this.apellidos = apellidos;
        // Actualizar en la base de datos
        try (Connection connection = DAO.connect()) {
            String query = "UPDATE Usuario SET apellidos = ? WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, apellidos);
            statement.setString(2, correo);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El campo 'apellidos' se actualizó en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCorreo() {
        return correo;
    }

    public HashMap<String, ClienteDiscoteca> getPuntosEnDiscoteca() {
        return puntosEnDiscoteca;
    }

    public void setPuntosEnDiscoteca(HashMap<String, ClienteDiscoteca> puntosEnDiscoteca) {
        this.puntosEnDiscoteca = puntosEnDiscoteca;
        // Actualizar en la base de datos
        try (Connection connection = DAO.connect()) {
            // Primero, obtener el ID del usuario basado en el correo
            String selectQuery = "SELECT id FROM Usuario WHERE correo = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, correo); // Suponiendo que tienes una variable de instancia "correo" para identificar al usuario
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int usuarioId = resultSet.getInt("id");

                // Luego, eliminar los puntos en discoteca existentes del usuario en la base de datos
                String deleteQuery = "DELETE FROM PuntosEnDiscoteca WHERE usuario_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, usuarioId);
                deleteStatement.executeUpdate();

                // Por último, insertar los nuevos puntos en discoteca del usuario en la base de datos
                String insertQuery = "INSERT INTO PuntosEnDiscoteca (usuario_id, discoteca_id, puntos) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                for (Entry<String, ClienteDiscoteca> entry : puntosEnDiscoteca.entrySet()) {
                    String discotecaId = entry.getKey();
                    ClienteDiscoteca clienteDiscoteca = entry.getValue();
                    int puntos = clienteDiscoteca.getPuntosAcumuladosDiscoteca(); // Asegúrate de obtener los puntos correctamente desde el objeto ClienteDiscoteca

                    insertStatement.setInt(1, usuarioId);
                    insertStatement.setString(2, discotecaId);
                    insertStatement.setInt(3, puntos);
                    insertStatement.executeUpdate();
                }

                System.out.println("Los puntos en discoteca se actualizaron en la base de datos");
            } else {
                System.out.println("No se encontró el usuario en la base de datos");
            }
        } catch (SQLException | ConexionFallidaException e) {
            e.printStackTrace();
        }
    }

    

    public HashMap<String, UsuarioPuntos> getPuntosTotales() {
		return puntosTotales;
	}

    public void setPuntosTotales(HashMap<String, UsuarioPuntos> puntosTotales) {
        this.puntosTotales = puntosTotales;
        
        try (Connection connection = DAO.connect()) {
            // Primero, obtener el ID del usuario basado en el correo
            String selectQuery = "SELECT id FROM Usuario WHERE correo = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, correo); // Suponiendo que tienes una variable de instancia "correo" para identificar al usuario
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int usuarioId = resultSet.getInt("id");

                // Luego, eliminar los puntos totales existentes del usuario en la base de datos
                String deleteQuery = "DELETE FROM usuariopuntos WHERE usuario_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, usuarioId);
                deleteStatement.executeUpdate();

                // Por último, insertar los nuevos puntos totales del usuario en la base de datos
                String insertQuery = "INSERT INTO usuariopuntos (usuario_id, puntos) VALUES (?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                for (Entry<String, UsuarioPuntos> entry : puntosTotales.entrySet()) {
                    String nombreDiscoteca = entry.getKey();
                    UsuarioPuntos usuarioPuntos = entry.getValue();
                    int puntos = usuarioPuntos.getPuntosTotales(); // Asegúrate de obtener los puntos correctamente desde el objeto UsuarioPuntos

                    insertStatement.setInt(1, usuarioId);
                    insertStatement.setInt(2, puntos);
                    insertStatement.executeUpdate();
                }

                System.out.println("Los puntos totales se actualizaron en la base de datos");
            } else {
                System.out.println("No se encontró el usuario en la base de datos");
            }
        } catch (SQLException | ConexionFallidaException e) {
            e.printStackTrace();
        }
    }

	@Override
    public String toString() {
        return "Usuario [nick=" + nick + ", qrCode=" + qrCode + ", apellidos=" + apellidos + ", correo=" + correo
                + ", puntosEnDiscoteca=" + puntosEnDiscoteca + ", puntosTotales=" + puntosTotales + "]";
    }

	public static Usuario getNick(String nick2) {
		// TODO Auto-generated method stub
		return null;
	}
}
