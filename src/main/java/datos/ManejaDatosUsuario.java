package datos;

import java.sql.*;
import java.util.ArrayList;

import modelo.*;

import javax.xml.transform.Result;

public class ManejaDatosUsuario {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    private static Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    private static Statement statement;

    public ManejaDatosUsuario() {
        handleEstablecerConexion("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
    }

    private void establecerConexion(String baseDatos, String usuario, String puerto) throws SQLException {
        connection = DriverManager.getConnection(baseDatos, usuario, puerto);
        statement = connection.createStatement();
    }
    public boolean handleEstablecerConexion(String baseDatos, String usuario, String puerto) {
        try {
            establecerConexion(baseDatos, usuario, puerto);
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }


    //Método que escribe un nuevo usuario en la base de datos
    private void crear(Usuario usuario) throws SQLException {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        String sql = "INSERT INTO `fungiaraucania`.`usuarios` (`nombredeusuario`,`correo`,`contrasena`, `fechadecreacion`) VALUES(?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, usuario.getNombre());
        preparedStatement.setNString(2, usuario.getCorreo());
        preparedStatement.setNString(3, usuario.getClave());
        preparedStatement.setDate(4, usuario.getFechaDeCreacion());
        preparedStatement.executeUpdate();
    }

    public boolean handleCrear(Usuario usuario) {
        try {
            crear(usuario);
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    private ArrayList<Usuario> obtenerUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //Los SQL Statement retornan objetos ResultSet. ResultSet es una tabla de datos representando los resultados de la base de datos
        ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");
        while (resultSet.next()) { // .next retorna un booleano que es true si hay más datos que mostrar
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombredeusuario");
            String correo = resultSet.getString("correo");
            String contrasena = resultSet.getString("contrasena");
            Date fechaDeCreacion = resultSet.getDate("fechadecreacion");

            usuarios.add(new Usuario(id, nombre, correo, contrasena, fechaDeCreacion));
        }
        return usuarios;
    }

    public ArrayList<Usuario> handleObtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = obtenerUsuarios();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return usuarios;
    }


    private Usuario iniciarUsuario(ResultSet resultSet, String contrasena, String correoONombreDeUsuario) throws SQLException {

        String correo = resultSet.getString("correo");
        String nombreDeUsuario = resultSet.getString("nombredeusuario");
        int id = Integer.parseInt(resultSet.getString("id"));

        return new Usuario(id, nombreDeUsuario, contrasena, correoONombreDeUsuario);
    }

    private Usuario iniciarSesion(String correoONombreDeUsuario, String contrasena) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE (correo = ? OR nombredeusuario = ?) AND contrasena = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, correoONombreDeUsuario);
        preparedStatement.setNString(2, correoONombreDeUsuario);
        preparedStatement.setNString(3, contrasena);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return iniciarUsuario(resultSet, correoONombreDeUsuario, contrasena);
        } else {
            System.out.println("El usuario no se encuentra en nuestra base de datos");
            return null;
        }
    }

    public Usuario handleIniciarSesion(String correoONombreDeUsuario, String contrasena) {
        try {
            return iniciarSesion(correoONombreDeUsuario, contrasena);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}