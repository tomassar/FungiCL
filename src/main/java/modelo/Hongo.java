package modelo;

import java.sql.Date;
import java.util.Objects;

public class Hongo {
    private int id;
    private final String nombre;
    private final String geolocalizacion;
    private final String descripcion;
    private final Date fechaDeCreacion;
    private final EstadoHongo estado;
    private final String categorias;
    private final byte[] imagen;

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado, Date fechaDeCreacion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.categorias = categorias;
        this.estado = estado;
        this.imagen = imagen;
    }

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.categorias = categorias;
        this.estado = estado;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
        this.imagen = imagen;
    }

    public Hongo(String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado, byte[] imagen) {
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.categorias = categorias;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
        this.imagen = imagen;
    }

    //Constructores por defecto en caso de que no haya una imágen, se pone como null. Importante.
    public Hongo(String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado) {
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.categorias = categorias;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
        this.imagen = null;
    }

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.categorias = categorias;
        this.estado = estado;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
        this.imagen = null;
    }

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, String categorias, EstadoHongo estado, Date fechaDeCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.categorias = categorias;
        this.estado = estado;
        this.imagen = null;
    }

    public int getId() {
        return id;
    }

    public EstadoHongo getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getCategorias() {
        return categorias;
    }

    public byte[] getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hongo hongo = (Hongo) o;
        return id == hongo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}