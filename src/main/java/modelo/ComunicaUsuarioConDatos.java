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
        return manejoDatosUsuario.handleObtenerUsuario();
    }

    public static String crearCuenta(String[] datos){
        Usuario usuario = new Usuario(datos[0], datos[1], datos[2]);
        for (String dato:
             datos) {
            if(ValidaDatosUsuario.esVacio (dato)){
                return "<html><p style='color:red'>Por favor, llene los campos faltantes</p></html>";
            }

            if(!ValidaDatosUsuario.clavesCoinciden (datos[1],datos[3])){
                return "<html><p style='color:red'>Las contraseñas no coinciden</p></html>";
            }

            if(!ValidaDatosUsuario.esEmail(datos[2])){
                return "<html><p style='color:red'>El email no parece ser correcto</p></html>";
            }
        }
        if(manejoDatosUsuario.handleCrear (usuario)){
            return "<html><p style='color:blue'>Usuario creado con éxito</p></html>";
        }else{
            return "<html><p style='color:red'>Nombre de usuario o correo ya existente</p></html>";
        }
    }
}
