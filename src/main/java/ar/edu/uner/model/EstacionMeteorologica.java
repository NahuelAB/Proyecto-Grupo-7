package ar.edu.uner.model;
/**
 * Estacion meteorologica. Pertenece a solamente una
 * zona.
 */
public class EstacionMeteorologica {
    private String idEstacion;
    private String nombre;
    private double latitud;
    private double longitud;
    private double altitud;
    //private EstadoOperativo estado;
    //private TipoEstacion tipo;

    public EstacionMeteorologica() {
        this("ID PENDIENTE", 
            "NOMBRE PENDIENTE",
            0,
            0,
            0);
    }

    public EstacionMeteorologica(String idEstacion, String nombre,
                                double latitud, double longitud,
                                double altitud) {
        this.idEstacion = idEstacion;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
    }

    


}
