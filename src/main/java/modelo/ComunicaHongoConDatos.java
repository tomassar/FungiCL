package modelo;

import datos.ManejaDatosHongo;
import utilidades.Utilidades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ComunicaHongoConDatos {

    private static final ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.handleObtenerHongos ();
    }

    public static boolean crearHongo(String nombre, String geolocalizacion, String descripcion, ArrayList<String> categorias, File imagen) {

        //convertir el File imágen a un arreglo de bytes
        // Se crea un array de bytes del tamaño del archivo
        byte[] fileBytes = null;
        try {
            //rediomensiona la imágen a un tamaño adecuado
            if(imagen != null){
                fileBytes = Utilidades.redimensionar (imagen.getAbsolutePath ());
            }
        } catch (IOException e) {
            System.err.println ("Error: "+e);
        }

        //Convertir categorias String a TipoHongo
        ArrayList<TipoHongo> categoriasEnumeration = new ArrayList<> ();
        for (String categoria:
             categorias) {
            categoriasEnumeration.add(TipoHongo.valueOf (categoria));
        }
        return manejaDatosHongo.handleCrear (new Hongo (nombre, geolocalizacion, descripcion, categoriasEnumeration, EstadoHongo.POR_CONFIRMAR, fileBytes));

    }

    public static ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        return manejaDatosHongo.handleBuscarHongoPorNombre(busqueda);
    }
}
