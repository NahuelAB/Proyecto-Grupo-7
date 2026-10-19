/*
* EstacionMeteorologica.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

import java.util.ArrayList;
import java.util.List;


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
    private List<Sensor> sensores;

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
     * @param idEstacion identificador de la estacion.
     * @param nombre nombre de la estacion.
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
        this.sensores = new ArrayList<Sensor>();
    }

    /**
     * Devuelve el ID de la estacion.
     *
     * @return ID de estacion.
     */
    public String getIdEstacion() {
        return idEstacion;
    }

    /**
     * Establece el ID de la estacion.
     * 
     * @param idEstacion nuevo nombre de la estacion.
     */
    public void setIdEstacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    /**
     * Devuelve el nombre de la estacion.
     * 
     * @return nombre de la estacion.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre de la estacion.
     * 
     * @param nombre nuevo nombre de la estacion.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la latitud geografica.
     * 
     * @return latitud geografica.
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Establece la latitud geografica.
     * 
     * @param latitud nueva latitud geografica.
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    /**
     * Devuelve la longitud geografica.
     * 
     * @return longitud geografica.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Establece la longitud geografica.
     * 
     * @param longitud nueva longitud geografica.
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Devuelve la altitud geografica.
     * 
     * @return altitud geografica.
     */
    public double getAltitud() {
        return altitud;
    }

    /**
     * Establece la altitud geografica.
     * 
     * @param altitud nueva altitud geografica.
     */
    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    public void instalarSensor(Sensor s) {
        sensores.add(s);
    }


}
