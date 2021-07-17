package modelo;

import datos.ManejaDatosUsuario;

import java.util.ArrayList;
import java.sql.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String contrasena;
    private String correo;
    private TipoUsuario categoria;
    private Date fechaDeCreacion;

    public Usuario(int id, String nombre, String contrasena, String correo, TipoUsuario categoria) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.categoria = categoria;
        this.fechaDeCreacion = new Date (System.currentTimeMillis());;
    }

    public Usuario(int id, String nombre, String contrasena, String correo, TipoUsuario categoria, Date fechaDeCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.categoria = categoria;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getClave() {
        return contrasena;
    }
    public int getId() {
        return id;
    }

    public Date getFechaDeCreacion(){
        return fechaDeCreacion;
    }
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public TipoUsuario getCategoria() {
        return categoria;
    }
}
