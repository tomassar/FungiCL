package datos;

import modelo.ComunicaUsuarioConDatos;
import modelo.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestManejaDatosUsuario {

    private static ManejaDatosUsuario manejo;

    @BeforeEach
    void setUp() {
        manejo = new ManejaDatosUsuario();
    }

    @AfterEach
    void tearDown() {
        manejo = null;
        System.gc();
    }

    @Test
    @DisplayName("Test para verificar conexiones fallidas")
    void conexionFallida() {
        assertFalse(manejo.handleEstablecerConexion(null, "root", "3306"));
    }

    @Test
    @DisplayName("Test para verificar fallo al crear un usuario con un id pre-determinado")
    void falloAlCrearUsuario() {
        Usuario usuario = new Usuario(1, "Genérico", "contrasenia", "generico@generico.com", new Date (System.currentTimeMillis ()));
        assertFalse(manejo.handleCrear(usuario));
    }

    @Test
    @DisplayName("Test para verificar creación de un usuario exitosa")
    void crearUsuario() {
            Usuario usuario = new Usuario("genérico", "contrasenia", "generico@generico.com");
            String a= "genérico";
            assertEquals(a, usuario.getNombre());
    }

    @Test
    @DisplayName("Test para verificar fallo al iniciar sesión")
    void falloAlBuscarHongos() {
        assertEquals("Nombre de usuario o email no existe.",manejo.handleIniciarSesion("inexistente@generico.com", "contrasenia"));
    }

    @Test
    @DisplayName("Test para verificar fallo al iniciar sesión con un email inválido")
    void falloAlIniciarSesionConEmailInvalido() {
        assertEquals("Nombre de usuario o email no existe.",manejo.handleIniciarSesion("inexistente", "contrasenia"));
    }

    @Test
    @DisplayName("Test para verificar fallo al crear cuenta con contraseñas que no coinciden")
    void falloAlCrearCuentaConContraseñasQueNoCoinciden() {
        String[] datos = {"Nombre", "contraseña", "correo@correo.com", "confirmacontraseña"};
        assertEquals("<html><p style='color:red'>Las contraseñas no coinciden</p></html>",ComunicaUsuarioConDatos.crearCuenta (datos));
    }
}
