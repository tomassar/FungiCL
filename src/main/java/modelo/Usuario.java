package modelo;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String clave;
    private String correo;
    private TipoUsuario categoria;
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void accederADatos(int opcion){

        /*
        ManejoDatosUsuario manejo = new ManejoDatosUsuario();

        switch (opcion){

            case 1:
                ManejoDatosUsuario.abrir(usuario);
                break;
            case 2:
                ManejoDatosUsuario.leer(usuario);
                break;
            case 3:
                if(categoria == TipoUsuario.ADMIN){
                    ManejoDatosUsuario.escribir(listaUsuarios);
                }
                break;
            case 4:
                if(categoria == TipoUsuario.ADMIN){
                    ManejoDatosUsuario.borrar(usuario);
                }
                break;
        }*/
    }

    public static ArrayList<Usuario> obtenerUsuarios(){
        return listaUsuarios;
    }

    public static void cargarUsuarios(){
        /*
        ManejoDatosUsuario = new ManejoDatosUsuario();
        ArrayList<String> datos = MaenjoDatosUsuario.leer();

        for(int i = 0; i < datos.size();i = i + 5){
            String nombre = datos.get(i);
            String clave = datos.get(i+1);
            String telefono = datos.get(i+2);
            String correo = datos.get(i+3);
            TipoUsuario categoria = TipoUsuario.valueOf(datos.get(i+4));
            Usuario usuario = new Usuario(nombre, clave, telefono, correo, categoria);
            listaUsuarios.add(usuario);
        }
        */
        //leer datos y agregárselo a la lista.
    }

    public static boolean confirmarUsuario(Usuario usuario){
        return listaUsuarios.contains(usuario);
    }

    public String getClave() {
        return clave;
    }
}
