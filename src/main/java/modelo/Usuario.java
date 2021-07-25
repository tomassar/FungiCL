package modelo;

import datos.ManejaDatosUsuario;

import java.util.ArrayList;
import java.sql.Date;

public class Usuario {
    private int id;
    private final String nombre;
    private final String contrasena;
    private final String correo;
    private final Date fechaDeCreacion;

    public Usuario(int id, String nombre, String contrasena, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaDeCreacion = new Date (System.currentTimeMillis());
    }

    public Usuario(String nombre, String contrasena, String correo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaDeCreacion = new Date (System.currentTimeMillis());
    }

    public Usuario(int id, String nombre, String contrasena, String correo, Date fechaDeCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
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
}