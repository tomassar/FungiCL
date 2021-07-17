package modelo;

import datos.ManejaDatosUsuario;

import java.util.ArrayList;

public class ComunicaUsuarioConDatos {
    private static ManejaDatosUsuario manejoDatosUsuario = new ManejaDatosUsuario();

    public static Usuario inicarSesion(String nombreDeUsuarioText, String contrasenaTexto) {
        return manejoDatosUsuario.iniciarSesion(nombreDeUsuarioText, contrasenaTexto);
    }

    public static ArrayList<Usuario> obtenerUsuarios(){
        return manejoDatosUsuario.obtenerUsuarios ();
    }
}
