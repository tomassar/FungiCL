package modelo;

import datos.ManejaDatosHongo;
import datos.ManejaDatosUsuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.handleObtenerHongos ();
    }

    public static void crearHongo(String nombre, String geolocalizacion, String descripcion, String categorias) {
        manejaDatosHongo.handleCrear(new Hongo (nombre, geolocalizacion, descripcion, categorias, EstadoHongo.POR_CONFIRMAR));
    }

    public static void crearHongoConImagen(String nombre, String geolocalizacion, String descripcion, String categorias, File imagen) {

        //convertir el File imágen a un arreglo de bytes
        // Se crea un array de bytes del tamaño del archivo
        byte[] fileBytes;
        try {
            //rediomensiona la imágen a un tamaño adecuado
            fileBytes = Utilidades.redimensionar (imagen.getAbsolutePath ());
            //fileBytes = Utilidades.read (imagen);
        } catch (IOException e) {
            fileBytes = null;
            e.printStackTrace ();
        }
        // Se pone el contenido del archivo en el array de bytes creado para que sea enviado.
        manejaDatosHongo.crear (new Hongo (nombre, geolocalizacion, descripcion, categorias, EstadoHongo.POR_CONFIRMAR, fileBytes));
    }

    public static ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        return manejaDatosHongo.handleBuscarHongoPorNombre(busqueda);
    }
}
