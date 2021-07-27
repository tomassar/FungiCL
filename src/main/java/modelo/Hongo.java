package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Hongo {
    private int id;
    private final String nombre;
    private final String geolocalizacion;
    private final String descripcion;
    private final Date fechaDeCreacion;
    private final EstadoHongo estado;
    private final ArrayList<TipoHongo> categorias;
    private final byte[] imagen;

    // Constructor para los hongos retirados de la bas de datos (ya tenían una fecha de creación y una id)
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

    //Constructor para los hongos a crear (la id es asignada automáticamente por la base de datos y la fecha también se asigna automáticamente en Java)
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

    public ArrayList<TipoHongo> getCategorias() {
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
        return nombre.equals(hongo.getNombre ()) && geolocalizacion.equals(hongo.getGeolocalizacion ()) && descripcion.equals (hongo.getDescripcion ()) && categorias.equals (hongo.getCategorias ()) && imagen == hongo.getImagen ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}