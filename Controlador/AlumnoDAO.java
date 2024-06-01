package Controlador;
import conexionBD.ConexionBD;
import modelo.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO {
    //metodos para acciones ABCC (CRUD)
    ConexionBD conexion = new ConexionBD();

    //---------------------------METODOS ABCC---------------------
    //METODO ALTAS
    public boolean agregarAlumno(Alumno alumno) {
        boolean res = false;
        // String sql = "INSERT INTO alumnos VALUES('01','Luke','Skywalker','-', 50, 7, 'ISC')";
        //cambiar la linea de abajo para q se ponda con los valores estatcos de la foto 17/05/24
        //String sql2= "INSERT INTO alumnos VALUES("+alumno.getNumControl()+"', '01','Luke', 'Skywalker', '-', '1950/10/10', 50, 7, 'ISC')";
        //  String sql2= "INSERT INTO alumnos VALUES('"+ alumno.getNumControl()+'," '

        // res=  conexion.ejecutarInstruccionDML(sql);
        return res;
    }

    //metodo eliminar
    public boolean eliminarAlumno(String numControl) {
        String sql = "DELETE FROM alumnos WHERE Num_Control='" + numControl + "'";
        return conexion.ejecutarInstruccionDML(sql);
    }

    //metodo cambios
    public boolean actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE alumnos SET Nombre='" + alumno.getNombre() + "', Primer_Ap='X', Segundo_Ap='X' ... WHERE Num_Control='" + alumno.getNumControl() + "'";
        return conexion.ejecutarInstruccionDML(sql);

    }

    public ArrayList<Alumno> buscarAlumnos(String filtro) {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        String sql = "SELECT * FROM alumnos WHERE columna_a_buscar LIKE '%" + filtro + "%'";

        ResultSet rs = conexion.ejecutarConsultaSQL(sql);

        try {
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("numControl"),
                        rs.getString("Nombre"),
                        rs.getString("PrimerAp"),
                        rs.getString("SegundoAP"),
                        rs.getByte("Edad"),
                        rs.getByte("Semestre"),
                        rs.getString("Carrera")
                );

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAlumnos;
    }

    //metodo consultas
    public ArrayList mostrarAlumnos(String filtro){
        ArrayList listaAlumnos= new ArrayList();

        String sql = "SELECT * FROM alumnos";
        ResultSet rs= conexion.ejecutarConsultaSQL(sql);

        try{
            rs.next();
            do{
                String nc= rs.getString(1);
                String curp= rs.getString("CURP");
                String n = rs.getString("Nombre");
                String primer = rs.getString("Primer_Ap");
                String segun = rs.getString("Segundo_AP");
                String fec = rs.getString("Fecha_Nac");
                byte e = rs.getByte("Edad");
                byte s = rs.getByte("Semestre");
                String c = rs.getString("Carrera");


            }while(rs.next());

        }catch(SQLException e){
            e.printStackTrace();
        }


        return listaAlumnos;
    }



}
