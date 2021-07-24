package modelo;

import datos.ManejaDatosHongo;
import datos.ManejaDatosUsuario;

import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.handleObtenerHongos ();
    }

    public static void crearHongo(String nombre, String geolocalizacion, String descripcion, String categorias) {
        manejaDatosHongo.handleCrear(new Hongo (nombre, geolocalizacion, descripcion, categorias, EstadoHongo.POR_CONFIRMAR));
    }

    public static ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        return manejaDatosHongo.handleBuscarHongoPorNombre(busqueda);
    }
}
