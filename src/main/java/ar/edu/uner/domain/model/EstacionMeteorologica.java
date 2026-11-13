/*
* EstacionMeteorologica.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.enums.TipoMantenimiento;


/**
 * Estacion meteorologica perteneciente a una unica zona.
 * 
 * @author Guillermina
 */
public class EstacionMeteorologica {
    private static int contadorEstacion = 1;

    private int idEstacion;
    private String nombre;
    private double latitud;
    private double longitud;
    private double altitud;
    private EstadoOperativo estadoOp;
    private TipoEstacion tipoEst;
    private ResponsableTecnico responsable;
    private List<Sensor> sensores;
    private List<Mantenimiento> mantenimientos;

    /**
     * Crea una estacion meteorologica con valores predeterminados.
     */
    public EstacionMeteorologica() {
        this("NOMBRE PENDIENTE",
            0,
            0,
            0,
            null,
            null);
    }

    /**
     * Crea una estacion meteorologica con valores especificos.
     * 
     * @param nombre nombre de la estacion.
     * @param latitud latitud geografica expresada en grados decimales.
     * @param longitud longitud geografica expresada en grados decimales.
     * @param altitud altitud respecto del nivel del mar, expresada en metros.
     */
    public EstacionMeteorologica(String nombre,
                                double latitud, double longitud,
                                double altitud, EstadoOperativo estadoOp,
                                TipoEstacion tipoEst) {
        idEstacion = contadorEstacion;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.estadoOp = estadoOp;
        this.tipoEst = tipoEst;
        sensores = new ArrayList<Sensor>();
        mantenimientos = new ArrayList<Mantenimiento>();

        contadorEstacion++;
    }

    /**
     * Devuelve el ID de la estacion.
     *
     * @return ID de estacion.
     */
    public int getIdEstacion() {
        return idEstacion;
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
        this.mantenimientos = mantenimientos;
    }

    /**
     * Agrega un nuevo mantenimiento.
     * @param fecha fecha en que se realizo el mantenimiento 
     * @param tipo tipo de mantenimiento realizado
     * @param descripcion descripción del mantenimiento
     * @param responsableTecnico
     */
    public void agregarMantenimiento(LocalDate fecha,
                                     TipoMantenimiento tipo, String descripcion,
                                     ResponsableTecnico responsableTecnico) {
        mantenimientos.add(new Mantenimiento( fecha, tipo, 
                          descripcion, responsableTecnico));
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
    public boolean instalarSensor(Sensor s) {
        if (s != null && !this.sensores.contains(s)) {
            return this.sensores.add(s);
        }
        return false;
    }

    /**
     * Remueve sensor pasado por parametro.
     * 
     * @param s sensor a remover.
     */
    public boolean retirarSensor(Sensor s) {
        if (s != null && this.sensores.contains(s)) {
            return this.sensores.remove(s);
        }
        return false;
    }

    /**
     * Verifica que la estacion este operativa.
     * 
     * @return true si la estacion esta operativa,
     *         false en caso contrario.
     */
    public boolean estaOperativa() {
        return estadoOp == EstadoOperativo.OPERATIVO;
    }
}
