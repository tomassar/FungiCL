package datos;

import modelo.EstadoHongo;
import modelo.Hongo;
import org.junit.jupiter.api.*;
import modelo.TipoHongo;

import java.sql.Date;
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
    void conexionFallida(){
        assertFalse(manejo.handleEstablecerConexion(null, "root", "3306"));
    }

    @Test
    @DisplayName("Test para verificar creación de un hongo con id")
    void crearHongo(){
        ArrayList<TipoHongo> genericas = new ArrayList<>();
       Hongo hongo = new Hongo(1, "Genérico", "Genérico", "Descripción: Genérica", genericas, EstadoHongo.valueOf("POR_CONFIRMAR"),new Date (System.currentTimeMillis ()),null);
        assertFalse(manejo.handleCrear(hongo));
    }

    @Test
    @DisplayName("Test para verificar fallo al buscar hongos por nombre en la base de datos")
    void FalloAlBuscarHongos(){

        assertEquals(0, manejo.handleBuscarHongoPorNombre("inexistente").size());
    }

    @Test
    @DisplayName("Test para verificar fallo al intentar crear un hongo ya existente")
    void falloAlCrearHongoYaExistente(){
        ArrayList<TipoHongo> tipoHongos = new ArrayList<> ();
        tipoHongos.add(TipoHongo.COMESTIBLE);
        Hongo hongo = new Hongo ("HongoPrueba", "GeolocalizacionPrueba", "DescripcionPrueba", tipoHongos,EstadoHongo.POR_CONFIRMAR,null);
        assertFalse(manejo.handleCrear (hongo));
    }
}
