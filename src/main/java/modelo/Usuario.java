package modelo;

import java.sql.Date;
/**
 * @author Proyecto FungiAraucania
 * Clase que permite modelar el usuario
 **/
public class Usuario {

    private int id;
    private final String nombre;
    private final String contrasena;
    private final String correo;
    private final Date fechaDeCreacion;

    /**
     * @author Proyecto FungiAraucania
     * Constructor para aquellos usuarios que están siendo creados, y por tanto no tienen id ni fechaDeCreación (se crean automáticamente)
     * @param nombre nombre del usuario
     * @param contrasena clave del usuario
     * @param correo correo del usuario
     **/
    public Usuario(String nombre, String contrasena, String correo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaDeCreacion = new Date (System.currentTimeMillis());
        this.id = -1;
    }
    /**
     * @author Proyecto FungiAraucania
     * Constructor para aquellos usuarios que provienen de la base de datos y que tienen un id y una fecha de creación ya asignados.
     * @param id id del usuario
     * @param nombre nombre del usuario
     * @param contrasena clave del usuario
     * @param correo correo del usuario
     * @param fechaDeCreacion fecha de creación de la cuenta del usuario
     **/
    public Usuario(int id, String nombre, String contrasena, String correo, Date fechaDeCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    /**
     * @return contrasena
     **/
    public String getClave() {
        return contrasena;
    }
    /**
     * @return fechaDeCreacion
     **/
    public Date getFechaDeCreacion(){
        return fechaDeCreacion;
    }
    /**
     * @return nombre
     **/
    public String getNombre() {
        return nombre;
    }
    /**
     * @return correo
     **/
    public String getCorreo() {
        return correo;
    }
    /**
     * @return id
     **/
    public int getId() {
        return id;
    }
}
