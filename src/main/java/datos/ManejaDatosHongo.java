package datos;

import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class ManejaDatosHongo {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    private static Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    private static Statement statement;

    public ManejaDatosHongo(){
        establecerConexion("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
    }

    public static boolean establecerConexion(String baseDatos, String usuario, String puerto){
        try {
            connection = DriverManager.getConnection(baseDatos, usuario, puerto);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    //Método que escribe un nuevo hongo en la base de datos
    public void crear(Hongo hongo) throws SQLException {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        //PreparedStatement es para evitar SQL Injection.
        String sql = "INSERT INTO `fungiaraucania`.`hongos` (`nombre`,`geolocalizacion`,`descripcion`,`fechadecreacion`, `categorias`, `estado`, `imagen`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, hongo.getNombre());
        preparedStatement.setNString(2, hongo.getGeolocalizacion());
        preparedStatement.setNString(3, hongo.getDescripcion());
        preparedStatement.setDate(4, hongo.getFechaDeCreacion());
        preparedStatement.setNString(5, hongo.getCategorias());
        preparedStatement.setNString(6, hongo.getEstado().name());
        byte[] bytesImagen = hongo.getImagen ();
        if(bytesImagen == null){
            //En caso de que sea null, se sube un null
            preparedStatement.setNull (7, Types.BLOB);
        }else {
            Blob blob = new javax.sql.rowset.serial.SerialBlob (hongo.getImagen ()); // Creando el blob que almacena los bytes de la imágen
            preparedStatement.setBlob (7, blob);
        }
        preparedStatement.executeUpdate();
    }

    //Método que maneja excepciones de crear.
    public boolean handleCrear(Hongo hongo) {
        try {
            crear(hongo);
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

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
            Blob blob = resultSet.getBlob ("imagen");
                //convertir blob a un arreglo de bytes
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                //Liberar el blob para liberar memoria
                blob.free();
                //ArrayList<TipoHongo> categorias = new ArrayList<> ();
                //for (String categoria:
                //        arrCategorias) {
                //    categorias.add(TipoHongo.valueOf(categoria.toUpperCase ()));
                //}
                hongos1.add(new Hongo(id, nombre, geolocalizacion, descripcion, categorias, estado, fechaDeSubida, blobAsBytes));
        }
        return hongos1;
    }
    public ArrayList<Hongo> handleObtenerHongos() {
        ArrayList<Hongo> hongos1 = new ArrayList<>();
        try {
            hongos1 = obtenerHongos();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return hongos1;
    }

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

            hongos2.add(new Hongo(id, nombre, geolocalizacion, descripcion, categorias, estado, fechaDeSubida));
        }
        return hongos2;
    }
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