package modelo;

import datos.ManejaDatosUsuario;
import validaciones.*;
/**
 * @author Fungi Araucania
 * Clase que se encarga de comunicar el modelo con la base de datos
 **/
public class ComunicaUsuarioConDatos {
    private static final ManejaDatosUsuario manejoDatosUsuario = new ManejaDatosUsuario ();

    /**
     * Método público que permite al usuario iniciar sesión
     *
     * @param nombreDeUsuarioText nombre de usuario provisto de vista
     * @param contrasenaTexto     clave proveniente de vista
     * @return String
     **/
    public static String inicarSesion(String nombreDeUsuarioText, String contrasenaTexto) {
        if (nombreDeUsuarioText.isEmpty () || contrasenaTexto.isEmpty ()) {
            return "Llene los campos vacíos.";
        }
        return manejoDatosUsuario.handleIniciarSesion (nombreDeUsuarioText, contrasenaTexto);
    }

    /**
     * Método público que permite al crearse una nueva cuenta
     *
     * @param datos datos del usuario proveniente de vista
     * @return String
     **/
    public static String crearCuenta(String[] datos) {
        Usuario usuario = new Usuario (datos[0], datos[1], datos[2]);
        for (String dato :
                datos) {
            if (ValidaDatosUsuario.esVacio (dato)) {
                return "<html><p style='color:red'>Por favor, llene los campos faltantes</p></html>";
            }

            if (!ValidaDatosUsuario.clavesCoinciden (datos[1], datos[3])) {
                return "<html><p style='color:red'>Las contraseñas no coinciden</p></html>";
            }

            if (!ValidaDatosUsuario.esEmail (datos[2])) {
                return "<html><p style='color:red'>El email no parece ser correcto</p></html>";
            }
        }
        String mensajeContrasena = AnalizaContrasenia.verificarContrasenia (datos[1]);
        AnalizaContrasenia.setMensaje (" ");
        System.out.println (mensajeContrasena);
        if (mensajeContrasena.contains ("INSEGURA")) {
            mensajeContrasena = "Contraseña insegura";
        }
        if (mensajeContrasena.contains ("REGULAR")) {
            mensajeContrasena = "Contraseña regular";
        }
        if (mensajeContrasena.contains ("MEDIANAMENTE SEGURA")) {
            mensajeContrasena = "Contraseña medianamente segura";
        }
        if (mensajeContrasena.equals ("Contraseña segura")) {
            if (manejoDatosUsuario.handleCrear (usuario)) {
                return "<html><p style='color:blue;'>Usuario creado con éxito</p></html>";
            } else {
                return "<html><p style='color:red'>Nombre de usuario o correo ya existente</p></html>";
            }
        } else {
            return "<html><p style='color:red'>" + mensajeContrasena + "</p></html>";
        }
    }
}