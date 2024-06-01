package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class VentanaInicio extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tablaAlumnos;

    public VentanaInicio() {
        setTitle("Ventana Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnAltas = new JButton("Altas");
        JButton btnBajas = new JButton("Bajas");
        JButton btnCambios = new JButton("Cambios");
        JButton btnConsultas = new JButton("Consultas");

        btnAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAltas ventanaAltas = new VentanaAltas(VentanaInicio.this);
                ventanaAltas.setVisible(true);
            }
        });
        btnBajas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBajas ventanaBajas = new VentanaBajas();
                ventanaBajas.setVisible(true);
            }
        });
        btnCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCambios ventanaCambios = new VentanaCambios();
                ventanaCambios.setVisible(true);
            }
        });

        btnConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaConsultas ventanaConsultas = new VentanaConsultas();
                ventanaConsultas.setVisible(true);
            }
        });

        setLayout(new GridLayout(2, 2));
        add(btnAltas);
        add(btnBajas);
        add(btnCambios);
        add(btnConsultas);
    }

    public void actualizarTablas(JLabel tabla){
        final String CONTROLADOR_JDBC = "com.mysql.cj.jbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/BD_Escuela_Topicos_2024";
        final String CONSULTA = "SELECT * FROM alumnos";

        try {
            //math.java
            ResultSetTableModel modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL, CONSULTA);
            tabla.setModel(modelo);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la consulta");
            }catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }
}
