package datos;

import modelo.EstadoHongo;
import modelo.Hongo;
import modelo.TipoHongo;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestManejaDatosHongo {

    private static ManejaDatosHongo manejo;

    @BeforeEach
    void setUp() {
        manejo = new ManejaDatosHongo();
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
        ArrayList<TipoHongo> genericas = new ArrayList<>();
        Hongo hongo = new Hongo("Genérico", "Genérico", "Descripción: Genérica", genericas, EstadoHongo.valueOf("POR_CONFIRMAR"), null);
        assertFalse(manejo.handleCrear(hongo));
    }

    @Test
    @DisplayName("Test para verificar creación de un hongo en la base de datos")
    void crearHongo() {
        manejo.handleEstablecerConexion("baseDatosPrueba", "root", "3306");
        ArrayList<TipoHongo> genericas = new ArrayList<>();
        Hongo hongo = new Hongo("Genérico", "Genérico", "Descripción: Genérica", genericas, EstadoHongo.valueOf("POR_CONFIRMAR"), null);
        assertTrue(manejo.handleCrear(hongo));
    }

    @Test
    @DisplayName("Test para verificar fallo al cargar hongos desde la base de datos")
    void FalloAlCargarHongos() {
        manejo.handleEstablecerConexion("baseDatosPrueba", "root", "3306");
        assertEquals(0, manejo.handleObtenerHongos().size());
    }

    @Test
    @DisplayName("Test para verificar carga de hongos exitosa")
    void CargarUsuarios() {
        manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
        assertEquals(1, manejo.handleObtenerHongos().size());
    }

    @Test
    @DisplayName("Test para verificar fallo al buscar hongos por nombre en la base de datos")
    void FalloAlBuscarHongos() {
        manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
        assertEquals(0, manejo.handleBuscarHongoPorNombre("inexistente").size());
    }

    @Test
    @DisplayName("Test para verifica busqueda de hongos por nombre en la base de datos")
    void BuscarHongos() {

        manejo.handleEstablecerConexion("baseDatosPrueba1", "root", "3306");
        assertEquals(1, manejo.handleBuscarHongoPorNombre("Genérico").size());
    }
}
