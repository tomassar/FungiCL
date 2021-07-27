package datos;

import java.sql.*;
import java.util.ArrayList;

import com.password4j.Hash;
import com.password4j.Password;
import modelo.*;

/**
 * @Autores: Proyecto FungiAraucania
 * @Descripción: Constructor de la clase ManejaDatosHongo.
 * Realiza conexión con la base de datos del proyecto.
 * No requiere parámetros.
 **/

public class ManejaDatosUsuario {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    private static Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    private static Statement statement;

    /**
     * Constructor de la clase, se conecta automáticamente con la base de datos.
     */

    public ManejaDatosUsuario() {
        handleEstablecerConexion("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
    }

    /**
     * Método privado que comunica la clase con la base de datos.
     * Favor de revisar la clase SQL de Java.
     * @param baseDatos String con la ubicación de la base de datos de la base de datos.
     * @param usuario String con el nombre del usuario.
     * @param puerto String con el puerto que utiliza la base de datos.
     * @throws SQLException
     **/

    private void establecerConexion(String baseDatos, String usuario, String puerto) throws SQLException {
        connection = DriverManager.getConnection(baseDatos, usuario, puerto);
        statement = connection.createStatement();
    }

    /**
     * Método publico utilizado para realizar la conexión con la base de datos.
     * @param baseDatos String con la ubicación de la base de datos de la base de datos.
     * @param usuario String con el nombre del usuario.
     * @param puerto String con el puerto que utiliza la base de datos.
     * @return: boolean.
     **/

    public boolean handleEstablecerConexion(String baseDatos, String usuario, String puerto) {
        try {
            establecerConexion(baseDatos, usuario, puerto);
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método privado que escribe un nuevo usuario en la base de datos.
     * @param usuario Objeto de tipo Usuario. Revisar documentación de Usuario en modelo.
     * @throws SQLException
     **/

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

    /**
     * Método público que se encarga de verificar que se haya creado el usuario,
     * utilizado en las pruebas unitarias del programa.
     * @param usuario usuario enviado por la clase ComunicaUsuarioConDatos del paquete modelo.
     * @return: boolean.
     **/

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

    /**
     * Método privado que utiliza ComunicaUsuarioConDatos
     * para recuperar los usuarios de la base de datos.
     * @return ArrayList<Usuario>.
     * @throws SQLException
     **/
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

    /**
     * Método público para recuperar usuarios de la base de datos. Utilizado
     * para las pruebas unitarias y la clase ComunicaUsuariosConDatos del paquete modelo.
     * @return ArrayList<Usuario>.
     */

    public ArrayList<Usuario> handleObtenerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = obtenerUsuarios();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Método que verifica que la contraseña ingresada sea la misma que está
     * en la base de datos, con ayuda del algoritmo Bcrypt.
     * @param resultSet los datos que se retiran de la base de datos.
     * @param contrasena cadena con la contraseña del usuario.
     * @return String.
     * @throws SQLException
     */

    //Nuevo nombre: VerificarContrasena.
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

    /**
     * Método privado que se comunica con la base de datos,
     * para retirar la información del usuario, que coincida el nombre o el correo.
     * @param correoONombreDeUsuario cadena con el nombre o el correo del usuario.
     * @param contrasena cadena con la contraseña del usuario.
     * @return String.
     * @throws SQLException
     */

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

    /**
     * Método publico que retorna mensajes de validación.
     * @param correoONombreDeUsuario cadena con el correo o el nombre del usuario.
     * @param contrasena cadena con la contraseña.
     * @return String.
     */
    public String handleIniciarSesion(String correoONombreDeUsuario, String contrasena) {
        try {
            return iniciarSesion(correoONombreDeUsuario, contrasena);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return "Error";
        }
    }
}