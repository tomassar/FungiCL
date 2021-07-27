package modelo;

import java.sql.Date;

public class Usuario {

    private int id;
    private final String nombre;
    private final String contrasena;
    private final String correo;
    private final Date fechaDeCreacion;

    //Constructor para aquellos usuarios que están siendo creados, y por tanto no tienen id ni fechaDeCreación (se crean automáticamente)
    public Usuario(String nombre, String contrasena, String correo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaDeCreacion = new Date (System.currentTimeMillis());
        this.id = -1;
    }

    //Constructor para aquellos usuarios que provienen de la base de datos y que tienen un id y una fecha de creación ya asignados.
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

    public Date getFechaDeCreacion(){
        return fechaDeCreacion;
    }
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public int getId() {
        return id;
    }
}
