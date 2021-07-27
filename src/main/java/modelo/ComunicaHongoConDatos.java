package modelo;

import datos.ManejaDatosHongo;
import utilidades.Utilidades;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.handleObtenerHongos ();
    }

    public static void crearHongo(String nombre, String geolocalizacion, String descripcion, ArrayList<String> categorias, File imagen) {

        //convertir el File imágen a un arreglo de bytes
        // Se crea un array de bytes del tamaño del archivo
        byte[] fileBytes;
        try {
            //rediomensiona la imágen a un tamaño adecuado
            fileBytes = Utilidades.redimensionar (imagen.getAbsolutePath ());
        } catch (IOException e) {
            fileBytes = null;
            e.printStackTrace ();
        }

        //Convertir categorias String a TipoHongo
        ArrayList<TipoHongo> categoriasEnumeration = new ArrayList<> ();
        for (String categoria:
             categorias) {
            categoriasEnumeration.add(TipoHongo.valueOf (categoria));
        }
        try {
            manejaDatosHongo.crear (new Hongo (nombre, geolocalizacion, descripcion, categoriasEnumeration, EstadoHongo.POR_CONFIRMAR, fileBytes));
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public static ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        return manejaDatosHongo.handleBuscarHongoPorNombre(busqueda);
    }
}
