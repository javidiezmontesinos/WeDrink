package ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaInicioSesion extends JFrame {
    private JTextField correoField;
    private JPasswordField contrasenaField;
    
    public VentanaInicioSesion() {
        // Configuración de la ventana
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Crear componentes de la interfaz
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(30, 30, 80, 25);
        add(correoLabel);
        
        correoField = new JTextField();
        correoField.setBounds(100, 30, 160, 25);
        add(correoField);
        
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setBounds(30, 70, 80, 25);
        add(contrasenaLabel);
        
        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(100, 70, 160, 25);
        add(contrasenaField);
        
        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        iniciarSesionButton.setBounds(100, 110, 120, 25);
        iniciarSesionButton.addActionListener(e -> iniciarSesion());
        add(iniciarSesionButton);
        
        setVisible(true);
    }
    
    private void iniciarSesion() {
        String correo = correoField.getText();
        String contrasena = new String(contrasenaField.getPassword());
        
        // Verificar si los datos de inicio de sesión son correctos
        boolean autenticado = verificarCredenciales(correo, contrasena);
        
        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            // Aquí puedes realizar las acciones correspondientes al inicio de sesión exitoso
        } else {
            int opcion = JOptionPane.showConfirmDialog(this, "Usuario no registrado. ¿Desea registrarse?");
            
            if (opcion == JOptionPane.YES_OPTION) {
                // Aquí puedes implementar la lógica para el registro de un nuevo usuario
            }
        }
    }
    
    private boolean verificarCredenciales(String correo, String contrasena) {
        // Aquí puedes implementar la lógica para verificar las credenciales
        // Puedes utilizar una base de datos o cualquier otro mecanismo de almacenamiento
        
        // Ejemplo de verificación básica
        return correo.equals("th.com") && contrasena.equals("123456");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaInicioSesion::new);
    }
}