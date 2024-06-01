 package vista;

import Controlador.AlumnoDAO;
import modelo.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaBajas extends JFrame {
    private JTextField textFieldNumeroControl, textFieldNombre, textFieldApellidoPaterno, textFieldApellidoMaterno;
    private JComboBox<String> comboBoxSemestre, comboBoxCarrera;
    private DefaultTableModel modeloTabla;
    private JTable tablaAlumnos;

    public VentanaBajas() {
        setTitle("Bajas Alumnos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelNumeroControl = new JLabel("Número de Control:");
        labelNumeroControl.setBounds(30, 30, 150, 25);
        add(labelNumeroControl);

        textFieldNumeroControl = new JTextField(10);
        textFieldNumeroControl.setBounds(200, 30, 200, 25);
        add(textFieldNumeroControl);

        JLabel labelNombre = new JLabel("NOMBRE(S):");
        labelNombre.setBounds(30, 70, 150, 25);
        add(labelNombre);

        textFieldNombre = new JTextField(20);
        textFieldNombre.setBounds(200, 70, 200, 25);
        add(textFieldNombre);

        JLabel labelApellidoPaterno = new JLabel("APELLIDO PATERNO:");
        labelApellidoPaterno.setBounds(30, 110, 150, 25);
        add(labelApellidoPaterno);

        textFieldApellidoPaterno = new JTextField(20);
        textFieldApellidoPaterno.setBounds(200, 110, 200, 25);
        add(textFieldApellidoPaterno);

        JLabel labelApellidoMaterno = new JLabel("APELLIDO MATERNO:");
        labelApellidoMaterno.setBounds(30, 150, 150, 25);
        add(labelApellidoMaterno);

        textFieldApellidoMaterno = new JTextField(20);
        textFieldApellidoMaterno.setBounds(200, 150, 200, 25);
        add(textFieldApellidoMaterno);

        JLabel labelSemestre = new JLabel("SEMESTRE:");
        labelSemestre.setBounds(30, 190, 150, 25);
        add(labelSemestre);

        comboBoxSemestre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        comboBoxSemestre.setBounds(200, 190, 200, 25);
        add(comboBoxSemestre);

        JLabel labelCarrera = new JLabel("CARRERA:");
        labelCarrera.setBounds(30, 230, 150, 25);
        add(labelCarrera);

        comboBoxCarrera = new JComboBox<>(new String[]{"Ingeniería en Sistemas", "Ingeniería en Mecatrónica", "Administración", "Contaduría"});
        comboBoxCarrera.setBounds(200, 230, 200, 25);
        add(comboBoxCarrera);

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(450, 30, 120, 25);
        add(btnEliminar);

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(450, 70, 120, 25);
        add(btnBuscar);

        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(450, 110, 120, 25);
        add(btnCancelar);

        String[] columnas = {"No. de Control", "Nombre", "Ap. Paterno", "Ap. Materno", "Semestre", "Carrera"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        scrollPane.setBounds(30, 270, 740, 250);
        add(scrollPane);

        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlumno();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void buscarAlumno() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ArrayList<Alumno> lista = alumnoDAO.mostrarAlumnos("");
        modeloTabla.setRowCount(0);

        for (Alumno alumno : lista) {
            Object[] fila = {
                    alumno.getNumControl(),
                    alumno.getNombre(),
                    alumno.getPrimerAp(),
                    alumno.getSegundoAp(),
                    alumno.getSemestre(),
                    alumno.getCarrera()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void eliminarAlumno() {
        String numControl = textFieldNumeroControl.getText();
        AlumnoDAO alumnoDAO = new AlumnoDAO();

        if (alumnoDAO.eliminarAlumno(numControl)) {
            JOptionPane.showMessageDialog(this, "Alumno eliminado con éxito!");
            buscarAlumno();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el alumno!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaBajas());
    }
}
