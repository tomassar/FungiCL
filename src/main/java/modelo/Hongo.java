package modelo;

import java.io.File;
import java.util.ArrayList;

public class Hongo {

    private String nombre;
    private String geolocalizacion;
    private String nombreCientifico;
    private String descripcion;
    private File foto;
    private ArrayList<TipoHongo> categorias = new ArrayList<>();
    private static ArrayList<Hongo> listaHongos = new ArrayList<>();

    public void accederADatos(int opcion){

        /*ManejoDatosHongos manejo = new ManejoDatosHongos();

        switch (opcion){

            case 1:
                ManejoDatosHongos.abrir();
                break;
            case 2:
                ManejoDatosHongos.leer();
                break;
            case 3:
                if(categoria == TipoUsuario.ADMIN){
                    ManejoDatosHongos.escribir();
                }
                break;
            case 4:
                if(categoria == TipoUsuario.ADMIN){
                    ManejoDatosUsuario.borrar();
                }
                break;
        }*/
    }

    public static ArrayList<Hongo> obtenerHongo(){
        return listaHongos;
    }

    public static void cargarUsuarios(){
        /*
        ManejoDatosHongos = new ManejoDatosHongos();
        ArrayList<String> datos = MaenjoDatosHongos.leer();
        */
        //leer datos y agregárselo a la lista.
    }
}
