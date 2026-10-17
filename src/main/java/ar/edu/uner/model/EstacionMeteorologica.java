/*
* EstacionMeteorologica.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

/**
 * Estacion meteorologica perteneciente a una unica zona.
 * 
 * @author Guillermina
 */
public class EstacionMeteorologica {
    private String idEstacion;
    private String nombre;
    private double latitud;
    private double longitud;
    private double altitud;
    //private EstadoOperativo estado;
    //private TipoEstacion tipo;

    /**
     * Crea una estacion meteorologica con valores predeterminados.
     */
    public EstacionMeteorologica() {
        this("ID PENDIENTE", 
            "NOMBRE PENDIENTE",
            0,
            0,
            0);
    }

    /**
     * Crea una estacion meteorologica con valores especificos.
     * 
     * @param idEstacion identificador de la estacion meteorologica.
     * @param nombre nombre de la estacion meteorologica.
     * @param latitud latitud geografica expresada en grados decimales.
     * @param longitud longitud geografica expresada en grados decimales.
     * @param altitud altitud respecto del nivel del mar, expresada en metros.
     */
    public EstacionMeteorologica(String idEstacion, String nombre,
                                double latitud, double longitud,
                                double altitud) {
        this.idEstacion = idEstacion;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
    }

    public String getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }


}
