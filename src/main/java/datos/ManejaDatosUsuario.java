package datos;

import java.sql.*;
import java.util.ArrayList;

import com.password4j.Hash;
import com.password4j.Password;
import modelo.*;

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
        String clave = usuario.getClave ();
        //Hashear la contraseña
        Hash hash = Password.hash(clave).addPepper ().withBCrypt ();
        clave = hash.getResult ();
        preparedStatement.setNString(3, clave);
        preparedStatement.setDate(4, usuario.getFechaDeCreacion());
        preparedStatement.executeUpdate();
    }

    public boolean handleCrear(Usuario usuario) {
        try {
            if(usuario.getId() != -1){
                //si no es igual a -1, significa que el id viene ya viene puesto, lo cual no puede suceder, porque el id
                //es autogenerado por la base de datos.
                return false;
            }
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
    private String iniciarUsuario(ResultSet resultSet, String contrasena) throws SQLException {

        String hashedContrasena = resultSet.getString ("contrasena");
        String nombreDeUsuario = resultSet.getString("nombredeusuario");
        boolean verificado = Password.check(contrasena, hashedContrasena).withBCrypt ();
        if(verificado){
            return nombreDeUsuario;
        }else{
            return "Contraseña incorrecta";
        }

    }

    private String iniciarSesion(String correoONombreDeUsuario, String contrasena) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE (correo = ? OR nombredeusuario = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, correoONombreDeUsuario);
        preparedStatement.setNString(2, correoONombreDeUsuario);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return iniciarUsuario(resultSet, contrasena);
        } else {
            return "Nombre de usuario o email no existe.";
        }
    }

    public String handleIniciarSesion(String correoONombreDeUsuario, String contrasena) {
        try {
            return iniciarSesion(correoONombreDeUsuario, contrasena);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return "Error";
        }
    }
}