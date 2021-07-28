package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;
/**
 * @author Proyecto FungiAraucania
 * Clase que permite modelar el hongo
 **/
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
     * @author Proyecto FungiAraucania
     * Constructor para los hongos retirados de la base de datos (ya tenían una fecha de creación y una id).
     * @param id id del hongo
     * @param nombre nombre del hongo
     * @param geolocalizacion geolocalizacion del hongo
     * @param descripcion descripcion del hongo
     * @param categorias categorias del hongo
     * @param estado estado del hongo
     * @param fechaDeCreacion fecha de creación del hongo
     * @param imagen imagen del hongo
     **/
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
     * @author Proyecto FungiAraucania
     * Constructor para los hongos a crear (la id es asignada automáticamente por la base de datos y la fecha también se asigna automáticamente en Java)
     * @param nombre nombre del hongo
     * @param geolocalizacion geolocalizacion del hongo
     * @param descripcion descripcion del hongo
     * @param categorias categorias del hongo
     * @param estado estado del hongo
     * @param imagen imagen del hongo
     **/
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
     * @return id
     **/
    public int getId() {
        return id;
    }
    /**
     * @return estado
     **/
    public EstadoHongo getEstado() {
        return estado;
    }
    /**
     * @return nombre
     **/
    public String getNombre() {
        return nombre;
    }
    /**
     * @return geolocalizacion
     **/
    public String getGeolocalizacion() {
        return geolocalizacion;
    }
    /**
     * @return descripcion
     **/
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @return fechaDeCreacion
     **/
    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    /**
     * @return categorias
     **/
    public ArrayList<TipoHongo> getCategorias() {
        return categorias;
    }
    /**
     * @return imagen
     **/
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param o: Object
     * @return boolean
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hongo hongo = (Hongo) o;
        return nombre.equals(hongo.getNombre ()) && geolocalizacion.equals(hongo.getGeolocalizacion ()) && descripcion.equals (hongo.getDescripcion ()) && categorias.equals (hongo.getCategorias ());
    }

    /**
     * @return int
     **/
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}