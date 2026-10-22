/*
* EstacionMeteorologica.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ar.edu.uner.model.enums.*;


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
    private EstadoOperativo estadoOp;
    private TipoEstacion tipoEst;
    private ResponsableTecnico responsable;
    private ZonaGeografica zona;
    private List<Sensor> sensores;
    private List<Mantenimiento> mantenimientos;

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

    /**
     * Devuelve el tipo de estacion.
     * 
     * @return tipo de estacion.
     */
    public TipoEstacion getTipoEst() {
        return tipoEst;
    }

    /**
     * Establece el tipo de estacion.
     * 
     * @param tipoEst nuevo tipo de estacion.
     */
    public void setTipoEst(TipoEstacion tipoEst) {
        this.tipoEst = tipoEst;
    }

    /**
     * Devuelve el estado operativo.
     * 
     * @return estado operativo.
     */
    public EstadoOperativo getEstadoOp() {
        return estadoOp;
    }

    /**
     * Establece el estado operativo.
     * 
     * @param estadoOp nuevo estado operativo.
     */
    public void setEstadoOp(EstadoOperativo estadoOp) {
        this.estadoOp = estadoOp;
    }

    /**
     * Devuelve el responsable tecnico de la estacion.
     * 
     * @return responsable tecnico.
     */
    public ResponsableTecnico getResponsable() {
        return responsable;
    }

    /**
     * Establece al responsable tecnico de la estacion.
     * 
     * @param responsable nuevo responsable tecnico.
     */
    public void setResponsable(ResponsableTecnico responsable) {
        this.responsable = responsable;
    }

    public ZonaGeografica getZona() {
        return zona;
    }

    public void setZona(ZonaGeografica zona) {
        this.zona = zona;
    }

    /**
     * Devuelve los sensores anidados a la estacion.
     * 
     * @return sensores anidados.
     */
    public List<Sensor> getSensores() {
        return sensores;
    }

    /**
     * Establece los sensores anidados a la estacion.
     * 
     * @param sensores nuevos sensores anidados.
     */
    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    /**
     * Devuelve la lista de mantenimientos de la estacion.
     * 
     * @return lista de mantenimientos de la estacion.
     */
    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    /**
     * Establece la lista de mantenimientos de la estacion.
     * 
     * @param mantenimientos nueva lista de mantenimientos.
     */
    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = new ArrayList<>(mantenimientos);
    }

    /**
     * Agrega un nuevo mantenimiento.
     * @param idMantenimiento identificador del mantenimiento
     * @param fecha fecha en que se realizo el mantenimiento 
     * @param tipo tipo de mantenimiento realizado
     * @param descripcion descripción del mantenimiento
     */
    public void agregarMantenimiento(String idMantenimiento, LocalDate fecha, 
                                     TipoMantenimiento tipo, String descripcion) {
        mantenimientos.add(new Mantenimiento(idMantenimiento, fecha, tipo, descripcion));

    }

    /**
     * Remueve mantenimiento pasado por parametro.
     * 
     * @param m mantenimiento a remover.
     */
    public void removerMantenimiento(Mantenimiento m) {
        mantenimientos.remove(m);
    }

    /**
     * Agrega nuevo sensor a la estacion.
     * 
     * @param s nuevo sensor agregado.
     */
    public void instalarSensor(Sensor s) {
        sensores.add(s);
        s.setEstacion(this);
    }

    /**
     * Remueve sensor pasado por parametro.
     * 
     * @param s sensor a remover.
     */
    public void retirarSensor(Sensor s) {
        s.setEstacion(null);
    }
}
