package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private HashMap<String, ClienteDiscoteca> puntosTotales;

    public Usuario(String nombre, String descripcion, String localidad, String direccion, String nick,
            String qrCode, String contraseña, String apellidos, String correo,
            HashMap<String, ClienteDiscoteca> puntosEnDiscoteca, HashMap<String, ClienteDiscoteca> puntosTotales) {
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
                        String fecha = rs.getString("fecha");
                        String descripcion = rs.getString("descripcion");
                        String localidad = rs.getString("localidad");
                        eventosCercanos.add(new Evento(nombre, descripcion, localidad, fecha));
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

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public HashMap<String, ClienteDiscoteca> getPuntosEnDiscoteca() {
        return puntosEnDiscoteca;
    }

    public void setPuntosEnDiscoteca(HashMap<String, ClienteDiscoteca> puntosEnDiscoteca) {
        this.puntosEnDiscoteca = puntosEnDiscoteca;
    }

    public HashMap<String, ClienteDiscoteca> getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(HashMap<String, ClienteDiscoteca> puntosTotales) {
        this.puntosTotales = puntosTotales;
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
