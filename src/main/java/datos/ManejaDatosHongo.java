package datos;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class ManejaDatosHongo {
    //Conexión con la base de datos de MySQL, específicamente al esquema fungiaraucania
    Connection connection;
    //Representa un SQL statement (en lenguaje SQL)
    Statement statement;

    public ManejaDatosHongo(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fungiaraucania", "root", "3306");
            statement = connection.createStatement();
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    //Método que escribe un nuevo hongo en la base de datos
    public void crear(Hongo hongo) {
        //executeUpdate es para ejecutar comandos SQL que manipulan los datos de la base de datos, y no retornan nada.
        //PreparedStatement es para evitar SQL Injection.
        try {
            String sql = "INSERT INTO `fungiaraucania`.`hongos` (`nombre`,`geolocalizacion`,`descripcion`,`fechadecreacion`, `categorias`, `estado`) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setNString (1, hongo.getNombre());
            preparedStatement.setNString (2, hongo.getGeolocalizacion ());
            preparedStatement.setNString (3, hongo.getDescripcion ());
            preparedStatement.setDate (4, hongo.getFechaDeCreacion ());
            preparedStatement.setNString (5, hongo.getCategorias ());
            preparedStatement.setNString (6, hongo.getEstado ().name ());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Hongo> obtenerHongos(){
        ArrayList<Hongo> hongos = new ArrayList<> ();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hongos");
            while(resultSet.next()) { //Si es que hay hongos entonces retorna true
                int id = resultSet.getInt ("id");
                String nombre = resultSet.getString ("nombre");
                String geolocalizacion = resultSet.getString ("geolocalizacion");
                String descripcion = resultSet.getString ("descripcion");
                Date fechaDeSubida = resultSet.getDate ("fechadecreacion");
                EstadoHongo estado = EstadoHongo.valueOf (resultSet.getString ("estado").toUpperCase());
                String categorias = resultSet.getString ("categorias");
                //ArrayList<TipoHongo> categorias = new ArrayList<> ();
                //for (String categoria:
                //        arrCategorias) {
                //    categorias.add(TipoHongo.valueOf(categoria.toUpperCase ()));
                //}
                hongos.add(new Hongo (id, nombre, geolocalizacion, descripcion, categorias, estado, fechaDeSubida));
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return hongos;
    }

    public ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        ArrayList<Hongo> hongos = new ArrayList<> ();
        try {
            String sql = "SELECT * FROM hongos WHERE nombre LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setNString (1, "%"+busqueda+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) { //Si es que hay hongos entonces retorna true
                int id = resultSet.getInt ("id");
                String nombre = resultSet.getString ("nombre");
                String geolocalizacion = resultSet.getString ("geolocalizacion");
                String descripcion = resultSet.getString ("descripcion");
                Date fechaDeSubida = resultSet.getDate ("fechadecreacion");
                EstadoHongo estado = EstadoHongo.valueOf (resultSet.getString ("estado").toUpperCase());
                String categorias = resultSet.getString ("categorias");
                //ArrayList<TipoHongo> categorias = new ArrayList<> ();
                //for (String categoria:
                //        arrCategorias) {
                //    categorias.add(TipoHongo.valueOf(categoria.toUpperCase ()));
                //}
                hongos.add(new Hongo (id, nombre, geolocalizacion, descripcion, categorias, estado, fechaDeSubida));
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return hongos;
    }
}
