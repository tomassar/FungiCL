package modelo;

import datos.ManejaDatosHongo;
import datos.ManejaDatosUsuario;

import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.obtenerHongos ();
    }

    public static void crearHongo(String nombre, String geolocalizacion, String descripcion, String categorias) {
        manejaDatosHongo.crear (new Hongo (nombre, geolocalizacion, descripcion, categorias, EstadoHongo.POR_CONFIRMAR));
    }
}
