package modelo;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public class Hongo {
    private int id;
    private String nombre;
    private String geolocalizacion;
    private String descripcion;
    private Date fechaDeCreacion;
    private EstadoHongo estado;
    private ArrayList<TipoHongo> categorias = new ArrayList<>();

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, ArrayList<TipoHongo> categorias, EstadoHongo estado, Date fechaDeCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.fechaDeCreacion = fechaDeCreacion;
        this.categorias = categorias;
        this.estado = estado;
    }

    public Hongo(int id, String nombre, String geolocalizacion, String descripcion, ArrayList<TipoHongo> categorias, EstadoHongo estado) {
        this.id = id;
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
        this.descripcion = descripcion;
        this.categorias = categorias;
        this.estado = estado;
        this.fechaDeCreacion = new Date (System.currentTimeMillis ());
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
}