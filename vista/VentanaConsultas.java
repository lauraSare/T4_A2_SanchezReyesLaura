package vista;

import Controlador.AlumnoDAO;
import modelo.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaConsultas extends JFrame {
    private JTextField textFieldNombre, textFieldApellidoPaterno, textFieldApellidoMaterno;
    private JComboBox<String> comboBoxSemestre, comboBoxCarrera;
    private DefaultTableModel modeloTabla;
    private JTable tablaAlumnos;

    public VentanaConsultas() {
        setTitle("Consultas Alumnos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(30, 30, 150, 25);
        add(labelNombre);

        textFieldNombre = new JTextField(20);
        textFieldNombre.setBounds(200, 30, 200, 25);
        add(textFieldNombre);

        JLabel labelApellidoPaterno = new JLabel("Apellido Paterno:");
        labelApellidoPaterno.setBounds(30, 70, 150, 25);
        add(labelApellidoPaterno);

        textFieldApellidoPaterno = new JTextField(20);
        textFieldApellidoPaterno.setBounds(200, 70, 200, 25);
        add(textFieldApellidoPaterno);

        JLabel labelApellidoMaterno = new JLabel("Apellido Materno:");
        labelApellidoMaterno.setBounds(30, 110, 150, 25);
        add(labelApellidoMaterno);

        textFieldApellidoMaterno = new JTextField(20);
        textFieldApellidoMaterno.setBounds(200, 110, 200, 25);
        add(textFieldApellidoMaterno);

        JLabel labelSemestre = new JLabel("Semestre:");
        labelSemestre.setBounds(30, 150, 150, 25);
        add(labelSemestre);

        comboBoxSemestre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        comboBoxSemestre.setBounds(200, 150, 200, 25);
        add(comboBoxSemestre);

        JLabel labelCarrera = new JLabel("Carrera:");
        labelCarrera.setBounds(30, 190, 150, 25);
        add(labelCarrera);

        comboBoxCarrera = new JComboBox<>(new String[]{"Ingeniería en Sistemas", "Ingeniería en Mecatrónica", "Administración", "Contaduría"});
        comboBoxCarrera.setBounds(200, 190, 200, 25);
        add(comboBoxCarrera);

        JButton botonBuscar = new JButton("BUSCAR");
        botonBuscar.setBounds(450, 30, 120, 25);
        add(botonBuscar);

        JButton botonActualizar = new JButton("ACTUALIZAR");
        botonActualizar.setBounds(450, 70, 120, 25);
        add(botonActualizar);

        JButton botonBorrar = new JButton("BORRAR");
        botonBorrar.setBounds(450, 110, 120, 25);
        add(botonBorrar);

        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(450, 150, 120, 25);
        add(botonCancelar);

        String[] columnas = {"No. de Control", "Nombre", "Ap. Paterno", "Ap. Materno", "Semestre", "Carrera"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        scrollPane.setBounds(30, 230, 740, 300);
        add(scrollPane);

        setVisible(true);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarAlumno();
            }
        });

        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarAlumno();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void buscarAlumno() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ArrayList<Alumno> lista = alumnoDAO.mostrarAlumnos("");
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

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

    private void actualizarAlumno() {
        String numControl = JOptionPane.showInputDialog("Ingrese el número de control del alumno a actualizar:");
        AlumnoDAO alumnoDAO = new AlumnoDAO();

        ArrayList<Alumno> lista = alumnoDAO.buscarAlumnos(numControl);

        if (!lista.isEmpty()) {
            Alumno alumno = lista.get(0);
            textFieldNombre.setText(alumno.getNombre());
            textFieldApellidoPaterno.setText(alumno.getPrimerAp());
            textFieldApellidoMaterno.setText(alumno.getSegundoAp());
            comboBoxSemestre.setSelectedItem(String.valueOf(alumno.getSemestre()));
            comboBoxCarrera.setSelectedItem(alumno.getCarrera());
        } else {
            JOptionPane.showMessageDialog(this, "Alumno no encontrado!");
        }
    }


    private void borrarAlumno() {
        String numControl = JOptionPane.showInputDialog("Ingrese el número de control del alumno a borrar:");
        AlumnoDAO alumnoDAO = new AlumnoDAO();

        if (alumnoDAO.eliminarAlumno(numControl)) {
            JOptionPane.showMessageDialog(this, "Alumno eliminado con éxito!");
            buscarAlumno();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el alumno!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaConsultas());
    }
}
