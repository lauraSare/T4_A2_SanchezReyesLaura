package conexionBD;

import java.sql.*;

public class ConexionBD {
    private Connection conexion; //atributo
    private Statement statement;
    /* NOTA: Es preferible utilizar PreparedStatement para evitar SQL INJECTION
     */
    private ResultSet resultSet;

    public ConexionBD(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String URL = "jdbc:mysql://localhost:3306/BD_Escuela_Topicos_2024"; //direccion de a que se conecta a mysql
            conexion = DriverManager.getConnection(URL,  "root",  "judilth@3");

            System.out.println("Yeeeeiiiii ya casi soy ISC");

        }catch(ClassNotFoundException e){
            // throw new RuntimeException(e);
            System.out.println("Error en el DRIVER");
        } catch (SQLException e) {
            System.out.println("Error en la URL");
        }

    }
    //Metodos para operaciones ABC (ALTAS, BAJAS, CAMBIOS)
    //(DataManipulation Languaje)
    public boolean ejecutarInstruccionDML (String instruccionSQL) {
        boolean res = false;
        try {
            statement = conexion.createStatement();

            if(statement.executeUpdate(instruccionSQL) >= 1)
                res = true;

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error en instruccion SQL");
        }

        return res;
    }

    //metodo para consultas
    public ResultSet ejecutarConsultaSQL(String instruccionSQL){
        resultSet = null;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(instruccionSQL);

        }catch (SQLException e) {
            System.out.println("Error en instruccion SQL");
        }
        return resultSet;
    }

    public static void main(String[] args) {
        new ConexionBD();

    }



}//class
