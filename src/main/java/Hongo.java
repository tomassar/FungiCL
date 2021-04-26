public class Hongo {
    private String nombre;
    private String geolocalizacion;
    private String image;

    public Hongo(String nombre, String geolocalizacion) {
        this.nombre = nombre;
        this.geolocalizacion = geolocalizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
