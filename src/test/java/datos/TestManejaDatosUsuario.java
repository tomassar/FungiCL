package datos;

import modelo.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Test para verificar fallo al crear un usuario en la base de datos")
    void falloAlCrearUsuario() {
        manejo.handleEstablecerConexion("baseDatosPrueba", "root", "3306");
        Usuario usuario = new Usuario(1, "Genérico", "contrasenia", "generico@generico.com");
        assertFalse(manejo.handleCrear(usuario));
    }

    @Test
    @DisplayName("Test para verificar creación de un usuario exitosa en la base de datos")
    void crearUsuario() {
            manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
            Usuario usuario = new Usuario("genérico", "contrasenia", "generico@generico.com");
            assertTrue(manejo.handleCrear(usuario));
    }

    @Test
    @DisplayName("Test para verificar fallo al cargar usuarios desde la base de datos")
    void falloAlCargarUsuarios() {
        manejo.handleEstablecerConexion("baseDatosPrueba", "root", "3306");
        assertEquals(0, manejo.handleObtenerUsuarios().size());
    }

    @Test
    @DisplayName("Test para verificar carga de usuarios exitosa")
    void CargarUsuarios() {
        manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
        assertEquals(1, manejo.handleObtenerUsuarios().size());
    }

    @Test
    @DisplayName("Test para verificar fallo al iniciar sesión")
    void falloAlBuscarHongos() {
        manejo.handleEstablecerConexion("baseDatosPrueba", "root", "3306");
        assertNull(manejo.handleIniciarSesion("inexistente@generico.com", "contrasenia"));
    }

    @Test
    @DisplayName("Test para verificar inicio de sesión exitosa")
    void BuscarHongos() {
        manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
        assertNull(manejo.handleIniciarSesion("generico@generico.com", "contrasenia"));
    }
}
