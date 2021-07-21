package datos;

import java.sql.*;
import java.util.ArrayList;

import modelo.*;

public class ManejaDatosUsuario{
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    Statement statement;

    public ManejaDatosUsuario(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
            statement = connection.createStatement();
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    //Método que escribe un nuevo usuario en la base de datos
    public void crear(Usuario usuario) {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        try {
            String sql = "INSERT INTO `fungiaraucania`.`usuarios` (`nombredeusuario`,`email`,`contrasena`, `fechadecreacion`) VALUES(?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setNString (1, usuario.getNombre ());
            preparedStatement.setNString (2, usuario.getCorreo ());
            preparedStatement.setNString (3, usuario.getClave ());
            preparedStatement.setDate (4, usuario.getFechaDeCreacion ());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<> ();
        try {
            //Los SQL Statement retornan objetos ResultSet. ResultSet es una tabla de datos representando los resultados de la base de datos
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");
            while(resultSet.next()) { // .next retorna un booleano que es true si hay más datos que mostrar
                int id = resultSet.getInt ("id");
                String nombre = resultSet.getString ("nombredeusuario");
                String correo = resultSet.getString ("correo");
                String contrasena = resultSet.getString ("contrasena");
                Date fechaDeCreacion = resultSet.getDate ("fechadecreacion");

                usuarios.add(new Usuario(id, nombre, correo, contrasena,fechaDeCreacion));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }
    //Retorna un objeto Usuario con los datos obtenidos de la base de datos usando el id del usuario
    public Usuario leer(int id) {
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt (1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) { //Si es que existe el usuario con el id indicado, entonces resultSet.next() será true
                String nombreDeUsuario = resultSet.getString ("nombredeusuario");
                String correo = resultSet.getString ("correo");
                String contrasena = resultSet.getString ("contrasena");
                Date fechaDeCreacion = resultSet.getDate ("fechadecreacion");

                return new Usuario (id, nombreDeUsuario, contrasena, correo, fechaDeCreacion);
            }
            else{
                System.out.println ("El usuario con el id "+id+" no se encuentra en nuestra base de datos.");
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return null;
    }

    //Actualiza el valor de un atributo especifico del usuario
    public void actualizar(Usuario usuario, String atributoACambiar, String nuevoValorAtributo) {
        int id = usuario.getId ();
        try {
            String sql = "UPDATE usuarios SET ? = ? WHERE id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setNString (1, atributoACambiar.toLowerCase());
            preparedStatement.setNString (2, nuevoValorAtributo);
            preparedStatement.setInt (3, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public void eliminar(Usuario usuario) {
        int id = usuario.getId ();
        try {
            String sql = "DELETE FROM usuarios WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt (1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public Usuario iniciarSesion(String correoONombreDeUsuario, String contrasena){
        try {
            String sql = "SELECT * FROM usuarios WHERE (correo = ? OR nombredeusuario = ?) AND contrasena = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setNString (1, correoONombreDeUsuario);
            preparedStatement.setNString (2, correoONombreDeUsuario);
            preparedStatement.setNString (3, contrasena);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next ()){
                String correo = resultSet.getString ("correo");
                String nombreDeUsuario = resultSet.getString ("nombredeusuario");
                int id = Integer.parseInt (resultSet.getString ("id"));

                return new Usuario (id, nombreDeUsuario, contrasena, correoONombreDeUsuario);
            }
            else{
                System.out.println ("El usuario no se encuentra en nuestra base de datos");
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return null;
    }
}
