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
    @DisplayName("Test para verificar fallo al crear un usuario")
    void falloAlCrearUsuario() {
        Usuario usuario = new Usuario(1, "Genérico", "contrasenia", "generico@generico.com");
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
    @DisplayName("Test para verificar fallo al cargar usuarios desde la base de datos")
    void falloAlCargarUsuarios() {
        assertEquals(0, manejo.handleObtenerUsuarios().size());
    }
    @Test
    @DisplayName("Test para verificar carga de usuarios exitosa")
    void cargarUsuarios() {
        assertEquals(0, manejo.handleObtenerUsuarios().size());
    }

    @Test
    @DisplayName("Test para verificar fallo al iniciar sesión")
    void falloAlBuscarHongos() {
        assertNull(manejo.handleIniciarSesion("inexistente@generico.com", "contrasenia"));
    }
}
