package modelo;

import datos.ManejaDatosUsuario;
import validaciones.*;
import java.util.ArrayList;

public class ComunicaUsuarioConDatos {
    private static ManejaDatosUsuario manejoDatosUsuario = new ManejaDatosUsuario();

    public static Usuario inicarSesion(String nombreDeUsuarioText, String contrasenaTexto) {
        return manejoDatosUsuario.iniciarSesion(nombreDeUsuarioText, contrasenaTexto);
    }

    public static ArrayList<Usuario> obtenerUsuarios(){
        return manejoDatosUsuario.obtenerUsuarios ();
    }

    public static String crearCuenta(String[] datos){
        Usuario usuario = new Usuario(datos[0], datos[1], datos[2]);
        for (String dato:
             datos) {
            if(ValidaDatosUsuario.esVacio (dato)){
                return "Por favor, llene los campos faltantes";
            }

            if(!ValidaDatosUsuario.clavesCoinciden (datos[1],datos[3])){
                return "Las contraseñas no coinciden";
            }

            if(!ValidaDatosUsuario.esEmail(datos[2])){
                return "El email no parece ser correcto";
            }
        }
        if(manejoDatosUsuario.crear (usuario)){
            return "Usuario creado con éxito";
        }else{
            return "Nombre de usuario o correo ya existente";
        }
    }
}
