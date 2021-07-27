package datos;

import modelo.*;
import utilidades.Utilidades;

import java.sql.*;
import java.util.ArrayList;
/**
 * @author Proyecto FungiAraucania
 * Clase que maneja los datos de los hongos, conectándose a la base de datos
 **/
public class ManejaDatosHongo {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    private static Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    private static Statement statement;

    /**
     * @author Proyecto FungiAraucania
     * Constructor de la clase ManejaDatosHongo.
     * Realiza conexión con la base de datos del proyecto.
     * No requiere parámetros.
     **/
    public ManejaDatosHongo() {
        handleEstablecerConexion("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
    }

    /**
     * Método privado que comunica la clase con la base de datos.
     * @param baseDatos String con la ubicación de la base de datos de la base de datos.
     * @param usuario String con el nombre del usuario.
     * @param puerto String con el puerto que utiliza la base de datos.
     * @throws SQLException
     * Favor de revisar la clase SQL de Java.
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
     * @return boolean.
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
     * Método privado que escribe un nuevo hongo en la base de datos.
     * @param hongo: Objeto de tipo Hongo. Revisar documentación de Hongo.
     * @throws SQLException En caso de que falle la base de datos
     **/
    public void crear(Hongo hongo) throws SQLException {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        //PreparedStatement es para evitar SQL Injection.
        String sql = "INSERT INTO `fungiaraucania`.`hongos` (`nombre`,`geolocalizacion`,`descripcion`,`fechadecreacion`, `categorias`, `estado`, `imagen`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, hongo.getNombre());
        preparedStatement.setNString(2, hongo.getGeolocalizacion());
        preparedStatement.setNString(3, hongo.getDescripcion());
        preparedStatement.setDate(4, hongo.getFechaDeCreacion());
        // Las categorías se guardan separadas por un ; en la base de datos.
        ArrayList<TipoHongo> categoriasArrList = hongo.getCategorias ();
        String categorias = Utilidades.crearCadenaCategoriasAPartirDeArrayList(categoriasArrList);
        preparedStatement.setNString(5, categorias);
        preparedStatement.setNString(6, hongo.getEstado().name());
        byte[] bytesImagen = hongo.getImagen ();
        if(bytesImagen == null){
            //En caso de que sea null, se sube un null
            preparedStatement.setNull (7, Types.BLOB);
        }else {
            Blob blob = Utilidades.convertirBytesABlob(bytesImagen); // Creando el blob que almacena los bytes de la imágen
            preparedStatement.setBlob (7, blob);
        }
        preparedStatement.executeUpdate();
    }
    /**
     * Método público que se encarga de verificar que se haya creado el hongo,
     * utilizado en las pruebas unitarias del programa y para mostrar
     * mensaje en pantalla de que se ha creado un hongo.
     * @param hongo hongo enviado por la clase ComunicaHongoConDatos del paquete modelo.
     * @return boolean.
     **/
    public boolean handleCrear(Hongo hongo) {
        try {
            if(hongo.getId () != -1){
                return false;
            }
            ArrayList<Hongo> hongos = handleObtenerHongos ();
            for (Hongo hongoBaseDeDatos:
                    hongos) {
                if(hongoBaseDeDatos.equals (hongo)){
                    return false;
                }
            }
            crear(hongo);
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método privado que utiliza ComunicaHongoConDatos
     * para recuperar los hongos de la base de datos.
     * @return ArrayList<Hongo>.
     * @throws SQLException
     **/
    private ArrayList<Hongo> obtenerHongos() throws SQLException {
        ArrayList<Hongo> hongos1 = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hongos");
        while (resultSet.next()) { //Si es que hay hongos entonces retorna true
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String geolocalizacion = resultSet.getString("geolocalizacion");
            String descripcion = resultSet.getString("descripcion");
            Date fechaDeSubida = resultSet.getDate("fechadecreacion");
            EstadoHongo estado = EstadoHongo.valueOf(resultSet.getString("estado").toUpperCase());
            String categorias = resultSet.getString("categorias");
            ArrayList<TipoHongo> categoriasArrList = Utilidades.convertirStringArrListCategorias (categorias);
            Blob blob = resultSet.getBlob ("imagen");
            byte[] blobAsBytes = Utilidades.convertirBlobArregloBytes (blob);
            hongos1.add(new Hongo(id, nombre, geolocalizacion, descripcion, categoriasArrList, estado, fechaDeSubida, blobAsBytes));
        }
        return hongos1;
    }
    /**
     * Método público para recuperar Hongos de la base de datos. Utilizado
     * para las pruebas unitarias y la clase ComunicaHongoConDatos del paquete modelo.
     * @return ArrayList
     */
    public ArrayList<Hongo> handleObtenerHongos() {
        ArrayList<Hongo> hongos1 = new ArrayList<>();
        try {
            hongos1 = obtenerHongos();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return hongos1;
    }
    /**
     * Método privado que retorna una lista de Hongos que contengan
     * la cadena indicada por el usuario. Favor revisar el paquete modelo.
     * @param busqueda cadena enviada por la clase ComunicaHongoConDatos desde el paquete modelo.
     * @return: ArrayList<Hongo>.
     * @throws SQLException
     **/
    private ArrayList<Hongo> buscarHongoPorNombre(String busqueda) throws SQLException {

        ArrayList<Hongo> hongos2 = new ArrayList<>();
        String sql = "SELECT * FROM hongos WHERE nombre LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, "%" + busqueda + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {//Si es que hay hongos entonces retorna true

            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String geolocalizacion = resultSet.getString("geolocalizacion");
            String descripcion = resultSet.getString("descripcion");
            Date fechaDeSubida = resultSet.getDate("fechadecreacion");
            EstadoHongo estado = EstadoHongo.valueOf(resultSet.getString("estado").toUpperCase());
            String categorias = resultSet.getString("categorias");
            String[] arrCategorias = categorias.split (";");
            ArrayList<TipoHongo> categoriasArrList = new ArrayList<> ();
            for (String categoria: arrCategorias) {
                categoriasArrList.add(TipoHongo.valueOf(categoria.toUpperCase ()));
            }
            Blob blob = resultSet.getBlob ("imagen");
            //convertir blob a un arreglo de bytes
            int blobLength = (int) blob.length();
            byte[] blobAsBytes = blob.getBytes(1, blobLength);
            //Liberar el blob para liberar memoria
            blob.free();

            hongos2.add(new Hongo(id, nombre, geolocalizacion, descripcion, categoriasArrList, estado, fechaDeSubida,blobAsBytes));
        }
        return hongos2;
    }
    /**
     * Método público que retorna una lista de Hongos que contengan
     * la cadena indicada por el usuario.
     * @param busqueda cadena enviada por el paquete modelo.
     * @return ArrayList
     */
    public ArrayList<Hongo> handleBuscarHongoPorNombre(String busqueda) {
        ArrayList<Hongo> hongos2 = new ArrayList<>();
        try {
            hongos2 = buscarHongoPorNombre(busqueda);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return hongos2;
    }

}