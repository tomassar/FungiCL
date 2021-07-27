package modelo;

import datos.ManejaDatosHongo;
import utilidades.Utilidades;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Autores: Proyecto FungiAraucania
 * Clase estática que comunica la clase Hongo con la clase ManejaDatosHongo.
 * Favor de revisar el paquete datos y la clase Hongo del paquete modelo.
 */
public class ComunicaHongoConDatos {

    private static final ManejaDatosHongo manejaDatosHongo = new ManejaDatosHongo();

    /**
     * Método público que retorna la lista de hongos desde ManejoDatosHongo.
     * @return
     */
    public static ArrayList<Hongo> obtenerHongos(){
        return manejaDatosHongo.handleObtenerHongos ();
    }

    /**
     * Método que crea un hongo y muestra en pantalla si se ha podido o no crear.
     * @param nombre
     * @param geolocalizacion
     * @param descripcion
     * @param categorias
     * @param imagen
     * @param jPanel
     * @return
     */
    public static boolean crearHongo(String nombre, String geolocalizacion, String descripcion, ArrayList<String> categorias, File imagen, JPanel jPanel) {
        if(nombre.isEmpty () || geolocalizacion.isEmpty () || descripcion.isEmpty ()){
            JOptionPane.showMessageDialog (jPanel, "Llene los campos faltantes");
            return false;
        }
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
        if(manejaDatosHongo.handleCrear (new Hongo (nombre, geolocalizacion, descripcion, categoriasEnumeration, EstadoHongo.POR_CONFIRMAR, fileBytes))){
            JOptionPane.showMessageDialog (jPanel, "Hongo agregado con éxito.");
            return true;
        }else{
            JOptionPane.showMessageDialog (jPanel, "Hongo ya existente en la base de datos.");
            return false;
        }
    }

    /**
     * Método público que retorna la lista de hongos especificada
     * en BuscarHongoPorNombre en el paquete datos.
     * @param busqueda
     * @return
     */

    public static ArrayList<Hongo> buscarHongosQueContengan(String busqueda) {
        return manejaDatosHongo.handleBuscarHongoPorNombre(busqueda);
    }
}
