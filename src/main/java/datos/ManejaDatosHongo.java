package datos;

import modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class ManejaDatosHongo {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    Statement statement;
    ArrayList<Hongo> hongos1, hongos2;

    public ManejaDatosHongo() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //Método que escribe un nuevo hongo en la base de datos
    public void crear(Hongo hongo) throws SQLException {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        //PreparedStatement es para evitar SQL Injection.
        String sql = "INSERT INTO `fungiaraucania`.`hongos` (`nombre`,`geolocalizacion`,`descripcion`,`fechadecreacion`, `categorias`, `estado`) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setNString(1, hongo.getNombre());
        preparedStatement.setNString(2, hongo.getGeolocalizacion());
        preparedStatement.setNString(3, hongo.getDescripcion());
        preparedStatement.setDate(4, hongo.getFechaDeCreacion());
        preparedStatement.setNString(5, hongo.getCategorias());
        preparedStatement.setNString(6, hongo.getEstado().name());
        preparedStatement.executeUpdate();
    }
    //Método que maneja excepciones de crear.
    public void handleCrear(Hongo hongo) {
        try {
            crear(hongo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void obtenerHongos() throws SQLException {
        hongos1 = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hongos");
        while (resultSet.next()) { //Si es que hay hongos entonces retorna true
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String geolocalizacion = resultSet.getString("geolocalizacion");
            String descripcion = resultSet.getString("descripcion");
            Date fechaDeSubida = resultSet.getDate("fechadecreacion");
            EstadoHongo estado = EstadoHongo.valueOf(resultSet.getString("estado").toUpperCase());
            String categorias = resultSet.getString("categorias");
            //ArrayList<TipoHongo> categorias = new ArrayList<> ();
            //for (String categoria:
            //        arrCategorias) {
            //    categorias.add(TipoHongo.valueOf(categoria.toUpperCase ()));
            //}
            hongos1.add(new Hongo(id, nombre, geolocalizacion, descripcion, categorias, estado, fechaDeSubida));
        }
    }
    public ArrayList<Hongo> handleObtenerHongos() {
        try {
            obtenerHongos();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return hongos1;
    }

    public void buscarHongoPorNombre(String busqueda) throws SQLException {

        hongos2 = new ArrayList<>();
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
    }
    public ArrayList<Hongo> handleBuscarHongoPorNombre(String busqueda) {
        try {
            buscarHongoPorNombre(busqueda);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return hongos2;
    }
}