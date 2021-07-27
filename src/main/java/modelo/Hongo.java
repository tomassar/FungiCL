package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Autores: Proyecto FungiAraucania
 * Clase Hongo
 */
public class Hongo {

    private int id;
    private final String nombre;
    private final String geolocalizacion;
    private final String descripcion;
    private final Date fechaDeCreacion;
    private final EstadoHongo estado;
    private final ArrayList<TipoHongo> categorias;
    private final byte[] imagen;

    /**
     * Constructor para los hongos provenientes de la base de datos.
     * (Ya tienen una id y fecha de creación asociada).
     * @param id
     * @param nombre
     * @param geolocalizacion
     * @param descripcion
     * @param categorias
     * @param estado
     * @param fechaDeCreacion
     * @param imagen
     */
    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, ArrayList<TipoHongo> categorias, EstadoHongo estado, Date fechaDeCreacion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.categorias = categorias;
        this.estado = estado;
        this.imagen = imagen;
    }

    /**
     * Constructor para los hongos a crear. Tanto la id como la fecha de creación
     * son asignadas automáticamente. La id por defecto es -1, pero la base
     * de datos se encarga de darle una id final.
     * @param nombre
     * @param geolocalizacion
     * @param descripcion
     * @param categorias
     * @param estado
     * @param imagen
     */
    public Hongo(String nombre, String geolocalizacion, String descripcion, ArrayList<TipoHongo> categorias, EstadoHongo estado, byte[] imagen) {
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.categorias = categorias;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
        this.imagen = imagen;
        this.id = -1;
    }

    /**
     *
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */

    public EstadoHongo getEstado() {
        return estado;
    }

    /**
     *
     * @return
     */

    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    /**
     *
     * @return
     */

    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @return
     */

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    /**
     *
     * @return
     */

    public ArrayList<TipoHongo> getCategorias() {
        return categorias;
    }

    /**
     *
     * @return
     */

    public byte[] getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hongo hongo = (Hongo) o;
        return nombre.equals(hongo.getNombre ()) && geolocalizacion.equals(hongo.getGeolocalizacion ()) && descripcion.equals (hongo.getDescripcion ()) && categorias.equals (hongo.getCategorias ()) && imagen == hongo.getImagen ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}