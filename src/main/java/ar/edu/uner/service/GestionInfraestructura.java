/*
 * GestionInfraestructura.java
 *
 * Version 1.0
 *
 * 04/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.service;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ZonaGeografica;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la infraestructura meteorológica del sistema.
 * Permite registrar zonas geográficas (o cuencas hidrográficas),
 * estaciones meteorológicas y sensores.
 *
 * @author Maricela
 */
public class GestionInfraestructura {
    private List<ZonaGeografica> zonas;
    private List<EstacionMeteorologica> estaciones;
    private List<Sensor> sensores;

    //Constructor
    /**
     * Crea la gestión de infraestructura sin registros.
     * Las listas de zonas, estaciones y sensores se inicializan vacías.
     */
    public GestionInfraestructura() {
        this.zonas = new ArrayList<ZonaGeografica>();
        this.estaciones = new ArrayList<EstacionMeteorologica>();
        this.sensores = new ArrayList<Sensor>();
    }

    //Registrar
    /**
     * Registra una zona geográfica o cuenca hidrográfica.
     *
     * @param zona la zona a registrar
     * @return {@code true} si se registró correctamente, {@code false} si
     *         era nula o ya estaba registrada
     */
    public boolean registrarZona(ZonaGeografica zona) {
        if (zona != null && !this.zonas.contains(zona)) {
            return this.zonas.add(zona);
        }
        return false;
    }

    /**
     * Registra un sensor. El sensor queda registrado sin estar instalado
     * en ninguna estación.
     *
     * @param sensor el sensor a registrar
     * @return {@code true} si se registró correctamente, {@code false} si
     *         era nulo o ya estaba registrado
     */
    public boolean registrarSensor(Sensor sensor) {
        if (sensor != null && !this.sensores.contains(sensor)) {
            return this.sensores.add(sensor);
        }
        return false;
    }

    /**
     * Registra una estación meteorológica y la asocia a la zona indicada.
     *
     * @param estacion la estación a registrar
     * @param idZona identificador de la zona a la que pertenece la estación
     * @return {@code true} si se registró correctamente, {@code false} si la
     *         estación era nula, ya estaba registrada o no existe una zona
     *         con ese identificador
     */
    public boolean registrarEstacion(EstacionMeteorologica estacion, int idZona) {
        if (estacion == null || this.estaciones.contains(estacion)) {
            return false;
        }
        ZonaGeografica zona = this.buscarZona(idZona);
        if (zona == null) {
            return false;
        }
        zona.agregarEstacion(estacion);
        return this.estaciones.add(estacion);
    }

    //Buscar
    /**
     * Busca una zona registrada a partir de su identificador.
     *
     * @param idZona identificador de la zona buscada
     * @return la zona con ese identificador, o {@code null} si no hay
     *         ninguna zona registrada con ese identificador
     */
    public ZonaGeografica buscarZona(int idZona) {
        ZonaGeografica encontrada=null;
        for (ZonaGeografica z : this.zonas) {
            if (z.getIdZona() == idZona) {
                encontrada= z;
                break;
            }
        }
        return encontrada;
    }
        /**
     * Busca un sensor registrado a partir de su identificador.
     *
     * @param idSensor identificador del sensor buscado
     * @return el sensor con ese identificador, o {@code null} si no hay
     *         ningún sensor registrado con ese identificador
     */
    public Sensor buscarSensor(int idSensor) {
        Sensor encontrado = null;
        for (Sensor s : this.sensores) {
            if (s.getIdSensor() == idSensor) {
                encontrado = s;
                break;
            }
        }
        return encontrado;
    }
    
        /**
     * Busca una estación meteorológica registrada a partir de su identificador.
     *
     * @param idEstacion identificador de la estación buscada
     * @return la estación con ese identificador, o {@code null} si no hay
     *         ninguna estación registrada con ese identificador
     */
    public EstacionMeteorologica buscarEstacion(int idEstacion){
        EstacionMeteorologica encontrada=null;
        for (EstacionMeteorologica e:this.estaciones){
            if(e.getIdEstacion()==idEstacion){
                encontrada=e;
                break;
            }
        }
        return encontrada;
        
    } 
    
        /**
     * Instala un sensor registrado en una estación registrada.
     *
     * @param idSensor identificador del sensor a instalar
     * @param idEstacion identificador de la estación donde se instala el sensor
     * @return {@code true} si se instaló correctamente, {@code false} si no
     *         existe el sensor o la estación con esos identificadores
     */
    public boolean instalarSensor(int idSensor, int idEstacion) {
        Sensor sensor = this.buscarSensor(idSensor);
        EstacionMeteorologica estacion = this.buscarEstacion(idEstacion);
        if (sensor == null || estacion == null) {
            return false;
        }
        estacion.instalarSensor(sensor);
        return true;
    }
    
        /**
     * Busca la estación meteorológica en la que está instalado un sensor.
     *
     * @param s el sensor cuya estación se busca
     * @return la estación en la que está instalado el sensor, o {@code null}
     *         si el sensor no está instalado en ninguna estación registrada
     */

        public EstacionMeteorologica buscarEstacionDeSensor(Sensor s) {
        EstacionMeteorologica e = null;
        for (EstacionMeteorologica es : this.estaciones) {
            if (es.getSensores().contains(s)) {
                e = es;
                break;
            }
        }
        return e;
    }
}