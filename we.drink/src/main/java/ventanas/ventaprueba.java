package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventaprueba extends JFrame {
	
	private JFrame frame;
    private JTextField nombreCategoriaField;
    private JTextField tipoCategoriaField;
    private JTextField precioCategoriaField;
    private JComboBox<String> categoriaBox;
    private JComboBox<String> ivaBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ventaprueba window = new ventaprueba();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public  ventaprueba() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNombreCategoria = new JLabel("Nombre Categoria");
        lblNombreCategoria.setBounds(20, 30, 120, 14);
        frame.getContentPane().add(lblNombreCategoria);

        nombreCategoriaField = new JTextField();
        nombreCategoriaField.setBounds(150, 30, 150, 20);
        frame.getContentPane().add(nombreCategoriaField);
        nombreCategoriaField.setColumns(10);

        JLabel lblTipoCategoria = new JLabel("Tipo Categoria");
        lblTipoCategoria.setBounds(20, 60, 120, 14);
        frame.getContentPane().add(lblTipoCategoria);

        tipoCategoriaField = new JTextField();
        tipoCategoriaField.setBounds(150, 60, 150, 20);
        frame.getContentPane().add(tipoCategoriaField);
        tipoCategoriaField.setColumns(10);

        JLabel lblPrecioCategoria = new JLabel("Precio Categoria");
        lblPrecioCategoria.setBounds(20, 90, 120, 14);
        frame.getContentPane().add(lblPrecioCategoria);

        precioCategoriaField = new JTextField();
        precioCategoriaField.setBounds(150, 90, 150, 20);
        frame.getContentPane().add(precioCategoriaField);
        precioCategoriaField.setColumns(10);

        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(20, 120, 120, 14);
        frame.getContentPane().add(lblCategoria);

        categoriaBox = new JComboBox<>();
        categoriaBox.setModel(new DefaultComboBoxModel<>(new String[] {"Extra", "Primera", "Segunda", "SuperExtra"}));
        categoriaBox.setBounds(150, 120, 150, 20);
        frame.getContentPane().add(categoriaBox);

        JLabel lblIva = new JLabel("IVA");
        lblIva.setBounds(20, 150, 120, 14);
        frame.getContentPane().add(lblIva);

        ivaBox = new JComboBox<>();
        ivaBox.setModel(new DefaultComboBoxModel<>(new String[] {"4%", "10%", "21%"}));
        ivaBox.setBounds(150, 150, 150, 20);
        frame.getContentPane().add(ivaBox);

        JButton btnVerDatos = new JButton("Ver Datos Tecleados");
        btnVerDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precio = Double.parseDouble(precioCategoriaField.getText());
                String ivaString = (String) ivaBox.getSelectedItem();
                double iva = Double.parseDouble(ivaString.substring(0, ivaString.length() - 1)) / 100;
                double precioConIva = precio + precio * iva;

                System.out.println("Nombre Categoria: " + nombreCategoriaField.getText());
                System.out.println("Tipo Categoria: " + tipoCategoriaField.getText());
                System.out.println("Precio Categoria: " + precioCategoriaField.getText());
                System.out.println("Categoria: " + categoriaBox.getSelectedItem());
                System.out.println("IVA: " + ivaBox.getSelectedItem());
                System.out.println("Precio con IVA: " + precioConIva);
            }
        });
        btnVerDatos.setBounds(20, 200, 130, 23);
        frame.getContentPane().add(btnVerDatos);

        JButton btnLimpiarDatos = new JButton("Limpiar Datos");
        btnLimpiarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nombreCategoriaField.setText("");
                tipoCategoriaField.setText("");
                precioCategoriaField.setText("");
                categoriaBox.setSelectedIndex(0);
                ivaBox.setSelectedIndex(0);
            }
        });
        btnLimpiarDatos.setBounds(160, 200, 130, 23);
        frame.getContentPane().add(btnLimpiarDatos);
    }
}