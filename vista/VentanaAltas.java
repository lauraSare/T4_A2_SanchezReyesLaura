package vista;

import Controlador.AlumnoDAO;
import modelo.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaAltas extends JFrame {
    private JTextField txtNumControl, txtNombre, txtApellidoP, txtApellidoM;
    private JComboBox<String> comboBoxSemestre, comboBoxCarrera;
    private DefaultTableModel modeloTabla;
    private JTable tablaAlumnos;
    private VentanaInicio ventanaInicio;
    private AlumnoDAO alumnoDAO;

    public VentanaAltas(VentanaInicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        setTitle("Altas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        alumnoDAO = new AlumnoDAO();

        JLabel lblNumControl = new JLabel("Número de Control:");
        lblNumControl.setBounds(10, 10, 150, 25);
        add(lblNumControl);

        txtNumControl = new JTextField();
        txtNumControl.setBounds(170, 10, 200, 25);
        add(txtNumControl);

        JLabel lblNombre = new JLabel("Nombre(s):");
        lblNombre.setBounds(10, 45, 150, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(170, 45, 200, 25);
        add(txtNombre);

        JLabel lblApellidoP = new JLabel("Apellido Paterno:");
        lblApellidoP.setBounds(10, 80, 150, 25);
        add(lblApellidoP);

        txtApellidoP = new JTextField();
        txtApellidoP.setBounds(170, 80, 200, 25);
        add(txtApellidoP);

        JLabel lblApellidoM = new JLabel("Apellido Materno:");
        lblApellidoM.setBounds(10, 115, 150, 25);
        add(lblApellidoM);

        txtApellidoM = new JTextField();
        txtApellidoM.setBounds(170, 115, 200, 25);
        add(txtApellidoM);

        JLabel lblSemestre = new JLabel("Semestre:");
        lblSemestre.setBounds(10, 150, 150, 25);
        add(lblSemestre);

        comboBoxSemestre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6" , "7", "8" ,"9" , "10"});
        comboBoxSemestre.setBounds(170, 150, 200, 25);
        add(comboBoxSemestre);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(10, 185, 150, 25);
        add(lblCarrera);

        comboBoxCarrera = new JComboBox<>(new String[]{"Ingeniería en Sistemas", "Ingeniería en Mecatronica", "Administración", "Contaduría"});
        comboBoxCarrera.setBounds(170, 185, 200, 25);
        add(comboBoxCarrera);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(50, 250, 100, 25);
        add(btnAgregar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(160, 250, 100, 25);
        add(btnBorrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 250, 100, 25);
        add(btnCancelar);

        String[] columnas = {"Número de Control", "Nombre", "Apellido Paterno", "Apellido Materno", "Semestre", "Carrera"};

        modeloTabla = new DefaultTableModel(columnas, 10);

        ArrayList<Alumno> listaAlumnos = alumnoDAO.mostrarAlumnos("");

        for (Alumno alumno : listaAlumnos) {
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

        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        scrollPane.setBounds(10, 290, 560, 200);
        add(scrollPane);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numControl = txtNumControl.getText();
                String nombre = txtNombre.getText();
                String apellidoP = txtApellidoP.getText();
                String apellidoM = txtApellidoM.getText();
                String carrera = (String) comboBoxCarrera.getSelectedItem();
                byte semestre = Byte.parseByte((String) comboBoxSemestre.getSelectedItem());

                Alumno alumno = new Alumno(numControl, nombre, apellidoP, apellidoM, (byte) 18, semestre, carrera);
                AlumnoDAO alumnoDAO = new AlumnoDAO();

                if (alumnoDAO.agregarAlumno(alumno)) {
                    JOptionPane.showMessageDialog(null, "Registro AGREGADO CON EXITO!!!");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR en la insercion!!");
                }
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNumControl.setText("");
                txtNombre.setText("");
                txtApellidoP.setText("");
                txtApellidoM.setText("");
                comboBoxSemestre.setSelectedIndex(0);
                comboBoxCarrera.setSelectedIndex(0);
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void actualizarTabla() {
        if (modeloTabla != null) {
            ArrayList<Alumno> listaAlumnos = alumnoDAO.mostrarAlumnos("");

            modeloTabla.setRowCount(0);

            for (Alumno alumno : listaAlumnos) {
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
        } else {
            System.out.println("El modelo de tabla es nulo.");
        }
    }
}
