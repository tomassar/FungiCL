package modelo;

import datos.ManejaDatosHongo;
import datos.ManejaDatosUsuario;

import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.obtenerHongos ();
    }
}
